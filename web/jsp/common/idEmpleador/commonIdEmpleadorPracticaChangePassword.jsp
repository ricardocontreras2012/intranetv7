<%--
    Document   : commonIdPlusChangePassword
    Created on : 23-03-2010, 03:24:47 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Formulario de Cambio de Password del Empleador</title>
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
    </head>
    <body class="inner-body">
        <div class="buttons-div">
            <button id="save-button" name="save-button" class="positive">
                <img src="/intranetv7/images/local/icon/disk.png" height="16" width="16" alt="save"/>
                <s:text name="label.button.save"/>
            </button>
        </div>
        <form id="cambioPassword-form" action="#">
            <table>
                <tr>
                    <td style="width:25%"><label for="passwdActual"><s:text
                                name="label.password.actual"/></label></td>
                    <td><input type="password" id="passwdActual" name="passwdActual" size="16"/></td>
                </tr>
                <tr>
                    <td><label for="passwdNueva"><s:text name="label.password.nueva"/></label></td>
                    <td><input type="password" id="passwdNueva" name="passwdNueva" size="16"/></td>
                </tr>
                <tr>
                    <td><label for="passwdConfirm"><s:text name="label.confirmacion.password"/></label></td>
                    <td><input type="password" id="passwdConfirm" name="passwdConfirm" size="16"/></td>
                </tr>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="actionDummy" name="actionDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).actionCall"/>"/>
            </div>
        </form>
        <div id="msg-div" title="<s:text name="error.title"/>"><s:actionerror cssClass="actionError"/></div>
    </body>
</html>