package tn.bizmatch.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.bizmatch.authentication.entities.Token;

import java.util.List;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<Token,Integer> {


    @Query("select t from Token  t where t.user.id= :userId and (t.expired = false or t.revoked = false )")
    List<Token> findAllValidTokenByUser(int userId);

    Optional<Token> findByToken(String token);
}
