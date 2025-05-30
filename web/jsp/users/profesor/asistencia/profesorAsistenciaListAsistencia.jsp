<%--
    Document   : profesorAsistenciaListAsistencia
    Created on : 09-08-2010, 08:30:25 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Asistencias x Curso</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/asistencia/profesorAsistenciaListAsistencia-3.0.0.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.asistencia"/> <s:property
                    value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
            </div>

            <div class="mb-3">
                <div class="btn-group" role="group">
                    <s:if test="#session.genericSession.getCurso(key).cursoPropio(#session.genericSession.userType, #session.genericSession.userType, #session.genericSession.rut, #session.genericSession.isAutoridad()) || #session.genericSession.isSecretaria()">
                        <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                            <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                        </button>
                        <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" >
                            <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                        </button>
                    </s:if>

                    <button id="planilla-button" title="Planilla" type="button" class="btn btn-light" >
                        <span class="fa fa-table"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.planilla"/></span>
                    </button>
                </div>
            </div>


            <s:form id="asistencias-form" action="#">
                <div class="data-tables-container">
                    <table id="asistencias-table" class="table table-striped table-bordered dataTable">
                        <thead>
                            <tr>
                                <th scope="col"><label for="checkHeadInput"></label><input
                                        style="height: 12px"
                                        id="checkHeadInput"
                                        name="checkHeadInput" type="checkbox"
                                        onclick="checkingHead('asistencias-form');"/>
                                </th>
                                <th scope="col" style="width:95%"><s:text name="label.asistencia"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.genericSession.getWorkSession(key).asistenciaAlumnoList" status="row">
                                <tr>
                                    <td style="width:5%"><input style="height: 12px" type="checkbox"
                                                                id="ck_<s:property value="#row.count -1"/>"
                                                                name="ck_<s:property value="#row.count -1"/>"
                                                                /></td>
                                    <td><a id="asistencia_<s:property value="#row.count -1"/>"><s:date name="asaFecha"
                                                                                 format="dd/MM/yyyy"/></a>
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div id="hidden-input-div">
                    <input type="hidden" id="pos" name="pos"/>
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                </div>
            </s:form>
        </div>

        <div class="modal fade" id="confirmacion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="confirmation.eliminacion.asistencia"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="remove();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="aviso" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="mySmallModalLabel" class="modal-title">Aviso</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <s:text name="alert.seleccione.asistencia"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>