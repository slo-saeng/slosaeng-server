package com.server.slosaeng.domain.login.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.login.application.LoginService;
import com.server.slosaeng.domain.login.dto.request.CreateAccessTokenRequest;
import com.server.slosaeng.domain.login.dto.request.LoginRequestDto;
import com.server.slosaeng.domain.login.dto.response.CreateAccessTokenResponse;
import com.server.slosaeng.domain.login.dto.response.LoginResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "auth", description = "로그인 관리")
public class LoginController {
	private final LoginService loginService;

	@PostMapping("/login")
	@Operation(summary = "로그인")
	public ApiResponse<?> login(
		@RequestBody LoginRequestDto loginRequestDto
	) {
		LoginResponseDto loginResponseDto = loginService.login(loginRequestDto);
		return ApiResponse.success(loginResponseDto, "login success");
	}

	@PostMapping("/access-token")
	@Operation(summary = "새로운 액세스 토큰 발급")
	public ApiResponse<CreateAccessTokenResponse> createNewAccessToken(
		@RequestBody CreateAccessTokenRequest request
	) {
		String newAccessToken = loginService.createNewAccessToken(request.getRefreshToken());
		return ApiResponse.success(new CreateAccessTokenResponse(newAccessToken), "new access token created");
	}
}
