<%--
    Document   : asistenteGetCalificaciones
    Created on : 16-10-2023, 02:19:44 PM
    Author     : Felipe
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
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/asistente/asistenteGetCalificaciones-3.0.1.js"></script>
    </head>
    <body class="inner-body">

        <div class="data-tables-container">
            <div class="mb-3">
                <div class="btn-group" role="group">
                    <button id="imprimir-button" title="Imprimir" type="button" class="btn btn-light" >
                        <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                    </button>
                </div>
            </div>
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
            <form id="cartola-form" accept-charset="UTF-8" action="#">        
                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                </div>
            </form>
        </div>
    </body>
</html>