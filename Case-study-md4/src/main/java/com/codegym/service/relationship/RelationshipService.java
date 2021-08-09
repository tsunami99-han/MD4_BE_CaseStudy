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
    public List<Relationship> findRelationshipsByUserAndUserFriend(Long id1, Long id2) {
        return relationshipRepository.findRelationshipsByUserAndUserFriend(id1, id2);
    }

    @Override
    public void unFriend(Long id1, Long id2) {
        relationshipRepository.unFriend(id1, id2);
    }


    @Override
    public Iterable<Relationship> findAll() {
        return relationshipRepository.findAll();
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