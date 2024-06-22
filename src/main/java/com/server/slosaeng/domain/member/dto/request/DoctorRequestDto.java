package com.server.slosaeng.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequestDto {

	@NotNull
	@Schema(description = "아이디", example = "JeongAg")
	private String id;

	@NotNull
	@Schema(description = "비밀번호", example = "asdf!")
	private String password;

	@NotNull
	@Schema(description = "이름", example = "김정은")
	private String name;

	@NotNull
	@Schema(description = "전화번호", example = "010-1234-6789")
	private String phone;

	@NotNull
	@Schema(description = "직책", example = "전공의")
	private String position;

	@NotNull
	@Schema(description = "생년월일", example = "19990101")
	private String birth;

	@NotNull
	@Schema(description = "요양기관번호", example = "1140292")
	private Long institutionNumber;
}
