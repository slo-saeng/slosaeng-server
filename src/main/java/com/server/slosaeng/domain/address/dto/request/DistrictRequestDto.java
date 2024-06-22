package com.server.slosaeng.domain.address.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictRequestDto {

	@NotNull
	@Schema(description = "지역 아이디", example = "1")
	private Long nationId;
	
	@Schema(description = "도시 아이디", example = "1")
	private Long cityId;

	@NotNull
	@Schema(description = "군/구명", example = "장안구")
	private String name;
}
