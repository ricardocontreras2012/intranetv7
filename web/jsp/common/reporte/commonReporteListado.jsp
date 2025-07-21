<%--
    Document   : commonReporteListado
    Created on : 15-04-2009, 12:51:30 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado Reportes x Curso</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/reporte/commonReporteListado-3.0.1.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.reportes"/>&nbsp;<s:property value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
            </div>

            <div class="mb-3">
                <div class="btn-group">
                    <s:if test="#session.genericSession.getCurso(key).cursoPropio(#session.genericSession.userType, #session.genericSession.userType, #session.genericSession.rut, #session.genericSession.isAutoridad())">
                            <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                                <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                            </button>
                            <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" >
                                <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                            </button>
                    </s:if>
                    <s:if test="#session.genericSession.userType==\"PR\" || #session.genericSession.isSecretaria() || #session.genericSession.isAutoridad()">
                            <button id="export-button" title="Exportar" type="button" class="btn btn-light" >
                                <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                            </button>
                    </s:if>
                </div>
            </div>

            <form id="reportes-form" action="#">
                <div class="data-tables-container">
                    <table id="reportes-table" class="table table-striped table-bordered dataTable">
                        <thead>
                            <tr>
                                <th scope="col" style="width:12px"><label for="checkHeadInput"></label><input id="checkHeadInput"
                                                                                                              name="checkHeadInput"
                                                                                                              value="off"
                                                                                                              type="checkbox"
                                                                                                              onclick="checkingHead('reportes-form');"/>
                                </th>
                                <th scope="col" style="width:8%"><s:text name="label.sesion"/></th>
                                <th scope="col" style="width:8%;text-align: center"><s:text name="label.date"/></th>
                                <th scope="col" style="width:8%"><s:text name="label.modulo"/></th>
                                <th scope="col"><s:text name="label.contenido"/></th>
                                <th scope="col" style="width:10%"><s:text name="label.recuperacion"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.genericSession.getWorkSession(key).reportes" status="row">
                                <tr>
                                    <td style="text-align: center;width:12px"><input type="checkbox"
                                                                                     id="ck_<s:property value="#row.count -1"/>"
                                                                                     name="ck_<s:property value="#row.count -1"/>"/></td>
                                    <td style="width:4%; text-align: right"><a id="sesion_<s:property value="rclaSesion -1"/>"><s:property
                                                value="rclaSesion"/></a></td>
                                    <td style="padding-left: 25px; width:8%"><a id="fecha_<s:property value="rclaSesion -1"/>"><s:date
                                                name="id.rclaFecClase" format="dd/MM/yy"/></a></td>
                                    <td style="width:8%"><a id="modulo_<s:property value="rclaSesion -1"/>"><s:property
                                                value="id.rclaDia"/><s:property value="id.rclaModulo"/></a></td>
                                    <td><a style="font-weight: normal;" id="cont_<s:property value="rclaSesion -1"/>"><s:property
                                                value="rclaContenido"/></a></td>
                                    <td style="width:10%"><a id="rec_<s:property value="rclaSesion -1"/>"><s:date
                                                name="rclaFecRecupera" format="dd/MM/yyyy"/></a></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div id="hidden-input-div">
                    <input type="hidden" id="sesionReporte" name="sesionReporte" value=""/>
                    <input type="hidden" id="contentDisposition" name="contentDisposition"
                           value='attachment;filename="REPORTES_<s:property value="#session.genericSession.getWorkSession(key).curso.nombreFull"/>."'/>
                    <input type="hidden" id="format" name="format" value="PDF"/>
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                </div>
            </form>
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
                        <p><s:text name="confirmation.eliminacion.reporte"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="remove();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="msg" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Aviso</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <s:text name="alert.seleccione.reporte"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
