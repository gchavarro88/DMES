/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.template;



import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.utilities.ItemTreeIcon;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author gchavarro88
 */
public class TemplateBean implements Serializable
{

    private final static Logger log = Logger.getLogger(TemplateBean.class);
    private TreeNode root; //Nodo base del árbol del menú
    private TabView tabView;
    IScModulePermissionByRole scModulePermissionByRoleServer;
    SessionBean sessionBean;
    List<Tab> mainTabs;
    HashMap<String, Integer> mapTabs;
            
    

    /** 
     * Creates a new instance of TemplateBean
     */
    
    public TemplateBean()
    { 
        
    }
    
    @PostConstruct
    public void initData()
    {

        initTreeCagotegories(); 
        initDataTab();  
        
    }
 
    public void initDataTab()
    {
        tabView = new TabView();
        setMapTabs(new HashMap<String, Integer>()) ;
    }
    
    public String listDateHeader()
    {
        log.info("Obteniendo la fecha del encabezado");
        String result = "";
        String dayOfWeek = "";
        String dayOfMonth = "";
        String monthOfYear = "";
        String year = "";
        String patron = "EEEE,dd,MMMM,yyyy";
        Date currentDay = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patron);
        result = simpleDateFormat.format(currentDay);
        String fields[] = result.split(",");
        dayOfWeek = ((fields[0].charAt(0) + "").toUpperCase()) + fields[0].substring(1, fields[0].length());
        dayOfMonth = fields[1];
        monthOfYear = ((fields[2].charAt(0) + "").toUpperCase()) + fields[2].substring(1, fields[2].length());
        year = fields[3];
        result = dayOfWeek + ", " + dayOfMonth + " de " + monthOfYear + " " + year;
        log.info("fecha " + result);
        return result;
    }

    /**
     * Método encargado de llenar las categorias fijas del árbol de la pantalla
     * inicial de la aplicación 
     * <p>
     * @return Nodo raiz del árbol de categorías con todos sus hijos
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public TreeNode initTreeCagotegories()
    { 
        log.info("Inicia la construcción del menú lateral principal");
        root = new DefaultTreeNode(new ItemTreeIcon("root", null,"Folder"), null);
        List<ScModulePermissionByRole> listModules = null;
        HashMap<Long, DefaultTreeNode> mapScModulePermissions = new HashMap<Long, DefaultTreeNode>(); 
        try
        {  
            listModules = getScModulePermissionByRoleServer().getAllIScModulePermissionsByRole(getSessionBean()
                    .getScUser().getIdRole());    
            DefaultTreeNode nodeIteractive = null;
            if(listModules != null && !listModules.isEmpty())
            {
                for(ScModulePermissionByRole scModulePermissionSelected: listModules)
                {
                    if(scModulePermissionSelected.getIdModulePermission().getFather() < 1)
                    {
                        nodeIteractive = new DefaultTreeNode(new ItemTreeIcon(scModulePermissionSelected.
                        getIdModulePermission().getName(),scModulePermissionSelected.getIdModulePermission().
                        getType(),scModulePermissionSelected.getIdModulePermission().getIcone(), scModulePermissionSelected.
                        getIdModulePermission().getPage()), root);
                        nodeIteractive.setType(scModulePermissionSelected.getIdModulePermission().getType());
                        nodeIteractive.setExpanded(true);
                    }
                    else
                    {
                        DefaultTreeNode nodeFather = mapScModulePermissions.get(scModulePermissionSelected.
                        getIdModulePermission().getFather());
                        
                        nodeIteractive = new DefaultTreeNode(new ItemTreeIcon(scModulePermissionSelected.
                        getIdModulePermission().getName(),scModulePermissionSelected.getIdModulePermission().
                        getType(),scModulePermissionSelected.getIdModulePermission().getIcone(), scModulePermissionSelected.
                        getIdModulePermission().getPage()), nodeFather);
                        
                        nodeIteractive.setType(scModulePermissionSelected.getIdModulePermission().getType());
                    }
                    mapScModulePermissions.put(scModulePermissionSelected.getIdModulePermission().getIdModulePermission(),
                            nodeIteractive);
                }
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar generar el menú principal", e.getCause());
        }
        log.info("Menú construido y expandido");
        return root;
    } 

    public String navigationTree(ItemTreeIcon nodeSelected)
    {
        String result = ""; 
        try 
        {
            //Caso para cuando el usuario quiere salir de la aplicación
            if(nodeSelected.getPage()!= null && nodeSelected.getPage().length() > 0)
            {
                 if(nodeSelected.getPage().equalsIgnoreCase("exit"))
                 {
                    result =  nodeSelected.getPage();
                    cleanSession();
                 }
                 else if(nodeSelected.getType().equalsIgnoreCase("Item"))
                 {
                     if(getMapTabs()!= null && !getMapTabs().containsKey(nodeSelected.getName()))
                     {
                        Tab tab = new Tab();
                        tab.setClosable(true);
                        tab.setTitle(nodeSelected.getName());
                        getTabView().getChildren().add(tab);
                        getMapTabs().put(nodeSelected.getName(), getMapTabs().size());
                     
                     }
                     else
                     {
                         getTabView().setActiveIndex(getMapTabs().get(nodeSelected.getName()));
                     }
                 }
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar realizar la navegación del árbol de menú", e);
            e.printStackTrace();
        }
        return result;
    }

    public void cleanSession()
    {
        getSessionBean().setScUser(null);
    }

    public TreeNode getRoot()
    {
        return root;
    }

    public void setRoot(TreeNode root)
    {
        this.root = root;
    }

  
    public IScModulePermissionByRole getScModulePermissionByRoleServer()
    {
        return scModulePermissionByRoleServer;
    }

    public void setScModulePermissionByRoleServer(IScModulePermissionByRole scModulePermissionByRoleServer)
    {
        this.scModulePermissionByRoleServer = scModulePermissionByRoleServer;
    }

    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public List<Tab> getMainTabs()
    {
        return mainTabs;
    }

    public void setMainTabs(List<Tab> mainTabs)
    {
        this.mainTabs = mainTabs;
    }

    public TabView getTabView()
    {
        return tabView;
    }

    public void setTabView(TabView tabView)
    {
        this.tabView = tabView;
    }

    public HashMap<String, Integer> getMapTabs()
    {
        return mapTabs;
    }

    public void setMapTabs(HashMap<String, Integer> mapTabs)
    {
        this.mapTabs = mapTabs;
    }

    
    

}
