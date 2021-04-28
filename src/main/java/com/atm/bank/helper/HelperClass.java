package com.atm.bank.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atm.bank.dao.OptionDao;
import com.atm.bank.exception.BadRequestException;
import com.atm.bank.model.Card;
import com.atm.bank.model.OptionRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.PostConstruct;

@Component
public class HelperClass {
  
  private Map<String, BiFunction<Card, Double, String>> mapDWT;

  @Autowired
  private OptionDao optionDao;

  @PostConstruct
  public void init() {
    mapDWT = new HashMap<>();
    mapDWT.put("Deposit", optionDao::depositMoney);
    mapDWT.put("Withdraw", optionDao::withdrawMoney);
    mapDWT.put("Transfer", optionDao::transferMoney);
  }

  public String executeOption(OptionRequest request) {
    if (mapDWT.containsKey(request.getOption())) {
      if (request.getOption().equals("Withdraw") || request.getOption().equals("Transfer")) {
        if (verify(request.getCard().getAmount(), request.getAmount())) {
          return mapDWT.get(request.getOption()).apply(request.getCard(), request.getAmount());
        } else {
          throw new BadRequestException(
              "You don't have enough money to do this operation or you don't put the receiver");
        }
      } else {
        return mapDWT.get(request.getOption()).apply(request.getCard(), request.getAmount());
      }
    } else {
      throw new BadRequestException("An error has occurred, check the operation requested");
    }
  }
  
  private boolean verify(Double moneyInCard, Double amount) {
    return moneyInCard > amount ? true : false;
  }

}
