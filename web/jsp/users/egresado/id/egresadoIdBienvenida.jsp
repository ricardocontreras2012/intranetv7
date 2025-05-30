<%--
    Document   : egresadoIdBienvenida
    Created on : 25-01-2012, 12:46:53 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bienvenida Intranet-Egresado</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-work-iframe.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.layout-1.4.4.min.js"></script>        
    </head>
    <body class="inner-body">
        <div class="ui-layout-north" id="top_work_iframe">
            <div id="layoutdims">
                <s:text name="label.title.bienvenido"/>&nbsp;<s:property value="#session.genericSession.nombre"/>
            </div>
        </div>

        <div class="ui-layout-center">
            <div id="encuestaCNA-div"></div>
        </div>

        <div class="ui-layout-east">
            <div style="width:145px; height:80px">
                <s:text name="label.last.login"/><s:date name="#session.genericSession.egresadoSession.alumno.aluLastLogin"
                                                         format="dd/MM/yyyy HH:mm:ss"/>
            </div>
        </div>

        <form id="dummy-form" action="#">
            <div>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>
