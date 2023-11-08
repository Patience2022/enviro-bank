package com.enviro.envirobank.controller;

import com.enviro.envirobank.dto.TransactionDTO;
import com.enviro.envirobank.model.Transaction;
import com.enviro.envirobank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@AllArgsConstructor

public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(path = "account-transactions", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TransactionDTO>> getAccountTransaction(@RequestParam String accountNumber){
        List<TransactionDTO> transactions = transactionService.getAccountTransations(accountNumber);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
