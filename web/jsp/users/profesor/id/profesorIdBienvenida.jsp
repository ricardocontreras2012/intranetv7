<%--
    Document   : profesorIdBienvenida
    Created on : 18-03-2011, 12:46:53 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bienvenida Intranet-Profesor</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/normativa/profesorNormativa-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/id/profesorIdWelcome-3.0.1.js"></script>        
    </head>
    <body class="inner-body">
        <img class="login_image" src="/intranetv7/images/local/logo-usach/logo-sai-green-750.png" alt=""/>

        <div class="modal fade" id="autoevaluacion" role="dialog">
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
                        <button type="button" class="btn btn-light" onclick="showAutoEvaluacion();">SI</button>
                        <button type="button" class="btn btn-light" onclick="showWelCome();">NO</button>
                    </div>
                </div>
            </div>
        </div>
                        
        <form id="welcome-form" action="#" method="post">
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="sizeAutoEvaluacion" name="sizeAutoEvaluacion" value="<s:property value="#session.genericSession.getWorkSession(key).cursosAutoEvaluacion.size"/>">
            </div>
        </form>
    </body>
</html>
