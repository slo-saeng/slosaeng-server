package com.server.slosaeng.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.slosaeng.SlosaengApplication;
import com.server.slosaeng.config.jwt.JwtFactory;
import com.server.slosaeng.domain.login.dao.RefreshTokenRepository;
import com.server.slosaeng.domain.login.domain.RefreshToken;
import com.server.slosaeng.domain.login.dto.request.CreateAccessTokenRequest;
import com.server.slosaeng.domain.member.dao.MemberRepository;
import com.server.slosaeng.domain.member.domain.Member;
import com.server.slosaeng.global.auth.jwt.JwtProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {SlosaengApplication.class})
public class TokenControllerTest {
	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	protected ObjectMapper objectMapper;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	JwtProperties jwtProperties;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	RefreshTokenRepository refreshTokenRepository;

	@BeforeEach
	public void mockMVcSetUp() {
		this.mockMvc = webAppContextSetup(context).build();
		memberRepository.deleteAll();
		refreshTokenRepository.deleteAll();
	}

	@Test
	@DisplayName("createNewAccessToken : 새로운 액세스 토큰 발급")
	public void createNewAccessToken() throws Exception {
		// given
		final String url = "/api/token";

		Member testMember = Member.builder()
			.id("test")
			.password("testPassword")
			.name("testName")
			.build();

		String refreshToken = JwtFactory.builder()
			.claims(Map.of("id", testMember.getId()))
			.build()
			.createToken(jwtProperties);

		refreshTokenRepository.save(new RefreshToken(testMember.getId(), refreshToken));

		CreateAccessTokenRequest request = new CreateAccessTokenRequest();
		request.setRefreshToken(refreshToken);
		final String requestBody = objectMapper.writeValueAsString(request);

		// when
		ResultActions resultActions = mockMvc.perform(post(url)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(requestBody));
		
		// then
		resultActions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.accessToken").isNotEmpty());
	}
}
