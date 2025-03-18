package com.bazar_elamigo.elamigo_bazar.exception;


public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String message) {
        super(message);
    }
}
