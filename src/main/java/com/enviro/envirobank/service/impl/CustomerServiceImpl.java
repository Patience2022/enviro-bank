package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.dto.ListCustomerDto;
import com.enviro.envirobank.mail.EmailSender;
import com.enviro.envirobank.model.Account;
import com.enviro.envirobank.model.Customer;
import com.enviro.envirobank.repository.BankUserRepository;
import com.enviro.envirobank.repository.CustomerExp;
import com.enviro.envirobank.repository.CustomerRepository;
import com.enviro.envirobank.service.AccountService;
import com.enviro.envirobank.service.BankUserService;
import com.enviro.envirobank.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;
    private final BankUserRepository userRepository;
    private final BankUserService bankUserService;

    @Override
    @Transactional
    public Customer createNewCustomer(Customer customer) {
        if(userRepository.existsByUserName(customer.getUserName())){
            throw new IllegalStateException("Username already exists");
        }
        if(userRepository.existsByEmail(customer.getEmail())){
            throw new IllegalArgumentException("Email supplied already exists");
        }
        if(userRepository.existsByIdentityNumber(customer.getIdentityNumber())){
            throw new IllegalArgumentException("Identity number supplied already exists");
        }
        String password = RandomStringUtils.randomAlphabetic(10);
        customer.setPassword(passwordEncoder.encode(password));
        Customer savedCustomer = customerRepository.save(customer);
        emailSender.sendPassword(customer.getFirstName(),password,customer.getEmail());

        return savedCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer, long id) {
        Customer existingCustomer =  customerRepository.findById(id).orElseThrow();
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public Customer getCustomerByIdentityNumber(String customerIdentityNumber) {
        return null;
    }

    @Override
    public Customer returnCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public Customer getCustomerUsernameOrEmail(String data) {

        return (Customer) bankUserService.findByUsernameOremail(data);
    }
    @Override
    public List<CustomerExp>getAllCustomers() {
//        int numOfAccounts = 0;
//        List<Account> accounts = accountService.getAccounts();
//        List<ListCustomerDto> listCustomerDtos = new ArrayList<>();
//        ListCustomerDto customerList = new ListCustomerDto();
//        if(Objects.nonNull(accounts) && !accounts.isEmpty()){
//
//            accounts.forEach(
//                    account -> {
//                        customerList.setFirstName(account.getCustomer().getFirstName());
//                        customerList.setLastName(account.getCustomer().getLastName());
//                        customerList.setEmail(account.getCustomer().getEmail());
//                        customerList.setNumberOfAccounts(numOfAccounts +1);
//                        if(listCustomerDtos.isEmpty()){
//                            listCustomerDtos.add(customerList);
//                        }
//                        else {
//                            listCustomerDtos.forEach(listCustomerDto -> {
//                                if(listCustomerDto.getEmail().equals(account.getCustomer().getEmail())){
//                                    listCustomerDto.setNumberOfAccounts(listCustomerDto.getNumberOfAccounts()+1);
//                                }
//                                else {
//                                    listCustomerDtos.add(customerList);
//                                }
//                            });
//                        }
//                      });
//        }
        List<CustomerExp> listCustomerDtos = customerRepository.findAllCustomers();
        return listCustomerDtos;
    }




}
