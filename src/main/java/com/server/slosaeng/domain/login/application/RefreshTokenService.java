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

	public void save(String memberId, String refreshToken) {
		if (isExistRefreshToken(memberId)) {
			RefreshToken refreshTokenEntity = findByMemberId(memberId);
			refreshTokenEntity.update(refreshToken);
		} else {
			RefreshToken newRefreshToken = new RefreshToken(memberId, refreshToken);
			refreshTokenRepository.save(newRefreshToken);
		}
	}

	public void delete(String memberId) {
		refreshTokenRepository.deleteByMemberId(memberId);
	}

	private boolean isExistRefreshToken(String memberId) {
		return refreshTokenRepository.existsByMemberId(memberId);
	}

	private RefreshToken findByMemberId(String memberId) {
		return refreshTokenRepository.findByMemberId(memberId)
			.orElseThrow(() -> new IllegalArgumentException("Unexpected refresh token"));
	}
}
