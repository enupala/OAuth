package com.auth.Repositories;

import com.auth.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValueAndIsDeletedEquals(String value,boolean isDeleted);
    //Optional<Token> findByValue(String value);

    /* Optional<Token>findByValueAndIsDeletedEqualsAndExpiryDateGreaterThan(String value,
                                                          boolean isDeleted,Date expiryDate);*/

    Optional<Token> findByValueAndIsDeletedEqualsAndExpiryDateGreaterThan(String token, boolean isDeleted, Date date);
}
