<%--
    Document   : secretariaDocenteConvalidacionGetExcel.jsp
    Created on : 17-01-2017, 05:09:07 PM
    Author     : Ricardo Contreras S.as
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Carga de Plantilla Excel para Convalidación de Asignaturas </title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/secretariaDocente/convalidacion/secretariaDocenteConvalidacionGetExcel-3.0.0.js"></script>
    </head>
    <body class="inner-body">
       <div class="title-div">
            PLANTILLA DE CONVALIDACIÓN  <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.getNombreStd()"/>
        </div>

        <div id="central-div">
            <s:form action="SecretariaDocenteConvalidacionGetExcel" method="post" enctype="multipart/form-data" theme="bootstrap">
                <s:file name="upload" label=""/>
                <s:submit/>
                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                </div>
            </s:form>
        </div>
    </body>
</html>
