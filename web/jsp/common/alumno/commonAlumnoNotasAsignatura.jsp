<%--
    Document   : commonAlumnoNotasAsignatura
    Created on : 14-06-2009, 04:43:42 PM
    Author     : Ricardo Contreras S.
--%>
<!--!DOCTYPE html-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!--link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" /-->

<table class="table">
    <thead>
    <tr>
        <th style="color:white">
            <s:text name="label.curso"/>
        </th>
        <th style="color:white">
            <s:text name="label.nota"/>
        </th>
        <s:if test="electiva== \"S\"">
            <th><s:text name="label.name"/></th>
        </s:if>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.calificacionesAsignatura" status="row">
        <tr>
            <td style="height: 16px; background-color: white; color: blue"><s:property value="calCoord"/>-<s:property
                    value="calSecc"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="id.calSem"/>/<s:property
                    value="id.calAgno"/></td>
            <s:if test="calSitAlu ==\"A\"">
                <s:if test="procedenciaCalificacion.procCod in {\"CON\",\"CCN\"}">
                    <td style="height: 16px; background-color: white; text-align: center; color: blue"><s:text
                            name="label.convalidada"/></td>
                </s:if>
                <s:else>
                    <td style="height: 16px; background-color: white; text-align: center; color: blue"><s:property value="calNotaFin"/>
                        <s:property value="calConcep"/></td>
                </s:else>
            </s:if>
            <s:else>
                <td style="height: 16px; background-color: white; text-align: center; color: red"><s:property value="calNotaFin"/>
                    <s:property value="calConcep"/></td>
            </s:else>
            <s:if test="electiva== \"S\"">
                <td style="height: 16px; background-color: white; text-align: center; color: blue"><s:property value="nombreFull"/></td>
            </s:if>
        </tr>
    </s:iterator>
    </tbody>
</table>