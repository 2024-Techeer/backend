package com.example.Backend.domain.user;

import com.example.Backend.domain.user.dto.UserLoginDto;
import com.example.Backend.domain.user.dto.UserRegisterDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController//@RestController는 @Controller에 @ResponseBody가 추가된 것입니다.
// 당연하게도 RestController의 주용도는 Json 형태로 객체 데이터를 반환하는 것
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserRegisterDto userDto){
        User registeredUser = userService.registerUser(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    @GetMapping("/register")
    public ResponseEntity<String> register(){
        return ResponseEntity.ok("register page");
    }

//    @PostMapping("auth/login")
//    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto){
//    }
    @GetMapping("/hello")//test 성공;
        public ResponseEntity<String> hello(){
            return ResponseEntity.ok("hello");
    }

}
