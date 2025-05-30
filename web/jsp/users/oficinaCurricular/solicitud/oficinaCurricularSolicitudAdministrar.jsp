<%--
    Document   :oficinaCurricularSolicitudAdministrar
    Created on : 13-09-2010, 08:35:03 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Solicitudes</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/oficinaCurricular/solicitud/oficinaCurricularSolicitudAdministrar-2.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:if test="estado ==\"GEN\"">
                <s:text name="label.menu.solicitudes.por.recepcionar"/>
            </s:if>
            <s:else>
                <s:text name="label.menu.solicitudes.por.informar"/>
            </s:else>
        </div>

        <div id="central-div">
            <form id="solicitudes-form" action="#">
                <table id="solicitudes-table">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.nro"/></th>
                            <th scope="col"><s:text name="label.rut"/></th>
                            <th scope="col"><s:text name="label.name"/></th>
                            <th scope="col"><s:text name="label.tipo.solicitud"/></th>
                            <th scope="col"><s:text name="label.date"/></th>
                            <th scope="col"><s:text name="label.folio"/></th>
                        </tr>
                    </thead>
                    <s:iterator value="#session.genericSession.getWorkSession(key).solicitudList" status="row">
                        <tr>
                            <td><s:property value="#row.count"/></td>
                            <td><a id="sol1_<s:property value="#row.count -1"/>"><s:property
                                        value="aluCar.alumno.aluRut"/>-<s:property value="aluCar.alumno.aluDv"/></a></td>
                            <td><a id="sol2_<s:property value="#row.count -1"/>"><s:property
                                        value="aluCar.alumno.getNombre()"/></a></td>
                            <td><a id="sol3_<s:property value="#row.count -1"/>"><s:property
                                        value="tsolicitud.tsolDescrip"/></a></td>
                            <td><a id="sol5_<s:property value="#row.count -1"/>"><s:date name="solFecSol"
                                                                         format="dd/MM/yyyy"/></a></td>
                            <td><a id="sol4_<s:property value="#row.count -1"/>"><s:property value="solFolio"/></a></td>
                        </tr>
                    </s:iterator>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                </div>
            </form>
        </div>
    </body>
</html>
