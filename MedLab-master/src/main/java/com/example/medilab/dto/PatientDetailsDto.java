package com.example.medilab.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDetailsDto {
	private String patientName;
    private String patientCode;
    private String gender;
    private String referredBy;
    private String age;
    private String testName;
    private String clinicalNotes;
    private LocalDateTime dateAndTime;
    private LocalDate issueDate;
}
