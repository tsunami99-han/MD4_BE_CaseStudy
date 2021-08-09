package com.codegym.service.user;

import com.codegym.model.Relationship;
import com.codegym.model.User;
import com.codegym.model.UserPrinciple;
import com.codegym.repository.IRelationshipRepository;
import com.codegym.repository.IUserRepository;
import com.codegym.service.relationship.IRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRelationshipService relationshipService;

    @Autowired
    private IRelationshipRepository relationshipRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUserCommons(String nameRelationship, String username, String friendName) {
        List<Relationship> userList1 = relationshipService.getAllFriends(nameRelationship, username);
        List<Relationship> userList2 = relationshipService.getAllFriends(nameRelationship, friendName);

        List<User> friendUser = new ArrayList<>();
        for (Relationship re : userList1) {
            if (re.getUser().getUsername().equals(username)) {
                friendUser.add(re.getUserFriend());
            } else if (re.getUserFriend().getUsername().equals(username)) {
                friendUser.add(re.getUser());
            }
        }

        List<User> friendUsers2 = new ArrayList<>();
        for (Relationship re : userList2) {
            if (re.getUser().getUsername().equals(friendName)) {
                friendUsers2.add(re.getUserFriend());
            } else if (re.getUserFriend().getUsername().equals(friendName)) {
                friendUsers2.add(re.getUser());
            }
        }

        List<User> friendCommons = new ArrayList<>();
        for (User u1 : friendUser) {
            for (User u2 : friendUsers2) {
                if (u2.getId() == u1.getId()) {
                    friendCommons.add(u2);
                    break;
                }
            }
        }
        return friendCommons;
    }
}