package com.example.medilab.entity;



import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="tb_test_report")
public class TestReport {
	    
	    @Id
	    @GeneratedValue(generator = "UUID")
	    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	    @Column(name="id")
	    private String id;
	    
	    @Column(name="note")
	    private String clinicalNote;
	    @Column(name="issue_date")
	    private LocalDate issueDate;
	    @Column(name="modified_counts")
	    private Integer modifiedCount;
	    @Column(name="status_p_d")
	    private String status;
	    
	    @ManyToOne
	    @JoinColumn(name = "appointment_test_id")
	    private AppointmentTest appointmentTest;
	    
	    @OneToMany(mappedBy ="testReport",fetch = FetchType.EAGER)
	    private List<TestReportEntry> testReportEntries;
	    
}
