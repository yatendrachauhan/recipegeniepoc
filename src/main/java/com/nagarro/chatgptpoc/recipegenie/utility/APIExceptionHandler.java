package com.nagarro.chatgptpoc.recipegenie.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponse> handleAPIException(APIException ex) {
        Integer errorCode = ex.getErrorCode()/100;
        ErrorResponse error = new ErrorResponse(errorCode, ex.getErrorMessage());
        return new ResponseEntity<>(error, HttpStatus.valueOf(errorCode));
    }
}

