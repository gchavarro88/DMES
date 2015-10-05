/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sip.dmes.utilities;



import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScMaintenanceClasification;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Gustavo
 */
@FacesConverter("maintenanceClasificationConverter")
public class MaintenanceClasificationConverter implements Converter{

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        ScMaintenanceClasification clasification = null;
        if(value.length()>0)
        {   
            String fields[] = value.split(",");
            FacesMessage msg = new FacesMessage("Error al convertir la máquina", "Formato no válido");
            clasification = new ScMaintenanceClasification(Long.parseLong(fields[0]));
            clasification.setClasification(fields[1]);
        }
        
        return clasification;
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null)
        {
            return "";
        }
        return value.toString();
    }
    
}
