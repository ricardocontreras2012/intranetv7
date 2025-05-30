<%--
    Document   : commonProfesorRecord
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
        <title>Ficha de Profesor</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>       
        <script type="text/javascript" src="/intranetv7/js/local/common/profesor/commonProfesorRecord-3.0.2.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <header class="header-interno mt-3 pl-2">
                <p> <s:text name="label.title.consulta.profesor"/></p>
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
                                <s:if test="#session.genericSession.userType != \"CFI\"">
                                    <a class="dropdown-item" onclick="serviceActionParm('CommonEncuestaGetCargaHistorica?tipo=I')">
                                        <s:text name="label.menu.encuestas"/>
                                    </a>
                                    <a class="dropdown-item" onclick="serviceActionParm('CommonEncuestaGetCargaHistorica?tipo=V')">
                                        Encuestas Intermedias 
                                    </a>
                                </s:if>
                                <a href="#" class="dropdown-item" onclick="serviceAction('CommonProfesorCargaHistorica')">
                                    <s:text name="label.menu.historico.carga"/>
                                </a>
                                <a class="dropdown-item" onclick="serviceActionParm('CommonHorarioGetHorario?id=PR');">
                                    <s:text name="label.horario"/>
                                </a>
                                <a class="dropdown-item" onclick="serviceAction('CommonProfesorCargaHistoricaMaterial');">
                                    <s:text name="label.menu.material"/>
                                </a>
                                <a class="dropdown-item" onclick="serviceAction('CommonReporteGetCargaHistorica');">
                                    <s:text name="label.menu.reportes"/>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>

            <form id="profesor-form" action="#" method="post">
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
                        <td><input type="text" class="rut form-control" id="rut" name="rut"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profRut"/>"/>
                        </td>
                        <td>
                            <input
                                name="dv" class="dv form-control" type="text" readonly="readonly"
                                value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profDv"/>"/>
                        </td>
                        <td><input type="text" class="paterno form-control" id="paterno" name="paterno"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profPat"/>"/>
                        </td>
                        <td><input type="text" class="materno form-control" id="materno" name="materno"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profMat"/>"/>
                        </td>
                        <td><input type="text" class="nombre form-control" id="nombre" name="nombre"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profNom"/>"/>
                        </td>
                        <td><img id="fotoImg"
                                 src="CommonProfesorGetFoto?rut=<s:property value="#session.genericSession.getWorkSession(key).profesor.profRut"/>&key=<s:property value="key"/>"
                                 class="foto"
                                 alt="<s:property value="#session.genericSession.getWorkSession(key).profesor.profRut"/>"/>
                        </td>
                    </tr>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                </div>
            </form>
            <div id="search-content-div"></div>
        </div>        
    </body>
</html>