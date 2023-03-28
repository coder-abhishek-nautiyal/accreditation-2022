package com.accreditation.userservice.exception;

public class UserServiceException extends Exception {

    public UserServiceException(String msg) {
        super(msg);
    }

    public UserServiceException(String msg,Throwable cause) {
        super(msg, cause);
    }

}