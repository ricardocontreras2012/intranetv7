<%--
    Document   : registradorCurricularActaConfirmarIntercambio
    Created on : 11-08-2020, 9:53:49
    Author     : Ricardo
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmación</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/acta/registradorCurricularActaConfirmarIntercambio-3.0.1.js"></script>
    </head>
    <body>
        <div class="modal fade" id="conf-emision-actas" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/> Generación de Actas de Alumno de Intercambio</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="form-conf">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Sem<span aria-hidden="true"></span></span>
                                </div>
                                <input type="text" class="form-control" id="sem" name="sem" placeholder="Sem" value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>"  required="required">
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Año<span aria-hidden="true"></span></span>
                                    </div>
                                    <input type="text" class="form-control" id="agno" name="agno" placeholder="Año" value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>" required="required">
                                </div>
                            </div>
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>" />
                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="generaActas();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
