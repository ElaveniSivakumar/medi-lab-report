package com.example.medilab.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="tb_appointment_test")
public class AppointmentTest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private String id;
    @Column(name="tube_no")
    private String tubeNo;
    @Column(name="collected_by")
    private String collectedBy;
    @Column(name="test_status")
    private String testStatus;
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    @ManyToOne
    @JoinColumn(name = "test_group_id")
    private TestGroup testGroup;
    
    @OneToMany(mappedBy = "appointmentTest",fetch = FetchType.EAGER)
    private List<TestReport> testReports;
}