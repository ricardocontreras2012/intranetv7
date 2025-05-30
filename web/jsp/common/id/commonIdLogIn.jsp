<%--
    Document   : commonIdLogIn
    Created on : 17-04-2009, 11:15:49 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>LogIn Intranet Académica USACH</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-claveunica.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.claveunica-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/id/commonIdLogIn-3.0.1.js"></script>
    </head>
    <body style="background: #00a499;">
        <div class="container-fluid">
            <div class="row justify-content-center my-3">
                <div class="col-12" style="text-align: center;">
                    <img src="/intranetv7/images/local/logo-usach/logo-sai-usach.png" alt="" class="logo-login mb-5"/>
                </div>
            </div>
            <div class="row justify-content-center my-3">
                <div class="col-12 col-sm-9 col-md-6 col-lg-4 col-xl-3">
                    <s:form id="login-form" action="#" method="post" theme="bootstrap">
                        <s:if test="#session != null && #session.loginSessionSupport != null && #session.loginSessionSupport.userTypeMap != null">
                            <s:select name="usuarios"
                                      id="usuarios"
                                      headerKey=""
                                      headerValue="Seleccione Tipo de Usuario"
                                      list="#session.loginSessionSupport.userTypeMap"
                                      />
                            <div class="form-group mt-3 mb-0">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="rutdv" name="rutdv" placeholder="RUN" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
                                </div>
                            </div>

                            <div class="d-block">
                                <button id="login-button" type="submit" class="btn btn-warning w-100">Ingresar</button>

                                <div class="alert alert-light mt-2 text-center">

                                    <p style="color:red">Alumn@s nuev@s su clave es su RUN sin puntos ni dígito verificador.</p>
                                    <p>Correo de Ayuda:</p>
                                    <p>Alumn@s Facultad de Administración y Economía: <strong>ivan.jorquera@usach.cl</strong></p>
                                    <p>Alumn@s Facultad de Derecho: <strong>karina.munoz@usach.cl</strong></p>
                                </div>
                            </div>
                            <div class="d-block mt-3 text-center">
                                <a href="/intranetv7/CommonLoginSolicitudEnable" style="color: #fff"><s:text
                                        name="label.olvide.password"/></a>
                            </div>

                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <div class="errorMessage">
                                    <s:actionerror/><s:actionmessage/><s:fielderror/>
                                </div>
                            </s:if>
                            <div class="card mt-5">
                                <div class="card-body" style="text-align: center;">
                                    <h5 class="mb-3">Puedes ingresar usando tu ClaveÚnica</h5>
                                    <a class="btn-cu btn-m btn-color-estandar" onClick="getClaveUnica();" style="display: block; margin: 0 auto;">
                                        <span class="cl-claveunica"></span>
                                        <span class="texto">Iniciar sesión</span>
                                    </a>
                                </div>
                            </div>                            
                            <div id="hidden-input-div">
                                <input type="hidden" id="rut" name="rut" value=""/>
                                <input type="hidden" id="userType" name="userType"
                                       value="<s:property value="#session.loginSessionSupport.userType"/>"/>
                                <s:token name="token"></s:token>
                                </div>
                        </s:if>
                    </s:form>

                    <form id="claveunica-form">
                        <div class="modal fade" id="clave-unica-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Seleccione Tipo de Usuario</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="select-div">
                                            <label><input type="radio" name="user" value="AL"> Alumno</label><br>
                                            <label><input type="radio" name="user" value="PR"> Profesor</label>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-light" onclick="getUser();">OK</button>
                                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>