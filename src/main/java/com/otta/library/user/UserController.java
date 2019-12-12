package com.otta.library.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.otta.library.user.model.UserInformation;
import com.otta.library.user.model.UserShow;
import com.otta.library.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json")
    public @ResponseBody ResponseEntity<UserInformation> save(@RequestBody UserInformation userInformation) {
        return ResponseEntity.ok(userService.saveUser(userInformation));
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<Collection<UserShow>> list() {
        return ResponseEntity.ok(this.userService.listAllUsers());
    }
}
