<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">      <title>Mis Cursos</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-help.css">
        <link rel="stylesheet" href="/intranetv7/css/lightbox/lightbox.min.css">
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
    </head>
    <body class="inner-body">
        <div id="content">
            <h3>Mis cursos</h3>
            Posicione el puntero del mouse sobre el curso deseado y presione el botón.
            <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/001.png" data-lightbox="screen"><img src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/001.png"></a>
        </div>
    </body>
    <script type="text/javascript" src="/intranetv7/js/lightbox/lightbox.js"></script>
</html>