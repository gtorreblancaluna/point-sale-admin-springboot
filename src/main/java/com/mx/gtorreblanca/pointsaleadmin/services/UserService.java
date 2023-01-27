package com.mx.gtorreblanca.pointsaleadmin.services;

import com.mx.gtorreblanca.pointsaleadmin.exeptions.BusinessException;
import com.mx.gtorreblanca.pointsaleadmin.exeptions.DataOriginException;
import com.mx.gtorreblanca.pointsaleadmin.models.UserVO;

import java.util.List;

public interface UserService {
    void registerDefaultUser(final UserVO userVO)throws BusinessException;
    void saveUser (final UserVO userVO)throws BusinessException;
    List<UserVO> getAllUsers ()throws BusinessException;
    UserVO getById (Long id)throws BusinessException;
}
