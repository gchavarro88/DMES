package com.sip.dmes.utilities;
import java.io.Serializable;



/**
 *
 * @author Gustavo
 */
public class ItemTreeIcon implements Serializable, Comparable<ItemTreeIcon>
{
    private String name;
    private String type;
    private int amountProducts;
    private long idCategory;


    public ItemTreeIcon(String name, String type, int amountProducts, long idCategory)
    {
        this.name = name;
        this.type = type;
        this.amountProducts = amountProducts;
        this.idCategory = idCategory;
    }
    
    public ItemTreeIcon(String name, String type)
    {
        this.name = name;
        this.type = type;     
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

    public int getAmountProducts()
    {
        return amountProducts;
    }
    
    public void setAmountProducts(int amountProducts)
    {
        this.amountProducts = amountProducts;
    }

    public long getIdCategory()
    {
        return idCategory;
    }

    public void setIdCategory(long idCategory)
    {
        this.idCategory = idCategory;
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
