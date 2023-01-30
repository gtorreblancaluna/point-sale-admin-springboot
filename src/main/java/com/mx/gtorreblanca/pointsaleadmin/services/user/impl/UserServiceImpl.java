package com.mx.gtorreblanca.pointsaleadmin.services.user.impl;

import com.mx.gtorreblanca.pointsaleadmin.constants.RoleConstant;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.BusinessException;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.DataOriginException;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.NoDataFoundException;
import com.mx.gtorreblanca.pointsaleadmin.models.requests.user.RoleRequest;
import com.mx.gtorreblanca.pointsaleadmin.models.responses.user.RoleResponse;
import com.mx.gtorreblanca.pointsaleadmin.models.responses.user.UserResponse;
import com.mx.gtorreblanca.pointsaleadmin.repositories.user.UserRepository;
import com.mx.gtorreblanca.pointsaleadmin.entities.user.Role;
import com.mx.gtorreblanca.pointsaleadmin.entities.user.User;
import com.mx.gtorreblanca.pointsaleadmin.models.requests.user.UserRequest;
import com.mx.gtorreblanca.pointsaleadmin.services.user.RoleService;
import com.mx.gtorreblanca.pointsaleadmin.services.user.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j
public class UserServiceImpl implements UserService {

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(RoleService roleService, UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerDefaultUser(final UserRequest userRequest) throws BusinessException {
        RoleRequest roleRequest = roleService.findByName(RoleConstant.ROLE_USER);
        User user = build(userRequest);
        user.addRole(build(roleRequest));
        log.info("User to save: "+ user);
        save(user);
    }

    @Override
    public void saveUser(final UserRequest userRequest) throws BusinessException {

        if (userRequest.getRoles() == null || userRequest.getRoles().isEmpty()) {
            registerDefaultUser(userRequest);
        } else {
            User user = build(userRequest);
            for (RoleRequest roleRequest : userRequest.getRoles()) {
                user.addRole(build(roleRequest));
            }
            save(user);
        }
    }

    @Override
    public List<UserResponse> getAllUsers() throws BusinessException {
        List<UserResponse> users = userRepository
                .findByEnabledTrue()
                .stream()
                .map(this::buildResponse)
                .toList();

        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }

        return users;
    }

    @Override
    public UserResponse getById(Long id) throws BusinessException {
        Optional<User> optionalUser =
                userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NoDataFoundException();
        }

        return buildResponse(optionalUser.get());
    }

    private Role build (RoleRequest roleRequest) {
        return Role.builder()
                .id(roleRequest.getId())
                .name(roleRequest.getName())
                .build();
    }

    private RoleResponse buildRoleResponse (Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
    private User build (UserRequest userRequest) {
        return User.builder()
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .name(userRequest.getName())
                .lastName(userRequest.getLastName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .username(userRequest.getUsername())
                .roles(new HashSet<>())
                .build();
    }

    private UserResponse buildResponse (User user) {

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(this::buildRoleResponse)
                                .collect(Collectors.toSet()))
                .build();
    }

    private void save (User user) throws BusinessException {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new DataOriginException(e.getMessage(),e);
        }
    }
}
