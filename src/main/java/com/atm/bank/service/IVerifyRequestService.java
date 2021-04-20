package com.atm.bank.service;

import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;

public interface IVerifyRequestService {

  MessageResponse retrieveMessageResponse(OptionRequest request);
  
}
