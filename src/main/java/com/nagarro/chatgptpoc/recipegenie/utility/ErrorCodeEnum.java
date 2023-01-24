package com.nagarro.chatgptpoc.recipegenie.utility;
public enum ErrorCodeEnum {
	RECIPE_NOT_FOUND(40401, "Recipe not found"),
    RECIPE_BAD_REQUEST(40001, "Bad Request"),
    INTERNAL_SERVER_ERROR(50001, "Internal Server Error");

    private final Integer errorCode;
    private final String errorMessage;

    ErrorCodeEnum(Integer errorCode, String errorMessage) {
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
