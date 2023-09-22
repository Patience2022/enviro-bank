package com.enviro.envirobank.controller;

import com.enviro.envirobank.dto.AccountDto;
import com.enviro.envirobank.dto.CustomerDto;
import com.enviro.envirobank.dto.WithdrawalRequestDTO;

import com.enviro.envirobank.repository.CustomerRepository;
import com.enviro.envirobank.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final ModelMapper modelMapper;


    @PostMapping("withdraw")
    public void withdraw(@RequestBody @Valid WithdrawalRequestDTO withdrawalRequestDTO) {
        accountService.withdraw(withdrawalRequestDTO.getAccountNumber(),
                withdrawalRequestDTO.getAmountToWithdraw());
    }
    @GetMapping
    public ResponseEntity<Page<AccountDto>> retrieveAccounts(
            @RequestParam(required = false) Long customerNumber,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {
        if (Objects.isNull(customerNumber)) {
            Page<AccountDto> accountDtos = accountService.retrieveAccounts(pageNumber, pageSize)
                    .map(account -> modelMapper.map(account, AccountDto.class));
            return new ResponseEntity<>(accountDtos, HttpStatus.OK);
        } else {
            Page<AccountDto> accounts = accountService.getClientAccounts(customerNumber,pageNumber,pageSize).map(
                    account -> modelMapper.map(account, AccountDto.class));
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
    }
}
