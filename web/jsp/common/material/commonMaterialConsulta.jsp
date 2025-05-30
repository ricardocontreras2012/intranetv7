<%--
    Document   : profesorMaterialConsulta
    Created on : 20-06-2009, 10:21:50 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consulta de Materiales</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialConsulta-3.0.0.js"></script>
    </head>
    <body class="inner-body">

        <div class="title-div">
            &nbsp;&nbsp;&nbsp;&nbsp;<s:text name="label.title.material"/> <s:property value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>

        <form id="material-form" action="#" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
            <div id="content-div">
                <s:iterator value="#session.genericSession.getWorkSession(key).otrosTmaterial" var="tipo" status="rowTipo">
                    <span style="margin-left:3px; font-weight: bold;"><s:property value="tmaDes"/></span>
                    <table id="tabla" class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col"><s:text name="label.descripcion"/></th>
                                <th scope="col" style="width:15%; text-align:center"><s:text name="label.date"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="materiales" status="rowMaterial">
                                <tr style="height:20px; vertical-align:bottom !important">
                                    <td style="height:20px; vertical-align:middle !important">
                                        <a href="CommonMaterialDownLoadMaterialOtros?tipo=<s:property value="#rowTipo.count -1"/>&material=<s:property value="#rowMaterial.count -1"/>&key=<s:property value="key"/>"><s:property
                                                value="matDescripcion"/></a>
                                    </td>
                                    <td style="height:20px; vertical-align:middle !important; text-align:center">
                                        <s:date name="matFechaHabilitacion" format="dd/MM/yyyy HH:mm:ss"/>
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </s:iterator>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="keyNew" name="keyNew" value="<s:property value="key"/>"/>
                <input type="hidden" id="tipoMaterial" name="tipoMaterial" value="<s:property value="tipoMaterial"/>"/>
                <input type="hidden" id="nTiposDummy" name="nTiposDummy" value="<s:property value="#session.genericSession.getWorkSession(key).tmaterial.size"/>"/>
            </div>
        </form>
    </body>
</html>