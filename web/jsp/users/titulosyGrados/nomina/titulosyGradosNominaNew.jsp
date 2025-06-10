<%--
    Document   : tiitulosyGradosNominaNew
    Created on : 01-08-2018, 10:40:08
    Author     : rcontreras
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
        <title>Creación de Nómina</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/5.3.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/5.3.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.maskedinput.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/titulosyGrados/nomina/titulosyGradosNominaNew-3.0.4.js"></script>
    </head>
    <body class="inner-body">
        <s:form id="nomina-form" method="post" theme="bootstrap">
            <div class="title-div">
                Nueva Nómina
            </div>

            <!-- Contenedor alineado a la izquierda, sin margin auto -->
            <div class="container" style="max-width: 600px; margin-left: 0;">
                <!-- Tipo de Nómina -->
                <div class="row mb-3">
                    <label for="tipo-select" class="col-sm-4 col-form-label text-start">
                        <s:text name="label.nomina.tipo"/>
                    </label>
                    <div class="col-sm-8">
                        <select id="tipo-select" name="tipo-select" class="form-select form-select-sm">
                            <%
                                List<Logro> logros = ContextUtil.getDAO().getLogroPersistence(ActionUtil.getDBUser()).find();
                                for (Logro logro : logros) {
                                    out.println("<option value=" + logro.getLogrCod() + ">" + logro.getLogrDes() + "</option>");
                                }
                            %>
                        </select>
                    </div>
                </div>

                <!-- Año -->
                <div class="row mb-3">
                    <label for="agno" class="col-sm-4 col-form-label text-start">
                        <s:text name="label.year"/>
                    </label>
                    <div class="col-sm-8">
                        <input type="text" id="agno" name="agno" value="<s:property value='agno'/>" maxlength="4" class="form-control form-control-sm" />
                    </div>
                </div>

                <!-- Número de Nómina -->
                <div class="row mb-3">
                    <label for="nomina" class="col-sm-4 col-form-label text-start">
                        <s:text name="label.nomina.nro.nom"/>
                    </label>
                    <div class="col-sm-8">
                        <input type="text" id="nomina" name="nomina" style="text-transform: uppercase;" value="<s:property value='nomina'/>" class="form-control form-control-sm" />
                    </div>
                </div>

                <!-- Fecha de Nómina -->
                <div class="row mb-3">
                    <label for="fechaNomina" class="col-sm-4 col-form-label text-start">
                        <s:text name="label.date"/>
                    </label>
                    <div class="col-sm-8">
                        <input id="fechaNomina" name="fechaNomina" value="<s:date name='#session.genericSession.lastLogin' format='dd/MM/yyyy'/>" class="form-control" />
                    </div>
                </div>

                <!-- Botón de Agregar Alumno -->
                <div class="container container-menu" style="padding-left: 0;">
                    <div class="row">
                        <div id="justified-button-bar" class="col-12 text-start">
                            <button id="add-button" title="Agregar Alumno" type="button" class="btn btn-light" onclick="agregarAlumno(); return false;">
                                <span class="fa fa-plus"></span>&nbsp; <span class="d-none d-sm-inline">Alumno</span>
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Inputs Ocultos -->
                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value='key'/>" />
                    <input type="hidden" id="tipo" name="tipo" value="<s:property value='tipo'/>" />
                    <input type="hidden" id="pos" name="pos" value=""/>
                    <input type="hidden" id="rut" name="rut" value=""/>
                </div>
            </div>
        </s:form>
    </body>
</html>
