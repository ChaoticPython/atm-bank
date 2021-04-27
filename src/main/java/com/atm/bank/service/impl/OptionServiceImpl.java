package com.atm.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.bank.dao.OptionDao;
import com.atm.bank.exception.BadRequestException;
import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;
import com.atm.bank.service.IOptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OptionServiceImpl implements IOptionService {

  @Autowired
  private OptionDao optionDao;

  @Override
  public MessageResponse retrieveMessageResponse(OptionRequest request) {
    // TODO Auto-generated method stub
    MessageResponse response = new MessageResponse();
    // You only can do the operations if you put the correct pin
    log.info("pin introduced: {} | pin of the card: {}", request.getPin(),
        request.getCard().getPin());
    if (request.getPin().equals(request.getCard().getPin())) {
      log.info("Request accepted, processing the operation...");
      if (request.getOption().equals("Check Balance")) {
        log.info("Operation accepted, checking the balance...");
        response.setMessage(optionDao.checkBalance(request.getCard()));
      } else if (request.getOption().equals("Deposit")) {
        log.info("Operation accepted, depositing balance...");
        response.setMessage(optionDao.depositMoney(request.getCard(), request.getAmount()));
      } else if (request.getOption().equals("Withdraw")) {
        if (verify(request.getCard().getAmount(), request.getAmount())) {
          log.info("Operation accepted, withdrawing money...");
          response.setMessage(optionDao.withdrawMoney(request.getCard(), request.getAmount()));
        } else {
          // Throw an exception - you don't have enough balance to do this operation
          log.info("An error has occurred");
          throw new BadRequestException("You don't have enough balance to do this operation");
        }
      } else if (request.getOption().equals("Transfer to other Account")) {
        if (verify(request.getCard().getAmount(), request.getAmount())
            && request.getReceiver() != null) {
          log.info("Operation accepted, transfering to another account...");
          response.setMessage(optionDao.transferMoney(request.getCard(), request.getReceiver(),
              request.getAmount()));
        } else {
          // Throw an exception - you don't have enough balance to do this operation
          log.info("An error has occurred");
          throw new BadRequestException(
              "You don't have enough balance to do this operation or you don't put the receiver");
        }
      } else {
        // Throw an exception - the operation doesn't exist
        log.info("An error has occurred");
        throw new BadRequestException("An error has occurred");
      }
    } else {
      // Throw an exception - pin error (it doesn't match with the pin in the card)
      log.info("An error has occurred, incorrect pin");
      throw new BadRequestException("An error has occurred, incorrect pin");
    }
    return response;
  }

  private boolean verify(Double moneyInCard, Double amount) {
    return moneyInCard > amount ? true : false;
  }

}
