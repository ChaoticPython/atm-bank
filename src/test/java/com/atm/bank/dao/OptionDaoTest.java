package com.atm.bank.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.atm.bank.model.Card;

@RunWith(MockitoJUnitRunner.class)
public class OptionDaoTest {

  @InjectMocks
  private OptionDao optionDao;

  @Test
  public void testCheckBalance() {
    Card card = generateCard();
    String previewResponse = "Amount in the account: " + card.getAmount();
    String response = optionDao.checkBalance(card);

    assertEquals(previewResponse, response);
  }

  @Test
  public void testDepositMoney() {
    Card card = generateCard();
    String previewResponse = "Operation (deposit) completed successfully."
        + " Your actual amount is: " + card.getAmount() + ". Don't forget to take your card";
    String response = optionDao.depositMoney(card, Mockito.anyDouble());

    assertEquals(previewResponse, response);
  }

  @Test
  public void testWithdrawMoney() {
    Card card = generateCard();
    String previewResponse = "Operation (withdraw) completed successfully."
        + " Your actual amount is: " + card.getAmount() + ". Don't forget to take your card";
    String response = optionDao.withdrawMoney(card, Mockito.anyDouble());

    assertEquals(previewResponse, response);
  }

  @Test
  public void testTransferMoney() {
    Card card = generateCard();
    String previewResponse = "The transaction for the other account has been successful."
        + " The actual amount in your account is: " + card.getAmount();
    String response = optionDao.transferMoney(card, Mockito.anyDouble());

    assertEquals(previewResponse, response);
  }

  private Card generateCard() {
    Card card = new Card();
    card.setPin(Mockito.anyInt());
    card.setAmount(Mockito.anyDouble());
    return card;
  }

}
