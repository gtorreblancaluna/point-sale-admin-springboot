package com.mx.gtorreblanca.pointsaleadmin.services.user;

import com.mx.gtorreblanca.pointsaleadmin.exeptions.NoDataFoundException;
import com.mx.gtorreblanca.pointsaleadmin.models.requests.user.RoleRequest;

public interface RoleService {

    RoleRequest findByName (String name)throws NoDataFoundException;
}
