package com.atm.bank.model;

import lombok.Getter;
import lombok.Setter;

/**
 * This is a model class for our custom exceptions.
 * 
 * @author JulioPachecoSanginez
 *
 */
@Getter
@Setter
public class ErrorResponse {

  /**
   * Our attributes for get the type, code and details of the exception.
   */
  private String type;

  private String code;

  private String details;

}
