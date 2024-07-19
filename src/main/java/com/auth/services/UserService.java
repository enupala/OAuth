package com.auth.services;

import com.auth.Repositories.UserRepository;
import com.auth.Repositories.TokenRepository;
import com.auth.models.Token;
import com.auth.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User signUp(String name, String email, String password)
    {
        //skipping email verification part here
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent())
        {
            //user is already exisits
        }
        User user=new User();

        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
       return userRepository.save(user);

    }

    public Token login(String mail, String password) {
        Optional<User> optionalUser=userRepository.findByEmail(mail);
        if(optionalUser.isEmpty())
        {
            //in-valid user
        }
        User user=optionalUser.get();
      if(!bCryptPasswordEncoder.matches(password,user.getHashedPassword())){
          // throw password is invalid
        }
       Token token=new Token();
      token.setUser(user);
      token.setExpiryDate(get30DaysLaterDate());
      token.setValue(UUID.randomUUID().toString());
      //token.setIsDeleted(false);
      return tokenRepository.save(token);
        }

    private Date get30DaysLaterDate() {
        Date date=new Date();
        //covert it into calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,30);

        return calendar.getTime();
    }

    public void logout(String token) {
      Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeletedEquals(token, false);
       // Optional<Token> optionalToken = tokenRepository.findByValue(token);

        if(optionalToken.isEmpty()) {
           // then doNthg
           return ;
       }

           Token validToken=optionalToken.get();
            validToken.setDeleted(true);
            tokenRepository.save(validToken);

    }

    public boolean validateToken(String token) {
        //if token is not deleted
        //if token is not expired
        //if token is present
        Optional<Token> tokenOptional = tokenRepository.findByValueAndIsDeletedEqualsAndExpiryDateGreaterThan(
                token, false, new Date());
        return tokenOptional.isPresent();
    }
}
