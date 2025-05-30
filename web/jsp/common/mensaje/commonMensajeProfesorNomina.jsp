<%--
    Document   : commonMensajeProfesorNomina
    Created on : 05-02-2010, 03:28:26 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Búsqueda de Profesores</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeNomina-3.0.4.js"></script>
    </head>    
    <body class="inner-body"> 
        <form id="profesor-nomina-form" action="#" method="post" accept-charset="UTF-8">

            <div id="table-div">
                <div class="data-tables-container">
                    <table id="search-table" class="table table-striped table-bordered dataTable">
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
                                            onClick="addProfesor(<s:property value="#row.count -1"/>); return false;"
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
            </div>
            <input id="keyDummy" name="keyDummy" type="hidden" value="<s:property value="key"/>"/>
            <input id="key" name="key" type="hidden" value="<s:property value="key"/>"/>
            <input id="pos" name="pos" type="hidden" />
        </form>   
    </body>
</html>