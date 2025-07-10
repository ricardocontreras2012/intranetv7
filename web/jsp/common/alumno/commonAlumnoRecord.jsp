<%--
    Document   : commonAlumnoRecord
    Created on : 31-07-2010, 11:00:26 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Ficha General del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/alumno/commonAlumnoRecord-3.0.3.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <header class="header-interno mt-3 pl-2">
                <p><s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.getNombre()"/>::&nbsp;&nbsp; Carrera&nbsp;<s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaCodCar"/> <s:property value="#session.genericSession.getWorkSession(key).aluCar.plan.mencion.getNombreCarreraFull()"/>&nbsp;&nbsp;<s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaSemIng"/>/<s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaAgnoIng"/></p>
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
                                <s:if test="#session.genericSession.userType != \"PR\"">
                                    <a class="dropdown-item" onclick="serviceAction('CommonAlumnoGetCalidades', 'CommonAlumnoPrintCalidades')">
                                        <s:text name="label.menu.calidades"/>
                                    </a>
                                </s:if>

                                <s:if test="#session.genericSession.userType in {\"RC\"}">
                                    <a class="dropdown-item" onclick="serviceAction('RegistradorCurricularGetCartola', 'CommonAlumnoPrintCurriculares')">
                                        <s:text name="label.menu.cartola"/>
                                    </a>
                                </s:if>
                                <s:else>
                                    <a class="dropdown-item" onclick="serviceAction('CommonAlumnoGetCalificaciones', 'CommonAlumnoPrintCartola')">
                                        <s:text name="label.menu.cartola"/>
                                    </a>
                                </s:else>
                                <s:if test="#session.genericSession.userType in {\"AS\"}">
                                    <a class="dropdown-item" onclick="serviceAction('AsistenteGetCalificacionesSearch')">
                                        <s:text name="label.menu.calificaciones"/>
                                    </a>
                                </s:if>
                                <a class="dropdown-item" onclick="serviceAction('CommonAlumnoGetDatosCurriculares', 'CommonAlumnoPrintCurriculares')">
                                    <s:text name="label.menu.curriculares"/>
                                </a>

                                <%--a class="dropdown-item" onclick="serviceActionBlank('CommonHorarioGetHorario', 'CommonHorarioPrintHorario')">
                                    <s:text name="label.horario"/>
                                </a--%>
                                
                                <a class="dropdown-item" onclick="serviceActionBlankParm('CommonHorarioGetHorario?id=AL')">
                                    <s:text name="label.horario"/>
                                </a>
                                
                                
                                <a class="dropdown-item" onclick="serviceActionBlank('CommonAlumnoGetMalla')">
                                    <s:text name="label.menu.malla"/>
                                </a>
                                <s:if test="#session.genericSession.userType != \"PR\"">
                                    <a class="dropdown-item" onclick="serviceAction('CommonAlumnoGetMatriculas', 'CommonAlumnoPrintMatriculas')">
                                        <s:text name="label.menu.matriculas"/>
                                    </a>
                                </s:if>
                                <s:if test="#session.genericSession.userType != \"PR\"">
                                    <a class="dropdown-item" onclick="serviceAction('CommonAlumnoGetDatosPersonales', 'CommonAlumnoPrintPersonales')">
                                        <s:text name="label.menu.personales"/>
                                    </a>
                                    <a class="dropdown-item" onclick="serviceAction('CommonAlumnoGetSituaciones', 'CommonAlumnoPrintSituaciones')">
                                        <s:text name="label.menu.situaciones"/>
                                    </a>
                                </s:if>
                                <%--s:if test="#session.genericSession.userType != \"OC\" || #session.genericSession.userType != \"RC\" ||
                                              #session.genericSession.userType != \"DE\" || #session.genericSession.userType != \"VD\" ||
                                              #session.genericSession.userType != \"JC\" || #session.genericSession.userType != \"DD\" ||
                                              #session.genericSession.userType != \"SD\" || #session.genericSession.userType != \"DP\"">
                                            <li><a onclick="serviceAction('CommonAlumnoGetSolicitudes')"><s:text name="label.menu.solicitudes"/></a></li>
                                </s:if--%>
                                <s:if test="#session.genericSession.userType not in {\"AL\", \"PR\",\"CN\",\"TG\"}">
                                    <a class="dropdown-item" onclick="serviceAction('CommonAlumnoGetLogInscripcion', 'CommonAlumnoPrintLogInscripcion')">
                                        <s:text name="label.menu.log.inscripcion"/>
                                    </a>
                                </s:if>
                                <s:if test="#session.genericSession.userType == \"TG\" && #session.genericSession.getWorkSession(key).aluCar.tcalidad.tcaCod==4">                                    
                                    <a class="dropdown-item" onclick="serviceActionBlank('TitulosyGradosAlumnoInformeCalificacionesPrint')">
                                        <s:text  name="label.menu.informe.calificaciones"/>
                                    </a>
                                    <a class="dropdown-item" onclick="serviceActionBlank('TitulosyGradosAlumnoGetLogros')">
                                        <s:text  name="label.menu.certificados"/>
                                    </a>
                                </s:if>
                                <s:if test="#session.genericSession.userType in {\"RC\", \"OC\"}">
                                    <a class="dropdown-item" onclick="serviceActionBlank('CommonAlumnoProgramaEstudioGetCalificaciones')">
                                        <s:text  name="label.menu.programa.estudio"/>
                                    </a>
                                </s:if>

                            </div>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="row content-record">
                <div class="col-lg-6 col-sm-12">
                    <form class="form-horizontal"  id="alumno-form" name="alumno-form" method="post">

                        <div class="form-group">
                            <label for="rut" class="col-lg-1 col-sm-1 control-label text-left"><s:text name="label.rut"/></label>
                            <div class="col-lg-3 col-sm-6">
                                <input type="text" class="form-control" id="rut" name="rut" placeholder="Rut" maxlength="12" size="12" value="<s:property value="#session.genericSession.getWorkSession(key).alumno.aluRut"/>-<s:property value="#session.genericSession.getWorkSession(key).alumno.aluDv"/>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="paterno" class="col-lg-1  col-sm-1 control-label"><s:text name="label.paterno"/></label>
                            <div class="col-lg-8 col-sm-6">
                                <input type="text" class="form-control" id="paterno" name="paterno" placeholder="Paterno" maxlength="100" value="<s:property value="#session.genericSession.getWorkSession(key).alumno.aluPaterno"/>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="materno" class="col-lg-1 col-sm-1 control-label"><s:text name="label.materno"/></label>
                            <div class="col-lg-8 col-sm-6">
                                <input type="text" class="form-control" id="materno" name="materno" placeholder="Materno" maxlength="100" value="<s:property value="#session.genericSession.getWorkSession(key).alumno.aluMaterno"/>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre" class="col-lg-1 col-sm-1 control-label"><s:text name="label.name"/></label>
                            <div class="col-lg-8 col-sm-6">
                                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" maxlength="150" value="<s:if test="#session.genericSession.getWorkSession(key).alumno.aluNombreSocial != null && #session.genericSession.getWorkSession(key).alumno.aluNombreSocial.trim() != ''">
                                <s:property value="#session.genericSession.getWorkSession(key).alumno.aluNombreSocial"/>
                            </s:if>
                            <s:else>
                                <s:property value="#session.genericSession.getWorkSession(key).alumno.aluNombre"/>
                            </s:else>">
                            </div>
                        </div>
                        <div id="hidden-input-div">
                            <input type="hidden" id="printAction" name="printAction" value="CommonAlumnoPrintCurriculares"/>
                            <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                        </div>

                    </form>
                </div>
                <div class="col-lg-6 col-sm-12">
                    <img id="fotoImg"
                         src="CommonAlumnoGetFoto?rut=<s:property value="#session.genericSession.getWorkSession(key).alumno.aluRut"/>&key=<s:property value="key"/>"
                         class="foto"
                         alt="<s:property value="#session.genericSession.getWorkSession(key).alumno.aluRut"/>"/>
                </div>
            </div>
            <div id="search-content-div" class="div-inner-iframe"></div>
        </div>        
    </body>
</html>