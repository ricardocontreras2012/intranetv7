<%--
    Document   : alumnoInscripcionConsola
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
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/inscripcion/alumnoInscripcionConsola-3.0.17.js"></script>
    </head>
    <body class="inner-body" style="font-size: 14px;">
        <div class="title-div">
            <s:text name="label.title.inscripcion.asignaturas"/>  <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.getNombreStd()"/>
        </div>

        <form id="inscripcion-form" action="#" method="post" class="form-inline"> 
            <div class="container-fluid">
                <!--  iframe inscripcion -->
                <div class="row" style="height: 50vh; overflow: hidden;">
                    <div class="col-12">
                        <iframe id="inscripcion-iframe" class="embed-responsive-item"
                                style="height: 50vh; width: 100%; overflow: auto; border: none;" 
                                allowfullscreen loading="lazy"></iframe>
                    </div>
                </div>

                <div class="row" style="overflow: hidden;">
                    <!-- Derechos -->
                    <div class="col-12 col-md-6">
                        <iframe id="derechos-iframe" class="embed-responsive-item"
                                src="AlumnoInscripcionGetDerechos?key=<s:property value='key'/>"
                                style="height: 50vh; width: 100%; overflow: auto; border: none;" 
                                allowfullscreen loading="lazy"></iframe>
                    </div>

                    <!-- Cursos -->
                    <div class="col-12 col-md-6">
                        <iframe id="cursos-iframe" class="embed-responsive-item"
                                style="height: 50vh; width: 100%; overflow: auto; border: none;" 
                                allowfullscreen loading="lazy"></iframe>
                    </div>
                </div>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="xxx" name="xxx" value="<s:property value="#session.genericSession.getWorkSession(key).derecho.derMencion"/>"/>
                <input type="hidden" id="men" name="men" value="aaaa"/>
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

        <div class="modal fade" id="aviso" role="dialog">
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

        <div class="modal fade" id="confirmacion" role="dialog">
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
                        <p>La desinscripción de asignaturas de un nivel inferior implicará automáticamente la desinscripción de las asignaturas de niveles superiores. ¿Desea proceder con la desinscripción?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="removeInscripcion();">SI</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>                

        <div class="modal fade" id="swap" role="dialog">
            <div class="modal-dialog modal-xl" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Cambio de curso: <div id="title-swap-div"></div></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <iframe id="swap-iframe" width="640" height="360" style="border: none;" allow="autoplay; fullscreen" allowfullscreen loading="lazy"></iframe>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div> 

        <div class="modal fade" id="aviso-swap" role="dialog">
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
                        <p><s:text name="alert.seleccione.curso"/></p>
                    </div>
                    <div class="modal-footer">
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

        <div class="modal fade" id="cambio-mencion1" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/>1</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <s:if test="#session.genericSession.getWorkSession(key).aluCar.acaCodMen == 0">
                        <div class="modal-body">                        
                            <p>Usted se encuentra en <s:property value="#session.genericSession.getWorkSession(key).aluCar.getNombreCarrera"/> </p>                        
                            <p>Desea cambiar/ingresar a <div id="men1-div"></div></p>
                            <s:text name="confirmation.cambio.mencion"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="cambiarMencion();">SI</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </s:if>
                    <s:else>
                        <div class="modal-body">                        
                            <p>Para realizar el cambio a esta nueva mención debe solicitarlo al correo ivan.jorquera@usach.cl</p>
                        </div>
                        <div class="modal-footer">                        
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </s:else>
                </div>
            </div>
        </div>
                        
        <div class="modal fade" id="cambio-mencion2" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/>1</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <s:if test="#session.genericSession.getWorkSession(key).aluCar.acaCodMen == 0">
                        <div class="modal-body">                        
                            <p>Usted se encuentra en <s:property value="#session.genericSession.getWorkSession(key).aluCar.getNombreCarrera"/> </p>                        
                            <p>Desea cambiar/ingresar a <div id="men2-div"></div></p>
                            <s:text name="confirmation.cambio.mencion"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="cambiarMencion();">SI</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </s:if>
                    <s:else>
                        <div class="modal-body">                        
                            <p>Para realizar el cambio a esta nueva mención debe solicitarlo al correo ivan.jorquera@usach.cl</p>
                        </div>
                        <div class="modal-footer">                        
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </s:else>
                </div>
            </div>
        </div>                
                        

        <div class="modal fade" id="cambio-mencion2" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/>2</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">                        
                        <p>Para realizar el cambio a esta nueva mención debe solicitarlo al correo ivan.jorquera@usach.cl</p>
                    </div>
                    <div class="modal-footer">                        
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>