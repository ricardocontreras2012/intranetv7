<%--
    Document   : alumnoSolicitudInscripcionNewSolicitud
    Created on : 14-09-2010, 10:39:01 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Generación de Solicitud de Inscripción de Asignaturas</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/upload/custominputfile.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/upload/custominputfile.min-es.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/solicitud/inscripcion/alumnoSolicitudInscripcionNewSolicitud-3.0.4.js"></script>
    </head>
    <body class="inner-body">

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="add-button" title="Curso" type="button" class="btn btn-light" >
                                <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs">Curso</span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.enviar"/></span>
                            </button>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>

        <form id="solicitud-form" action="#" method="post" accept-charset="UTF-8">
            <table id="solicitud-table" class="table table-striped table-bordered">
                <thead>
                <th style="width: 50%">Curso</th>
                <th>Motivo</th>
                <th>Otro</th>
                <th></th>
                </thead>
                <tbody></tbody>
            </table>                                              
            <table id="attach-table">
            </table>            

            <div id="hidden-input-div" style="display:none">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <div id="options-curso-div">
                    <s:iterator value="#session.genericSession.getWorkSession(key).cursoSolicitudList" status="row">
                        <option value="<s:property value="#row.count -1"/>"><s:property value="getNombreFull()"/> </option>
                    </s:iterator>
                </div>
                <div id="options-motivo-div">
                    <s:iterator value="#session.genericSession.getWorkSession(key).getTmotivoSolicitudInscripcionList()" status="row">
                        <option value="<s:property value="tmsiCod"/>"><s:property value="tmsiDes"/> </option>
                    </s:iterator>
                </div>
                <input type="hidden" id="id-row" name="id-row" value="0" />
            </div>
        </form>

        <form id="attach-form" action="#" method="post" enctype="multipart/form-data">
            <s:file label="File" name="upload" multiple="multiple"></s:file>
            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
        </form>        
    </body>
</html>

