package com.server.slosaeng.domain.institution.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InstitutionResponseDto {
	private Long id;
	private String code;
	private String name;
	private String type;
}
