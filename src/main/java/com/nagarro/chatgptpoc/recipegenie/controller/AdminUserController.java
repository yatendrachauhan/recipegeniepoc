package com.nagarro.chatgptpoc.recipegenie.controller;

import javax.servlet.http.HttpSession;

import com.nagarro.chatgptpoc.recipegenie.model.AdminUser;
import com.nagarro.chatgptpoc.recipegenie.service.AdminUserService;
import com.nagarro.chatgptpoc.recipegenie.utility.APIException;
import com.nagarro.chatgptpoc.recipegenie.utility.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AdminUser adminUser, HttpSession session) throws APIException {
        try{
            AdminUser dbUser = adminUserService.login(adminUser.getUsername(), adminUser.getPassword());
            if (dbUser != null) {
                session.setAttribute("adminEmail",dbUser.getUserEmail());
                return "Login Successful for " + dbUser.getUserEmail();
            } else {
                throw new APIException(ErrorCodeEnum.AUTHENTICATION_FAILED);
            }
        }catch (IllegalArgumentException ex){
            throw new APIException(ErrorCodeEnum.USER_BAD_REQUEST, "for Login");
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) throws APIException {
        String email = (String) session.getAttribute("adminEmail");
        if(email==null){
            throw new APIException(ErrorCodeEnum.USER_BAD_REQUEST, "for Logout");
        }
        session.removeAttribute("adminEmail");
        return "Logged Out User" + email;
    }
}
