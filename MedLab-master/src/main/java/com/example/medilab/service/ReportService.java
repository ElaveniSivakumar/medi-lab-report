package com.example.medilab.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.example.medilab.dto.LabInfoDto;
import com.example.medilab.entity.LabInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.medilab.bean.MyRecord;
import com.example.medilab.dto.PatientDetailsDto;
import com.example.medilab.dto.RecordDto;
import com.example.medilab.dto.ResultDto;
import com.example.medilab.repository.ReportRep;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class ReportService {

		@Autowired
		private ReportRep recordRep;
		
	    private final TemplateEngine templateEngine;

	    private static final Logger LOG = LoggerFactory.getLogger(ReportService.class);

	    public ReportService(TemplateEngine templateEngine) {
	        this.templateEngine = templateEngine;
	    }

	    public byte[] generatePdf(String templatePath, Map<String, Object> contextVariables)
	            throws DocumentException, IOException {
	        Context context = new Context();
	        
	     // Fetch data from the database
	        //List<MyRecord> reportData = recordRep.getPatientTestReportDetails("1","1");
	        // Convert MyRecord to RecordDto
	        
	        // Add the database data to the context
	        //contextVariables.put("reportData",recordDtos);
	        //context.setVariables(contextVariables);
	        
	        // Fetch data from the database and convert it to a map
	        Map<String, List<Object>> reportData = convertDetails();
	        
	        // Add the report data to the context
	        contextVariables.putAll(reportData);
	        contextVariables.put("repotData",reportData);
	        context.setVariables(contextVariables);
	        
	        
	        String html = templateEngine.process(templatePath, context);

	        Path path = Files.createTempFile(UUID.randomUUID().toString(), ".pdf");
	        try (OutputStream outputStream = Files.newOutputStream(path)) {
	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(html);
	            renderer.layout();
	            renderer.createPDF(outputStream);
	        }
	        LOG.debug("Wrote PDF file for template {}: {}", templatePath, path);
	        byte[] bytes = Files.readAllBytes(path);
	        Files.delete(path);
	        return bytes;
	    }
	    
	    /*private List<RecordDto> getMyRecordDto(MyRecord myRecordEntity) {
	    	List<RecordDto> myRecordList = new ArrayList<>();
	    	RecordDto recordDto = new RecordDto();
	    	recordDto.setPatientName(myRecordEntity.getPatientName());
	    	recordDto.setPatientCode(myRecordEntity.getPatientCode());
	    	recordDto.setAge(myRecordEntity.getAge());
	    	recordDto.setGender(myRecordEntity.getGender());
	    	recordDto.setDateAndTime(myRecordEntity.getDateAndTime());
	    	recordDto.setClinicalNotes(myRecordEntity.getClinicalNotes());
	    	recordDto.setIssueDate(myRecordEntity.getissueDate());
	    	recordDto.setReferredBy(myRecordEntity.getReferredBy());
	    	recordDto.setReferenceValue(myRecordEntity.getReferenceValue());
	    	recordDto.setTestName(myRecordEntity.getTestName());
	    	recordDto.setResults(myRecordEntity.getResults());
	    	recordDto.setUnits(myRecordEntity.getUnits());
	    	recordDto.setVariablesName(myRecordEntity.getVariablesName());
			return myRecordList;
	    	
	    }*/
	    
	    /*private List<RecordDto> getMyRecordsDto(List<MyRecord> myRecordEntities) {
	        List<RecordDto> recordDtoList = new ArrayList<>();
	        for (MyRecord myRecordEntity : myRecordEntities) {
	            RecordDto recordDto = new RecordDto();
	            recordDto.setPatientName(myRecordEntity.getPatientName());
	            recordDto.setPatientCode(myRecordEntity.getPatientCode());
	            recordDto.setAge(myRecordEntity.getAge());
	            recordDto.setGender(myRecordEntity.getGender());
	            recordDto.setDateAndTime(myRecordEntity.getDateAndTime());
	            recordDto.setClinicalNotes(myRecordEntity.getClinicalNotes());
	            recordDto.setIssueDate(myRecordEntity.getissueDate());
	            recordDto.setReferredBy(myRecordEntity.getReferredBy());
	            recordDto.setReferenceValue(myRecordEntity.getReferenceValue());
	            recordDto.setTestName(myRecordEntity.getTestName());
	            recordDto.setResults(myRecordEntity.getResults());
	            recordDto.setUnits(myRecordEntity.getUnits());
	            recordDto.setVariablesName(myRecordEntity.getVariablesName());
	            recordDtoList.add(recordDto);
	        }
	        return recordDtoList;
	    }*/
	    private List<RecordDto> getMyRecordsDto(List<MyRecord> myRecordEntities) {
	        List<RecordDto> recordDtoList = new ArrayList<>();
	        for (MyRecord re : myRecordEntities) {
	            RecordDto record= RecordDto.builder().patientName(re.getPatientName())
	                    .patientCode(re.getPatientCode())
	                    .age(re.getAge())
	                    .gender(re.getGender())
	                    .dateAndTime(re.getDateAndTime())
	                    .clinicalNotes(re.getClinicalNotes())
	                    .issueDate(re.getIssueDate())
	                    .referredBy(re.getReferredBy())
	                    .referenceValue(re.getReferenceValue())
	                    .testName(re.getTestName())
	                    .units(re.getUnits())
	                    .results(re.getResults())
	                    .variablesName(re.getVariablesName())
						.labName(re.getLabName())
						.labAddress(re.getLabAddress())
						.labLogo(re.getLabLogo())
						.labMoto(re.getLabMoto())
						.labEmail(re.getLabEmail())
						.labPhone(re.getLabPhone())
						.labWebsite(re.getLabWebsite()).build();
	            recordDtoList.add(record);
	        }
	        return recordDtoList;
	    }
	    public Map<String, List<Object>> convertDetails() {
	        List<MyRecord> reportData = recordRep.getPatientTestReportDetail("1", "1");
	        List<RecordDto> recordDto = getMyRecordsDto(reportData);
	        Map<String, List<Object>> recordDetails = new HashMap<>();

	        List<Object> patientDetails = recordDto.stream().map(p ->
	                PatientDetailsDto.builder().patientName(p.getPatientName())
	                        .age(p.getAge())
	                        .patientCode(p.getPatientCode())
	                        .referredBy(p.getReferredBy())
	                        .gender(p.getGender())
	                        .testName(p.getTestName())
	                        .issueDate(p.getIssueDate())
	                        .dateAndTime(p.getDateAndTime())
	                        .clinicalNotes(p.getClinicalNotes()).build()).limit(1).collect(Collectors.toList());

	        List<Object> resultDto = recordDto.stream().map(p ->
	                ResultDto.builder().results(p.getResults())
	                        .units(p.getUnits())
	                        .referenceValue(p.getReferenceValue())
	                        .variablesName(p.getVariablesName()).build()).collect(Collectors.toList());

			List<Object> labInfoDto = recordDto.stream().map(p ->
					LabInfoDto.builder().labLogo(p.getLabLogo())
							.labName(p.getLabName())
							.labMoto(p.getLabMoto())
							.labAddress(p.getLabAddress())
							.labPhone(p.getLabPhone())
							.labEmail(p.getLabEmail())
							.labWebsite(p.getLabWebsite()).build()).limit(1).collect(Collectors.toList());

			recordDetails.put("labInfo",labInfoDto);
	        recordDetails.put("patientDetails", patientDetails);
	        
	        recordDetails.put("result", resultDto);
	        recordDetails.forEach((key, value) -> System.out.println(key + " " + value));
	        return recordDetails;
	    }

	    }
	

		



