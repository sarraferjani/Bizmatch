package tn.bizmatch.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.bizmatch.authentication.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User>findByEmail(String email);



    @Query("select u from User  u where u.email= :email")
    User validEmail(String email);


    User findUserByEmail(String email);

    @Query("select u from User  u where u.role='Manager'")
    List<User> findUserMangers( );

    @Query("select u from User  u where u.role='Client'")
    List<User> findUserClients( );

    @Query("select u from User  u where u.lastName like %:lastName%  or u.firstName like %:lastName% ")
    List<User>findAllSearch(String lastName);
}
