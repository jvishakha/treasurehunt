package com.vishakha.treasurehunt.service;

import com.vishakha.treasurehunt.model.User;

public interface RegistrationService {
        String register(User user);
        String createToken(User user);
}
