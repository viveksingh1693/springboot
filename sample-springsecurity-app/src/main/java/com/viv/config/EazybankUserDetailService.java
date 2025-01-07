package com.viv.config;

import com.viv.model.Customer;
import com.viv.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class EazybankUserDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository
                .findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User details not found for the use"+username));


//        List<GrantedAuthority> grantedAuthorities =
//                List.of(new SimpleGrantedAuthority(customer.getRole()));

        List<SimpleGrantedAuthority> authorities = customer.getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .toList();


        return  new User(customer.getEmail(),customer.getPwd(),authorities);

    }
}
