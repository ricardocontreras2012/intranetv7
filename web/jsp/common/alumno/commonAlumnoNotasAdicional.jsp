<%--
    Document   : commonAlumnoNotasAdicional
    Created on : 14-12-2010, 05:26:25 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" />

<table>
    <thead>
    <tr>
        <th style="color:white">
            <s:text name="label.nota"/>
        </th>
        <th style="color:white">
            <s:text name="label.term.short"/> <s:text name="label.year"/>
        </th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.calificacionLogroAdicionalList" status="row">
        <tr>
            <s:if test="calSitAlu ==\"A\"">
                <td style="height: 16px; background-color: white; color: blue"><s:property value="calNota"/><s:property
                        value="calConcep"/></td>
                <td style="height: 16px; background-color: white; color: blue"><s:property value="id.calSem"/>
                    <s:property value="id.calAgno"/></td>
            </s:if>
            <s:else>
                <td style="height: 16px; background-color: white; color: red"><s:property value="calNota"/><s:property
                        value="calConcep"/></td>
                <td style="height: 16px; background-color: white; color: red"><s:property value="id.calSem"/>
                    <s:property value="id.calAgno"/></td>
            </s:else>
        </tr>
    </s:iterator>
    </tbody>
</table>