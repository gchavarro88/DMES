/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sip.dmes.utilities;



import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScInputLocation;
import com.sip.dmes.entitys.ScPackingUnit;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Gustavo
 */
@FacesConverter("inputLocationConverter")
public class InputLocationConverter implements Converter{

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
        ScInputLocation inputLocation = null;
        if(value.length()>0)
        {   
            FacesMessage msg = new FacesMessage("Error al convertir la unidad de empaque", "Formato no válido");
            inputLocation = new ScInputLocation(Long.parseLong(value));
        }
        
        return inputLocation;
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
