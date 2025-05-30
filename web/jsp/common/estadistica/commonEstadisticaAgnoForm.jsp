<%--
    Document   : commonEstadisticaAgnoForm
    Created on : 27-06-2011, 12:08:59 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Estadistica Calidad</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/estadistica/commonEstadisticaAgnoForm-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.estadisticas.titulados"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>

        <div id="central-div">
            <button id="excel-button" title="Consultar" type="button" class="btn btn-light" ><span class="fa fa-search" aria-hidden="true"></span>
                &nbsp; <span class="hidden-xs"><s:text name="label.consultar"/></span>
            </button>

            <form id="estadistica-form" action="#">
                <label for="agno"><s:text name="label.year"/>&nbsp;Titulados&nbsp;</label><input id="agno" name="agno"
                                                                                                 size="4" class="form-control input-sm input-agno"/>

                <div id="hidden-input-div">
                    <input type="hidden" id="calidad" name="calidad" value="4"/>
                    <input type="hidden" id="contentDisposition" name="contentDisposition"
                           value="<s:property value="contentDisposition"/>"/>
                </div>
            </form>
        </div>
    </body>
</html>