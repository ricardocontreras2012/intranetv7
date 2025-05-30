<%--
    Document   : commonCursoGetProfesorSaved
    Created on : 29-10-2020, 16:28:03
    Author     : Ricardo
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:property value="#session.genericSession.getWorkSession(key).curso.curProfesores"/>
