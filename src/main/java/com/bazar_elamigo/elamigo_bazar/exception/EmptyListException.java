package com.bazar_elamigo.elamigo_bazar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmptyListException extends RuntimeException{
    private String message;
    private String errorCode;
}
