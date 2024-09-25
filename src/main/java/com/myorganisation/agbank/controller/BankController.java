package com.myorganisation.agbank.controller;

import com.myorganisation.agbank.dto.BankDTO;
import com.myorganisation.agbank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return new ResponseEntity<>("Bank Server is live!", HttpStatusCode.valueOf(200));
    }

    @PostMapping("/dummy-account")
    public ResponseEntity<BankDTO> createDummyAccount() {
        return new ResponseEntity<>(bankService.createDummyAccount(), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/account")
    public ResponseEntity<BankDTO> viewAccount(@RequestBody Map<String, String> payload) {
        String accountno = payload.get("accountno");

        return new ResponseEntity<>(bankService.viewAccount(accountno), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> checkBalance(@RequestBody Map<String, String> payload) {
        String accountno = payload.get("accountno");

        return new ResponseEntity<>(bankService.checkBalance(accountno), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/credit")
    public ResponseEntity<Map<String, Object>> creditAccount(@RequestBody Map<String, String> payload) {
        String accountno = payload.get("accountno");
        Double amount = Double.valueOf(payload.get("amount"));

        bankService.creditAmount(accountno, amount);
        BankDTO bankDTO = bankService.viewAccount(accountno);

        Map<String, Object> response = new HashMap<>();
        response.put("bankDetails", bankDTO);
        response.put("creditAmount", amount);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/debit")
    public ResponseEntity<Map<String, Object>> debitAccount(@RequestBody Map<String, String> payload) {
        String accountno = payload.get("accountno");
        Double amount = Double.valueOf(payload.get("amount"));

        bankService.debitAmount(accountno, amount);
        BankDTO bankDTO = bankService.viewAccount(accountno);

        Map<String, Object> response = new HashMap<>();
        response.put("bankDetails", bankDTO);
        response.put("debitAmount", amount);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

}
