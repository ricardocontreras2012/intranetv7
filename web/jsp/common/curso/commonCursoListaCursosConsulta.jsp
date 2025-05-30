<%--
    Document   : commonCursoListaCursosConsulta
    Created on : 07-06-2009, 07:05:29 PM
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
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/common/curso/commonCursoListaCursosConsulta-3.0.4.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.definicion.cursos"/>&nbsp;<s:property
                value="#session.genericSession.getWorkSession(key).nombreCarrera"/>
            <s:property value="#session.genericSession.getWorkSession(key).semAct"/>/<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>
        </div>
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
                            <button id="export-button" title="Exportar" type="button" class="btn btn-light" >
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
                            <th scope="col"><s:text name="label.name"/></th>
                            <th scope="col"><s:text name="label.profesor"/></th>
                            <th scope="col"><s:text name="label.ayudante"/></th>
                            <th scope="col"><s:text name="label.cupo"/></th>
                            <th scope="col"><s:text name="label.inscritos"/></th>
                            <th scope="col"><s:text name="label.horario"/></th>
                            <th scope="col"><s:text name="label.salas"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                            <tr>
                                <td><s:property value="id.curAsign"/>-<s:property value="id.curElect"/>&nbsp;<s:property
                                        value="id.curCoord"/><s:property value="id.curSecc"/></td>
                                <td><s:property value="curNombre"/></td>
                                <td><s:property value="curProfesores"/></td>
                                <td><s:property value="curAyudantes"/></td>
                                <td><s:property value="curCupoIni"/></td>
                                <td><s:property value="curCupoIni - curCupoDis"/></td>
                                <td><s:property value="curHorario"/></td>
                                <td><s:property value="curSalas"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>