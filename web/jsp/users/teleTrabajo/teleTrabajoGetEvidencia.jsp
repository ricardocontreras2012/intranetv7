<%-- 
    Document   : teleTrabajoGetEvidencia
    Created on : 03-10-2023, 9:11:39
    Author     : Javier
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Tabla de evidencias</title>
        <link rel="stylesheet" href="/intranetv7/css/local/dragNDropStyle.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/upload/custominputfile.min.css" type="text/css" /> 
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
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/upload/custominputfile.min-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/teleTrabajoGetEvidencia-3.1.0.js"></script>
    </head>
    <body class="inner-body">
        
        <div class="title-div">
            Lista de Evidencia &nbsp;
        </div>

        <table id="evidencia-table" class="table table-striped table-bordered">
                <thead>
                <th style="width: 70%">Descripción</th>
                <th>Nombre del archivo</th>
                <th></th>
                </thead>
                <tbody>
                    <s:iterator value="#session.teleTrabajoSession.evidenciaList" status="row">
                        <tr id="evidenciaRow_<s:property value="id.etatelCorrel"/>">
                            <td>
                                <form id="descripcion<s:property value="id.etatelCorrel"/>-form" action="#" method="post" accept-charset="UTF-8">
                                    <input type="text" readonly="readonly" id="otro_<s:property value="id.etatelCorrel"/>" name="descripcionEvidencia" value="<s:property value="etatelDes"/>" class="form-control" maxlength="200"/>
                                </form>
                            </td>
                            <td><s:property value="etatelFile"/></td>
                            <td>
                                <s:if test="etatelFile != null"> 
                                    <button id="download-button_<s:property value="id.etatelCorrel"/>" title="Descargar" type="button" onClick="downloadFileToRow(<s:property value="id.etatelCorrel"/>)" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-download"></span>&nbsp; <span class="hidden-xs"></span></button>
                                </s:if>
                            </td>
                        </tr>
                    </s:iterator>
                    
                </tbody>
            </table>                                                         
        <form id="evidencia-form" action="#" method="post" accept-charset="UTF-8">
            <div id="hidden-input-div" style="display:none">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="sizeList" name="sizeList" value="<s:property value="#session.teleTrabajoSession.evidenciaList.size"/>" />
                <input type="hidden" id="formEscogido" name="formEscogido" />
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="posTarea" name="posTarea" />
            </div>
        </form>
    </body>
</html>