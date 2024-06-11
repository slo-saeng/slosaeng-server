package com.server.slosaeng.domain.login.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDto {
	private String accessToken;
	private String refreshToken;
}
