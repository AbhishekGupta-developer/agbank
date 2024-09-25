package com.myorganisation.agbank.service;

import com.myorganisation.agbank.dto.BankDTO;

public interface BankService {
    public BankDTO createDummyAccount();
    public BankDTO viewAccount(String accountno);
    public Double checkBalance(String accountno);
    public Double creditAmount(String accountno, Double amount);
    public Double debitAmount(String accountno, Double amount);
}
