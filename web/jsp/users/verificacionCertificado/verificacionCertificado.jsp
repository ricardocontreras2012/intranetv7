<%--
    Document   :verificacionCertificado
    Created on : 11-04-2008, 09:12:16 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Verificiación de Autenticidad de Certificados</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-message-bar.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/verificacionCertificado/verificacionCertificado-3.0.0.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row justify-content-center my-3">
                <div class="col-12 col-sm-8 col-md-6 col-xl-4">
                    <div class="card">
                        <div class="card-header"><h3>Verificación<br/>de Certificado</h3></div>
                        <div class="card-body">
                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <div id="errorBox" class="alert alert-danger" role="alert" style="background: #ffbaba;">
                                        <p style="text-align: center"><s:actionerror/><s:actionmessage/><s:fielderror/></p>
                                </div>
                            </s:if>

                            <form id="verificacion-form" action="#" method="post">
                                <table class="table">
                                    <tr>
                                        <td style="width: 30%;  text-transform: uppercase">
                                            <label for="folio"><s:text name="label.folio"/></label>
                                        </td>
                                        <td>
                                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                                <input class="form-control" type="text" name="folio" id="folio"
                                                       value="<s:property value="folio"/>"/>
                                            </s:if>
                                            <s:else>
                                                <input class="form-control" type="text" name="folio" id="folio"/>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label for="verificador"><s:text name="label.verificador"/></label>
                                        </td>
                                        <td>
                                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                                <input class="form-control" type="text" name="verificador" id="verificador"
                                                       value="<s:property value="verificador"/>"/>
                                            </s:if>
                                            <s:else>
                                                <input class="form-control" style="text-transform:uppercase" type="text" name="verificador" id="verificador"/>
                                            </s:else>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div class="card-footer" style="text-align: center;">
                            <button id="verificar-button" name="verificar-button" class="btn btn-light">
                                <span class="fa fa-check-circle" aria-hidden="true"></span>
                                <s:text name="label.button.verificar"/>
                            </button>
                            <button id="clear-button" name="clear-button" class="btn btn-light">
                                <span class="fa fa-eraser" aria-hidden="true"></span>
                                <s:text name="label.button.clear"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>