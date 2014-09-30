/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.template;



import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScModulePermissionByRole;
import com.sip.dmes.entitys.ScModulePermission;
import com.sip.dmes.entitys.ScModulePermissionByRole;
import com.sip.dmes.utilities.ItemTreeIcon;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
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
    SessionBean sessionBean;


    /** 
     * Creates a new instance of TemplateBean
     */
    
    public TemplateBean()
    {
        
    }
    
    @PostConstruct
    public void initData()
    {

        fillCategories(); 
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
    public TreeNode fillCategories()
    { 
        log.info("Inicia la construcción del menú lateral principal");
        root = new DefaultTreeNode(new ItemTreeIcon("root", null,"Folder"), null);
        List<ScModulePermissionByRole> listModules = null;
        DefaultTreeNode node1 = new DefaultTreeNode(new ItemTreeIcon("DMES", null,"Folder"), root);
        node1.setType("Home");
        node1.setExpanded(true);
        try
        { 
            listModules = getScModulePermissionByRoleServer().getAllIScModulePermissionsByRole(getSessionBean()
                    .getScUser().getIdRole());    
            DefaultTreeNode nodeIteractive = null;
            if(listModules != null && !listModules.isEmpty())
            {
                for(ScModulePermissionByRole scModulePermissionSelected: listModules)
                {
                    nodeIteractive = new DefaultTreeNode(new ItemTreeIcon(scModulePermissionSelected.getIdModulePermission().getName(),
                            scModulePermissionSelected.getIdModulePermission().getType(),
                            scModulePermissionSelected.getIdModulePermission().getIcone()), node1);
                    nodeIteractive.setType(scModulePermissionSelected.getIdModulePermission().getType());
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
    
    

}
