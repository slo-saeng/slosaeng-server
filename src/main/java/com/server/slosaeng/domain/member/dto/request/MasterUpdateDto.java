package com.server.slosaeng.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterUpdateDto {

	@NotNull
	@Schema(description = "비밀번호", example = "12345!")
	private String password;

	@NotNull
	@Schema(description = "기관이름", example = "경기병원")
	private String name;

	@NotNull
	@Schema(description = "요양기관번호", example = "1140292")
	private Long institutionNumber;
}
