package com.jpa.devtiho.service;

import com.jpa.devtiho.model.UserPrincipal;
import com.jpa.devtiho.model.Users;
import com.jpa.devtiho.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userRepo.findByUsername(username);

        if (optionalUser.isEmpty()) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        Users user = optionalUser.get(); // Extract the Users object from Optional
        return new UserPrincipal(user);  // Pass the Users object to the UserPrincipal constructor

    }
}
