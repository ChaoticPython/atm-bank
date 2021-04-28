package com.atm.bank.exception;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.atm.bank.model.ErrorResponse;

/**
 * This is the unit test for the GlobalExceptionHandler class.
 * 
 * @author JulioPachecoSanginez
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

  /**
   * An instance of WebRequest.
   */
  @Mock
  private WebRequest wr;
  
  /**
   * An instance of GlobalExceptionHandler.
   */
  @InjectMocks
  private GlobalExceptionHandler geh;
  
  /**
   * Given a WebRequest and a BadRequestException,
   * When a BadRequestException happens,
   * Then return the ErrorResponse and the HTTP status.
   */
  @Test
  public void testHandleBadRequestException() {
    BadRequestException bre = new BadRequestException("Bad Request");
    ResponseEntity<ErrorResponse> response = geh.handleBadRequestException(bre, wr);
    
    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
  
}
