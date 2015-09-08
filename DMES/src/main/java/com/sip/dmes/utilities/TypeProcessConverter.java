/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sip.dmes.utilities;



import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScInput;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScProcessType;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Gustavo
 */
@FacesConverter("typeProcessConverter")
public class TypeProcessConverter implements Converter{

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
        ScProcessType processType = null;
        
        if(value.length()>0)
        {   
            String fields[] = value.split(",");
            FacesMessage msg = new FacesMessage("Error al convertir el tipo de proceso", "Formato no válido");
            processType = new ScProcessType(Long.parseLong(fields[0]));
            processType.setType(fields[1]);
        }
        
        return processType;
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
