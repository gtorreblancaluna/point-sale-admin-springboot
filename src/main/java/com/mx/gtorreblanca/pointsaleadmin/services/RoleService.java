package com.mx.gtorreblanca.pointsaleadmin.services;

import com.mx.gtorreblanca.pointsaleadmin.exeptions.NoDataFoundException;
import com.mx.gtorreblanca.pointsaleadmin.models.requests.RoleRequest;

public interface RoleService {

    RoleRequest findByName (String name)throws NoDataFoundException;
}
