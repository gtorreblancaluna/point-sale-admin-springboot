package com.mx.gtorreblanca.pointsaleadmin.services.impl;

import com.mx.gtorreblanca.pointsaleadmin.config.CustomPasswordEncoder;
import com.mx.gtorreblanca.pointsaleadmin.constants.RoleConstant;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.BusinessException;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.NoDataFoundException;
import com.mx.gtorreblanca.pointsaleadmin.models.RoleVO;
import com.mx.gtorreblanca.pointsaleadmin.repositories.UserRepository;
import com.mx.gtorreblanca.pointsaleadmin.entities.Role;
import com.mx.gtorreblanca.pointsaleadmin.entities.User;
import com.mx.gtorreblanca.pointsaleadmin.models.UserVO;
import com.mx.gtorreblanca.pointsaleadmin.services.RoleService;
import com.mx.gtorreblanca.pointsaleadmin.services.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final RoleService roleService;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(RoleService roleService, UserRepository userRepository, @Lazy CustomPasswordEncoder customPasswordEncoder) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Override
    public void registerDefaultUser(final UserVO userVO) throws BusinessException {
        RoleVO roleVO = roleService.findByName(RoleConstant.ROLE_USER);
        User user = build(userVO);
        user.addRole(build(roleVO));
        userRepository.save(user);
    }

    @Override
    public void saveUser(final UserVO userVO) throws BusinessException {

        if (userVO.getRoles() == null || userVO.getRoles().isEmpty()) {
            registerDefaultUser(userVO);
        } else {
            User user = build(userVO);
            for (RoleVO roleVO : userVO.getRoles()) {
                user.addRole(build(roleVO));
            }
            userRepository.save(user);
        }
    }

    @Override
    public List<UserVO> getAllUsers() throws BusinessException {
        List<UserVO> users = userRepository
                .findAll()
                .stream()
                .map(this::buildVO)
                .toList();

        if (users.isEmpty()) {
            throw new NoDataFoundException();
        }

        return users;
    }

    @Override
    public UserVO getById(Long id) throws BusinessException {
        Optional<User> optionalUser =
                userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NoDataFoundException();
        }

        return buildVO(optionalUser.get());
    }

    private Role build (RoleVO roleVO) {
        return Role.builder()
                .id(roleVO.getId())
                .name(roleVO.getName())
                .build();
    }

    private RoleVO buildVO (Role role) {
        return RoleVO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
    private User build (UserVO userVO) {
        return User.builder()
                .email(userVO.getEmail())
                .phoneNumber(userVO.getPhoneNumber())
                .name(userVO.getName())
                .lastName(userVO.getLastName())
                .password(customPasswordEncoder.encode(userVO.getPassword()))
                .roles(new HashSet<>())
                .build();
    }

    private UserVO buildVO (User user) {
        UserVO userVO = UserVO.builder()
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();

        userVO.setRoles(
                user.getRoles()
                        .stream()
                        .map(this::buildVO)
                        .toList());

        return userVO;
    }
}
