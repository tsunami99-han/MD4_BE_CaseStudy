package com.codegym.service.verificationToken;

import com.codegym.model.VerificationToken;

public interface IVerificationTokenService {
    VerificationToken findByToken(String token);

    void save(VerificationToken token);
}