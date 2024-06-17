package com.server.slosaeng.domain.address.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NationRequestDto {

	@NotNull
	@Schema(description = "지역명", example = "경기도")
	private String name;

}
