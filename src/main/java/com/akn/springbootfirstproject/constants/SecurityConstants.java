package com.akn.springbootfirstproject.constants;

public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String REFRESH_SECRET = "RefreshsecretTogenertaeHgt";
    public static final long EXPIRATION_TIME = 300000; // 5 minutes
    public static final long REFRESH_EXPIRATION_TIME = 86400000; //1 day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String REFRESH_TOKEN_HEADER = "refresh_token";
}
