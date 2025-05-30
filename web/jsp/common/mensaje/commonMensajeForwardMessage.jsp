<%--
    Document   : commonMensajeForwardMessage
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
        <title>REenvio de Mensaje</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-message-bar.css" type="text/css" />
        <style type="text/css">
            label.error {
                color: red;
                vertical-align: top
            }
        </style>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeForwardMessage-3.0.3.js"></script>
    </head>    
    <body class="inner-body">
        <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
            <div class="errorBox">
                <s:actionerror/><s:actionmessage/><s:fielderror/>
            </div>
        </s:if>

        <button id="send-button" title="Enviar" type="button" class="btn btn-light"  onClick="sendForwardMessage();return false;"><span class="fa fa-envelope-o" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.enviar"/></span>
        </button>

        <div id="content-div">
            <s:form id="message-fwd-form" enctype="multipart/form-data" action="#" method="post" theme="bootstrap">
                <table class="table">
                    <tr>
                        <td style="width:15%;"><s:text name="label.para"/></td>
                        <td style="width:85%;"><s:property
                                value="#session.genericSession.getWorkSession(key).mensajeSupport.para"/></td>
                    </tr>
                    <tr>
                        <td style="width:15%;"><label for="subject"><s:text name="label.subject"/></label></td>
                        <td style="width:85%;">
                            <input name="subject" id="subject" type="text" size="110"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).mensajeFwd.msgSubject"/>" class="input-group"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:15%;"><label for="messageText"><s:text
                                    name="label.message"/></label></td>
                        <td style="width:85%">
                            <textarea name="messageText" id="messageText" cols="110" rows="8" class="input-group"><s:property
                                    value="#session.genericSession.getWorkSession(key).mensajeFwd.msgMsg"/>
                            </textarea>
                        </td>
                    </tr>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                </div>
            </s:form>
        </div>
    </body>
</html>