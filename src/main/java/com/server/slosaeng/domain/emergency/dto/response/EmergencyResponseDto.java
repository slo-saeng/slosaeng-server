package com.server.slosaeng.domain.emergency.dto.response;

import com.server.slosaeng.domain.elder.domain.Elder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EmergencyResponseDto {
	private Long id;
	private String info;
	private Elder elder;
}
