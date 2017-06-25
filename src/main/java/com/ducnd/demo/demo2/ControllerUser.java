package com.ducnd.demo.demo2;

import com.ducnd.demo.security.withoutloginregister.JwtAuthenticationToken;
import com.ducnd.mysql.tables.User;
import com.ducnd.mysql.tables.records.UserRecord;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.jooq.Condition;
import org.jooq.SelectLimitStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ducnd on 6/11/17.
 */

@RestController
@RequestMapping(value = "/api/user")
public class ControllerUser {
    private static final Logger LOG = Logger.getLogger(ControllerUser.class);
    @Autowired
    private BaseManager baseManager;

    @GetMapping(value = "getAll")
    @ResponseBody
    public List<com.ducnd.mysql.tables.pojos.User> getAllUser() {
        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        System.out.println("ControllerUser........" + token.getCredentials().getToken());
        List<com.ducnd.mysql.tables.pojos.User> users = new ArrayList<>();
        SelectLimitStep<UserRecord> userRecords = baseManager.getDslContext().selectFrom(User.USER).orderBy(User.USER.ID);
        for (UserRecord userRecord : userRecords) {
            com.ducnd.mysql.tables.pojos.User user = new com.ducnd.mysql.tables.pojos.User();
            user.setId(userRecord.getId());
            user.setPassword(userRecord.getPassword());
            user.setUsername(userRecord.getUsername());
            user.setToken(userRecord.getToken());
            users.add(user);
        }
        return users;
    }

    @PostMapping(value = "postUser", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    @ResponseBody
    public BaseResponse postUser(@RequestHeader(value = "X-Authorization", required = false) String headers, @RequestBody com.ducnd.mysql.tables.pojos.User user) {
        LOG.info("postUser header: " + headers);
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(new byte[128]);

        Condition condition = User.USER.USERNAME.eq(user.getUsername());
        UserRecord userRecord = baseManager.getDslContext().selectFrom(User.USER).where(condition).fetchOne();
        if (userRecord != null) {
            return new BaseResponse(HttpStatus.SC_CONFLICT, "username had");
        }
        baseManager.getDslContext().insertInto(DSL.table(User.USER.getName()), DSL.field(User.USER.USERNAME.getName()), DSL.field(User.USER.PASSWORD.getName()))
                .values(user.getUsername(), user.getPassword()).execute();
        return new BaseResponse(0, "ok");
    }

    @PostMapping(value = "login", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public BaseResponse login(@RequestBody com.ducnd.mysql.tables.pojos.User user) {
        String jws = Jwts.builder()
                .setIssuer("Stormpath")
                .setSubject("msilverman")
                .claim("name", user.getUsername())
                .claim("scope", "admins")
                // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
//                .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                .setIssuedAt(new Date())
                // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
                .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                )
                .compact();

//        LoginResponse loginResponse = new LoginResponse(0, "ok", Jwts.builder().setSubject(user.getUsername())
//                .claim("roles", "USER")
//                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
        LoginResponse loginResponse = new LoginResponse(0, "ok", jws);
        return loginResponse;
    }

    @GetMapping(value = "postJWT", params = {"username", "password"})
    @ResponseBody
    public BaseResponse getUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password, @RequestHeader(value = "X-Authorization") String headerToken) {

        try {
            decoded(headerToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("username: " + username);
        LOG.info("password: " + password);

        return new BaseResponse(0, "OK");
    }

    public static void decoded(String jWTEncoded) throws Exception {
        try {
            String[] split = jWTEncoded.split("\\.");
//            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            getJson(jWTEncoded);
//            Log.d("JWT_DECODED", "Body: " + );
        } catch (UnsupportedEncodingException e) {
            //Error
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
//        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
//        return new String(decodedBytes, "UTF-8");
        Claims body = Jwts.parser()
                .setSigningKey(TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="))
                .parseClaimsJws(strEncoded)
                .getBody();
        LOG.info("JWT_DECODED: " + body.get("name", String.class));
        return "dfd";
    }


    private static class LoginResponse extends BaseResponse {
        private String token;

        public LoginResponse(int status, String message, String token) {
            super(status, message);
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
