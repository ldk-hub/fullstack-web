package com.hodoledu.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class HodollogException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public HodollogException(String message, Throwable cause) {
        super(message, cause);
    }

    public HodollogException(String message) {
        super(message);
    }


    public abstract int statusCode();

    public void addValidation(String fieldName, String message){

    }

}
