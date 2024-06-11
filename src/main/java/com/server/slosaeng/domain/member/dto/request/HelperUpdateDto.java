package com.server.slosaeng.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelperUpdateDto {

	@NotNull
	@Schema(description = "비밀번호", example = "1234!")
	private String password;

	@NotNull
	@Schema(description = "이름", example = "이한음")
	private String name;

	@NotNull
	@Schema(description = "전화번호", example = "010-1234-5678")
	private String phone;

	@NotNull
	@Schema(description = "주민번호", example = "030811-1234567")
	private String idNumber;
}
