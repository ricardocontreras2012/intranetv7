<%--
    Document   : profesorCalificacionEvaluacionPorcentajesRelativos
    Created on : 21-11-2009, 08:11:15 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Definición de Medios Evaluativos x Curso Porcentajes Relativos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/calificacion/profesorCalificacionEvaluacionPorcentajesRelativos-3.0.4.js"></script>
    </head>
    <body class="inner-body">
        <s:set var="puedeCalificar"
               value="#session.genericSession.getWorkSession(key).curso.puedePonerNotasParciales"/>
        <div class="title-div">
            <s:text name="label.title.evaluacion.parciales"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>


        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <s:if test="#puedeCalificar==true">
                            <div class="btn-group">
                                <button id="save-button" title="Grabar" type="button" class="btn btn-light"  onclick="saveForm();">
                                    <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                                </button>
                            </div>
                            <div class="btn-group">
                                <button id="add-button" title="Nueva" type="button" class="btn btn-light"  onclick="addTipoEvaluacion();">
                                    <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.nuevo.medio.evaluacion"/></span>
                                </button>
                            </div>
                        </s:if>

                        <div class="btn-group">
                            <button id="export-button" title="Exportar" type="button" class="btn btn-light"  onclick="exportPlanilla();">
                                <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.excel"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="change-button" title="Cambiar Modalidad de Evaluación" type="button" class="btn btn-light"  onclick="changeModalidad();">
                                <span class="fa fa-recycle"></span>&nbsp; <span class="hidden-xs">Cambiar Modalidad</span>
                            </button>
                        </div>
                        <s:if test="#session.genericSession.getWorkSession(key).curso.getPuedeEmitir()==true">
                            <div class="btn-group">
                                <button id="emitir-button" title="Emitir Acta Final" type="button" class="btn btn-light"  onclick="confirmarEmision();">
                                    <span class="fa fa-lock"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.emitir"/></span>
                                </button>
                            </div>
                        </s:if>
                    </div>
                </div>
            </div>
        </div>

        <div id="new">
        </div>
        <div id="tipos" style="visibility: hidden"><s:select id="tipoEvaluacion" name="tipoEvaluacion" headerKey="0"
                  headerValue="-- Seleccione Medio de Evaluación --"
                  list="#session.genericSession.getWorkSession(key).tevaluacion"
                  listKey="tevalCod" listValue="tevalDes"
                  cssClass="input"/></div>
        <form id="evaluaciones-form" action="#" method="post">
            <s:iterator value="#session.genericSession.getWorkSession(key).cursoTevaluacion" status="row">
                <div id="eval" style="overflow: hidden; width: 99%">
                    <table style="background-color:#BDE5F8;">
                        <tr>
                            <td style="text-align: center; background-color: #679FD2; color:white" valign="top"
                                colspan="2">
                                <s:property value="tevaluacion.tevalDes"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:30%;" colspan="2">


                                <div class="container container-menu">
                                    <div class="row">
                                        <div id="justified-button-bar" class="col-lg-12">
                                            <div class="btn-group">
                                                <div class="btn-group">
                                                    <button id="add-button" title="Nueva" type="button" class="btn btn-light"  onclick="addRow(<s:property value="tevaluacion.tevalCod"/>, '<s:property value="tevaluacion.tevalDes"/>', '');return false;">
                                                        <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.evaluacion"/></span>
                                                    </button>
                                                </div>
                                                <div class="btn-group">
                                                    <button id="del-button" title="Eliminar" type="button" class="btn btn-light"  onclick="deleteEvaluaciones(<s:property value="tevaluacion.tevalCod"/>);return false;">
                                                        <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:20%">
                                <input name="nom_tbody_<s:property value="tevaluacion.tevalCod"/>"
                                       id="nom_tbody_<s:property value="tevaluacion.tevalCod"/>" type="hidden"
                                       value="<s:property value="tevaluacion.tevalDes"/>"/><s:text
                                    name="label.porcentaje.nota.final"/>&nbsp;<input class=""
                                             name="porcTbody_<s:property value="tevaluacion.tevalCod"/>"
                                             id="porcTbody_<s:property value="tevaluacion.tevalCod"/>"
                                             type="text"
                                             value="<s:property value="ctevaPorc"/>"
                                             style="text-align: right"
                                             size="3"/>
                            </td>
                            <td style="width:80%">
                            </td>
                        </tr>
                    </table>
                    <table id="evaluaciones-table_<s:property value="tevaluacion.tevalCod"/>" style="background-color:#BDE5F8; margin-top:-5px">
                        <tbody id="tbody_<s:property value="tevaluacion.tevalCod"/>">
                            <s:iterator value="#session.genericSession.getWorkSession(key).evaluacionList" status="roweval">
                                <s:if test="id.evalTeva == tevaluacion.tevalCod">
                                    <tr id="row_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>">
                                        <td style="width:5%"><input type="checkbox"
                                                                    id="ck_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                                    name="ck_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"/><input
                                                                    type="hidden"
                                                                    id="id_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                                    name="id_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                                    value="id_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"/>
                                        </td>
                                        <td style="width:40%"><s:property
                                                value="tevaluacion.tevalDes"/><input readonly="readonly"
                                                   id="rowCorrel_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   name="rowCorrel_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   type="text"
                                                   value="<s:property value="id.evalEval"/>"
                                                   size="3" style="text-align: right"/>
                                        </td>
                                        <td><s:text name="label.porcentaje"/><input class="" type="text"
                                                                                    id="porc_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                                                    name="porc_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                                                    value="<s:property value="evalPorc"/>"
                                                                                    size="3"
                                                                                    style="text-align: right"/>%
                                        </td>
                                        <td>
                                            <s:if test="#puedeCalificar==true">
                                                <button id="save-button" title="Notas" type="button" class="btn btn-light"  onclick="showNominaCurso(<s:property value="#roweval.count -1"/>);return false;"><span class="fa fa-list-ol" aria-hidden="true"></span>
                                                    &nbsp; <span class="hidden-xs"><s:text name="label.button.evaluate"/></span>
                                                </button>
                                            </s:if>
                                        </td>
                                    </tr>
                                </s:if>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </s:iterator>
            <div id="hidden-input-div">
                <input type="hidden" id="type" name="type" value="<s:property value="type"/>"/>
                <input type="hidden" id="correl" name="correl" value="<s:property value="correl"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="tipoTmp" name="tipoTmp"/>
                <input type="hidden" id="modalidad" name="modalidad" value=""/>
            </div>
        </form>
        <form id="dummy" action="#" method="post"></form>


        <div class="modal fade bs-modal-dialog" id="msg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="msgModalLabel">Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"><div id="msg-div"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="confirmacion-del" role="dialog">
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
                        <p><s:text name="confirmation.eliminacion.evaluacion"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="deleteRows();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="confirmacion-acta" role="dialog">
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
                        <button type="button" class="btn btn-light" onclick="emitir();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>