<%--
    Document   : secretariaDocenteGraduacionTitulacionActaForm
    Created on : 16-05-2014, 11:59:11 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>ACTA</title>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/secretariaDocente/graduacionTitulacion/secretariaDocenteGraduacionTitulacionActaForm-2.0.0.js"></script>
    </head>
    <body class="inner-body">
        <h1>Hello World!</h1>
        <button onclick="imprimirActaExamen();">holasss</button>
        <form id="acta-form">
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>
