package com.server.slosaeng.domain.emergency.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmergencyRequestDto {
	@NotNull
	@Schema(description = "사유", example = "응급상황 발생")
	private String info;

	@NotNull
	@Schema(description = "고령자 아이디", example = "1")
	private Long elderId;
}
