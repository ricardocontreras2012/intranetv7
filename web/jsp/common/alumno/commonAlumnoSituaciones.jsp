<%--
    Document   : commonAlumnoSituaciones
    Created on : 07-06-2009, 07:05:15 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Situaciones del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />  
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/dataTables.bootstrap4.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/alumno/commonAlumnoSituaciones-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.situaciones"/>
        </div>

        <form id="situaciones-form" method="post">
            <div id="table-div">
                <table id="situaciones-table"  class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.nro"/></th>
                            <th scope="col"><s:text name="label.situacion"/></th>
                            <th scope="col"><s:text name="label.term.short"/><s:text name="label.year"/></th>
                            <th scope="col"><s:text name="label.nro.documento.presentacion"/></th>
                            <th scope="col"><s:text name="label.fecha.presentacion"/></th>
                            <th scope="col"><s:text name="label.nro.documento.aceptacion"/></th>
                            <th scope="col"><s:text name="label.fecha.aceptacion"/></th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.situaciones" status="row">
                            <tr>
                                <td style="text-align: center"><s:property value="id.sacCorrel"/></td>
                                <s:if test="tsacademica.tsaCod in {3 ,4, 5, 6, 10, 22, 99}">
                                    <td style="color: red;"><s:property value="tsacademica.tsaDescrip"/></td>
                                </s:if>
                                <s:else>
                                    <td><s:property value="tsacademica.tsaDescrip"/></td>
                                </s:else>
                                <td style="text-align: center"><s:property value="sacSemInic"/>/<s:property
                                        value="sacAgnoInic"/></td>
                                <td style="text-align: right"><s:property value="sacDocPres"/></td>
                                <td style="text-align: center"><s:date name="sacFechaPres" format="dd/MM/yyyy"/></td>
                                <td style="text-align: right"><s:property value="sacDocAcep"/></td>
                                <td style="text-align: center"><s:date name="sacFechaAcep" format="dd/MM/yyyy"/></td>
                                <s:if test="tsacademica.tsaCod in {7,8,9,14,17,18,21,22,103,104,105,106,109,122}">
                                    <td><img   class="imageLink" onClick="getFile(<s:property value="#row.count -1"/>,'<s:property value="key"/>')"
                                               src="/intranetv7/images/local/icon/download-pdf.png" height="16" width="16" alt="download"/></td>
                                    </s:if>
                                    <s:else>
                                    <td></td>
                                </s:else>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>