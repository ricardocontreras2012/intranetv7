<%--
    Document   : commonAyudanteRecord
    Created on : 31-07-2010, 06:03:17 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Ficha General de Ayudante</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/ayudante/commonAyudanteRecord-3.0.1.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <header class="header-interno mt-3 pl-2">
                <p><s:text name="label.title.consulta.ayudante"/></p>
            </header>

            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSubNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSubNav">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a href="#" class="nav-link" id="print-button"><span class="fa fa-print" aria-hidden="true"></span>&nbsp;<s:text name="label.imprimir"/></a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link" id="clear-button"><span class="fa fa-eraser" aria-hidden="true"></span>&nbsp;<s:text name="label.button.clear"/></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Info</a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" onclick="serviceActionBlankParm('CommonHorarioGetHorario?id=AY')">
                                    <s:text name="label.horario"/>
                                </a>
                                <a class="dropdown-item" onclick="serviceActionPrint('CommonAyudanteGetDatosPersonales', 'CommonAyudantePrintPersonales')">
                                    <s:text name="label.menu.personales"/>
                                </a>                                
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>

            <form id="ayudante-form" action="#" method="post">
                <table class="table-search">
                    <tr>
                        <td><label for="rut"><s:text name="label.rut"/></label></td>
                        <td></td>
                        <td><label for="paterno"><s:text name="label.paterno"/></label></td>
                        <td><label for="materno"><s:text name="label.materno"/></label></td>
                        <td><label for="nombre"><s:text name="label.name"/></label></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <input class="rut form-control" type="text" id="rut" name="rut"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuRut"/>"/>
                        </td>
                        <td>
                            <input class="dv form-control" name="dv" type="text" readonly="readonly"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuDv"/>"/>
                        </td>
                        <td>
                            <input class="paterno form-control" type="text" id="paterno" name="paterno"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuPaterno"/>"/>
                        </td>
                        <td>
                            <input class="materno form-control" type="text" id="materno" name="materno"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuMaterno"/>"/>
                        </td>
                        <td>
                            <input class="nombre form-control" type="text" id="nombre" name="nombre"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuNombre"/>"/>
                        </td>
                        <td><img id="fotoImg"
                                 src="CommonAyudanteGetFoto?rut=<s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuRut"/>&key=<s:property value="key"/>"
                                 height="125" width="110"
                                 alt="<s:property value="#session.genericSession.getWorkSession(key).ayudante.ayuRut"/>"/>
                        </td>
                    </tr>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="printAction" name="printAction" value="CommonAyudantePrintPersonales"/>
                    <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                </div>
            </form>
            <div id="search-content-div"></div>

        </div>        
    </body>
</html>