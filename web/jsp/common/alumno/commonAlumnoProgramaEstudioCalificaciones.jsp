<%--
    Document   : commonAlumnoProgramaEstudioCalificaciones
    Created on : 09-06-2009, 02:19:44 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Programa de Estudio: Calificaciones</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/alumno/commonAlumnoProgramaEstudioCalificaciones-3.0.0.js"></script>
    </head>
    <body class="inner-body">

        <div class="title-div">
            <s:text name="label.title.programas"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="print-button" title="Imprimir Programas" type="button" class="btn btn-light"  onclick="printProgramas();return false;">
                                <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs">Imprimir</span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <form id="calificacion-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="calificacion-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col" style="text-align:center; width: 12px"><label for="checkHeadInput"></label><input
                                    id="checkHeadInput" name="checkHeadInput"
                                    type="checkbox"
                                    onclick="checkingHead('calificacion-form');"
                                    value="off"/></th>
                            <th scope="col"><s:text name="label.code"/></th>                       
                            <th scope="col"><s:text name="label.asignatura"/></th>                        
                            <th scope="col"><s:text name="label.electivo"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).calificaciones" status="row">
                            <tr>
                                <td style="text-align:center; width: 12px"><input type="checkbox"
                                                                                  id="ck_<s:property value="#row.count -1"/>"
                                                                                  name="ck_<s:property value="#row.count -1"/>"/></td>
                                <td style="text-align:right; width: 10%"><s:property value="id.calAsign"/>
                                    <s:property value="id.calElect"/></td>                            
                                <td><s:property value="asignatura.asiNom"/></td>                                                        
                                <td><s:property value="electivo.eleNom"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
            
            <div class="modal fade" id="aviso" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="alert.seleccione.asignatura"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>