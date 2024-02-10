package com.codex.tomobilina.services;

import com.codex.tomobilina.dtos.CredentialsDto;
import com.codex.tomobilina.dtos.SignUpDto;
import com.codex.tomobilina.dtos.UserDto;
import com.codex.tomobilina.models.User;
import com.codex.tomobilina.exceptions.AppException;
import com.codex.tomobilina.mappers.UserMapper;
import com.codex.tomobilina.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByEmail(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        
        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());
        user.setDateheure(t);
        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByEmail(String login) {
        User user = userRepository.findByEmail(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }
    
}
