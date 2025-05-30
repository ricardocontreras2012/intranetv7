<%--
    Document   : commonEncuestaCurso
    Created on : 12-01-2010, 10:55:34 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consulta Resultado de la Encuesta x Curso</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/encuesta/commonEncuestaCurso-3.0.2.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.encuesta.docente"/>( <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/> <s:property
                value="#session.genericSession.getWorkSession(key).getProfesor().getNombre()"/> )
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="print-button" title="Print" type="button" class="btn btn-light" >
                                <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="export-button" title="Excel" type="button" class="btn btn-light" >
                                <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form id="encuesta" action="#">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">
                            <s:text name="label.nro"/>
                        </th>
                        <th scope="col">
                            <s:text name="label.pregunta"/>
                        </th>
                        <th scope="col">
                            <s:text name="label.promedio.pregunta"/>
                        </th>
                        <th scope="col">
                            <s:text name="label.maximo"/>
                        </th>
                        <th scope="col">
                            <s:text name="label.minimo"/>
                        </th>
                        <th scope="col">
                            <s:text name="label.desviacion"/>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).respEncta" status="row">
                        <tr>
                            <td style="width:5%; text-align: center"><s:property value="pregNro"/>
                            </td>
                            <td style="width:75%">
                                <s:property value="pregDes"/>
                            </td>
                            <td style="text-align:center">
                                <s:text name="format.calificacion"><s:param name="value" value="prom"/></s:text>
                                </td>
                                <td style="text-align:center">
                                <s:property value="maximo"/>
                            </td>
                            <td style="text-align:center">
                                <s:property value="minimo"/>
                            </td>
                            <td style="text-align:center">
                                <s:text name="format.calificacion"><s:param name="value" value="desv"/></s:text>
                                </td>
                            </tr>
                    </s:iterator>
                </tbody>
            </table>
            <table style="width:50%">
                <tr>
                    <td><s:text name="label.alumnos.respondieron"/></td>
                    <td><s:property
                            value="#session.genericSession.getWorkSession(key).respEncta.get(0).numResp"/></td>
                </tr>
                <tr>
                    <td><s:text name="label.promedio.curso"/></td>
                    <td><s:text name="format.calificacion"><s:param name="value"
                                                                value="#session.genericSession.getWorkSession(key).respEncta.get(0).cedPromCur"/></s:text></td>
                    </tr>
                    <tr>
                        <td><s:text name="label.promedio.area"/></td>
                    <s:if test="#session.genericSession.getWorkSession(key).respEncta.get(0).cedPromArea != null">
                        <td><s:text name="format.calificacion"><s:param name="value"
                                                                    value="#session.genericSession.getWorkSession(key).respEncta.get(0).cedPromArea"/></s:text></td>
                        </s:if>
                        <s:else>
                        <td></td>
                    </s:else>
                </tr>
            </table>
            <div>
                &nbsp;
            </div>    
                    
            <div>
                <s:if test="#session.genericSession.getWorkSession(key).comentarioEncuestaDocenteList.get(0).encuestaDocente.edoAgno >= 2020">
                    <p></p>
                    <p style="background-color:#ddd">- 1.	En el marco de la implementación de esta asignatura ¿qué problemas o desafíos tuvo que enfrentar este semestre? (Por ejemplo, de ética y compromiso del profesorado o estudiantado, problemas tecnológicos, con el Protocolo Institucional de Docencia, entre otros).</p>
                    <p></p>
                </s:if>
                <s:else>
                    COMENTARIOS: POSITIVOS::
                </s:else>
            </div>
            <table>
                <s:iterator value="#session.genericSession.getWorkSession(key).comentarioEncuestaDocenteList" status="row">
                    <s:if test="cedComen1 != null && cedCorrel < 80000000">
                        <tr>
                            <td><s:property value="cedComen1"/></td>
                        </tr>
                    </s:if>
                </s:iterator>
            </table>

            <div>
                <s:if test="#session.genericSession.getWorkSession(key).comentarioEncuestaDocenteList.get(0).encuestaDocente.edoAgno >= 2020">
                    <p></p>
                    <p style="background-color:#ddd">- 2.	¿Cuáles podrían ser posibles soluciones para resolver las problemáticas o desafíos planteados anteriormente?
                    <p></p>
                </s:if>
                <s:else>
                    COMENTARIOS: POR MEJORAR::
                </s:else>
            </div>
            <table>
                <s:iterator value="#session.genericSession.getWorkSession(key).comentarioEncuestaDocenteList" status="rowComen2">
                    <s:if test="cedComen2 != null && cedCorrel < 80000000">
                        <tr>
                            <td><s:property value="cedComen2"/></td>
                        </tr>
                    </s:if>
                </s:iterator>
            </table>

            <div id="hidden-input-div">
                <input type="hidden" id="contentDisposition" name="contentDisposition"
                       value='attachment;filename="ENCUESTA_<s:property value="#session.genericSession.getWorkSession(key).curso.nombreFull"/>.XLS"'/>
                <input type="hidden" id="format" name="format" value="PDF"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>