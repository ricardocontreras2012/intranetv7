<%--
    Document   : commonActaRectificatoriaCursos
    Created on : 18-abr-2014
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Cursos Acta Rectificatoria</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/acta/commonActaRectificatoriaCursos-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.cursos"/> <s:text name="label.title.acta.rectificatoria"/> &nbsp;<s:property
                value="#session.genericSession.getWorkSession(key).nombreCarrera"/>
        </div>
        <form id="cursos-form" action="#" method="post">
            <table id="cursos-table" class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col"><s:text name="label.code"/></th>
                        <th scope="col"><s:text name="label.name"/></th>
                        <th scope="col"><s:text name="label.profesor"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                        <tr>
                            <td><a onclick="getActa(<s:property value="#row.count -1"/>);"><s:property value="id.curAsign"/>-<s:property value="id.curElect"/></a>
                                <s:property value="id.curCoord"/><s:property value="id.curSecc"/></td>
                            <td><s:property value="curNombre"/></td>
                            <td><s:property value="curProfesores"/></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
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