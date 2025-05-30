<%--
    Document   : commonCursoListaCarreras
    Created on : 09-09-2010, 09:00:26 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Tipos de Carrera</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/curso/commonCursoListaCarreras-3.0.2.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.definicion.cursos"/> <s:text name="label.title.carreras"/>
        </div>

        <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
            <div class="errorBox">
                <s:actionerror/><s:actionmessage/><s:fielderror/>
            </div>
        </s:if>
        <form id="carreras-form" action="#" method="post">            
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
                <table id="carreras-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col">
                                <s:text name="label.carrera.programa"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).miCarreraSupportList" status="row">
                            <tr>
                                <td>
                                    <a id="carrera_<s:property value="#row.count -1"/>">
                                        <s:property value="nombreCarrera"/>
                                    </a>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="tipoDummy" name="tipoDummy" value="<s:property value="#session.genericSession.userType"/>"/>
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                <input type="hidden" id="actionName" name="actionName" value="<s:property value="actionName"/>"/>
                <input type="hidden" id="actionCall" name="actionCall" value="<s:property value="actionCall"/>"/>
            </div>
        </form>
    </body>
</html>