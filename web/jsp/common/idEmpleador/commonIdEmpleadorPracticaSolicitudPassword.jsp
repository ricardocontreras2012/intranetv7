<%--
    Document   : commonIdEmpleadorPracticaSolicitudPassword
    Created on : 15-04-2010, 11:41:01 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Formulario de Solicitud Nueva Password del Empleador</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-login.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-message-bar.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/idEmpleador/commonIdEmpleadorPracticaSolicitudPassword-2.0.1.min.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.solicitud.de.password"/>
        </div>

        <div id="central-div">
            <div id="dialog-form-div" title="SOLICITUD DE PASSWORD">
                <form id="login-form" action="#">
                    <fieldset>
                        <label for="usuarios"><s:text name="label.tipo.usuario"/></label>
                        <s:select name="usuarios"
                                  id="usuarios"
                                  headerKey="-1"
                                  headerValue="-- Seleccione Tipo de Usuario --"
                                  list="userTypeMap"
                                  cssClass="input"
                                  />
                        <p>&nbsp;</p>
                        <label for="rutdv"><s:text name="label.rut"/></label>
                        <input id="rutdv" name="rutdv" type="text" size="16" class="text ui-widget-content ui-corner-all"/>
                    </fieldset>
                    <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                        <div class="errorMessage">
                            <s:actionerror/><s:actionmessage/><s:fielderror/>
                        </div>
                    </s:if>
                    <div id="hidden-input-div">
                        <input type="hidden" id="rut" name="rut" value=""/>
                        <input type="hidden" id="userType" name="userType"
                               value="<s:property value="userType"/>"/>
                    </div>
                </form>
            </div>
        </div>
        <div id="msg-div" title="<s:text name="message.title.msg"/>"><s:text name="alert.seleccione.tipo.usuario"/></div>
    </body>
</html>