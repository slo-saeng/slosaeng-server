package com.server.slosaeng.domain.institution.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenApiService {

	public InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
		urlConnection.setConnectTimeout(3000);
		urlConnection.setReadTimeout(3000);
		urlConnection.setRequestMethod("GET");
		urlConnection.setDoInput(true);

		if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
		}

		return urlConnection.getInputStream();
	}

	public String readStreamToString(InputStream stream) throws IOException {
		StringBuilder result = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

		String readLine;
		while ((readLine = br.readLine()) != null) {
			result.append(readLine + "\n\r");
		}

		br.close();

		return result.toString();
	}
}
