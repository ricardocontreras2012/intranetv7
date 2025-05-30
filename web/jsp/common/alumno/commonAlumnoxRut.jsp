<%--
    Document   : commonAlumnoxRut
    Created on : 19-06-2011, 06:48:58 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="#session.genericSession.getWorkSession(key).alumno != null">
    <label for="nombre"></label><input id="nombre" name="nombre" size="100" style="text-transform: uppercase;"
                                       readonly="readonly"
                                       value="<s:property value="#session.genericSession.getWorkSession(key).alumno.getNombre()"/>"/>
</s:if>
<s:else>
    <label for="nombre"></label><input id="nombre" name="nombre" size="100" style="text-transform: uppercase;"/>
</s:else>
