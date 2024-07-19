package com.auth.controllers;

import com.auth.Repositories.UserRepository;
import com.auth.dtos.LoginRequestDto;
import com.auth.dtos.SignUpRequstBody;
import com.auth.models.Token;
import com.auth.models.User;
import com.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
   private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpRequstBody signUpRequstBody) {
        String name=signUpRequstBody.getName();
        String email=signUpRequstBody.getEmail();
        String password=signUpRequstBody.getPassword();
      return  userService.signUp(name, email, password);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto)
    {
        String mail=loginRequestDto.getEmail();
        String password=loginRequestDto.getPassword();
       return userService.login(mail,password);

    }
    @PostMapping("/logout")
    public ResponseEntity<Void>logout(@RequestParam("token") String token)
    {
        userService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public Boolean validateToken(@PathVariable("token") String token)
    {

      return   userService.validateToken(token);
    }
}
