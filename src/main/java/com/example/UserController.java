package com.example;

import com.example.models.User;
import com.example.models.UserRepository;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mike on 3/13/17.
 */

@Controller
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/")
    public
    @ResponseBody
    String addNewUser(@RequestParam String email, @Nullable @RequestParam String passwordHash) {
        User user = new User();
        user.setEmail(email);
        if (passwordHash != null)
            user.setPasswordHash(passwordHash);
        userRepository.save(user);
        return user.toString();
    }

    @GetMapping(path = "/")
    public
    @ResponseBody
    String getUserByEmail(@RequestParam String email) {
        final User[] tmp = new User[1];
        userRepository.findAll().forEach((User user) -> {
            if (user.getEmail() == email)
                tmp[0] = user;
        });
        if (tmp[0] != null) {
            return tmp[0].toString();
        } else {
            return "No such user.";
        }
    }
}
