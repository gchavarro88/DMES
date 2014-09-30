/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScModulePermission;
import com.sip.dmes.entitys.ScRoles;
import com.sip.dmes.entitys.ScSubmodulePermission;
import com.sip.dmes.entitys.ScSubmodulePermissionByRole;
import java.util.List;

/**
 *
 * @author gchavarro88
 */
public interface IScSubModulePermissionByRole
{
    public List<ScSubmodulePermissionByRole> getAllIScModulePermissionsByRole
        (ScRoles scRole, ScModulePermission scModulePermission) throws Exception;
}
