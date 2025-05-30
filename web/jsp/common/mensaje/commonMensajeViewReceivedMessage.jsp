<%--
    Document   : commonMensajeViewReceivedMessage
    Created on : 07-08-2009, 05:32:26 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Mensaje Recibido</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeViewReceivedMessage-3.0.3.js"></script>
    </head>
    <body class="inner-body">
        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="del-button" title="Eliminar" type="button" class="btn btn-light"  onclick="deleteMsg(); return false;">
                                <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="reply-button" title="Responder" type="button" class="btn btn-light"  onclick="replyMessage(); return false;">
                                <span class="fa fa-pencil"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.reply"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="print-button" title="Imprimir" type="button" class="btn btn-light"  onclick="printMessage();">
                                <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="forward-button" title="Reenviar" type="button" class="btn btn-light"  onclick="forwardMessage(); return false;">
                                <span class="fa fa-paper-plane"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.forward"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form id="view-received-message-form" action="#" accept-charset="UTF-8">                    
            <table class="table">
                <tr>
                    <td><s:text name="label.sentBy"/></td>
                    <td><s:property
                            value="#session.genericSession.getWorkSession(key).currentMsg.msgNombreEnv"/></td>
                </tr>
                <tr>
                    <td><s:text name="label.para"/></td>
                    <td><s:property
                            value="#session.genericSession.getWorkSession(key).currentMsg.getPara()"/></td>
                </tr>
                <tr>
                    <td><s:text name="label.date"/></td>
                    <td><s:date name="#session.genericSession.getWorkSession(key).currentMsg.msgFecha"
                            format="dd/MM/yyyy hh:mm:ss"/></td>
                </tr>
                <tr>
                    <td><s:text name="label.subject"/></td>
                    <td><s:property
                            value="#session.genericSession.getWorkSession(key).currentMsg.msgSubject"/></td>
                </tr>
                <tr>
                    <td><s:text name="label.message"/></td>
                    <td><label>
                            <textarea rows="6" cols="120" style="margin-left: 0; width: 100%"><s:property
                                    value="#session.genericSession.getWorkSession(key).currentMsg.msgMsg"/></textarea>
                        </label>
                    </td>
                </tr>
            </table>
            <table style="width: 98%">
                <s:iterator value="#session.genericSession.getWorkSession(key).currentMsg.mensajeAttachList" status="row">
                    <tr>
                        <td style="width:5%"><span class="fa fa-paperclip"></span>&nbsp;</td>
                        <td><a class="link" href="CommonMensajeDownLoadFile?file=<s:property value="#row.count -1"/>&key=<s:property value="key"/>"><s:property
                                    value="menaAttachFile"/></a></td>
                    </tr>
                </s:iterator>
                <s:if test="#session.genericSession.getWorkSession(key).currentMsg.msgTipo==\"IMG\"">
                    <tr>
                        <td style="width:5%"><span class="fa fa-paperclip"></span>&nbsp;</td>
                        <td><a class="link" href="CommonMensajeDownLoadImg?key=<s:property value="key"/>"><s:property
                                    value="#session.genericSession.getWorkSession(key).currentMsg.msgImagen"/></a>
                        </td>
                    </tr>
                </s:if>
            </table>
            <div id="hidden-input-received-div">
                <input type="checkbox" id="ck_<s:property value="pos"/>" name="ck_<s:property value="pos"/>" style="visibility:hidden;" checked="checked"/>
                <input type="hidden" id="contentDisposition" name="contentDisposition"/>
                <input type="hidden" id="format" name="format" value="PDF"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="tipo" name="tipo" value="R"/>               
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
                        <p><s:text name="confirmation.eliminacion.mensaje"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="removeMsg();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>