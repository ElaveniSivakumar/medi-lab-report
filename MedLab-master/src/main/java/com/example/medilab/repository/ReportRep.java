package com.example.medilab.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.medilab.bean.MyRecord;
import com.example.medilab.entity.PatientBasicDetails;



@Repository
public interface ReportRep extends JpaRepository<PatientBasicDetails,UUID>{

	/*@Query(value = "SELECT \n"
            + "    CONCAT(pbd.first_name, ' ', pbd.last_name) AS patient_name,\n"
            + "    pbd.patient_code,\n"
            + "    pbd.age,\n"
            + "    pbd.gender,\n"
            + "    app.date_time AS appointment_date,\n"
            + "    app.referred_by,\n"
            + "    tg.name AS test_name,\n"
            + "    tgd.variables AS variable,\n"
            + "    tgd.units,\n"
            + "    tgd.reference_value AS reference,\n"
            + "    tre.note,\n"
            + "    tre.result,\n"
            + "    tre.issue_date\n"
            + "FROM \n"
            + "    tb_patient_basic_details pbd\n"
            + "JOIN \n"
            + "    tb_newappointment app ON app.patient_id = pbd.id\n"
            + "JOIN \n"
            + "    tb_appointment_test apt ON apt.appointment_id = app.id\n"
            + "JOIN \n"
            + "    tb_test_report_entry tre ON tre.appointment_test_id = apt.id\n"
            + "JOIN \n"
            + "    tb_testgroup_details tgd ON tgd.test_group_id = tre.appointment_test_id\n"
            + "JOIN \n"
            + "    tb_test_group tg ON tg.id = tgd.test_group_id\n"
            + "WHERE\n"
            + "    pbd.id = '1'\n"
            + "    AND tg.id = '1';", nativeQuery = true)
	
    List<MyRecord> getReportDataForPatientAndTest();*/
	
	/*@Query(value = "SELECT " +
			"CONCAT(pb.first_name, ' ', pb.last_name) AS patientName," +
	        "pb.patient_code AS patientCode, " +
	        "pb.age AS age, " +
	        "pb.gender AS gender, " +
	        "a.date_time AS dateAndTime, " +
	        "a.referred_by AS referredBy, " +
	        "tg.name AS testName, " +
	        "tgd.variables AS variablesName, " +
	        "tr.issue_date AS issueDate, " +
	        "tgd.units AS units, " +
	        "tgd.reference_value AS referenceValue, " +
	        "tr.note AS clinicalNotes, " +
	        "tre.result AS results " +
	        "FROM tb_patient_basic_details pb " +
	        "JOIN tb_newappointment a ON pb.id = a.patient_id " +
	        "JOIN tb_appointment_test at ON a.id = at.appointment_id " +
	        "JOIN tb_test_group tg ON at.test_group_id = tg.id " +
	        "JOIN tb_testgroup_details tgd ON tg.id = tgd.test_group_id " +
	        "JOIN tb_test_report tr ON at.id = tr.appointment_test_id " +
	        "JOIN tb_test_report_entry tre ON tr.id = tre.test_report_id " +
	        "WHERE pb.id = :patientId " +
	        "AND tg.id = :testId", nativeQuery = true)
	List<MyRecord> getPatientTestReportDetails(@Param("patientId") String patientId, @Param("testId") String testId);*/

	@Query(value = "SELECT pb.first_name AS patientName, pb.patient_code AS patientCode, " +
			"pb.age AS age, pb.gender AS gender, na.date_time AS dateAndTime, na.referred_by AS referredBy, " +
			"tg.name AS testName, tgd.variables AS variablesName, tre.result AS results, tgd.units AS units, " +
			"tgd.reference_value AS referenceValue, tr.note AS clinicalNotes, tr.issue_date AS issueDate, li.name AS labName, li.phone AS labPhone, li.moto AS labMoto, li.website AS labWebsite, li.email AS labEmail, li.address AS labAddress, li.logo AS labLogo " +
			"FROM tb_patient_basic_details AS pb " +
			"INNER JOIN tb_newappointment na ON pb.id = na.patient_id " +
			"LEFT JOIN tb_appointment_test apt ON na.id = apt.appointment_id " +
			"LEFT JOIN tb_test_group tg ON apt.test_group_id = tg.id " +
			"LEFT JOIN tb_testgroup_details tgd ON tg.id = tgd.test_group_id " +
			"LEFT JOIN tb_test_report_entry tre ON tgd.id = tre.test_group_details_id " +
			"LEFT JOIN tb_test_report tr ON tre.test_report_id = tr.id " +
			"CROSS JOIN tb_lab_info li " +
			"WHERE pb.id = :patientId AND tr.id = :testId", nativeQuery = true)
	List<MyRecord> getPatientTestReportDetail(@Param("patientId") String patientId, @Param("testId") String testId);
}

