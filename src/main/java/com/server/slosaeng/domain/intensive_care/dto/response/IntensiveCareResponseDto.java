package com.server.slosaeng.domain.intensive_care.dto.response;

import com.server.slosaeng.domain.elder.domain.Elder;
import com.server.slosaeng.domain.intensive_care.domain.Grade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class IntensiveCareResponseDto {
	private Long id;
	private String info;
	private Grade grade;
	private Elder elder;
}
