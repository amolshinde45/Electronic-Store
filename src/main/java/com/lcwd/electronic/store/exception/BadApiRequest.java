package com.lcwd.electronic.store.exception;

public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String message){
        super(message);
    }

    BadApiRequest(){
        super("Bad Request !!");
    }
}
