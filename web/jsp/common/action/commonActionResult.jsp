<%--
    Document   : commonActionResult
    Created on : 23-04-2021, 19:33:27
    Author     : Ricardo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="result-status-div">
     <s:property value="#session.genericSession.getWorkSession(key).actionSupport.actionStatus"/>
</div>

<div id="result-error-div">
     <s:property value="#session.genericSession.getWorkSession(key).actionSupport.actionErrorMsg"/>
</div>

<div id="result-success-div">
     <s:property value="#session.genericSession.getWorkSession(key).actionSupport.actionResult"/>
</div>