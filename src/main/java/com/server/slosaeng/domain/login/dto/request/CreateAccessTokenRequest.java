package com.server.slosaeng.domain.login.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequest {
	private String refreshToken;
}
