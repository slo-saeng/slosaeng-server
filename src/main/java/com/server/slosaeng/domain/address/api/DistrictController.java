package com.server.slosaeng.domain.address.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.address.application.DistrictService;
import com.server.slosaeng.domain.address.dto.request.DistrictRequestDto;
import com.server.slosaeng.domain.address.dto.response.DistrictResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/district")
@Tag(name = "District", description = "군/구 관리")
public class DistrictController {
	private final DistrictService districtService;

	@PostMapping("")
	@Operation(summary = "군/구 추가")
	public ApiResponse<?> createDistrict(
		@RequestBody DistrictRequestDto districtRequestDto
	) {
		Long id = districtService.save(districtRequestDto);
		return ApiResponse.success(id, "Creating District succeed");
	}

	@GetMapping("")
	@Operation(summary = "군/구 리스트 조회")
	public ApiResponse<?> getDistricts() {
		List<DistrictResponseDto> districts = districtService.findAll();
		return ApiResponse.success(districts, "Reading Districts succeed");
	}

	@DeleteMapping("/{districtId}")
	@Operation(summary = "지역 삭제")
	public ApiResponse<?> deleteDistrict(
		@PathVariable Long districtId
	) {
		districtService.delete(districtId);
		return ApiResponse.success(true, "Deleting District succeed");
	}
}
