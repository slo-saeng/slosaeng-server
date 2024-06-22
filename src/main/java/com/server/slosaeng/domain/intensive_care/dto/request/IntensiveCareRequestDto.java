package com.server.slosaeng.domain.intensive_care.dto.request;

import com.server.slosaeng.domain.intensive_care.domain.Grade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntensiveCareRequestDto {

	@NotNull
	@Schema(description = "사유", example = "건강검진 필요")
	private String info;

	@NotNull
	@Schema(description = "등급", example = "관심")
	private Grade grade;

	@NotNull
	@Schema(description = "고령자 아이디", example = "1")
	private Long elderId;
}
