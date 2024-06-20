package com.server.slosaeng.domain.elder.dto.request;

import com.server.slosaeng.domain.elder.domain.BloodType;
import com.server.slosaeng.domain.elder.domain.Gender;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElderRequestDto {

	@NotNull
	@Schema(description = "이름", example = "나고령")
	private String name;

	@NotNull
	@Schema(description = "주민번호", example = "030811-3******")
	private String idNumber;

	@NotNull
	@Schema(description = "전화번호", example = "01023234545")
	private String phone;

	@NotNull
	@Schema(description = "성별", example = "MEN")
	private Gender gender;

	@NotNull
	@Schema(description = "혈액형", example = "RH+A")
	private BloodType bloodType;

	@NotNull
	@Schema(description = "지역 아이디", example = "1")
	private Long nationId;

	@NotNull
	@Schema(description = "도시 아이디", example = "1")
	private Long cityId;

	@NotNull
	@Schema(description = "군/구 아이디", example = "1")
	private Long districtId;

	@NotNull
	@Schema(description = "상세주소", example = "대평로27 101-101")
	private String detailAddress;

	@Schema(description = "특이사항", example = "없음")
	private String etc;
}
