package com.mx.gtorreblanca.pointsaleadmin.services;

import com.mx.gtorreblanca.pointsaleadmin.exeptions.NoDataFoundException;
import com.mx.gtorreblanca.pointsaleadmin.models.RoleVO;

public interface RoleService {

    RoleVO findByName (String name)throws NoDataFoundException;
}
