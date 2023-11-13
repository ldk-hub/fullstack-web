package com.hodoledu.service;

import com.hodoledu.domain.User;
import com.hodoledu.exception.AlreadyExistsEmailException;
import com.hodoledu.repository.UserRepository;
import com.hodoledu.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(Signup signup) {

        Optional<User> userOpt = userRepository.findByEmail(signup.getEmail());
        if (userOpt.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        //Scrypt 암호화방식 코드

        String encryptPw = passwordEncoder.encode(signup.getPassword());


        var user = User.builder()
                .name(signup.getName())
                .pw(encryptPw)
                .email(signup.getEmail())
                .build();
        userRepository.save(user);
    }
}
