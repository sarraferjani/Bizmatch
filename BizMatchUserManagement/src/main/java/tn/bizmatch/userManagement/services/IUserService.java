package tn.bizmatch.userManagement.services;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.bizmatch.userManagement.entities.User;
import tn.bizmatch.userManagement.generic.IGenericService;

import java.io.IOException;
import java.util.List;

public interface IUserService  extends IGenericService<User,Integer> {

    void resetPassword(int iduser,String password);
    String uploadImage(int userId, MultipartFile file) throws IOException;

     Resource downloadImage(String imageName) throws IOException;

     List<User> getMangers();
    List<User> getClients();


    }
