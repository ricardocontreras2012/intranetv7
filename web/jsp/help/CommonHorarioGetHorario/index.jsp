<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>¿Cómo puedo ver mi horario de clases?</title>
    <
    <link rel="stylesheet" href="/intranetv7/css/local/local-help.css">
    <link rel="stylesheet" href="/intranetv7/css/lightbox/lightbox.min.css">
    <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
</head>
<body class="inner-body">
<div id="content">
    <h3>¿Cómo puedo ver mi horario de clases?</h3>
    <p>Para ver su horario de clases, una vez que haya ingresado a la Intranet, en el menú lateral izquierdo haga click
        en el vínculo <strong>Horario</strong> ubicado bajo el Menú Mis Cursos.</p>
    <p>Aparecerá el horario de clases para el año y semestre en curso. Para cada curso se muestra el código de la
        asignatura, coordinación, sección, año y semestre en que se imparte.</p>
    <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/001.png"
       data-lightbox="screen"><img
            src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/001.png"></a>
    <p>Haciendo click sobre una celda podrá ver la sala correspondiente.</p>
    <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/002.png"
       data-lightbox="screen"><img
            src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/002.png"></a>
    <p>Dado que algunos nombres de algunas asignaturas son demasiados largo, abajo del horario se muestran los nombres
        completos.</p>
    <p>Si desea imprimir el horario haga click en el botón Imprimir ubicado en la parte superior del horario, se
        mostrará su horario en formato PDF.</p>
</div>
<script type="text/javascript" src="/intranetv7/js/lightbox/lightbox.js"></script>
</body>
</html>