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

import com.server.slosaeng.domain.member.application.DoctorService;
import com.server.slosaeng.domain.member.dto.request.DoctorRequestDto;
import com.server.slosaeng.domain.member.dto.response.DoctorResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
@Tag(name = "Doctor", description = "의료진 관리")
public class DoctorController {
	private final DoctorService doctorService;

	@PostMapping("")
	@Operation(summary = "의료진 가입")
	public ApiResponse<?> createDoctor(
		@RequestBody DoctorRequestDto doctorRequestDto
	) {
		String id = doctorService.save(doctorRequestDto);
		return ApiResponse.success(id, "Creating doctor succeed");
	}

	@PostMapping("/approve/{doctorId}")
	@Operation(summary = "의료진 승인")
	public ApiResponse<?> approveDoctor(
		@PathVariable String doctorId
	) {
		doctorService.approve(doctorId);
		return ApiResponse.success(true, "Approving doctor succeed");
	}

	@GetMapping("/{doctorId}")
	@Operation(summary = "의료진 조회")
	public ApiResponse<?> getDoctor(
		@PathVariable String doctorId
	) {
		return ApiResponse.success(doctorService.findById(doctorId), "Reading doctor succeed");
	}

	@GetMapping("/not-approved")
	@Operation(summary = "미승인 의료진 조회")
	public ApiResponse<?> getNotApprovedDoctors() {
		List<DoctorResponseDto> notApprovedDoctors = doctorService.findNotApprovedDoctors();
		return ApiResponse.success(notApprovedDoctors, "Reading not approved doctors succeed");
	}

	@PatchMapping("/{doctorId}")
	@Operation(summary = "의료진 정보 수정")
	public ApiResponse<?> updateDoctor(
		@PathVariable String doctorId,
		@RequestBody DoctorRequestDto doctorRequestDto
	) {
		doctorService.update(doctorId, doctorRequestDto);
		return ApiResponse.success(true, "Updating doctor succeed");
	}

	@DeleteMapping("/{doctorId}")
	@Operation(summary = "의료진 탈퇴")
	public ApiResponse<?> deleteDoctor(
		@PathVariable String doctorId
	) {
		doctorService.delete(doctorId);
		return ApiResponse.success(true, "Deleting doctor succeed");
	}
}
