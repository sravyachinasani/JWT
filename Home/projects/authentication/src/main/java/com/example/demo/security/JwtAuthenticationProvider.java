package com.example.demo.security;

import com.example.demo.model.JwtAuthenticationToken;
import com.example.demo.model.JwtUser;
import com.example.demo.model.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

 @Autowired
  private JwtValidator validator;
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken=(JwtAuthenticationToken)usernamePasswordAuthenticationToken;

        String token=jwtAuthenticationToken.getToken();

        JwtUser jwtUser= validator.validate(token);
        if(jwtUser==null)
        {
            throw new RuntimeException("jwt token is incorrect");
        }
        List<GrantedAuthority> grantedAuthorities =AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.getRole());
                new JwtUserDetails(jwtUser.getUsername(),jwtUser.getId(),token,grantedAuthorities);
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return  JwtAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
