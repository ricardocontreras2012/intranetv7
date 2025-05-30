<%-- 
    Document   : teleTrabajoAsignacionConsola
    Created on : 07-09-2023, 15:27:57
    Author     : Felipe
--%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consola de Asignar Teletrabajo - Calendario</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/dataTables.bootstrap4.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/asignacion/teleTrabajoAsignacionConsola-1.0.0.js"></script>       
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
            Asignar Actividades &nbsp;<s:property value="#session.teleTrabajoSession.funcionarioTeletrabajo.funcionario.funPaterno"/>&nbsp;<s:property value="#session.teleTrabajoSession.funcionarioTeletrabajo.funcionario.funMaterno"/>&nbsp;<s:property value="#session.teleTrabajoSession.funcionarioTeletrabajo.funcionario.funNombre"/>
        </div>
            <s:form id="actividad-form" action="#" method="post" theme="bootstrap" >
                <div class = "row">
                    <div class="col-lg-12">
                        <div class="form-row">
                            <div class="col-12 col-sm-6 col-md-5 col-lg-2 mb-3">
                                <label for="inicio">Fecha de Inicio</label>
                                <input id="inicio" name="inicio"/>
                            </div>
                            <div class="col-12 col-sm-6 col-md-5 col-lg-2 mb-3">
                                <label for="termino">Fecha de Término</label>
                                <input id="termino" name="termino"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <button id="buscar-button" title="Buscar" type="button" class="btn btn-light" >
                        <span class="fa fa-pencil"></span>&nbsp; <span class="hidden-xs">Buscar</span>
                    </button>
                </div>
                <div id="horario-div" style="height: auto;"></div>
                <div class="row">
                    <div class="col-md-6">
                        <iframe id="actividad" class="embed-responsive-item" src=""
                                allowfullscreen style="height: 30vh; width: 48vw;overflow: auto; border:0"></iframe>
                    </div>
                    <div class="col-md-6">
                        <iframe id="item" class="embed-responsive-item" src=""
                                allowfullscreen style="height: 30vh; width: 48vw;overflow: auto; border:0"></iframe>
                    </div>
                </div>
                <div id="hidden-input-div">
                    <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="titulo" name="titulo" value="FUNCIONARIO: <s:property value="#session.teleTrabajoSession.funcionarioTeletrabajo.ftelRut"/>-<s:property value="#session.teleTrabajoSession.funcionarioTeletrabajo.funcionario.funDv"/>"/>
                    <input type="hidden" id="fecha" name="fecha" value="<s:property value="fecha"/>"/>
                    <input type="hidden" id="inicioSemana" name="inicioSemana" value="<s:property value="inicioSemana"/>"/>
                    <input type="hidden" id="terminoSemana" name="terminoSemana" value="<s:property value="terminoSemana"/>"/>
                    <input type="hidden" id="inicioPos" name="inicioPos" value="<s:property value="inicioPos"/>"/>
                    <input type="hidden" id="terminoPos" name="terminoPos" value="<s:property value="terminoPos"/>"/>
                    <input type="hidden" id="modulo" name="modulo"/>
                    <input type="hidden" id="dia" name="dia"/>
                </div>

                <div class="modal fade" id="detail" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Resumen de Actividad</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div id="detail-div"></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="asignar" role="dialog">
                    <div class="modal-dialog" role="document">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header"> 
                                <h5 class="modal-title"><div id="title_actividad_div"></div></h5>
                                <button id="close-button" type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h5 id="modulo-horario"></h5>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label for="descripcion">Descripcion del teletrabajo</label>
                                            <textarea name="descripcion" id="descripcion" class="form-control" rows="10" /></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <label>Desea asignar teletrabajo para el funcionario y la fecha seleccionada?</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button id="asignar-button" title="Asignar" type="button" class="btn btn-light" >
                                    <span class="fa fa-lock" aria-hidden="true"></span>
                                    &nbsp; <span class="hidden-xs">Asignar Teletrabajo</span>
                                </button>
                                <button id="close-button" type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </s:form>
        </div>
    </body>
</html>