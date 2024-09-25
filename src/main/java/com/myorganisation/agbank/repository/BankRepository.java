package com.myorganisation.agbank.repository;

import com.myorganisation.agbank.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByAccountno(String accountno);
}
