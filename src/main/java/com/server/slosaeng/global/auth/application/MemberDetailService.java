package com.server.slosaeng.global.auth.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.member.dao.MemberRepository;
import com.server.slosaeng.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public Member loadUserByUsername(String username) {
		return memberRepository.findById(username)
			.orElseThrow(() -> new UsernameNotFoundException("Member not found"));
	}
}
