package com.atm.bank.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.atm.bank.model.Card;
import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;
import com.atm.bank.service.IVerifyRequestService;

@RunWith(MockitoJUnitRunner.class)
public class AtmControllerTest {

  @Mock
  private IVerifyRequestService requestService;

  @InjectMocks
  private AtmController atmController;

  @Test
  public void messageResponse() {
    OptionRequest request = generateRequest();
    MessageResponse previewResponse = generatePreviewResponse();

    Mockito.when(requestService.retrieveMessageResponse(request)).thenReturn(previewResponse);
    
    ResponseEntity<MessageResponse> response = atmController.messageResponse(request);

    assertNotNull(response);
  }

  private MessageResponse generatePreviewResponse() {
    MessageResponse response = new MessageResponse();
    response.setMessage("Works!");
    return response;
  }

  private OptionRequest generateRequest() {
    OptionRequest request = new OptionRequest();
    request.setPin(3289);
    request.setOption("option");
    request.setReceiver("receiver");
    request.setAmount(100.0);
    request.setCard(generateCard());
    return request;
  }

  private Card generateCard() {
    Card card = new Card();
    card.setAmount(129.0);
    card.setPin(3289);
    return card;
  }

}
