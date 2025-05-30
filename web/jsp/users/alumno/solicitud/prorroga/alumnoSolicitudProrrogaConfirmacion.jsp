<%--
    Document   : alumnoSolicitudProrrogaConfirmacion
    Created on : 28-12-2017, 9:50:02
    Author     : rcontreras
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Confirmación</title>
         <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-modal.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/prorroga/alumnoSolicitudProrrogaConfirmacion-3.0.0.js"></script>
    </head>
    <body class="inner-body"> 
        <div class="container-fluid">
            <form id="confirmacion-form">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </form>

            <div class="d-flex align-items-center justify-content-center" style="height: 100vh">
                <div class="modal fade modal-center" id="modal-confirmacion" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="msgModalLabel">Prórroga de Periodo Lectivo</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div id="new-evaluacion-div">
                                    <p>¿Desea solicitar prórroga del periodo?</p>
                                    <p></p>
                                    <p>Copia del resultado de esta solicitud será enviada a su correo institucional</p>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light" onclick="aceptar();">Sí</button>
                                <button type="button" class="btn btn-light" data-dismiss="modal">No</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
