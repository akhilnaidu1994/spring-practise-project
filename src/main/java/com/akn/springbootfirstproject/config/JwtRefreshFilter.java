package com.akn.springbootfirstproject.config;

import com.akn.springbootfirstproject.model.ApplicationUser;
import com.akn.springbootfirstproject.repository.UserRepository;
import com.akn.springbootfirstproject.utils.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.akn.springbootfirstproject.constants.SecurityConstants.HEADER_STRING;
import static com.akn.springbootfirstproject.constants.SecurityConstants.REFRESH_TOKEN_HEADER;

public class JwtRefreshFilter extends BasicAuthenticationFilter {

    private TokenUtils tokenUtils;

    private UserRepository userRepository;

    public JwtRefreshFilter(AuthenticationManager authenticationManager,TokenUtils tokenUtils,UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.tokenUtils = tokenUtils;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String refreshToken = request.getHeader(REFRESH_TOKEN_HEADER);
        if (refreshToken != null && request.getRequestURI().endsWith("/refresh")) {
            String userName = tokenUtils.verifyRefreshToken(refreshToken);
            if (userName != null) {
                ApplicationUser user = userRepository.getByUsername(userName);
                String accessToken = tokenUtils.createAccessToken(user);
                response.addHeader(HEADER_STRING, accessToken);
            }
        }
        chain.doFilter(request,response);
    }
}
