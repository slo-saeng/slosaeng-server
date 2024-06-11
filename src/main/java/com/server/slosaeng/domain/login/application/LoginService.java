package com.server.slosaeng.domain.login.application;

import java.time.Duration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.login.dto.request.LoginRequestDto;
import com.server.slosaeng.domain.login.dto.response.LoginResponseDto;
import com.server.slosaeng.domain.member.application.MemberService;
import com.server.slosaeng.domain.member.domain.Member;
import com.server.slosaeng.global.auth.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final TokenProvider tokenProvider;
	private final RefreshTokenService refreshTokenService;
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		String id = loginRequestDto.getId();
		String password = loginRequestDto.getPassword();

		Member member = memberService.findById(id);
		if (!passwordEncoder.matches(password, member.getPassword())) {
			throw new IllegalArgumentException("password is not correct");
		}

		String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(7));
		String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));

		refreshTokenService.save(member.getId(), refreshToken);

		return LoginResponseDto.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public String createNewAccessToken(String refreshToken) {
		if (!tokenProvider.validateToken(refreshToken)) {
			throw new IllegalArgumentException("Unexpected refresh token");
		}

		String memberId = refreshTokenService.findByRefreshToken(refreshToken).getMemberId();
		Member member = memberService.findById(memberId);

		return tokenProvider.generateToken(member, Duration.ofHours(2));
	}
}
