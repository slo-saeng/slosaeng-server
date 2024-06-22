package com.server.slosaeng.domain.intensive_care.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.elder.application.ElderService;
import com.server.slosaeng.domain.elder.domain.Elder;
import com.server.slosaeng.domain.intensive_care.dao.IntensiveCareRepository;
import com.server.slosaeng.domain.intensive_care.domain.IntensiveCare;
import com.server.slosaeng.domain.intensive_care.dto.request.IntensiveCareRequestDto;
import com.server.slosaeng.domain.intensive_care.dto.request.IntensiveCareUpdateDto;
import com.server.slosaeng.domain.intensive_care.dto.response.IntensiveCareResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntensiveCareService {
	private final ElderService elderService;
	private final IntensiveCareRepository intensiveCareRepository;

	public Long register(IntensiveCareRequestDto intensiveCareRequestDto) {
		Elder elder = elderService.findById(intensiveCareRequestDto.getElderId());
		return intensiveCareRepository.save(IntensiveCare.builder()
			.info(intensiveCareRequestDto.getInfo())
			.grade(intensiveCareRequestDto.getGrade())
			.elder(elder)
			.build()).getId();
	}

	public IntensiveCareResponseDto getIntensiveCareById(Long intensiveCareId) {
		IntensiveCare intensiveCare = findById(intensiveCareId);
		return IntensiveCareResponseDto.builder()
			.id(intensiveCare.getId())
			.info(intensiveCare.getInfo())
			.grade(intensiveCare.getGrade())
			.elder(intensiveCare.getElder())
			.build();
	}

	public List<IntensiveCareResponseDto> getIntensiveCareList() {
		return intensiveCareRepository.findAll().stream()
			.map(intensiveCare -> IntensiveCareResponseDto.builder()
				.id(intensiveCare.getId())
				.info(intensiveCare.getInfo())
				.grade(intensiveCare.getGrade())
				.elder(intensiveCare.getElder())
				.build())
			.collect(Collectors.toList());
	}

	public void update(Long intensiveCareId, IntensiveCareUpdateDto intensiveCareUpdateDto) {
		IntensiveCare intensiveCare = findById(intensiveCareId);
		intensiveCare.updateInfo(intensiveCareUpdateDto.getInfo());
		intensiveCare.updateGrade(intensiveCareUpdateDto.getGrade());
		intensiveCareRepository.save(intensiveCare);
	}

	public void delete(Long intensiveCareId) {
		intensiveCareRepository.deleteById(intensiveCareId);
	}

	private IntensiveCare findById(Long intensiveCareId) {
		return intensiveCareRepository.findById(intensiveCareId)
			.orElseThrow(() -> new IllegalArgumentException("Info not found"));
	}
}
