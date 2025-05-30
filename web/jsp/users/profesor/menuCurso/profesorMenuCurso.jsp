<%--
    Document   : profesorMenuCurso
    Created on : 15-abr-2018, 17:36:01
    Author     : ricardo
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Menu Curso Intranet-Profesor</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />

        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialMenuCurso-3.0.0.js"></script>
    </head>
    <body>
        <header class="header-interno mt-3 pl-2">
            <p><s:property value="#session.genericSession.getWorkSession(key).curso.nombreFull"/></p>
        </header>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSubNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSubNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="#" class="nav-link" onclick="executeMenuCursoAction('ProfesorActaGetActa')"><s:text name="label.menu.acta.final"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link" onclick="executeMenuCursoAction('CommonCursoGetListaCurso')"><s:text name="label.menu.nominas"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link" onclick="showMaterial()"><s:text name="label.menu.material"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link" onclick="executeMenuCursoAction('ProfesorEvaluacionEnableForm')"><s:text name="label.menu.notas"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link" onclick="executeMenuCursoAction('CommonAsistenciaGetAsistencias')"><s:text name="label.menu.asistencia"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link" onclick="executeMenuCursoAction('CommonReporteGetReportesCurso')"><s:text name="label.menu.reportes"/></a>
                    </li>

                    <li class="nav-item">
                        <a href="#" class="nav-link" onclick="executeMenuCursoAction('ProfesorSolicitudGetJustificativosCurso')">Justificativos PEP</a>
                    </li>                                      
                    
                    <li class="nav-item">
                        <a href="#" class="nav-link" onclick="executeMenuCursoAction('ProfesorHorarioComun')">Horario Común</a>
                    </li>                    
                </ul>
            </div>
        </nav>

        <div class="container-fluid text-center">
            <div class="row content">
                <div class="col-xs-12 col-sm-12 col-md-12 div-inner-iframe">
                    <iframe id="menu-inner-iframe" class="inner-iframe" src="" frameborder="0" allowfullscreen></iframe>
                </div>
            </div>
        </div>

        <form id="curso-form" action="#" method="post">
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                <input type="hidden" id="tipo" name="tipo" value=""/>
            </div>
        </form>

        <div class="modal fade" id="material" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Materiales</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="text-center">Subidos por</div>
                        <p><a onclick="executeGetMaterial('CommonMaterialEnableConsola', 'PR');">Mi</a></p>
                        <p><a onclick="executeGetMaterial('CommonMaterialEnableConsola', 'AY');">Mis Ayudantes</a></p>
                        <p><a onclick="executeGetMaterial('CommonMaterialEnableConsola', 'AL');">Mis Alumnos</a></p>                        
                        <p><a onclick="closeMsg();executeMenuCursoAction('ProfesorMaterialGetListaCursosReutilizacion');">Mi en otros cursos para reutilizarlos</a></p>
                    </div>
                    <div class="modal-footer">                       
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
