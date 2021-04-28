package com.atm.bank.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.atm.bank.dao.OptionDao;
import com.atm.bank.exception.BadRequestException;
import com.atm.bank.helper.HelperClass;
import com.atm.bank.model.Card;
import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;

@RunWith(MockitoJUnitRunner.class)
public class OptionServiceImplTest {

  @Mock
  private OptionDao optionDao;
  
  @Mock
  private HelperClass helper;

  @InjectMocks
  private OptionServiceImpl optionService;

  @Test(expected = BadRequestException.class)
  public void testRetrieveMessageResponseFail() {
    OptionRequest request = generateRequest();
    request.setPin(3280);

    optionService.retrieveMessageResponse(request);
  }

  @Test
  public void testRetrieveMessageResponse() {
    OptionRequest request = generateRequest();
    request.setOption("Check");
    String previewResponse = "";

    Mockito.when(optionDao.checkBalance(request.getCard())).thenReturn(previewResponse);
    
    MessageResponse response = optionService.retrieveMessageResponse(request);
    
    assertNotNull(response);
  }
  
  @Test
  public void testRetrieveMessageResponseHelper() {
    OptionRequest request = generateRequest();
    request.setOption("Other");
    String previewResponse = "";

    Mockito.when(helper.executeOption(request)).thenReturn(previewResponse);
    
    MessageResponse response = optionService.retrieveMessageResponse(request);
    
    assertNotNull(response);
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
