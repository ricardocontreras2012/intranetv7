<%--
    Document   : secretariaDocenteConvalidacionSolicitud.jsp
    Created on : 25-11-2014, 10:57:52 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Generación de Acta de Convalidación de Asignaturas de Malla</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/secretariaDocente/convalidacion/secretariaDocenteConvalidacionSolicitud-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.acta.convalidacion"/>  <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.getNombreStd()"/>
        </div>
        
        Estado=<s:property value="#session.secretariaSession.convalidacion.cosEstado"/>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <s:if test="#session.secretariaSession.convalidacion.cosEstado in {\"N\",\"G\"} ">
                            <div class="btn-group">
                                <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                    <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                                </button>
                            </div>
                        </s:if>
                        <s:if test="#session.secretariaSession.convalidacion.cosEstado in {\"I\"} ">
                            <div class="btn-group">
                                <button id="emitir-button" title="Emitir" type="button" class="btn btn-light" >
                                    <span class="fa fa-lock"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.emitir"/></span>
                                </button>
                            </div>
                        </s:if>
                        <s:if test="#session.secretariaSession.convalidacion.cosEstado in {\"N\"} ">
                            <div class="btn-group">
                                <button id="importar-button" title="Importar" type="button" class="btn btn-light" >
                                    <span class="fa fa-download"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.importar"/></span>
                                </button>
                            </div>
                        </s:if>
                        <s:if test="#session.secretariaSession.convalidacion.cosEstado in {\"G\",\"I\"} ">
                            <div class="btn-group">
                                <button id="print-button" title="Imprimir Informe" type="button" class="btn btn-light" >
                                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.print"/></span>
                                </button>
                            </div>
                        </s:if>
                    </div>
                </div>
            </div>
        </div>

        <form id="convalidacion-form">
            <label for="agno"><s:text name="label.year"/>&nbsp;&nbsp;</label><input id="agno" name="agno" size="4" value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>"/>
            <label for="sem"><s:text name="label.term"/>&nbsp;&nbsp;</label><input id="sem" name="sem" size="1" value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>"/>

            <div id="table-div" class="data-tables-container">
                <table id="malla-table" class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"><s:text name="label.asignatura"/></th>
                            <th scope="col"><s:text name="label.electivo"/></th>
                            <th scope="col"><s:text name="label.cursada"/></th>
                            <th scope="col"><s:text name="label.situacion"/></th>
                            <th scope="col"><s:text name="label.nota"/></th>
                            <th scope="col"><s:text name="label.observaciones"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.secretariaSession.porAprobar" status="row">
                            <tr>
                                <td><s:property value="#row.count"/></td>
                                <td>
                                    <s:if test="#session.secretariaSession.convalidacion.cosEstado in {\"N\"} ">
                                        <input type="checkbox" id="ck_<s:property value="#row.count -1"/>"
                                               name="ck_<s:property value="#row.count -1"/>"/>
                                    </s:if>
                                </td>
                                <td style="text-align:right; width: 10%"><s:property value="asignatura.asiCod"/></td>
                                <td><s:property value="asignatura.asiNom"/></td>
                                <s:if test="asignatura.asiElect==\"S\"">
                                    <td><input name="electivo_<s:property value="#row.count -1"/>" id="electivo_<s:property value="#row.count -1"/>" value="<s:property value="csaElectivo"/>"></td>
                                    </s:if>
                                    <s:else>
                                    <td></td>
                                </s:else>
                                <td><input name="cursada_<s:property value="#row.count -1"/>" id="cursada_<s:property value="#row.count -1"/>" value="<s:property value="csaCursada"/>"></td>
                                    <%--td><input name="estado_<s:property value="#row.count -1"/>" id="estado_<s:property value="#row.count -1"/>" value="<s:property value="csaEstado"/>"></td--%>
                                <td><select name="estado_<s:property value="#row.count -1"/>" id="estado_<s:property value="#row.count -1"/>" >
                                        <s:if test="csaEstado==\"C\"">
                                            <option value="C" selected="selected">Convalidada</option>
                                        </s:if>
                                        <s:else>
                                            <option value="C">Convalidada</option>
                                        </s:else>
                                        <s:if test="csaEstado==\"R\"">
                                            <option value="R" selected="selected">Rechazada</option>
                                        </s:if>
                                        <s:else>
                                            <option value="R">Rechazada</option>
                                        </s:else>
                                    </select>
                                </td>
                                <td><input name="nota_<s:property value="#row.count -1"/>" id="nota_<s:property value="#row.count -1"/>" value="<s:property value="csaNota"/>"></td>
                                <td><input name="obs_<s:property value="#row.count -1"/>" id="obs_<s:property value="#row.count -1"/>" value="<s:property value="csaObs"/>"></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="estado" name="estado" value="<s:property value="#session.secretariaSession.convalidacion.cosEstado"/>"/>
            </div>
        </form>

        <div class="modal fade" id="confirmacion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="confirmation.emision.acta"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="generar();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade bs-modal-dialog" id="msg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="msgModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"><div id="error-div"></div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
