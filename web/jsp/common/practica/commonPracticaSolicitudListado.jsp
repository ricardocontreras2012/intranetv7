<%--
    Document   : commonPracticaSolicitudListado
    Created on : 13-09-2010, 08:35:03 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Solicitudesde Práctica</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/practica/commonPracticaSolicitudListado-3.0.0.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.solicitudes"/>
            </div>

            <form id="solicitudes-form" action="#">
                <div class="data-tables-container">
                    <table id="solicitudes-table" class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col" style="width:15%"><s:text name="label.button.alumno"/></th>
                                <th scope="col" style="width:20%"><s:text name="label.tipo.solicitud"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.genericSession.getWorkSession(key).solicitudList" status="row">
                                <tr>
                                    <td><a id="sol1_<s:property value="#row.count -1"/>"><s:property
                                                value="aluCar.alumno.getNombre()"/></a></td>
                                    <td><a id="sol2_<s:property value="#row.count -1"/>"><s:property
                                                value="tsolicitud.tsolDescrip"/></a></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div id="hidden-input-div">
                    <input type="hidden" id="contentDisposition" name="contentDisposition"
                           value='attachment;filename="SOLICITUD.XLS"'/>
                    <input type="hidden" id="format" name="format"/>
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                </div>
            </form>
        </div>
    </body>
</html>
