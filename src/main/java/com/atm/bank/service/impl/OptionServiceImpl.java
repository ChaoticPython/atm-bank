package com.atm.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.bank.dao.OptionDao;
import com.atm.bank.exception.BadRequestException;
import com.atm.bank.helper.HelperClass;
import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;
import com.atm.bank.service.IOptionService;

@Service
public class OptionServiceImpl implements IOptionService {

  @Autowired
  private OptionDao optionDao;
  
  @Autowired
  private HelperClass helper;

  @Override
  public MessageResponse retrieveMessageResponse(OptionRequest request) {
    MessageResponse response = new MessageResponse();
    // You only can do the operations if you put the correct pin
    if (request.getPin().equals(request.getCard().getPin())) {
      if (request.getOption().equals("Check")) {
        response.setMessage(optionDao.checkBalance(request.getCard()));
      } else {
        // Invoke other function
        response.setMessage(helper.executeOption(request));
      }
      return response;
    } else {
      // Throw an exception - pin error (it doesn't match with the pin in the card)
      throw new BadRequestException("An error has occurred, incorrect pin");
    }

  }
}
