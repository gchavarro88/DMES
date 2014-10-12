/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;

import com.sip.dmes.entitys.ScModulePermission;
import java.util.List;

/**
 *
 * @author gchavarro88
 */
public interface IScModulePermission
{
    public List<ScModulePermission> findAllModulesPermission(String type) throws Exception;
    
}
