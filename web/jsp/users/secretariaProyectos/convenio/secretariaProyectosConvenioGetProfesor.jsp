<%-- 
    Document   : secretariaProyectosConvenioGetProfesor
    Created on : 26-05-2021, 9:49:21
    Author     : Ricardo
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<input >
<div id="dummy-rut-jefe-div"><s:property value="#session.genericSession.getWorkSession(key).profesor.profRut"/>-<s:property value="#session.genericSession.getWorkSession(key).profesor.profDv"/></div>
<div id="dummy-nombre-jefe-div"><s:property value="#session.genericSession.getWorkSession(key).profesor.profNombreSimple"/></div>