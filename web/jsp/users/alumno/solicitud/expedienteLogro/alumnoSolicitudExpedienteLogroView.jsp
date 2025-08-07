<%-- 
    Document   : alumnoSolicitudExpedienteLogroView
    Created on : 09-06-2025, 12:09:54
    Author     : Alvaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Seguimiento de Documentación</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/5.3.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/2.11.8/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/5.3.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/expedienteLogro/alumnoSolicitudExpedienteLogroView-1.0.3.js"></script>
        <style>

            .step, .owner {
                text-align: center;
                position: relative;
                flex: 1;
            }

            .step .circle {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                background: #ddd;
                margin: 0 auto;
                line-height: 40px;
                color: #000;
                font-weight: bold;
            }

            .step.completed .circle {
                background: #00a499; /* verde éxito */
                color: white;
            }

            .step::after {
                content: '';
                position: absolute;
                top: 25px;
                left: 50%;
                width: 100%;
                height: 2px;
                background: #ddd;
                z-index: -1;
            }

            .step:last-child::after {
                content: none;
            }

            .step.completed::after {
                background: #00a499;
            }

            .step .label {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container py-5">
            <!--h2 class="mb-4 text-center">Seguimiento de Documentación</h2-->

            <!-- Progress bar -->
            <div class="d-flex justify-content-between mb-3" style="background: #00a499; color: white; border-radius: 10px;">
                <div class="owner">
                    <div>Alumno</div>
                </div>
                <div class="owner">
                    <div>Registro Curricular</div>
                </div>
                <div class="owner">
                    <div>Registro Curricular</div>
                </div>
                <div class="owner">
                    <div>Títulos y Grados USACH</div>
                </div>
            </div>
            
            <div class="d-flex justify-content-between mb-3">
                <div class="step completed" id="step-1">
                    <div class="circle" onclick="abrirPaso(1)" style="cursor: pointer">1</div>
                    <div class="label">Envío de Documentación</div>
                </div>
                <div class="step" id="step-2">
                    <div class="circle" onclick="abrirPaso(2)" style="cursor: pointer">2</div>
                    <div class="label">Verificación</div>
                </div>
                <div class="step" id="step-3">
                    <div class="circle" onclick="abrirPaso(3)" style="cursor: pointer">3</div>
                    <div class="label">Solicitud de Constancias</div>
                </div>
                <div class="step" id="step-4">
                    <div class="circle" onclick="abrirPaso(4)" style="cursor: pointer">4</div>
                    <div class="label">Despacho Expediente</div>
                </div>
            </div>

            <!-- Detalles de cada paso -->
            <div class="accordion" id="accordionSteps">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading1">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="false">
                            Paso 1: Envío de Documentación
                        </button>
                    </h2>
                    <div id="collapse1" class="accordion-collapse collapse" data-bs-parent="#accordionSteps">
                        <div class="accordion-body">
                            <p>Alumno envió la documentación requerida.</p>
                        </div>
                    </div>
                </div>

                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading2">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse2" aria-expanded="false"> 
                            Paso 2: Verificación
                        </button>
                    </h2>
                    <div id="collapse2" class="accordion-collapse collapse" data-bs-parent="#accordionSteps">
                        <div class="accordion-body">
                            <form id="expediente-form" action="#">
                                <table class="table">
                                    <tbody>
                                        <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                            <s:if test="tDocExpediente.tdeUser == 'AL'">
                                                <tr>
                                                    <td>
                                                        <s:if test="edeEstado == null">
                                                            <span data-estado-al="0"></span>
                                                        </s:if>
                                                        <s:if test="edeEstado == 1">
                                                            <span data-estado-al="1" class="badge text-bg-primary">Enviado</span>
                                                        </s:if>
                                                        <s:elseif test="edeEstado == 2">
                                                            <span data-estado-al="2" class="badge text-bg-success">Aceptado</span>
                                                        </s:elseif>
                                                        <s:elseif test="edeEstado == 3">
                                                            <button type="button" class="btn btn-link text-danger" style="padding: 0px; text-decoration: none;"
                                                                    data-bs-toggle="tooltip"
                                                                    data-bs-placement="top"
                                                                    data-bs-custom-class="custom-tooltip"
                                                                    data-bs-html="true"
                                                                    data-bs-title="<s:property value="edeObservacion"/>. <br/><strong>Vuelva a subir su documento.</strong>">
                                                                <span data-estado-al="3" class="badge text-bg-danger">Observado</span>
                                                                <span class="fa fa-info-circle" aria-hidden="true"></span>
                                                            </button>
                                                        </s:elseif>
                                                        <s:elseif test="edeEstado == 4">
                                                            <span data-estado-al="4" class="badge text-bg-danger">Rechazado</span>
                                                        </s:elseif>
                                                    </td>
                                                    <td><strong><s:property value="tDocExpediente.tdeDes" /></strong></td>
                                                    <td>
                                                        <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><i class="fa fa-download" aria-hidden="true"></i></a>
                                                    </td>
                                                    <td>
                                                        <s:if test="edeEstado == 3">
                                                            <s:if test="id.edeTdoc == 10 ">
                                                                <button id="caratula-button" title="<s:property value="tDocExpediente.tdeTexto" />" type="button" class="btn btn-light" >
                                                                    <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                                </button>
                                                            </s:if>
                                                            <s:if test="id.edeTdoc == 30">
                                                                <a href="https://www.registrocivil.cl/" title="<s:property value="tDocExpediente.tdeTexto" />" target="_blank" class="btn btn-light" >
                                                                    <span class="fa fa-globe"></span>&nbsp; <span class="hidden-xs"><s:text name="Registro Civil"/></span>
                                                                </a>
                                                            </s:if>
                                                            <s:if test="id.edeTdoc == 100">
                                                                <a href="https://fae.usach.cl/fae/docs/intranet/solicitud-de-genero.pdf" title="<s:property value="tDocExpediente.tdeTexto" />" target="_blank" class="btn btn-light" >
                                                                    <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                                </a>
                                                            </s:if>
                                                            <s:if test="id.edeTdoc == 110">
                                                                <button id="pago-arancel-button" title="<s:property value="tDocExpediente.tdeTexto" />" type="button" class="btn btn-light" >
                                                                    <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                                </button>
                                                            </s:if>
                                                            <s:if test="id.edeTdoc == 120">
                                                                <a href="mailto:ingresoderequerimientosefusach@usach.cl" title="<s:property value="tDocExpediente.tdeTexto" />" target="_blank" class="btn btn-light" >
                                                                    <span class="fa fa-envelope-o"></span>&nbsp; <span class="hidden-xs">Escribir</span>
                                                                </a>
                                                            </s:if>
                                                        </s:if>
                                                    </td>
                                                    <td>
                                                        <s:if test="edeEstado == 3">
                                                            <input type="file" title="Subir Documento" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                        </s:if>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                    </tbody>
                                </table>
                                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading3">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse3" aria-expanded="false">
                            Paso 3: Solicitud de Constancias
                        </button>
                    </h2>
                    <div id="collapse3" class="accordion-collapse collapse" data-bs-parent="#accordionSteps">
                        <div class="accordion-body">
                            <table class="table">
                                <tbody>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                        <s:if test="tDocExpediente.tdeUser == 'US'">
                                            <tr>
                                                <td>
                                                    <s:if test="edeEstado == null">
                                                        <span data-estado-us="0"></span>
                                                    </s:if>
                                                    <s:if test="edeEstado == 1">
                                                        <span data-estado-us="1" class="badge text-bg-primary">Enviado</span>
                                                    </s:if>
                                                    <s:elseif test="edeEstado == 2">
                                                        <span data-estado-us="2" class="badge text-bg-success">Aceptado</span>
                                                    </s:elseif>
                                                    <s:elseif test="edeEstado == 3">
                                                        <span data-estado-us="3" class="badge text-bg-warning">Observado</span>
                                                    </s:elseif>
                                                    <s:elseif test="edeEstado == 4">
                                                        <span data-estado-us="4" class="badge text-bg-danger">Rechazado</span>
                                                    </s:elseif>
                                                    <s:elseif test="edeEstado == 5">
                                                        <span data-estado-us="5" class="badge text-bg-primary">Solicitado</span>
                                                    </s:elseif>
                                                </td>
                                                <td><strong><s:property value="tDocExpediente.tdeDes" /></strong></td>
                                            </tr>
                                        </s:if>
                                    </s:iterator>
                                </tbody>
                            </table>
                            <p><strong>Plazo de respuesta:</strong> 20 días hábiles</p>
                        </div>
                    </div>
                </div>

                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading4">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse4" aria-expanded="false">
                            Paso 4: Despacho de Expediente
                        </button>
                    </h2>
                    <div id="collapse4" class="accordion-collapse collapse" data-bs-parent="#accordionSteps">
                        <div class="accordion-body">
                            <input type="hidden" name="exp-estado" id="exp-estado" value="<s:property value="#session.genericSession.getWorkSession(key).expedienteLogro.explEstado" />" />
                            <s:if test="#session.genericSession.getWorkSession(key).expedienteLogro.explEstado == 'GE'">
                                <p>Una vez verificado, el expediente será enviado a la <strong>Unidad de Títulos y Grados</strong> para el trámite final.</p>
                            </s:if>
                            <s:else>
                                <p>Su expediente fue enviado a <strong>Unidad de Títulos y Grados</strong> con fecha: <strong><s:property value="#session.genericSession.getWorkSession(key).expedienteLogro.explFecOrd" /></strong></p>
                                <p>Plazo máximo de respuesta por parte de Unidad de Títulos y Grados, 37 días hábiles.</p>
                            </s:else>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
