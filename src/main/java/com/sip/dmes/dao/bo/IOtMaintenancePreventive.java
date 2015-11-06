/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bo;


import com.sip.dmes.entitys.OtMaintenancePreventive;
import com.sip.dmes.entitys.OtMaintenanceSchedule;
import com.sip.dmes.entitys.ScEmployee;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMachinePart;
import com.sip.dmes.entitys.ScMaintenanceClasification;
import com.sip.dmes.entitys.ScMaintenanceDamage;
import com.sip.dmes.entitys.ScMaintenanceState;
import com.sip.dmes.entitys.ScPriority;
import com.sip.dmes.entitys.ScReplacement;
import com.sip.dmes.entitys.ScTool;
import java.util.Date;
import java.util.List;

public interface IOtMaintenancePreventive 
{
    
    public List<OtMaintenancePreventive> getAllPreventives() throws Exception;
    
    public List<ScMachine> getAllMachines() throws Exception;
    
    public List<ScMachinePart> getAllMachinePartByMachine(ScMachine machine) throws Exception;
    
    public List<ScMaintenanceClasification> getAllClasifications() throws Exception;
    
    public List<ScPriority> getAllPriorities() throws Exception;
    
    public List<ScMaintenanceDamage> getAllDamage() throws Exception;
    
    public List<ScEmployee> getAllEmployees() throws Exception;
  
    public void deleteMaintenance(OtMaintenancePreventive orderSelected) throws Exception;

    public void updateMaintenance(OtMaintenancePreventive orderSelected, List<Date> scheduleMaintenance,
            int months, int days, int hours, int minutes) throws Exception;
    
    public List<ScReplacement> getAllReplacements() throws Exception;
    
    public List<ScTool> getAllTools() throws Exception;
    
    public List<ScMaintenanceState> getAllMaintenanceStates() throws Exception;
    
    public Object getInitialParameters() throws Exception;
    
    public List<OtMaintenancePreventive> getMaintenanceByParameters(Date initDate, 
            Date endDate, ScMaintenanceClasification clasification, ScMaintenanceState state) throws Exception;
    
    public void saveMaintenance(OtMaintenancePreventive orderSave, Date endDate, List<Date> scheduleMantenaince,
            int months, int days, int hours, int minutes) throws Exception;
    
    public List<OtMaintenanceSchedule> getMaintenancesByMonth(Date startDate, Date endDate)throws Exception;
    
    public OtMaintenancePreventive getMaintenanceById(Long idMaintenance);
    
}
