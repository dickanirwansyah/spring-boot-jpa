package com.rnd.springapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptions extends RuntimeException {

    public ResourceNotFoundExceptions(){}

    public ResourceNotFoundExceptions(String entity, int id){
        super(entity + "id" + id + "not found.");
    }
}
