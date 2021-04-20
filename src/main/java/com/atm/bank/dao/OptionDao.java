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
    return "Operation (deposit) completed successfully\n" + "Your actual amount is:"
        + card.getAmount() + "\nDon't forget to take your card";
  }

  public String withdrawMoney(Card card, Double withdrawAmount) {
    card.setAmount(card.getAmount() - withdrawAmount);
    return "Operation (withdraw) completed successfully\n" + "Your actual amount is:"
        + card.getAmount() + "\nDon't forget to take your card";
  }

  public String transferMoney(Card card, String otherAccount, Double amountToTransfer) {
    card.setAmount(card.getAmount() - amountToTransfer);
    return "The transaction for the " + otherAccount + " account has been successful\n"
        + "The actual amount in your account is:" + card.getAmount();
  }

}
