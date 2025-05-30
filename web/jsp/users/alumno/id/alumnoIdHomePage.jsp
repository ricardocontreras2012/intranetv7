<%--
    Document   : alumnoIdHomePage
    Created on : 06-06-2009, 09:12:15 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>HomePage Intranet-Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/id/alumnoIdHomePage-3.0.4.js"></script>
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
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.personales"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeAction('AlumnoGetMisDatos');"><s:text
                                    name="label.menu.mis.datos"/></a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonPasswordEnableForm');"><s:text
                                    name="label.password"/></a>                            
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.curriculares"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="showNormativa();"><s:text name="label.menu.normativa"/></a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonAlumnoGetDatosCurriculares');"><s:text name="label.menu.mis.datos"/></a>
                            <a href="#" class="dropdown-item" onclick="getMalla();"><s:text name="label.menu.malla"/></a>                               
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonAlumnoGetCalificaciones');"><s:text name="label.menu.cartola"/></a>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onclick="recargaListaCursos();"><s:text name="label.menu.mis.cursos"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown"  id="div-mis-cursos">
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.utilidades"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeActionParm('CommonHorarioGetHorario?id=AL')">
                                <s:text name="label.horario"/></a>
                            <a href="#" class="dropdown-item" onclick="showCalendario();">
                                <s:text name="label.menu.calendario"/></a>
                            <a href="#" class="dropdown-item" onclick="executeAction('AlumnoCertificacionGetCertificados');">
                                <s:text name="label.menu.certificados"/></a>                             

                            <s:if test="#session.genericSession.getWorkSession(key).aluCar.getParametros().getBloqueada() == \"NO\" ">                                    
                                <a href="#" class="dropdown-item" onclick="executeAction('AlumnoInscripcionEnableInscripcion');">
                                    <s:text name="label.inscripcion"/></a>                                
                                </s:if>
                                <s:else>

                                <a href="#" class="dropdown-item" onclick="showModal();">
                                    <s:text name="label.inscripcion"/></a>  
                                </s:else>    

                            <a href="#" class="dropdown-item" onclick="executeAction('AlumnoSolicitudGetSolicitudes');">
                                <s:text name="label.menu.solicitudes"/></a>
                                <s:if test="#session.genericSession.getWorkSession(key).aluCar.inscribioExamenAP()">
                                <a href="#" class="dropdown-item" onclick="executeAction('AlumnoExamenAPConfirmacion');">Sorteo Grado</a>
                            </s:if>
                            <a href="#" class="dropdown-item" onclick="executeActionMessage();">
                                <s:text name="label.menu.mensajes"/></a>
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

        <div class="modal fade" id="inscripcion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Aviso</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Tu postulación de inscripción se encuentra actualmente en proceso. A partir del <s:date name="#session.genericSession.getWorkSession(key).aluCar.parametros.getTerminoFechaCorte()" format="dd/MM/yyyy HH:mm:ss"/>, podrás acceder al resultado del proceso. Te invitamos a estar atento(a) a la fecha indicada.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <form id="alumno-form" action="#" method="post">
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                <input type="hidden" id="urlCalendario" name="urlCalendario" value="<s:property value="#session.genericSession.getWorkSession(key).mencionInfoIntranet.miniUrlCalendarioAct"/>"/>
                <input type="hidden" id="urlNormativa" name="urlNormativa" value="<s:property value="#session.genericSession.getWorkSession(key).mencionInfoIntranet.miniUrlNormativa"/>"/>
            </div>
        </form>

        <footer class="page-footer fixed-bottom text-center">
            <p><s:text name="label.footer"/></p>
        </footer>
    </body>
</html>
