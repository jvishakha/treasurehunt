package com.vishakha.treasurehunt.service;

import com.vishakha.treasurehunt.model.User;
import com.vishakha.treasurehunt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.Types;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${query.retrieveUser}")
    private String retrieveUser;

    @Value("${query.addUser}")
    private String addUser;

    @Value("${query.saveToken}")
    private String saveToken;

    @Value("${query.fetchToken}")
    private String fetchToken;

    @Override
    public String register(User user) {
        if(isUserRegistered(user) > 0)
            return "Email ID already taken!!";
        else {
            jdbcTemplate.update(addUser, new Object[]{user.getEmail()});
            return "Registration successful!!";
        }
    }
    @Override
    public String createToken(User user){
        if(isUserRegistered(user) ==0)
            return "User not registered";
        else {
            List<String> existingToken = jdbcTemplate.query(fetchToken, new Object[]{user.getEmail()}, (rs, rownum) -> rs.getString("token"));
            if (existingToken.get(0) != null) {
                return existingToken.get(0);
            } else {
                String newtoken = jwtTokenUtil.generateToken(user);
                jdbcTemplate.update(saveToken, new Object[]{newtoken, user.getEmail()});
                return newtoken;
            }
        }
    }
    private int isUserRegistered(User user){
        int count = jdbcTemplate.queryForObject(retrieveUser, new Object[]{user.getEmail()}, Integer.class);
        return count;
    }
}
