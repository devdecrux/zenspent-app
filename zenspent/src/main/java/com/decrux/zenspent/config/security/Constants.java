package com.decrux.zenspent.config.security;

import lombok.Getter;

@Getter
public enum Constants {

    BACKEND_LOGIN_PROCESSING_URL("/login"),
    BACKEND_REGISTER_ENDPOINT("/api/v1/user/register"),

    FRONTEND_LOGIN_URL("/frontend/login"),
    FRONTEND_REGISTER_URL("/frontend/register"),
    FRONTEND_STATIC_ASSETS("/frontend/assets/**"),
    FRONTEND_FAVICON("/frontend/favicon.ico"),
    FRONTEND_LANDING_PAGE("/frontend/index.html");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

}
