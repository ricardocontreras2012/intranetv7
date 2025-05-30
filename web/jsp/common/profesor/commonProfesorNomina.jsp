<%--
    Document   : commonProfesorNomina
    Created on : 07-06-2009, 07:05:29 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Nómina de Profesores de la Búsqueda</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.nomina-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/profesor/commonProfesorNomina-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.resultado.busqueda"/>.
        </div>

        <div class="data-tables-container">
            <table id="nomina-table" class="table table-striped table-bordered dataTable">
                <thead>
                    <tr>
                        <th scope="col" style="width: 15%"><s:text name="label.rut"/></th>
                        <th scope="col"><s:text name="label.paterno"/></th>
                        <th scope="col"><s:text name="label.materno"/></th>
                        <th scope="col"><s:text name="label.name"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).profesorList" status="row">
                        <tr>
                            <td style="width: 15%; text-align: right"><a
                                    id="profesor_<s:property value="#row.count -1"/>"><s:property value="profRut"/>-<s:property
                                        value="profDv"/></a></td>
                            <td><s:property value="profPat"/></td>
                            <td><s:property value="profMat"/></td>
                            <td><s:property value="profNom"/></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
        <form id="profesores-form" action="#">
            <fieldset>
                <input type="hidden" id="typeSearchDummy" name="typeSearchDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).typeSearch"/>"/>
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
            </fieldset>
        </form>
    </body>
</html>