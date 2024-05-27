package ru.mtsbank.fintech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mtsbank.fintech.entity.AnimalUser;
import ru.mtsbank.fintech.repositories.AnimalUserRepository;

@Service
public class UserService implements UserDetailsService {

    private final AnimalUserRepository animalUserRepository;

    private final AuthenticationManager authenticationManager;

    public UserService(@Lazy AnimalUserRepository animalUserRepository,@Lazy AuthenticationManager authenticationManager) {
        this.animalUserRepository = animalUserRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AnimalUser user = animalUserRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return user;
    }
}
