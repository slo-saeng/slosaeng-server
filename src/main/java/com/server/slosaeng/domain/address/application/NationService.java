package com.server.slosaeng.domain.address.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.address.dao.NationRepository;
import com.server.slosaeng.domain.address.domain.Nation;
import com.server.slosaeng.domain.address.dto.request.NationRequestDto;
import com.server.slosaeng.domain.address.dto.response.NationResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NationService {
	private final NationRepository nationRepository;

	public Long save(NationRequestDto nationRequestDto) {
		return nationRepository.save(Nation.builder()
			.name(nationRequestDto.getName())
			.build()
		).getId();
	}

	public List<NationResponseDto> findAll() {
		return nationRepository.findAll().stream()
			.map(nation -> NationResponseDto.builder()
				.id(nation.getId())
				.name(nation.getName())
				.build()
			).collect(Collectors.toList());
	}

	public void delete(Long nationId) {
		nationRepository.deleteById(nationId);
	}

}
