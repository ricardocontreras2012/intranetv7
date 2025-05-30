<%--
    Document   : commonAlumnoCalidades
    Created on : 07-06-2009, 01:10:15 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Calidades del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.calidades"/>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col"><s:text name="label.calidad"/></th>
                    <th scope="col"><s:text name="label.term.short"/> <s:text name="label.year"/></th>
                    <th scope="col"><s:text name="label.date"/></th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.calidades" status="row">
                    <tr>
                        <td><s:property value="tcalidad.tcaDescrip"/></td>
                        <td style="width:30%"><s:property value="id.ccaSem"/> <s:property value="id.ccaAgno"/></td>
                        <td style="width:15%"><s:date name="ccaFecha" format="dd/MM/yyyy"/></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </body>
</html>