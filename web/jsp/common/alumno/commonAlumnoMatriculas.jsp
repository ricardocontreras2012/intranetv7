<%--
    Document   : commonAlumnoMatriculas
    Created on : 07-06-2009, 01:10:29 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Matriculas del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready.consulta-3.0.0.min.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.matriculas"/>
        </div>

        <table style="width: 25%" class="table table-striped">
            <thead>
                <tr>
                    <th scope="col" style="width:40%"><s:text name="label.term.short"/></th>
                    <th scope="col" style="width:60%"><s:text name="label.year"/></th>
                    <th scope="col" style="width:60%"><s:text name="label.date"/></th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.matriculas" status="row">
                    <tr>
                        <td style="text-align: center">
                            <s:property value="id.mathSem"/>
                        </td>
                        <td style="text-align: center">
                            <s:property value="id.mathAgno"/>
                        </td>
                        <td style="text-align: center">
                            <s:date name="mathFecha" format="dd/MM/yyyy"/>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </body>
</html>