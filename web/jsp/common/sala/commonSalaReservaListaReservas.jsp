<%--
    Document   : commonSalaReservaListaReservas
    Created on : 03-08-2010, 09:02:46 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Reservas</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/sala/commonSalaReservaListaReservas-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            RESERVA SALAS
        </div>

        <s:if test="actionCall==null">
            <div class="container container-menu">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <div class="btn-group">
                                <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                                    <span class="fa fa-pencil"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                                </button>
                            </div>
                            <div class="btn-group">
                                <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" >
                                    <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                                </button>
                            </div>                           
                        </div>
                    </div>
                </div>
            </div>
        </s:if>

        <form id="reservas-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="reservas-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th></th>
                            <th scope="col">Id</th>
                            <th scope="col">Sala</th>
                            <th scope="col">Módulo</th>
                            <th scope="col">Inicio</th>
                            <th scope="col">Término</th>
                            <th scope="col">Motivo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).reservaList" status="row">
                            <tr>
                                <td style="width:5%"><input style="height: 12px" type="checkbox"
                                                            id="ck_<s:property value="#row.count -1"/>"
                                                            name="ck_<s:property value="#row.count -1"/>"
                                                            /></td>
                                <td><s:property value="rsalCorrel"/></td>
                                <td><s:property value="sala.salaNum"/></td>
                                <td><s:property value="rsalDia"/><s:property value="rsalModulo"/></td>
                                <td><s:property value="rsalFechaInicio"/></td>
                                <td><s:property value="rsalFechaTermino"/></td>
                                <td><s:property value="rsalMotivo"/></td>                                
                            </tr>  
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div class="modal fade" id="confirmacion" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p><s:text name="confirmation.eliminacion.reserva"/></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="remove();">OK</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <div id="hidden-input-div">               
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>
