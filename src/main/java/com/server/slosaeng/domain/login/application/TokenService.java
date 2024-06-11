package com.server.slosaeng.domain.login.application;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.member.application.MemberService;
import com.server.slosaeng.domain.member.domain.Member;
import com.server.slosaeng.global.auth.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
	private final TokenProvider tokenProvider;
	private final RefreshTokenService refreshTokenService;
	private final MemberService memberService;

	public String createNewAccessToken(String refreshToken) {
		if (!tokenProvider.validateToken(refreshToken)) {
			throw new IllegalArgumentException("Unexpected refresh token");
		}

		String memberId = refreshTokenService.findByRefreshToken(refreshToken).getMemberId();
		log.info("createNewAccessToken: memberId = {}", memberId);
		Member member = memberService.findById(memberId);

		return tokenProvider.generateToken(member, Duration.ofHours(2));
	}
}
