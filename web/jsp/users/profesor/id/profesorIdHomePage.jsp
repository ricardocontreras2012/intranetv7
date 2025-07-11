<%--
    Document   : profesorIdHomePage
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
        <title>HomePage Intranet-Profesor</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/id/profesorIdHomePage-3.0.1.js"></script>
    </head>
    <body>
        <header class="header-home">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <p><s:if test="#session.genericSession.profesor.profSexo==1">
                        BIENVENIDA
                        </s:if>
                        <s:else>
                        BIENVENIDO
                        </s:else>                        
                        &nbsp;<s:property value="#session.genericSession.nombre"/></p>
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
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonProfesorGetMisDatos')">
                                <s:text name="label.menu.mis.datos"/></a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonPasswordEnableForm')">
                                <s:text name="label.password"/></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.mis.carreras"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeAction('ProfesorNormativaMencion')">
                                <s:text name="label.menu.normativa"/></a>
                            <a href="#" class="dropdown-item" onclick="executeAction('ProfesorCalendarioMencion')">
                                <s:text name="label.menu.calendario"/></a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <s:text name="label.menu.mis.cursos"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                                <a href="#" class="dropdown-item" onclick="getMenuCurso(<s:property value="#row.count -1"/>);">
                                    <s:property value="nombreFull"/>
                                </a>
                            </s:iterator>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown_utilidades" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <s:text name="label.menu.utilidades"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeActionParm('CommonHorarioGetHorario?id=PR')">
                                <s:text name="label.horario"/>
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeActionNested('CommonAlumnoSearchEnable?actionCall=CommonAlumnoRecordEnable&actionNested=CommonAlumnoGetRecord&typeSearch=F&key=<s:property value="key"/>')">
                                <s:text name="label.menu.consulta.alumno"/>
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeActionNested('CommonAyudanteSearchEnable?actionCall=CommonAyudanteRecordEnable&typeSearch=F&key=<s:property value="key"/>')">
                                <s:text name="label.menu.consulta.ayudante"/>
                            </a>
                            <a href="#" class="dropdown-item" id="mensajes-li"></a>
                            <a href="#" class="dropdown-item" onclick="executeActionNested('CommonProfesorSearchEnable?actionCall=CommonReporteGetCargaHistorica&typeSearch=F&key=<s:property value="key"/>')">
                                <s:text name="label.menu.otros.reportes"/>
                            </a>
                            <%--a href="#" class="dropdown-item" id="solictFirma" onclick="executeAction('CommonProfesorSolicitudPAF')">
                                Solicitud de firma
                            </a--%>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><s:text name="label.menu.historico"/></a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a href="#" class="dropdown-item" onclick="executeAction('ProfesorActaGetCargaHistorica')">
                                <s:text name="label.menu.acta.final"/>
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeAction('ProfesorActaRectificatoriaGetCursos')">
                                <s:text name="label.menu.acta.rectificatoria"/>
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonProfesorCargaHistorica')">
                                <s:text name="label.menu.historico.carga"/>
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonEncuestaGetCargaHistorica?tipo=I&key=<s:property value="key"/>')">
                                <s:text name="label.menu.encuestas"/>
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeSimpleAction('CommonEncuestaGetCargaHistorica?tipo=V&key=<s:property value="key"/>')">
                                Encuestas Intermedias
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonReporteGetCargaHistorica')">
                                <s:text name="label.menu.reportes"/>
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonMaterialSelectTipo')">
                                <s:text name="label.menu.material"/>
                            </a>
                            <a href="#" class="dropdown-item" onclick="executeAction('CommonNotasParcialesGetCargaHistorica')">
                                <s:text name="label.menu.notas"/>
                            </a>
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
                    <iframe id="main-content-iframe" src="ProfesorLoginWelcome?key=<s:property value="key"/>" style="width: 100%; height: 90vh; border: none;" allowfullscreen loading="lazy"></iframe>                   
                </div>
            </div>
        </div>

        <form id="profesor-form" action="#" method="post">
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
            </div>
        </form>

        <footer class="page-footer fixed-bottom text-center">
            <p><s:text name="label.footer"/></p>
        </footer>
    </body>
</html>
