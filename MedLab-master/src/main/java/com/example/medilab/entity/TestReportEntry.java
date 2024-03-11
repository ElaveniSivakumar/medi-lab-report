package com.example.medilab.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="tb_test_report_entry")
public class TestReportEntry {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;
    @Column(name="result")
    private String result;
    
    @ManyToOne
    @JoinColumn(name = "test_group_details_id")
    private TestGroupDetails testGroupDetails;
    
    @ManyToOne
    @JoinColumn(name = "test_report_id")
    private TestReport testReport;
}



