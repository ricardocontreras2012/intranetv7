<%--
    Document   : registradorCurricularActaNominaGeneradas
    Created on : 02-11-2010, 02:31:29 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Actas Generadas</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/acta/registradorCurricularActaNominaGeneradas-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.menu.actas"/>
        </div>

        <div id="central-div">
            <form id="actas-form" action="#">
                <div id="table-div">
                    <table id="actas-table" class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col"><s:text name="label.folio"/></th>
                                <th scope="col"><s:text name="label.asignatura"/></th>
                                <th scope="col"><s:text name="label.electividad"/></th>
                                <th scope="col"><s:text name="label.coordinacion"/></th>
                                <th scope="col"><s:text name="label.seccion"/></th>
                                <th scope="col"><s:text name="label.name"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.registradorSession.nominaActaViewList" status="row">
                                <tr>
                                    <td><s:property value="acalFolio"/></td>
                                    <td><s:property value="acalAsign"/></td>
                                    <td><s:property value="acalElect"/></td>
                                    <td><s:property value="acalCoord"/></td>
                                    <td><s:property value="acalSecc"/></td>
                                    <td><s:property value="asign"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </body>
</html>