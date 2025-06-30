<%--
    Document   : commonReporteListaCursoAgnoSem
    Created on : 04-07-2016, 02:25:30 PM
    Author     : Ricardo Contreras S.as
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Cursos por Año/Semestre</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/reporte/commonReporteListaCursoAgnoSem-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.cursos.reportes"/>
        </div>

        <div>
            <button id="search-button" title="Search" type="button" class="btn btn-light" ><span class="fa fa-search" aria-hidden="true"></span>
                &nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
            </button>
        </div>

        <form id="cursos-form" action="#" method="post">
            <div class="container-fluid pt-2 pb-2">
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-inline row">
                            <div class="col-md-3">                                
                                Sem/Año
                            </div>
                            <div class="col-md-9">
                                <input id="sem" name="sem"
                                       value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>"
                                       maxlength="1" size="1" class="form-control input-sm"/>
                                <input id="agno" name="agno"
                                       value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>"
                                       maxlength="4" size="4" class="form-control input-sm"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-9"></div>
            </div>

            <div class="data-tables-container">
                <table id="cursos-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.code"/></th>
                            <th scope="col"><s:text name="label.name"/></th>
                            <th scope="col"><s:text name="label.profesor"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                            <tr>
                                <td><a id="curso_<s:property value="#row.count -1"/>"><s:property value="id.curAsign"/>-<s:property value="id.curElect"/>&nbsp;<s:property
                                            value="id.curCoord"/><s:property value="id.curSecc"/></a></td>
                                <td><s:property value="curNombre"/></td>
                                <td><s:property value="curProfesores"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="pos" name="pos" value=""/>
                <input type="hidden" id="actionCall" name="actionCall" value="CommonReporteGetReportesCurso"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>