<%--
    Document   : commonMaterialViewMaterial
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
        <title>Consulta de Material</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialViewMaterial-3.0.3.js"></script>
    </head>
    <body class="inner-body">

        <div class="title-div">
            &nbsp;&nbsp;&nbsp;&nbsp;<s:text name="label.title.material.modificacion"/>
            <s:if test="#session.genericSession.getWorkSession(key).tipoMaterial==#session.genericSession.userType">
                <s:text name="label.title.material.subido.por.mi"/>
            </s:if>
            <s:else>
                <s:if test="#session.genericSession.getWorkSession(key).tipoMaterial==\"PR\"">
                    <s:text name="label.title.material.subido.por.mi.profesor"/>
                </s:if>
                <s:else>
                    <s:if test="#session.genericSession.getWorkSession(key).tipoMaterial==\"AL\"">
                        <s:text name="label.title.material.subido.por.mis.alumnos"/>
                    </s:if>
                    <s:else>
                        <s:if test="#session.genericSession.getWorkSession(key).tipoMaterial==\"AY\"">
                            <s:text name="label.title.material.subido.por.mis.ayudantes"/>
                        </s:if>
                    </s:else>
                </s:else>
            </s:else>
        </div>

        <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
            <div class="errorBox">
                <s:actionerror/><s:actionmessage/><s:fielderror/>
            </div>
        </s:if>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <s:if test="#session.genericSession.rut == #session.genericSession.getWorkSession(key).material.matRutAutor">
                            <%--div class="btn-group">
                                <button id="delete-button" title="Eliminar" type="button" class="btn btn-light"  onclick="deleteMaterial(); return false;">
                                    <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                                </button>
                            </div--%>
                            <div class="btn-group">
                                <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                    <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                                </button>
                            </div>

                        </s:if>
                    </div>
                </div>
            </div>
        </div>


        <s:form id="material-form" action="#" method="post" accept-charset="UTF-8" enctype="multipart/form-data" theme="bootstrap">
            <table class="table">
                <tr>
                    <td style="width:15%"><s:text name="label.tipo.material"/></td>
                    <td>
                        <s:select
                            name="tipo"
                            id="tipo"
                            headerKey="0"
                            headerValue="-- Seleccione --"
                            list="#session.genericSession.getWorkSession(key).tmaterialSelectOption"
                            listKey="tmaCod"
                            listValue="tmaDes"
                            cssClass="input"
                            />
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.archivo"/></td>
                    <td><a class="link"
                           href="CommonMaterialDownLoadMaterial?tipo=<s:property value="tipo"/>&material=<s:property value="material"/>&key=<s:property value="key"/>">
                            <s:property value="#session.genericSession.getWorkSession(key).material.matArchivo"/></a></td>
                </tr>
                <tr>
                    <td><s:text name="label.reemplazar.por"/></td>
                    <td style="width: 80%"><s:file name="upload" id="upload" size="45"
                            cssStyle="height: 24px; width:80%"/></td>
                </tr>
                <tr>
                    <td style="vertical-align:top; width: 20%"><s:text
                            name="label.descripcion"/></td>
                    <td><label>
                            <textarea name="caption" rows="3" cols="110" style="margin-left: 0; width: 100%" class="form-control"><s:property
                                    value="#session.genericSession.getWorkSession(key).material.matDescripcion"/></textarea>
                        </label></td>
                </tr>
            </table>
            <div id="hidden-input-div">
                <input type="checkbox" id="ck_<s:property value="tipo"/>_<s:property value="material"/>"
                       name="ck_<s:property value="tipo"/>_<s:property value="material"/>" value="1"
                       checked="checked" style="visibility:hidden;"/>
                <input type="hidden" id="tipoDummy" name="tipoDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).material.tmaterial.tmaCod"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </s:form>
    </body>
</html>

