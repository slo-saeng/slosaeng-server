package com.server.slosaeng.domain.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.login.application.RefreshTokenService;
import com.server.slosaeng.domain.member.dao.MasterRepository;
import com.server.slosaeng.domain.member.domain.Master;
import com.server.slosaeng.domain.member.domain.Role;
import com.server.slosaeng.domain.member.dto.request.MasterRequestDto;
import com.server.slosaeng.domain.member.dto.request.MasterUpdateDto;
import com.server.slosaeng.domain.member.dto.response.MasterResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MasterService {
	private final RefreshTokenService refreshTokenService;
	private final MasterRepository masterRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public String save(MasterRequestDto masterRequestDto) {
		if (isExistMaster(masterRequestDto.getId())) {
			throw new IllegalArgumentException("Already exist master");
		}
		return masterRepository.save(Master.builder()
			.id(masterRequestDto.getId())
			.password(bCryptPasswordEncoder.encode(masterRequestDto.getPassword()))
			.name(masterRequestDto.getName())
			.role(Role.MASTER)
			.institutionNumber(masterRequestDto.getInstitutionNumber())
			.build()).getId();
	}

	public MasterResponseDto findById(String masterId) {
		Master master = findMasterById(masterId);
		return MasterResponseDto.builder()
			.id(master.getId())
			.name(master.getName())
			.role(master.getRole())
			.institutionNumber(master.getInstitutionNumber())
			.build();
	}

	public void update(String masterId, MasterUpdateDto masterUpdateDto) {
		Master master = findMasterById(masterId);
		master.updatePassword(bCryptPasswordEncoder.encode(masterUpdateDto.getPassword()));
		master.updateName(masterUpdateDto.getName());
		master.updateInstitutionNumber(masterUpdateDto.getInstitutionNumber());
		masterRepository.save(master);
	}

	@Transactional
	public void delete(String masterId) {
		refreshTokenService.delete(masterId);
		masterRepository.deleteById(masterId);
	}

	private Master findMasterById(String masterId) {
		return masterRepository.findById(masterId)
			.orElseThrow(() -> new IllegalArgumentException("Not found master"));
	}

	private boolean isExistMaster(String masterId) {
		return masterRepository.existsById(masterId);
	}
}
