/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.utilities;

import java.io.Serializable;

/**
 * Proyecto: DMES - Versión: 1.0
 * @author (es): Gustavo Adolfo Chavarro Ortiz 
 * @Funcionalidad: Esta clase está encargada de servir como objeto para un modelo
 * de pestañas en un panel, teniendo como atributos el nombre de la pestaña, la 
 * url de la página a la que hace referencia y un atributo booleano que inidica
 * si s epuede visualizar.
 * @Modificado por:  
 * @Funcionalidad agregada:
 */
public class MainTabs implements Serializable
{
    private String title;
    private int index;
    private String page;
    private boolean visible;
    
    /**
    * Método constructor.
    */
    public MainTabs(String title, int index, String page, boolean visible)
    {
        this.title = title;
        this.index = index;
        this.page = page;
        this.visible = visible;
    }
    /**
    * Métodos getters and setters.
    */
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getPage()
    {
        return page;
    }

    public void setPage(String page)
    {
        this.page = page;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
    
    
    
    
}
