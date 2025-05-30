<%--
    Document   : commonAsistenciaPlanilla
    Created on : 12-08-2010, 09:40:51 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Planilla de Asistencias</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/common/asistencia/commonAsistenciaPlanilla-3.0.1.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.asistencia"/> <s:property
                    value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
            </div>

            <div class="mb-33">
                <div class="btn-group">                    
                    <button id="export-button" title="Exportar" type="button" class="btn btn-light" >
                        <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.excel"/></span>
                    </button>
                </div>
            </div>

            <form id="lista-form" action="#" method="post">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col"><s:text name="label.rut"/></th>
                            <th scope="col"><s:text name="label.paterno"/></th>
                            <th scope="col"><s:text name="label.materno"/></th>
                            <th scope="col"><s:text name="label.name"/></th>
                            <th scope="col"></th>
                                <s:iterator value="#session.genericSession.getWorkSession(key).asistenciaAlumnoList" status="row">
                                <th scope="col"><s:date name="asaFecha" format="dd-MM"/></th>
                                </s:iterator>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).nominaCurso" status="rowNomina">
                            <tr>
                                <td style="text-align: right">
                                    <s:property value="#rowNomina.count"/>
                                </td>
                                <td>
                                    <s:property value="alumno.aluRut"/>-<s:property value="alumno.aluDv"/>
                                </td>
                                <td>
                                    <s:property value="alumno.aluPaterno"/>
                                </td>
                                <td>
                                    <s:property value="alumno.aluMaterno"/>
                                </td>
                                <td>
                                    <s:property value="alumno.aluNombre"/>
                                </td>
                                <td style="text-align: center">
                                    <img height="60" width="50" id="foto<s:property value="#rowNomina.count"/>"
                                         src="dummy/<s:property value="#rowNomina.count -1"/>/CommonCursoGetFotoAlumno?pos=<s:property value="#rowNomina.count -1"/>&key=<s:property value="key"/>"
                                         alt=""/>
                                </td>
                                <s:iterator value="#session.genericSession.getWorkSession(key).asistenciaAlumnoList"
                                            status="rowAsistencia">
                                    <td>
                                        <s:if test="#session.genericSession.getWorkSession(key).asisteClases(asaCorrel, alumno.aluRut)">
                                            <input name="ck_<s:property value="#rowNomina.count -1"/>_<s:property value="#rowAsistencia.count -1"/>"
                                                   id="ck_<s:property value="#rowNomina.count -1"/>_<s:property value="#rowAsistencia.count -1"/>"
                                                   type="checkbox" checked="checked"/>
                                        </s:if>
                                        <s:else>
                                            <input name="ck_<s:property value="#rowNomina.count -1"/>_<s:property value="#rowAsistencia.count -1"/>"
                                                   id="ck_<s:property value="#rowNomina.count -1"/>_<s:property value="#rowAsistencia.count -1"/>"
                                                   type="checkbox"/>
                                        </s:else>
                                    </td>
                                </s:iterator>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                </div>
            </form>
        </div>
    </body>
</html>