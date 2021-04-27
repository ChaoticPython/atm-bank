package com.atm.bank.exception;

/**
 * This class provide the message for the Bad Request Exception of the Microservice.
 * 
 * @author JulioPachecoSanginez
 *
 */
public class BadRequestException extends RuntimeException {

  /**
   * The default generated serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The method define the message error (more specific).
   * 
   * @param message.
   */
  // Proporcionamos un mensaje más específico del problema
  public BadRequestException(String message) {
    super(message);
  }

}
