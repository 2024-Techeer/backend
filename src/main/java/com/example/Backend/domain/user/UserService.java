package com.example.Backend.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(UserRegisterDto userRegisterDto){
        User newUser= new User();
        newUser.setName(userRegisterDto.getName());
        newUser.setEmail(userRegisterDto.getEmail());
        newUser.setPassword(userRegisterDto.getPassword());// 실제 서비스에서는 비밀번호를 암호화하여 저장해야 합니다.
        return userRepository.save(newUser);
    }
}
