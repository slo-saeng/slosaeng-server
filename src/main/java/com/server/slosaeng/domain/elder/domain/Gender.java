package com.server.slosaeng.domain.elder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

	MEN("MEN", "남성"),
	WOMEN("WOMEN", "여성");

	private String key;
	private String description;
}
