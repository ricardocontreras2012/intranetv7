<%--
    Document   :oficinaCurricularIdHomePage
    Created on : 06-06-2009, 10:12:26 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">      
        <title>HomePage Intranet-Oficina Curricular</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link href="/intranetv7/css/smartMenu/jquery.smartmenus.bootstrap-4.css" rel="stylesheet">
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/smartMenu/jquery.smartmenus.js"></script>
        <script type="text/javascript" src="/intranetv7/js/smartMenu/jquery.smartmenus.bootstrap-4.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/id/registradorCurricularIdHomePage-3.0.0.js"></script>
    </head>

    <body style="padding-top:5px;">

        <header class="header-home" style="color: white;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <p><s:text name="label.title.bienvenido"/>&nbsp;<s:property value="#session.genericSession.nombre"/></p>
                    </div>
                </div>
            </div>
        </header>

        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg navbar-light bg-light rounded mb-4">
                <a class="navbar-brand" href="#"><img src="/intranetv7/images/local/logo-usach/logo-usach-200.png" width="40" height="40" alt=""></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNavDropdown">

                    <!-- Left nav -->
                    <ul class="nav navbar-nav mr-auto">

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#"><s:text name="label.menu.personales"/></a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#" onclick="executeAction('CommonEmailEnableForm');"><s:text name="label.menu.email"/></a></li>
                                <li><a class="dropdown-item" href="#" onclick="executeAction('CommonPasswordEnablePlusForm')"><s:text name="label.password"/></a></li>
                            </ul>
                        </li>

                        <li class="nav-item"><a class="nav-link" href="#" onclick="executeSimpleAction('CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=<s:property value="key"/>');">Alumnos</a></li>

                        <li class="nav-item"><a class="nav-link" href="#">Certificación</a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonAlumnoSearchEnable?typeSearch=F&actionCall=CommonAlumnoGetAluCarList&actionNested=CommonCertificacionCheckRegular&key=<s:property value="key"/>');">C1-Alumno Regular</a>
                                </li>
                                <li>
                                    <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonAlumnoSearchEnable?typeSearch=F&actionCall=CommonAlumnoGetAluCarList&actionNested=CommonCertificacionCheckEgresado&key=<s:property value="key"/>');">C3-Egresado</a>
                                </li>
                                <li>
                                    <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonAlumnoSearchEnable?typeSearch=F&actionCall=CommonAlumnoGetAluCarList&actionNested=CommonCertificacionCheckInformeCalificaciones&key=<s:property value="key"/>');">I3-Informe de Calificaciones</a>
                                </li>                               
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#">Actas</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Calificación</a>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Cursos Malla</a>
                                            <ul class="dropdown-menu">

                                                <li><a class="dropdown-item" href="#" onclick="executeAction('OficinaCurricularActaPrint');">Imprimir</a></li>
                                                <li><a class="dropdown-item" href="#" onclick="executeAction('OficinaCurricularActaRecepcion');">Recepcionar</a></li>

                                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Consultar</a>
                                                    <ul class="dropdown-menu">
                                                        <li><a class="dropdown-item" onclick="executeSimpleAction('OficinaCurricularActaConsultarDummy?key=<s:property value="key"/>');" href="#">Estado</a></li>                                                        
                                                        <li><a class="dropdown-item" href="#">Listado Recepción</a></li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Req. Adicional Titulación</a>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" href="#">Imprimir</a></li>
                                                <li><a class="dropdown-item" href="#">Recepcionar</a></li>
                                            </ul>
                                        </li>
                                    </ul>

                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Convalidación</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Imprimir</a></li>
                                        <li><a class="dropdown-item" href="#">Recepcionar</a></li>
                                    </ul>
                                </li>

                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Práctica</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Imprimir</a></li>
                                        <li><a class="dropdown-item" href="#">Recepcionar</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#">Calificaciones</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Alumno</a>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Malla</a>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" href="#">Modificar</a></li>
                                                <li><a class="dropdown-item" href="#">Convalidar</a></li>
                                                <li><a class="dropdown-item" href="#">Equivalencia</a></li>
                                                <li><a class="dropdown-item" href="#">Nombre Actividad</a></li>
                                            </ul>
                                        </li>
                                        <li><a class="dropdown-item" href="#">Requisito Adicional de Titulación</a></li>
                                        <li><a class="dropdown-item" href="#">Electivos</a></li>
                                    </ul>
                                </li>
                                <li><a class="dropdown-item" href="#">Curso</a></li>
                            </ul>
                        </li>


                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#">Inscripciones</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Consultar</a></li>
                                <li><a class="dropdown-item" href="#">Auditar</a></li>                                
                            </ul>
                        </li>


                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#">Listados</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Calidades</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Egresados</a></li>
                                        <li><a class="dropdown-item" href="#">Titulados</a></li>
                                        <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Graduados</a>
                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item" href="#">General</a></li>
                                                <li><a class="dropdown-item" href="#">Por Area (Contador Público y Auditor)</a></li>
                                                <li><a class="dropdown-item" href="#">Por Area (Ing. Comercial-Administración)</a></li>
                                                <li><a class="dropdown-item" href="#">Por Area (Ing. Comercial-Economía)</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li><a class="dropdown-item" href="#">Situaciones</a></li>
                                <li><a class="dropdown-item" href="#">Solicitud de Certificado</a></li>
                                <li><a class="dropdown-item" href="#">Cuadro de Honor</a></li>
                                <li><a class="dropdown-item" href="#">Mejor Rendimiento</a></li>
                                <li><a class="dropdown-item" href="#">Matriculados</a></li>
                                <li><a class="dropdown-item" href="#">Tramitación de Títulos</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#">Solicitudes</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Cambio de Bachillerato</a></li>
                                <li><a class="dropdown-item" href="#">Autorización de Matrícula</a></li>
                                <li><a class="dropdown-item" href="#">Cambio de Carrera</a></li>
                                <li><a class="dropdown-item" href="#">Cambio de Jornada</a></li>
                                <li><a class="dropdown-item" href="#">Elimina Última Situación</a></li>
                                <li><a class="dropdown-item" href="#">Registrar Anotación</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#">Titulación</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Imprimir Carátula</a></li>
                                <li><a class="dropdown-item" href="#">Imprimir Acta</a></li>
                                <li><a class="dropdown-item" href="#">Recepcionar Acta</a></li>
                                <li><a class="dropdown-item" href="#">Expediente</a></li>
                                <li><a class="dropdown-item" href="#">Situación Financiera</a></li>
                                <li><a class="dropdown-item" href="#">Pago Arancel</a></li>
                            </ul>
                        </li>                        

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#">Utilidades</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" onclick="executeActionMessage();" href="#"><s:text name="label.menu.mensajes"/></a></li>
                            </ul>
                        </li>
                    </ul>

                    <!-- Right nav -->
                    <ul class="nav navbar-nav">
                        <li class="nav-item active"><a href="CommonSessionClose?key=<s:property value="key"/>" class="nav-link"><span class="fa fa-sign-out" aria-hidden="true"></span><s:text name="label.menu.close.session"/></a></li>
                    </ul>

                </div>
            </nav>
        </div> <!-- /container -->

        <div class="container-fluid text-center no-padding" style="width: 100vw;height: 90vh;">
            <div class="row content no-padding">
                <div class="col-xs-12 col-sm-12 col-md-12 home-iframe no-padding" style="width: 100vw;height: 90vh;">
                    <iframe id="main-content-iframe" style="width: 100vw;height: 90vh;position: relative;" src="" frameborder="0" allowfullscreen></iframe>
                </div>
            </div>
        </div>

        <form id="rc-form" action="#" method="post">
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
            </div>
        </form>

        <footer class="page-footer fixed-bottom text-center">
            <p><s:text name="label.footer"/></p>
        </footer>

    </body>
</html>