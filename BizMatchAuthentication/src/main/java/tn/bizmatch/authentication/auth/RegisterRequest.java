package tn.bizmatch.authentication.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.bizmatch.authentication.entities.Gender;
import tn.bizmatch.authentication.entities.RoleType;


import javax.management.relation.Role;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String jobTitle;
    private String companyRole; // Par exemple, CEO, CTO, etc.
    private String bio; // Description de l'utilisateur
    private String profilePictureUrl; // URL de la photo de profil
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private boolean isActive; // Statut actif/inactif
    private Date registrationDate; // Date d'inscription

    private Gender gender;


    private RoleType role;
}
