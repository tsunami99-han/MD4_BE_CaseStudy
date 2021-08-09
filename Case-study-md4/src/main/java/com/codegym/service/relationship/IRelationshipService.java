package com.codegym.service.relationship;

import com.codegym.model.Relationship;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IRelationshipService extends IGeneralService<Relationship> {

    List<Relationship> getAllFriends(String name, String userName);
}
