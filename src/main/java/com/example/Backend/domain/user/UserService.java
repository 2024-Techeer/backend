package com.example.Backend.domain.user;

import com.example.Backend.domain.user.dto.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Backend.domain.user.dto.UserLoginDto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class UserService {
//Java에서는 인터페이스 타입으로 변수를 선언할 수 있습니다.이러한 방법은 객체 지향 프로그래밍에서 다형성을 활용하는 데 중요한 역할을 함.
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegisterDto userRegisterDto) {
        User newUser = User.builder()
                .name(userRegisterDto.getName())
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .build();
        return userRepository.save(newUser);
    }

    public Authentication getAuthentication(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail());
        if (user == null) {
            System.out.println("User not found with email: " + userLoginDto.getEmail());
            return null;
        }
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            System.out.println("Password mismatch for user: " + user.getEmail());
            return null;
        }
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    }



//    public String encodePassword(String password, PasswordEncoder passwordEncoder){
//        return passwordEncoder.encode(password);
    }// "단일 책임 원칙(Single Responsibility Principle, SRP)"과 "분리 및 추상화 지키기위해 User.java에서 Userservice로이동
//UserService 사용자의 데이터를 처리하고 관리하는 데 필요한 로직을 캡슐화 -> 여기가 적합.


