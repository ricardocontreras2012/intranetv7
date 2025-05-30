<%--
    Document   : commonIdPlusLogIn
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
        <title>LogIn Plus Intranet Académica USACH</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/idPlus/commonIdPlusLogIn-3.0.1.js"></script>
    </head>
    <body style="background: #ebeced;">
        <div class="container-fluid">
            <div class="row justify-content-center my-3">
                <div class="col-12 col-sm-9 col-md-6 col-lg-4 col-xl-3">
                    <div class="card">
                        <div class="card-header" style="text-align: center;">
                            <img src="/intranetv7/images/local/logo-usach/logo-sai-grey-200.png" alt=""/>
                        </div>
                        <div class="card-body">
                            <s:form id="login-form" action="#" method="post" theme="bootstrap">
                                <s:if test="#session != null && #session.loginSessionSupport != null && #session.loginSessionSupport.userTypeMap != null">
                                    <s:select name="usuarios"
                                              id="usuarios"
                                              headerKey=""
                                              headerValue="Seleccione Tipo de Usuario"
                                              list="#session.loginSessionSupport.userTypeMap"
                                              />
                                    <div class="form-group mt-3">
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><span class="fa fa-user" aria-hidden="true"></span></span>
                                            </div>
                                            <input type="text" class="form-control" id="rutdv" name="rutdv" placeholder="RUN" required="required">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text"><span class="fa fa-key" aria-hidden="true"></span></span>
                                            </div>
                                            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class="d-block">
                                        <button id="login-button" type="submit" class="btn btn-light"><span class="fa fa-unlock-alt" aria-hidden="true"></span>&nbsp;&nbsp;Ingresar</button>                                        
                                    </div>
                                    <div class="d-block mt-3">
                                        <a href="/intranetv7/CommonLoginSolicitudPlusEnable"><s:text
                                                name="label.olvide.password"/></a>
                                    </div>
                                </div>
                            </div>

                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <div class="errorMessage">
                                    <s:actionerror/><s:actionmessage/><s:fielderror/>
                                </div>
                            </s:if>

                            <div id="hidden-input-div">
                                <input type="hidden" id="rut" name="rut" value=""/>
                                <input type="hidden" id="userType" name="userType"
                                       value="<s:property value="#session.loginSessionSupport.userType"/>"/>
                                <s:token name="token"></s:token>
                                </div>
                        </s:if>
                    </s:form>                    
                </div>
            </div>
        </div>
        <div class="version-bottom-right">
            <div class="alert alert-secondary"><s:text name="label.title.version"/></div>                
        </div>
    </body>
</html>