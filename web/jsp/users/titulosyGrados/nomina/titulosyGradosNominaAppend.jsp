<%--
    Document   : titulosyGradosNominaShow
    Created on : 19-08-2013, 01:31:19 PM
    Author     : Ricardo Contreras S.
--%>

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
        <title>Nómina</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.maskedinput.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/titulosyGrados/nomina/titulosyGradosNominaAppend-3.0.4.js"></script>
    </head>
    <body class="inner-body">
        <s:form id="nomina-form" method="post" theme="bootstrap">
            <div class="title-div">
                <s:text name="label.title.nominas"/><s:property value="agno"/><s:property value="nomina"/>
            </div>

            <div class="container container-menu">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <div class="btn-group">
                                <button id="add-button" title="Agregar Alumno" type="button" class="btn btn-light"  onclick="agregarAlumno(); return false;">
                                    <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs">Alumno</span>
                                </button>
                            </div>
                            <%--s:if test="%{nomina != null}"--%>

                            <div class="btn-group">
                                <button id="save-nomina-button" title="Grabar Nómina" type="button" class="btn btn-light"  onclick="saveNomina();return false;">
                                    <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs">Grabar Nómina/STD</span>
                                </button>
                            </div>
                            <%--/s:if--%>
                        </div>
                    </div>
                </div>
            </div>


            <div class="data-tables-container">
                <table id="nomina-table" class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th rowspan="2" scope="col"><s:text name="label.rut"/></th>
                            <th rowspan="2" scope="col"><s:text name="label.name"/></th>
                            <th rowspan="2" scope="col"><s:text name="label.carrera.programa"/></th>
                            <th scope="col" style="width: 70px; text-align:center"><s:text name="label.nomina.nro.exp"/></th>
                            <th scope="col" style="width: 80px; text-align:center"><s:text name="label.nomina.fecha.exp"/></th>
                            <th rowspan="2" scope="col" style=" text-align:center"><s:text name="label.nomina.rol"/></th>
                        </tr>

                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).ExpedienteLogroList" status="row">
                            <tr>
                                <td style="padding:0; text-align: left"><s:property value="aluCar.alumno.aluRut"/>-<s:property value="aluCar.alumno.aluDv"/></td>
                                <td style="padding:0"><s:property value="aluCar.alumno.getNombre()"/></td>
                                <td style="padding:0"><s:property value="aluCar.getNombreCarrera()"/></td>
                                <td style="padding:0;width: 70px; text-align:center"><input style=" width: 70px; text-align: right" id="ne_<s:property value="#row.count -1"/>" name="ne_<s:property value="#row.count -1"/>" value="<s:property value="explNumExp"/>" maxlength="5" /></td>
                                <td style="padding:0;width: 80px; text-align:center"><input style=" width: 80px; text-align: center" id="fe_<s:property value="#row.count -1"/>" name="fe_<s:property value="#row.count -1"/>" value="<s:date name="explFecExp" format="dd/MM/yyyy"/>" size="10" maxlength="10" /></td>
                                <td style="padding:0;width: 70px; text-align:center"><input style=" width: 70px; text-align: right" id="rol_<s:property value="#row.count -1"/>" name ="rol_<s:property value="#row.count-1"/>" value="<s:property value="explRol"/>"  maxlength="7" /></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>" />
                <input type="hidden" id="tipo" name="tipo" value="<s:property value="tipo"/>" />
                <input type="hidden" id="pos" name="pos" value=""/>
                <input type="hidden" id="rut" name="rut" value=""/>
            </div>
        </s:form>
    </body>
</html>
