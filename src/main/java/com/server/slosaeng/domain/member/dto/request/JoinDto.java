package com.server.slosaeng.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {

	@NotNull
	@Schema(description = "아이디", example = "leehaneum")
	private String id;

	@NotNull
	@Schema(description = "비밀번호", example = "1234!")
	private String password;

	@NotNull
	@Schema(description = "이름", example = "이한음")
	private String name;

}
