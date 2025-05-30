<%--
    Document   : profesorActaRectificatoriaActaNumerica
    Created on : 18-abr-2014
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Emisión de Acta Rectificatoria con Calificación Numérica</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/calificacion/profesorCalificacionActaRectificatoriaNumerica-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.acta"/> &nbsp;<s:property value="#session.genericSession.getWorkSession(key).curso.getNombreFull()"/>
        </div>

        <button id="emitir-button" title="Emitir" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.button.emitir"/></span>
        </button>

        <form id="acta-form" action="#" method="post">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col"><s:text name="label.nro"/></th>
                        <th scope="col"><s:text name="label.rut"/></th>
                        <th scope="col"><s:text name="label.name"/></th>
                        <th scope="col" style="width:10%; text-align:center"><s:text name="label.nota"/></th>
                        <th scope="col" style="text-align:center"><s:text name="label.situacion"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).actaRectificatoriaList" status="row">
                        <tr>
                            <td style="text-align:right;width:5%"><s:property value="#row.count"/>&nbsp;&nbsp;</td>
                            <td style="text-align:right; width:10%"><s:property
                                    value="aluCar.alumno.aluRut"/>-<s:property
                                    value="aluCar.alumno.aluDv"/>&nbsp;&nbsp;</td>
                            <td><s:property value="aluCar.alumno.aluPaterno"/> <s:property
                                    value="aluCar.alumno.aluMaterno"/> <s:property
                                    value="aluCar.alumno.aluNombre"/></td>

                            <td style="width:10%; text-align:center;align-content: center">
                                <input style="width:60%;" size="3"
                                       id="nota_<s:property value="aluCar.alumno.aluRut"/>"
                                       name="nota_<s:property value="aluCar.alumno.aluRut"/>" type="text"
                                       maxlength="3" class="form-control <s:if test="calSitAlu == \"A\"">aprobado</s:if><s:else>reprobado</s:else>"
                                       value="<s:if test="calNotaFin != null"><s:text name="format.calificacion"><s:param name="value" value="calNotaFin"/></s:text></s:if>"/>
                            </td>
                            <td style="text-align:center">
                                           <span style="text-align:center; font-weight: bold;"
                                                 class="<s:if test="calSitAlu == \"A\"">aprobado</s:if><s:else>reprobado</s:else>"> <s:property value="calSitAlu"/></span>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="contentDisposition" name="contentDisposition"
                       value='attachment;filename="NOTAS_FINALES_<s:property value="#session.genericSession.getWorkSession(key).curso.nombreFull"/>.XLS"'/>
                <input type="hidden" id="format" name="format" value=""/>
                <input type="hidden" id="puedeEmitirDummy" name="puedeEmitirDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).puedeEmitir"/>"/>
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
                        <p><s:text name="confirmation.emision.acta"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="emite();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>