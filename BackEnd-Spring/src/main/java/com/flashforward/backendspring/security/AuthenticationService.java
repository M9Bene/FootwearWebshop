package com.flashforward.backendspring.security;


import com.flashforward.backendspring.model.User;
import com.flashforward.backendspring.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

        String jwtToken = jwtService.generateToken(user);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(RegisterRequest request) {

        return null;

    }
}
