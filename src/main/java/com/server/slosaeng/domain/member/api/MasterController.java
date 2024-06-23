package com.server.slosaeng.domain.member.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.member.application.MasterService;
import com.server.slosaeng.domain.member.dto.request.MasterRequestDto;
import com.server.slosaeng.domain.member.dto.request.MasterUpdateDto;
import com.server.slosaeng.domain.member.dto.response.MasterResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
@Tag(name = "Master", description = "의료진 관리")
public class MasterController {
	private final MasterService masterService;

	@PostMapping("")
	@Operation(summary = "마스터 가입")
	public ApiResponse<?> createMaster(
		@RequestBody MasterRequestDto masterRequestDto
	) {
		String id = masterService.save(masterRequestDto);
		return ApiResponse.success(id, "Creating master succeed");
	}

	@GetMapping("/{masterId}")
	@Operation(summary = "마스터 조회")
	public ApiResponse<?> getMaster(
		@PathVariable String masterId
	) {
		return ApiResponse.success(masterService.findById(masterId), "Reading master succeed");
	}

	@GetMapping("")
	@Operation(summary = "마스터 목록 조회")
	public ApiResponse<?> getMasters() {
		List<MasterResponseDto> masters = masterService.findAll();
		return ApiResponse.success(masters, "Reading masters succeed");
	}

	@PatchMapping("/{masterId}")
	@Operation(summary = "마스터 정보 수정")
	public ApiResponse<?> updateMaster(
		@PathVariable String masterId,
		@RequestBody MasterUpdateDto masterUpdateDto
	) {
		masterService.update(masterId, masterUpdateDto);
		return ApiResponse.success(true, "Updating master succeed");
	}

	@DeleteMapping("/{masterId}")
	@Operation(summary = "마스터 탈퇴")
	public ApiResponse<?> deleteMaster(
		@PathVariable String masterId
	) {
		masterService.delete(masterId);
		return ApiResponse.success(true, "Deleting master succeed");
	}

}
