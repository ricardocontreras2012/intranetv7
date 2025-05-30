<%--
    Document   : asistenteGetCalificacionesSearch
    Created on : 16-10-2023, 04:23:55 PM
    Author     : Felipe
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Búsqueda por Año de Calificaciones</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/asistente/asistenteGetCalificacionesSearch-3.0.6.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.consulta.alumno"/>
            </div>

            <div class="mb-3">
                <div class="btn-group" role="group">
                    <button id="search-button" title="Buscar" type="button" class="btn btn-light" >
                        <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                    </button>
                </div>
            </div>
            <form id="agno-form" accept-charset="UTF-8" action="#">
                <div class="form-row">
                    <table class="table-agno-sem">
                    <tr>
                        <td><label for="agno"><s:text name="label.year"/></label></td>
                        <td><input id="agno" name="agno"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>"
                                   maxlength="4" size="4" class="form-control input-sm input-agno"/></td>
                    </tr>
                    </table>

                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    </div>
                </div>
            </form>

            <div id="search-content-div" style="height:70vh;width: 70vw;">
                <iframe id="calificaciones-content-iframe" name="calificaciones-content-iframe" style="width: 95vw;height: 70vh;position: relative;" frameborder="0"></iframe>
            </div>
        </div>
    </body>
</html>