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
        <script type="text/javascript" src="/intranetv7/js/bootstrap/5.3.0/bootstrap.min.js"></script>
        <style>
            .step {
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
                background: #198754; /* verde éxito */
                color: white;
            }

            .step::after {
                content: '';
                position: absolute;
                top: 20px;
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
                background: #198754;
            }

            .step .label {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container py-5">
            <h2 class="mb-4 text-center">Seguimiento de Documentación</h2>

            <!-- Progress bar -->
            <div class="d-flex justify-content-between mb-5">
                <div class="step completed">
                    <div class="circle">1</div>
                    <div class="label">Envío Documentación</div>
                </div>
                <!-- validar que todos los documentos AL, su estado = 2 -->
                <div class="step">
                    <div class="circle">2</div>
                    <div class="label">Solicitudes Internas</div>
                </div>
                <div class="step">
                    <div class="circle">3</div>
                    <div class="label">Verificación</div>
                </div>
                <div class="step">
                <!-- Si expediente logro = TR -->
                    <div class="circle">4</div>
                    <div class="label">Despacho Expediente</div>
                </div>
            </div>

            <!-- Detalles de cada paso -->
            <div class="accordion" id="accordionSteps">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading1">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="true">
                            Paso 1: Envío Documentación Alumno
                        </button>
                    </h2>
                    <div id="collapse1" class="accordion-collapse collapse show" data-bs-parent="#accordionSteps">
                        <div class="accordion-body">
                            <table class="table">
                                <tbody>
                                <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                    <s:if test="tDocExpediente.tdeUser == 'AL'">
                                        <tr>
                                            <td>
                                                <s:if test="edeEstado == 1">
                                                    Enviado
                                                </s:if>
                                                <s:elseif test="edeEstado == 2">
                                                    Aceptado
                                                </s:elseif>
                                                <s:elseif test="edeEstado == 3">
                                                    Observado
                                                </s:elseif>
                                                <s:elseif test="edeEstado == 4">
                                                    Rechazado
                                                </s:elseif>
                                            </td>
                                            <td><strong><s:property value="tDocExpediente.tdeDes" /></strong></td>
                                            <td><a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><s:property value="edeFile" /></a></td>
                                            <td><s:property value="edeObservacion"/></td>
                                        </tr>
                                    </s:if>
                                </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading2">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse2">
                            Paso 2: Solicitud de Constancias Internas
                        </button>
                    </h2>
                    <div id="collapse2" class="accordion-collapse collapse" data-bs-parent="#accordionSteps">
                        <div class="accordion-body">
                            <table class="table">
                                <tbody>
                                <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                    <s:if test="tDocExpediente.tdeUser == 'US'">
                                        <tr>
                                            <td>
                                                <s:if test="edeEstado == 1">
                                                    Enviado
                                                </s:if>
                                                <s:elseif test="edeEstado == 2">
                                                    Aceptado
                                                </s:elseif>
                                                <s:elseif test="edeEstado == 3">
                                                    Observado
                                                </s:elseif>
                                                <s:elseif test="edeEstado == 4">
                                                    Rechazado
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
                    <h2 class="accordion-header" id="heading3">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse3">
                            Paso 3: Verificación
                        </button>
                    </h2>
                    <div id="collapse3" class="accordion-collapse collapse" data-bs-parent="#accordionSteps">
                        <div class="accordion-body">
                            <ul>
                                <li>Carátula de expediente firmada</li>
                                <li>Certificado de nacimiento</li>
                                <li>Solicitud de género, marcar su preferencia y firmar</li>
                                <li>Comprobante de pago arancel</li>
                                <li>Certificado de título anterior (con código de verificación) - para prosecución o bachillerato</li>
                                <li>Constancia de Pago (solicitar a SDT) - para prosecución</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading4">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse4">
                            Paso 4: Despacho de Expediente
                        </button>
                    </h2>
                    <div id="collapse4" class="accordion-collapse collapse" data-bs-parent="#accordionSteps">
                        <div class="accordion-body">
                            <p>Una vez verificado, el expediente será enviado a la <strong>Unidad de Títulos y Grados</strong> para el trámite final.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
