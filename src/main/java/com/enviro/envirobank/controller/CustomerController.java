package com.enviro.envirobank.controller;

import com.enviro.envirobank.dto.CustomerDto;
import com.enviro.envirobank.dto.ListCustomerDto;
import com.enviro.envirobank.model.Customer;
import com.enviro.envirobank.model.UserRole;
import com.enviro.envirobank.repository.CustomerExp;
import com.enviro.envirobank.service.CustomerService;
import com.enviro.envirobank.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        if(customerDto.getUserRolesName() ==null){
            customerDto.setUserRolesName("Customer");
        }
    UserRole userRole = roleService.getUserRole(customerDto.getUserRolesName());
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

        Customer existingCustomer = modelMapper.map(customerDto, Customer.class);
         CustomerDto updatedCustomer = modelMapper.map(
                 customerService.updateCustomer(existingCustomer,id), CustomerDto.class
         );
         return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id){
        CustomerDto updatedCustomer = modelMapper.map(
                customerService.returnCustomer(id), CustomerDto.class);
     return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CustomerExp>> getAllCustomers(){
    List<CustomerExp> customerList= customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
    @GetMapping("find/{data}")
    public ResponseEntity<CustomerDto> getCustomerUsernameOrEmail(@PathVariable String data){
        CustomerDto updatedCustomer = modelMapper.map(customerService.getCustomerUsernameOrEmail(data), CustomerDto.class);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
}
