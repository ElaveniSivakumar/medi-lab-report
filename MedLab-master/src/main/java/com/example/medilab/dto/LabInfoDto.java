package com.example.medilab.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LabInfoDto {
    private String labName;
    private byte[] labLogo;
    private String labMoto;
    private String labAddress;
    private String labPhone;
    private String labEmail;
    private String labWebsite;
}
