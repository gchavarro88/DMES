/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.utilities;

import java.io.Serializable;

/**
 *
 * @author gchavarro88
 */
public class MainTabs implements Serializable
{
    private String title;
    private int index;
    private String page;
    private boolean visible;

    public MainTabs(String title, int index, String page, boolean visible)
    {
        this.title = title;
        this.index = index;
        this.page = page;
        this.visible = visible;
    }

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
