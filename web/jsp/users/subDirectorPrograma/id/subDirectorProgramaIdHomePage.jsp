<%--
    Document   : subDirectorProgramaIdHomePage
    Created on : 06-05-2011, 06:02:45 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">      
        <title>HomePage Intranet-SubDirector Programa</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.5.min.js"></script>
        <script type="text/javascript">
            "use strict";

            $(document).ready(function () {
                $.get('<s:url action="CommonMensajeGetNumMessages"/>', {'key': '<s:property value="key"/>'}, function (data) {
                    $("#mensajes").html(data);
                });
                //Handlers
            });
        </script>
    </head>
    <body class="inner-body">
        <div class="ui-layout-north" id="top">
            <div id="layoutdims">
                <ul id="menu">
                    <li>
                        <div style="margin-top: -5px; overflow: hidden; height: 25px">
                            <table>
                                <tr><td style="background-color: white"><img id="help" src="/intranetv7/images/local/icon/aid.png" alt="help"/></td><td><a href="CommonSessionHelp" target= "_blank"><s:text name="label.menu.help"/></a></td><td>&nbsp;&nbsp;</td><td style="background-color: white"><img id="lock" src="/intranetv7/images/local/icon/lock.png" alt="lock"/></td><td><a href="CommonSessionClose?key=<s:property value="key"/>"><s:text name="label.menu.close.session"/></a></td></tr>
                            </table>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <div id ="center-div" class="ui-layout-center">
            <iframe id="main-content-iframe" name="main-content-iframe" src=""></iframe>
        </div>

        <div class="ui-layout-west">
            <div class="menu">
                <div id="personales-title" class="tituloGrupo">
                    <s:text name="label.menu.personales"/>
                </div>
                <div id="personales" style="width:130px; height:42px; margin-left:6px">
                    <ul class="flyout">
                        <li><a onclick="executeAction('CommonPasswordEnablePlusForm')"><s:text
                                    name="label.password"/></a></li>
                    </ul>
                </div>
                <script type="text/javascript">
                    $('#personales-title').corner("round top 5px");
                </script>

                <div id="mis-cursos_title" class="tituloGrupo">
                    <s:text name="label.menu.mis.cursos"/>
                </div>
                <div id="mis-cursos" style="width:130px; height:126px; margin-left:6px">
                    <ul class="flyout">
                        <li>
                            <a onclick="executeActionNested('CommonCursoGetCarreras?key=<s:property value="key"/>&actionName=CommonCursoGetDefCursosxAgnoSem')"><s:text
                                    name="label.menu.definicion"/></a></li>
                        <li><a onclick="executeActionNested('CommonCursoGetCarreras?key=<s:property value="key"/>&actionName=CommonCursoGetCursosxAgnoSem&actionCall=CommonCursoGetListaCurso')"><s:text
                                    name="label.menu.nominas"/></a></li>
                        <li><a onclick="executeActionNested('CommonCursoGetCarreras?key=<s:property value="key"/>&actionName=CommonCursoGetCursosxAgnoSem&actionCall=CommonMaterialConsultaMateriales')"><s:text
                                    name="label.menu.material"/></a></li>
                        <li><a onclick="executeActionNested('CommonCursoGetCarreras?key=<s:property value="key"/>&actionName=CommonCursoGetCursosxAgnoSem&actionCall=CommonReporteGetReportesCurso')"><s:text
                                    name="label.menu.reportes"/></a></li>
                        <li><a onclick="executeActionNested('CommonCursoGetCarreras?key=<s:property value="key"/>&actionName=CommonCursoGetCursosxAgnoSem&actionCall=CommonAsistenciaGetAsistencias')"><s:text
                                    name="label.menu.asistencia"/></a></li>
                        <li><a onclick="executeActionNested('CommonCursoGetCarreras?key=<s:property value="key"/>&actionName=CommonCursoGetCursosxAgnoSem&actionCall=CommonProfesorGetEncuesta')"><s:text
                                    name="label.menu.encuestas"/></a></li>
                    </ul>
                </div>
                <script type="text/javascript">
                    $('#mis-cursos_title').corner("round top 5px");
                </script>

                <div id="utilidades-title" class="tituloGrupo">
                    <s:text name="label.menu.utilidades"/>
                </div>
                <div id="utilidades" style="width:130px; height:105px; margin-left:6px">
                    <ul class="flyout">
                        <li><a
                                onclick="executeSimpleAction('CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=<s:property value="key"/>');"><s:text
                                    name="label.menu.consulta.alumno"/></a></li>
                        <li><a
                                onclick="executeAction('CommonAyudanteSearchEnable?actionCall=CommonAyudanteRecordEnable&typeSearch=F&key=<s:property value="key"/>')"><s:text
                                    name="label.menu.consulta.ayudante"/></a></li>
                        <li><a
                                onclick="executeAction('CommonProfesorSearchEnable?actionCall=CommonProfesorRecordEnable&typeSearch=F&key=<s:property value="key"/>')"><s:text
                                    name="label.menu.consulta.profesor"/></a></li>
                        <li id="mensajes-li"></li>
                        <li><a onclick="executeAction('CommonSalaEnable')"><s:text
                                    name="label.salas"/></a></li>
                    </ul>
                </div>
                <script type="text/javascript">
                    $('#utilidades-title').corner("round top 5px");
                </script>
            </div>
        </div>
    </body>
</html>
