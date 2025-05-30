<%--
    Document   : alumnoSolicitudPracticaUploadDocumento
    Created on : 07-03-2019, 15:03:15
    Author     : Ricardo
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="session.GenericSession" %>
<%@ page import="session.WorkSession" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Subir Documento de Práctica</title>
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
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/solicitud/practica/alumnoSolicitudPracticaUploadDocumento-3.0.0.js"></script>
    </head>
    <body>
        <button id="upload-button" title="Subir Documento" type="button" class="btn btn-light" ><span class="fa fa-upload" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.upload.file"/></span>
        </button>
        <s:form id="solicitud-form" action="#" theme="bootstrap" enctype="multipart/form-data">
            <table class="table">
                <tr>
                    <td><s:text name="label.tipo.documento"/></td>
                    <td><select id="tipo" name="tipo">
                            <option value = "20">Carta Autorización</option>
                            <option value = "24">Informe de Práctica</option>
                            <option value = "26">Evaluación Empresa</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="width: 20%"><s:text name="label.archivo.maximo"/>
                    </td>
                    <td style="width: 80%"><s:file id="upload" name="upload" size="45"
                            cssStyle="height: 24px; width:80%"/>
                    </td>
                </tr>
            </table>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </s:form>
    </body>
</html>

