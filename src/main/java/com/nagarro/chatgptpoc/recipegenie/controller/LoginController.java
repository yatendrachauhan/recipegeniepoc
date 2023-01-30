package com.nagarro.chatgptpoc.recipegenie.controller;

import com.nagarro.chatgptpoc.recipegenie.model.LoginRequest;
import com.nagarro.chatgptpoc.recipegenie.model.LoginResponse;
import com.nagarro.chatgptpoc.recipegenie.service.UserService;
import com.nagarro.chatgptpoc.recipegenie.utility.APIException;
import com.nagarro.chatgptpoc.recipegenie.utility.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/admin")

public class LoginController {
    private final UserService userService;
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) throws APIException {
        try{
            if (userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
                session.setAttribute("username",loginRequest.getUsername());
                return new ResponseEntity<>(new LoginResponse("Login Successful for User " + loginRequest.getUsername()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new LoginResponse("Invalid Credentials"), HttpStatus.UNAUTHORIZED);
            }
        }catch (IllegalArgumentException ex){
            throw new APIException(ErrorCodeEnum.USER_BAD_REQUEST, "for Login");
        }
    }

    @PostMapping(value = "/logout",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String logout(HttpSession session) throws APIException {
        String email = (String) session.getAttribute("username");
        if(email==null){
            throw new APIException(ErrorCodeEnum.USER_BAD_REQUEST, "User not logged In");
        }
        session.removeAttribute("username");
        return "Successfully Logged Out User " + email;
    }
}
