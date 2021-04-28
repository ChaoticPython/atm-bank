package com.atm.bank.dao;

import org.springframework.stereotype.Component;

import com.atm.bank.model.Card;

@Component
public class OptionDao {

  public String checkBalance(Card card) {
    return "Amount in the account: " + card.getAmount();
  }
  
  public String depositMoney(Card card, Double amountToDeposit) {
    card.setAmount(card.getAmount() + amountToDeposit);
    return "Operation (deposit) completed successfully." + " Your actual amount is: "
        + card.getAmount() + ". Don't forget to take your card";
  }

  public String withdrawMoney(Card card, Double withdrawAmount) {
    card.setAmount(card.getAmount() - withdrawAmount);
    return "Operation (withdraw) completed successfully." + " Your actual amount is: "
        + card.getAmount() + ". Don't forget to take your card";
  }

  public String transferMoney(Card card, Double amountToTransfer) {
    card.setAmount(card.getAmount() - amountToTransfer);
    return "The transaction for the other account has been successful."
        + " The actual amount in your account is: " + card.getAmount();
  }

}
