<%--
    Document   : commonAlumnoAsistencia
    Created on : 11-08-2010, 01:47:54 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Asistencias del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/alumno/commonAlumnoAsistencia-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.asistencia"/>&nbsp;<s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>
        <form id="asistencias-form" action="#">
            <table id="asistencias-table" class="table table-striped" style="width: 30%">
                <thead>
                    <tr>
                        <th scope="col"><s:text name="label.asistencia"/></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).asistenciaAlumnoNominaList" status="row">
                        <tr>
                            <td style="width:60%; text-align: center"><s:date name="asistenciaAlumno.asaFecha"
                                    format="dd/MM/yyyy"/></td>
                            <td style="width:40%">
                                <s:if test="aanAsistio==1"><s:text name="label.asistio"/></s:if>
                                <s:else><s:text name="label.no.asistio"/></s:else>
                                </td>
                            </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>