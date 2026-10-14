package com.oauth2.api.exception;

public class UnsupportedGrantTypeException extends OAuth2Exception {

    public UnsupportedGrantTypeException() {

        super(

                "unsupported_grant_type",

                "Grant Type Not Supported");

    }

}