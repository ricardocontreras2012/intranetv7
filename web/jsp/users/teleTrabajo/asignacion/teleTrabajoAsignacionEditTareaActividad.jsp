<%-- 
    Document   : teleTrabajoAsignacionEditTareaActividad
    Created on : 27-09-2023, 15:27:57
    Author     : Felipe
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Agregar Items a Actividad</title>
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
        src="/intranetv7/js/local/users/teleTrabajo/asignacion/teleTrabajoAsignacionEditTareaActividad-1.0.0.js"></script>
    </head>
    <body class="inner-body">

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
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
                <th>ID</th>
                <th style="width: 90%">Descripción</th>
                <th></th>
                </thead>
                <tbody>
                    <s:iterator value="#session.teleTrabajoSession.listaTareas" status="row">
                        <tr>
                            <td><s:property value="id.tatelTarea"/></td>
                            <td><input type="text" id="otro_<s:property value="id.tatelTarea"/>" name="otro_<s:property value="id.tatelTarea"/>" value="<s:property value="tatelDes"/>" class="form-control" maxlength="200"/></td>
                        </tr>
                    </s:iterator>
                    
                </tbody>
            </table>                                                         

            <div id="hidden-input-div" style="display:none">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="id-row" name="id-row" value="0" />
            </div>
        </form>
   
    </body>
</html>