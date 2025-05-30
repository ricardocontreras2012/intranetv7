<%--
    Document   : registradorCurricularListaReincorporadosPorEliminacion
    Created on : 03-08-2010, 09:02:46 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Reincorporados</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/reincorporacion/registradorCurricularReincorporacionNomina-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            Nómina Reincorporados
        </div>

        <form id="nomina-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="nomina-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col">RUT</th>
                            <th scope="col">Paterno</th>
                            <th scope="col">Materno</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Carrera</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).reincorporacionList" status="row">
                            <tr>
                                <td><s:property value="aluCar.alumno.aluRut"/>-<s:property value="aluCar.alumno.aluDv"/></td>
                                <td><s:property value="aluCar.alumno.aluPaterno"/></td>
                                <td><s:property value="aluCar.alumno.aluMaterno"/></td>
                                <td><s:property value="aluCar.alumno.aluNombre"/></td>
                                <td><s:property value="aluCar.id.acaCodCar"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>