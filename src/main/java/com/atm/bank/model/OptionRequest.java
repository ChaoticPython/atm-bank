package com.atm.bank.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionRequest {

  private Integer pin;
  private String option;
  private String deceiver;
  private Double amount;
  
}
