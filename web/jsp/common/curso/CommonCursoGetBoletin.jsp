<%-- 
    Document   : CommonCursoGetBoletin
    Created on : 06-12-2023, 14:30:04
    Author     : Javier Frez V.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista alumnos - Boletin</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/CommonCursoGetBoletin-1.0.0.js"></script>
    </head>
    <body class="inner-body" onload="onLoad();">
        <div class="data-tables-container" style="margin-top: 10px; line-height: 1;">
            <table id="alumnosBoletin-table" class="table table-striped table-bordered dataTable" style="width:100%">
                <thead>
                    <tr>
                        <th scope="col">
                            Rut
                        </th>
                        <th scope="col">
                            Paterno
                        </th>
                        <th scope="col">
                            Materno
                        </th>
                        <th scope="col">
                            Nombres
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).aluCarList" status="row">
                        <tr>
                            <td>
                                <a id="alumnoBoletin01_<s:property value="#row.count -1"/>">
                                    <s:property value="id.acaRut"/>
                                </a>
                            </td>
                            <td>
                                <a id="alumnoBoletin02_<s:property value="#row.count -1"/>">
                                    <s:property value="alumno.aluPaterno"/>
                                </a>
                            </td>
                            <td>
                                <a id="alumnoBoletin03_<s:property value="#row.count -1"/>">
                                    <s:property value="alumno.aluMaterno"/>
                                </a>
                            </td>
                            <td>
                                <a id="alumnoBoletin04_<s:property value="#row.count -1"/>">
                                    <s:if test="alumno.aluNombreSocial != null && alumno.aluNombreSocial.trim() != ''">
                                        <s:property value="alumno.aluNombreSocial"/>
                                    </s:if>
                                    <s:else>
                                        <s:property value="alumno.aluNombre"/>
                                    </s:else>
                                </a>
                            </td>                                                        
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
        <s:form id="alumnoBoletin-form" action="#" accept-charset="UTF-8">
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos"/>
            </div>
        </s:form>
    </body>
</html>
