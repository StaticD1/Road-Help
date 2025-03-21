package ru.itis.roadhelp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.roadhelp.form.SignUpForm;
import ru.itis.roadhelp.entity.User;
import ru.itis.roadhelp.repositories.UserRepository;
import ru.itis.roadhelp.services.SignUpService;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm form) {

        if (userRepository.existsByEmail(form.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        User user = User.builder()
                .passwordHash(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();
        userRepository.save(user);
    }
}
