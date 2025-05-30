<%--
    Document   : commonPersona
    Created on : 10-06-2011, 10:46:47 PM
    Author     : Ricardo Contreras S.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%--s:if test="#session.genericSession.getWorkSession(key).persona != null">
    <label for="nombre-persona"></label><input id="nombre" name="nombre-persona" size="100" style="text-transform: uppercase;"
                                       readonly="readonly"
                                       value="<s:property value="#session.genericSession.getWorkSession(key).persona.getNombre()"/>"/>
</s:if>
<s:else>
    <label for="nombre"></label><input id="nombre" name="nombre-persona" size="100" style="text-transform: uppercase;"/>
</s:else--%>

<s:property value="#session.genericSession.getWorkSession(key).persona.perFull"/>
