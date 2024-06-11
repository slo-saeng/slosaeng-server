package com.server.slosaeng.domain.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.member.dao.HelperRepository;
import com.server.slosaeng.domain.member.domain.Helper;
import com.server.slosaeng.domain.member.dto.request.HelperRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelperService {

	private final HelperRepository helperRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public String save(HelperRequestDto helperRequestDto) {
		return helperRepository.save(Helper.builder()
			.id(helperRequestDto.getId())
			.password(bCryptPasswordEncoder.encode(helperRequestDto.getPassword()))
			.name(helperRequestDto.getName())
			.phone(helperRequestDto.getPhone())
			.idNumber(helperRequestDto.getIdNumber())
			.build()).getId();
	}

}
