package com.server.slosaeng.domain.institution.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.institution.dao.InstitutionRepository;
import com.server.slosaeng.domain.institution.domain.Institution;
import com.server.slosaeng.domain.institution.dto.response.InstitutionResponseDto;
import com.server.slosaeng.global.handler.ExcelSheetHandler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitutionService {
	private final InstitutionRepository institutionRepository;

	@Transactional
	public void initInstitutionData() throws Exception {
		String filePath = "/home/ubuntu/slosaeng/institution_detail.xlsx";
		// String filePath = "src/main/resources/static/institution_detail.xlsx";
		File file = new File(filePath);
		ExcelSheetHandler excelSheetHandler = ExcelSheetHandler.readExcel(file);
		List<List<String>> excelDatas = excelSheetHandler.getRows();

		int batchSize = 50;
		List<Institution> batchList = new ArrayList<>();

		for (List<String> dataRow : excelDatas) {
			Institution institution = Institution.builder()
				.code(dataRow.get(0))
				.name(dataRow.get(1))
				.type(dataRow.get(2))
				.address(dataRow.get(3))
				.tel(dataRow.get(4))
				.homepage(dataRow.get(5))
				.build();
			batchList.add(institution);

			if (batchList.size() == batchSize) {
				institutionRepository.saveAll(batchList);
				batchList.clear();
			}
		}

		if (!batchList.isEmpty()) {
			institutionRepository.saveAll(batchList);
		}
	}

	public List<InstitutionResponseDto> searchInstitution(String keyword) {
		return institutionRepository.findByNameContaining(keyword).stream()
			.map(Institution -> InstitutionResponseDto.builder()
				.id(Institution.getId())
				.code(Institution.getCode())
				.name(Institution.getName())
				.type(Institution.getType())
				.address(Institution.getAddress())
				.tel(Institution.getTel())
				.homepage(Institution.getHomepage())
				.build()
			).collect(Collectors.toList());
	}

	public List<InstitutionResponseDto> getAllInstitutions() {
		return institutionRepository.findAll().stream()
			.map(Institution -> InstitutionResponseDto.builder()
				.id(Institution.getId())
				.code(Institution.getCode())
				.name(Institution.getName())
				.type(Institution.getType())
				.address(Institution.getAddress())
				.tel(Institution.getTel())
				.homepage(Institution.getHomepage())
				.build()
			).collect(Collectors.toList());
	}

	public Page<InstitutionResponseDto> getAllInstitutionByPage(Pageable pageable) {
		return institutionRepository.findAll(pageable)
			.map(Institution -> InstitutionResponseDto.builder()
				.id(Institution.getId())
				.code(Institution.getCode())
				.name(Institution.getName())
				.type(Institution.getType())
				.address(Institution.getAddress())
				.tel(Institution.getTel())
				.homepage(Institution.getHomepage())
				.build()
			);
	}

}
