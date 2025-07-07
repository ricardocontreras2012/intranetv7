<%--
    Document   : commonSolicitudListado
    Created on : 13-09-2010, 08:35:03 PM
    Authors     : Ricardo Contreras S and Javier Frez Valdenegro.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Solicitudes</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/solicitud/commonSolicitudListado-3.0.3.js"></script>
    </head>
    <body class="inner-body" onload="blockBack();onLoad();">
        <div class="title-div">
            <s:text name="label.title.solicitudes"/>
        </div>

        <s:form id="solicitudes-form" action="#" theme="bootstrap">
            <div class="row">
                <div class="col-lg-12">
                    <div id="estado-div" style ="margin-top: 15px; margin-bottom: 15px;">
                        <%--s:if test="#session.genericSession.userType in {\"SF\"}">
                            <select name="estadoList" id="estadoList" class="input" onchange="verSolicitudes()">
                                <option value="65">En Trámite</option>
                                <option value="40">Resuelta</option>                                
                            </select>
                        </s:if>
                        <s:else>
                            <s:action name="CommonSolicitudGetEstados" executeResult="true">
                                <s:param name="key"><s:property value="key"/></s:param>
                            </s:action>
                        </s:else--%>

                        <s:action name="CommonSolicitudGetEstados" executeResult="true" >
                            <s:param name="key"><s:property value="key"/></s:param>
                        </s:action>

                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="col-lg-12">
                        <div class="form-row">
                            <div class="col-12 col-sm-6 col-md-5 col-lg-2 mb-3">
                                <label for="inicio">Fecha de Inicio</label>
                                <input id="inicio" name="inicio" value="<s:property value="inicio"/>"/>
                            </div>
                            <div class="col-12 col-sm-6 col-md-5 col-lg-2 mb-3">
                                <label for="termino">Fecha de Término</label>
                                <input id="termino" name="termino" value="<s:property value="termino"/>"/>
                            </div>
                            <div style="display: flex; flex-direction: column-reverse;">
                                <button style="position: relative; bottom: 15px;" id="reload-button" class="btn btn-light" type="button">Recargar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="data-tables-container">
                <table id="solicitudes-table" class="table table-striped dataTable" style="width:100%">
                    <thead>
                        <tr>
                            <th scope="col" style="width:5%"><s:text name="label.nro"/></th>
                            <th scope="col" style="width:40%"><s:text name="label.name"/></th>
                            <th scope="col" style="width:435%"><s:text name="label.solicita"/></th>
                            <th scope="col" style="width:435%"><s:text name="label.date"/></th>
                            <th scope="col" style="width:10%"><s:text name="label.estado"/></th>
                            <th scope="col" style="width:10%"><s:text name="label.resolucion"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).solicitudList" status="row">
                            <tr>
                                <td>
                                    <a id="sol0_<s:property value="#row.count -1"/>">
                                        <s:property value="solFolio"/>
                                    </a>
                                </td>
                                <td>
                                    <a id="sol1_<s:property value="#row.count -1"/>">
                                        <s:property value="aluCar.alumno.getNombre()"/>
                                    </a>
                                </td>
                                <td>
                                    <a id="sol2_<s:property value="#row.count -1"/>">
                                        <s:property value="solSolicita"/>
                                    </a>
                                </td>
                                <td>
                                    <a id="sol3_<s:property value="#row.count -1"/>">
                                        <span style="display:none;">
                                            <s:property value="solFecha.getTime()"/>
                                        </span>
                                        <s:property value="getFormattedFullDate()"/>
                                    </a>
                                </td>
                                <td>
                                    <a id="sol4_<s:property value="#row.count -1"/>">
                                        <s:property value="estadoSolicitud.esolDes"/>
                                    </a>
                                </td>
                                <td>
                                    <a id="sol5_<s:property value="#row.count -1"/>">
                                        <s:property value="getResolucion()"/>
                                    </a>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="contentDisposition" name="contentDisposition" value='attachment;filename="SOLICITUD.XLS"'/>
                <input type="hidden" id="format" name="format"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="estado" name="estado" value="<s:property value="estado"/>"/>
            </div>
        </s:form>
    </body>
</html>
