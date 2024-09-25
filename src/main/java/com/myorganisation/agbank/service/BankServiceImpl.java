package com.myorganisation.agbank.service;

import com.myorganisation.agbank.dto.BankDTO;
import com.myorganisation.agbank.repository.BankRepository;
import com.myorganisation.agbank.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    BankRepository bankRepository;

    //Dummy Account creation method implementation...
    @Override
    public BankDTO createDummyAccount() {

        Bank bank = new Bank();
        bank.setAccountno("000000123");
        bank.setBalance(0D);

        bank = bankRepository.save(bank);

        BankDTO bankDTO = BankDTO
                .builder()
                .accountno(bank.getAccountno())
                .balance(bank.getBalance())
                .build();

        return bankDTO;
    }

    @Override
    public BankDTO viewAccount(String accountno) {
        Bank bank = bankRepository.findByAccountno(accountno);

        BankDTO bankDTO = BankDTO
                .builder()
                .accountno(bank.getAccountno())
                .balance(bank.getBalance())
                .build();

        return bankDTO;
    }

    @Override
    public Double checkBalance(String accountno) {
        Bank bank = bankRepository.findByAccountno(accountno);
        return bank.getBalance();
    }

    @Override
    public Double creditAmount(String accountno, Double amount) {
        Bank bank = bankRepository.findByAccountno(accountno);
        if(amount>0) {
            Double newBalance = bank.getBalance() + amount;
            bank.setBalance(newBalance);
            bankRepository.save(bank);
        }

        return checkBalance(accountno);
    }

    @Override
    public Double debitAmount(String accountno, Double amount) {
        Bank bank = bankRepository.findByAccountno(accountno);

        if(amount>0 && amount <= bank.getBalance()) {
            Double newBalance = bank.getBalance() - amount;
            bank.setBalance(newBalance);
            bankRepository.save(bank);
        }

        return checkBalance(accountno);
    }

}
