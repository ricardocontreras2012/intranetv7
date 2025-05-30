<%--
    Document   : commonMaterialHistoricoListado
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
        <title>Lista de Materiales x Curso</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialListadoMateriales-2.0.4.js"></script>
    </head>
    <body class="inner-body">

            <div class="title-div">
                <s:text name="label.title.material"/>
                &nbsp;&nbsp; <s:property value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
            </div>

                <form id="materiales-form" action="#">
                    <s:iterator value="#session.genericSession.getWorkSession(key).tmaterial" var="tipo" status="rowTipo">
                        <p></p>
                        <p style="margin-left:3px"><s:property value="tmaDes"/></p>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <%--th scope="col" style="width:20px">
                                        <input style="height: 12px"
                                               id="checkHeadInput<s:property value="#rowTipo.count -1"/>"
                                               name="checkHeadInput<s:property value="#rowTipo.count -1"/>"
                                               type="checkbox"
                                               onclick="checkingHeadTipo('materiales-form', <s:property
                                                   value="#rowTipo.count -1"/>);"
                                               value="off"/>
                                    </th--%>
                                    <th scope="col"><s:text name="label.descripcion"/></th>
                                    <th scope="col" style="width:15%; text-align:center"><s:text name="label.date"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="materiales" status="rowMaterial">
                                    <tr style="height:20px; vertical-align:bottom !important">
                                        <%--td style="text-align: center; vertical-align: middle">
                                            <s:if test="#session.genericSession.rut == matRutAutor">
                                                <input type="checkbox"
                                                       id="ck_<s:property value="#rowTipo.count -1"/>_<s:property value="#rowMaterial.count -1"/>"
                                                       name="ck_<s:property value="#rowTipo.count -1"/>_<s:property value="#rowMaterial.count -1"/>"
                                                       value="1"/>
                                            </s:if>
                                        </td--%>
                                        <td style="height:20px; vertical-align:middle !important">
                                            <a href="CommonMaterialDownLoadMaterial?tipo=<s:property value="#rowTipo.count -1"/>&material=<s:property value="#rowMaterial.count -1"/>&key=<s:property value="key"/>"><s:property
                                                    value="matDescripcion"/></a>
                                        </td>
                                        <td style="width:15%; height:20px; vertical-align:middle !important; text-align:center">
                                            <s:date name="matFechaHabilitacion" format="dd/MM/yyyy HH:mm:ss"/>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </s:iterator>
                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        <input type="hidden" id="tipoMaterial" name="tipoMaterial" value=""/>
                        <input type="hidden" id="nTiposDummy" name="nTiposDummy"
                               value="<s:property value="#session.genericSession.getWorkSession(key).tmaterial.size"/>"/>
                    </div>
                </form>

        <form id="dummy-form" action="#"></form>
    </body>
</html>
