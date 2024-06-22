package com.server.slosaeng.domain.address.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NationResponseDto {
	private Long id;
	private String name;
}
