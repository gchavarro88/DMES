/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IOtMaintenanceCorrective;
import com.sip.dmes.entitys.OtMaintenanceCorrective;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository(value = "IOtMaintenanceCorrective")
public class OtMaintenanceCorrectiveDao implements IOtMaintenanceCorrective
{
    private final static Logger log = Logger.getLogger(OtMaintenanceCorrectiveDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    public List<OtMaintenanceCorrective> getAllCorrectives() throws Exception
    {
        Calendar currentDate = Calendar.getInstance();
        currentDate.clear(Calendar.HOUR_OF_DAY);
        currentDate.clear(Calendar.HOUR);
        currentDate.clear(Calendar.MINUTE);
        currentDate.clear(Calendar.SECOND);
        currentDate.clear(Calendar.AM_PM);
        List<OtMaintenanceCorrective> result = null;
        Query query  = entityManager.createNamedQuery("OtMaintenanceCorrective.findByToday"); 
        query.setParameter("creationDate", currentDate.getTime());
        try
        {
            result = (List<OtMaintenanceCorrective>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los mantenimientos correctivos",e);
        }
        return result;
    }

    @Override
    public List<ScMachine> getAllMachines() throws Exception
    {
        List<ScMachine> result =null;
        Query query  = entityManager.createNamedQuery("ScMachine.findAll"); 
        try
        {
            result = (List<ScMachine>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las máquinas",e);
        }
        return result;
    }

    @Override
    public List<ScMachinePart> getAllMachinePartByMachine(ScMachine machine) throws Exception
    {
        List<ScMachinePart> result =null;
        Query query  = entityManager.createNamedQuery("ScMachinePart.findByIdMachine"); 
        query.setParameter("idMachine", machine.getIdMachine());
        try
        {
            result = (List<ScMachinePart>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las partes de máquinas",e);
        }
        return result;
    }

    @Override
    public List<ScMaintenanceClasification> getAllClasifications() throws Exception
    {
        List<ScMaintenanceClasification> result =null;
        Query query  = entityManager.createNamedQuery("ScMaintenanceClasification.findAll"); 
        try
        {
            result = (List<ScMaintenanceClasification>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las clasificaciones del mantenimiento",e);
        }
        return result;
    }

    @Override
    public Object getInitialParameters() throws Exception
    {
        Object result = null;
        try
        {
            String sql = "SELECT MAX_SIZE_FILE, EXTENSION, PATH FROM dmes.sc_constants_load_files";
            Query query = entityManager.createNativeQuery(sql);
            result = query.getSingleResult();
        }
        catch(Exception e)
        {
            log.error("Error al intentar consultar los parámetros iniciales para cargar archivos",e);
        }
        return result;
    }

    @Override
    public List<ScPriority> getAllPriorities() throws Exception
    {
        List<ScPriority> result =null;
        Query query  = entityManager.createNamedQuery("ScPriority.findAll"); 
        try
        {
            result = (List<ScPriority>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las prioridades del mantenimiento",e);
        }
        return result;
    }

    @Override
    public List<ScMaintenanceDamage> getAllDamage() throws Exception
    {
        List<ScMaintenanceDamage> result =null;
        Query query  = entityManager.createNamedQuery("ScMaintenanceDamage.findAll"); 
        try
        {
            result = (List<ScMaintenanceDamage>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los daños del mantenimiento",e);
        }
        return result;
    }

    @Override
    public List<ScEmployee> getAllEmployees() throws Exception
    {
        List<ScEmployee> result =null;
        Query query  = entityManager.createNamedQuery("ScEmployee.findAll"); 
        try
        {
            result = (List<ScEmployee>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los empleados del mantenimiento",e);
        }
        return result;
    }

    @Override
    public List<ScReplacement> getAllReplacements() throws Exception
    {
        List<ScReplacement> result =null;
        Query query  = entityManager.createNamedQuery("ScReplacement.findAll"); 
        try
        {
            result = (List<ScReplacement>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los repuestos y consumibles del mantenimiento",e);
        }
        return result;
    }

    @Override
    public List<ScTool> getAllTools() throws Exception
    {
        List<ScTool> result =null;
        Query query  = entityManager.createNamedQuery("ScTool.findAll"); 
        try
        {
            result = (List<ScTool>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las herramientas del mantenimiento",e);
        }
        return result;
    }

    @Override
    @Transactional
    public void saveMaintenance(OtMaintenanceCorrective orderSave, Date endDate) throws Exception
    {   OtMaintenanceSchedule maintenanceSchedule = new OtMaintenanceSchedule();
        try
        {
            entityManager.persist(orderSave.getIdMaintenance().getIdWorkforce());
            entityManager.persist(orderSave.getIdMaintenance());
            entityManager.persist(orderSave);
            maintenanceSchedule.setIdEmployee(orderSave.getIdMaintenance().getIdWorkforce().getIdEmployee());
            maintenanceSchedule.setCreationDate(orderSave.getIdMaintenance().getCreationDate());
            maintenanceSchedule.setEndDate(endDate);
            maintenanceSchedule.setIdMaintenance(orderSave.getIdMaintenance().getIdMaintenance());
            entityManager.persist(maintenanceSchedule);
            orderSave.getIdMaintenance().setMaintenanceSchedule(maintenanceSchedule.getIdScheduleMaintenance()+"");
            entityManager.merge(orderSave.getIdMaintenance());
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de las ordenes de mantenimiento",e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteMaintenance(OtMaintenanceCorrective orderSelected) throws Exception
    {
        try 
        {
            OtMaintenanceSchedule maintenanceSchedule = null; 
            entityManager.remove(entityManager.contains(orderSelected)?orderSelected:entityManager.merge(orderSelected));
            entityManager.remove(entityManager.contains(orderSelected.getIdMaintenance())
            ?orderSelected.getIdMaintenance():entityManager.merge(orderSelected.getIdMaintenance()));            
            Query query = entityManager.createNamedQuery("OtMaintenanceSchedule.findByIdScheduleMaintenance");
            query.setParameter("idScheduleMaintenance", Long.parseLong(orderSelected.getIdMaintenance().getMaintenanceSchedule()));
            maintenanceSchedule = (OtMaintenanceSchedule) query.getSingleResult();
            entityManager.remove(entityManager.contains(maintenanceSchedule)?maintenanceSchedule:entityManager.merge(maintenanceSchedule));
        }
        catch (Exception e)
        { 
            log.error("Error al intentar hacer la eliminación de las ordenes de mantenimiento",e);
            throw e;
        }
        
        
    }

    @Override
    @Transactional
    public void updateMaintenance(OtMaintenanceCorrective orderSelected) throws Exception
    {
        String startDate = getFormatDateGlobal("yyyy-MM-dd HH:mm:ss", orderSelected.getIdMaintenance().getCreationDate());
        String finishDate = getFormatDateGlobal("yyyy-MM-dd HH:mm:ss", orderSelected.getIdMaintenance().getEndDate());
        String nativeQuery = "UPDATE dmes.ot_maintenance_schedule set creation_date = '"+startDate+
        "', end_date = '"+finishDate+"', id_employee = "+orderSelected.getIdMaintenance().getIdWorkforce().getIdEmployee().getIdEmployee()+
                " where id_schedule_maintenance = "+orderSelected.getIdMaintenance().getMaintenanceSchedule();
        try
        {
            entityManager.merge(orderSelected.getIdMaintenance().getIdWorkforce());
            entityManager.merge(orderSelected.getIdMaintenance());
            entityManager.merge(orderSelected);
            Query query = entityManager.createNativeQuery(nativeQuery);
            int rows = query.executeUpdate();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la actualización de las ordenes de mantenimiento",e);
            throw e;
        }
    }

    /**
     * Método que se encarga de recibir un patrón y una fecha de tipo Date, y
     * deberá retornar una cadena de carácteres de la fecha siguiendo el patrón
     * recibido
     * <p>
     * @param pattern patrón del formato de la fecha
     * @param date fecha a visualizar
     * <p>
     * @return valor de la fecha en el formato indicado por el patrón de tipo
     * String
     * <p>
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public String getFormatDateGlobal(String pattern, Date date)
    {
        String result = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null)
        {
            result = simpleDateFormat.format(date);
        }
        return result;
    }

    @Override
    public List<ScMaintenanceState> getAllMaintenanceStates() throws Exception
    {
        
        List<ScMaintenanceState> result =null;
        Query query  = entityManager.createNamedQuery("ScMaintenanceState.findAll"); 
        try
        {
            result = (List<ScMaintenanceState>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los estados del mantenimiento",e);
        }
        return result;
    }

    @Override
    public List<OtMaintenanceCorrective> getMaintenanceByParameters(Date initDate, Date endDate, ScMaintenanceClasification clasification, ScMaintenanceState state) throws Exception
    {
        String nameQuery = "SELECT o FROM OtMaintenanceCorrective o WHERE 1=1 ";
        List<OtMaintenanceCorrective> result = null;
        if(initDate != null)
        {
            nameQuery += " AND o.idMaintenance.creationDate >= :initDate ";
        }
        if(endDate != null)
        {
            nameQuery += " AND o.idMaintenance.creationDate <= :endDate  ";
        }
        if(clasification != null)
        {
            nameQuery += " AND o.idMaintenance.idMaintenanceClasification.idMaintenanceClasification = :clasification ";
        }
        if(state != null)
        {
            nameQuery += " AND o.idMaintenance.idMaintenanceState.idMaintenanceState = :state ";
        }
        try
        {
            Query query = entityManager.createQuery(nameQuery);
            if(initDate != null)
            {
                query.setParameter("initDate", initDate);
            }
            if(endDate != null)
            {
                query.setParameter("endDate", endDate);
            }
            if(clasification != null)
            {
                query.setParameter("clasification", clasification.getIdMaintenanceClasification());
            }
            if(state != null)
            {
                query.setParameter("state", state.getIdMaintenanceState());
            }
            result = (List<OtMaintenanceCorrective>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los estados del mantenimiento",e);
        }
        return result;
    }

    @Override
    public OtMaintenanceCorrective getMaintenanceById(Long idMaintenance)
    {
        OtMaintenanceCorrective result =null;
        Query query  = entityManager.createNamedQuery("OtMaintenanceCorrective.findByIdMaintenance"); 
        query.setParameter("idMaintenance", idMaintenance);
        try
        {
            result = (OtMaintenanceCorrective) query.getSingleResult();
        }
        catch (Exception e)
        {
            log.error("Error al consultar el mantenimiento correctivo por id del mantenimiento",e);
        }
        return result;
    }
    

}
