package com.akn.springbootfirstproject.config;

import com.akn.springbootfirstproject.model.ApplicationUser;
import com.akn.springbootfirstproject.repository.UserRepository;
import com.akn.springbootfirstproject.utils.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.akn.springbootfirstproject.constants.SecurityConstants.*;

public class JwtRefreshTokenAuthenticationFilter extends BasicAuthenticationFilter {

    private TokenUtils tokenUtils;

    private UserRepository userRepository;

    public JwtRefreshTokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenUtils tokenUtils, UserRepository userRepository) {
        super(authenticationManager);
        this.tokenUtils = tokenUtils;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getRequestURI().endsWith("/refresh") && request.getHeader(REFRESH_TOKEN_HEADER).startsWith(TOKEN_PREFIX)) {
            String refreshToken = request.getHeader(REFRESH_TOKEN_HEADER);
            String username = tokenUtils.verifyRefreshToken(refreshToken);
            ApplicationUser applicationUser = userRepository.getByUsername(username);
            String accessToken = tokenUtils.createAccessToken(applicationUser);
            response.addHeader(HEADER_STRING, accessToken);
        }
        chain.doFilter(request, response);
    }
}
