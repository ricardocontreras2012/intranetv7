<%--
    Document   : commonInscripcionConsola
    Created on : 28-04-2010, 07:01:48 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consola de Inscripción de Cursos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />        
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/inscripcion/commonInscripcionConsola-3.0.8.js"></script>
    </head>
    <body class="inner-body"  style="font-size: 16px;">
        <div class="title-div">
            <s:text name="label.title.inscripcion.asignaturas"/>  <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.getNombreStd()"/>
        </div>

        <form id="inscripcion-form" action="#" method="post" class="form-inline">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-sm-6 order-1 order-sm-2" >
                        <div style="border: 1px solid grey; width: 120px; height: 135px; margin: 4px auto; padding: 5px;">
                            <img id="fotoImg"
                                 src="CommonAlumnoGetFoto?rut=<s:property value="#session.genericSession.getWorkSession(key).alumno.aluRut"/>&key=<s:property value="key"/>"
                                 height="125" width="110"
                                 alt="<s:property value="#session.genericSession.getWorkSession(key).alumno.aluRut"/>"/>
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 order-2 order-sm-1 d-flex align-middle">
                        <label class=" font-weight-bold" for="sem"><s:text name="label.term.short"/>&nbsp;/&nbsp;<label class=" font-weight-bold" for="agno"><s:text name="label.year"/></label>

                            &nbsp;<input id="sem" name="sem" value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>"
                                         maxlength="1" size="1" style="width:40px;text-align: center" class="form-control"/>
                            &nbsp;<input id="agno" name="agno"
                                         value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>" maxlength="4"
                                         size="4"  style="width:80px;text-align: center" class="form-control"/>

                            &nbsp;&nbsp;<button id="search-button" title="Buscar" type="button" class="btn btn-light"  onclick="getInscripcion();">
                                <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                            </button>
                    </div>
                </div>     
            </div>

            <div class="container-fluid">                
                <div class="row" style="height: 40vh; overflow: hidden;">
                    <div class="col-12">
                        <iframe id="inscripcion-iframe" class="embed-responsive-item"
                                allowfullscreen style="height: 40vh; width: 100%; overflow: auto; border:0"></iframe>
                    </div>
                </div>     
                
                <div class="row" style="overflow: hidden;">    
                    <div class="col-12 col-md-6">
                        <iframe id="derechos-iframe" class="embed-responsive-item" src="CommonInscripcionGetDerechos?key=<s:property value="key"/>"
                                allowfullscreen style="height: 60vh; width: 100%;overflow: auto; border:0"></iframe>
                    </div>
                    <div class="col-12 col-md-6">
                        <iframe id="cursos-iframe" class="embed-responsive-item" 
                                allowfullscreen style="height: 60vh; width: 100%;overflow: auto; border:0"></iframe>
                    </div>
                </div>                             
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>

        <div class="modal fade" id="msg-error" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="msg-error-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>        

        <div id="msg-confirmacion" class="modal fade" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="msg-confirmacion-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="inscribir();">SI</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">NO</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="aviso-elimina" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="alert.seleccione.curso.desinscripcion"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="confirmacion-elimina" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="confirmation.eliminacion.inscripcion"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="removeInscripcion();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
                    
        <div class="modal fade" id="profesor" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Profesor</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="profesor-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>            
    </body>
</html>