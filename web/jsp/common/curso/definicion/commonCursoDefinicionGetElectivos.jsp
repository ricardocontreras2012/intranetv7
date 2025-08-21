<%--
    Document   : commonCursoDefinicionGetElectivos
    Created on : 03-08-2020, 10:02:43 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Electivos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-form.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tooltip.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/definicion/commonCursoDefinicionGetElectivos-3.0.1.js"></script>
    </head>
    <body class="inner-body" style="overflow: hidden">
        <nav class="navbar-brand  fixed-top bg-light">
            <div class="container-fluid" >
                <div class="title-div">
                    <s:text name="label.title.definicion.electivos"/> &nbsp;<s:property
                        value="#session.genericSession.getWorkSession(key).nombreCarrera"/>
                    &nbsp;<s:property
                        value="#session.genericSession.getWorkSession(key).agnoAct"/>
                    /<s:property
                        value="#session.genericSession.getWorkSession(key).semAct"/>
                </div>
            </div>
            <div class="container-fluid container-menu">
                <div class="row">
                    <div class="col">
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
            </div>
        </nav>
        <div class="container-fluid d-flex flex-column vh-100">



            <row class="overflow-auto">
                <div class="container-fluid overflow-auto">
                    <form id="electivos-form" action="#">
                        <div class="data-tables-container container-fluid" style="margin: 0; padding: 0;">
                            <table id="cursos-table" class="display responsive table table-striped table-bordered dataTable" style="overflow-y: auto">
                                <thead>
                                    <tr>
                                        <th scope="col" style="width: 2%"></th>
                                        <th scope="col" style="width: 6%"><s:text name="label.asignatura"/></th>
                                        <th scope="col" style="width: 2%">Electividad</th>
                                        <th scope="col" style="width: 66%"><s:text name="label.name"/></th>
                                        <th scope="col" style="width: 6%">Area</th>
                                        <th scope="col" style="width: 6%">Minor</th>
                                            <s:if test="isEconomia">
                                            <th scope="col" style="width: 6%">Tipo</th>
                                            <th scope="col" style="width: 6%">SCT</th>
                                            </s:if>

                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).electivoList" status="row">
                                        <s:if test="eleTipo == 'PRO'"><tr style="background-color:#629aed"></s:if>
                                        <s:elseif test="eleTipo == 'COM'"><tr style="background-color:#3ec8a5"></s:elseif>
                                        <s:elseif test="eleTipo == 'PRA'"><tr style="background-color:#ffad52"></s:elseif>
                                        <s:else><tr></s:else>
                                            <td><input type="checkbox" id="ck_<s:property value="#row.count -1"/>"
                                                       name="ck_<s:property value="#row.count -1"/>"/></td>
                                            <td><a id="searchAnchor_<s:property value="#row.count -1"/>" onclick="show(<s:property value="id.eleAsign"/>, '<s:property value="id.eleElect"/>', '<s:property value="eleNom"/>', '<s:property value="minor"/>', '<s:property value="area.areDes"/>', '<s:property value="eleTipo"/>')"><s:property value="id.eleAsign"/></a></td>
                                            <td><a id="searchAnchor_<s:property value="#row.count -1"/>" onclick="show(<s:property value="id.eleAsign"/>, '<s:property value="id.eleElect"/>', '<s:property value="eleNom"/>', '<s:property value="minor"/>', '<s:property value="area.areDes"/>', '<s:property value="eleTipo"/>')"><s:property value="id.eleElect"/></a></td>                                            
                                            <td><a id="searchAnchor_<s:property value="#row.count -1"/>" onclick="show(<s:property value="id.eleAsign"/>, '<s:property value="id.eleElect"/>', '<s:property value="eleNom"/>', '<s:property value="minor"/>', '<s:property value="area.areDes"/>', '<s:property value="eleTipo"/>')"><s:property value="eleNom"/></a></td>
                                            <td><a id="searchAnchor_<s:property value="#row.count -1"/>" onclick="show(<s:property value="id.eleAsign"/>, '<s:property value="id.eleElect"/>', '<s:property value="eleNom"/>', '<s:property value="minor"/>', '<s:property value="area.areDes"/>', '<s:property value="eleTipo"/>')"><s:property value="area.areDes"/></a></td>
                                            <td><a id="searchAnchor_<s:property value="#row.count -1"/>" onclick="show(<s:property value="id.eleAsign"/>, '<s:property value="id.eleElect"/>', '<s:property value="eleNom"/>', '<s:property value="minor"/>', '<s:property value="area.areDes"/>', '<s:property value="eleTipo"/>')"><s:property value="minor.asiCod"/></a></td>
                                                <s:if test="isEconomia">
                                                <td><a id="searchAnchor_<s:property value="#row.count -1"/>" onclick="show(<s:property value="id.eleAsign"/>, '<s:property value="id.eleElect"/>', '<s:property value="eleNom"/>', '<s:property value="minor"/>', '<s:property value="area.areDes"/>', '<s:property value="eleTipo"/>')">
                                                        <s:if test="eleTipo == 'PRO'">Profundización</s:if>
                                                        <s:if test="eleTipo == 'COM'">Complementario</s:if>
                                                        <s:if test="eleTipo == 'PRA'">Práctico</s:if>
                                                        </a>
                                                    </td>
                                                    <td><s:property value="asignatura.asiSct"/></td>
                                            </s:if>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>

                        <div id="hidden-input-div">
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>                                                       
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
                                        <p><s:text name="confirmation.eliminacion.electivos"/></p>
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
                                        <h5 class="modal-title">DEFINICIÓN DE ELECTIVO</h5>
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
                                                        <td><input type="text" id="electivo" name="electivo" class="form-control" maxlength="100"/></td>
                                                    </tr>

                                                <td>Área</td>
                                                <td><select id="area" name="area" class="form-control">
                                                        <option value="0">Seleccione Area</option>
                                                        <option value="-1">Sin Area</option>
                                                        <s:iterator value="#session.jefeCarreraSession.getAreaList(#session.genericSession.rut)" status="row">
                                                            <option value="<s:property value="areCod"/>"><s:property value="areDes"/></option>
                                                        </s:iterator>
                                                    </select>
                                                </td>

                                                <tr>
                                                    <td>Minor</td>
                                                    <td><select id="minor" name="minor" class="form-control">
                                                            <option value="0">Seleccione Minor</option>
                                                            <option value="-1">Sin Minor</option>
                                                            <s:iterator value="#session.genericSession.getWorkSession(key).minorList" status="row">
                                                                <option value="<s:property value="asiCod"/>"><s:property value="asiCod"/>&nbsp;&nbsp;<s:property value="asiNom"/></option>
                                                            </s:iterator>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <s:if test="isEconomia">
                                                    <tr>
                                                        <td>Tipo</td>
                                                        <td><select id="tipo" name="tipo" class="form-control">
                                                                <option value="">Seleccione Tipo</option>
                                                                <option value="PRO">Profundización</option>
                                                                <option value="COM">Complementario</option>
                                                                <option value="PRA">Práctico</option>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                </s:if>

                                                </tbody>
                                            </table>
                                        </div>
                                        <div id="electivo-msg" style="color:red; text-align: center;"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="save-new-electivo-event">Grabar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="modify-modal" role="dialog">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">MODIFICACIÓN DE ELECTIVO</h5>
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
                                                        <td><input type="text" id="asignMod" name="asignMod" class="form-control" maxlength="100" readonly="readonly"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Electividad</td>
                                                        <td><input type="text" id="electMod" name="electMod" class="form-control" maxlength="100" readonly="readonly"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Electivo</td>
                                                        <td><input type="text" id="electivoMod" name="electivoMod" class="form-control" maxlength="100"/></td>
                                                    </tr>

                                                    <tr>
                                                        <td>Área</td>
                                                        <td><select id="areaMod" name="areaMod" class="form-control">
                                                                <option value="0">Seleccione Area</option>
                                                                <option value="-1">Sin Area</option>
                                                                <s:iterator value="#session.jefeCarreraSession.getAreaList(#session.genericSession.rut)" status="row">
                                                                    <option value="<s:property value="areCod"/>"><s:property value="areDes"/></option>
                                                                </s:iterator>
                                                            </select>
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td>Minor</td>
                                                        <td><select id="minorMod" name="minorMod" class="form-control">
                                                                <option value="0">Seleccione Minor</option>
                                                                <option value="-1">Sin Minor</option>
                                                                <s:iterator value="#session.jefeCarreraSession.getMinorList(#session.genericSession.rut)" status="row" var="item">
                                                                    <option value="<s:property value='#item'/>">
                                                                        <s:property value='#item'/>
                                                                    </option>
                                                                </s:iterator>
                                                            </select>
                                                        </td>
                                                    </tr>

                                                    <s:if test="isEconomia">
                                                        <tr>
                                                            <td>Tipo</td>
                                                            <td><select id="tipoMod" name="tipoMod" class="form-control">
                                                                    <option value="">Seleccione Tipo</option>
                                                                    <option value="PRO">Profundización</option>
                                                                    <option value="COM">Complementario</option>
                                                                    <option value="PRA">Práctico</option>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                    </s:if>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div id="electivo-modify-msg" style="color:red; text-align: center;"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="save-modify-electivo-event">Grabar</button>
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