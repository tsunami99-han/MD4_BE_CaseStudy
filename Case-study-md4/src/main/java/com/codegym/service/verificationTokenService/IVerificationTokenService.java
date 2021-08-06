package com.codegym.service.verificationTokenService;

import com.codegym.model.VerificationToken;

public interface IVerificationTokenService {
    VerificationToken findByToken(String token);

    void save(VerificationToken token);
}
