<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">     
        <title>Porcentajes Relativos</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-help.css">
        <link rel="stylesheet" href="/intranetv7/css/lightbox/lightbox.min.css">
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
    </head>
    <body class="inner-body">
        <div id="content">
            <h3>Cómo puedo definir una estructura de evaluación relativa?</h3>
            <p></p>
            <p>Por defecto se le mostrará al inicio la programación de 2 PEP's la cual Ud. podrá modificar de acuerdo a sus necesidaes evaluativas.</p>
            <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/001.png" data-lightbox="screen"><img src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/001.png"></a>
            <p>Suponga que Ud. quiere realizar 2 PEPs y 3 Controles de manera que la PEPs son el 70% y los controles 30% de la nota final  y por otro lado se quiere los siguientes porcentajes relativos respecto el tipo de evaluación:</p>
            <p>PEP #1 40%</p>
            <p>PEP #2 60%</p>
            <p>Control #1 20%</p>
            <p>Control #2 40%</p>
            <p>Control #3 40%</p>
            <p>Primero cambie los porcentajes predefinidos</p>
            <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/002.png" data-lightbox="screen"><img src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/002.png"></a>
            <p>Luego presione botón Nuevo Medio</p>
            <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/003.png" data-lightbox="screen"><img src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/003.png"></a>
            <p>Seleccione Control e ingrese el porcentaje de nota final (30%) y la cantidad de controles (3). Presione luego el botón Agregar de la derecha. </p>
            <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/004.png" data-lightbox="screen"><img src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/004.png"></a>
            <p>Con esta operación ha terminado de definir los porcentajes</p>
            <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/005.png" data-lightbox="screen"><img src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/005.png"></a>
            <p>Finalmente presione el botón Grabar y ya tendrá disponible la estructura de evaluaciones para que inicie el registro de la calificaciones.</p>
            <a href="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/006.png" data-lightbox="screen"><img src="/intranetv7/jsp/help/<s:property value="#session.genericSession.getCurrentAction()"/>/006.png"></a>
            <p></p>
            <p>Todos los porcentajes son números enteros y sea los relativos de cada tipo de evaluación como también los que ponderan los tipos de evaluación respecto la nota final.</p>
            <p>Ud. puede cambiar su estructura las veces que estime conveniente e incluso cambiar a la modalidad relativa presionando el botón Relativa</p>
            <p>Puede agregar por ejemplo un nuevo control presionando el botón Nuevo dentro de la sección Control de su estructura de evaluaciones.</p>
            <p>El botón Planilla le permite obtener una visión completa de todas las evaluaciones con las respectivas calificaciones de los alumnos del curso.</p>
        </div>
    </body>
    <script type="text/javascript" src="/intranetv7/js/lightbox/lightbox.js"></script>
</html>