package com.example.medilab.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_specimen_type")
public class SpecimenType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    
    @OneToMany(mappedBy = "specimenType",fetch = FetchType.EAGER)
    private List<TestGroup> testGroups;
}