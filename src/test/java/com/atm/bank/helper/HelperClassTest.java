package com.atm.bank.helper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.atm.bank.dao.OptionDao;
import com.atm.bank.exception.BadRequestException;
import com.atm.bank.model.Card;
import com.atm.bank.model.OptionRequest;

@RunWith(MockitoJUnitRunner.class)
public class HelperClassTest {

  @Mock
  private OptionDao optionDao;

  @InjectMocks
  private HelperClass helperClass;

  @Test(expected = BadRequestException.class)
  public void testExecuteOptionWithdrawFail() {
    OptionRequest request = generateRequest();
    request.setOption("Withdraw");
    request.getCard().setAmount(0.0);

    helperClass.init();
    helperClass.executeOption(request);
  }

  @Test(expected = BadRequestException.class)
  public void testExecuteOptionTransferFail() {
    OptionRequest request = generateRequest();
    request.setOption("Transfer");
    request.getCard().setAmount(0.0);

    helperClass.init();
    helperClass.executeOption(request);
  }

  @Test(expected = BadRequestException.class)
  public void testExecuteOptionOperationDoesntExistFail() {
    OptionRequest request = generateRequest();
    request.setOption("anything");

    helperClass.init();
    helperClass.executeOption(request);
  }

  @Test
  public void testExecuteOperation() {
    OptionRequest request = generateRequest();
    request.setOption("Deposit");
    String previewResponse =
        "Operation (deposit) completed successfully." + " Your actual amount is: "
            + request.getCard().getAmount() + ". Don't forget to take your card";

    Mockito.when(optionDao.depositMoney(request.getCard(), request.getAmount()))
        .thenReturn(previewResponse);

    helperClass.init();
    String response = helperClass.executeOption(request);

    assertNotNull(response);
  }

  @Test
  public void testExecuteOptionWithoutFail() {
    OptionRequest request = generateRequest();
    request.setOption("Withdraw");
    String previewResponse = "Operation (withdraw) completed successfully."
        + " Your actual amount is: " + request.getCard().getAmount() + ". Don't forget to take your card";

    Mockito.when(optionDao.withdrawMoney(request.getCard(), request.getAmount()))
        .thenReturn(previewResponse);

    helperClass.init();
    String response = helperClass.executeOption(request);

    assertNotNull(response);
  }

  private OptionRequest generateRequest() {
    OptionRequest request = new OptionRequest();
    request.setPin(3289);
    request.setOption("option");
    request.setReceiver("receiver");
    request.setAmount(100.0);
    request.setCard(generateCard());
    return request;
  }

  private Card generateCard() {
    Card card = new Card();
    card.setAmount(129.0);
    card.setPin(3289);
    return card;
  }

}
