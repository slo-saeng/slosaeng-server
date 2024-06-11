package com.server.slosaeng.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

	HELPER("ROLE_HELPER", "Helper"),
	DOCTOR("ROLE_DOCTOR", "Doctor"),
	SUPER("ROLE_SUPER", "Super Administrator");

	private String key;
	private String description;
}
