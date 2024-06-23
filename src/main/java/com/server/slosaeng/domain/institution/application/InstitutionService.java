package com.server.slosaeng.domain.institution.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.institution.dao.InstitutionRepository;
import com.server.slosaeng.domain.institution.domain.Institution;
import com.server.slosaeng.global.handler.ExcelSheetHandler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitutionService {
	private final InstitutionRepository institutionRepository;

	@Transactional
	public void initInstitutionData() throws Exception {
		String[] filePaths = {
			"src/main/resources/static/institution1.xlsx",
			"src/main/resources/static/institution2.xlsx",
			"src/main/resources/static/institution3.xlsx"
		};

		for (String filePath : filePaths) {
			processExcelFile(filePath);
		}
	}

	private void processExcelFile(String filePath) throws Exception {
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

}
