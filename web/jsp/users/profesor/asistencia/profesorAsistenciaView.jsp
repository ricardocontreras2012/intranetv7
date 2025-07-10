<%--
    Document   : profesorAsistenciaView
    Created on : 10-08-2010, 03:30:09 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Detalle de Asistencia</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/reporte/profesorReporteFormulario-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/asistencia/profesorAsistenciaView-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.asistencia"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>

        <s:if test="#session.genericSession.getCurso(key).cursoPropio(#session.genericSession.userType, #session.genericSession.userType, #session.genericSession.rut, #session.genericSession.isAutoridad()) || #session.genericSession.isSecretaria()">
            <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
            </button>
        </s:if>

        <form id="lista-form" action="#" method="post">
            <div id="row">
                <div class="col-12 col-sm-6 col-md-5 col-lg-2 mb-3">
                    <label for="fechaAsistencia"><s:text name="label.date"/></label>
                    <input id="fechaAsistencia" name="fechaAsistencia"
                           value="<s:date name="#session.genericSession.getWorkSession(key).asistenciaAlumno.asaFecha" format="dd/MM/yyyy"/>" />
                </div>
            </div>

            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"><s:text name="label.rut"/></th>
                        <th scope="col"><s:text name="label.paterno"/></th>
                        <th scope="col"><s:text name="label.materno"/></th>
                        <th scope="col"><s:text name="label.name"/></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).asistenciaAlumnoNominaList" status="row">
                        <tr>
                            <td style="text-align: right">
                                <s:property value="#row.count"/>
                            </td>
                            <td>
                                <s:property value="aluCar.alumno.aluRut"/>-<s:property value="aluCar.alumno.aluDv"/>
                            </td>
                            <td>
                                <s:property value="aluCar.alumno.aluPaterno"/>
                            </td>
                            <td>
                                <s:property value="aluCar.alumno.aluMaterno"/>
                            </td>
                            <td>
                                <s:if test="aluCar.alumno.aluNombreSocial != null && aluCar.alumno.aluNombreSocial.trim() != ''">
                                    <s:property value="aluCar.alumno.aluNombreSocial"/>
                                </s:if>
                                <s:else>
                                    <s:property value="aluCar.alumno.aluNombre"/>
                                </s:else>
                            </td>
                            <%--td align="center">
                                <img height="80" id="foto<s:property value="#row.count"/>" src="CommonCursoGetFotoAlumno?pos=<s:property value="#row.count -1"/>&key=<s:property value="key"/>" alt=""/>
                            </td--%>
                            <td style="text-align: center">
                                <s:if test="aanAsistio==0">
                                    <input type="checkbox" name="ck_<s:property value="#row.count -1"/>"
                                           id="ck_<s:property value="#row.count -1"/>"/>
                                </s:if>
                                <s:else>
                                    <input type="checkbox" name="ck_<s:property value="#row.count -1"/>"
                                           id="ck_<s:property value="#row.count -1"/>" checked="checked"/>
                                </s:else>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="checkbox" id="check<s:property value="pos"/>" name="check<s:property value="pos"/>"
                       value="1" checked="checked" style="visibility:hidden;"/>
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
                        <p><s:text name="confirmation.eliminacion.asistencia.actual"/></p>
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