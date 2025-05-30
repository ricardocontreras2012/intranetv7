<%--
    Document   : profesorAsistenciaNew
    Created on : 09-08-2010, 09:21:53 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Creación de Asistencia</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/reporte/profesorReporteFormulario-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/asistencia/profesorAsistenciaNew-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.asistencia"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>


        <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
        </button>

        <form id="lista-form" action="#" method="post">
            <div id="row">
                <div class="col-12 col-sm-6 col-md-5 col-lg-2 mb-3">
                    <label for="fechaAsistencia"><s:text name="label.date"/></label>
                    <input id="fechaAsistencia" name="fechaAsistencia"/>
                </div>
            </div>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"><s:text name="label.rut"/></th>
                        <th scope="col"><s:text name="label.paterno"/></th>
                        <th scope="col"><s:text name="label.materno"/></th>
                        <th scope="col"><s:text name="label.name"/></th>
                        <th scope="col" style="width:5%; text-align: center"><label for="checkHeadInput"></label><input
                                type="checkbox" id="checkHeadInput"
                                name="checkHeadInput" value="off"
                                onclick="checkingHead('lista-form');"/>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).nominaCurso" status="row">
                        <tr>
                            <td style="text-align: right">
                                <s:property value="#row.count"/>
                            </td>
                            <td>
                                <s:property value="alumno.aluRut"/>-<s:property value="alumno.aluDv"/>
                            </td>
                            <td>
                                <s:property value="alumno.aluPaterno"/>
                            </td>
                            <td>
                                <s:property value="alumno.aluMaterno"/>
                            </td>
                            <td>
                                <s:property value="alumno.aluNombre"/>
                            </td>
                            <%--td align="center">
                                <img height="80" id="foto<s:property value="#row.count"/>" src="CommonCursoGetFotoAlumno?pos=<s:property value="#row.count -1"/>&key=<s:property value="key"/>" alt=""/>
                            </td--%>
                            <td style="width:5%; text-align:center">
                                &nbsp;<input type="checkbox" id="ck_<s:property value="#row.count -1"/>"
                                             name="ck_<s:property value="#row.count -1"/>"/>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="fechaActual" name="fechaActual"
                       value="<s:property value="%{#session.genericSession.getWorkSession(key).fechaActual}"/>"/>
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>