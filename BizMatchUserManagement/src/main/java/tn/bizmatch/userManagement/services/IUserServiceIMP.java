package tn.bizmatch.userManagement.services;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tn.bizmatch.userManagement.entities.User;
import tn.bizmatch.userManagement.generic.IGenericServiceImp;
import tn.bizmatch.userManagement.repositories.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IUserServiceIMP extends IGenericServiceImp<User,Integer> implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public void resetPassword(int idUser, String password) {
        User user = userRepository.findById(idUser).orElse(null);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

    }

    private static final String UPLOAD_DIR = "C:\\Users\\Fatma\\BizMatch\\src\\assets\\img\\profiles";
    public String uploadImage(int userId, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        user.setProfilePictureUrl(userId+fileName); // Stockez le chemin de l'image dans le champ "image" de l'utilisateur
        userRepository.save(user);

        // Enregistrez le fichier dans le répertoire spécifié (UPLOAD_DIR)
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(userId+fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        return "/api/users/" + userId + "/image";

}
@Override
    public Resource downloadImage(String imageName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR ).resolve(imageName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        return resource;
    }

    @Override
    public List<User> getMangers() {
        return userRepository.findUserMangers();
    }

    @Override
    public List<User> getClients() {
        return userRepository.findUserClients();
    }






}