<%--
    Document   : commonCursoNominaAlumnos
    Created on : 03-08-2009, 09:02:46 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Nómina de Alumnos x Curso</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/commonCursoNominaAlumnos-3.0.6.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div" style="line-height: 1.5;">
            <s:text name="label.title.listaCurso"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="print-button" title="Imprimir" type="button" class="btn btn-light" >
                                <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="foto-button" title="Imprimir con Foto" type="button" class="btn btn-light" >
                                <span class="fa fa-camera"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="export-button" title="Excel" type="button" class="btn btn-light" >
                                <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.excel"/></span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div id="content-div" style="height: 90vh; overflow-y: scroll; overflow-x: hidden;">
            <form id="lista-form" action="#" method="post">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col"><s:text name="label.rut"/></th>
                            <th scope="col"><s:text name="label.paterno"/></th>
                            <th scope="col"><s:text name="label.materno"/></th>
                            <th scope="col"><s:text name="label.name"/></th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).nominaCurso" status="row">
                            <tr>
                                <td style="text-align: right">
                                    <s:property value="#row.count"/>&nbsp;&nbsp;&nbsp;
                                </td>
                                <td>
                                    <s:if test="#session.genericSession.userType !=\"AY\"">
                                        <a id="alumno_<s:property value="#row.count -1"/>"><s:property
                                                value="alumno.aluRut"/>-<s:property value="alumno.aluDv"/></a>
                                        </s:if>
                                        <s:else>
                                            <s:property value="alumno.aluRut"/>-<s:property value="alumno.aluDv"/>
                                    </s:else>
                                </td>
                                <td>
                                    <s:property value="alumno.aluPaterno"/>
                                </td>
                                <td>
                                    <s:property value="alumno.aluMaterno"/>
                                </td>
                                <td>
                                    <s:if test="alumno.aluNombreSocial != null && alumno.aluNombreSocial.trim() != ''">
                                        <s:property value="alumno.aluNombreSocial"/>
                                    </s:if>
                                    <s:else>
                                        <s:property value="alumno.aluNombre"/>
                                    </s:else>
                                </td>
                                <td style="text-align: center">
                                    <img height="60" width="50" id="foto<s:property value="#row.count"/>"
                                         src="dummy/<s:property value="#row.count -1"/>/CommonCursoGetFotoAlumno?pos=<s:property value="#row.count -1"/>&key=<s:property value="key"/>"
                                         alt="foto"/>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                    <input type="hidden" id="nombreDummy" name="nombreDummy"
                           value="<s:property value="#session.genericSession.getWorkSession(key).curso.nombreNormalizado"/>"/>
                </div>
            </form>
        </div>
    </body>
</html>