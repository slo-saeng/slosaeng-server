package com.server.slosaeng.domain.intensive_care.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {

	관심("관심", "관심"),
	주의("주의", "주의"),
	심각("심각", "심각");

	private String key;
	private String description;
}
