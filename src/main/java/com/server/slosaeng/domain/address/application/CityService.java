package com.server.slosaeng.domain.address.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.address.dao.CityRepository;
import com.server.slosaeng.domain.address.domain.City;
import com.server.slosaeng.domain.address.domain.Nation;
import com.server.slosaeng.domain.address.dto.request.CityRequestDto;
import com.server.slosaeng.domain.address.dto.response.CityResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {
	private final NationService nationService;
	private final CityRepository cityRepository;

	public Long save(CityRequestDto cityRequestDto) {
		Nation nation = nationService.findById(cityRequestDto.getNationId());
		return cityRepository.save(City.builder()
			.nation(nation)
			.name(cityRequestDto.getName())
			.build()
		).getId();
	}

	public List<CityResponseDto> findAll() {
		return cityRepository.findAll().stream()
			.map(city -> CityResponseDto.builder()
				.id(city.getId())
				.name(city.getName())
				.build()
			).collect(Collectors.toList());
	}

	public void delete(Long cityId) {
		cityRepository.deleteById(cityId);
	}

	protected City findById(Long cityId) {
		return cityRepository.findById(cityId)
			.orElseThrow(() -> new IllegalArgumentException("City not found"));
	}
}
