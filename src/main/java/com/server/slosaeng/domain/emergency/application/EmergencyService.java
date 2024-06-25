package com.server.slosaeng.domain.emergency.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.elder.application.ElderService;
import com.server.slosaeng.domain.elder.domain.Elder;
import com.server.slosaeng.domain.emergency.dao.EmergencyRepository;
import com.server.slosaeng.domain.emergency.domain.Emergency;
import com.server.slosaeng.domain.emergency.dto.request.EmergencyRequestDto;
import com.server.slosaeng.domain.emergency.dto.request.EmergencyUpdateDto;
import com.server.slosaeng.domain.emergency.dto.response.EmergencyResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmergencyService {
	private final ElderService elderService;
	private final EmergencyRepository emergencyRepository;

	public Long register(EmergencyRequestDto emergencyRequestDto) {
		Elder elder = elderService.findById(emergencyRequestDto.getElderId());
		return emergencyRepository.save(Emergency.builder()
				.info(emergencyRequestDto.getInfo())
				.elder(elder)
				.build())
			.getId();
	}

	public EmergencyResponseDto findEmergencyById(Long emergencyId) {
		Emergency emergency = findById(emergencyId);
		return EmergencyResponseDto.builder()
			.id(emergency.getId())
			.info(emergency.getInfo())
			.elder(emergency.getElder())
			.build();
	}

	public List<EmergencyResponseDto> findAllEmergency() {
		return emergencyRepository.findAll().stream()
			.map(emergency -> EmergencyResponseDto.builder()
				.id(emergency.getId())
				.info(emergency.getInfo())
				.elder(emergency.getElder())
				.build())
			.collect(Collectors.toList());
	}

	public Page<EmergencyResponseDto> findAllEmergencyByPage(Pageable pageable) {
		return emergencyRepository.findAll(pageable)
			.map(emergency -> EmergencyResponseDto.builder()
				.id(emergency.getId())
				.info(emergency.getInfo())
				.elder(emergency.getElder())
				.build());
	}

	public void updateEmergency(Long id, EmergencyUpdateDto emergencyUpdateDto) {
		Emergency emergency = findById(id);
		emergency.updateInfo(emergencyUpdateDto.getInfo());
		emergencyRepository.save(emergency);
	}

	public void deleteEmergency(Long id) {
		emergencyRepository.deleteById(id);
	}

	private Emergency findById(Long id) {
		return emergencyRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Emergency request not found."));
	}

}
