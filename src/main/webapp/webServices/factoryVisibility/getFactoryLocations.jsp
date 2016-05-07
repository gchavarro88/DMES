<%-- 
    Document   : getFactoryLocations
    Created on : 28/04/2016, 12:42:15 AM
    Author     : gchavarro88--%>


<%@page import="com.sip.dmes.entitys.ScFactoryLocation"%>
<%@page import="java.util.List"%>
<%@page import="com.sip.dmes.beans.factoryVisibility.FactoryVisibilityBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
try
{
    FactoryVisibilityBean bean = new FactoryVisibilityBean();
    List<ScFactoryLocation> result = (List<ScFactoryLocation>) session.getAttribute("locations");
    String factory = "";
    if(result != null && !result.isEmpty())
    {
        factory +="{\"object\":[";
        for(ScFactoryLocation factoryLocation: result)
        {
            
            int countObjects = 0;
            factory +="{\"object"+countObjects+"\":";
            factory += "{\"object"+countObjects+"\":\""+factoryLocation.getIdFactoryLocation()+"\",";
            countObjects++;
            factory += "\"object"+countObjects+"\":\""+factoryLocation.getLocation()+"\",";
            countObjects++;
            factory += "\"object"+countObjects+"\":\""+factoryLocation.getDescription()+"\"}},";
            
        }
        factory = factory.substring(0, factory.length()-1);
        factory +="]}";
        out.print(factory);
    }
    else
    {
        out.print("\'mensaje\':\'No existe ninguna máquina de fábrica configuraifda\'");
    }
    
    
    
}
catch(Exception e)
{
    out.print("'mensaje':'Error intentando consultar las localizacones de la fábrica:   "+e.getMessage()+"'");
}
%>
