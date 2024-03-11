package com.example.medilab.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tb_testgroup_details")
public class TestGroupDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;
    @Column(name="variables")
    private String variable;
    @Column(name="units")
    private String unit;
    @Column(name="reference_value")
    private String referenceValue;
    
    
    @ManyToOne
    @JoinColumn(name = "test_group_id")
    private TestGroup testGroup;
    
    @OneToMany(mappedBy ="testGroupDetails",fetch = FetchType.EAGER)
    private List<TestReportEntry> testReportEntries;
    
    
}