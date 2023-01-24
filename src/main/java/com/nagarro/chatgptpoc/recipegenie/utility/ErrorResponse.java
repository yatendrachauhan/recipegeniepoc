package com.nagarro.chatgptpoc.recipegenie.utility;
public class ErrorResponse {

    private final Integer errorCode;
    private final String errorMessage;

    public ErrorResponse(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

