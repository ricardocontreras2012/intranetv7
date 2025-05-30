<%--
    Document   : commonSolicitudEstados
    Created on : 18-07-2017, 13:49:02
    Author     : Ricardo Contreras S.as
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<label class="col-sm-3 col-form-label">Seleccione el estado:</label>
<div class="col-sm-6">
    <s:select
        id="estadoList"
        name="estadoList"
        list="#session.genericSession.getListaEstadoSolicitud()"
        listKey="esolCod"
        listValue="esolDes"
        cssClass="form-control w-50"
        onchange="verSolicitudes()"/>
</div>
