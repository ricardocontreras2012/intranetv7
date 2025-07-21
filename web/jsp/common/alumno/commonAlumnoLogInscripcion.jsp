<%--
    Document   : commonAlumnoLogInscripcion
    Created on : 11-08-2010, 01:47:54 PM
    Author     : Ricardo Contreras S and Javier Frez V.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Auditoría de la Inscripción del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/alumno/commonAlumnoLogInscripcion-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            Auditoría de Inscripción
        </div>
        
        <div class="container-fluid pt-2 pb-2">
            <div class="row">
                <div class="form-inline row">
                    <div class="col-md-3">                                
                        Sem/Año
                    </div>
                    <div class="col-md-9">
                        <input id="semInput" style="width: 40px; margin-left: 20px;" name="semInput" value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>" maxlength="1" size="1" class="form-control input-sm">
                        <input id="agnoInput" name="agnoInput" value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>" maxlength="4" size="4" class="form-control input-sm">
                    </div>
                </div>
                <button id="buscar-button" title="buscar-button" type="button" class="btn btn-light" >
                    <span class="fa fa-pencil"></span>&nbsp; <span class="hidden-xs">Buscar</span>
                </button>
            </div>
        </div>

        <s:form id="logInscripcion-form" action="#" theme="bootstrap">
            <table id="logInscripcion-table" class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col"><s:text name="label.date"/></th>
                        <th scope="col"><s:text name="label.code"/></th>
                        <th scope="col"><s:text name="label.coordinacion"/></th>
                        <th scope="col"><s:text name="label.term.short"/> <s:text name="label.year"/></th>
                        <th scope="col"><s:text name="label.name"/></th>
                        <th scope="col"><s:text name="label.accion"/></th>
                        <th scope="col"><s:text name="label.motivo"/></th>
                        <th scope="col"><s:text name="label.user"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="logInscripcionList" status="row">
                        <tr>
                            <td style="width: 10%">
                                <s:date name="logFecha" format="dd/MM/yyyy HH:mm:ss"/>
                            </td>
                            <td style="width: 10%;" align="right">
                                <s:property value="asignatura.asiCod"/>-<s:property value="logElect"/>
                            </td>
                            <td style="width: 10%;" align="right">
                                <s:property value="logCoord"/>-<s:text name="format.seccion"><s:param value="logSecc"/></s:text>
                            </td>
                            <td style="width: 10%;" align="center">
                                <s:property value="logSem"/>/<s:property value="logAgno"/>
                            </td>
                            <td style="width: 35%">
                                <s:property value="asignatura.asiNom"/>
                            </td>
                            <td style="width: 10%">
                                <s:if test="logTipoMod ==\"A\"">
                                    <s:text name="label.inscribe"/>
                                </s:if>
                                <s:else>
                                    <s:text name="label.desinscribe"/>
                                </s:else>
                            </td>
                            <td style="width: 10%">
                                <s:property value="procesoInscripcion.pinsDes"/>
                            </td>
                            <td style="width: 5%">
                                <s:property value="logUser"/>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="sem" name="sem" value="<s:property value="sem"/>"/>
                <input type="hidden" id="agno" name="agno" value="<s:property value="agno"/>"/>
            </div>
        </s:form>
    </body>
</html>

