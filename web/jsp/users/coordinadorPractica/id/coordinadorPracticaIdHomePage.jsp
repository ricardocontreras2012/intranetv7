<%--
    Document   : coordinadorPracticaIdHomePage
    Created on : 20-08-2011, 11:30:29 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>HomePage Intranet-Coordinador Practicas</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.5.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/coordinadorPractica/id/coordinadorPracticaIdHomePage-3.0.0.js"></script>
    </head>
    <body>
        <header class="header-home">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 color1">
                        <p><s:text name="label.title.bienvenido"/>&nbsp;<s:property value="#session.genericSession.nombre"/></p>
                    </div>
                </div>
            </div>
        </header>

        <nav class="navbar navbar-expand-lg navbar-dark">
            <a class="navbar-brand" href="#">
                <img src="/intranetv7/images/local/logo-usach/logo-usach-200.png" width="40" height="40" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarPrincipal" aria-controls="navbarPrincipal" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarPrincipal">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.solicitudes"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonPracticaGetSolicitudesxEstado?estado=10&autoridad=67&key=<s:property value="key"/>')"><s:text
                                    name="label.menu.solicitudes.por.resolver"/></a>
                            <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonPracticaGetSolicitudesxEstado?estado=40&autoridad=67&key=<s:property value="key"/>')"><s:text
                                    name="label.menu.solicitudes.resueltas"/></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.practicas"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonPracticaActaEnable?key=<s:property value="key"/>')"><s:text
                                    name="label.button.emitir.acta"/></a>
                            <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonPracticaActaRectificatoriaEnable?key=<s:property value="key"/>')"><s:text
                                    name="label.button.emitir.acta"/> <s:text
                                    name="label.menu.acta.rectificatoria"/></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.utilidades"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=<s:property value="key"/>');"><s:text
                                    name="label.menu.consulta.alumno"/></a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonAyudanteSearchEnable?actionCall=CommonAyudanteRecordEnable&typeSearch=F&key=<s:property value="key"/>');"><s:text
                                    name="label.menu.consulta.ayudante"/></a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonProfesorSearchEnable?actionCall=CommonProfesorRecordEnable&typeSearch=F&key=<s:property value="key"/>');"><s:text
                                    name="label.menu.consulta.profesor"/></a>
                            <a href="#" class="dropdown-item" id="mensajes-li"></a>
                        </div>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="nav-item"><a href="CommonSessionClose?key=<s:property value="key"/>" class="nav-link"><span class="fa fa-sign-out" aria-hidden="true"></span><s:text name="label.menu.close.session"/></a></li>
                </ul>
            </div>
        </nav>

        <div class="container-fluid text-center no-padding" style="width: 100vw;height: 90vh;">
            <div class="row content no-padding">
                <div class="col-xs-12 col-sm-12 col-md-12 home-iframe no-padding" style="width: 100vw;height: 90vh;">
                    <iframe id="main-content-iframe" style="width: 100vw;height: 90vh;position: relative;" src="" frameborder="0" allowfullscreen></iframe>
                </div>
            </div>
        </div>

        <form id="menu-form" action="#" method="post">
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
            </div>
        </form>

        <footer class="page-footer fixed-bottom text-center">
            <p><s:text name="label.footer"/></p>
        </footer>
    </body>
</html>
