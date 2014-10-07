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
import com.sip.dmes.utilities.MainTabs;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.tabview.TabView;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author gchavarro88
 */
public class TemplateBean implements Serializable
{

    private final static Logger log = Logger.getLogger(TemplateBean.class);
    private TreeNode root; //Nodo base del árbol del menú
    private TabView tabView;
    private IScModulePermissionByRole scModulePermissionByRoleServer;
    private SessionBean sessionBean;
    private List<MainTabs> mainTabsRecord;
    private List<MainTabs> mainTabs;
    private HashMap<String, Integer> mapTabs;
    private int activeIndex;
    private MenuModel model;
    

    /** 
     * Creates a new instance of TemplateBean
     */
    
    public TemplateBean()
    { 
        
    }
    
    @PostConstruct
    public void initData()
    {
        initDataTab();  
        initTreeCagotegories(); 
        
    }
 
    public void initDataTab()
    {
        setMainTabsRecord(new ArrayList<MainTabs>());
        setMainTabs(new ArrayList<MainTabs>());
        setMapTabs(new HashMap<String, Integer>()) ;
        setModel(new DefaultMenuModel());
        getMainTabs().add(new MainTabs("DMES", 0, "", true));
        
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
        int amountTabs = 0;
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
                        
                        if(scModulePermissionSelected.getIdModulePermission().getType().equalsIgnoreCase("Item"))
                        {
                            mainTabsRecord.add(new MainTabs(scModulePermissionSelected.
                            getIdModulePermission().getName(), amountTabs, scModulePermissionSelected.
                            getIdModulePermission().getPage(), false));
                            nodeIteractive = new DefaultTreeNode(new ItemTreeIcon(scModulePermissionSelected.
                            getIdModulePermission().getName(),scModulePermissionSelected.getIdModulePermission().
                            getType(),scModulePermissionSelected.getIdModulePermission().getIcone(), scModulePermissionSelected.
                            getIdModulePermission().getPage(), amountTabs), nodeFather);
                            
                            amountTabs++;
                        }
                        else
                        {
                            nodeIteractive = new DefaultTreeNode(new ItemTreeIcon(scModulePermissionSelected.
                            getIdModulePermission().getName(),scModulePermissionSelected.getIdModulePermission().
                            getType(),scModulePermissionSelected.getIdModulePermission().getIcone(), scModulePermissionSelected.
                            getIdModulePermission().getPage()), nodeFather);
                        }
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
        DefaultMenuItem menuItemClose;
        DefaultMenuItem menuItemView;
        DefaultSubMenu subMenuItem;
        CommandButton buttonItem;
        int idSubMenu = 0;
        int idMenuItem = 0;
        try  
        {
            //Caso para cuando el usuario quiere salir de la aplicación
            if(nodeSelected.getPage()!= null && nodeSelected.getPage().length() > 0)
            {
                if(nodeSelected.getType().equalsIgnoreCase("Item"))
                {
                    if(getMapTabs() != null && getMapTabs().get(nodeSelected.getName()) == null)
                    {
                        if(getMainTabs() != null && getMainTabs().size() == 1 && 
                                getMainTabs().get(0).getTitle().equalsIgnoreCase("DMES"))
                        {
                            getMainTabs().clear();
                        }
                        idSubMenu = getMapTabs().size();
                        //Se crea la pestaña nueva
                        MainTabs maintTabAdd = getMainTabsRecord().get(nodeSelected.getPosition());
                        maintTabAdd.setIndex(getMapTabs().size());
                        mainTabs.add(maintTabAdd);
                        getMapTabs().put(nodeSelected.getName(), getMapTabs().size());
                        setActiveIndex(getMapTabs().size()-1);
                        //Se crea el botón para el menú de pestañas
                        subMenuItem = new DefaultSubMenu((getMapTabs().size())+"-"+nodeSelected.getName());
                        subMenuItem.setId(idSubMenu+"");
//                        subMenuItem.setStyle("width:50px;");
                        menuItemView = new DefaultMenuItem("Ver pestaña", "ui-icon-document");
                        menuItemView.setId(idSubMenu+"_"+idMenuItem);
                        menuItemView.setCommand("#{templateBean.selectedTab(\""+nodeSelected.getName()+"\")}");
                        menuItemView.setUpdate(":formMainTabs");
                        menuItemView.setIconPos("right");
                        menuItemView.setStyleClass("subMenuItem");
                        //menuItemView.setStyle("width:25px;");
                        idMenuItem++;
                        //Se crea el segundo menú
                        menuItemClose = new DefaultMenuItem("Cerrar pestaña", "ui-icon-circle-close");
                        menuItemClose.setId(idSubMenu+"_"+idMenuItem);
                        menuItemClose.setCommand("#{templateBean.removeTabs(\""+nodeSelected.getName()+"\")}");
                        menuItemClose.setUpdate(":formMainTabs");
                        menuItemClose.setIconPos("right");
                        menuItemClose.setStyleClass("subMenuItem");
                        //menuItemClose.setStyle("width:25px;");
                        subMenuItem.addElement(menuItemView);
                        subMenuItem.addElement(menuItemClose);
                        model.addElement(subMenuItem);
                        idSubMenu++;
                    } 
                    else
                    {
                        setActiveIndex(getMapTabs().get(nodeSelected.getName()));
                    }
                }
                else
                {
                    if(nodeSelected.getPage().equalsIgnoreCase("exit"))
                    {
                       result =  nodeSelected.getPage();
                       cleanSession();
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

    public boolean isTabVisible(int index)
    {
        return getMainTabs().get(index).isVisible();
    }
    
    public void removeTabs(String idMenu)
    {
       try 
       {
           int position = getMapTabs().get(idMenu);
           mainTabs.remove(position);
           mapTabs.remove(idMenu);
           Iterator iterator = getMapTabs().entrySet().iterator();
           while (iterator.hasNext())
           {
               Map.Entry entry = (Map.Entry) iterator.next();
               int valueMap = ((Integer) entry.getValue());
               if(valueMap > position)
               {
                   entry.setValue( valueMap -1);
                   System.out.println(valueMap+" Value Map");
               }
           }
           if(getMainTabs().isEmpty())
           {
               getMainTabs().clear();
               getMainTabs().add(new MainTabs("DMES", 0, "", true));
           }
           String idDelete = model.getElements().get(position).getId();
           model.getElements().remove(position);
           updateIdsModel(idDelete);
       }
       catch(Exception e)
       {
           
       }
    }
    
    public void updateIdsModel(String idDelete)
    {
        try
        {
            int idDeleted = Integer.parseInt(idDelete);
            for(Object o: getModel().getElements())
            {
                if(((DefaultSubMenu) o).getId() != null && ((DefaultSubMenu) o).getId().length() > 0)
                {
                    int idNew = Integer.parseInt(((DefaultSubMenu) o).getId());
                    if(idDeleted < idNew)
                    {
                        idNew -= 1;
                        int newIdMenuItem = 0;
                        String tittle = ((DefaultSubMenu) o).getLabel();
                        tittle = (idNew+1)+tittle.substring(1, tittle.length());
                        ((DefaultSubMenu) o).setLabel(tittle);
                        ((DefaultSubMenu) o).setId(idNew+"");
                        for(Object menuItem: ((DefaultSubMenu) o).getElements())
                        {
                            ((DefaultMenuItem) menuItem).setId(idNew+"_"+newIdMenuItem);
                            newIdMenuItem++;
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void selectedTab(String idMenu)
    {
         setActiveIndex(getMapTabs().get(idMenu));
    }
    public void cleanSession()
    {
        getSessionBean().setScUser(null);
    }

    public static MethodExpression createMethodExpression(String expression, Class<?> returnType, Class<?>... parameterTypes)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getExpressionFactory().createMethodExpression(
                facesContext.getELContext(), expression, returnType, parameterTypes);
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

    public List<MainTabs> getMainTabs()
    {
        return mainTabs;
    }

    public void setMainTabs(List<MainTabs> mainTabs)
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

    public int getActiveIndex()
    {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex)
    {
        this.activeIndex = activeIndex;
    }

    public List<MainTabs> getMainTabsRecord()
    {
        return mainTabsRecord;
    }

    public void setMainTabsRecord(List<MainTabs> mainTabsRecord)
    {
        this.mainTabsRecord = mainTabsRecord;
    }

    public MenuModel getModel()
    {
        return model;
    }

    public void setModel(MenuModel model)
    {
        this.model = model;
    }

  


    
    

}
