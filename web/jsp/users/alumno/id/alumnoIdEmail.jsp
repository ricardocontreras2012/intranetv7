<%-- 
    Document   : alumnoIdEmail.jsp
    Created on : 06-08-2023, 14:05:27
    Author     : Ricardo
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Correo Electrónico</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />       
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/id/alumnoIdEmail-3.0.0.js"></script>        
    </head>
    <body>
        <%--div class="btn-group">
            <button id="save-button" title="Aceptar" type="button" class="btn btn-light" >
                <span class="fa fa-check-circle"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.accept"/></span>
            </button>
        </div--%>

        <div class="container-fluid">
            <div class="row justify-content-center my-3">
                <div class="col-12 col-sm-9 col-md-6 col-lg-4 col-xl-3">
                    <div class="card">
                        <div class="card-header">
                            <h3>Registro de correo electrónico</h3>
                        </div>
                        <div class="card-body">                            
                            <form id="email-form" action="#" method="post">
                                <div class="form-group mt-3">
                                    <div class="input-group">
                                        <label for="email">Ingrese Correo Electrónico</label><br>                                       
                                    </div>
                                    <div class="input-group">                                        
                                        <input type="email" class="form-control" id="email" name="email" placeholder="" required="required" value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmail"/>" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off" ><br>
                                    </div>
                                </div>
                                <div class="form-group mt-5">
                                    <div class="input-group">
                                        <label for="emailConfirm">Reingrese Correo Electrónico</label><br>                                        
                                    </div>
                                    <div class="input-group">                                        
                                        <input type="email" class="form-control" id="emailConfirm" name="emailConfirm" placeholder="" required="required" value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmail"/>" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete="off" >
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <div class="d-block">
                                    <button id="save-button" type="submit" class="btn btn-light"><span class="fa fa-save" aria-hidden="true"></span>&nbsp;&nbsp;<s:if test="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmail == null">Registrar</s:if><s:else>Confirmar</s:else></button>
                                </div>
                            </div>

                            <div id="hidden-input-div">
                                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>