package com.example.medilab.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_test_group")
public class TestGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "specimen_id")
    private SpecimenType specimenType;
    
    @OneToMany(mappedBy = "testGroup")
    private List<TestGroupDetails> testGroupDetails;
    
    @OneToMany(mappedBy = "testGroup")
    private List<AppointmentTest> appointmentTests;
}