package com.server.slosaeng.domain.member.application;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.member.dao.MemberRepository;
import com.server.slosaeng.domain.member.domain.Member;
import com.server.slosaeng.domain.member.domain.Role;
import com.server.slosaeng.domain.member.dto.request.AddMemberRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public String save(AddMemberRequestDto addMemberRequestDto) {
		if (isExistMember(addMemberRequestDto.getId())) {
			throw new IllegalArgumentException("Duplicate member id");
		}
		return memberRepository.save(Member.builder()
			.id(addMemberRequestDto.getId())
			.password(bCryptPasswordEncoder.encode(addMemberRequestDto.getPassword()))
			.name(addMemberRequestDto.getName())
			.role(Role.HELPER)
			.build()).getId();
	}

	public Member getCurrentMember() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = (UserDetails)principal;
			String username = ((UserDetails)principal).getUsername();
			return findById(username);
		} catch (Exception e) {
			throw new IllegalArgumentException("authenticated member not found. please login again.");
		}
	}

	public Member findById(String id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Unexpected member"));
	}

	private boolean isExistMember(String id) {
		return memberRepository.existsById(id);
	}
}
