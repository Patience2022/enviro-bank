package com.enviro.envirobank.security;

import com.enviro.envirobank.exception.ResourceNotFoundException;
import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.repository.BankUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService {
    private final BankUserRepository bankUserRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        BankUser user = bankUserRepo.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                ()-> new ResourceNotFoundException("user", "username or email", usernameOrEmail)
        );
        Set<GrantedAuthority> authorities = user.getUserRoles()
                .stream()
                .map(
                        (role)-> new SimpleGrantedAuthority("ROLE_"+role.getName())
                ).collect(Collectors.toSet());
        return new User(user.getEmail(), user.getPassword().toString(), authorities);

    }
}
