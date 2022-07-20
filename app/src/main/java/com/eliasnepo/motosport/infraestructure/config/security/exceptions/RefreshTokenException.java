package com.eliasnepo.motosport.infraestructure.config.security.exceptions;

public class RefreshTokenException extends RuntimeException {

    public RefreshTokenException(String message) {
        super(message);
    }
}
