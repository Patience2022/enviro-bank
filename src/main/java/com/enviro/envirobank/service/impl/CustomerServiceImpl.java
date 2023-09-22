package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.dto.PasswordResetRequest;
import com.enviro.envirobank.mail.EmailSender;
import com.enviro.envirobank.model.*;
import com.enviro.envirobank.repository.*;
import com.enviro.envirobank.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ConfirmationServiceImpl loginService;
    private final EmailSender emailSender;
    private ConfirmationRepository confirmationRepository;
    private PasswordEncoder passwordEncoder;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository accountRepository;
    private final BankUserRepository bankUser;




    @Override
    @Transactional
    public BankUser createNewCustomer(BankUser customer) {
        String token =RandomStringUtils.randomAlphabetic(10);
        customer.setPassword(passwordEncoder.encode(token));
        BankUser savedCustomer = bankUser.save(customer);

        LoginConfirmation loginConfirmation = new LoginConfirmation();
        loginConfirmation.setCreatedAt(LocalDate.now());
        loginConfirmation.setBankUser(savedCustomer);
        loginConfirmation.setToken(token);

//        Change to minutes later
        loginConfirmation.setExpireAt(LocalDate.now().plusDays(1));

        String link = "http://localhost:8080/api/v1/auth/confirmToken?token="+token+"&email="+savedCustomer.getEmail()
                +"&name="+savedCustomer.getFirstName();
        System.out.println(link);
        emailSender.sendEmail(savedCustomer.getEmail(),emailSender.buildEmail(savedCustomer.getFirstName(), link, token));

        System.out.println(token);

        return savedCustomer;
    }

    @Override
    public BankUser updateCustomer(BankUser customer) {
        return bankUser.save(customer);
    }

    @Override
    public Customer getCustomerByIdentityNumber(String customerIdentityNumber) {
        return null;
    }


    @Override
    public BankUser setPassword(String name, String email, String token) {
        AccountTypes type = accountTypeRepository.findById(1L).orElseThrow();
        LoginConfirmation loginConfirmation = confirmationRepository
                .findLoginConfirmationByToken(token).orElseThrow();
        Account account = accountRepository.findById(2L).orElseThrow();

        BankUser customer = bankUser.findById(
                loginConfirmation.getBankUser().getId()).orElseThrow();

        if(email.equals(customer.getEmail()) && loginConfirmation.getBankUser().getId()==
                customer.getId()){
            customer.setPassword(passwordEncoder.encode(token));
            customer.setEnabled(true);
            customer.setLocked(false);
            bankUser.save(customer);
        }
       return customer;
    }

    @Override
    public BankUser resetPassword(PasswordResetRequest request) {
        return null;
    }

    @Override
    public BankUser returnCustomer(Long id) {
        return bankUser.findById(id).orElseThrow();
    }

}
