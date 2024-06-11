package com.server.slosaeng.domain.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.login.application.RefreshTokenService;
import com.server.slosaeng.domain.member.dao.HelperRepository;
import com.server.slosaeng.domain.member.domain.Helper;
import com.server.slosaeng.domain.member.dto.request.HelperRequestDto;
import com.server.slosaeng.domain.member.dto.response.HelperResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelperService {

	private final RefreshTokenService refreshTokenService;
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

	public HelperResponseDto findById(String helperId) {
		Helper helper = findHelperById(helperId);
		return HelperResponseDto.builder()
			.id(helper.getId())
			.name(helper.getName())
			.phone(helper.getPhone())
			.idNumber(helper.getIdNumber())
			.elderIds(helper.getElderIds())
			.build();
	}

	public void update(String helperId, HelperRequestDto helperRequestDto) {
		Helper helper = findHelperById(helperId);
		helper.updatePassword(bCryptPasswordEncoder.encode(helperRequestDto.getPassword()));
		helper.updateName(helperRequestDto.getName());
		helper.updatePhone(helperRequestDto.getPhone());
		helper.updateIdNumber(helperRequestDto.getIdNumber());
		helperRepository.save(helper);
	}

	public void delete(String helperId) {
		refreshTokenService.delete(helperId);
		helperRepository.deleteById(helperId);
	}

	private Helper findHelperById(String helperId) {
		return helperRepository.findById(helperId).orElseThrow(
			() -> new IllegalArgumentException("해당 보호자가 존재하지 않습니다.")
		);
	}
}
