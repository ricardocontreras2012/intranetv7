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
        <script type="text/javascript" src="/intranetv7/js/datePicker/moment-with-locales.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/bootstrap-datetimepicker.js"></script>
        <link rel="stylesheet" href="/intranetv7/css/datePicker/bootstrap-datetimepicker.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/local/users/titulosyGrados/nomina/titulosyGradosNominaShow-3.0.4.js"></script>
    </head>
    <body class="inner-body">
        <s:form id="nomina-form" method="post" theme="bootstrap">
            <div class="title-div">
                <s:text name="label.title.nominas"/>&nbsp;<s:property value="agno"/>&nbsp;<s:property value="nomina"/>
            </div>

            <div class="container container-menu">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <s:if test="%{nomina != null}">
                                <%--div class="btn-group">
                                    <button id="print-nomina-button" title="Imprimir Nómina Rector" type="button" class="btn btn-light"  onclick="printNominaRector();return false;">
                                        <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs">Nómina Rector</span>
                                    </button>
                                </div--%>
                                <div class="btn-group">
                                    <button id="print-resolucion-button" title="Imprimir Resolución" type="button" class="btn btn-light"  onclick="printResolucion();return false;">
                                        <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs">Resolución</span>
                                    </button>
                                </div>
                                <div class="btn-group">
                                    <button id="delete-button" title="Eliminar" type="button" class="btn btn-light"  onclick="remove();return false;">
                                        <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                                    </button>
                                </div>
                            </s:if>
                        </div>
                    </div>
                </div>
            </div>

            <s:if test="%{nomina != null}">
                <div class="data-tables-container">
                    <table id="nomina-table" class="table table-striped table-bordered dataTable">
                        <thead>
                            <tr>
                                <th rowspan="2" scope="col" style="text-align:center; width: 8px"><label for="checkHeadInput"></label><input
                                        id="checkHeadInput" name="checkHeadInput"
                                        type="checkbox"
                                        onclick="checkingHead('nomina-form');"
                                        value="off"/></th>
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
                                    <td style="text-align:center;vertical-align: middle;width: 8px"><input type="checkbox"
                                                                                     id="ck_<s:property value="#row.count -1"/>"
                                                                                     name="ck_<s:property value="#row.count -1"/>"
                                                                                     value="p"/></td>
                                    <td style="padding:0;text-align: left;vertical-align: middle"><s:property value="aluCar.alumno.aluRut"/>-<s:property value="aluCar.alumno.aluDv"/></td>
                                    <td style="padding:0;vertical-align: middle"><s:property value="aluCar.alumno.getNombre()"/></td>
                                    <td style="padding:0;vertical-align: middle"><s:property value="aluCar.getNombreCarrera()"/></td>

                                    <td style="padding:0;width: 70px; text-align:center;vertical-align: middle;"><input style=" width: 70px; text-align: right" id="ne_<s:property value="#row.count"/>" name="ne_<s:property value="#row.count"/>" value="<s:property value="explNumExp"/>" maxlength="5" /></td>
                                    <td style="padding:0;width: 80px; text-align:center;vertical-align: middle;"><input style=" width: 100px; text-align: center" id="fe_<s:property value="#row.count"/>" name="fe_<s:property value="#row.count"/>" value="<s:date name="explFecExp" format="dd/MM/yyyy"/>" size="12" maxlength="12" /></td>
                                    <td style="padding:0;width: 70px; text-align:center;vertical-align: middle;"><input style=" width: 70px; text-align: right" id="rol_<s:property value="#row.count"/>" name ="rol_<s:property value="#row.count"/>" value="<s:property value="explRol"/>"  maxlength="7" /></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </s:if>

            <div class="modal fade" id="aviso" role="dialog">
                <div class="modal-dialog" role="document">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Seleccione Alumno(s)</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <%--div class="modal fade" id="confirmacion" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Desea eliminar alumno(s) de la nómina?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="alert('hola')">OK</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div--%>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>" />
                <input type="hidden" id="tipo" name="tipo" value="<s:property value="tipo"/>" />
                <input type="hidden" id="pos" name="pos" value=""/>
                <input type="hidden" id="rut" name="rut" value=""/>
            </div>
        </s:form>
    </body>
</html>
