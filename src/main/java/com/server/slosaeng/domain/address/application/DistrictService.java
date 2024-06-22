package com.server.slosaeng.domain.address.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.address.dao.DistrictRepository;
import com.server.slosaeng.domain.address.domain.City;
import com.server.slosaeng.domain.address.domain.District;
import com.server.slosaeng.domain.address.domain.Nation;
import com.server.slosaeng.domain.address.dto.request.DistrictRequestDto;
import com.server.slosaeng.domain.address.dto.response.DistrictResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistrictService {
	private final NationService nationService;
	private final CityService cityService;
	private final DistrictRepository districtRepository;

	public Long save(DistrictRequestDto districtRequestDto) {
		Nation nation = nationService.findById(districtRequestDto.getNationId());
		City city = null;
		if (districtRequestDto.getCityId() != null) {
			city = cityService.findById(districtRequestDto.getCityId());
		}
		return districtRepository.save(District.builder()
			.nation(nation)
			.city(city)
			.name(districtRequestDto.getName())
			.build()
		).getId();
	}

	public List<DistrictResponseDto> findAll() {
		return districtRepository.findAll().stream()
			.map(district -> DistrictResponseDto.builder()
				.id(district.getId())
				.name(district.getName())
				.build()
			).collect(Collectors.toList());
	}

	public List<DistrictResponseDto> findAllByCity(Long cityId) {
		return districtRepository.findAllByCityId(cityId).stream()
			.map(district -> DistrictResponseDto.builder()
				.id(district.getId())
				.name(district.getName())
				.build()
			).collect(Collectors.toList());
	}

	public List<DistrictResponseDto> findAllByNation(Long nationId) {
		return districtRepository.findAllByNationId(nationId).stream()
			.map(district -> DistrictResponseDto.builder()
				.id(district.getId())
				.name(district.getName())
				.build()
			).collect(Collectors.toList());
	}

	public void delete(Long districtId) {
		districtRepository.deleteById(districtId);
	}

	public District findById(Long districtId) {
		return districtRepository.findById(districtId)
			.orElseThrow(() -> new IllegalArgumentException("District not found"));
	}
}
