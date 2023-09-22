package com.enviro.envirobank.dto;

import com.enviro.envirobank.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsResponse {
    private List<Account> accountsDetails;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean last;
}
