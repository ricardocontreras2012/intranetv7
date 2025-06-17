<%-- 
    Document   : alumnoSolicitudExpedienteLogroNew.jsp
    Created on : 03-06-2025, 12:27:22
    Author     : Alvaro
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Proceso de Obtención de Título</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/5.3.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/5.3.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/expedienteLogro/alumnoSolicitudExpedienteLogroNew-1.0.6.js"></script>
        <style>
            h4 { color: #00a499 !important;}
        </style>
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="card shadow rounded-4">
                <div class="card-body p-5">
                    <div id="paso1" style="display:block;">
                        <h2 class="text-center mb-4" style="color: #00a499;">🎓 ¡Felicidades por alcanzar este gran logro!</h2>
                        <p class="text-center">A continuación, te indicamos los pasos a seguir para obtener tu Licenciatura y Título.</p>

                        <hr>

                        <h4>1. Revisión y preparación de documentos</h4>
                        <ul>
                            <li>Verifica tus datos personales y actualízalos  <a href="AlumnoGetMisDatos?key=<s:property value="key"/>">aquí</a>. Si cambiaste tu nombre o apellidos ante el Registro Civil, adjunta el certificado de nacimiento actualizado.</li>
                            <li>Descarga y completa los formularios requeridos: carátula de expediente, solicitud de género, solicitud de pago de arancel y datos bancarios para realizar transferencia.</li>
                        </ul>

                        <h4 class="mt-4">Paso 2: Carga de documentación en PDF</h4>
                        <ul>
                            <li>Sube todos los documentos solicitados, carátula de expediente firmada, certificado de nacimiento, solicitud de género, marcar su preferencia y firmar, comprobante de pago arancel. </li>
                            <li>En caso de haber ingresado por prosecución de estudios o bachillerato, también debes subir, certificado de título anterior con código de verificación, constancia de pago, solicitarla a SDT, sólo para estudiantes que hayan ingresado por prosecución de estudios.</li>
                        </ul>

                        <h4 class="mt-4">Paso 3: Validación y gestión interna</h4>
                        <ul>
                            <li>Registro Curricular FAE validará y confirmará la documentación y gestionará internamente las constancias necesarias (biblioteca central, departamento de beneficios estudiantiles, situación financiera).</li>
                            <li>Plazo de respuesta 20 días hábiles.</li>
                        </ul>

                        <h4 class="mt-4">Paso 4: Envío del certificado</h4>
                        <ul>
                            <li>El expediente se enviará a la Unidad de Títulos y Grados, que te enviará el certificado de Grado Académico/Título Profesional a tu correo institucional, en un plazo aproximado de 37 días hábiles</li>
                        </ul>
                        
                        <div class="alert alert-warning" role="alert">
                            <strong>Importante</strong>: puedes revisar el estado de tu solicitud en la intranet
                        </div>
                        <p>Para dudas, dirígete al enlace de preguntas frecuentes en el sitio <a target="_blank" href="https://fae.usach.cl/fae/index.php/apoyo-estudiantil/registro-curricular">fae.usach.cl</a></p>
                        <p>O puedes contactar a los siguientes correos:</p>
                        <ul>
                            <li><strong>Ingeniería Comercial:</strong> titulacion.ingeco@usach.cl</li>
                            <li><strong>Contador Público y Auditor:</strong> titulacion.cpa@usach.cl</li>
                            <li><strong>Administración Pública:</strong> titulacion.ap@usach.cl</li>
                            <li><strong>Diplomados y Postítulos:</strong> titulacion.educo@usach.cl</li>
                            <li><strong>Magíster y Doctorado:</strong> postgrados@usach.cl</li>
                        </ul>

                        <div class="text-end mt-4">
                            <button class="btn btn-primary" id="btnSiguiente">Siguiente</button>
                        </div>

                    </div>

                    <div id="paso2" style="display:none;">
                        <form id="expediente-form" action="#">
                            <h4 class="mb-4">Documentación requerida para gestionar el Certificado de Grado Académico o Título Profesional</h4>

                            <div class="mb-3">
                                <p>Revisa y actualiza tus datos personales <a href="AlumnoGetMisDatos?key=<s:property value="key"/>">aquí</a></p>
                                <div class="form-check my-3 bold">
                                    <input class="form-check-input" type="checkbox" id="aceptarRevision">
                                    <label class="form-check-label" for="aceptarRevision">
                                        <strong>Confirmo que mis datos personas están correctos.</strong>
                                    </label>
                                </div>
                                <p><i>Importante: Si cambiaste tu nombre o apellidos ante el Registro Civil, adjunta el certificado de nacimiento actualizado, para generar el cambio en el sistema.</i></p>
                                <div class="formulario" id="formulario">
                                    <fieldset id="expediente-fieldset" disabled>

                                        <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                            <s:if test="tDocExpediente.tdeUser == 'AL'">
                                                <s:if test="id.edeTdoc == 10 ">
                                                    <h4><s:property value="tDocExpediente.tdeDes" /></h4>
                                                    <table id="solicitudes-table" class="table table-bordered dataTable">
                                                        <tbody>
                                                            <tr>
                                                                <td width="50%"><p>Descargue la caratula de su expediente y subala firmada</p>
                                                                    <button id="caratula-button" title="<s:text name="label.button.download"/>" type="button" class="btn btn-light" >
                                                                        <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <label class="form-label">Subir Expediente de Grado/Título (firmado)</label>
                                                                    <s:if test="edeFile == null">
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><s:property value="edeFile" /></a>
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </s:if>
                                                <s:if test="id.edeTdoc == 30">
                                                    <h4><s:property value="tDocExpediente.tdeDes" /></h4>
                                                    <table id="solicitudes-table" class="table table-bordered dataTable">
                                                        <tbody>
                                                            <tr>
                                                                <td width="50%"><p>Obtenga su certificado de nacimiento (Para Todo Tramite) de la pagina https://www.registrocivil.cl/ y adjunto al formulario</p>
                                                                    <a href="https://www.registrocivil.cl/" target="_blank" class="btn btn-light" >
                                                                        <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="Registro Civil"/></span>
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <label class="form-label">Subir Certificado de Nacimiento</label>
                                                                    <s:if test="edeFile == null">
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><s:property value="edeFile" /></a>
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true" />
                                                                   </s:else>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </s:if>
                                                <s:if test="id.edeTdoc == 100">
                                                    <h4><s:property value="tDocExpediente.tdeDes" /></h4>
                                                    <table id="solicitudes-table" class="table table-bordered dataTable">
                                                        <tbody>
                                                            <tr>
                                                                <td width="50%"><p>Descargué el siguiente formulario y completelo</p>
                                                                    <a href="https://fae.usach.cl/" target="_blank" class="btn btn-light" >
                                                                        <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <label class="form-label">Subir Solcitud de Genero</label>
                                                                    <s:if test="edeFile == null">
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input"  />
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><s:property value="edeFile" /></a>
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true" />
                                                                   </s:else>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </s:if>
                                                <s:if test="id.edeTdoc == 110">
                                                    <h4><s:property value="tDocExpediente.tdeDes" /></h4>
                                                    <table id="solicitudes-table" class="table table-bordered dataTable">
                                                        <tbody>
                                                            <tr>
                                                                <td width="50%"><p>Adjuntar el documento con el detalle completo de la transferencia</p>
                                                                    <button id="pago-arancel-button" title="<s:text name="label.button.download"/>" type="button" class="btn btn-light" >
                                                                        <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <label class="form-label">Subir comprobante de pago</label>
                                                                    <s:if test="edeFile == null">
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><s:property value="edeFile" /></a>
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true" />
                                                                   </s:else>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </s:if>
                                                <s:if test="id.edeTdoc == 120">
                                                    <h4><s:property value="tDocExpediente.tdeDes" /></h4>
                                                    <table id="solicitudes-table" class="table table-bordered dataTable">
                                                        <tbody>
                                                            <tr>
                                                                <td width="50%"><p>Adjunte su certificado de título</p>
                                                                    <button id="pago-arancel-button" title="<s:text name="label.button.download"/>" type="button" class="btn btn-light" >
                                                                        <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <label class="form-label">Subir certificado de título</label>
                                                                    <s:if test="edeFile == null">
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><s:property value="edeFile" /></a>
                                                                        <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true" />
                                                                   </s:else>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </s:if>
                                            </s:if>
                                        </s:iterator>

                                    </fieldset>
                                </div>
                            </div>
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </form>


                        <form id="solicitud-form" action="#" method="post" class="form-horizontal" enctype="multipart/form-data">
                            <!--div class="form-group">
                                <label for="causa" class="col-sm-1 control-label">Causa</label>
                                <div class="col-sm-8">
                                    <textarea name="causa" id="causa" rows="3" maxlength="1500" class="form-control" ></textarea>
                                </div>
                            </div-->

                            <div id="hidden-input-div">
                                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                            </div>
                        </form>

                        <div class="buttons-div">
                            <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                                &nbsp; <span class="hidden-xs"><s:text name="label.enviar"/></span>
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <script>
            $('#btnSiguiente').on('click', function () {
                $('#paso1').hide();
                $('#paso2').fadeIn();
            });
        </script>

    </body>
</html>

