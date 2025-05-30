<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Select Tipo de Usuario</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialSelectTipo-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="modal" id="materialModal" style="display: block; background: rgba(0, 0, 0, 0.5);">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Cabecera del modal -->
                    <div class="modal-header">
                        <h4 class="modal-title">Selecciona Tipo de Material</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Cuerpo del modal -->
                    <div class="modal-body">
                        <button class="btn btn-info" onclick="showMaterial('PR')">Profesor</button>
                        <button class="btn btn-info" onclick="showMaterial('AY')">Ayudante</button>
                        <button class="btn btn-info" onclick="showMaterial('AL')">Alumno</button>
                    </div>

                    <!-- Pie del modal -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                    </div>

                </div>
            </div>
        </div>

        <form id="material-form" method="post">
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/> 
                <input type="hidden" id="tipoMaterial" name="tipoMaterial"/>
            </div>
        </form>
    </body>
</html>
