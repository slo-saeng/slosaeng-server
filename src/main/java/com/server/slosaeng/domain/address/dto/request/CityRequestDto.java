package com.server.slosaeng.domain.address.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequestDto {

	@NotNull
	@Schema(description = "지역 아이디", example = "1")
	private Long nationId;

	@NotNull
	@Schema(description = "도시명", example = "수원시")
	private String name;
}
