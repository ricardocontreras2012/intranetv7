<%--
    Document   : commonCursoDefinicionGetCursos
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
        <link rel="stylesheet" href="/intranetv7/css/local/local-form.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tooltip.css" type="text/css" />         
        <link rel="stylesheet" href="/intranetv7/css/flatpickr/flatpickr.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/flatpickr/material_blue.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/flatpickr/all.min.css" type="text/css" /> 
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.scrollintoview.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/flatpickr/flatpickr.js"></script>
        <script type="text/javascript" src="/intranetv7/js/flatpickr/es.js"></script>  
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/definicion/commonCursoDefinicionGetCursos-3.3.2.js"></script>         
    </head>

    <body class="inner-body" style="overflow: hidden">
        <div class="container-fluid d-flex flex-column vh-100">
            <row>
                <div class="navbar-brand container-fluid">
                    <div class="title-div">
                        <s:text name="label.title.definicion.cursos"/> &nbsp;<s:property
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
                                        <th scope="col"><s:text name="label.horario"/></th>
                                        <th scope="col"><s:text name="label.salas"/></th>
                                        <th scope="col"><s:text name="label.profesor"/></th>
                                        <th scope="col">Ejercicios/Ayudantía</th>
                                        <th scope="col"><s:text name="label.cupo"/></th>
                                        <th scope="col"><s:text name="label.inscritos"/></th>
                                        <th scope="col">D</th>
                                        <th scope="col">V</th>
                                        <th scope="col">Inicio</th>
                                        <th scope="col">Término</th>
                                        <th></th>
                                        <th style="display:none"></th>
                                        <th style="display:none"></th>
                                        <th style="display:none"></th>
                                        <th style="display:none"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                                        <tr>
                                            <td>
                                                <input type="checkbox" id="ck_<s:property value="#row.count -1"/>" name="ck_<s:property value="#row.count -1"/>"/>
                                            </td>
                                            <td>
                                                <s:property value="id.curAsign"/>-<s:property value="id.curElect"/>&nbsp;<s:property value="id.curCoord"/><s:property value="id.curSecc"/>
                                            </td>
                                            <td>
                                                <a id="searchAnchor_<s:property value="#row.count -1"/>">
                                                    <s:property value="curNombre"/>
                                                </a>
                                            </td>   
                                            <td>
                                                <a>
                                                    <s:property value="curHorario"/>
                                                </a>
                                            </td>
                                            <td><s:property value="curSalas"/></td>
                                            <td><s:property value="curProfesores"/></td>
                                            <td><s:property value="curAyudantes"/></td>
                                            <td><s:property value="curCupoIni"/></td>
                                            <td><s:property value="curCupoIni - curCupoDis"/></td>                                            
                                            <td><s:property value="curJorDiurno"/></td>
                                            <td><s:property value="curJorVesp"/></td>
                                            <td><s:date name="curFechaInicio" format="dd-MM-yyyy" /></td>
                                            <td><s:date name="curFechaTermino" format="dd-MM-yyyy" /></td>
                                            <td>
                                                <a href="#"  title="<s:if test="curTipo==\"C\"">Cerrado</s:if><s:if test="curTipo==\"E\"">Espejo</s:if><s:if test="curTipo==\"T\"">Transversal</s:if>">
                                                    <s:property value="curTipo"/>
                                                </a>
                                            </td>
                                            <td style="display:none"><s:property value="curEnableProf"/></td>
                                            <td style="display:none"><s:property value="curEnableAyu"/></td>
                                            <td style="display:none"><s:property value="curEnableLab"/></td>
                                            <td style="display:none"><s:property value="asignatura.asiTipoControlTel"/></td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>

                        <div id="hidden-input-div">
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                            <input type="hidden" id="newRow" name="newRow" value="100"/>
                            <input type="hidden" id="pos" name="pos" value="<s:property value="#session.genericSession.getWorkSession(key).pos"/>"/>
                            <input type="hidden" id="rut-prof" name="rut-prof"/>
                            <input type="hidden" id="rut-ayu" name="rut-ayu"/>
                            <input type="hidden" id="tipo" name="tipo"/>
                            <input type="hidden" id="flagEstricto" name="flagEstricto"/>
                            <input type="hidden" id="userType" name="userType" value="<s:property value="#session.genericSession.userType"/>"/>
                        </div>

                        <div style="display:none">
                            <div id="horarioDummy"></div>
                            <div id="profesorDummy"></div>
                            <div id="ayudanteDummy"></div>
                            <div id="electivo-div"></div>
                            <select id="diaDummy" name="diaDummy">
                                <s:iterator value="#session.genericSession.diaList" status="row">
                                    <option value="<s:property value="diaCod"/>" class="form-control"><s:property value="diaCod"/></option>
                                </s:iterator>
                            </select>
                            <select id="modDummy" name="modDummy">
                                <s:iterator value="#session.genericSession.getWorkSession(key).moduloHorarioList" status="row">
                                    <option value="<s:property value="id.modCod"/>" class="form-control"><s:property value="id.modCod"/></option>
                                </s:iterator>
                            </select>
                        </div>

                        <div class="modal fade" id="error-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">AVISO</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="error-div" class="error">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                        </div>
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
                                        <p><s:text name="confirmation.eliminacion.cursos"/></p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="remove();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="new-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">DEFINICIÓN DE CURSO</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="curso-modal-div">
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>Asignatura</td>
                                                        <td><select id="asign" name="asign" class="form-control">
                                                                <option value="0">Seleccione Asignatura</option>
                                                                <s:iterator value="#session.genericSession.getWorkSession(key).asignaturaList" status="row">
                                                                    <option value="<s:property value="asiCod"/>"><s:property value="asiCod"/>&nbsp;&nbsp;<s:property value="asiNom"/></option>
                                                                </s:iterator>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Electividad</td>
                                                        <td><input type="number" id="elect" name="elect" class="form-control" maxlength="2" min="1" max="99"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Electivo</td>
                                                        <td><input type="text" id="electivo" name="electivo" class="form-control" maxlength="100" readonly="readonly"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td >Coordinación</td>
                                                        <td><input type="text" id="coord" name="coord" class="form-control" maxlength="1" style="text-transform: uppercase;"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Sección</td>
                                                        <td><input  type="text" id="secc" name="secc" class="form-control" maxlength="2"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Cupo</td>
                                                        <td><input type="text" id="cupo" name="cupo" class="form-control" maxlength="3"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Fecha Inicio</td>
                                                        <td>
                                                            <div class="input-group" style="max-width: fit-content;">
                                                                <input type="text" id="inicio" name="inicio" class="form-control" placeholder="dd-mm-yyyy" data-input />
                                                                <button class="btn btn-outline-secondary" type="button" id="btnCalendarioInicio">
                                                                    <i class="fa fa-calendar"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Fecha Término</td>
                                                        <td>
                                                            <div class="input-group" style="max-width: fit-content;">
                                                                <input type="text" id="termino" name="termino" class="form-control" placeholder="dd-mm-yyyy" data-input />
                                                                <button class="btn btn-outline-secondary" type="button" id="btnCalendarioTermino">
                                                                    <i class="fa fa-calendar"></i>
                                                                </button>
                                                            </div>                                                            
                                                        </td>
                                                    </tr>

                                                    <s:if test="#session.genericSession.userType !=\"CFI\"" >
                                                        <tr>
                                                            <td>Diurno</td>
                                                            <s:if test="#session.genericSession.getWorkSession(key).getMiCarreraSupport().getRegimen==\"D\""  >
                                                                <td>
                                                                    <input type="checkbox" id="diurno" name="diurno" value="D" checked />
                                                                </td>
                                                            </s:if>
                                                            <s:else>
                                                                <td>
                                                                    <input type="checkbox" id="diurno" name="diurno" value="D" />
                                                                </td>
                                                            </s:else>
                                                        </tr>
                                                        <tr>
                                                            <td>Vespertino</td>
                                                            <s:if test="#session.genericSession.getWorkSession(key).getMiCarreraSupport().getRegimen()==\"V\""  >
                                                                <td>
                                                                    <input type="checkbox" id="vespertino" name="vespertino" value="V" checked />
                                                                </td>
                                                            </s:if>
                                                            <s:else>
                                                                <td>
                                                                    <input type="checkbox" id="vespertino" name="vespertino" value="V" />
                                                                </td>
                                                            </s:else>

                                                        </tr>
                                                    </s:if>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div id="curso-msg" style="color:red; text-align: center;"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="save-new-curso-event">Grabar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="modify-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">MODIFICIÓN DE CURSO</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="curso-modify-modal-div">
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>Asignatura</td>
                                                        <td id="asignId">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Electividad</td>
                                                        <td id="electId"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Electivo</td>
                                                        <td><input type="text" id="electivoId" name="electivoId" class="form-control" maxlength="100" readonly="readonly"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td >Coordinación</td>
                                                        <td id="coordId"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Sección</td>
                                                        <td id="seccId"></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Cupo</td>
                                                        <td><input type="text" id="cupoId" name="cupoId" class="form-control" maxlength="3"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Fecha Inicio</td>                                                                                                                
                                                        <td>
                                                             <div class="input-group" style="max-width: fit-content;">
                                                                <input type="text" id="inicioId" name="inicioId" class="form-control" placeholder="dd-mm-yyyy" data-input />
                                                                <button class="btn btn-outline-secondary" type="button" id="btnCalendarioInicioId">
                                                                    <i class="fa fa-calendar"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Fecha Término</td>
                                                        <td>
                                                            <div class="input-group" style="max-width: fit-content;">
                                                                <input type="text" id="terminoId" name="terminoId" class="form-control" placeholder="dd-mm-yyyy" data-input />
                                                                <button class="btn btn-outline-secondary" type="button" id="btnCalendarioTerminoId">
                                                                    <i class="fa fa-calendar"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Diurno</td>
                                                        <td><input type="checkbox" id="diurnoId" name="diurnoId" value="D" checked />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Vespertino</td>
                                                        <td><input type="checkbox" id="vespertinoId" name="vespertinoId" value="V" checked />
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div id="curso-modify-msg" style="color:red; text-align: center;"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="save-modify-curso-event">Grabar</button>                                        
                                    </div>
                                </div>
                            </div>
                        </div>        

                        <div class="modal fade" id="horario-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <div id="hor-title"></div>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="horario-modal-div"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="new-horario-event">Nuevo</button>
                                        <button type="button" class="btn btn-primary" id="del-horario-event">Eliminar</button>
                                        <button type="button" class="btn btn-primary" id="save-horario-event">Grabar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="del-horario-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea eliminar el(los) horario(s)?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="delHorario();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="save-horario-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea grabar la información?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="saveHorario();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="modal-profesor" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <div id="prof-title"></div>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="profesor-modal-div"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="new-profesor-event">Nuevo</button>
                                        <button type="button" class="btn btn-primary" id="add-profesor-event">Reusar</button>
                                        <button type="button" class="btn btn-primary" id="del-profesor-event">Eliminar</button>
                                        <button type="button" class="btn btn-primary" id="save-profesor-event">Grabar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="modal-profesor-estricto" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <div id="prof-estricto-title"></div>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="profesor-estricto-modal-div"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="new-profesor-estricto-event">Nuevo</button>
                                        <button type="button" class="btn btn-primary" id="del-profesor-estricto-event">Eliminar</button>
                                        <button type="button" class="btn btn-primary" id="save-profesor-estricto-event">Grabar</button>
                                    </div>
                                </div>
                            </div>
                        </div>                

                        <div class="modal fade" id="profesor-search-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">BÚSQUEDA PROFESOR</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="profesor-modal-div">
                                            <div>
                                                <input id="rut-dv-prof" nam="rut-dv-prof"  placeholder="123456789-0" class="form-control"/>
                                                <input id="pat-prof" nam="pat-prof"  placeholder="paterno" class="form-control"/>
                                                <input id="mat-prof" nam="mat-prof"  placeholder="materno" class="form-control"/>
                                                <input id="nom-prof" nam="nom-prof"  placeholder="nombre" class="form-control"/>
                                                <button id="search-prof-button" title="Buscar" type="button" class="btn btn-light" >
                                                    <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                                                </button>
                                            </div>
                                            <div id="profesor-search-modal-div"></div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="profesor-estricto-search-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">BÚSQUEDA PROFESOR</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="profesor-estricto-modal-div">
                                            <div>
                                                <input id="rut-dv-prof-estricto" nam="rut-dv-prof-estricto"  placeholder="123456789-0" class="form-control"/>
                                                <input id="pat-prof-estricto" nam="pat-prof-estricto"  placeholder="paterno" class="form-control"/>
                                                <input id="mat-prof-estricto" nam="mat-prof-estricto"  placeholder="materno" class="form-control"/>
                                                <input id="nom-prof-estricto" nam="nom-prof-estricto"  placeholder="nombre" class="form-control"/>
                                                <button id="search-prof-estricto-button" title="Buscar" type="button" class="btn btn-light" >
                                                    <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                                                </button>
                                            </div>
                                            <div id="profesor-estricto-search-modal-div"></div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="del-profesor-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea eliminar profesor(es) seleccionado(s)?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="delProfesor();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="del-profesor-estricto-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea eliminar profesor(es) seleccionado(s)?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="delProfesorEstricto();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="save-profesor-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea grabar la información?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="saveProfesor();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="save-profesor-estricto-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea grabar la información?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="saveProfesorEstricto();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="ayudante-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <div id="ayu-title"></div>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="ayudante-modal-div"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="new-ayudante-event">Nuevo</button>
                                        <button type="button" class="btn btn-primary" id="del-ayudante-event">Eliminar</button>
                                        <button type="button" class="btn btn-primary" id="save-ayudante-event">Grabar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="ayudante-estricto-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <div id="ayu-estricto-title"></div>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="ayudante-estricto-modal-div"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="new-ayudante-estricto-event">Nuevo</button>
                                        <button type="button" class="btn btn-primary" id="del-ayudante-estricto-event">Eliminar</button>
                                        <button type="button" class="btn btn-primary" id="save-ayudante-estricto-event">Grabar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="ayudante-search-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">BÚSQUEDA AYUDANTE</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="ayudante-modal-div">
                                            <div>
                                                <input id="rut-dv-ayu" nam="rut-dv-ayu"  placeholder="123456789-0" class="form-control"/>
                                                <input id="pat-ayu" nam="pat-ayu"  placeholder="paterno" class="form-control"/>
                                                <input id="mat-ayu" nam="mat-ayu"  placeholder="materno" class="form-control"/>
                                                <input id="nom-ayu" nam="nom-ayu"  placeholder="nombre" class="form-control"/>
                                                <button id="search-ayu-button" title="Buscar" type="button" class="btn btn-light" >
                                                    <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                                                </button>
                                            </div>
                                            <div id="ayudante-search-modal-div"></div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="ayudante-estricto-search-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">BÚSQUEDA AYUDANTE</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="ayudante-estricto-modal-div">
                                            <div>
                                                <input id="rut-dv-ayu-estricto" nam="rut-dv-ayu-estricto"  placeholder="123456789-0" class="form-control"/>
                                                <input id="pat-ayu-estricto" nam="pat-ayu-estricto"  placeholder="paterno" class="form-control"/>
                                                <input id="mat-ayu-estricto" nam="mat-ayu-estricto"  placeholder="materno" class="form-control"/>
                                                <input id="nom-ayu-estricto" nam="nom-ayu-estricto"  placeholder="nombre" class="form-control"/>
                                                <button id="search-ayu-estricto-button" title="Buscar" type="button" class="btn btn-light" >
                                                    <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                                                </button>
                                            </div>
                                            <div id="ayudante-estricto-search-modal-div"></div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="del-ayudante-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea eliminar ayudante(es) seleccionado(s)?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="delAyudante();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="del-ayudante-estricto-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea eliminar ayudante(es) seleccionado(s)?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="delAyudanteEstricto();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>                

                        <div class="modal fade" id="save-ayudante-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea grabar la información?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="saveAyudante();">OK</button>
                                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="save-ayudante-estricto-modal" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Desea grabar la información?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-light" onclick="saveAyudanteEstricto();">OK</button>
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