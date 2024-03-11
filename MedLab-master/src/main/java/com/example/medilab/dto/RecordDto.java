package com.example.medilab.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class RecordDto {

	private String patientName;
	private String patientCode;
	private String gender;
	private String referredBy;
	private String age;
	private String testName;
	private String variablesName;
	private String units;
	private String referenceValue;
	private String results;
	private String clinicalNotes;
	private LocalDateTime dateAndTime;
	private LocalDate issueDate;


	private String labName;
	private byte[] labLogo;
	private String labMoto;
	private String labAddress;
	private String labPhone;
	private String labEmail;
	private String labWebsite;
}
