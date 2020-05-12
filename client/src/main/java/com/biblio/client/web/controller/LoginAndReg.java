package com.biblio.client.web.controller;

import com.biblio.client.DTO.UserRegister;
import com.biblio.client.proxy.MicroserviceUserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginAndReg {

    @Autowired
    private MicroserviceUserProxy userProxy;

    @ModelAttribute("user")
    public UserRegister userRegister() {
        return new UserRegister();
    }

    @PostMapping("/registration")
    public String registPost(@ModelAttribute("user") @Valid UserRegister userRegister,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "signup";
        }
        userProxy.createUser(userRegister);


        return "redirect:/signup?success";
    }

    @GetMapping("/registration")
    public String registGet(Model model) {


        return "signup";
    }
}
