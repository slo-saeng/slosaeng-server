package com.server.slosaeng.domain.member.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HelperResponseDto {
	private String id;
	private String name;
	private String phone;
	private String idNumber;
	private List<Long> elderIds;
}
