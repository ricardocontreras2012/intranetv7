<%--
    Document   : alumnoCertificadoGetCertificados
    Created on : 17-01-2010, 08:43:27 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Certificados</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/certificado/alumnoCertificadoGetCertificados-3.0.5.js"></script>
    </head>
    <body class="inner-body container-fluid">
        <div class="title-div">
            <s:text name="label.title.certificacion"/>
        </div>

        <div>
            <button id="next-button" title="Next" type="button" class="btn btn-light" >
                <span class="fa fa-step-forward"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.next"/></span>
            </button>
        </div>

        <div class="mt-2 col-12 col-md-6">
            <s:form id="certificacion-form" action="#" method="post" theme="bootstrap">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Certificado</th>
                            <th scope="col">Observación</th>
                            <%--th scope="col">Valor ($)</th--%>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.alumnoSession.certList" status="row">
                            <tr>
                                <td><input type="radio" name="tcert" onclick="setPos(<s:property value="#row.count -1"/>, <s:property value="certTipo"/>);"></td>
                                <td><s:property value="certDes"/></td>                                        
                                <td><s:property value="certAviso"/></td>
                                <%--td style="text-align: right"><s:property value="certValor"/></td--%>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="pos" name="pos" />
                    <input name="tipo" id="tipo" type="hidden" />
                    <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                </div>
            </s:form>
        </div>
    </body>
</html>
