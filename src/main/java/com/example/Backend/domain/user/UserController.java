package com.example.Backend.domain.user;

import com.example.Backend.domain.user.dto.UserLoginDto;
import com.example.Backend.domain.user.dto.UserRegisterDto;
import com.example.Backend.global.jwt.TokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController//@RestController는 @Controller에 @ResponseBody가 추가된 것입니다.
// 당연하게도 RestController의 주용도는 Json 형태로 객체 데이터를 반환하는 것
public class UserController {
    private final UserService userService;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserController(UserService userService, TokenProvider tokenProvider){
        this.userService = userService;
        this.tokenProvider=tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserRegisterDto userDto) {
        User registeredUser = userService.registerUser(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        Authentication authentication = userService.getAuthentication(userLoginDto);
        System.out.println(authentication);
        if (authentication != null && authentication.isAuthenticated()) {
            String jwt = tokenProvider.createToken(authentication);
            return ResponseEntity.ok(jwt);  // JWT 토큰 반환
        }
        return ResponseEntity.status(401).body("Login failed");
    }
//    @PostMapping("auth/login")
//    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto){
//}


    @GetMapping("/hello")//test 성공;
        public ResponseEntity<String> hello(){
            return ResponseEntity.ok("hello");
    }

}
