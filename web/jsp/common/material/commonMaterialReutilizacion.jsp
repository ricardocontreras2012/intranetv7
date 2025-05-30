<%--
    Document   : commonMaterialReutilizacion
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
        <title>Listado de Materiales para Reutilización</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialReutilizacion-3.0.0.js"></script>
    </head>
    <body class="inner-body">

        <div class="title-div">
            &nbsp;&nbsp;&nbsp;&nbsp;<s:text name="label.title.reuse"/> <s:property value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>

        <button id="reuse-button" title="Reusar" type="button" class="btn btn-light"  onclick="reuseMaterial('<s:property value="key"/>');"><span class="fa fa-recycle" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.button.reuse"/></span>
        </button>
        
        <s:form id="material-form" action="#" method="post" accept-charset="UTF-8" enctype="multipart/form-data" theme="bootstrap">
            <p></p>
            <s:iterator value="#session.genericSession.getWorkSession(key).otrosTmaterial" var="tipo" status="rowTipo">
                <span style="margin-left:3px; font-weight: bold;"><s:property value="tmaDes"/></span>
                <table id="tabla" class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col" style="width:20px"><input style="height: 12px"
                                                                      id="checkHeadInput<s:property value="#rowTipo.count -1"/>"
                                                                      name="checkHeadInput<s:property value="#rowTipo.count -1"/>"
                                                                      type="checkbox"
                                                                      onclick="checkingHeadTipo('materiales-form',
                                                                      <s:property value="#rowTipo.count -1"/>);"/>
                            </th>
                            <th scope="col"><s:text name="label.descripcion"/></th>
                            <th scope="col" style="width:15%; text-align:center"><s:text name="label.date"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="materiales" status="rowMaterial">
                            <tr style="height:20px; vertical-align:bottom !important">
                                <td style="text-align: center">
                                    <input type="checkbox"
                                           id="ck_<s:property value="#rowTipo.count -1"/>_<s:property value="#rowMaterial.count -1"/>"
                                           name="ck_<s:property value="#rowTipo.count -1"/>_<s:property value="#rowMaterial.count -1"/>"/>
                                </td>
                                <td style="height:20px; vertical-align:middle !important">
                                    <a href="ProfesorMaterialDownLoadReusedMaterial?tipo=<s:property value="#rowTipo.count -1"/>&material=<s:property value="#rowMaterial.count -1"/>&key=<s:property value="key"/>" id="material_<s:property value="#rowTipo.count -1"/>_<s:property value="#rowMaterial.count -1"/>"><s:property
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
            <div id="hidden-input-div">
                <input type="hidden" id="keyNew" name="keyNew" value="<s:property value="key"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </s:form>
    </body>
</html>