package com.nagarro.chatgptpoc.recipegenie.utility;

public class APIException extends Exception{
	
	private final Integer errorCode;
	private final String errorMessage;

	public APIException(ErrorCodeEnum errorCodeEnum, String customMessage) {
        super(errorCodeEnum.getErrorMessage());
        this.errorCode = errorCodeEnum.getErrorCode();
        this.errorMessage = errorCodeEnum.getErrorMessage() + " - " + customMessage;        
    }
	
	public APIException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getErrorMessage());
        this.errorCode = errorCodeEnum.getErrorCode();
        this.errorMessage = errorCodeEnum.getErrorMessage();        
    }

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
		
}
