package com.atm.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.bank.model.Card;
import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;
import com.atm.bank.service.IOptionService;
import com.atm.bank.service.IVerifyRequestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VerifyRequestServiceImpl implements IVerifyRequestService {

  @Autowired
  private IOptionService optionService;

  @Override
  public MessageResponse retrieveMessageResponse(OptionRequest request) {
    // TODO Auto-generated method stub
    MessageResponse response = new MessageResponse();
    // creation of a dummy card
    request.setCard(createCard());
    // If our PIN, the card or the option are null, we can't proceed with the request
    log.info("Verificando petición...");
    if (request.getPin() == null || request.getOption() == null || request.getCard() == null) {
      // Throw an exception - you need a pin and option to do any operation
      log.info("Ocurrio un error");
    } else {
      log.info("Procesando petición...");
      // Invoke the other service
      response = optionService.retrieveMessageResponse(request);
    }
    return response;
  }

  private static Card createCard() {
    Card card = new Card();
    card.setPin(1539);
    card.setAmount((double) Math.round(Math.random() * 100000));
    return card;
  }
  
}
