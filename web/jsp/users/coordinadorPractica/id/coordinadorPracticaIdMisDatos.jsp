<%--
    Document   : coordinadorPracticaIdMisDatos
    Created on : 20-08-2011, 12:50:09 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Mis Datos Personales Coordinador Practicas Laborales</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-message-bar.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/coordinadorPractica/id/coordinadorPracticaIdMisDatos-2.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="buttons-div">
            <button id="save-button" name="save-button" class="positive">
                <img src="/intranetv7/images/local/icon/disk.png" height="16" width="16" alt="password" title="Cambiar Password"/>
                <s:text name="label.button.save"/>
            </button>
        </div>
        <div id="content-div">
            <form id="consulta-form" action="#">
                <table>
                    <tr>
                        <td style="width: 75%">
                            <table cellspacing="3" cellpadding="5">
                                <tr>
                                    <td style="width: 25%"><s:text name="label.paterno"/></td>
                                    <td><s:property value="#session.genericSession.consultaSession.externo.extPaterno"/></td>
                                </tr>
                                <tr>
                                    <td><s:text name="label.materno"/></td>
                                    <td><s:property value="#session.genericSession.consultaSession.externo.extMaterno"/></td>
                                </tr>
                                <tr>
                                    <td><s:text name="label.name"/></td>
                                    <td><s:property value="#session.genericSession.consultaSession.externo.extNombre"/></td>
                                </tr>
                                <tr>
                                    <td><label for="email"><s:text name="label.email"/></label></td>
                                    <td>
                                        <input type="text" id="email" name="email" size="57"
                                               value="<s:property value="#session.genericSession.consultaSession.externo.extEmail"/>"/>
                                    </td>
                                </tr>
                            </table>
                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <div class="errorBox">
                                    <s:actionerror/>
                                    <s:actionmessage/>
                                    <s:fielderror/>
                                </div>
                            </s:if>
                        </td>
                        <td style="vertical-align: top"><img id="foto"
                                                             src="CommonGetFoto?key=<s:property value="key"/>&rut=<s:property value="#session.genericSession.rut"/>"
                                                             alt="<s:property value="#session.genericSession.rut"/>"
                                                             height="125" width="110"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>