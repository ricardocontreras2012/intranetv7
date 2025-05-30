<%-- 
    Document   : teleTrabajoIdHomePage
    Created on : 07-09-2023, 10:54:34
    Author     : Javier
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">      
        <title>TeletrabajoHomePage Intranet</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/id/teleTrabajoIdHomePage-1.0.0.js"></script>
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
                <img src="/intranetv7/images/local/logo-usach/logo_nav.png" width="35" height="44" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarPrincipal" aria-controls="navbarPrincipal" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarPrincipal">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.personales"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonPasswordEnableForm')">
                                <s:text name="label.password"/></a>
                        </div>
                    </li>
                    <s:if test="#session.teleTrabajoSession.isTeletrabajoJefe(#session.genericSession.rut)">
                        <li class="nav-item">
                            <a href="#" class="nav-link" onclick="executeAction('TeleTrabajoGetFuncionarios')">Mis Funcionarios</a>
                        </li>
                    </s:if>

                    <s:if test="#session.teleTrabajoSession.isTeletrabajoFuncionario(#session.genericSession.rut)">
                        <li class="nav-item">
                            <a href="#" class="nav-link" onclick="executeAction('TeleTrabajoGetMisActividades')">Mis Actividades</a>
                        </li>
                    </s:if>

                    <s:if test="#session.teleTrabajoSession.isSuperTeletrabajo(#session.genericSession.rut)">
                        <li class="nav-item">
                            <a href="#" class="nav-link" onclick="executeAction('TeleTrabajoExportReporte')">Reporte</a>
                        </li>
                    </s:if>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="nav-item"><a href="CommonSessionClose?key=<s:property value="key"/>" class="nav-link"><span class="fa fa-sign-out" aria-hidden="true"></span><s:text name="label.menu.close.session"/></a></li>
                </ul>
            </div>
        </nav>

        <div class="container-fluid text-center no-padding">
            <div class="row content no-padding">
                <div class="col-xs-12 col-sm-12 col-md-12 home-iframe no-padding">
                    <iframe id="main-content-iframe" style="width: 100vw;height: 82vh;position: relative;" src="" frameborder="0" allowfullscreen></iframe>
                </div>
            </div>
        </div>

        <form id="decano-form" action="#" method="post">
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                <input type="hidden" id="keyProfDummy" name="keyProfDummy" value="<s:property value="keyProf"/>"/>
            </div>
        </form>

        <footer class="page-footer fixed-bottom text-center">
            <p><s:text name="label.footer"/></p>
        </footer>
    </body>
</html>