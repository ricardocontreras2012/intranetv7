<%--
    Document   : commonCursoDefinicionGetEspejos
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
        <title>Lista de Cursos Espejos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-form.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/definicion/commonCursoDefinicionGetEspejos-3.0.0.js"></script>
    </head>
    <body class="inner-body" style="overflow: hidden">
        <div class="container-fluid d-flex flex-column vh-100">
            <row>
                <div class="navbar-brand container-fluid">
                    <div class="title-div">
                        <s:text name="label.title.definicion.cursos"/>&nbsp;Espejos&nbsp;<s:property
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
                                    <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                                        <span class="fa fa-pencil"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                                    </button>
                                </div>
                                <div class="btn-group">
                                    <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" >
                                        <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
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
                                        <th colspan="3" style="background-color: #90EE90">Espejo</th>
                                        <th colspan="11" style="background-color: #ADD8E6">Transversal</th>
                                    </tr>
                                    <tr>
                                        <th scope="col"></th>
                                        <th scope="col"><s:text name="label.code"/></th>
                                        <th scope="col"><s:text name="label.name"/></th>
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
                                    <s:iterator value="#session.genericSession.getWorkSession(key).cursoEspejoList" status="row">
                                        <tr>
                                            <td>
                                                <input type="checkbox" id="ck_<s:property value="#row.count -1"/>" name="ck_<s:property value="#row.count -1"/>" <s:if test="curTipo == \"T\"">checked</s:if>/>
                                                </td>
                                                <td><s:property value="id.cesAsign"/>-<s:property value="id.cesElect"/>&nbsp;<s:property
                                                    value="id.cesCoord"/><s:property value="id.cesSecc"/></td>
                                            <td><a id="searchAnchor_<s:property value="#row.count -1"/>"><s:property
                                                        value="espejo.curNombre"/></a></td>
                                            <td><s:property value="transversal.id.curAsign"/>-<s:property value="transversal.id.curElect"/>&nbsp;<s:property
                                                    value="transversal.id.curCoord"/><s:property value="transversal.id.curSecc"/></td>
                                            <td><a id="searchAnchor_<s:property value="#row.count -1"/>"><s:property
                                                        value="transversal.curNombre"/></a></td>
                                            <td><s:property value="transversal.curProfesores"/></td>
                                            <td><s:property value="transversal.curAyudantes"/></td>
                                            <td><s:property value="espejo.curCupoIni"/></td>
                                            <td><s:property value="transversal.curHorario"/></td>
                                            <td><s:property value="transversal.curSalas"/></td>
                                            <td><s:property value="espejo.curJorDiurno"/></td>
                                            <td><s:property value="espejo.curJorVesp"/></td>
                                            <td><s:property value="transversal.curFechaInicio"/></td>
                                            <td><s:property value="transversal.curFechaTermino"/></td>

                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>

                        <div id="hidden-input-div">
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </div>

                        <div class="modal fade" id="new-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">DEFINICIÓN DE CURSO ESPEJO</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="curso-modal-div">
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>Curso Transversal</td>
                                                        <td><select id="transversal" name="transversal" class="form-control">
                                                                <option value="-1">Seleccione Transversal</option>
                                                                <s:iterator value="#session.genericSession.getWorkSession(key).cursoTransversalFullList" status="row">
                                                                    <option value="<s:property value="#row.count -1"/>"><s:property value="getNombreFull()"/>&nbsp;&nbsp;Prof:: <s:property value="curProfesores"/>&nbsp;&nbsp;Horario::<s:property value="curHorario"/></option>
                                                                </s:iterator>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>Curso Cerrado</td>
                                                        <td><select id="cerrado" name="cerrado" class="form-control">
                                                                <option value="-1">Seleccione Cerrado</option>
                                                                <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                                                                    <option value="<s:property value="#row.count -1"/>"><s:property value="getNombreFull()"/></option>
                                                                </s:iterator>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                        </div>
                                        <div id="curso-msg" style="color:red; text-align: center;"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="save-new-espejo-event">Grabar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="confirmacion" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea eliminar la condición de espejo a los cursos selecccionados?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="remove();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </row>
        </div>
    </body>
</html>