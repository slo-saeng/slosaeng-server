package com.server.slosaeng.domain.address.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.slosaeng.domain.address.application.CityService;
import com.server.slosaeng.domain.address.dto.request.CityRequestDto;
import com.server.slosaeng.domain.address.dto.response.CityResponseDto;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
@Tag(name = "City", description = "도시 관리")
public class CityController {
	private final CityService cityService;

	@PostMapping("")
	@Operation(summary = "도시 추가")
	public ApiResponse<?> createCity(
		@RequestBody CityRequestDto cityRequestDto
	) {
		Long id = cityService.save(cityRequestDto);
		return ApiResponse.success(id, "Creating city succeed");
	}

	@GetMapping("")
	@Operation(summary = "도시 리스트 조회")
	public ApiResponse<?> getCities() {
		List<CityResponseDto> cities = cityService.findAll();
		return ApiResponse.success(cities, "Reading cities succeed");
	}

	@GetMapping("/{nationId}")
	@Operation(summary = "지역 기반 도시 리스트 조회")
	public ApiResponse<?> getCitiesByNation(
		@PathVariable Long nationId
	) {
		List<CityResponseDto> cities = cityService.findAllByNation(nationId);
		return ApiResponse.success(cities, "Reading cities succeed");
	}

	@DeleteMapping("/{cityId}")
	@Operation(summary = "지역 삭제")
	public ApiResponse<?> deleteCity(
		@PathVariable Long cityId
	) {
		cityService.delete(cityId);
		return ApiResponse.success(true, "Deleting city succeed");
	}
}
