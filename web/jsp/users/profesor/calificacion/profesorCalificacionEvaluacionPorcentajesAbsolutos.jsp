<%--
    Document   : profesorCalificacionEvaluacionPorcentajesAbsolutos
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
        <title>Formulario de Definición de Medios Evaluativos x Curso Mediante Porcentajes Absolutos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/calificacion/profesorCalificacionEvaluacionPorcentajesAbsolutos-3.0.4.js"></script>
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
                                <button id="add-button" title="Nueva" type="button" class="btn btn-light"  onclick="addEvaluacion();">
                                    <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.evaluacion"/></span>
                                </button>
                            </div>
                            <div class="btn-group">
                                <button id="del-button" title="Eliminar" type="button" class="btn btn-light"  onclick="deleteEvaluaciones(); return false;">
                                    <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
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

        <div id="new"></div>

        <form id="evaluaciones-form" action="#" method="post">
            <div class="table-responsive">
                <table class="table table-sm table-borderless" id="evaluaciones-table" style="background-color:#BDE5F8;">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Evaluación</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Porc(%)</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody id="evaluaciones-tbody">
                        <s:iterator value="#session.genericSession.getWorkSession(key).cursoTevaluacion" status="row">
                            <s:iterator value="#session.genericSession.getWorkSession(key).evaluacionList" status="roweval">
                                <s:if test="id.evalTeva == tevaluacion.tevalCod">
                                    <tr id="row_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>">
                                        <td style="display: none">
                                            <input
                                                id="sort_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                name="id_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                value="<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"/>
                                        </td>

                                        <td>
                                            <input 
                                                type="checkbox"
                                                   id="ck_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   name="ck_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"/>
                                            <input                                            
                                                type="hidden"
                                                id="id_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                name="id_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                value="id_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"/>
                                        </td>

                                        <td>
                                            <s:property value="tevaluacion.tevalDes"/>
                                        </td>
                                        <td>
                                            <input class="form-control col-2"
                                                   readonly="readonly"
                                                   id="rowCorrel_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   name="rowCorrel_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   type="text"
                                                   value="<s:property value="id.evalEval"/>"
                                                   style="text-align: right"/>
                                        </td>

                                        <td>
                                            <input class="form-control col-12"
                                                   type="text"
                                                   id="label_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   name="label_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   value="<s:property value="evalLabel"/>"
                                                   placeholder="Descripción"
                                                   maxlength="150"
                                                   style="text-align: left"/>
                                        </td>

                                        <td>
                                            <input class="form-control col-2"
                                                   type="text"
                                                   id="porc_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   name="porc_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>"
                                                   value="<s:property value="evalPorc"/>"                                               
                                                   style="text-align: right"
                                                   onkeyup="forceNumber('porc_<s:property value="tevaluacion.tevalCod"/>_<s:property value="id.evalEval"/>');"/>
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
                                </div>
                            </s:iterator>
                        </s:iterator>
                    </tbody>
                </table>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="correl" name="correl" value="<s:property value="correl"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="modalidad" name="modalidad" value=""/>
            </div>
            <div id="medio-div" style="display: none"><s:select id="tipoEvaluacion" name="tipoEvaluacion" headerKey="0" headerValue="-- Seleccione Medio de Evaluación --" list="#session.genericSession.getWorkSession(key).tevaluacion" listKey="tevalCod" listValue="tevalDes" cssClass="input"/>
            </div>
        </form>
        <form id="dummy" action="#" method="post"></form>

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

        <div class="modal fade bs-modal-dialog" id="msg" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="msg-div"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="new-evaluacion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Nueva Evaluación</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="new-evaluacion-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="agregar();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>