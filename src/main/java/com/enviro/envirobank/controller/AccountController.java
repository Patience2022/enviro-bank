package com.enviro.envirobank.controller;

import com.enviro.envirobank.dto.AccountDto;
import com.enviro.envirobank.dto.TransferRequest;
import com.enviro.envirobank.dto.WithdrawalRequestDTO;
import com.enviro.envirobank.dto.WithdrawalResponse;
import com.enviro.envirobank.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final ModelMapper modelMapper;

@PreAuthorize("hasRole('Customer')")
    @PostMapping("withdraw")
    public WithdrawalResponse withdraw(@RequestBody @Valid WithdrawalRequestDTO withdrawalRequestDTO) {

        return accountService.withdraw(withdrawalRequestDTO.getAccountNumber(),
                withdrawalRequestDTO.getAmountToWithdraw());
    }

    @GetMapping
    public ResponseEntity<Page<AccountDto>> retrieveAccounts(
            @RequestParam(required = false) String id,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {
        if (Objects.isNull(id)) {
            Page<AccountDto> accountDtos = accountService.getAccounts(pageNumber, pageSize)
                    .map(account -> modelMapper.map(account, AccountDto.class));
            return new ResponseEntity<>(accountDtos, HttpStatus.OK);
        } else {
            long customerNumber = Long.parseLong(id);
            Page<AccountDto> accounts = accountService.getClientAccounts(customerNumber,pageNumber,pageSize).map(
                    account -> modelMapper.map(account, AccountDto.class));
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
    }

    @PostMapping(path="transfer",consumes = {
            MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void transfers(@Valid @RequestBody TransferRequest transferRequest){
    System.out.println(" I am here");
            accountService.interTransfer(transferRequest);
    }


}
