package com.parousia.eatouts.data;

public class BaseResponse {

	Error error;

	public BaseResponse() {
		super();
	}

	public BaseResponse(Error error) {
		super();
		this.error = error;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
