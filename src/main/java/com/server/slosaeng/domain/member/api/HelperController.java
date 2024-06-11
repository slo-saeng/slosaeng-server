package com.server.slosaeng.domain.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.member.application.HelperService;
import com.server.slosaeng.domain.member.dto.request.HelperRequestDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/helper")
@Tag(name = "Helper", description = "보호자 관리")
public class HelperController {
	private final HelperService helperService;

	@PostMapping("")
	public ApiResponse<?> createHelper(
		@RequestBody HelperRequestDto helperRequestDto
	) {
		String id = helperService.save(helperRequestDto);
		return ApiResponse.success(id, "보호자 가입 성공");
	}

}
