package com.codesa.schoolRegistry.application.UserCase.Auth.Querys.Login;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.UserRepository;
import com.codesa.schoolRegistry.infrastructure.auth.CustomUserDetails;
import com.codesa.schoolRegistry.infrastructure.auth.JwtTokenProvider;

@Service
public class LoginHandler implements RequestHandler<LoginQuery, String> {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public LoginHandler(
                        JwtTokenProvider jwtTokenProvider,
                        UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public String handle(LoginQuery query) {
       CustomUserDetails userDetails = userRepository.findByUsername(query.getUsername())
                .map(user -> new CustomUserDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));

        return jwtTokenProvider.generateToken(userDetails);
    }

}
