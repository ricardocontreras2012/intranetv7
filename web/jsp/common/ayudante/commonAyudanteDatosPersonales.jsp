<%--
    Document   : commonAyudanteDatosPersonales
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
        <title>Datos Personales del Ayudante</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.mis.datos.personales"/>
        </div>

        <div id="central-div">
            <table>
                <tr>
                    <td style="width:20%">
                        <s:text name="label.direccion"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuDirec"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:text name="label.comuna"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).ayudante.comuna.comNom"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:text name="label.region"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).ayudante.regionByAyuRegion.regNom"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:text name="label.telefono"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuFono"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:text name="label.email"/>
                    </td>
                    <td>
                        <s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuEmail"/>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>