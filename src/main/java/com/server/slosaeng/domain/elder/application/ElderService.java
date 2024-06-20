package com.server.slosaeng.domain.elder.application;

import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.address.application.CityService;
import com.server.slosaeng.domain.address.application.DistrictService;
import com.server.slosaeng.domain.address.application.NationService;
import com.server.slosaeng.domain.address.domain.City;
import com.server.slosaeng.domain.address.domain.District;
import com.server.slosaeng.domain.address.domain.Nation;
import com.server.slosaeng.domain.elder.dao.ElderRepository;
import com.server.slosaeng.domain.elder.domain.Elder;
import com.server.slosaeng.domain.elder.dto.request.ElderRequestDto;
import com.server.slosaeng.domain.elder.dto.response.ElderResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElderService {
	private final NationService nationService;
	private final CityService cityService;
	private final DistrictService districtService;
	private final ElderRepository elderRepository;

	public Long save(ElderRequestDto elderRequestDto) {
		Nation nation = nationService.findById(elderRequestDto.getNationId());
		City city = cityService.findById(elderRequestDto.getCityId());
		District district = districtService.findById(elderRequestDto.getDistrictId());

		return elderRepository.save(Elder.builder()
			.name(elderRequestDto.getName())
			.idNumber(elderRequestDto.getIdNumber())
			.phone(elderRequestDto.getPhone())
			.gender(elderRequestDto.getGender())
			.bloodType(elderRequestDto.getBloodType())
			.nation(nation)
			.city(city)
			.district(district)
			.detailAddress(elderRequestDto.getDetailAddress())
			.etc(elderRequestDto.getEtc())
			.build()).getId();
	}

	public ElderResponseDto getElderById(Long elderId) {
		Elder elder = findById(elderId);
		return ElderResponseDto.builder()
			.id(elder.getId())
			.name(elder.getName())
			.idNumber(elder.getIdNumber())
			.phone(elder.getPhone())
			.gender(elder.getGender())
			.bloodType(elder.getBloodType())
			.nation(elder.getNation())
			.city(elder.getCity())
			.district(elder.getDistrict())
			.detailAddress(elder.getDetailAddress())
			.etc(elder.getEtc())
			.build();
	}

	public void update(Long elderId, ElderRequestDto elderRequestDto) {
		Nation nation = nationService.findById(elderRequestDto.getNationId());
		City city = cityService.findById(elderRequestDto.getCityId());
		District district = districtService.findById(elderRequestDto.getDistrictId());

		Elder elder = findById(elderId);
		elder.updateName(elderRequestDto.getName());
		elder.updateIdNumber(elderRequestDto.getIdNumber());
		elder.updatePhone(elderRequestDto.getPhone());
		elder.updateGender(elderRequestDto.getGender());
		elder.updateBloodType(elderRequestDto.getBloodType());
		elder.updateNation(nation);
		elder.updateCity(city);
		elder.updateDistrict(district);
		elder.updateDetailAddress(elderRequestDto.getDetailAddress());
		elder.updateEtc(elderRequestDto.getEtc());
		elderRepository.save(elder);
	}

	public void delete(Long elderId) {
		elderRepository.deleteById(elderId);
	}

	private Elder findById(Long elderId) {
		return elderRepository.findById(elderId)
			.orElseThrow(() -> new IllegalArgumentException("Elder not found"));
	}
}
