package com.server.slosaeng.config.jwt;

import static org.assertj.core.api.Assertions.*;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.server.slosaeng.domain.member.dao.MemberRepository;
import com.server.slosaeng.domain.member.domain.Member;
import com.server.slosaeng.global.config.jwt.JwtProperties;
import com.server.slosaeng.global.config.jwt.TokenProvider;

import io.jsonwebtoken.Jwts;

@SpringBootTest
public class TokenProviderTest {
	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private JwtProperties jwtProperties;

	@Test
	@DisplayName("generateToken() : 토큰 생성 테스트")
	void generateTokenTest() {
		// given
		Member testMember = memberRepository.save(Member.builder()
			.id("test")
			.password("testPassword")
			.name("testName")
			.build()
		);
		// when
		String token = tokenProvider.generateToken(testMember, Duration.ofDays(14));
		// then
		String memberId = Jwts.parser()
			.setSigningKey(jwtProperties.getSecretKey())
			.parseClaimsJws(token)
			.getBody()
			.get("id", String.class);

		assertThat(memberId).isEqualTo(testMember.getId());
	}

	@Test
	@DisplayName("validToken() : 만료된 토큰 유효성 검사 테스트")
	void validToken_invalidTest() {
		// given
		String token = JwtFactory.builder()
			.expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
			.build()
			.createToken(jwtProperties);
		// when
		boolean result = tokenProvider.validateToken(token);
		// then
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName("validToken() : 토큰 유효성 검사 테스트")
	void validToken_validTest() {
		// given
		String token = JwtFactory.withDefaultValues()
			.createToken(jwtProperties);
		// when
		boolean result = tokenProvider.validateToken(token);
		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("getAuthentication() : 토큰으로 인증 객체 생성 테스트")
	void getAuthentication() {
		// given
		String memberId = "test";
		String token = JwtFactory.builder()
			.subject(memberId)
			.build()
			.createToken(jwtProperties);
		// when
		Authentication authentication = tokenProvider.getAuthentication(token);
		// then
		assertThat(((UserDetails)authentication.getPrincipal()).getUsername()).isEqualTo(memberId);
	}

	@Test
	@DisplayName("getMemberId() : 토큰에서 회원 아이디 추출 테스트")
	void getMemberId() {
		// given
		String memberId = "";
		String token = JwtFactory.builder()
			.claims(Map.of("id", memberId))
			.build()
			.createToken(jwtProperties);

		// when
		String result = tokenProvider.getMemberId(token);
		// then
		assertThat(result).isEqualTo(memberId);
	}
}
