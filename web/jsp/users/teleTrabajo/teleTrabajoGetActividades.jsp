<%--
    Document   : teleTrabajoGetActividades
    Created on : 29-09-2023, 13:22:46 PM
    Author     : Felipe
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
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/teleTrabajoGetActividades-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            Lista de Actividades &nbsp;<s:property value="#session.teleTrabajoSession.funcionarioTeletrabajo.funcionario.funPaterno"/>&nbsp;<s:property value="#session.teleTrabajoSession.funcionarioTeletrabajo.funcionario.funMaterno"/>&nbsp;<s:property value="#session.teleTrabajoSession.funcionarioTeletrabajo.funcionario.funNombre"/>
        </div>

        <s:if test="#session.teleTrabajoSession.isTeletrabajoJefe(#session.genericSession.rut) && #session.teleTrabajoSession.funcionarioTeletrabajo.ftelRut != #session.genericSession.getRut">
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
                            <div class="btn-group">
                                <button id="check-button" title="Terminar" type="button" class="btn btn-light" >
                                    <span class="fa fa-check"></span>&nbsp; <span class="hidden-xs">Terminar</span>
                                </button>
                            </div>
                            <div class="btn-group">
                                <button id="close-button" title="Revisión" type="button" class="btn btn-light" >
                                    <span class="fa fa-close"></span>&nbsp; <span class="hidden-xs">Revisión</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:if>

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
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.teleTrabajoSession.actividadList" status="row">
                                <tr>                                
                                    <td style="width:5%"><input style="height: 12px" type="checkbox"
                                                                id="ck_<s:property value="#row.count -1"/>"
                                                                name="ck_<s:property value="#row.count -1"/>"
                                                                /></td>
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
                                </tr>  
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
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
                            <p><s:text name="confirmation.eliminacion.actividad"/></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="removeActividades();">OK</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="hidden-input-div">               
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos"/>
                <input type="hidden" id="estado" name="estado"/>
            </div>
        </form>
        <div class="modal fade" id="aviso" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Seleccione una o más actividades</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
