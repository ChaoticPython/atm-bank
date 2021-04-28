package com.atm.bank.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.atm.bank.exception.BadRequestException;
import com.atm.bank.model.Card;
import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;
import com.atm.bank.service.IOptionService;

@RunWith(MockitoJUnitRunner.class)
public class VerifyRequestServiceImplTest {

  @Mock
  private IOptionService optionService;

  @InjectMocks
  private VerifyRequestServiceImpl verifyRequest;

  @Test(expected = BadRequestException.class)
  public void testRetrieveMessageResponsePinFail() {
    OptionRequest request = generateRequest();
    request.setPin(null);

    verifyRequest.retrieveMessageResponse(request);
  }

  @Test(expected = BadRequestException.class)
  public void testRetrieveMessageResponseOptionFail() {
    OptionRequest request = generateRequest();
    request.setOption(null);

    verifyRequest.retrieveMessageResponse(request);
  }

  @Test
  public void testRetrieveMessageResponse() {
    OptionRequest request = generateRequest();
    MessageResponse previewResponse = generatePreviewResponse();

    Mockito.when(optionService.retrieveMessageResponse(request)).thenReturn(previewResponse);

    MessageResponse response = verifyRequest.retrieveMessageResponse(request);

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
