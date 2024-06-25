package com.server.slosaeng.domain.intensive_care.api;

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

import com.server.slosaeng.domain.intensive_care.application.IntensiveCareService;
import com.server.slosaeng.domain.intensive_care.dto.request.IntensiveCareRequestDto;
import com.server.slosaeng.domain.intensive_care.dto.request.IntensiveCareUpdateDto;
import com.server.slosaeng.domain.intensive_care.dto.response.IntensiveCareResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/intensive-care")
@Tag(name = "IntensiveCare", description = "집중 관리")
public class IntensiveCareController {
	private final IntensiveCareService intensiveCareService;

	@PostMapping("")
	@Operation(summary = "집중 관리 등록")
	public ApiResponse<?> createIntensiveCare(
		@RequestBody IntensiveCareRequestDto intensiveCareRequestDto
	) {
		Long id = intensiveCareService.register(intensiveCareRequestDto);
		return ApiResponse.success(id, "Register IntensiveCare succeed");
	}

	@GetMapping("/{intensiveCareId}")
	@Operation(summary = "집중 관리 조회")
	public ApiResponse<?> getIntensiveCare(
		@PathVariable Long intensiveCareId
	) {
		IntensiveCareResponseDto intensiveCare = intensiveCareService.getIntensiveCareById(intensiveCareId);
		return ApiResponse.success(intensiveCare, "Read IntensiveCare succeed");
	}

	@GetMapping("")
	@Operation(summary = "집중 관리 목록 조회")
	public ApiResponse<?> getIntensiveCareList() {
		List<IntensiveCareResponseDto> intensiveCareList = intensiveCareService.getIntensiveCareList();
		return ApiResponse.success(intensiveCareList, "Read IntensiveCareList succeed");
	}

	@GetMapping("/page")
	@Operation(summary = "집중 관리 페이징 조회")
	public ApiResponse<?> getIntensiveCareList(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	) {
		Pageable pageable = PageRequest.of(page, size);
		Page<IntensiveCareResponseDto> intensiveCareList = intensiveCareService.getIntensiveCareByPage(pageable);
		return ApiResponse.success(intensiveCareList, "Read IntensiveCareList by page succeed");
	}

	@PatchMapping("/{intensiveCareId}")
	@Operation(summary = "집중 관리 정보 수정")
	public ApiResponse<?> updateIntensiveCare(
		@PathVariable Long intensiveCareId,
		@RequestBody IntensiveCareUpdateDto intensiveCareUpdateDto
	) {
		intensiveCareService.update(intensiveCareId, intensiveCareUpdateDto);
		return ApiResponse.success(true, "Update IntensiveCare succeed");
	}

	@DeleteMapping("/{intensiveCareId}")
	@Operation(summary = "집중 관리 삭제")
	public ApiResponse<?> deleteIntensiveCare(
		@PathVariable Long intensiveCareId
	) {
		intensiveCareService.delete(intensiveCareId);
		return ApiResponse.success(true, "Delete IntensiveCare succeed");
	}
}
