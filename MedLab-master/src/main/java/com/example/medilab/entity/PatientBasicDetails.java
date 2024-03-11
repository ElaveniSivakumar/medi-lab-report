package com.example.medilab.entity;
import com.example.medilab.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;
import javax.validation.constraints.Email;
@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_patient_basic_details")
public class PatientBasicDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "date_of_birth")
    private String dob;
    @Column(name = "blood_group")
    private String bloodGroup;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Email
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "patient_code")
    private String patientCode;
    @Column(name = "age")
    private String age;
    @OneToMany(mappedBy = "patientbasicDetails",fetch = FetchType.EAGER)
    private List<Appointment> appointments;
}