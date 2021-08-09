package com.codegym.controller;

import com.codegym.model.Relationship;
import com.codegym.service.relationship.IRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/relationshipss")
@CrossOrigin("*")
public class ControllerRela_LUC {

    @Autowired
    private IRelationshipService relationshipService;

    @PutMapping("/{id}")
    public ResponseEntity<Relationship> blockRelationship(@PathVariable Long id, @RequestParam("status") String status){
        Optional<Relationship> relationshipOptional = relationshipService.findById(id);
        if (!relationshipOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            relationshipOptional.get().setName(status);
            relationshipService.save(relationshipOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
