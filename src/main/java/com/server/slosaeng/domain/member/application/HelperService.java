package com.server.slosaeng.domain.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.elder.domain.Elder;
import com.server.slosaeng.domain.login.application.RefreshTokenService;
import com.server.slosaeng.domain.member.dao.HelperRepository;
import com.server.slosaeng.domain.member.domain.Helper;
import com.server.slosaeng.domain.member.domain.Role;
import com.server.slosaeng.domain.member.dto.request.HelperRequestDto;
import com.server.slosaeng.domain.member.dto.request.HelperUpdateDto;
import com.server.slosaeng.domain.member.dto.response.HelperResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelperService {
	private final MemberService memberService;
	private final RefreshTokenService refreshTokenService;
	private final HelperRepository helperRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public String save(HelperRequestDto helperRequestDto) {
		if (isExistHelper(helperRequestDto.getId())) {
			throw new IllegalArgumentException("Duplicate helper id");
		}
		return helperRepository.save(Helper.builder()
			.id(helperRequestDto.getId())
			.password(bCryptPasswordEncoder.encode(helperRequestDto.getPassword()))
			.name(helperRequestDto.getName())
			.role(Role.HELPER)
			.phone(helperRequestDto.getPhone())
			.idNumber(helperRequestDto.getIdNumber())
			.build()).getId();
	}

	public HelperResponseDto findById(String helperId) {
		Helper helper = findHelperById(helperId);
		return HelperResponseDto.builder()
			.id(helper.getId())
			.name(helper.getName())
			.role(helper.getRole())
			.phone(helper.getPhone())
			.idNumber(helper.getIdNumber())
			.elders(helper.getElders())
			.build();
	}

	public void update(String helperId, HelperUpdateDto helperUpdateDto) {
		Helper helper = findHelperById(helperId);
		helper.updatePassword(bCryptPasswordEncoder.encode(helperUpdateDto.getPassword()));
		helper.updateName(helperUpdateDto.getName());
		helper.updatePhone(helperUpdateDto.getPhone());
		helper.updateIdNumber(helperUpdateDto.getIdNumber());
		helperRepository.save(helper);
	}

	@Transactional
	public void addElderId(Elder elder) {
		Helper helper = (Helper)memberService.getCurrentMember();
		helper.addElder(elder);
		helperRepository.save(helper);
	}

	@Transactional
	public void delete(String helperId) {
		refreshTokenService.delete(helperId);
		helperRepository.deleteById(helperId);
	}

	private Helper findHelperById(String helperId) {
		return helperRepository.findById(helperId).orElseThrow(
			() -> new IllegalArgumentException("Helper not found")
		);
	}

	private boolean isExistHelper(String helperId) {
		return helperRepository.existsById(helperId);
	}
}
