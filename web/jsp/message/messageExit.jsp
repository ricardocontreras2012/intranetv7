<%--
    Document   : messageExit
    Created on : 21-09-2009, 01:12:12 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Aviso: Mensaje de Fin de Sesión</title>
        <meta http-equiv="Refresh" content="1; url=http://<s:property value="urlRedirect"/>"/>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
    </head>
    <body class="inner-body">
        <p class="labelMessage"><s:text name="message.logout"/></p>
    </body>
</html>
