package com.university.itis.repository;


import com.university.itis.model.Token;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 24.05.17.
 */
@Repository
@Lazy
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByEmailAndEndDateIsNull(String username);

    Token findByEmailAndTokenAndEndDateIsNull(String username, String token);

}
