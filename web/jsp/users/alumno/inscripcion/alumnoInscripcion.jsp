<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Inscripción</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-inscripcion.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/inscripcion/alumnoInscripcion-3.1.1.js"></script>        
    </head>
    <body class="inner-body">

        <form id="inscripcion-form" action="#" method="post">
            <!-- Bloque superior de botones -->
            <div class="container container-menu" style="margin-bottom: 5px; margin-top: 10px; padding-bottom: 0px">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <div class="btn-group">
                                <button id="print-button" title="Imprimir" type="button" class="btn btn-light" onClick="printInscripcion()">
                                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                                </button>
                            </div>

                            <s:if test="response.flags.puedeEliminar==\"OK\"">
                                <div class="btn-group">
                                    <button id="delete-button" title="Desinscribir Cursos Seleccionados" type="button" class="btn btn-light" onClick="deleteInscripcion();
                                            return false;">
                                        <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.desinscribir"/></span>
                                    </button>
                                </div>
                            </s:if>

                            <s:if test="response.flags.puedeModificar==\"OK\"">
                                <div class="btn-group">
                                    <button id="change-button" title="Cambiar curso" type="button" class="btn btn-light" onClick="getCursosSwap();
                                            return false;">
                                        <span class="fa fa-recycle"></span>&nbsp; <span class="hidden-xs">Cambiar de Curso</span>
                                    </button>
                                </div>
                            </s:if>
                        </div>
                    </div>
                </div>
            </div>  

            <div class="table-responsive">
                <table class="table table-sm table-striped">
                    <thead>
                        <tr class="header-table">
                            <th scope="col" style="width: 5%"></th>
                            <th scope="col" style="width: 50%"><s:text name="label.curso"/></th>
                            <th scope="col" style="width: 10%"><s:text name="label.horario"/></th>
                            <th scope="col" style="width: 30%"><s:text name="label.profesor"/></th>
                            <th scope="col" style="width: 5%"></th>
                            <th scope="col" style="display: none"></th>
                        </tr>
                    </thead>
                    <tbody>

                        <s:iterator value="response.inscripciones" status="row">
                            <tr>
                                <s:if test="(id.insRut == insRutReali || insForce != 'F') && (response.flags.puedeEliminar==\"OK\" || response.flags.puedeModificar==\"OK\")">                               
                                    <td class="align-middle text-center" style="width: 5%; padding: 0;">
                                        <input class="form-check-input" type="checkbox" name="ck_<s:property value="#row.count -1"/>"
                                               id="ck_<s:property value="#row.count -1"/>"/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td style="width: 5%"></td>
                                </s:else>                                                    
                                <td style="width: 50%">
                                    <input type="hidden" name="<s:property value="#row.count -1"/>_insAsign" value="<s:property value='id.insAsign' />" />
                                    <input type="hidden" name="<s:property value="#row.count -1"/>_insElect" value="<s:property value='id.insElect' />" />
                                    <input type="hidden" name="<s:property value="#row.count -1"/>_insCoord" value="<s:property value='insCoord' />" />
                                    <input type="hidden" name="<s:property value="#row.count -1"/>_insSecc" value="<s:property value='insSecc' />" />
                                    <input type="hidden" name="<s:property value="#row.count -1"/>_insAgno" value="<s:property value='id.insAgno' />" />
                                    <input type="hidden" name="<s:property value="#row.count -1"/>_insSem" value="<s:property value='id.insSem' />" />
                                    <input type="hidden" name="<s:property value="#row.count -1"/>_insComp" value="<s:property value='id.insComp' />" />
                                    <s:property value="id.insAsign"/> 
                                    <s:property value="id.insElect"/>
                                    <s:property value="insCoord"/>
                                    <s:property value="insSecc"/>
                                    <s:property value="curso.curNombre"/>
                                </td>
                                <td style="width: 10%"><s:property value="curso.curHorario"/></td>
                                <td style="width: 30%"><s:property value="curso.curProfesores"/></td>
                                <td><img
                                        onclick="showProfesor('<s:property value="curso.curProfesores.trim().replace('/ /g','0')"/>', <s:property
                                            value="#row.count -1"/>, 'inscripcion');"
                                        style="cursor: pointer"
                                        id="profesor_<s:property value="#row.count -1"/>_<s:property value="curso.curProfesores.trim().replace('/ /g','0')"/>"
                                        src="/intranetv7/images/local/icon/user.png" height="16" width="16" alt="prof"/></td>
                                <td style="display: none"><span id="force_<s:property value="#row.count -1"/>"><s:property value="insForce"/></span></td>

                            </tr>
                        </s:iterator>
                        <tr style="background-color: #679FD2;">
                            <td colspan="2" style="width: 55%; color: #fff;">Asignaturas de Malla: Créditos Inscritos <s:property
                                    value="response.totales.creditos"/>
                                &nbsp;&nbsp;Sct Inscritos <s:property
                                    value="response.totales.sct"/>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div id="msg-dummy" name="msg-dummy" style="display: none;"><s:actionerror/></div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="status" name="status" value="<s:property value="response.status"/>"/>
                <input type="hidden" id="message" name="message" value="<s:property value="response.message"/>"/>
            </div>
        </form>

        <div class="modal fade" id="inscripcion" role="dialog">
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
                        <div id="msg"></div>
                        <div id="error" class="error"></div>                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>        
    </body>
</html>
