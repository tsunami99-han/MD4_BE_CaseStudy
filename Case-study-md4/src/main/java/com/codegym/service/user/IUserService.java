package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    Optional<User> findByUsername(String username);

    List<User> getUserCommons(String nameRelationship, String username, String userFriend);

}
