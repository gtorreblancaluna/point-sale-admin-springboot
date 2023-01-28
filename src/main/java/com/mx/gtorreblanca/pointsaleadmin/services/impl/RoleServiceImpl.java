package com.mx.gtorreblanca.pointsaleadmin.services.impl;

import com.mx.gtorreblanca.pointsaleadmin.entities.Role;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.NoDataFoundException;
import com.mx.gtorreblanca.pointsaleadmin.models.requests.RoleRequest;
import com.mx.gtorreblanca.pointsaleadmin.repositories.RoleRepository;
import com.mx.gtorreblanca.pointsaleadmin.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleRequest findByName(String name) throws NoDataFoundException {
        Optional<Role> optRole = roleRepository.findByName(name);
        if (!optRole.isPresent()){
            throw new NoDataFoundException();
        }
        return RoleRequest.builder()
                .id(optRole.get().getId())
                .name(optRole.get().getName())
                .build();
    }
}
