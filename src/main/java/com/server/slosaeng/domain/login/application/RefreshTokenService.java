package com.server.slosaeng.domain.login.application;

import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.login.dao.RefreshTokenRepository;
import com.server.slosaeng.domain.login.domain.RefreshToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshToken findByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken)
			.orElseThrow(() -> new IllegalArgumentException("Unexpected refresh token"));
	}
}
