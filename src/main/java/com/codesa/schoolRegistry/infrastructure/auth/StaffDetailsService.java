package com.codesa.schoolRegistry.infrastructure.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.core.domain.User;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.UserRepository;

@Service
public class StaffDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public StaffDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));

        return new CustomUserDetails(user);
    }
}

