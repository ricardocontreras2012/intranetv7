<%--
    Document   : commonIdPlusSecurePassword
    Created on : 06-02-2021, 8:57:34
    Author     : Ricardo
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Secure Password</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-modal.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/idPlus/commonIdPlusSecurePassword-3.0.2.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.cambio.password"/>
            </div>
        </div>

        <br><br><br><br><br><br>
        <div class="row d-flex justify-content-center">

            <br>
            <div style="background-color: #00a499; color:white; border-radius: 20px; padding: 20px;">
                &compfn; Longitud de contraseña: mínimo 10 caracteres<br>

                &compfn; Letras mayúsculas: mínimo 1 letra mayúscula<br>

                &compfn; Letras minúsculas: mínimo 1 letra minúscula<br>

                &compfn; Caracteres numéricos: mínimo 1 número<br>

                &compfn; Caracteres especiales(  @#$%&+=_-  ): mínimo 1 carácter especial<br>
            </div>
        </div>

        <br><br><br>
        <div class="row d-flex justify-content-center">
            <s:form id="cambioPassword-form" action="#">
                <div class="form-group row">
                    <label for="passwdActual" class="col-sm-4 control-label"><s:text name="label.password.actual"/></label>
                    <div class="col-sm-6">
                        <input type="password" id="passwdActual" name="passwdActual" size="16" class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="passwdNueva" class="col-sm-4 control-label"><s:text name="label.password.nueva"/></label>
                    <div class="col-sm-6">
                        <input type="password" id="passwdNueva" name="passwdNueva" size="16" class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="passwdConfirm" class="col-sm-4 control-label"><s:text name="label.confirmacion.password"/></label>
                    <div class="col-sm-6">
                        <input type="password" id="passwdConfirm" name="passwdConfirm" size="16" class="form-control" />
                    </div>
                </div>

                <div class="form-group row">
                    <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                        &nbsp; <span class="hidden-xs">Cambiar contraseña</span>
                    </button>
                </div>

                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <s:token name="token"></s:token>
                </div>
            </s:form>
        </div>

        <div class="d-flex align-items-center justify-content-center" style="height: 100vh">
            <div class="modal fade modal-center" id="aviso" role="dialog">
                <div class="modal-dialog" role="document">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><s:text name="error.title"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p><div id="err-div" style="color:red"><s:actionerror cssClass="actionError"/></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
