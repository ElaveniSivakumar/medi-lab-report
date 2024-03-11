package com.example.medilab.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface MyRecord {
	String getPatientName();
	String getPatientCode();
	 String getGender();
	 String getReferredBy();
	 String getAge();
	String getTestName();
	String getVariablesName();
	String getUnits();
	String getReferenceValue();
	String getResults();
	String getClinicalNotes();
	 LocalDateTime getDateAndTime();
	 LocalDate getIssueDate();

	 String getLabName();
	 byte[] getLabLogo();
	 String getLabMoto();
	 String getLabAddress();
	 String getLabPhone();
	 String getLabEmail();
	 String getLabWebsite();


}
