package com.server.slosaeng.domain.emergency.api;

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

import com.server.slosaeng.domain.emergency.application.EmergencyService;
import com.server.slosaeng.domain.emergency.dto.request.EmergencyRequestDto;
import com.server.slosaeng.domain.emergency.dto.request.EmergencyUpdateDto;
import com.server.slosaeng.domain.emergency.dto.response.EmergencyResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emergency")
@Tag(name = "Emergency", description = "긴급 도움 요청 관리")
public class EmergencyController {
	private final EmergencyService emergencyService;

	@PostMapping("")
	@Operation(summary = "긴급 도움 요청 등록")
	public ApiResponse<?> createEmergency(
		@RequestBody EmergencyRequestDto emergencyRequestDto
	) {
		Long id = emergencyService.register(emergencyRequestDto);
		return ApiResponse.success(id, "Register Emergency succeed");
	}

	@GetMapping("/{emergencyId}")
	@Operation(summary = "긴급 도움 요청 조회")
	public ApiResponse<?> getEmergency(
		@PathVariable Long emergencyId
	) {
		EmergencyResponseDto emergency = emergencyService.findEmergencyById(emergencyId);
		return ApiResponse.success(emergency, "Read Emergency succeed");
	}

	@GetMapping("")
	@Operation(summary = "긴급 도움 요청 목록 조회")
	public ApiResponse<?> getEmergencyList() {
		List<EmergencyResponseDto> emergencyList = emergencyService.findAllEmergency();
		return ApiResponse.success(emergencyList, "Read EmergencyList succeed");
	}

	@GetMapping("/page")
	@Operation(summary = "긴급 도움 요청 목록 페이징 조회")
	public ApiResponse<?> getEmergencyListByPage(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		Pageable pageable = PageRequest.of(page, size);
		Page<EmergencyResponseDto> emergencyList = emergencyService.findAllEmergencyByPage(pageable);
		return ApiResponse.success(emergencyList, "Read EmergencyList by page succeed");
	}

	@PatchMapping("/{emergencyId}")
	@Operation(summary = "긴급 도움 요청 정보 수정")
	public ApiResponse<?> updateEmergency(
		@PathVariable Long emergencyId,
		@RequestBody EmergencyUpdateDto emergencyUpdateDto
	) {
		emergencyService.updateEmergency(emergencyId, emergencyUpdateDto);
		return ApiResponse.success(true, "Update Emergency succeed");
	}

	@DeleteMapping("/{emergencyId}")
	@Operation(summary = "긴급 도움 요청 삭제")
	public ApiResponse<?> deleteEmergency(
		@PathVariable Long emergencyId
	) {
		emergencyService.deleteEmergency(emergencyId);
		return ApiResponse.success(true, "Delete Emergency succeed");
	}
}
