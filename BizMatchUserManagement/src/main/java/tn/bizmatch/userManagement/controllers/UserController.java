package tn.bizmatch.userManagement.controllers;


import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import tn.bizmatch.userManagement.entities.User;
import tn.bizmatch.userManagement.generic.GenericController;
import tn.bizmatch.userManagement.repositories.UserRepository;
import tn.bizmatch.userManagement.services.IUserService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController extends GenericController<User,Integer> {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @PostMapping("/resetpass/{iduser}/{pass}")
    public void resetPassword(@PathVariable int iduser, @PathVariable String pass) {
        userService.resetPassword(iduser, pass);

    }

    @PostMapping("/uploadImage/{userId}")
    public void uploadImage(@PathVariable int userId, @RequestParam("file") MultipartFile file) throws IOException {

             userService.uploadImage(userId, file);

    }


    @GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName) throws IOException {
        Resource resource = userService.downloadImage(imageName);
        return ResponseEntity.ok().body(resource);



    }

    @PutMapping("/edit")
    public void  editProfile(@RequestBody User user)  {
       userRepository.save(user);



    }

    @GetMapping("/managers")
    public List<User> getMangers()  {
        return userRepository.findUserMangers();



    }

    @GetMapping("/clients")
    public List<User> getClients()  {
        return userRepository.findUserClients();



    }

    @GetMapping("/usersSearch/{lastname}")
    public List<User> getUsersSearch(@PathVariable String lastname)  {
        return userRepository.findAllSearch(lastname);



    }

}
