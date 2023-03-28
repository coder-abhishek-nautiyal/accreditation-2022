package com.accreditation.userservice.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_GATEWAY);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,HttpStatus status,
                                                                  WebRequest request){

        Map<String,String> errors=new HashMap<>();
        if(ex.getCause() instanceof InvalidFormatException){
            String targetType=((InvalidFormatException) ex.getCause()).getTargetType().getSimpleName();
            String sourceType=((InvalidFormatException) ex.getCause()).getValue().getClass().getSimpleName();
            ((InvalidFormatException) ex.getCause()).getPath().forEach(error->{
                String fieldName = error.getFieldName();
                String message=fieldName+" expected input type is "+targetType+" but entered value type is "+sourceType;
                errors.put(fieldName,message);
            });
        }

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

}
