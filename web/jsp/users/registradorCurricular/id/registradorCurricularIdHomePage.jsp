<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">      
        <title>HomePage Intranet-Registrador Curricular</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/5.1.3/bootstrap.min.css" type="text/css" />
        <link href="/intranetv7/css/bootstrap/multilevel/multilevel.bootstrap-5.css" rel="stylesheet">
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/5.1.3/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/multilevel/multilevel.bootstrap-5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/id/registradorCurricularIdHomePage-3.0.0.js"></script>
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
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <img src="/intranetv7/images/local/logo-usach/logo-usach-200.png" width="40" height="40" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNavDropdown">

                    <!-- Left nav -->
                    <ul class="nav navbar-nav">

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown"><s:text name="label.menu.personales"/></a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#" onclick="executeAction('CommonEmailEnableForm');"><s:text name="label.menu.email"/></a></li>
                                <li><a class="dropdown-item" href="#" onclick="executeAction('CommonPasswordEnablePlusForm')"><s:text name="label.password"/></a></li>
                            </ul>
                        </li>

                        <li class="nav-item"><a class="nav-link" href="#" onclick="executeSimpleAction('CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=<s:property value="key"/>');">Alumnos</a></li>

                        <%--li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Solicitud Certificación</a>
                            <ul class="dropdown-menu">                                
                                <li>
                                    <a href="#" class="dropdown-item" onclick="executeAction('RegistradorCurricularCertificadosGetGlosa?key=<s:property value="key"/>');">Emitir</a>
                                </li>
                            </ul>
                        </li>
                        
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Certificación</a>
                            <ul class="dropdown-menu">                                
                                <li>
                                     <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonAlumnoSearchEnable?typeSearch=F&actionCall=CommonAlumnoGetAluCarList&actionNested=CommonCertificacionCheckRegular&key=<s:property value="key"/>');">C1-Regular</a>
                                </li>                                
                                <li>
                                    <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonAlumnoSearchEnable?typeSearch=F&actionCall=CommonAlumnoGetAluCarList&actionNested=CommonCertificacionCheckInformeCalificaciones&key=<s:property value="key"/>');">I3-Informe de Calificaciones</a>
                                </li>
                            </ul>
                        </li--%>



                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Certificación</a>

                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#" class="dropdown-item" onclick="executeAction('RegistradorCurricularCertificadosGetGlosa?key=<s:property value="key"/>');">Solicitudes</a>
                                </li>

                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Emitir</a>
                                    <ul class="submenu dropdown-menu">
                                        <li><a href="#" class="dropdown-item"
                                               onclick="executeSimpleAction('CommonAlumnoSearchEnable?typeSearch=F&amp;actionCall=CommonAlumnoGetAluCarList&amp;actionNested=CommonCertificacionCheckRegular&amp;key=<s:property value="key"/>');">C1-Regular</a></li>
                                        <li><a href="#" class="dropdown-item"
                                               onclick="executeSimpleAction('CommonAlumnoSearchEnable?typeSearch=F&amp;actionCall=CommonAlumnoGetAluCarList&amp;actionNested=CommonCertificacionCheckInformeCalificaciones&amp;key=<s:property value="key"/>');">I3-Informe de Calificaciones</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>


                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Actas</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Calificación</a>
                                    <ul class="submenu dropdown-menu">
                                        <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Cursos Malla</a>
                                            <ul class="submenu dropdown-menu">
                                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Generar</a>
                                                    <ul class="submenu dropdown-menu">
                                                        <li><a class="dropdown-item" href="#" onclick="executeActionNested('RegistradorCurricularGetCarreras?actionCall=RegistradorCurricularActaGenerarxCarrera&key=<s:property value="key"/>')">General</a></li>
                                                        <li><a class="dropdown-item" href="#" onclick="executeAction('RegistradorCurricularActaConfirmarIntercambio');">Intercambio</a></li>
                                                    </ul>
                                                </li>
                                                <li><a class="dropdown-item" href="#" onclick="executeAction('OficinaCurricularActaPrint');">Imprimir</a></li>
                                                <li><a class="dropdown-item" href="#" onclick="executeAction('OficinaCurricularActaRecepcion');">Recepcionar</a></li>
                                                <li><a class="dropdown-item" href="#">Eliminar</a></li>
                                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Consultar</a>
                                                    <ul class="submenu dropdown-menu">
                                                        <li><a class="dropdown-item" onclick="executeSimpleAction('OficinaCurricularActaConsultarDummy?key=<s:property value="key"/>');" href="#">Estado</a></li>
                                                        <li><a class="dropdown-item" href="#">Listado Recepción</a></li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Req. Adicional Titulación</a>
                                            <ul class="submenu dropdown-menu">
                                                <li><a class="dropdown-item" href="#">Imprimir</a></li>
                                                <li><a class="dropdown-item" href="#">Recepcionar</a></li>
                                            </ul>
                                        </li>
                                    </ul>

                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Convalidación</a>
                                    <ul class="submenu dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Imprimir</a></li>
                                        <li><a class="dropdown-item" href="#">Recepcionar</a></li>
                                    </ul>
                                </li>

                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Práctica</a>
                                    <ul class="submenu dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Imprimir</a></li>
                                        <li><a class="dropdown-item" href="#">Recepcionar</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Calificaciones</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Alumno</a>
                                    <ul class="submenu dropdown-menu">
                                        <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Malla</a>
                                            <ul class="submenu dropdown-menu">
                                                <li><a class="dropdown-item" href="#">Modificar</a></li>
                                                <li><a class="dropdown-item" href="#">Convalidar</a></li>
                                                <li><a class="dropdown-item" href="#" 
                                                       <%--'CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=<s:property value="key"--%>
                                                       onclick="executeAction('RegistradorCurricularAddEquivalencia?&key=<s:property value="key"/>');">
                                                        Equivalencia</a></li>

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


                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Inscripciones</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Consultar</a></li>
                                <li><a class="dropdown-item" href="#">Auditar</a></li>
                                <li class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Eliminar Sin Matrícula</a></li>
                                <li><a class="dropdown-item" href="#">Eliminar Situación Irregular</a></li>
                                <li><a class="dropdown-item" href="#">Eliminar Por Asignatura Convalidada</a></li>
                                <li><a class="dropdown-item" href="#">Eliminar Sin Requisitos</a></li>
                                <li class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Generar Ranking</a></li>
                                <li><a class="dropdown-item" href="#">Generar Derechos</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Control</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Generar Eliminados por Causal Académica</a>
                                    <ul class="submenu dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Reglamento</a></li>
                                        <li><a class="dropdown-item" href="#">Progresión</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Generar Eliminados por No Titulación</a>
                                    <ul class="submenu dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Pregrados</a></li>
                                        <li><a class="dropdown-item" href="#">Posgrados</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Generar Eliminados por Abandonos</a>
                                    <ul class="submenu dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Sin Matrícula</a></li>
                                        <li><a class="dropdown-item" href="#">Sin Calificación</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Validar Eliminados</a></li>
                                <li class="dropdown-divider"></li>
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Reincorporación</a>
                                    <ul class="submenu dropdown-menu">
                                        <li><a class="dropdown-item" href="#" onclick="reincorpora('RT');">Retiros Temporales</a></li>
                                        <li><a class="dropdown-item" href="#" onclick="reincorpora('PPL');">Prórroga Periodo Lectivo</a></li>
                                        <li><a class="dropdown-item" href="#" onclick="reincorpora('EL');">Elimados Por Causal Académica</a></li>
                                        <!--li><a class="dropdown-item" href="#" onclick="reincorpora('MA-EL');">Elimados Por Causal Académica (Masiva)</a></-->
                                        <li><a class="dropdown-item" href="#" onclick="executeAction('RegistradorCurricularReincorporacionEliminadoReprintForm');$.unblockUI();">Elimados Por Causal Académica (Reimpresión)</a></li>
                                        <li><a class="dropdown-item" href="#" onclick="executeAction('RegistradorCurricularSolicitudReprintForm');$.unblockUI();">Soliciud Retiro con Causal (Reimpresión)</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Listados</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Calidades</a>
                                    <ul class="submenu dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Egresados</a></li>
                                        <li><a class="dropdown-item" href="#">Titulados</a></li>
                                        <li class="dropdown"><a class="dropdown-item dropdown-toggle" href="#">Graduados</a>
                                            <ul class="submenu dropdown-menu">
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

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Solicitudes</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Cambio de Bachillerato</a></li>
                                <li><a class="dropdown-item" href="#">Autorización de Matrícula</a></li>
                                <li><a class="dropdown-item" href="#">Cambio de Carrera</a></li>
                                <li><a class="dropdown-item" href="#">Cambio de Jornada</a></li>
                                <li><a class="dropdown-item" href="#">Elimina Última Situación</a></li>
                                <li><a class="dropdown-item" href="#">Registrar Anotación</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Titulación</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Imprimir Carátula</a></li>
                                <li><a class="dropdown-item" href="#">Imprimir Acta</a></li>
                                <li><a class="dropdown-item" href="#">Recepcionar Acta</a></li>
                                <li><a class="dropdown-item" href="#">Expediente</a></li>
                                <li><a class="dropdown-item" href="#">Situación Financiera</a></li>
                                <li><a class="dropdown-item" href="#">Pago Arancel</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Mantenedores</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Generales</a></li>
                                <li class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Departamentos</a></li>
                                <li><a class="dropdown-item" href="#">Tipo de Carreras</a></li>
                                <li><a class="dropdown-item" href="#">Especialidades</a></li>
                                <li><a class="dropdown-item" href="#">Carreras</a></li>
                                <li>
                                    <a class="dropdown-item" href="#" onclick="executeAction('CommonRCurricularGetMencion')">
                                        Menciones
                                    </a>
                                </li>
                                <li><a class="dropdown-item" href="#">Planes de Estudio</a></li>
                                <li><a class="dropdown-item" href="#">Asignaturas</a></li>
                                <li><a class="dropdown-item" href="#">Electivos</a></li>
                                <li><a class="dropdown-item" href="#">Mallas Curriculares</a></li>
                                <li><a class="dropdown-item" href="#">Prerequisitos</a></li>
                                <li class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="#">Tipos Trámites</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Parámetros</a>
                            <ul class="dropdown-menu">
                                <%--li><a class="dropdown-item" onclick="executeAction('RegistradorCurricularParametrosGeneralesGet?key=<s:property value="key"/>');">Generales</a></li--%>
                                <li><a class="dropdown-item" onclick="executeAction('RegistradorCurricularMencionGet?key=<s:property value="key"/>&actionCall=RegistradorCurricularParametrosxMencionGet')">x Mención</a></li>
                            </ul>
                        </li>                      

                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown">Utilidades</a>
                            <ul class="dropdown-menu">
                                <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=<s:property value="key"/>');">
                                    <s:text name="label.menu.consulta.alumno"/>
                                </a>
                                <a href="#" class="dropdown-item" onclick="executeAction('CommonAyudanteSearchEnable?actionCall=CommonAyudanteRecordEnable&typeSearch=F&key=<s:property value="key"/>');">
                                    <s:text name="label.menu.consulta.ayudante"/>
                                </a>
                                <a href="#" class="dropdown-item" onclick="executeAction('CommonProfesorSearchEnable?actionCall=CommonProfesorRecordEnable&typeSearch=F&key=<s:property value="key"/>');">
                                    <s:text name="label.menu.consulta.profesor"/>
                                </a>
                                <li><a class="dropdown-item" onclick="executeActionMessage();" href="#"><s:text name="label.menu.mensajes"/></a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- Right nav -->
                    <ul class="nav navbar-nav ms-auto">
                        <li class="nav-item active"><a href="CommonSessionClose?key=<s:property value="key"/>" class="nav-link"><span class="fa fa-sign-out" aria-hidden="true"></span><s:text name="label.menu.close.session"/></a></li>
                    </ul>
                </div>            
            </div> <!-- /container -->
        </nav>

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