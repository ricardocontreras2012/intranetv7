<%--
    Document   : commonEmpleadoresxNombre
    Created on : 27-jul-2017, 12:54:47
    Author     : Álvaro Romero C.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<select name="empleadorLista" id="empleadorLista" size="10" class="input" style="height:130px; width:100%">
    <s:iterator value="#session.genericSession.getWorkSession(key).empleadorList" status="row">
        <option value="<s:property value="empRut"/>"><s:property value="empRut"/>-<s:property value="empDv"/> -  <s:property value="empNombre"/></option>
    </s:iterator>
</select>
