<%--
    Document   : commonCursoReportesResumen
    Created on : 03-08-2010, 09:02:46 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Resumen de Reportes x Curso</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/commonCursoReportesResumen-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.reporte"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="print-button" title="Print" type="button" class="btn btn-light" >
                                <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="export-button" title="Print" type="button" class="btn btn-light" >
                                <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form id="cursos-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="cursos-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.code"/></th>
                            <th scope="col"><s:text name="label.coordinacion"/></th>
                            <th scope="col"><s:text name="label.term.short"/> <s:text name="label.year"/></th>
                            <th scope="col"><s:text name="label.asignatura"/></th>
                            <th scope="col"><s:text name="label.profesor"/></th>
                            <th scope="col"><s:text name="label.numero.reportes"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).resumenCurso" status="row">
                            <tr>
                                <td style="width: 7%;text-align: right" align="right"><a
                                        id="curso_<s:property value="#row.count -1"/>"
                                        name="curso_<s:property value="#row.count -1"/>"><s:property value="asign"/> <s:property
                                            value="elect"/></a></td>
                                <td style="width: 7%;text-align: right"><s:property value="coord"/>-<s:if
                                        test="secc != null"><s:text name="format.seccion"><s:param
                                                value="secc"/></s:text></s:if></td>
                                <td style="width: 7%;text-align: center"><s:property value="sem"/>/<s:property
                                        value="agno"/></td>
                                <td><s:property value="nombre"/></td>
                                <td><s:property value="profesor"/></td>
                                <td><s:property value="nobjs1"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos"/>
                <input type="hidden" id="actionCall" name="actionCall" value="CommonReporteGetReportesCurso"/>
            </div>
        </form>
    </body>
</html>