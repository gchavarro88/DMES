/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmes.beans.resources.humans;

import com.sip.dmes.beans.SessionBean;
import com.sip.dmes.dao.bo.IScEmployee;
import com.sip.dmes.dao.bo.IScPerson;
import com.sip.dmes.entitys.ScCompetencies;
import com.sip.dmes.entitys.ScEmployee;
import com.sip.dmes.entitys.ScPerson;
import com.sip.dmes.entitys.ScWorkExperience;

import com.sip.dmes.utilities.DMESConstants;
import com.sip.dmes.utilities.Utilities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author gchavarro88
 */
public class ScemployeesBean
{
    
    private SessionBean sessionBean;
    private IScEmployee scEmployeeServer;
    private IScPerson scPersonServer;
    private ScPerson personAdd;
    private ScPerson personUpdate;
    private ScEmployee employeeAdd;
    private ScEmployee employeeSelected;
    private ScEmployee employeeUpdate;
    private ScWorkExperience workExperienceAdd;
    private ScWorkExperience workExperienceUpdate;
    private ScCompetencies competenciesAdd;
    private ScCompetencies competenciesUpdate;
    private List<ScCompetencies> competenciesListAdd;
    private List<ScWorkExperience> workExperiencesListAdd;
    private List<ScPerson> personsList;
    private List<ScPerson> personsListUpdate;
    private List<ScEmployee> employeesList;
    private final static Logger log = Logger.getLogger(ScemployeesBean.class);
    
    
    private final String TAB_PERSON_SAVE = "tabPerson";
    private final String TAB_PERSON_UPDATE = "tabPersonUpdate";
    private final String TAB_CONFIRM_SAVE = "tabAcceptSave";
    private final String TAB_CONFIRM_UPDATE = "tabAcceptUpdate";
    private final String TAB_EMPLOYEE_SAVE = "tabEmployee";
    private final String TAB_EMPLOYEE_UPDATE = "tabEmployeeUpdate";
    

    /**
     * Creates a new instance of ScemployeesBean
     */
    public ScemployeesBean()
    {
        
    }
    
    
    @PostConstruct
    public void initData()
    {
        cleanValues();
        fillListEmployee();
        fillListPersons();
    }   
    
    public void fillListEmployee()
    {
        if(getEmployeesList() == null)
        {
            try
            {
                setEmployeesList(getScEmployeeServer().getAllEmployees());
            }
            catch (Exception e)
            {
                log.error("Error al intentar consutlar la lista de empleados para visualizar", e);
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            }
        }
    }
    
    
    public void fillListPersons()
    { 
        try
        { 
            if(getPersonsList() == null)
            {
                List personsWithOutEmployee = getScPersonServer().findPersonWithOutPartnerOrEmployee();
                if(personsWithOutEmployee != null)
                {
                    setPersonsList(new ArrayList<ScPerson>());
                    for (Object object : personsWithOutEmployee)
                    {
                        getPersonsList().add(ObjectToScPerson(object));
                    }
                }
            }
        }
        catch (Exception e)
        { 
            log.error("Error al intentar consultar las personas que no cuentan con un usuario",e);
        }
    
    }
    
    public void numericValidation(String field)
    {
        if(!Utilities.isNumeric(field))
        {
            addError(null, "Valor no numérico", "Debe ingresar un valor numérico, "
                    + "de lo contrario no podrá ir al siguiente paso");
        }
    }
    public void cleanValues()
    {
        setPersonAdd(new ScPerson());
        setPersonUpdate(new ScPerson());
        setEmployeeAdd(new ScEmployee());
        setEmployeeSelected(new ScEmployee());
        setEmployeeUpdate(new ScEmployee());
        setWorkExperienceAdd(new ScWorkExperience());
        setWorkExperienceUpdate(new ScWorkExperience());
        setCompetenciesAdd(new ScCompetencies());
        setCompetenciesUpdate(new ScCompetencies());
        setCompetenciesListAdd(new ArrayList<ScCompetencies>());
        setWorkExperiencesListAdd(new ArrayList<ScWorkExperience>());
        //setPersonsList(new ArrayList<ScPerson>());
        //setEmployeesList(new ArrayList<ScEmployee>());
    }
    
