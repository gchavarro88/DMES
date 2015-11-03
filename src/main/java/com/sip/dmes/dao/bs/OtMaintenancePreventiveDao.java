/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.dao.bs;

import com.sip.dmes.dao.bo.IOtMaintenancePreventive;
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


@Repository(value = "IOtMaintenancePreventive")
public class OtMaintenancePreventiveDao implements IOtMaintenancePreventive
{
    private final static Logger log = Logger.getLogger(OtMaintenancePreventiveDao.class);
    @PersistenceContext()
    EntityManager entityManager;

    @Override
    public List<OtMaintenancePreventive> getAllPreventives() throws Exception
    {
        Calendar currentDate = Calendar.getInstance();
        currentDate.clear(Calendar.HOUR_OF_DAY);
        currentDate.clear(Calendar.HOUR);
        currentDate.clear(Calendar.MINUTE);
        currentDate.clear(Calendar.SECOND);
        currentDate.clear(Calendar.AM_PM);
        List<OtMaintenancePreventive> result = null;
        Query query  = entityManager.createNamedQuery("OtMaintenancePreventive.findByToday"); 
        query.setParameter("creationDate", currentDate.getTime());
        try
        {
            result = (List<OtMaintenancePreventive>) query.getResultList();
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
    public void saveMaintenance(OtMaintenancePreventive orderSave, Date endDate, List<Date> scheduleMaintenance,
            int months, int days, int hours, int minutes) throws Exception
    {   OtMaintenanceSchedule maintenanceSchedule; 
        try
        {
            entityManager.persist(orderSave.getIdMaintenance().getIdWorkforce());
            entityManager.persist(orderSave.getIdMaintenance());
            entityManager.persist(orderSave);
            orderSave.getIdMaintenance().setMaintenanceSchedule("");
            for(Date date: scheduleMaintenance)
            {
                maintenanceSchedule = new OtMaintenanceSchedule();
                maintenanceSchedule.setIdEmployee(orderSave.getIdMaintenance().getIdWorkforce().getIdEmployee());
                maintenanceSchedule.setCreationDate(date);
                maintenanceSchedule.setEndDate(addTime(date, months, days, hours, minutes));
                maintenanceSchedule.setIdMaintenance(orderSave.getIdMaintenance().getIdMaintenance());
                entityManager.persist(maintenanceSchedule);
                orderSave.getIdMaintenance().setMaintenanceSchedule(orderSave.getIdMaintenance().getMaintenanceSchedule()
                        +","+maintenanceSchedule.getIdScheduleMaintenance());
            }
            orderSave.getIdMaintenance().setMaintenanceSchedule(orderSave.getIdMaintenance().getMaintenanceSchedule()
                    .substring(1, orderSave.getIdMaintenance().getMaintenanceSchedule().length()));
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
    public void deleteMaintenance(OtMaintenancePreventive orderSelected) throws Exception
    {
        try 
        {
            OtMaintenanceSchedule maintenanceSchedule = null; 
            entityManager.remove(entityManager.contains(orderSelected)?orderSelected:entityManager.merge(orderSelected));
            entityManager.remove(entityManager.contains(orderSelected.getIdMaintenance())
            ?orderSelected.getIdMaintenance():entityManager.merge(orderSelected.getIdMaintenance()));            
            Query query = entityManager.createNamedQuery("OtMaintenanceSchedule.findByIdScheduleMaintenance");
            String idsSechedule[] = orderSelected.getIdMaintenance().getMaintenanceSchedule().split(",");
            for(int i=0; i < idsSechedule.length; i++)
            {
                query.setParameter("idScheduleMaintenance", Long.parseLong(idsSechedule[i]));
                maintenanceSchedule = (OtMaintenanceSchedule) query.getSingleResult();
                entityManager.remove(entityManager.contains(maintenanceSchedule)?maintenanceSchedule:entityManager.merge(maintenanceSchedule));
            }
            
            
        }
        catch (Exception e)
        { 
            log.error("Error al intentar hacer la eliminación de las ordenes de mantenimiento",e);
            throw e;
        }
        
        
    }

    @Override
    @Transactional
    public void updateMaintenance(OtMaintenancePreventive orderSelected, List<Date> scheduleMaintenance,
            int months, int days, int hours, int minutes) throws Exception
    {
        OtMaintenanceSchedule maintenanceSchedule = null; 
        
        try
        {
            Query query = entityManager.createNamedQuery("OtMaintenanceSchedule.findByIdScheduleMaintenance");
            String idsSechedule[] = orderSelected.getIdMaintenance().getMaintenanceSchedule().split(",");
            for(int i=0; i < idsSechedule.length; i++)
            {
                query.setParameter("idScheduleMaintenance", Long.parseLong(idsSechedule[i]));
                maintenanceSchedule = (OtMaintenanceSchedule) query.getSingleResult();
                entityManager.remove(entityManager.contains(maintenanceSchedule)?maintenanceSchedule:entityManager.merge(maintenanceSchedule));
            }
            
            orderSelected.getIdMaintenance().setMaintenanceSchedule("");
            for(Date date: scheduleMaintenance)
            {
                maintenanceSchedule = new OtMaintenanceSchedule();
                maintenanceSchedule.setIdEmployee(orderSelected.getIdMaintenance().getIdWorkforce().getIdEmployee());
                maintenanceSchedule.setCreationDate(date);
                maintenanceSchedule.setEndDate(addTime(date, months, days, hours, minutes));
                maintenanceSchedule.setIdMaintenance(orderSelected.getIdMaintenance().getIdMaintenance());
                entityManager.persist(maintenanceSchedule);
                orderSelected.getIdMaintenance().setMaintenanceSchedule(orderSelected.getIdMaintenance().getMaintenanceSchedule()
                        +","+maintenanceSchedule.getIdScheduleMaintenance());
            }
            orderSelected.getIdMaintenance().setMaintenanceSchedule(orderSelected.getIdMaintenance().getMaintenanceSchedule()
                    .substring(1, orderSelected.getIdMaintenance().getMaintenanceSchedule().length()));
            entityManager.merge(orderSelected.getIdMaintenance().getIdWorkforce());
            entityManager.merge(orderSelected.getIdMaintenance());
            entityManager.merge(orderSelected);
            
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
    public List<OtMaintenancePreventive> getMaintenanceByParameters(Date initDate, Date endDate, ScMaintenanceClasification clasification, ScMaintenanceState state) throws Exception
    {
        String nameQuery = "SELECT o FROM OtMaintenancePreventive o WHERE 1=1 ";
        List<OtMaintenancePreventive> result = null;
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
            result = (List<OtMaintenancePreventive>) query.getResultList();
        }
        catch (Exception e)
        {
            log.error("Error al intentar hacer la persistencia de los estados del mantenimiento",e);
        }
        return result;
    }

    

    
    /**
     * Método encargado de agregar tiempo a la duracción del mantenimiento para 
     * obtener la fecha final.
     * @param creationDate fecha de inicio del mantenimiento
     * @param months meses de duracción
     * @param days dias de duracción
     * @param hours horas de duracción
     * @param minutes minutos de duracción
     * @return Date fecha de finalizacion a retornar
     * @author Gustavo Chavarro Ortiz
     */
    public Date addTime(Date creationDate, int months, int days, int hours, int minutes)
    {
        Date endDate = new Date();
        if(creationDate != null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(creationDate);
            calendar.add(Calendar.MONTH, months);
            calendar.add(Calendar.DAY_OF_YEAR, days);
            calendar.add(Calendar.HOUR, hours);
            calendar.add(Calendar.MINUTE, minutes);
            endDate = calendar.getTime();
        }
        return endDate; //Se acomoda la fecha final
    }
}
