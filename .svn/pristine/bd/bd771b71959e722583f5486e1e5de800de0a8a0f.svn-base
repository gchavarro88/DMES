package com.sip.dmes.utilities;
import java.io.Serializable;



/**
 * Proyecto: DMES - Versión: 1.0
 * @author (es): Gustavo Adolfo Chavarro Ortiz 
 * @Funcionalidad: Esta clase está encargada de servir como objeto para un modelo
 * de menú en un árbol, teniendo como atributos el nombre del nodo, el tipo de nodo,
 * el icono del nodo, la página a la que hace referencia el nodo y la posición de la
 * pestaña a la que hace referencia.
 * @Modificado por:  
 * @Funcionalidad agregada:
 */
public class ItemTreeIcon implements Serializable, Comparable<ItemTreeIcon>
{
    private String name;//Nombre del nodo
    //Tipo de nodo ejemplo: Home--> principal, Folder -->  directorio,
    //Item --> hace referencia a objeto para invocar una pestaña
    private String type;
    
    private String icone;//Icono del nodo
    private String page; //Página de la pestaña a la que hace referencia
    private int position; //posición de la pestaña a la que hace referencia

    /**
     * Método constructor.
     */
    public ItemTreeIcon(String name, String type, String icone)
    {
        this.name = name;
        this.type = type;
        this.icone = icone;
    }
    /**
     * Método constructor.
     */
    public ItemTreeIcon(String name, String type, String icone, String page)
    {
        this.name = name;
        this.type = type;
        this.icone = icone;
        this.page = page;
    }
    /**
     * Método constructor.
     */
    public ItemTreeIcon(String name, String type, String icone, String page, int position)
    {
        this.name = name;
        this.type = type;
        this.icone = icone;
        this.page = page;
        this.position = position;
    }
    /**
     * Métodos getters and setters.
     */
    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }
    
    

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getIcone()
    {
        return icone;
    }

    public void setIcone(String icone)
    {
        this.icone = icone;
    }

    public String getPage()
    {
        return page;
    }

    public void setPage(String page)
    {
        this.page = page;
    }

    
    
    @Override
    public String toString()
    {
        return name;
    }


    @Override
    public int compareTo(ItemTreeIcon itemTreeIcon)
    {
        return this.getName().compareTo(itemTreeIcon.getName());
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemTreeIcon other = (ItemTreeIcon) obj;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else
            if (!name.equals(other.name))
                return false;
        if (type == null)
        {
            if (other.type != null)
                return false;
        }
        else
            if (!type.equals(other.type))
                return false;
        return true;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

}
