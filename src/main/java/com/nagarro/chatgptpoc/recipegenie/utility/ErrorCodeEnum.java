package com.nagarro.chatgptpoc.recipegenie.utility;
public enum ErrorCodeEnum {
	RECIPE_NOT_FOUND(40401, "Recipe not found"),
    USER_BAD_REQUEST(40001, "Bad Request"),
    RECIPE_BAD_REQUEST(40002, "Recipe Bad Request"),
    INTERNAL_SERVER_ERROR(50001, "Internal Server Error"),
    AUTHENTICATION_FAILED(40101,"Invalid Username or Password" ),
    RECIPE_ALREADY_EXIST(40901,"Recipe Already Exist");

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
