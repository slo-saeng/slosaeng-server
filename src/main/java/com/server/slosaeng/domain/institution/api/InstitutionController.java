package com.server.slosaeng.domain.institution.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.institution.application.InstitutionService;
import com.server.slosaeng.domain.institution.dto.response.InstitutionResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/institution")
@Tag(name = "Institution", description = "병의원 관리")
public class InstitutionController {
	private final InstitutionService institutionService;

	@PostMapping("/init")
	@Operation(summary = "병의원 데이터 init")
	public ApiResponse<?> initInstitutionData() {
		try {
			institutionService.initInstitutionData();
			return ApiResponse.success(true, "Init institution data succeed");
		} catch (Exception e) {
			return ApiResponse.error("Init institution data failed");
		}
	}

	@GetMapping("/{keyword}")
	@Operation(summary = "병의원 검색")
	public ApiResponse<?> searchInstitution(
		@PathVariable String keyword
	) {
		List<InstitutionResponseDto> result = institutionService.searchInstitution(keyword);
		return ApiResponse.success(result, "Search institution succeed");
	}
}
