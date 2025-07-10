<%--
    Document   : profesorCalificacionEvaluacionNominaCurso
    Created on : 01-12-2009, 02:14:30 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Notas Parciales</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/calificacion/profesorCalificacionEvaluacionNominaCurso-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:property
                value="#session.genericSession.getWorkSession(key).evaluacionAlumno.get(0).evaluacion.cursoTevaluacion.tevaluacion.tevalDes"/><s:property
                value="#session.genericSession.getWorkSession(key).evaluacionAlumno.get(0).evaluacion.id.evalEval"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>

        <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
        </button>

        <form id="nomina-form" onsubmit="return false;" action="#" method="post">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col" style="width:5%"><s:text name="label.nro"/></th>
                        <th scope="col" style="width:10%; text-align: center"><s:text name="label.rut"/></th>
                        <th scope="col"><s:text name="label.name"/></th>
                        <th scope="col" style="width:10%; text-align:center"><s:text name="label.nota"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).evaluacionAlumno" status="row">
                        <tr>
                            <td style="text-align: right"><s:property value="#row.count"/></td>
                            <td style="text-align: right"><s:property value="aluCar.alumno.aluRut"/>-<s:property
                                    value="aluCar.alumno.aluDv"/>&nbsp;&nbsp;</td>
                            <td><s:property value="aluCar.alumno.aluPaterno"/> <s:property
                                    value="aluCar.alumno.aluMaterno"/> 
                                <s:if test="aluCar.alumno.aluNombreSocial != null && aluCar.alumno.aluNombreSocial.trim() != ''">
                                    <s:property value="aluCar.alumno.aluNombreSocial"/>
                                </s:if>
                                <s:else>
                                    <s:property value="aluCar.alumno.aluNombre"/>
                                </s:else>

                            </td>
                            <td style="width:10%; text-align:center">
                                <s:if test="evaluNota == null">
                                    <input size="3"
                                           id="row_<s:property value="aluCar.alumno.aluRut"/>"
                                           name="row_<s:property value="aluCar.alumno.aluRut"/>" type="text"
                                           value="<s:property value="evaluNota"/>"/>
                                </s:if>
                                <s:else>
                                    <s:if test="evaluNota >= 4">
                                        <input class="aprobado"
                                               size="3"
                                               id="row_<s:property value="aluCar.alumno.aluRut"/>"
                                               name="row_<s:property value="aluCar.alumno.aluRut"/>" type="text"
                                               value="<s:text name="format.calificacion"><s:param value="evaluNota"/></s:text>"/>
                                    </s:if>
                                    <s:else>
                                        <input class="reprobado"
                                               size="3"
                                               id="row_<s:property value="aluCar.alumno.aluRut"/>"
                                               name="row_<s:property value="aluCar.alumno.aluRut"/>" type="text"
                                               value="<s:text name="format.calificacion"><s:param value="evaluNota"/></s:text>"/>
                                    </s:else>
                                </s:else>
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