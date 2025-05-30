<%-- 
    Document   : teleTrabajoGetMisTareas
    Created on : 01-10-2023, 18:08:13
    Authors     : Ricardo and Javier
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Tareas</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />        
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>       
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/teleTrabajoGetMisTareas-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            Lista de Tareas de actividad &nbsp;<s:property value="#session.teleTrabajoSession.actividadTeletrabajo.id.atelFecha"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <s:if test="#session.teleTrabajoSession.actividadTeletrabajo.atelEstado == \"A\" || #session.teleTrabajoSession.actividadTeletrabajo.atelEstado == \"E\"">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                                <span class="fa fa-pencil"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                            </button>
                            <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" >
                                <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                            </button>
                        </div>
                    </div>
                </s:if>
            </div>
        </div>

        <form id="tareas-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="tareas-table" class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th></th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.teleTrabajoSession.tareaList" status="row">
                            <tr>
                                <s:if test="#session.teleTrabajoSession.isTeletrabajoFuncionario(#session.genericSession.rut)">
                                    <td style="width:5%"><input style="height: 12px" type="checkbox"
                                                                id="ck_<s:property value="#row.count -1"/>"
                                                                name="ck_<s:property value="#row.count -1"/>"
                                                                /></td>
                                    </s:if>
                                    <s:else>
                                    <td></td>
                                </s:else>
                                <td>
                                    <a id="act_<s:property value="#row.count -1"/>">
                                        <s:property value="tatelDes"/>
                                    </a>
                                </td>
                                <s:if test="tatelEvidencia == \"S\"">
                                    <td style="width:2%">
                                        <img src="/intranetv7/images/local/icon/attachment.png" height="16" width="16" alt="attach"/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td style="width:2%"></td>
                                </s:else>
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
                            <p><s:text name="confirmation.eliminacion.tarea"/></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="removeTareas();">OK</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="hidden-input-div">               
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
            </div>
        </form>
        <div class="modal fade" id="aviso" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Seleccione una o más tareas</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
