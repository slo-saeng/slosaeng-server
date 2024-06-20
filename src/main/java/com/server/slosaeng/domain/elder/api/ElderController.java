package com.server.slosaeng.domain.elder.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.elder.application.ElderService;
import com.server.slosaeng.domain.elder.dto.request.ElderRequestDto;
import com.server.slosaeng.domain.elder.dto.response.ElderResponseDto;
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

	@GetMapping("/{elderId}")
	@Operation(summary = "고령자 조회")
	public ApiResponse<?> getElder(
		@PathVariable Long elderId
	) {
		ElderResponseDto elder = elderService.getElderById(elderId);
		return ApiResponse.success(elder, "Reading Elder succeed");
	}

	@PatchMapping("/{elderId}")
	@Operation(summary = "고령자 정보 수정")
	public ApiResponse<?> updateElder(
		@PathVariable Long elderId,
		@RequestBody ElderRequestDto elderRequestDto
	) {
		elderService.update(elderId, elderRequestDto);
		return ApiResponse.success(true, "Update Elder succeed");
	}

	@DeleteMapping("/{elderId}")
	@Operation(summary = "고령자 삭제")
	public ApiResponse<?> deleteElder(
		@PathVariable Long elderId
	) {
		elderService.delete(elderId);
		return ApiResponse.success(true, "Delete Elder succeed");
	}
}
