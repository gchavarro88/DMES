<%-- 
    Document   : getFactoryLocations
    Created on : 28/04/2016, 12:42:15 AM
    Author     : gchavarro88--%>


<%@page import="com.sip.dmes.entitys.ScMachine"%>
<%@page import="com.sip.dmes.entitys.ScFactoryLocation"%>
<%@page import="java.util.List"%>
<%@page import="com.sip.dmes.beans.factoryVisibility.FactoryVisibilityBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
try
{
    FactoryVisibilityBean bean = new FactoryVisibilityBean();
    Long idLocation = new Long(request.getParameter("idLocation"));
    List<ScFactoryLocation> result = (List<ScFactoryLocation>) session.getAttribute("locations");
    String resultMachine = "";
    if(result != null && !result.isEmpty())
    {
        resultMachine +="{\"object\":[ ";
        for(ScFactoryLocation factoryLocation: result)
        {
            if(idLocation.equals(factoryLocation.getIdFactoryLocation()))
            {
                for(ScMachine machine: factoryLocation.getScMachineList())
                {
                    int countObjects = 0;
                    resultMachine +="{\"object"+countObjects+"\":";
                    resultMachine += "{\"object"+ (countObjects++) +"\":\""+machine.getIdMachine()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getName()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getIdState()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getIdPriority().getName()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getMark()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getSerie()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getDescription()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getClasification()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getType()+"\",";   
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getPathPicture()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getHourValue()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getIdCostCenter().getCostCenter()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getIdMoney().getAcronym()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getIdMoney().getTrm()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getIdTime().getAcronym()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getIdTime().getMinutes()+"\",";
                    resultMachine += "\"object"+ (countObjects++) +"\":\""+machine.getUsefulLife()+"\"}},";
                }
            }
            
            
        }
        resultMachine = resultMachine.substring(0, resultMachine.length()-1);
        resultMachine +="]}";
        
    }
    else
    {
        out.print("\'mensaje\':\'No existe ninguna maquina configurada\'");
    }
    out.print(resultMachine);
}
catch(Exception e)
{
    out.print("'mensaje':'Error intentando consultar las localizacones de la fábrica:   "+e.getMessage()+"'");
}
%>
