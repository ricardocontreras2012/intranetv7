<%--
    Document   : commonActaComplementariaConsola
    Created on : 17-abr-2014
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Generación de Actas Complementarias</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.3.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/acta/commonActaComplementariaConsola-3.0.4.js"></script>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.acta.complementaria"/>  <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.getNombreStd()"/>
        </div>

        <form id="cursos-form" action="#" method="post">
            <table class="table-agno-sem">
                <tr>
                    <td><label for="semCal"><s:text name="label.term.short"/></label></td>
                    <td><label for="agnoCal"><s:text name="label.year"/></label></td>
                    <td><input id="semCal" name="semCal" value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.parametros.semCal"/>"
                               maxlength="1" size="1" style="text-align: center" class="form-control input-sm input-sem"/></td>
                    <td><input id="agnoCal" name="agnoCal"
                               value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.parametros.agnoCal"/>" maxlength="4"
                               size="4"  style=";text-align: center" class="form-control input-sm input-agno"/></td>
                </tr>
            </table>

            <div class="container container-menu">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <div class="btn-group">
                                <button id="search-button" title="Buscar" type="button" class="btn btn-light">
                                    <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                                </button>
                            </div>
                            <div class="btn-group">
                                <button id="emitir-button" title="Emitir" type="button" class="btn btn-light" >
                                    <span class="fa fa-lock"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.emitir"/></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            

            <div class="data-tables-container">
                <table id="cursos-table" class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.code"/></th>
                            <th scope="col"><s:text name="label.name"/></th>
                            <th scope="col"><s:text name="label.profesor"/></th>
                            <th scope="col"><s:text name="label.nota"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:if test="hasActionErrors()">
                            <tr><td></td><td></td><td></td><td></tr>
                        </s:if>
                        <s:else>
                            <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                                <tr>
                                    <td><s:property value="id.curAsign"/>-<s:property value="id.curElect"/>&nbsp;<s:property
                                            value="id.curCoord"/><s:property value="id.curSecc"/></td>
                                    <td><s:property value="curNombre"/></td>
                                    <td><s:property value="curProfesores"/></td>
                                    <td style="width: 80px"><input id="nota_<s:property value="#row.count"/>" name="nota_<s:property value="#row.count"/>" type="text" maxlength="3" width="80px" class="form-control"></td>
                                </tr>
                            </s:iterator>
                        </s:else>
                    </tbody>
                </table>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>

        <div class="modal fade" id="result" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="msg-result-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="msg-dummy-error-div" style="display: none"><s:actionerror/></div>
        <div id="msg-dummy-ok-div" style="display: none"><s:actionmessage/></div>
    </body>
</html>
