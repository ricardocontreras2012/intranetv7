<%--
    Document   : alumnoIdIngresos
    Created on : 06-06-2009, 07:05:20 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Carreras del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/id/alumnoIdIngresos-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.seleccione.carrera"/>
        </div>

        <div id="central-div">
            <form id="carreras-form" action="#" method="post">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">
                                <s:text name="label.carrera"/>
                            </th>
                            <th scope="col">
                                <s:text name="label.term.short"/> <s:text name="label.year"/> <s:text name="label.ingreso"/>
                            </th>
                            <th scope="col">
                                <s:text name="label.calidad"/>
                            </th>
                            <th scope="col">
                                <s:text name="label.situacion"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).aluCarList" status="row">
                            <tr>
                                    <td>
                                            <a id="searchAnchor_<s:property value="#row.count -1"/>">
                                        <s:property value="id.acaCodCar"/>&nbsp;<s:property value="nombreCarrera"/>
                                    </a>
                                </td>
                                <td>
                                    <s:property value="id.acaSemIng"/> <s:property value="id.acaAgnoIng"/>
                                </td>
                                <td>
                                    <s:property value="tcalidad.tcaDescrip"/>
                                </td>
                                <td>
                                    <s:property value="tsacademica.tsaDescrip"/>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div id="hidden-input-div">
                    <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                </div>
            </form>
        </div>
    </body>
</html>