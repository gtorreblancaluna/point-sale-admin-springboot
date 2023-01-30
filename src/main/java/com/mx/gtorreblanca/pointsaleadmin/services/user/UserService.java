package com.mx.gtorreblanca.pointsaleadmin.services.user;

import com.mx.gtorreblanca.pointsaleadmin.exeptions.BusinessException;
import com.mx.gtorreblanca.pointsaleadmin.models.requests.user.UserRequest;

import java.util.List;

public interface UserService {
    void registerDefaultUser(final UserRequest userRequest)throws BusinessException;
    void saveUser (final UserRequest userRequest)throws BusinessException;
    List<UserRequest> getAllUsers ()throws BusinessException;
    UserRequest getById (Long id)throws BusinessException;
}
