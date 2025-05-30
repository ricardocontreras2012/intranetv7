<%--
    Document   : profesorEncuestaConfirm
    Created on : 19-02-2021, 14:09:53
    Author     : Ricardo
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Respuesta de Autoevaluación Docente</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/autoevaluacion/profesorEncuestaConfirm-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="modal fade" id="confirmacion" tabindex="-1" role="dialog" aria-labelledby="msgModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Desea responder la encuesta de autoevaluación de sus cursos?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="showForm();">SI</button>
                        <button type="button" class="btn btn-light" onclick="stack();">Más Adelante</button>
                        <button type="button" class="btn btn-light" onclick="remove();">No</button>
                    </div>
                </div>
            </div>
        </div>
        <form id="confirm-form" action="#">
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>
