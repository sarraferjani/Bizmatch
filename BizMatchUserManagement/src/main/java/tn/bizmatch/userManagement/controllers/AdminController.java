package tn.bizmatch.userManagement.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.bizmatch.userManagement.entities.User;
import tn.bizmatch.userManagement.repositories.UserRepository;
import tn.bizmatch.userManagement.services.IUserService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor

public class AdminController {
    private final IUserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping()
    public String get(){
        return "GET:: Admin Controller";
    }

    @PostMapping()
    public String post(){
        return "POST:: Admin Controller";
    }


    @PostMapping("/userRegister")
    public User registeruser(@RequestBody User user) {
user.setActive(false);
user.setRegistrationDate(new Date());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);


    }

    @GetMapping("/users")
    public List<User> registeruser(){
        return userRepository.findAll();


    }
    @PostMapping("/resetpass/{iduser}/{pass}")
    public void resetPassword(@PathVariable int iduser, @PathVariable String pass){
        userService.resetPassword(iduser, pass);

    }
    @PostMapping("/desactivate/{iduser}")
    public void AccountDesactivation(@PathVariable int iduser){
        User user=userRepository.findById(iduser).orElse(null);
        if(user!=null){
            user.setActive(false);
            userRepository.save(user);
        }

    }

}
