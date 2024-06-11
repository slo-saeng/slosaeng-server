package com.server.slosaeng.domain.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.member.dao.MemberRepository;
import com.server.slosaeng.domain.member.domain.Member;
import com.server.slosaeng.domain.member.dto.request.JoinDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public String save(JoinDto joinDto) {
		return memberRepository.save(Member.builder()
			.id(joinDto.getId())
			.password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
			.name(joinDto.getName())
			.build()).getId();
	}

	public Member findById(String id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Unexpected member"));
	}
}
