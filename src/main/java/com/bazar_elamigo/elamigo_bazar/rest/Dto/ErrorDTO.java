package com.bazar_elamigo.elamigo_bazar.rest.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;
    private String detail;
    private String path;




}
