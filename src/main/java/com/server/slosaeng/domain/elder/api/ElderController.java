package com.server.slosaeng.domain.elder.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.elder.application.ElderService;
import com.server.slosaeng.domain.elder.dto.request.ElderRequestDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/elder")
@Tag(name = "Elder", description = "고령자 관리")
public class ElderController {
	private final ElderService elderService;

	@PostMapping("")
	@Operation(summary = "고령자 등록")
	public ApiResponse<?> createElder(
		@RequestBody ElderRequestDto elderRequestDto
	) {
		Long id = elderService.save(elderRequestDto);
		return ApiResponse.success(id, "Register Elder succeed");
	}
}
