<%--
    Document   : commonAlumnoDatosPersonales
    Created on : 07-06-2009, 01:00:27 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Datos Personales del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">

            <div class="title-div">
                <s:text name="label.title.mis.datos.personales"/>
            </div>

            <table class="table" style="max-width: 500px">
                <s:if test="#session.genericSession.userType in {\"AS\"}">
                <tr>
                    <td style="width:20%">
                        <s:text name="label.fecha.nacimiento"/>
                    </td>
                    <td>
                        <s:date name="#session.genericSession.getWorkSession(key).aluCar.alumno.aluFechaNac" format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:20%">
                        <s:text name="label.direccion"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluDirecAlu"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:text name="label.comuna"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.comNom"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:text name="label.region"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.regionByAluRegionAlu.regNom"/>
                    </td>
                </tr>
                </s:if>
                <tr>
                    <td>
                        <s:text name="label.telefono"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluFonoAlu"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:text name="label.email"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmail"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:text name="label.email.institucional"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmailUsach"/>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>