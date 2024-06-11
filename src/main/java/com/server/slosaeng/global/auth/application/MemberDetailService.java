package com.server.slosaeng.global.auth.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.member.dao.MemberRepository;
import com.server.slosaeng.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public Member loadUserByUsername(String id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException((id)));
	}
}
