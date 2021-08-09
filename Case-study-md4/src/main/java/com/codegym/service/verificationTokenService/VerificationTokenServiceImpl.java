package com.codegym.service.verificationTokenService;

import com.codegym.model.VerificationToken;
import com.codegym.repository.IVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements IVerificationTokenService {

    @Autowired
    private IVerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken findByToken(String token) {
        return findByToken(token);
    }

    @Override
    public void save(VerificationToken token) {
        verificationTokenRepository.save(token);
    }
}