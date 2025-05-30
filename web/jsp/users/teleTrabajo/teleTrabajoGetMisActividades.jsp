<%--
    Document   : teleTrabajoGetMisActividades
    Created on : 29-09-2023, 13:22:46 PM
    Author     : Felipe and Javier
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Actividades</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />       
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>     
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/teleTrabajoGetMisActividades-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            Lista de Actividades &nbsp;
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="check-button" title="Aceptar" type="button" class="btn btn-light" >
                                <span class="fa fa-check"></span>&nbsp; <span class="hidden-xs">Aceptar</span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="close-button" title="Rechazar" type="button" class="btn btn-light" >
                                <span class="fa fa-close"></span>&nbsp; <span class="hidden-xs">Rechazar</span>
                            </button>
                        </div>                           
                    </div>
                </div>
            </div>
        </div>

        <form id="actividades-form" action="#" method="post">
            <div class="container-fluid overflow-auto">
                <div class="data-tables-container container-fluid" style="margin: 0; padding: 0;">
                    <table id="actividades-table" class="display responsive table table-striped table-bordered dataTable" style="overflow-y: auto">
                    <thead>
                        <tr>
                            <th></th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Estado</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.teleTrabajoSession.actividadList" status="row">
                            <tr>                                
                                <td style="width:5%">
                                    <input style="height: 12px" type="checkbox" id="ck_<s:property value="#row.count -1"/>" name="ck_<s:property value="#row.count -1"/>" />
                                </td>
                                <td>
                                    <s:if test="atelEstado == \"C\" || atelEstado == \"R\"">
                                        <s:property value="id.atelFecha"/>
                                    </s:if>
                                    <s:else>
                                        <a id="act_<s:property value="#row.count -1"/>"><s:property value="id.atelFecha"/></a>
                                    </s:else>
                                </td> 
                                <td><s:property value="atelDes"/></td>
                                <td>
                                    <div title="<s:property value="estado.eatFullDes"/>">
                                        <s:property value="estado.eatDes"/>
                                    </div>
                                </td>
                                <td>
                                    <s:if test="atelEstado == \"E\"">
                                        <button type="button" title="Enviar" onClick="enviarActividad(<s:property value="#row.count -1"/>)" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-paper-plane"></span>&nbsp; <span class="hidden-xs"></span></button>
                                    </s:if>
                                </td>
                            </tr>  
                        </s:iterator>
                    </tbody>
                    </table>
                </div>
            </div>
            <div id="hidden-input-div">               
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos"/>
                <input type="hidden" id="estado" name="estado"/>
            </div>
        </form>
        <div class="modal fade" id="AvisoConfirmacionAceptacion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloModalAceptacion" class="modal-title"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="mensajeConfirmacionAceptacion"></p>
                    </div>
                    <div class="modal-footer">
                        <button id="botonAceptacion" type="button" class="btn btn-light" data-dismiss="modal">Acepto</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="AvisoConfirmacionRechazo" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloModalRechazo" class="modal-title"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="mensajeConfirmacionRechazo"></p>
                    </div>
                    <div class="modal-footer">
                        <button id="botonRechazo" type="button" class="btn btn-light" data-dismiss="modal">Rechazo</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
