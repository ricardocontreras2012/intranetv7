<%--
    Document   : profesorReporteFormulario
    Created on : 15-07-2009, 08:50:28 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Creación de Reporte de Clases x Curso</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/reporte/profesorReporteFormulario-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.reporte"/> <s:property
                value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>

        <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
        </button>

        <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
            <div class="errorBox">
                <s:actionerror/><s:actionmessage/><s:fielderror/>
            </div>
        </s:if>

        <s:form id="reporte-form" accept-charset="UTF-8" enctype="multipart/form-data" action="#" method="post" theme="bootstrap">
            <table>
                <tr>
                    <td style="width: 10%"><s:text name="label.modulo.horario"/></td>
                    <td style="width: 60%">
                        <div style="width: 80px">
                            <s:select
                                id="moduloHorario"
                                name="moduloHorario"
                                headerKey="1"
                                headerValue="%{seleccioneModulo}"
                                list="#session.genericSession.getWorkSession(key).modulos"
                                />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="fecha"><s:text name="label.date"/></label>
                    </td>
                    <td>
                        <input id="fecha" name="fecha"  width="300" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="objetivos"><s:text name="label.objetivos"/></label>
                    </td>
                    <td>
                        <textarea name="objetivos" id="objetivos" rows="3" cols="120" maxlength="1000" class="form-control">
                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <s:property value="objetivos"/>
                            </s:if>
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="contenido"><s:text name="label.contenido"/></label>
                    </td>
                    <td>
                        <textarea name="contenido" id="contenido" rows="3" cols="120" maxlength="1000" class="form-control">
                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <s:property value="contenido"/>
                            </s:if>
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="metodo"><s:text name="label.metodo"/></label>
                    </td>
                    <td>
                        <textarea name="metodo" id="metodo" rows="3" cols="120" maxlength="1000" class="form-control">
                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <s:property value="metodo"/>
                            </s:if>
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="observaciones"><s:text name="label.observaciones"/></label>
                    </td>
                    <td>
                        <textarea name="observaciones" id="observaciones" rows="3" cols="120" class="form-control">
                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <s:property value="observaciones"/>
                            </s:if>
                        </textarea>
                    </td>
                </tr>
                <tr style="height:20px">
                    <td style="width: 10%;"><span class="fa fa-paperclip"></span>&nbsp;
                        <s:text name="label.archivo.attach"/></td>
                    <td><s:file id="upload" name="upload" label="" size="45"
                            cssStyle="height: 24px; width:99%"/></td>
                </tr>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="fechaActual" name="fechaActual"
                       value="<s:property value="%{#session.genericSession.getWorkSession(key).fechaActual}"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </s:form>
        <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
            <script type="text/javascript">
                //cambioModulo();
            </script>
        </s:if>
    </body>
</html>