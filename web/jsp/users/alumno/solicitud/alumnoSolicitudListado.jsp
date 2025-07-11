<%--
    Document   : alumnoSolicitudListado
    Created on : 13-09-2010, 08:35:03 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Solicitudes</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/alumnoSolicitudListado-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.solicitudes"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                                <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="del-button" title="Eliminar" type="button" class="btn btn-light" >
                                <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form id="solicitudes-form" action="#">
            <div class="data-tables-container">
                <table id="solicitudes-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col" style="width:10%; text-align: center"><label for="checkHeadInput"></label><input style="height: 12px"
                                                                                                                             id="checkHeadInput"
                                                                                                                             name="checkHeadInput"
                                                                                                                             type="checkbox"
                                                                                                                             onclick="checkingHead('solicitudes-form');"/>
                            </th>
                            <th scope="col" style="width:10%"><s:text name="label.tipo.solicitud"/></th>
                            <th scope="col" style="width:50%"><s:text name="label.solicita"/></th>
                            <th scope="col" style="width:20%"><s:text name="label.estado"/></th>
                            <th scope="col" style="width:20%"><s:text name="label.resolucion"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).solicitudList" status="row">
                            <tr>
                                <td style="width: 5%; text-align: center;">
                                    <input style="height: 12px" type="checkbox"
                                           id="ck_<s:property value="#row.count -1"/>"
                                           name="ck_<s:property value="#row.count -1"/>"/></td>

                                <td><a id="sol2_<s:property value="#row.count -1"/>"><s:property
                                            value="tsolicitud.tsolDescrip"/></a></td>
                                <td><a id="sol3_<s:property value="#row.count -1"/>"><s:property
                                            value="solSolicita"/></a></td>
                                <td><a id="sol4_<s:property value="#row.count -1"/>"><s:property
                                            value="estadoSolicitud.esolDes"/></a></td>
                                <td><a id="sol5_<s:property value="#row.count -1"/>"><s:property value="getResolucion()"/></a></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="contentDisposition" name="contentDisposition"
                           value='attachment;filename="SOLICITUD.XLS"'/>
                    <input type="hidden" id="format" name="format"/>
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                </div>
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
                        <p><s:text name="confirmation.eliminacion.solicitud"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="remove();">SI</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">NO</button>
                    </div>
                </div>
            </div>
        </div>

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
                        <p><s:text name="alert.seleccione.solicitud"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
