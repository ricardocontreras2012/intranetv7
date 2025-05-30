<%--
    Document   : evaluadorPracticaIdHomePage
    Created on : 06-06-2009, 06:32:41 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>HomePage Intranet-Evaluador Practica</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-home-page.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/flyOut/fly-out-one.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/flyOut/fly-out-dropdown-one.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-browser.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.corner.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript">
            "use strict";

            $(document).ready(function() {
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
                        <li><a onclick="executeAction('ConsultaMisDatosGetMisDatos')"><s:text
                                    name="label.menu.mis.datos"/></a></li>
                        <li><a onclick="executeAction('CommonPasswordEnableForm')"><s:text
                                    name="label.password"/></a></li>
                    </ul>
                </div>
                <script type="text/javascript">
                    $('#personales-title').corner("round top 5px");
                </script>

                <div id="utilidades-title" class="tituloGrupo">
                    <s:text name="label.menu.utilidades"/>
                </div>
                <div id="utilidades" style="width:130px; height:21px; margin-left:6px">
                    <ul class="flyout">
                        <li><a
                                onclick="executeSimpleAction('CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=<s:property value="key"/>');"><s:text
                                    name="label.menu.consulta.alumno"/></a></li>
                    </ul>
                </div>
                <script type="text/javascript">
                    $('#utilidades-title').corner("round top 5px");
                </script>
            </div>
        </div>
    </body>
</html>
