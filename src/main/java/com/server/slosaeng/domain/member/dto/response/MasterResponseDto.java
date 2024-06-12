package com.server.slosaeng.domain.member.dto.response;

import com.server.slosaeng.domain.member.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MasterResponseDto {
	private String id;
	private String name;
	private Role role;
	private Long institutionNumber;
}
