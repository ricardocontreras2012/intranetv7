<%-- 
    Document   : alumnoSolicitudExpedienteLogroGetLogros
    Created on : 26-05-2025, 12:39:26
    Author     : Ricardo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Logros</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/expedienteLogro/alumnoSolicitudExpedienteLogroGetLogros-3.0.1.js"></script>
    </head>
    <body>
        <form id="solicitudes-form" action="#">
            <div class="data-tables-container">
                <table id="solicitudes-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>                            
                            <th scope="col" style="width:100%">Logro</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).expedienteLogroList" status="row">
                            <tr>
                            <s:if test="#session.genericSession.getWorkSession(key).aluCar.alumno.aluSexo == \"1\"">
                                <td><a id="expl_<s:property value="#row.count -1"/>">
                                <s:property value="planLogro.plalLin1F"/> <s:property value="planLogro.plalLin2F"/></a>
                                </td>
                            </s:if>
                            <s:else>
                                <td><a id="expl_<s:property value="#row.count -1"/>">
                                <s:property value="planLogro.plalLin1M"/> <s:property value="planLogro.plalLin2M"/></a>
                                </td>
                            </s:else>    
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                </div>
            </div>
        </form>
    </body>
</html>