    public ScPerson ObjectToScPerson(Object object)
    {
        Object[] objectList = ((Object[]) object);
        ScPerson newPerson = new ScPerson();
        newPerson.setIdPerson(Long.parseLong(objectList[0].toString()));
        newPerson.setFirstName(objectList[1].toString());
        newPerson.setLastName(objectList[2].toString());
        newPerson.setAge(Short.parseShort(objectList[3].toString()));
        newPerson.setCountry(objectList[4].toString());
        newPerson.setCity(objectList[5].toString());
        newPerson.setPersonalInformation(objectList[6] !=null ? objectList[6].toString():"");
        newPerson.setDomicilie(objectList[7].toString());
        newPerson.setStudies(objectList[8] !=null ? objectList[8].toString():"");
        newPerson.setDescription(objectList[9] !=null ? objectList[9].toString():"");
        newPerson.setPathPhoto(objectList[10] !=null ? objectList[10].toString():"/");
        newPerson.setIdentification(Long.parseLong(objectList[13].toString()));
        return newPerson;
    }
    
    public String onFlowProcessSaveEmployee(FlowEvent event) 
    {    
        if(event.getOldStep().equals(TAB_PERSON_SAVE))
        {
            if(getPersonAdd() == null || getPersonAdd().getLastName().length() < 1)
            {
                addError(null, "Error al intentar crear un nuevo empleado", "Debe seleccionar un tercero");
                return event.getOldStep();
            }
        }
        if(event.getOldStep().equals(TAB_EMPLOYEE_SAVE))
        {
            if(getEmployeeAdd().getSalary().doubleValue() < 1)
            {
                addError(null, "Error al intentar crear un nuevo empleado", "El salario debe ser mayor 0");
                return event.getOldStep();
            }
        }
        if(event.getNewStep().equals(TAB_CONFIRM_SAVE))
        {
            getEmployeeAdd().setCreationDate(new Date());
        }
        
        
            return event.getNewStep();  
    }
    
    public String onFlowProcessUpdateEmployee(FlowEvent event) 
    {    
        if(event.getNewStep().equals(TAB_CONFIRM_UPDATE))
        {
            getEmployeeSelected().setModifyDate(new Date());
        }
        if(event.getOldStep().equals(TAB_EMPLOYEE_UPDATE))
        {
            if(getEmployeeSelected().getSalary().doubleValue() < 1)
            {
                addError(null, "Error al intentar actualizar un nuevo empleado", "El salario debe ser mayor 0");
            }
        }
            return event.getNewStep(); 
    }
    
    public void saveWorkExperiencieAdd()
    {
        if(getWorkExperienceAdd() != null && getWorkExperiencesListAdd() != null)
        {
            getWorkExperienceAdd().setIdEmployee(getEmployeeAdd());
            getWorkExperiencesListAdd().add(getWorkExperienceAdd());
            setWorkExperienceAdd(new ScWorkExperience());
            addInfo(null, "Experiencia Laboral Agregada", "Se agregó la experiencia laboral con éxito");
        }
        else
        {
            log.error("Error al intentar agregar una experiencia laboral");
            addError(null, "Error al Agregar una Experiencia Laboral ", "No se pudo agregar la experiencia laboral");
        }
    }
    
