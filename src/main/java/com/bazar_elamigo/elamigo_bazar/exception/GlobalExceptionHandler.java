package com.bazar_elamigo.elamigo_bazar.exception;

import com.bazar_elamigo.elamigo_bazar.rest.Dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), ex.getErrorCode(), LocalDateTime.now(), "Recurso no encontrado", request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>("Error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<?> handlerEmptyListException(EmptyListException ex, HttpServletRequest request){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), ex.getErrorCode(), LocalDateTime.now(), "Recurso no encontrado", request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<?> handleInssuficientStockException( InsufficientStockException ex, HttpServletRequest request){
        ErrorDTO error = new ErrorDTO(ex.getMessage(), "STOCK_ERROR", LocalDateTime.now(), "El producto no tiene stock suficiente para completar la venta", request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
