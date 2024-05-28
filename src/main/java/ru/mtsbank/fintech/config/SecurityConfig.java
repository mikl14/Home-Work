package ru.mtsbank.fintech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mtsbank.fintech.entity.AnimalUser;
import ru.mtsbank.fintech.repositories.AnimalUserRepository;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AnimalUserRepository animalUserRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

    /*   authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("jam")
                .password(passwordEncoder().encode("pass"))
                .roles("ADMIN")
                .and()
                .withUser("mikl")
                .password(passwordEncoder().encode("ss"))
                .roles("USER");
*/
        authenticationManagerBuilder.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AnimalUser user = animalUserRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("no user " + username));
                if (user == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                Set<SimpleGrantedAuthority> testSet = new HashSet<>();

       //         new SimpleGrantedAuthority("ROLE_" + role)
                testSet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new User(user.getUsername(), passwordEncoder().encode(user.getPassword()), testSet);
            }
        });
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/add").hasRole("ADMIN")
                .antMatchers("/index").hasAnyRole("USER", "ADMIN")
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
