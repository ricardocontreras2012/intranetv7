<%--
    Document   : commonMensajeGetListaRUN
    Created on : 02-10-2017, 9:42:18
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de RUT</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeAlumnoLista-3.0.0.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/common/mensaje/commonMensajeGetListaRUN-3.0.1.js"></script>
    </head>    
    <body class="inner-body"> 
        <div class="buttons-div">
            <button id="upload-button" name="upload-button" class="positive">
                <img src="/intranetv7/images/local/icon/upload.png" height="16" width="16" alt="save" title="Grabar"/>
                <s:text name="label.upload.file"/>
            </button>
        </div>
         <s:form id="lista-rut-form" action="#" theme="bootstrap" enctype="multipart/form-data">
            <div style="width: 80%">
                <s:file id="upload" name="upload" size="45" class="form-control"
                        cssStyle="height: 30px; width:80%"/>
            </div>
            <div id="hidden-input-div">
                <input id="key" name="key" type="hidden" value="<s:property value="key"/>"/>

            </div>          
        </s:form>   
    </body>
</html>
