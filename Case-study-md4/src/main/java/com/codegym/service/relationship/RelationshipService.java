package com.codegym.service.relationship;

import com.codegym.model.Relationship;
import com.codegym.repository.IRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelationshipService implements IRelationshipService {
    @Autowired
    private IRelationshipRepository relationshipRepository;

    @Override
    public List<Relationship> findFriendById(Long id) {
        return relationshipRepository.findFriendById(id);
    }

    @Override
    public Optional<Relationship> findByNameAndUserAndUserFriend(String name, Long id_User, Long id_UserFriend) {
        return relationshipRepository.findByNameAndUserAndUserFriend(name,id_User,id_UserFriend);
    }

    @Override
    public Iterable<Relationship> findAll() {
        return null;
    }

    @Override
    public Optional<Relationship> findById(Long id) {
        return relationshipRepository.findById(id);
    }

    @Override
    public Relationship save(Relationship relationship) {
        return relationshipRepository.save(relationship);
    }

    @Override
    public void remove(Long id) {
        relationshipRepository.deleteById(id);
    }
}