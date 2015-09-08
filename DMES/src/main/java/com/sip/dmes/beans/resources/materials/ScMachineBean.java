/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.materials;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScMachine;
import com.sip.dmes.entitys.ScMachine;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

/**
 *
 * @author gchavarr88
 */
public class ScMachineBean
{

    List<ScMachine> machineList; //Lista de máquinas
    ScMachine machineSave; //Máquina a agregar
    ScMachine machineUpdate; //Máquina seleccionada
    
    
    
    private SessionBean sessionBean; //Bean de sesion
    private IScMachine scMachineServer;
    
    private final static Logger log = Logger.getLogger(ScMachineBean.class);
    
    
    @PostConstruct
    public void initDate()
    {
        fillListMachine();
    }
    
    /**
     * Método encargado 
     */
    public void fillListMachine()
    {
        try
        {
            setMachineList(getScMachineServer().getAllMachine());
        }
        catch (Exception e)
        {
            log.error("Error al intentar consultar las máquinas");
        }
    }
    
    public List<ScMachine> getMachineList()
    {
        return machineList;
    }

    public void setMachineList(List<ScMachine> machineList)
    {
        this.machineList = machineList;
    }

    public ScMachine getMachineSave()
    {
        return machineSave;
    }

    public void setMachineSave(ScMachine machineSave)
    {
        this.machineSave = machineSave;
    }

    public ScMachine getMachineUpdate()
    {
        return machineUpdate;
    }

    public void setMachineUpdate(ScMachine machineUpdate)
    {
        this.machineUpdate = machineUpdate;
    }

    public IScMachine getScMachineServer()
    {
        return scMachineServer;
    }

    public void setScMachineServer(IScMachine scMachineServer)
    {
        this.scMachineServer = scMachineServer;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }
    
    
    
    
    
    
}