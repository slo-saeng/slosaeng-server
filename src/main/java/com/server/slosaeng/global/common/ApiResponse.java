package com.server.slosaeng.global.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";

	private String status;
	private T data;
	private String message;

	public static <T> ApiResponse<T> success(T data, String message) {
		return new ApiResponse<>(SUCCESS_STATUS, data, message);
	}

	public static ApiResponse<?> error(String message) {
		return new ApiResponse<>(ERROR_STATUS, null, message);
	}

	private ApiResponse(String status, T data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
}

