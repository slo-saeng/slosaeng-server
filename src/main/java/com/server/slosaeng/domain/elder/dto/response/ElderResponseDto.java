package com.server.slosaeng.domain.elder.dto.response;

import com.server.slosaeng.domain.address.domain.City;
import com.server.slosaeng.domain.address.domain.District;
import com.server.slosaeng.domain.address.domain.Nation;
import com.server.slosaeng.domain.elder.domain.BloodType;
import com.server.slosaeng.domain.elder.domain.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ElderResponseDto {
	private Long id;
	private String name;
	private String idNumber;
	private String phone;
	private Gender gender;
	private BloodType bloodType;
	private Nation nation;
	private City city;
	private District district;
	private String detailAddress;
	private String etc;
}
