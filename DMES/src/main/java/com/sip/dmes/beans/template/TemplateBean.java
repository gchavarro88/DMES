/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.template;



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
//    private ScModulePermissionDao scModulePermissionDao;//Objeto de acceso a los datos de los módulos
    private final String TREE_MENU_PLANT_VISIBILITY = "Visibilidad de Planta";//Item del menú principal
    private final String TREE_MENU_SCHEDULING_ORDER = "Programación de Orden de Fabricación";//Item del menú principal
    private final String TREE_MENU_QUALITY_MANAGEMENT_AND_TRACKING = "Gestión de la Calidad y la Trazabilidad";//Item del menú principal
    private final String TREE_MENU_MAINTENANCE_MANAGEMENT = "Gestión del Mantenimiento";//Item del menú principal
    private final String TREE_MENU_ROSOURCES_MANAGEMENT = "Gestión de los Recursos";//Item del menú principal
    private final String TREE_MENU_SETTINGS = "Configuraciones";//Item del menú principal
    private final String TREE_MENU_LEAVE = "Salir";//Item del menú principal

    /** 
     * Creates a new instance of TemplateBean
     */
    
    public TemplateBean()
    {
        
    }
    @PostConstruct
    public void initData()
    {
//        scModulePermissionDao = new ScModulePermissionDao();
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
        root = new DefaultTreeNode(new ItemTreeIcon("root", "Folder"), null);
//        List<ScModulePermission> listModules = null;
        DefaultTreeNode node1 = new DefaultTreeNode(new ItemTreeIcon("DMES", "Folder"), root);
        node1.setType("Home");
        node1.setExpanded(true);
        try
        { 
//            listModules = getScModulePermissionDao().getAllModulePermissions();    
//            DefaultTreeNode nodeIteractive = null;
//            if(listModules != null && !listModules.isEmpty())
//            {
//                for(ScModulePermission scModulePermissionSelected: listModules)
//                {
//                    nodeIteractive = new DefaultTreeNode(new ItemTreeIcon(scModulePermissionSelected.getName()
//                            , scModulePermissionSelected.getType()), node1);
//                    nodeIteractive.setType(scModulePermissionSelected.getType());
//                }
//            }
        }
        catch(Exception e)
        {
            log.error("Error al intentar generar el menú principal", e.getCause());
        }
        node1.setExpanded(true);
        log.info("Menú construido y expandido");
        return root;
    }

//    /**
//     * Método encargado de llenar las categorias fijas del árbol de la pantalla
//     * inicial de pedidos
//     * <p>
//     * @return Nodo raiz del árbol de categorías con todos sus hijos
//     * @author: Gustavo Adolfo Chavarro Ortiz
//     */
//    public DefaultTreeNode getTypeNode(DefaultTreeNode node)
//    {
//        if (node != null)
//        {
//            node.setType("document");
//        }
//        return node;
//    }

//    public String getImage(String nameNode)
//    {
//        String result = "";
//        if (nameNode.equalsIgnoreCase(TREE_MENU_PLANT_VISIBILITY))
//        {
//            result = "oee.png";
//        }
//        else if (nameNode.equalsIgnoreCase(TREE_MENU_SCHEDULING_ORDER))
//        {
//            result = "ord.png";
//        }
//        else if (nameNode.equalsIgnoreCase(TREE_MENU_QUALITY_MANAGEMENT_AND_TRACKING))
//        {
//            result = "cal.png";
//        }
//        else if (nameNode.equalsIgnoreCase(TREE_MENU_MAINTENANCE_MANAGEMENT))
//        {
//            result = "man.png";
//        }
//        else if (nameNode.equalsIgnoreCase(TREE_MENU_ROSOURCES_MANAGEMENT))
//        {
//            result = "rec.png";
//        }
//        else if (nameNode.equalsIgnoreCase(TREE_MENU_SETTINGS))
//        {
//            result = "confi.png";
//        }
//        else if (nameNode.equalsIgnoreCase(TREE_MENU_LEAVE))
//        {
//            result = "salir.png";
//        }
//        return result;
//    }

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

//    public ScModulePermissionDao getScModulePermissionDao()
//    {
//        return scModulePermissionDao;
//    }
//
//    public void setScModulePermissionDao(ScModulePermissionDao scModulePermissionDao)
//    {
//        this.scModulePermissionDao = scModulePermissionDao;
//    }
    
    

}
