<%-- 
    Document   : alumnoSolicitudMatricula
    Created on : 13-12-2024, 13:50:56
    Author     : Usach
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Matrícula Fuera de Plazo</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>       
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/matricula/alumnoSolicitudMatricula-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="container-fluid">
            <div class="buttons-div">
                <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                    &nbsp; <span class="hidden-xs"><s:text name="label.enviar"/></span>
                </button>
            </div>

            <form id="causa-form" action="#" method="post" class="form-horizontal" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="causa" class="col-sm-1 control-label">Motivo de su Solicitud</label>
                    <div class="col-sm-8">
                        <textarea name="causa" id="causa" rows="3" maxlength="1500" class="form-control" ></textarea>
                    </div>
                    <s:file label="File" name="upload" multiple="multiple"></s:file>
                    </div>

                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                </div>
            </form>

            <div class="modal fade" id="aviso" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Desea presentar Solicitud de Matrícula Fuera de Plazo?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="grabar();">SI</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
    </body>
</html>


