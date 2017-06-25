package com.ducnd.demo.security.regiseter;

import com.ducnd.demo.demo2.BaseManager;
import com.ducnd.mysql.tables.User;
import com.ducnd.mysql.tables.records.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by ducnd on 6/25/17.
 */
@Component
public class AjaxRegisterProvider implements AuthenticationProvider {
    @Autowired
    private BaseManager baseManager;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AjaxAuthenRegister register = (AjaxAuthenRegister) authentication;
        UserRecord userRecord = baseManager.getDslContext().selectFrom(User.USER).where(User.USER.USERNAME.eq(register.getPrincipal().getUsername())).fetchOne();
        if (userRecord != null) {
            throw new AuthenticationServiceException("user exsit");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = passwordEncoder.encode(register.getCredentials());
        int indexCollum = baseManager.getDslContext().insertInto(User.USER, User.USER.USERNAME, User.USER.PASSWORD, User.USER.TOKEN)
                .values(register.getPrincipal().getUsername(), passwordEncode, register.getPrincipal().getToken()).execute();
        if (indexCollum <= 0) {
            throw new AuthenticationServiceException("Can not insert user into database");
        }
        return new UsernamePasswordAuthenticationToken(register.getPrincipal(), register.getCredentials());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AjaxAuthenRegister.class);
    }
}
