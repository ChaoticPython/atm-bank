package com.atm.bank.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.atm.bank.model.ErrorResponse;

/**
 * This is the class handler of exceptions, in her we put the necesary exceptions.
 * 
 * @author JulioPachecoSanginez
 *
 */
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * This method is the handler for a BadRequestException.
   * 
   * @param bre as BadRequestException.
   * @param wr as WebRequest.
   * @return a ResponseEntity with the status and exception.
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException bre,
      WebRequest wr) {
    ErrorResponse er = new ErrorResponse();
    er.setType("error");
    er.setCode("400 - " + HttpStatus.BAD_REQUEST.getReasonPhrase().toUpperCase());
    er.setDetails(bre.getMessage());
    return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
  }

}
