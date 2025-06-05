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
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/expedienteLogro/alumnoSolicitudExpedienteLogroNew-1.0.2.js"></script>
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="card shadow rounded-4">
                <div class="card-body p-5">
                    <div id="paso1">
                        <h2 class="text-center text-success mb-4">🎓 ¡Felicidades por alcanzar este gran logro!</h2>
                        <p class="text-center">A continuación, te indicamos los pasos a seguir para obtener tu Licenciatura y Título.</p>

                        <hr>

                        <h4 class="text-primary">Paso 1: Solicitud de Información</h4>
                        <ul>
                            <li><strong>Ingeniería Comercial:</strong> titulacion.ingeco@usach.cl</li>
                            <li><strong>Contador Público y Auditor:</strong> titulacion.cpa@usach.cl</li>
                            <li><strong>Administración Pública:</strong> titulacion.ap@usach.cl</li>
                            <li><strong>Diplomados y Postítulos:</strong> titulacion.educo@usach.cl</li>
                            <li><strong>Magíster y Doctorado:</strong> postgrados@usach.cl</li>
                        </ul>

                        <h4 class="text-primary mt-4">Paso 2: Entrega de Antecedentes</h4>
                        <ul>
                            <li>Certificado de nacimiento</li>
                            <li>Expediente de grado/título (firmado)</li>
                            <li>Solicitud de género (llenado y firmado)</li>
                            <li>Comprobante de pago de arancel</li>
                        </ul>

                        <h4 class="text-primary mt-4">Paso 3: Verificación</h4>
                        <p>El Registro Curricular FAE revisa y confirma la recepción de antecedentes.</p>
                        <p><strong>Plazo de respuesta:</strong> 20 días hábiles</p>

                        <h4 class="text-primary mt-4">Paso 4: Despacho de Antecedentes</h4>
                        <p>El Registro Curricular FAE envía los antecedentes a la Oficina de Títulos y Grados.</p>

                        <h4 class="text-primary mt-4">Paso 5: Certificado de Licenciatura y Título</h4>
                        <p>La Oficina de Títulos y Grados enviará el Certificado digital al correo institucional del solicitante.</p>
                        <p><strong>Plazo de respuesta:</strong> 37 días hábiles</p>

                        <div class="text-end mt-4">
                            <button class="btn btn-primary" id="btnSiguiente">Siguiente</button>
                        </div>
                    </div>

                    <div id="paso2" style="display:none;">
                        <form id="expediente-form" action="#">
                            <h4 class="mb-4 text-primary">Documentos para completar el proceso</h4>

                            <div class="mb-3">
                                <p>Puedes descargar los siguientes documentos, completarlos y subirlos firmados junto con otros antecedentes requeridos.</p>
                                <button id="caratula-button" title="Exportar" type="button" class="btn btn-light" >
                                    <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                                </button>
                                <button id="pago-arancel-button" title="Exportar" type="button" class="btn btn-light" >
                                    <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                                </button>
                                
                                <a href="#" class="btn btn-outline-success me-2">📄 Descargar Solicitud de Género</a>
                                <a href="#" id="caratula-button" class="btn btn-outline-success">📄 Descargar Caratula de Grado/Título</a>
                                <a href="#" onclick="pago-arancel-button() class="btn btn-outline-success">📄 Descargar Solicitud de Pago</a>
                                <a class="link" href="AlumnoSolicitudExpedienteGeneraCaratula?action=AlumnoSolicitudExpedienteGeneraCaratula&key=<s:property value="key" />">Caratula</a>
                                <button id="caratula-button" title="Exportar" type="button" class="btn btn-light" onclick="caratula-button()">
                                    <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                                </button>
                            </div>



                            <div class="mb-3">
                                <label class="form-label">Subir Solicitud de Género (firmada)</label>
                                <input type="file" class="form-control" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Subir Expediente de Grado/Título (firmado)</label>
                                <input type="file" class="form-control" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Subir Certificado de Nacimiento</label>
                                <input type="file" class="form-control" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Subir Comprobante de Pago de Arancel</label>
                                <input type="file" class="form-control" required>
                            </div>

                            <div class="text-end">
                                <button type="submit" class="btn btn-success">Enviar Documentos</button>
                            </div>
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </form>
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

