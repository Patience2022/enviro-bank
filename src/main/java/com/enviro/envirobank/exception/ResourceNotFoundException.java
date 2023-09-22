package com.enviro.envirobank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private final String resourceName;
    private final String filedName;
    private final String filedValue;


    public ResourceNotFoundException(String resourceName, String filedName, String filedValue) {

        super(String.format(" No %s found with %s : '%s'",resourceName,filedName,filedValue));

        this.resourceName = resourceName;
        this.filedName =filedName;
        this.filedValue = filedValue;

    }
}
