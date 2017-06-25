package com.ducnd.demo.security.withoutloginregister;

import com.ducnd.demo.demo2.BaseManager;
import com.ducnd.mysql.tables.User;
import com.ducnd.mysql.tables.records.UserRecord;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


/**
 * Created by ducnd on 6/25/17.
 */

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BaseManager baseManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;

        Condition condition = User.USER.USERNAME.eq(token.getCredentials().getUsername());
        UserRecord userRecord = baseManager.getDslContext().selectFrom(User.USER).where(condition).fetchOne();
        if (userRecord == null) {
            throw new AuthenticationServiceException("fount fount user");
        }
        if ( !token.getCredentials().getToken().equals(userRecord.getToken())) {
            throw new AuthenticationServiceException("token invalidate");
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(JwtAuthenticationToken.class);
    }
}
