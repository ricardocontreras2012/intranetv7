<%--
    Document   : commonSalaReservaContainer
    Created on : 01-12-2010, 10:46:55 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consola de Consulta de Salas - Calendario</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/sala/commonSalaReservaContainer-3.0.4.js"></script>       
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                Reserva de Salas
            </div>
            <s:form id="salas-form" action="#" method="post" theme="bootstrap" >
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

                <div id="lista-salas-div"></div>
                <div id="horario-div" style="height: auto;"></div>
                <div id="hidden-input-div">
                    <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="sala" name="sala"
                           value="<s:property value="#session.genericSession.getWorkSession(key).sala.salaNum"/>"/>
                    <input type="hidden" id="modulo" name="modulo"/>
                    <input type="hidden" id="dia" name="dia"/>
                </div>

                <div class="modal fade" id="detail" role="dialog">
                    <div class="modal-dialog" role="document">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Uso de Sala</h5>
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

                <div class="modal fade" id="reserva" role="dialog">
                    <div class="modal-dialog" role="document">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header"> 
                                <h5 class="modal-title"><div id="title_sala_div"></div></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h5 id="modulo-horario"></h5>
                                <div class = "row">
                                    <div class="col-lg-12">
                                        <div class="form-row">
                                            <div class="col-12 col-sm-6 col-lg-6 mb-3">
                                                <label for="inicioReserva">Fecha de Inicio</label>
                                                <input id="inicioReserva" name="inicioReserva"/>
                                            </div>
                                            <div class="col-12 col-sm-6 col-lg-6 mb-3">
                                                <label for="terminoReserva">Fecha de Término</label>
                                                <input id="terminoReserva" name="terminoReserva"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label for="motivo">Motivo de la Reserva</label>
                                            <textarea name="motivo" id="motivo" class="form-control" rows="10" /></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div id="reserva-div"></div>
                            </div>
                            <div class="modal-footer">
                                <button id="reservar-button" title="Reservar" type="button" class="btn btn-light" >
                                    <span class="fa fa-lock" aria-hidden="true"></span>
                                    &nbsp; <span class="hidden-xs">Reservar</span>
                                </button>
                                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </s:form>
        </div>
    </body>
</html>