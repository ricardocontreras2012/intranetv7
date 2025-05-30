<%--
    Document   : commonActaListaActas
    Created on : 20-07-2011, 10:44:05 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consulta Estado de Actas x Carrera</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/acta/commonActaListaActas-3.0.6.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.acta"/> <s:text name="label.title.carreras"/>&nbsp;<s:property value="#session.genericSession.getWorkSession(key).nombreCarrera"/>&nbsp;
                <s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>/<s:property value="#session.genericSession.getWorkSession(key).semAct"/>
        </div>       
        
        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">

                        <div class="btn-group">
                            <button id="export-button" title="Exportar" type="button" class="btn btn-light" >
                                <span class="fa fa-th"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                            </button>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>

        <form id="actas-form" action="#">
            <div>
                <table id="actas-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.code"/></th>
                            <th scope="col"><s:text name="label.name"/></th>
                            <th scope="col"><s:text name="label.profesor"/></th>
                            <th scope="col"><s:text name="label.folio"/></th>
                            <th scope="col"><s:text name="label.tipo"/></th>
                            <th scope="col"><s:text name="label.estado"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).actaConsultaSupportList" status="row">
                            <tr>
                                <td><a id="searchAnchor_<s:property value="#row.count -1"/>"><s:property value="acalAsign"/> <s:property value="acalElect"/>
                                    <s:property value="acalCoord"/> <s:property value="acalSecc"/></a></td>
                                <td><s:property value="nombreCurso"/></td>
                                <td><s:property value="profesores"/></td>
                                <td><s:property value="id.acalFolio"/></td>
                                <td><s:property value="acalTipo"/></td>
                                <td><s:property value="estado"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos"/>
            </div>
        </form>
    </body>
</html>