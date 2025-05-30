<%--
    Document   : commonCursoListaCursosAyudante
    Created on : 03-07-2019, 09:02:46 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Cursos</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/commonCursoListaCursosAyudante-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:if test="actionCall==null">
                <s:text name="label.title.cursos.historicos"/>
            </s:if>
            <s:if test="actionCall==\"CommonCursoGetListaCurso\"">
                <s:text name="label.title.cursos"/>
            </s:if>
            <s:if test="actionCall==\"CommonReporteGetReportesCurso\"">
                <s:text name="label.title.cursos.reportes"/>
            </s:if>
            <s:if test="actionCall==\"CommonReporteGetCursosAsignatura\"">
                <s:text name="label.title.cursos.reportes"/>
            </s:if>
            <s:if test="actionCall==\"CommonMaterialEnableConsola\"">
                <s:text name="label.title.cursos.materiales"/>
            </s:if>
            <s:if test="actionCall==\"CommonProfesorGetEncuesta\"">
                <s:text name="label.title.cursos.encuestas"/>
            </s:if>
            <s:if test="actionCall==\"ProfesorActaGetActa\"">
                <s:text name="label.title.cursos.actas"/>
            </s:if>
            <s:if test="actionCall==\"CommonAsistenciaGetAsistencias\"">
                <s:text name="label.title.asistencia"/>
            </s:if>
            <s:if test="actionCall==\"ProfesorEvaluacionPorcentajesRelativos\"">
                <s:text name="label.title.cursos.notas.parciales"/>
            </s:if>
            <s:if test="actionCall==\"ProfesorEvaluacionPorcentajesAbsolutos\"">
                <s:text name="label.title.cursos.notas.parciales"/>
            </s:if>
        </div>

        <s:if test="actionCall==null">
            <div class="container container-menu">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <div class="btn-group">
                                <button id="print-button" title="Imprimir" type="button" class="btn btn-light" >
                                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                                </button>
                            </div>
                            <div class="btn-group">
                                <button id="export-button" title="Excel" type="button" class="btn btn-light" >
                                    <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </s:if>

        <form id="cursos-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="cursos-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.code"/></th>
                            <th scope="col"><s:text name="label.coordinacion"/></th>
                            <th scope="col"><s:text name="label.term.short"/> <s:text name="label.year"/></th>
                            <th scope="col"><s:text name="label.asignatura"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                            <tr>
                                <s:if test="actionCall != null && actionCall != \"\"">
                                    <td style="width: 10%;text-align: right"><a
                                            id="curso_<s:property value="#row.count -1"/>"><s:property value="id.curAsign"/>
                                            <s:property value="id.curElect"/></a></td>
                                    <td style="width: 10%;text-align: right"><s:property value="id.curCoord"/>-<s:if
                                            test="id.curSecc != null"><s:text name="format.seccion"><s:param
                                                    value="id.curSecc"/></s:text></s:if></td>
                                    <td style="width: 10%;text-align: center"><s:property value="id.curSem"/>/<s:property
                                            value="id.curAgno"/></td>
                                        <s:if test="nombreProfesores.trim()==#session.genericSession.getWorkSession(key).profesor.nombre.trim()">
                                        <td><s:property value="curNombre"/></td>
                                    </s:if>
                                    <s:else>
                                        <td><s:property value="curNombre"/> (<s:property value="curProfesores"/>)</td>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    <td style="width: 10%;" align="right"><s:property value="id.curAsign"/> <s:property
                                            value="id.curElect"/></td>
                                    <td style="width: 10%;" align="right"><s:property value="id.curCoord"/>-<s:if
                                            test="id.curSecc != null"><s:text name="format.seccion"><s:param
                                                    value="id.curSecc"/></s:text></s:if></td>
                                    <td style="width: 10%;" align="center"><s:property value="id.curSem"/>/<s:property
                                            value="id.curAgno"/></td>
                                        <s:if test="curProfesores.trim()==#session.genericSession.getWorkSession(key).profesor.nombre.trim()">
                                        <td><s:property value="curNombre"/></td>
                                    </s:if>
                                    <s:else>
                                        <td><s:property value="curNombre"/> (<s:property value="curProfesores"/>)</td>
                                    </s:else>
                                </s:else>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="actionCall" name="actionCall" value="<s:property value="actionCall"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
            </div>
        </form>
    </body>
</html>