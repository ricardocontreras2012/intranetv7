<%--
    Document   : commonAlumnoEvaluacionParcial
    Created on : 28-12-2010, 04:26:01 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista Evaluaciones Parciales del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/common/alumno/commonAlumnoEvaluacionParcial-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.evaluacion.parciales"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>

        <form id="evaluaciones-form" action="#">
            <table id="evaluaciones-table" style="width: 40%" class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col" style="width:95%"><s:text name="label.evaluacion"/></th>
                        <th scope="col" style="width:5%"><s:text name="label.nota"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.evaluacionAlumnoList" status="row">
                        <tr>
                            <td><s:property value="evaluacion.cursoTevaluacion.tevaluacion.tevalDes"/><s:property
                                    value="evaluacion.id.evalEval"/></td>
                            <td style="text-align: center"><s:if test="evaluNota != null">
                                    <s:text name="format.calificacion"><s:param value="evaluNota"/></s:text>
                                </s:if>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>