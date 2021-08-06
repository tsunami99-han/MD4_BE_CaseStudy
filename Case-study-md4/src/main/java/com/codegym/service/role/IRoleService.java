package com.codegym.service.role;

import com.codegym.model.Role;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(String name);
}
