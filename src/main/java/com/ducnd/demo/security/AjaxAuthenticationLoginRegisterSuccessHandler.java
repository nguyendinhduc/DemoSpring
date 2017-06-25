package com.ducnd.demo.security;

import com.ducnd.Constants;
import com.ducnd.demo.demo2.BaseManager;
import com.ducnd.demo.demo2.BaseResponse;
import com.ducnd.demo.response.ResponseUtils;
import com.ducnd.mysql.tables.User;
import com.ducnd.mysql.tables.records.UserRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ducnd on 6/25/17.
 */
@Component
public class AjaxAuthenticationLoginRegisterSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String tokenString = ((UserContext) authenticationToken.getPrincipal()).getToken();
        Map<String, String> map = new HashMap<>();
        map.put(Constants.TOKEN_NAME, tokenString);
        objectMapper.writeValue(httpServletResponse.getWriter(), ResponseUtils.getBaseResponse(map));
        clearAuthenticationAttributes(request);
    }

}
