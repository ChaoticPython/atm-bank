package com.atm.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    // If our PIN, the card or the option are null, we can't proceed with the request
    if (request.getPin() == null || request.getOption() == null || request.getCard() == null) {
      // Throw an exception
    } else {
      response = optionService.retrieveMessageResponse(request); // Invoke the other service
    }
    return response;
  }

}
