<%--
    Document   : profesorSolicitudListado
    Created on : 13-09-2010, 08:35:03 PM
    Authors     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Solicitudes</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.solicitudes"/> de Justificativo Prueba PEP
        </div>

        <s:form id="solicitudes-form" action="#" theme="bootstrap">
            <div class="data-tables-container">
                <table id="solicitudes-table" class="table table-striped dataTable" style="width:100%">
                    <thead>
                        <tr>
                            <th scope="col" style="width:5%"><s:text name="label.nro"/></th>
                            <th scope="col" style="width:55%"><s:text name="label.name"/></th>
                            <th scope="col" style="width:20%">Fecha Inicio</th>
                            <th scope="col" style="width:20%">Fecha Tértmino</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).solicitudList" status="row">
                            <tr>
                                <td>
                                    <s:property value="solFolio"/>
                                </td>
                                <td>
                                    <s:property value="aluCar.alumno.getNombre()"/>
                                </td>
                                <td>
                                    <s:date name="solFechaInicio" format="dd/MM/yyyy"/>
                                </td>   
                                <td>                                    
                                    <s:date name="solFechaTermino" format="dd/MM/yyyy"/>                                 
                                </td>                                   
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">                
                <input type="hidden" id="format" name="format"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
            </div>
        </s:form>
    </body>
</html>
