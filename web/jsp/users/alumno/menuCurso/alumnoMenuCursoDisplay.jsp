<%--
    Document   : alumnoMenuCursoDisplay
    Created on : 09-08-2020, 13:46:55
    Author     : Ricardo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
    <a href="#" class="dropdown-item" onclick="getMenuCurso(<s:property value="#row.count -1"/>);"><s:property value="nombreFull"/></a>
</s:iterator>
