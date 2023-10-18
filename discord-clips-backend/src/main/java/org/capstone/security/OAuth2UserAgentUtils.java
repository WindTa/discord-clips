package org.capstone.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;

public class OAuth2UserAgentUtils {
    public RequestEntity<?> withUserAgent(RequestEntity<?> request) {
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(request.getHeaders());
        headers.add(HttpHeaders.USER_AGENT, DISCORD_CLIPS_USER_AGENT);

        return new RequestEntity<>(request.getBody(), headers, request.getMethod(), request.getUrl());
    }

    private static final String DISCORD_CLIPS_USER_AGENT = ""
}
