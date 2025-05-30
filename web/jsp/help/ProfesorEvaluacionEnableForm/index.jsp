<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <meta charset="utf-8" />      <title>Cómo puedo administrar las notas parciales?</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-help.css">
        <link rel="stylesheet" href="/intranetv7/css/lightbox/lightbox.min.css">
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
    </head>
    <body class="inner-body">
        <div id="content">
            <h3>Cómo puedo administrar las notas parciales?</h3>
            <p>La opción de Evaluaciones Parciales permite registrar las calificaciones de los distintos medios evaluativos que Ud. a seleccionado para un curso en particular. </p>
            <p>Estas evaluaciones son visibles por parte del alumno de manera quye cada alumno ve solo sus propias calificaciones en el momemto que Ud. las registra.</p>
            <p>También al final del período lectivo Ud. podrá emitir el acta final a partir de las evaluaciones parciales en forma directa. </p>
            <p></p>
            <p>Las evaluaciones requieren la definición de una estructura evaluativa ya sea que cada porcentaje de una evaluación parcial sea parte directa de la evaluación final(estructura absoluta) o sea parte del medio evaluativo al cual pertenece (avaluación relativa)</p>
            <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/001.png" data-lightbox="screen"><img src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/001.png"></a>
            <p></p>
            <p>Suponga que Ud. quiere hacer 2 PEP's y 3 controles de manera que:</p>
            <p>PEP #1 30%<p>
            <p>PEP #2 30%<p>
            <p>Control #1 10%<p>
            <p>Control #2 15%<p>
            <p>Control #3 15%<p>
            <p>En este caso su estructura es absoluta, es decir si suma todos porcentajes de las evaluaciones tiene 100% en forma directa.</p>
            <p></p>
            <p>Por otro, suponga que Ud. desea definir la nota final del curso por porecentajes de los medios evaluativos, como por ejemplo:</p>
            <p>PEP 60%</p>
            <p>Controles 20%</p>
            <p>Trabajos 20%</p>
            <p>En este caso su estructura es relativa, es decir la suma de los porcentajes de medios evaluaciones es 100% independiente de cuantas evaluaciones ded cada medio se realicen.</p>
            <p>A su vez la suma todos porcentajes de las evaluaciones parciales de cada medio debe sumar 100%.</p>
        </div>
    </body>
    <script type="text/javascript" src="/intranetv7/js/lightbox/lightbox.js"></script>
</html>