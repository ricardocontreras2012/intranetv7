<%--
    Document   : commonMensajeNewMessage
    Created on : 28-10-2009, 12:50:00 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Mensaje</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />        
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-message-bar.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeNewMessage-3.0.9.js"></script>
    </head>
    <body class="inner-body" style="overflow: hidden">

        <style type="text/css">
            label.error {
                color: red;
                vertical-align: top
            }
            input:valid {
                border: 1px solid green;
            }
        </style>


        <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
            <div class="errorBox">
                <s:actionerror/><s:actionmessage/><s:fielderror/>
            </div>
        </s:if>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="send-button" title="Enviar" type="button" class="btn btn-light"  onclick="sendMessage();return false;">
                                <span class="fa fa-envelope-o" aria-hidden="true"></span>&nbsp; <span class="hidden-xs"><s:text name="label.enviar"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="attach-button" title="Adjuntar" type="button" class="btn btn-light"  onclick="addAttach();  return false;">
                                <span class="fa fa-paperclip" aria-hidden="true"></span>&nbsp; <span class="hidden-xs">Adjuntar</span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form id="new-mensaje-form" action="#" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
            <table class="table">
                <tr>
                    <td style="width:15%;"><s:text name="label.para"/></td>
                    <td style="width:85%;"><s:property
                            value="#session.genericSession.getWorkSession(key).mensajeSupport.para"/></td>
                </tr>
                <tr>
                    <td style="width:15%;"><label for="subject"><s:text name="label.subject"/></label></td>
                    <td style="width:85%;">
                        <input class="form-control" name="subject" id="subject" type="text" size="110" maxlength="250"
                               value="<s:property value="#session.genericSession.getWorkSession(key).mensajeSupport.subject"/>"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:15%;"><label for="messageText"><s:text name="label.message"/></label></td>
                    <td style="width:85%">
                        <s:if test="#session.genericSession.getMessagePlus() == \"S\"">
                            <input type="radio" name="tipo" value="TXT" />
                            <br>
                            <label for="messageText">TEXTO:</label><textarea class="form-control" name="messageText" id="messageText" cols="110" rows="8" readonly="readonly">
                            </textarea>
                            <br><br><br>
                            <input type="radio" name="tipo" value="HTML" />
                            <br>
                            <label for="url">URL:</label><input class="form-control" type="text" id="url" name="url" placeholder="Dirección WEB" cols="110" readonly="readonly">
                            <br>
                            <textarea class="form-control" name="messageHtml" id="messageHtml" cols="110" rows="8" readonly="readonly">
                            </textarea>
                            <br><br><br>
                            <input type="radio" name="tipo" value="IMG" />
                            <br>
                            <div id="image-div">
                                <label for="imagen">ARCHIVO IMAGEN:</label>
                                <s:file class="form-control" id="imagen" name="imagen" label="" size="45"/>
                            </div>
                            <br>

                        </s:if>
                        <s:else>
                            <textarea class="form-control" name="messageText" id="messageText" cols="110" rows="8" maxlength="3500">
                            </textarea>
                            <input type="hidden" id="tipo" name="tipo" value="TXT" />
                        </s:else>
                    </td>
                </tr>
            </table>
            <table id="attach-table">
            </table>
            <input type="hidden" id="flag" name="flag" value="<s:property value="#session.genericSession.getMessagePlus()"/>"/>
            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
        </form>
    </body>
</html>
