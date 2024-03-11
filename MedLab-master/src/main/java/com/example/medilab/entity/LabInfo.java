package com.example.medilab.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_lab_info")
public class LabInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String labName;
    @Column(name="address")
    private String labAddress;
    @Column(name="moto")
    private String labMoto;
    @Column(name="website")
    private String labWebsite;
    @Column(name="phone")
    private String labPhone;
    @Column(name="email")
    private String labEmail;
    @Column(name="logo")
    @Lob
    private byte[] labLogo;

}
