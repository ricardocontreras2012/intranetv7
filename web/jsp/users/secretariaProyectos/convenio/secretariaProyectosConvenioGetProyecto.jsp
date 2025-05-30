<%-- 
    Document   : secretariaProyectosConvenioGetProyecto
    Created on : 25-05-2021, 22:32:18
    Author     : Ricardo
--%>


<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input >
<div id="dummy-rut-jefe-div"><s:property value="#session.proyectoSession.proyecto.jefe.profRut"/>-<s:property value="#session.proyectoSession.proyecto.jefe.profDv"/></div>
<div id="dummy-nombre-jefe-div"><s:property value="#session.proyectoSession.proyecto.jefe.profNombreSimple"/></div>