    public void removeWorkExperiencie(ScWorkExperience workExperienceDelete)
    {
        int i=0;
        if(getWorkExperiencesListAdd() != null)
        {
            for(ScWorkExperience workExperienceList: getWorkExperiencesListAdd())
            {
                    if(workExperienceList.getCompanyName().
                    equalsIgnoreCase(workExperienceDelete.getCompanyName()))
                    {
                        getWorkExperiencesListAdd().remove(i);
                        addInfo(null, "Experiencia Laboral Eliminada", "Se eliminó la experiencia laboral con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    
    public void saveCompetencieAdd()
    {
        if(getCompetenciesAdd() != null && getCompetenciesListAdd()!= null)
        {
            getCompetenciesAdd().setIdEmployee(getEmployeeAdd());
            getCompetenciesListAdd().add(getCompetenciesAdd());
            setCompetenciesAdd(new ScCompetencies());
            addInfo(null, "Competencia Agregada", "Se agregó la competencia con éxito");
        }
        else
        {
            log.error("Error al intentar agregar una competencia");
            addError(null, "Error al Agregar una Competencia ", "No se pudo agregar la competencia");
        }
    }
    
    public void removeCompetencie(ScCompetencies competencies)
    {
        int i=0;
        if(getWorkExperiencesListAdd() != null)
        {
            for(ScCompetencies competenciesList: getCompetenciesListAdd())
            {
                    if(competenciesList.getTittle().equals(competencies.getTittle()))
                    {
                        getCompetenciesListAdd().remove(i);
                        addInfo(null, "Compentencia Eliminada", "Se eliminó la competencia con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    public void saveEmployee()
    {
        if(getEmployeeAdd() != null)
        {
            if(getPersonAdd() != null)
            {
                getEmployeeAdd().setIdPerson(getPersonAdd());
            }
            if(!getWorkExperiencesListAdd().isEmpty())
            {
                getEmployeeAdd().setScWorkExperienceList(getWorkExperiencesListAdd());
            }
            if(!getCompetenciesListAdd().isEmpty())
            {
                getEmployeeAdd().setScCompetenciesList(getCompetenciesListAdd());
            }
            try
            {
                getEmployeeAdd().setActive('Y');
                getScEmployeeServer().createEmployee(getEmployeeAdd());
                getPersonsList().remove(getPersonAdd());
                getEmployeesList().add(getEmployeeAdd());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                cleanValues();
            }
            catch(Exception e)
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                log.error("Error al intentar crear un nuevo empleado",e);
            }
        }
    
    }
    
    public void getEmployeeByDataTable(ScEmployee employeeSelected)
    {
        try
        {
            if(employeeSelected != null)
            {
                setEmployeeSelected(employeeSelected);
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando asignar el empleado seleccionado para operaciones de CRUD",e);
        }
    }
    
    public void removeWorkExperiencieUpdate(ScWorkExperience workExperienceDelete)
    {
        int i=0;
        if(getEmployeeSelected().getScWorkExperienceList() != null)
        {
            for(ScWorkExperience workExperienceList: getEmployeeSelected().getScWorkExperienceList())
            {
                    if(workExperienceList.getCompanyName().
                    equalsIgnoreCase(workExperienceDelete.getCompanyName()))
                    {
                        getEmployeeSelected().getScWorkExperienceList().remove(i);
                        addInfo(null, "Experiencia Laboral Eliminada", "Se eliminó la experiencia laboral con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    public void updateWorkExperiencieAdd()
    {
        if(getWorkExperienceAdd() != null && getEmployeeSelected().getScWorkExperienceList() != null)
        {
            getWorkExperienceAdd().setIdEmployee(getEmployeeSelected());
            getEmployeeSelected().getScWorkExperienceList().add(getWorkExperienceAdd());
            setWorkExperienceAdd(new ScWorkExperience());
            addInfo(null, "Experiencia Laboral Agregada", "Se agregó la experiencia laboral con éxito");
        }
        else
        {
            log.error("Error al intentar agregar una experiencia laboral");
            addError(null, "Error al Agregar una Experiencia Laboral ", "No se pudo agregar la experiencia laboral");
        }
    }
    
    public void updateCompetencieAdd()
    {
        if(getCompetenciesAdd() != null && getEmployeeSelected().getScCompetenciesList()!= null)
        {
            getCompetenciesAdd().setIdEmployee(getEmployeeSelected());
            getEmployeeSelected().getScCompetenciesList().add(getCompetenciesAdd());
            setCompetenciesAdd(new ScCompetencies());
            addInfo(null, "Competencia Agregada", "Se agregó la competencia con éxito");
        }
        else
        {
            log.error("Error al intentar agregar una competencia");
            addError(null, "Error al Agregar una Competencia ", "No se pudo agregar la competencia");
        }
    }
    
    public void removeCompetencieUpdate(ScCompetencies competencies)
    {
        int i=0;
        if(getEmployeeSelected().getScCompetenciesList() != null)
        {
            for(ScCompetencies competenciesList: getEmployeeSelected().getScCompetenciesList())
            {
                    if(competenciesList.getTittle().equals(competencies.getTittle()))
                    {
                        getEmployeeSelected().getScCompetenciesList().remove(i);
                        addInfo(null, "Compentencia Eliminada", "Se eliminó la competencia con éxito");
                        break;
                    }
                    i++;
            }
        }
    }
    
    public void updateEmployee()
    {
        if(getEmployeeSelected()!= null)
        {
            try
            {
                getEmployeeSelected().setActive('Y');
                getScEmployeeServer().updateEmployee(getEmployeeSelected());
                getPersonsListUpdate().remove(getEmployeeSelected().getIdPerson());
                addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                cleanValues();
            }
            catch(Exception e)
            {
                addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
                log.error("Error al intentar crear un nuevo empleado",e);
            }
        }
    }
    
    public void getEmployeeByDataTableUpdate(ScEmployee employeeSelected)
    {
        try
        {
            if(employeeSelected != null)
            {
                setEmployeeSelected(employeeSelected);
                setPersonsListUpdate(getPersonsList());
                getPersonsListUpdate().add(employeeSelected.getIdPerson());
            }
        }
        catch (Exception e)
        {
            log.error("Error intentando asignar el empleado seleccionado para operaciones de CRUD",e);
        }
    }
    
    public void deleteEmployee()
    {
        try
        {
            int i=0;
            if(getEmployeeSelected() != null)
            {
                for(ScEmployee employee: getEmployeesList())
                {
                    if(Objects.equals(employee.getIdEmployee(), getEmployeeSelected().getIdEmployee()))
                    {
                        getScEmployeeServer().deleteteEmployeeById(getEmployeeSelected());
                        getEmployeesList().remove(i);
                        addInfo(null, DMESConstants.MESSAGE_TITTLE_SUCCES, DMESConstants.MESSAGE_SUCCES);
                        getPersonsList().add(getEmployeeSelected().getIdPerson());
                        break;
                    }
                    i++;
                }
            }
        }
        catch(Exception e)
        {
            addError(null, DMESConstants.MESSAGE_TITTLE_ERROR_ADMINISTRATOR, DMESConstants.MESSAGE_ERROR_ADMINISTRATOR);
            log.error("Error al intentar eliminar un empleado", e);
        }
    
    }
    
    /**
     * Método que se encarga de recibir un patrón y una fecha de tipo Date, y
     * deberá retornar una cadena de carácteres de la fecha siguiendo el patrón
     * recibido
     * <p>
     * @param pattern patrón del formato de la fecha
     * @param date fecha a visualizar
     * <p>
     * @return valor de la fecha en el formato indicado por el patrón de tipo
     * String
     * <p>
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public String getFormatDateGlobal(String pattern, Date date)
    {
        String result = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null)
        {
            result = simpleDateFormat.format(date);
        }
        return result;
    } 
    
    public String getFormatDate(Date date)
    {
        String result = "";
        String patron = "dd-MM-yyyy";
        result = getFormatDateGlobal(patron, date);
        return result;
    }
    
    /**
     * Método encargado de visualizar un  mensaje en la pantalla de tipo informativo
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addInfo(ActionEvent actionEvent, String tittle, String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, tittle, message));
    }

    /**
     * Método encargado de visualizar un  mensaje en la pantalla de tipo Advertencia
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addWarn(ActionEvent actionEvent, String tittle, String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, tittle, message));
    }

    /**
     * Método encargado de visualizar un  mensaje en la pantalla de tipo Error
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addError(ActionEvent actionEvent, String tittle, String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, tittle, message));
    }

    /**
     * Método encargado de visualizar un  mensaje en la pantalla de tipo Fatal
     * @param actionEvent Evento de donde es llamado
     * @param tittle Título del mensaje
     * @param message cuerpo del mensaje
     * @author: Gustavo Adolfo Chavarro Ortiz
     */
    public void addFatal(ActionEvent actionEvent, String tittle, String message)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, tittle, message));
    }
    /**
     * Métodos Getters And Setters.
     */
    public SessionBean getSessionBean()
    {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean)
    {
        this.sessionBean = sessionBean;
    }

    public IScEmployee getScEmployeeServer()
    {
        return scEmployeeServer;
    }

    public void setScEmployeeServer(IScEmployee scEmployeeServer)
    {
        this.scEmployeeServer = scEmployeeServer;
    }

    public IScPerson getScPersonServer()
    {
        return scPersonServer;
    }

    public void setScPersonServer(IScPerson scPersonServer)
    {
        this.scPersonServer = scPersonServer;
    }

    public ScPerson getPersonAdd()
    {
        return personAdd;
    }

    public void setPersonAdd(ScPerson personAdd)
    {
        this.personAdd = personAdd;
    }

    public ScPerson getPersonUpdate()
    {
        return personUpdate;
    }

    public void setPersonUpdate(ScPerson personUpdate)
    {
        this.personUpdate = personUpdate;
    }

    public ScEmployee getEmployeeAdd()
    {
        return employeeAdd;
    }

    public void setEmployeeAdd(ScEmployee employeeAdd)
    {
        this.employeeAdd = employeeAdd;
    }

    public ScEmployee getEmployeeUpdate()
    {
        return employeeUpdate;
    }

    public void setEmployeeUpdate(ScEmployee employeeUpdate)
    {
        this.employeeUpdate = employeeUpdate;
    }

    public List<ScPerson> getPersonsList()
    {
        return personsList;
    } 

    public void setPersonsList(List<ScPerson> personsList)
    {
        this.personsList = personsList;
    }

    public List<ScEmployee> getEmployeesList()
    {
        return employeesList;
    } 

    public void setEmployeesList(List<ScEmployee> employeesList)
    {
        this.employeesList = employeesList;
    }

    public ScEmployee getEmployeeSelected()
    {
        return employeeSelected;
    }

    public void setEmployeeSelected(ScEmployee employeeSelected)
    {
        this.employeeSelected = employeeSelected;
    }

    public ScWorkExperience getWorkExperienceAdd()
    {
        return workExperienceAdd;
    }

    public void setWorkExperienceAdd(ScWorkExperience workExperienceAdd)
    {
        this.workExperienceAdd = workExperienceAdd;
    }

    public ScWorkExperience getWorkExperienceUpdate()
    {
        return workExperienceUpdate;
    }

    public void setWorkExperienceUpdate(ScWorkExperience workExperienceUpdate)
    {
        this.workExperienceUpdate = workExperienceUpdate;
    }

    public ScCompetencies getCompetenciesAdd()
    {
        return competenciesAdd;
    }

    public void setCompetenciesAdd(ScCompetencies competenciesAdd)
    {
        this.competenciesAdd = competenciesAdd;
    }

    public ScCompetencies getCompetenciesUpdate()
    {
        return competenciesUpdate;
    }

    public void setCompetenciesUpdate(ScCompetencies competenciesUpdate)
    {
        this.competenciesUpdate = competenciesUpdate;
    }

    public List<ScCompetencies> getCompetenciesListAdd()
    {
        return competenciesListAdd;
    }

    public void setCompetenciesListAdd(List<ScCompetencies> competenciesListAdd)
    {
        this.competenciesListAdd = competenciesListAdd;
    }

    public List<ScWorkExperience> getWorkExperiencesListAdd()
    {
        return workExperiencesListAdd;
    }

    public void setWorkExperiencesListAdd(List<ScWorkExperience> workExperiencesListAdd)
    {
        this.workExperiencesListAdd = workExperiencesListAdd;
    }

    public List<ScPerson> getPersonsListUpdate()
    {
        return personsListUpdate;
    }

    public void setPersonsListUpdate(List<ScPerson> personsListUpdate)
    {
        this.personsListUpdate = personsListUpdate;
    }
    
    
}
