<%--
    Document   : commonCarreraListaCarreras
    Created on : 31-10-2010, 07:44:11 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Carreras</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.nomina-3.0.0.min.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/common/carrera/commonCarreraListaCarreras-3.0.3.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.carreras"/>
        </div>     
        
        <s:if test="actionCall==\"RegistradorCurricularActaGenerarxCarrera\"">
            <button id="execute-button" title="Generar" type="button" class="btn btn-light" ><span class="fa fa-list" aria-hidden="true"></span>
                &nbsp; <span class="hidden-xs"><s:text name="label.button.generar"/></span>
            </button>
        </s:if>

        <form id="carreras-form" action="#">
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
                <table id="carreras-table" class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col"></th>
                            <th scope="col"><s:text name="label.carrera.programa"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).mencionList" status="row">
                            <tr>
                                <td><input type="checkbox" id="ck_<s:property value="#row.count -1"/>"
                                           name="ck_<s:property value="#row.count -1"/>"/></td>
                                <td><s:property value="id.menCodCar"/>&nbsp;<s:property value="id.menCodMen"/></td>
                                <td><s:property value="getNombreCarreraFull()"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="actionDummy" name="actionDummy" value="<s:property value="actionCall"/>"/>
                <input type="hidden" id="flag" name="flag" value=""/>
            </div>
        </form>

        <s:if test="actionCall==\"RegistradorCurricularActaGenerarxCarrera\"">
            <div class="modal fade" id="conf-emision-actas" role="dialog">
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
                            <input type="checkbox" id="ck_r" name="ck_r" value="R">
                            <label for="ck_r"> Regulares</label><br>
                            <input type="checkbox" id="ck_b" name="ck_b" value="B">
                            <label for="ck_b"> Bachillerato</label><br>
                            <input type="checkbox" id="ck_i" name="ck_i" value="I">
                            <label for="ck_i"> Intercambio</label><br>
                            <input type="checkbox" id="ck_m" name="ck_m" value="M">
                            <label for="ck_m"> Movilidad</label><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="generaActas();">OK</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </s:if>
    </body>
</html>