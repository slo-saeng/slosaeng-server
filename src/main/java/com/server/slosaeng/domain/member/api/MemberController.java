package com.server.slosaeng.domain.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.member.application.MemberService;
import com.server.slosaeng.domain.member.dto.request.AddMemberRequestDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "Member", description = "회원 관리")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("")
	@Operation(summary = "회원 생성")
	public ApiResponse<?> createMember(
		@RequestBody AddMemberRequestDto addMemberRequestDto
	) {
		String id = memberService.save(addMemberRequestDto);
		return ApiResponse.success(id, "회원 가입 성공");
	}
}
