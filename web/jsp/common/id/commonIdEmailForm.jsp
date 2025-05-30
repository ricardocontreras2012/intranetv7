<%--
    Document   : commonIdEmailForm
    Created on : 17-01-2010, 08:24:20 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Registro de Email</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/id/commonIdEmailForm-3.0.0.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <%--div class="title-div">
                <s:text name="label.title.email"/>
            </div--%>

            <%--div class="mb-3">
                <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                    &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                </button>
            </div--%>

            <%--div id="info" class="infoBox">
                <s:text name="label.info.email"/>
            </div--%>

            <div class="row">
                <div class="col-lg-12">
                    <form id="email-form" action="#">
                        <div class="form-outline mb-4">
                            <input type="email" id="email" name="email" class="form-control" value="<s:property value="#session.genericSession.email"/>"/>
                            <label class="form-label" for="email"><s:text name="label.email"/></label>
                        </div>

                        <%--div class="form-group row">
                            <label for="email" class="col-lg-2 col-sm-4 control-label"><s:text name="label.email"/></label>
                            <div class="col-lg-3 col-sm-8">
                                <input type="text" class="form-control" id="email" name="email"
                                       value="<s:property value="#session.genericSession.email"/>" />
                            </div>
                        </div--%>
                        
                        <div id="hidden-input-div">
                            <input type="hidden" id="actionDummy" name="actionDummy"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).actionCall"/>"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>