package com.server.slosaeng.domain.emergency.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmergencyUpdateDto {
	@NotNull
	@Schema(description = "사유", example = "응급상황 발생")
	private String info;

}
