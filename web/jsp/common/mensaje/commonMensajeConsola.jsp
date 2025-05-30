<%--
    Document   : commonMensajeConsola
    Created on : 28-10-2009, 12:05:42 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consola de Mensajes</title>
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
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeConsola-3.0.16.js"></script>
    </head>
    <body class="inner-body" style="overflow: hidden">
        <div class="container-fluid d-flex flex-column vh-100">
            <row>
                <div class="btn-group">
                    <button id="nuevo-button" title="Nuevo" type="button" class="btn btn-light"  onclick="newMessage();">
                        <span class="fa fa-pencil"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.redactar"/></span>
                    </button>
                    <button id="del-button" title="Eliminar" type="button" class="btn btn-light"  onclick="deleteMessage();">
                        <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                    </button>
                    <button id="recibidos-button" title="Recibidos" type="button" class="btn btn-light"  onclick="getReceivedMessages();">
                        <span class="fa fa-inbox"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.received"/></span>
                    </button>
                    <button id="sent-button" title="Enviados" type="button" class="btn btn-light"  onclick="getSentMessages();">
                        <span class="fa fa-paper-plane"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.sent"/></span>
                    </button>
                </div>
            </row>
            <row class="overflow-auto">
                <form id="message-form" action="#" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
                    <iframe id="message-iframe" style="height: 90vh; width: 90vw;" frameBorder="0"></iframe>
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="tipo" name="tipo"/>
                </form>
            </row>
        </div>

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
                        <p><s:text name="confirmation.eliminacion.mensaje"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="removeMsgs();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
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
                        <p><s:text name="alert.seleccione.mensaje"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>        
    </body>
</html>