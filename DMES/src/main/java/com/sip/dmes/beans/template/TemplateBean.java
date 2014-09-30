/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.template;



import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.dao.bo.IScSubModulePermissionByRole;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.entitys.ScSubmodulePermission;
import com.sip.dmes.entitys.ScSubmodulePermissionByRole;
import com.sip.dmes.utilities.ItemTreeIcon;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.component.tabview.Tab;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author gchavarro88
 */
public class TemplateBean
{

    private final static Logger log = Logger.getLogger(TemplateBean.class);
    private TreeNode root; //Nodo base del árbol del menú
    private TreeNode nodeSeleted; //Nodo del árbol seleccionado
    IScModulePermissionByRole scModulePermissionByRoleServer;
    IScSubModulePermissionByRole scSubModulePermissionByRoleServer;
    SessionBean sessionBean;
    List<Tab> mainTabs;
    

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
        setMainTabs(new ArrayList<Tab>());
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
        List<ScSubmodulePermissionByRole> listSubModules = null;
        DefaultTreeNode node1 = new DefaultTreeNode(new ItemTreeIcon("DMES", null,"Folder"), root);
        node1.setType("Home");
        node1.setExpanded(true);
        try
        { 
            listModules = getScModulePermissionByRoleServer().getAllIScModulePermissionsByRole(getSessionBean()
                    .getScUser().getIdRole());    
            DefaultTreeNode nodeIteractive = null;
            DefaultTreeNode nodeIntern = null;
            if(listModules != null && !listModules.isEmpty())
            {
                for(ScModulePermissionByRole scModulePermissionSelected: listModules)
                {
                    nodeIteractive = new DefaultTreeNode(new ItemTreeIcon(scModulePermissionSelected.getIdModulePermission().getName(),
                            scModulePermissionSelected.getIdModulePermission().getType(),
                            scModulePermissionSelected.getIdModulePermission().getIcone()), node1);
                    nodeIteractive.setType(scModulePermissionSelected.getIdModulePermission().getType());
                    //Se construyen los items de cada módulo
                    listSubModules = getScSubModulePermissionByRoleServer().getAllIScModulePermissionsByRole
                        (scModulePermissionSelected.getIdRole(),scModulePermissionSelected.getIdModulePermission());
                    for(ScSubmodulePermissionByRole scSubmodulePermissionSelected: listSubModules)
                    {
                        nodeIntern = new DefaultTreeNode(new ItemTreeIcon(scSubmodulePermissionSelected.getScSubmodulePermission()
                                .getName(), scSubmodulePermissionSelected.getScSubmodulePermission().getType(), 
                                scSubmodulePermissionSelected.getScSubmodulePermission().getDescription()), nodeIteractive);
                        nodeIntern.setType("Item");
                    }
                }
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar generar el menú principal", e.getCause());
        }
        node1.setExpanded(true);
        log.info("Menú construido y expandido");
        return root;
    }

    public void navigationTree()
    {
        try
        {
            if(((ItemTreeIcon) nodeSeleted.getData()).getName().equalsIgnoreCase("Salir"))
            {
                 FacesContext.getCurrentInstance().getExternalContext().dispatch("Login.xhtml");
            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar realizar la navegación del árbol de menú", e);
        }
        
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

    public TreeNode getNodeSeleted()
    {
        return nodeSeleted;
    }

    public void setNodeSeleted(TreeNode nodeSeleted)
    {
        this.nodeSeleted = nodeSeleted;
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

    public IScSubModulePermissionByRole getScSubModulePermissionByRoleServer()
    {
        return scSubModulePermissionByRoleServer;
    }

    public void setScSubModulePermissionByRoleServer(IScSubModulePermissionByRole scSubModulePermissionByRoleServer)
    {
        this.scSubModulePermissionByRoleServer = scSubModulePermissionByRoleServer;
    }
    
    

}
