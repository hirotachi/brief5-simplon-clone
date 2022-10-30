package com.simplon.brief5simplonclone.annotations;

public enum Methods {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String value;

    Methods(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
