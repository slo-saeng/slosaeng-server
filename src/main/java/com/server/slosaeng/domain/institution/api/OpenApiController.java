package com.server.slosaeng.domain.institution.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.slosaeng.domain.institution.application.OpenApiService;
import com.server.slosaeng.global.common.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/openapi")
@Tag(name = "OpenApi", description = "OpenAPI 관리")
public class OpenApiController {

	@Value("${openApi.base_url}")
	private String baseUrl;

	@Value("${openApi.service_key}")
	private String serviceKey;

	@Value("${openApi.data_format}")
	private String _type;

	private final ObjectMapper objectMapper;
	private final OpenApiService openApiService;

	@GetMapping("")
	@Operation(summary = "병의원 시설 정보 Open API")
	public ApiResponse<?> getOpenApiInfo(
		@RequestParam String ykiho
	) {
		HttpURLConnection urlConnection = null;
		InputStream stream = null;
		String result = null;
		Object jsonResult = null;

		String url = baseUrl + "?ServiceKey=" + serviceKey + "&ykiho=" + ykiho + "&_type=" + _type;

		try {
			URL requestUrl = new URL(url);

			urlConnection = (HttpURLConnection)requestUrl.openConnection();
			stream = openApiService.getNetworkConnection(urlConnection);
			result = openApiService.readStreamToString(stream);

			jsonResult = objectMapper.readValue(result, Object.class);

			if (stream != null)
				stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return ApiResponse.success(jsonResult, "Open API data succeed");
	}

}
