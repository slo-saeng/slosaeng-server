package com.server.slosaeng.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

	NOT_APPROVED("ROLE_NOT_APPROVED", "Not Approved"),
	HELPER("ROLE_HELPER", "Helper"),
	DOCTOR("ROLE_DOCTOR", "Doctor"),
	MASTER("ROLE_MASTER", "Master"),
	SUPER("ROLE_SUPER", "Super Administrator");

	private String key;
	private String description;
}
