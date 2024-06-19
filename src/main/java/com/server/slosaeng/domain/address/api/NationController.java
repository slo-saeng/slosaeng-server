package com.server.slosaeng.domain.address.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.address.application.NationService;
import com.server.slosaeng.domain.address.dto.request.NationRequestDto;
import com.server.slosaeng.domain.address.dto.response.NationResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nation")
@Tag(name = "Nation", description = "지역 관리")
public class NationController {
	private final NationService nationService;

	@PostMapping("")
	@Operation(summary = "지역 추가")
	public ApiResponse<?> createNation(
		@RequestBody NationRequestDto nationRequestDto
	) {
		Long id = nationService.save(nationRequestDto);
		return ApiResponse.success(id, "Creating nation succeed");
	}

	@GetMapping("")
	@Operation(summary = "지역 리스트 조회")
	public ApiResponse<?> getNations() {
		List<NationResponseDto> nations = nationService.findAll();
		return ApiResponse.success(nations, "Reading nations succeed");
	}

	@DeleteMapping("/{nationId}")
	@Operation(summary = "지역 삭제")
	public ApiResponse<?> deleteNation(
		@PathVariable Long nationId
	) {
		nationService.delete(nationId);
		return ApiResponse.success(true, "Deleting nation succeed");
	}
}
