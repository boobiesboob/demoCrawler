package com.example.demoCrawler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TokenMapper {
    // objects to deserialize
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("access_token")
    private String accessToken;

    //getters
    public String getAccessToken() {
        return accessToken;
    }
}
