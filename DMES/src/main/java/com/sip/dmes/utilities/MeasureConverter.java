/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sip.dmes.utilities;



import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScMeasureUnit;
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
@FacesConverter("measureConverter")
public class MeasureConverter implements Converter{

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
        ScMeasureUnit measureUnit = null;
        if(value.length()>0)
        {   
            FacesMessage msg = new FacesMessage("Error al convertir el centro de costo", "Formato no válido");
            String campos[] = value.split(",");
            if(campos.length > 1)
            {
                measureUnit = new ScMeasureUnit(Long.parseLong(campos[0]));
                measureUnit.setAcronym(campos[1]);
            }
        }
        
        return measureUnit;
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
