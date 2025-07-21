<%--
    Document   : commonComunaComunas
    Created on : 27-08-2010, 04:59:13 PM
    Author     : Ricardo Contreras S.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:select
        id="comuna"
        name="comuna"
        list="comunaList"
        listKey="comCod"
        listValue="comNom"
        headerKey=""
        headerValue="Seleccione Comuna"
        cssClass="form-select"/>
