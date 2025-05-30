<%--
    Document   : commonMaterialCursosReutilizacion
    Created on : 25-09-2014, 08:20:59 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Cursos Reutilización de Materiales</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.menu-3.0.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialCursosReutilizacion-3.0.3.js"></script>
    </head>
    <body>
        <div class="title-div">
            &nbsp;&nbsp;&nbsp;&nbsp;<s:text name="label.title.cursos.reutilizacion"/>
        </div>

        <s:form id="material-form" action="#" method="post" accept-charset="UTF-8" enctype="multipart/form-data" theme="bootstrap">
            <div class="data-tables-container">
                <table id="cursos-table" class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.code"/></th>
                            <th scope="col"><s:text name="label.coordinacion"/></th>
                            <th scope="col"><s:text name="label.term.short"/> <s:text name="label.year"/></th>
                            <th scope="col"><s:text name="label.asignatura"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                            <tr>
                                <td style="width: 10%;text-align: right"><a
                                        id="curso_<s:property value="#row.count -1"/>"><s:property value="id.curAsign"/>
                                        <s:property value="id.curElect"/></a></td>
                                <td style="width: 10%;text-align: right"><s:property value="id.curCoord"/>-<s:if
                                        test="id.curSecc != null"><s:text name="format.seccion"><s:param
                                                value="id.curSecc"/></s:text></s:if></td>
                                <td style="width: 10%;text-align: center"><s:property value="id.curSem"/>/<s:property
                                        value="id.curAgno"/></td>
                                <td><s:property value="curNombre"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="keyNew" name="keyNew" value="<s:property value="key"/>"/>
                    <input type="hidden" id="pos" name="pos" value=""/>
                </div>
            </div>

        </s:form>
    </body>
</html>
