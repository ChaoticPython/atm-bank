package com.atm.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.bank.dao.OptionDao;
import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;
import com.atm.bank.service.IOptionService;

@Service
public class OptionServiceImpl implements IOptionService {

  @Autowired
  private OptionDao optionDao;

  @Override
  public MessageResponse retrieveMessageResponse(OptionRequest request) {
    // TODO Auto-generated method stub
    MessageResponse response = new MessageResponse();
    // You only can do the operations if you put the correct pin
    if(request.getPin() == request.getCard().getPin()) {
      if (request.getOption().equals("Check Balance")) {
        response.setMessage(optionDao.checkBalance(request.getCard()));
      } else if (request.getOption().equals("Deposit")) {
        response.setMessage(optionDao.depositMoney(request.getCard(), request.getAmount()));
      } else if (request.getOption().equals("Withdraw")) {
        if (verify(request.getCard().getAmount(), request.getAmount())) {
          response.setMessage(optionDao.withdrawMoney(request.getCard(), request.getAmount()));
        } else {
          // Throw an exception - you don't have enough balance to do this operation
        }
      } else if (request.getOption().equals("Transfer to other Account")) {
        if (verify(request.getCard().getAmount(), request.getAmount())) {
          response.setMessage(optionDao.transferMoney(request.getCard(), request.getReceiver(), request.getAmount()));
        } else {
          // Throw an exception - you don't have enough balance to do this operation
        }
      } else {
          // Throw an exception - the operation doesn't exist
      }
    } else {
      // Throw an exception - pin error (it doesn't match with the pin in the card)
    }
    return response;
  }
  
  private boolean verify(Double moneyInCard, Double amount) {
    return moneyInCard < amount ? true : false;
  }

}
