package com.mx.gtorreblanca.pointsaleadmin.services.user.impl;

import com.mx.gtorreblanca.pointsaleadmin.constants.RoleConstant;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.BusinessException;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.NoDataFoundException;
import com.mx.gtorreblanca.pointsaleadmin.models.requests.user.RoleRequest;
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
        userRepository.save(user);
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
            userRepository.save(user);
        }
    }

    @Override
    public List<UserRequest> getAllUsers() throws BusinessException {
        List<UserRequest> users = userRepository
                .findByEnabledTrue()
                .stream()
                .map(this::buildVO)
                .toList();

        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }

        return users;
    }

    @Override
    public UserRequest getById(Long id) throws BusinessException {
        Optional<User> optionalUser =
                userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NoDataFoundException();
        }

        return buildVO(optionalUser.get());
    }

    private Role build (RoleRequest roleRequest) {
        return Role.builder()
                .id(roleRequest.getId())
                .name(roleRequest.getName())
                .build();
    }

    private RoleRequest buildVO (Role role) {
        return RoleRequest.builder()
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

    private UserRequest buildVO (User user) {
        UserRequest userRequest = UserRequest.builder()
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();

        userRequest.setRoles(
                user.getRoles()
                        .stream()
                        .map(this::buildVO)
                        .toList());

        return userRequest;
    }
}
