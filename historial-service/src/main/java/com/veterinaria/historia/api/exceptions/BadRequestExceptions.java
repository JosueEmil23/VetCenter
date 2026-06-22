package com.veterinaria.historia.api.exceptions;

public class BadRequestExceptions extends RuntimeException {
    public BadRequestExceptions(String message) {
        super(message);
    }
}
