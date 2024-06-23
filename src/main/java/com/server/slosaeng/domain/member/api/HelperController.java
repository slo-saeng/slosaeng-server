package com.server.slosaeng.domain.member.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.member.application.HelperService;
import com.server.slosaeng.domain.member.dto.request.HelperRequestDto;
import com.server.slosaeng.domain.member.dto.request.HelperUpdateDto;
import com.server.slosaeng.domain.member.dto.response.HelperResponseDto;
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

	@GetMapping("")
	@Operation(summary = "보호자 목록 조회")
	public ApiResponse<?> getHelpers() {
		List<HelperResponseDto> helpers = helperService.findAll();
		return ApiResponse.success(helpers, "Reading helpers succeed");
	}

	@GetMapping("/page")
	@Operation(summary = "보호자 목록 페이징 조회")
	public ApiResponse<?> getHelpersPage(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		Pageable pageable = PageRequest.of(page, size);
		Page<HelperResponseDto> helpers = helperService.findAllByPage(pageable);
		return ApiResponse.success(helpers, "Reading helpers succeed");
	}

	// @Secured("ROLE_HELPER")
	@PatchMapping("/{helperId}")
	@Operation(summary = "보호자 정보 수정")
	public ApiResponse<?> updateHelper(
		@PathVariable String helperId,
		@RequestBody HelperUpdateDto helperUpdateDto
	) {
		helperService.update(helperId, helperUpdateDto);
		return ApiResponse.success(true, "Updating helper succeed");
	}

	// @PreAuthorize("isAuthenticated()")
	@DeleteMapping("/{helperId}")
	@Operation(summary = "보호자 탈퇴")
	public ApiResponse<?> deleteHelper(
		@PathVariable String helperId
	) {
		helperService.delete(helperId);
		return ApiResponse.success(true, "Deleting helper succeed");
	}

}
