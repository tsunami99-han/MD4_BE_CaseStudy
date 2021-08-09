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
    private IRelationshipRepository repository;

    @Override
    public List<Relationship> getAllFriends(String name, String userName) {
        return repository.findAllFriends(name, userName);
    }

    @Override
    public Iterable<Relationship> findAll() {
        return null;
    }

    @Override
    public Optional<Relationship> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Relationship save(Relationship relationship) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
