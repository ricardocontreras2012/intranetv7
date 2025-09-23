<%--
    Document   : commonCursoGetTransversales
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
        <title>Lista de Cursos Transversales</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-form.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/sweetalert2/sweetalert2.all.min.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/definicion/commonCursoDefinicionGetTransversales-3.1.1.js"></script>
    </head>
    <body class="inner-body" style="overflow: hidden">
        <div class="container-fluid d-flex flex-column vh-100">
            <row>
                <div class="navbar-brand container-fluid">
                    <div class="title-div">
                        <s:text name="label.title.definicion.cursos"/>&nbsp;Transversales&nbsp;<s:property
                            value="#session.genericSession.getWorkSession(key).nombreCarrera"/>
                        &nbsp;<s:property
                            value="#session.genericSession.getWorkSession(key).agnoAct"/>
                        /<s:property
                            value="#session.genericSession.getWorkSession(key).semAct"/>
                    </div>
                </div>
            </row>
            <row>
                <div class="container-fluid container-menu">
                    <div class="row">
                        <div id="justified-button-bar" class="col-lg-12">
                            <div class="btn-group">
                                <div class="btn-group">
                                    <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                        <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </row>

            <row class="overflow-auto">
                <div class="container-fluid overflow-auto">
                    <form id="cursos-form" action="#">
                        <div class="data-tables-container container-fluid" style="margin: 0; padding: 0;">
                            <table id="cursos-table" class="display responsive table table-striped table-bordered dataTable" style="overflow-y: auto">
                                <thead>
                                    <tr>
                                        <th scope="col"></th>
                                        <th scope="col"><s:text name="label.code"/></th>
                                        <th scope="col"><s:text name="label.name"/></th>
                                        <th scope="col"><s:text name="label.profesor"/></th>
                                        <th scope="col"><s:text name="label.ayudante"/></th>
                                        <th scope="col"><s:text name="label.cupo"/></th>
                                        <th scope="col"><s:text name="label.horario"/></th>
                                        <th scope="col"><s:text name="label.salas"/></th>
                                        <th scope="col">D</th>
                                        <th scope="col">V</th>
                                        <th scope="col">Inicio</th>
                                        <th scope="col">Término</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).cursoTransversalList" status="row">
                                        <tr <s:if test="curTipo==\"T\"">style="background-color:#93E9BE"</s:if>>
                                                <td>
                                                    <input type="checkbox" id="ck_<s:property value="#row.count -1"/>" name="ck_<s:property value="#row.count -1"/>" <s:if test="curTipo == \"T\"">checked</s:if> <s:if test="curTipo == \"E\"">disabled</s:if>/>
                                                </td>
                                                    <td><s:property value="id.curAsign"/>-<s:property value="id.curElect"/>&nbsp;<s:property
                                                    value="id.curCoord"/><s:property value="id.curSecc"/></td>
                                            <td><a id="searchAnchor_<s:property value="#row.count -1"/>"><s:property
                                                        value="curNombre"/></a></td>
                                            <td><s:property value="curProfesores"/></td>
                                            <td><s:property value="curAyudantes"/></td>
                                            <td><s:property value="curCupoIni"/></td>
                                            <td><s:property value="curHorario"/></td>
                                            <td><s:property value="curSalas"/></td>
                                            <td><s:property value="curJorDiurno"/></td>
                                            <td><s:property value="curJorVesp"/></td>
                                            <td><s:property value="curFechaInicio"/></td>
                                            <td><s:property value="curFechaTermino"/></td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                        <div id="hidden-input-div">
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </div>
                    </form>
                </div>
            </row>
        </div>
    </body>
</html>