package com.server.slosaeng.domain.login.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

	@NotNull
	@Schema(description = "아이디", example = "leehaneum")
	private String id;

	@NotNull
	@Schema(description = "패스워드", example = "1234!")
	private String password;
}
