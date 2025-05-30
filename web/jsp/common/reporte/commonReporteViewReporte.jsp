<%--
    Document   : commonReporteViewReporte
    Created on : 15-05-2009, 08:50:28 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Detalle de Reporte de Clases</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/reporte/commonReporteViewReporte-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.reporte"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>


        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <s:if test="#session.genericSession.userType==\"PR\"  || #session.genericSession.isSecretaria() || #session.genericSession.isAutoridad()">
                            <div class="btn-group">
                                <button id="export-button" title="Exportar" type="button" class="btn btn-light" >
                                    <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                                </button>
                            </div>
                        </s:if>
                        <s:if test="#session.genericSession.getCurso(key).cursoPropio(#session.genericSession.userType, #session.genericSession.userType, #session.genericSession.rut, #session.genericSession.isAutoridad())">
                            <div class="btn-group">
                                <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" >
                                    <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                                </button>
                            </div>
                        </s:if>
                        <s:if test="#session.genericSession.getCurso(key).cursoPropio(#session.genericSession.userType, #session.genericSession.userType, #session.genericSession.rut, #session.genericSession.isAutoridad())">
                            <div class="btn-group">
                                <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                    <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                                </button>
                            </div>
                        </s:if>
                    </div>
                </div>
            </div>
        </div>

        <form id="reporte-form" accept-charset="UTF-8" enctype="multipart/form-data" action="#" method="post">
            <table>
                <tr>
                    <td style="width: 10%"><s:text name="label.date"/></td>
                    <td style="width: 60%"><s:date name="#session.genericSession.getWorkSession(key).reporte.id.rclaFecClase"
                            format="dd/MM/yyyy"/></td>
                </tr>
                <tr>
                    <td><s:text name="label.modulo"/></td>
                    <td><s:property value="#session.genericSession.getWorkSession(key).reporte.id.rclaDia"/><s:property
                            value="#session.genericSession.getWorkSession(key).reporte.id.rclaModulo"/></td>
                </tr>
                <tr>
                    <td><label for="objetivos"><s:text name="label.objetivos"/></label></td>
                    <td><textarea name="objetivos" id="objetivos" rows="3" cols="120" class="form-control"><s:property
                                value="#session.genericSession.getWorkSession(key).reporte.rclaObApren"/></textarea></td>
                </tr>
                <tr>
                    <td><label for="contenido"><s:text name="label.contenido"/></label></td>
                    <td><textarea name="contenido" id="contenido" rows="3" cols="120" class="form-control"><s:property
                                value="#session.genericSession.getWorkSession(key).reporte.rclaContenido"/></textarea></td>
                </tr>
                <tr>
                    <td><label for="metodo"><s:text name="label.metodo"/></label></td>
                    <td><textarea name="metodo" id="metodo" rows="3" cols="120" class="form-control"><s:property
                                value="#session.genericSession.getWorkSession(key).reporte.rclaMetodo"/></textarea></td>
                </tr>
                <tr>
                    <td><label for="observaciones"><s:text name="label.observaciones"/></label></td>
                    <td><textarea name="observaciones" id="observaciones" rows="3" cols="120" class="form-control"><s:property
                                value="#session.genericSession.getWorkSession(key).reporte.rclaObs"/></textarea></td>
                </tr>
                <tr>
                    <td><s:text name="label.archivo.attach"/>
                    </td>
                    <td><a class="link" href="CommonReporteDownLoadAttach?key=<s:property value="key"/>"> <s:property
                                value="#session.genericSession.getWorkSession(key).reporte.rclaAttach"/></a></td>
                </tr>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="sesionReporte" name="sesionReporte"
                       value="<s:property value="sesionReporte"/>"/>
                <input type="checkbox"
                       id="ck_<s:property value="#session.genericSession.getWorkSession(key).reporte.rclaSesion -1"/>"
                       name="ck_<s:property value="#session.genericSession.getWorkSession(key).reporte.rclaSesion -1"/>"
                       value="<s:property value="#session.genericSession.reporte.rclaSesion"/>" checked="checked"
                       style="visibility:hidden;"/>
                <input type="hidden" id="contentDisposition" name="contentDisposition"
                       value='attachment;filename="REPORTE_SESION_<s:property value="#session.genericSession.getWorkSession(key).reporte.rclaSesion"/>_CURSO_<s:property value="#session.genericSession.getWorkSession(key).curso.nombreFull"/>.XLS"'/>
                <input type="hidden" id="format" name="format"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="fechaActual" name="fechaActual"
                       value="<s:property value="%{#session.genericSession.getWorkSession(key).fechaActual}"/>"/>
            </div>
        </form>

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
                        <p><s:text name="confirmation.eliminacion.reporte.actual"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="remove();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>