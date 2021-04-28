package com.atm.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.bank.exception.BadRequestException;
import com.atm.bank.model.Card;
import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;
import com.atm.bank.service.IOptionService;
import com.atm.bank.service.IVerifyRequestService;

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
    if (request.getPin() == null || request.getOption() == null) {
      // Throw an exception - you need a pin and option to do any operation
      throw new BadRequestException("The request received is null or doesn't have all the attributes");
    } else {
      // Invoke the other service
      response = optionService.retrieveMessageResponse(request);
    }
    return response;
  }

  private Card createCard() {
    Card card = new Card();
    card.setPin(1539);
    card.setAmount((double) Math.round(Math.random() * 100000));
    return card;
  }
  
}
