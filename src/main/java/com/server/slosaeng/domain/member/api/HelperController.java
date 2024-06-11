package com.server.slosaeng.domain.member.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.member.application.HelperService;
import com.server.slosaeng.domain.member.dto.request.HelperRequestDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/helper")
@Tag(name = "Helper", description = "보호자 관리")
public class HelperController {
	private final HelperService helperService;

	@PostMapping("")
	@Operation(summary = "보호자 가입")
	public ApiResponse<?> createHelper(
		@RequestBody HelperRequestDto helperRequestDto
	) {
		String id = helperService.save(helperRequestDto);
		return ApiResponse.success(id, "Creating helper succeed");
	}

	@GetMapping("/{helperId}")
	@Operation(summary = "보호자 조회")
	public ApiResponse<?> getHelper(
		@PathVariable String helperId
	) {
		return ApiResponse.success(helperService.findById(helperId), "Reading helper succeed");
	}

	@PatchMapping("/{helperId}")
	@Operation(summary = "보호자 정보 수정")
	public ApiResponse<?> updateHelper(
		@PathVariable String helperId,
		@RequestBody HelperRequestDto helperRequestDto
	) {
		helperService.update(helperId, helperRequestDto);
		return ApiResponse.success(null, "Updating helper succeed");
	}

	@DeleteMapping("/{helperId}")
	@Operation(summary = "보호자 탈퇴")
	public ApiResponse<?> deleteHelper(
		@PathVariable String helperId
	) {
		helperService.delete(helperId);
		return ApiResponse.success(null, "Deleting helper succeed");
	}

}
