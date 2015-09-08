/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sip.dmes.utilities;



import com.sip.dmes.entitys.ScCostCenter;
import com.sip.dmes.entitys.ScMachine;
import com.sip.dmes.entitys.ScStoreOrderState;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Gustavo
 */
@FacesConverter("storeOrderStateConverter")
public class StoreOrderStatusConverter implements Converter{

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
        ScStoreOrderState storeOrderState = null;
        if(value.length()>0)
        {   
            FacesMessage msg = new FacesMessage("Error al convertir la estado de la orden de estado", "Formato no válido");
            storeOrderState = new ScStoreOrderState(Long.parseLong(value));   
        }
        return storeOrderState;
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value == null)
        {
            return "";
        }
        return value.toString();
    }
    
}
