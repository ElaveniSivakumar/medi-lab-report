package com.example.medilab.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_newappointment")
public class Appointment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private String id;
    @Column(name="date_time")
    private LocalDateTime dateAndTime;
    @Column(name="referred_by")
    private String referredBy;
    @Column(name="token")
    private String token;
    @Column(name="appointment_status")
    private String appointmentStatus;
    @Column(name="n0_of_test")
    private Integer noOfTest;
    @Column(name="n0_of_sample_collected")
    private Integer noOfSampleCollection;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientBasicDetails patientbasicDetails;
    @OneToMany(mappedBy = "appointment",fetch = FetchType.EAGER)
    private List<AppointmentTest> appointmentTests;
}