<%--
    Document   : commonMaterialNewMaterial
    Created on : 19-05-2009, 09:37:02 AM
    Author     : Ricardo Contreras S.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Materiales</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialNewMaterial-3.0.3.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            &nbsp;&nbsp;<s:text name="label.title.material.nuevo"/>
            <s:if test="#session.genericSession.getWorkSession(key).tipoMaterial==\"PR\"">
                <s:text name="label.title.catedra"/>
            </s:if>
            <s:if test="#session.genericSession.getWorkSession(key).tipoMaterial==\"AY\"">
                <s:text name="label.title.ayudantia"/>
            </s:if>
            <s:if test="#session.genericSession.getWorkSession(key).tipoMaterial==\"AL\"">
                <s:text name="label.title.alumno"/>
            </s:if>
        </div>

        <button id="upload-button" title="Subir Material" type="button" class="btn btn-light" ><span class="fa fa-upload" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.upload.file"/></span>
        </button>

        <s:form id="material-form" action="#" method="post" accept-charset="UTF-8" enctype="multipart/form-data" theme="bootstrap">

            <table class="table">
                <tr>
                    <td><s:text name="label.tipo.material"/>
                    </td>
                    <td><s:select
                            name="tipo"
                            id="tipo"
                            headerKey="0"
                            headerValue="%{seleccioneTipoMaterial}"
                            list="#session.genericSession.getWorkSession(key).tmaterialSelectOption"
                            listKey="tmaCod"
                            listValue="tmaDes"
                            />
                    </td>
                </tr>
                <tr>
                    <td style="width: 20%"><s:text name="label.archivo.maximo"/>
                    </td>
                    <td style="width: 80%"><s:file id="upload" name="upload" size="45"
                            cssStyle="height: 24px; width:80%"/>
                    </td>
                </tr>
                <tr>
                    <td style="vertical-align:top; width: 20%"><s:text name="label.descripcion"/>
                    </td>
                    <td><s:textarea id="caption" name="caption" label="%{descripcion}" rows="3" cols="80" maxlength="500"
                                class="form-control"/>
                    </td>
                </tr>
            </table>

            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            <input type="hidden" id="tipoMaterial" name="tipoMaterial" value="<s:property value="tipoMaterial"/>"/>
        </s:form>
    </body>
</html>
