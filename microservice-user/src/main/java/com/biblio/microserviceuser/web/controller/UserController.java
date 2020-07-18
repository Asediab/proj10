package com.biblio.microserviceuser.web.controller;

import com.biblio.microserviceuser.DTO.UserDTO;
import com.biblio.microserviceuser.model.User;
import com.biblio.microserviceuser.web.exceptions.UserNotFoundException;
import com.biblio.microserviceuser.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/current")
    public Principal getUser(OAuth2Authentication principal) {

        return principal.getUserAuthentication();
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDTO user) {
        User userSave = userService.create(user);
        if (userSave == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("Invalid userID");
        }
        return user;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public void logMeOut(HttpServletRequest request) {
        String token = (String) request.getAttribute("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE");
        if (token != null) {
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
            tokenStore.readAccessToken(token);
        }
    }

}
