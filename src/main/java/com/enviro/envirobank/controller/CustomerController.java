package com.enviro.envirobank.controller;

import com.enviro.envirobank.dto.CustomerDto;
import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.model.Customer;
import com.enviro.envirobank.model.UserRole;
import com.enviro.envirobank.service.CustomerService;
import com.enviro.envirobank.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
private CustomerService customerService;
private ModelMapper modelMapper;
private final UserRoleService roleService;


@PreAuthorize("hasRole({'Admin'})")
    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@Valid @RequestBody CustomerDto customerDto){
        Customer customer = modelMapper.map(customerDto, Customer.class);
    UserRole userRole = roleService.getUserRole(customerDto.getUserRoles());
    Set<UserRole> roles = new HashSet<>();
    roles.add(userRole);
    customer.setUserRoles(roles);
        CustomerDto savedCustomer = modelMapper.map(
                customerService.createNewCustomer(customer), CustomerDto.class);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,
                                                      @RequestBody @Valid CustomerDto customerDto){
         customerDto.setId(id);
         BankUser existingCustomer = modelMapper.map(customerDto, BankUser.class);
         CustomerDto updatedCustomer = modelMapper.map(
                 customerService.updateCustomer(existingCustomer), CustomerDto.class
         );
         return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }

}
