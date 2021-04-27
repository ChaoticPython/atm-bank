package com.atm.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atm.bank.model.MessageResponse;
import com.atm.bank.model.OptionRequest;
import com.atm.bank.service.IVerifyRequestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${atm-bank.context.path}")
public class AtmController {

  @Autowired
  private IVerifyRequestService requestService;

  @PostMapping("${atm-bank.paths.option}")
  public ResponseEntity<MessageResponse> messageResponse(@RequestBody OptionRequest request) {
    log.info("Request received, processing...");
    MessageResponse response = requestService.retrieveMessageResponse(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
