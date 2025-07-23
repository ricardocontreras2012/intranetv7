<%--
    Document   : titulosyGradosNominaSearch
    Created on : 19-08-2013, 01:31:19 PM
    Author     : Ricardo Contreras S.
--%>

<%@page import="infrastructure.util.ActionUtil"%>
<%@page import="infrastructure.util.AppStaticsUtil"%>
<%@page import="domain.model.Logro"%>
<%@page import="java.util.List"%>
<%@ page import="infrastructure.util.ContextUtil" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Búsqueda de Nóminas</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.maskedinput.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/titulosyGrados/nomina/titulosyGradosNominaSearch-3.0.3.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.nominas"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">

                        <div class="btn-group">
                            <button id="search-button" title="Buscar" type="button" class="btn btn-light" >
                                <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <s:form id="nomina-form" method="post" theme="bootstrap">
            <table class="table" style="max-width: 50%">
                <tr>
                    <td style="width: 40%"><label for="tipo-select"><s:text name="label.nomina.tipo"/></label></td>
                    <td><select id="tipo-select" name="tipo-select" class="form-control input-sm">
                            <%
                                List<Logro> logros = ContextUtil.getDAO().getLogroRepository(ActionUtil.getDBUser()).find();
                                out.println("<option value=\"\">Seleccione Logro</option>");
                                for (Logro logro : logros) {
                                    out.println("<option value=" + logro.getLogrCod() + ">" + logro.getLogrDes() + "</option>");
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="agno"><s:text name="label.year"/></label></td>
                    <td><input type="text" id="agno" name="agno" value="<s:property value="agno"/>" maxlength="4" class="form-control input-sm input-agno"/></td>
                </tr>
                <tr>
                    <td><label for="nomina"><s:text name="label.nomina.nro.nom"/></label></td>
                    <td><input type="text" id="nomina" name="nomina" style="text-transform: uppercase;" value="<s:property value="nomina"/>" class="form-control input-sm"/></td>
                </tr>
            </table>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>" />
                <input type="hidden" id="tipo" name="tipo" value="<s:property value="tipo"/>" />
                <input type="hidden" id="rut" name="rut" value=""/>
            </div>
        </s:form>
    </body>
</html>
