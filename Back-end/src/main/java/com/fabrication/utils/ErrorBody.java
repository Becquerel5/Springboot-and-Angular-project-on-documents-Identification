package com.fabrication.utils;

public class ErrorBody {
    private String message;
    private String code;
    private String resource;

    public ErrorBody() {
    }

    public ErrorBody(String message, String code, String resource) {
        this.message = message;
        this.code = code;
        this.resource = resource;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
