package com.server.slosaeng.domain.member.dto.response;

import java.util.List;

import com.server.slosaeng.domain.member.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HelperResponseDto {
	private String id;
	private String name;
	private Role role;
	private String phone;
	private String idNumber;
	private List<Long> elderIds;
}
