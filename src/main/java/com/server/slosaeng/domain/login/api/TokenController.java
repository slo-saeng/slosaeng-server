package com.server.slosaeng.domain.login.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.login.application.TokenService;
import com.server.slosaeng.domain.login.dto.request.CreateAccessTokenRequest;
import com.server.slosaeng.domain.login.dto.response.CreateAccessTokenResponse;
import com.server.slosaeng.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TokenController {
	private final TokenService tokenService;

	@PostMapping("/api/token")
	public ApiResponse<CreateAccessTokenResponse> createNewAccessToken(
		@RequestBody CreateAccessTokenRequest request
	) {
		String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

		return ApiResponse.success(new CreateAccessTokenResponse(newAccessToken), "새로운 액세스 토큰 발급 성공");
	}
}
