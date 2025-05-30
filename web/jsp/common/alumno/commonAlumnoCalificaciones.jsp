<%--
    Document   : commonAlumnoCalificaciones
    Created on : 09-06-2009, 02:19:44 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Calificaciones del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/alumno/commonAlumnoCalificaciones-3.0.0.js"></script>
    </head>
    <body class="inner-body">

        <div class="data-tables-container">
            <table id="cartola-table" class="table table-striped table-bordered dataTable">
                <thead>
                    <tr>
                        <th scope="col"><s:text name="label.code"/></th>
                        <th scope="col"><s:text name="label.coordinacion"/></th>
                        <th scope="col"><s:text name="label.term.short"/> <s:text name="label.year"/></th>
                        <th scope="col"><s:text name="label.asignatura"/></th>
                        <th scope="col"><s:text name="label.hc"/></th>
                        <th scope="col"><s:text name="label.nota"/></th>
                        <th scope="col"><s:text name="label.situacion"/></th>
                        <th scope="col"><s:text name="label.procedencia"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.cartolaView" status="row">
                        <tr>
                            <td style="text-align:right; width: 10%"><s:property value="id.cartAsign"/>
                                <s:property value="id.cartElect"/></td>
                            <td style="text-align:right; width: 10%"><s:property value="cartCoord"/> <s:if
                                    test="cartSecc != null"><s:text name="format.seccion"><s:param
                                            value="cartSecc"/></s:text></s:if></td>
                            <td style="text-align:center; width: 10%"><s:property
                                    value="id.cartSem"/>/<s:property value="id.cartAgno"/></td>
                            <td><s:property value="cartNombreCompleto"/></td>
                            <td style="text-align:center; width: 5%"><s:property value="cartHC"/></td>
                            <s:if test="cartSitAlu ==\"A\"">
                                <td class="aprobado" style="text-align:center"><s:property value="cartNotaFin"/></td>
                                <td class="aprobado" style="text-align:center"><s:property value="cartSitAlu"/></td>
                            </s:if>
                            <s:else>
                                <td class="reprobado" style="color: red;text-align:center"><s:property
                                        value="cartNotaFin"/></td>
                                <td class="reprobado" style="color: red;text-align:center"><s:property value="cartSitAlu"/></td>
                            </s:else>
                            <td style="text-align:center"><s:property value="cartProc"/></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
    </body>
</html>