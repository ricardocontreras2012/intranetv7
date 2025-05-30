<%--
    Document   : commonSalaReservaCurso
    Created on : 09-07-2018, 17:36:53
    Author     : rcontreras
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:property value="#session.genericSession.getWorkSession(key).curso.getNombreFull()"/>
<p></p>
<s:property value="#session.genericSession.getWorkSession(key).curso.getCurProfesores()"/>
<p></p>
<s:property value="#session.genericSession.getWorkSession(key).curso.getCurHorario()"/>
