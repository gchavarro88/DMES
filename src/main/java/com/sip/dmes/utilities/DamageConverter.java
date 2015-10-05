/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sip.dmes.utilities;




import com.sip.dmes.entitys.ScMaintenanceDamage;
import com.sip.dmes.entitys.ScTime;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Gustavo
 */
@FacesConverter("damageConverter")
public class DamageConverter implements Converter{

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
        ScMaintenanceDamage damage = null;
        if(value.length()>0)
        {   
            String fields[] = value.split(",");
            FacesMessage msg = new FacesMessage("Error al convertir la unidad de tiempo", "Formato no válido");
            damage = new ScMaintenanceDamage(Long.parseLong(fields[0]));
            damage.setDamage(fields[1]);
        }
        return damage;
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
