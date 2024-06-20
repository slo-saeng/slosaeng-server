package com.server.slosaeng.domain.elder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BloodType {

	RH_PLUS_A("RH+A", "RH+A"),
	RH_PLUS_B("RH+B", "RH+B"),
	RH_PLUS_AB("RH+AB", "RH+AB"),
	RH_PLUS_O("RH+O", "RH+O"),
	RH_MINUS_A("RH-A", "RH-A"),
	RH_MINUS_B("RH-B", "RH-B"),
	RH_MINUS_AB("RH-AB", "RH-AB"),
	RH_MINUS_O("RH-O", "RH-O");

	private String key;
	private String description;
}


