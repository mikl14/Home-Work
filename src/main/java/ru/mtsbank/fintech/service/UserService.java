package ru.mtsbank.fintech.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mtsbank.fintech.entity.AnimalUser;
import ru.mtsbank.fintech.repositories.AnimalUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final AnimalUserRepository animalUserRepository;

    private final AuthenticationManager authenticationManager;

    public UserService(@Lazy AnimalUserRepository animalUserRepository, @Lazy AuthenticationManager authenticationManager) {
        this.animalUserRepository = animalUserRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AnimalUser user = animalUserRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("no user " + username));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        return new AnimalUser(user.getUsername(), user.getPassword(), authorities);
    }


}
