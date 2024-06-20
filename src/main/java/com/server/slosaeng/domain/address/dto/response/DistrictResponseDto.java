package com.server.slosaeng.domain.address.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DistrictResponseDto {
	private Long id;
	private String name;
}
