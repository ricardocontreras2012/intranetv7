<%--
    Document   :oficinaCurricularActaxImprimir
    Created on : 03-11-2010, 10:09:50 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consulta Actas</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/oficinaCurricular/acta/oficinaCurricularActaConsulta-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:if test="%{#session.genericSession.getWorkSession(key).flag==\"G\"}">
                ACTAS SIN EMITIR
            </s:if>
            <s:if test="%{#session.genericSession.getWorkSession(key).flag==\"E\"}">
                 ACTAS SIN IMPRIMIR
            </s:if>
            <s:if test="%{#session.genericSession.getWorkSession(key).flag==\"I\"}">
                ACTAS SIN RECEPCIONAR
            </s:if>
            <s:if test="%{#session.genericSession.getWorkSession(key).flag==\"*\"}">
                ACTAS
            </s:if>

        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">

                        <div class="btn-group">
                            <button id="search-button" title="Buscar" type="button" class="btn btn-light" >
                                <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                            </button>
                        </div>
                        <%--div class="btn-group">
                            <button id="print-button" title="Imprimir" type="button" class="btn btn-light" >
                                <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.print"/></span>
                            </button>
                        </div--%>
                    </div>
                </div>
            </div>
        </div>

        <form id="actas-form" action="#" method="post">
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

            <table id="actas-table" class="table table-striped table-bordered dataTable">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"><s:text name="folio"/></th>
                        <th scope="col"><s:text name="tipo"/></th>
                        <th scope="col"><s:text name="curso"/></th>
                        <th scope="col"><s:text name="nombre"/></th>
                        <th scope="col"><s:text name="profesor"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).actas" status="row">
                        <tr>
                            <td style="width: 4%"><input type="checkbox" id="ck_<s:property value="#row.count -1"/>"
                                                         name="ck_<s:property value="#row.count -1"/>"/></td>
                            <td style="width: 6%"><s:property value="id.acalFolio"/></td>
                            <td style="width: 6%"><s:property value="acalTipo"/></td>
                            <td style="width: 10%"><s:property value="curso.id.curAsign"/> <s:property
                                    value="curso.id.curElect"/> <s:property value="curso.id.curCoord"/> <s:property
                                    value="curso.id.curSecc"/></td>
                            <td><s:property value="curso.curNombre"/></td>
                            <td><s:property value="curso.curProfesores"/></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>
