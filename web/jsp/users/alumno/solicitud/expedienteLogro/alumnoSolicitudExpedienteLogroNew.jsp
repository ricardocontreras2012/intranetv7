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
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/5.3.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/expedienteLogro/alumnoSolicitudExpedienteLogroNew-1.0.2.js"></script>
        <style>
            h4 { color: #00a499 !important;}
            .paso {
                display: none;
            }
            .paso.activo {
                display: block;
            }

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
            .bebas-neue-regular {
                font-family: "Bebas Neue", sans-serif;
                font-weight: 400;
                font-style: normal;
              }
        </style>
    </head>
    <body class="bg-light">

        <div class="container mt-3 mb-5">

            <div class="paso activo"  data-step="1">
                <div class="card shadow rounded-4">
                    <div class="card-body px-5 py-4">
                        <h2 class="text-center bebas-neue-regular" style="color: #00a499;">🎓 ¡Felicidades!<br/></h2>
                        <h3 class="text-center mb-4" style="color: #00a499;">Pasos a seguir para solicitar <s:property value="#session.genericSession.getWorkSession(key).expedienteLogro.planLogro.plalNomLogro" />.</h3>

                        <hr>

                        <div class="row justify-content-center">
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card text-center h-100">
                                    <div class="card-body">
                                        <button type="button" class="btn btn-light" style="height:100%" data-bs-toggle="modal" data-bs-target="#paso1Modal">
                                            <h3>Paso 1.<br/>Revisión datos personales</h3>
                                            <img src="/intranetv7/images/local/users/alumno/solicitud/revision.png" style="width: 130px; max-width:100%;">
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card text-center h-100">
                                    <div class="card-body">
                                        <button type="button" class="btn btn-light" style="height:100%" data-bs-toggle="modal" data-bs-target="#paso2Modal">
                                            <h3>Paso 2.<br/>Descarga y completa los formularios requeridos</h3>
                                            <img src="/intranetv7/images/local/users/alumno/solicitud/descarga.png" style="width: 130px; max-width:100%;">
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card text-center h-100">
                                    <div class="card-body">
                                        <button type="button" class="btn btn-light" style="height:100%" data-bs-toggle="modal" data-bs-target="#paso3Modal">
                                            <h3>Paso 3.<br/>Subir Documentación</h3>
                                            <img src="/intranetv7/images/local/users/alumno/solicitud/subida.png" style="width: 130px; max-width:100%;">
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6 mb-4">
                                <div class="card text-center h-100">
                                    <div class="card-body">
                                        <button type="button" class="btn btn-light" style="height:100%" data-bs-toggle="modal" data-bs-target="#paso4Modal">
                                            <h3>Paso 4.<br/>Revisión y envío de Solicitud</h3>
                                            <img src="/intranetv7/images/local/users/alumno/solicitud/envio.png" style="width: 130px; max-width:100%;">
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="card-footer">
                        <div class="text-center my-2">
                            <button class="siguiente btn btn-primary btn-lg">Iniciar</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="paso" data-step="2">
                <div class="d-flex justify-content-between mb-2">
                    <div class="step completed" id="step-1">
                        <div class="circle">1</div>
                    </div>
                    <div class="step" id="step-2">
                        <div class="circle">2</div>
                    </div>
                    <div class="step" id="step-3">
                        <div class="circle">3</div>
                    </div>
                    <div class="step" id="step-4">
                        <div class="circle">4</div>
                    </div>
                </div>

                <div class="card shadow rounded-4">
                    <div class="card-body px-5 py-4">
                        <s:form id="personales-form" action="#" class="form-horizontal">
                            <div class="row">
                                <div class="col-12">
                                    <h4 class="mb-4">Paso 1. Revisión datos personales</h4>
                                    <p>Verifica tus datos personales y actualízalos si es necesario.</p>

                                    <div class="mb-1 mb-md-3 row">
                                        <label for="name" class="col-12 col-md-4 col-form-label"><s:text name="label.name"/></label>
                                        <div class="col-12 col-md-8">
                                            <input type="text" class="form-control" id="nombre" name="nombre"   readonly disabled
                                                   value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluNombre"/> <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluPaterno"/> <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluMaterno"/>" />
                                        </div>
                                    </div>
                                    <div class="mb-1 mb-md-3 row">
                                        <label for="nacimiento" class="col-12 col-md-4 col-form-label"><s:text name="label.fecha.nacimiento"/></label>
                                        <div class="col-12 col-md-8">
                                            <input type="text" class="form-control" id="nacimiento" name="nacimiento" readonly disabled
                                                   value="<s:date name="#session.genericSession.getWorkSession(key).aluCar.alumno.aluFechaNac"
                                                           format="dd/MM/yyyy"/>" />
                                        </div>
                                    </div>
                                    <div class="mb-1 mb-md-3 row">
                                        <label for="estadoCivil" class="col-12 col-md-4 col-form-label"><s:text name="label.estado.civil"/></label>
                                        <div class="col-12 col-md-8">
                                            <s:select id="estadoCivil"
                                                      name="estadoCivil"
                                                      value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEstCiv"
                                                      list="#session.genericSession.getListaEstadoCivil()"
                                                      listKey="ecivCod"
                                                      listValue="ecivDes"
                                                      cssClass="form-select"/>
                                        </div>
                                    </div>
                                    <div class="mb-1 mb-md-3 row">
                                        <label for="nacimiento" class="col-12 col-md-4 col-form-label"><s:text name="label.direccion"/></label>
                                        <div class="col-12 col-md-8">
                                            <input type="text" class="form-control" id="direccion" name="direccion" maxlength="120"
                                                   value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluDirecAlu"/>"/>
                                        </div>
                                    </div>
                                    <div class="mb-1 mb-md-3 row">
                                        <label for="region" class="col-12 col-md-4 col-form-label"><s:text name="label.region"/></label>
                                        <div class="col-12 col-md-8">
                                            <s:select id="region"
                                                      name="region"
                                                      headerKey=""
                                                      headerValue="Seleccione Región"
                                                      list="#session.genericSession.getListaRegion()"
                                                      listKey="regCod"
                                                      listValue="regNom"
                                                      cssClass="form-select"/>
                                        </div>
                                    </div>
                                    <div class="mb-1 mb-md-3 row">
                                        <label for="comunas" class="col-12 col-md-4 col-form-label"><s:text name="label.comuna"/></label>
                                        <div class="col-12 col-sm-8" id="comunas">
                                            <s:if test="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod != null">
                                                <s:action name="CommonComunaGetComunas" executeResult="true">
                                                    <s:param name="region"><s:property
                                                            value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod"/></s:param>
                                                    <s:param name="key"><s:property value="key"/></s:param>
                                                </s:action>
                                            </s:if>
                                        </div>
                                    </div>
                                    <div class="mb-1 mb-md-3 row">
                                        <label for="fono" class="col-12 col-md-4 col-form-label"><s:text name="label.telefono"/></label>
                                        <div class="col-12 col-md-8">
                                            <input type="text" class="form-control" id="fono" name="fono" maxlength="57"
                                                   value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluFonoAlu"/>"/>
                                        </div>
                                    </div>
                                    <div class="mb-1 mb-md-3 row">
                                        <label for="email" class="col-12 col-md-4 col-form-label"><s:text name="label.email"/></label>
                                        <div class="col-12 col-md-8">
                                            <input type="text" class="form-control" id="email" name="email" maxlength="57"
                                                   value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmail"/>"/>
                                        </div>
                                    </div>
                                    <input type="hidden" id="emailLaboral" name="emailLaboral" maxlength="57" value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmailLaboral"/>"/>
                                    <div class="mb-1 mb-md-3 row">
                                        <label for="emailInstitucional" class="col-12 col-md-4 col-form-label"><s:text name="label.email.institucional"/></label>
                                        <div class="col-12 col-md-8">
                                            <input type="text" class="form-control" id="emailInstitucional" name="emailInstitucional" maxlength="57" readonly disabled
                                                   value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmailUsach"/>"/>
                                        </div>
                                    </div>

                                    <div class="alert alert-warning" role="alert" style="color: #37332a">
                                        Si modificó su nombre y/o apellidos, envíe su Certificado de Nacimiento a: ivan.jorquera@usach.cl
                                    </div>

                                    <div class="alert alert-secondary " role="alert">
                                        <div class="form-check my-1 bold">
                                            <input class="form-check-input" type="checkbox" id="aceptarRevision">
                                            <label class="form-check-label" for="aceptarRevision">
                                                <strong>Confirmo que mis datos personas están correctos.</strong>
                                            </label>
                                        </div>
                                    </div>

                                    <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                        <div class="errorBox">
                                            <s:actionerror/>
                                            <s:actionmessage/>
                                            <s:fielderror/>
                                        </div>
                                    </s:if>


                                </div>

                                <div id="hidden-input-div">
                                    <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                                    <input type="hidden" id="comunaDummy" name="comunaDummy"
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.comCod"/>"/>
                                    <input type="hidden" id="regionDummy" name="regionDummy"
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod"/>"/>
                                </div>

                            </div>
                        </s:form>
                        <button id="save-personales-button" title="Grabar" type="button" class="btn btn-light" disabled><span class="fa fa-save" aria-hidden="true" disabled></span>
                            &nbsp; <span class="hidden-xs">Grabar Datos Personales</span>
                        </button>



                    </div>
                    <div class="card-footer">
                        <div class="text-end my-2">
                            <button class="volver btn btn-primary" id="btn-back-step-2">Volver</button>
                            <button class="siguiente btn btn-primary" id="btn-next-step-2" disabled>Continuar</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="paso" data-step="3">
                <div class="d-flex justify-content-between mb-2">
                    <div class="step completed" id="step-1">
                        <div class="circle">1</div>
                    </div>
                    <div class="step completed" id="step-2">
                        <div class="circle">2</div>
                    </div>
                    <div class="step" id="step-3">
                        <div class="circle">3</div>
                    </div>
                    <div class="step" id="step-4">
                        <div class="circle">4</div>
                    </div>
                </div>

                <div class="card shadow rounded-4">
                    <div class="card-body px-5 py-4">
                        <form id="get-docs-form" action="#">
                            <h4 class="mb-4">Paso 2. Descarga y completa los formularios requeridos</h4>
                            <p>Recopilar los siguientes documentos, completarlos y/o firmarlos según corresponda.</p>

                            <div class="mb-3">
                                <div class="row">
                                    <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                        <s:if test="tDocExpediente.tdeUser == 'AL'">
                                            <div class="col col-md-6 mb-3">
                                                <div class="card h-100">
                                                    <div class="card-body">
                                                        <h5 class="card-title"><s:property value="tDocExpediente.tdeDes" /></h5>
                                                        <p class="card-text"><s:property value="tDocExpediente.tdeTexto" /></p>
                                                        <s:if test="id.edeTdoc == 10 ">
                                                            <button id="caratula-button" title="<s:text name="label.button.download"/>" type="button" class="btn btn-light" >
                                                                <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                            </button>
                                                        </s:if>
                                                        <s:if test="id.edeTdoc == 30">
                                                            <a href="https://www.registrocivil.cl/" target="_blank" class="btn btn-light" >
                                                                <span class="fa fa-globe"></span>&nbsp; <span class="hidden-xs"><s:text name="Registro Civil"/></span>
                                                            </a>
                                                        </s:if>
                                                        <s:if test="id.edeTdoc == 100">
                                                            <a href="https://fae.usach.cl/fae/docs/intranet/solicitud-de-genero.pdf" target="_blank" class="btn btn-light" >
                                                                <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                            </a>
                                                            <button id="genero-button" title="<s:text name="label.button.download"/>" type="button" class="btn btn-light" >
                                                                <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                            </button>
                                                        </s:if>
                                                        <s:if test="id.edeTdoc == 110">
                                                            <button id="pago-arancel-button" title="<s:text name="label.button.download"/>" type="button" class="btn btn-light" >
                                                                <span class="fa fa-file-pdf-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.download"/></span>
                                                            </button>
                                                        </s:if>
                                                        <s:if test="id.edeTdoc == 120">
                                                            <a href="mailto:ingresoderequerimientosefusach@usach.cl" target="_blank" class="btn btn-light" >
                                                                <span class="fa fa-envelope-o"></span>&nbsp; <span class="hidden-xs">Escribir</span>
                                                            </a>
                                                        </s:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </s:if>
                                    </s:iterator>
                                </div>
                            </div>
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </form>
                        <div class="alert alert-warning " role="alert" title="color: #37332a">Los documentos le serán requeridos en el siguiente paso.</div>
                    </div>
                    <div class="card-footer">
                        <div class="text-end my-2">
                            <button class="volver btn btn-primary">Volver</button>
                            <button class="siguiente btn btn-primary" id="btn-next-step-3">Continuar</button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="paso" data-step="4">
                <div class="d-flex justify-content-between mb-2">
                    <div class="step completed" id="step-1">
                        <div class="circle">1</div>
                    </div>
                    <div class="step completed" id="step-2">
                        <div class="circle">2</div>
                    </div>
                    <div class="step completed" id="step-3">
                        <div class="circle">3</div>
                    </div>
                    <div class="step" id="step-4">
                        <div class="circle">4</div>
                    </div>
                </div>

                <div class="card shadow rounded-4">
                    <div class="card-body px-5 py-4">
                        <form id="expediente-form" action="#">
                            <h4 class="mb-4">Paso 3. Subir Documentación</h4>
                            <p>Sube todos los documentos solicitados en en el paso anterior en formato PDF.</p>
                            <div class="mb-3">
                                <div class="formulario row" id="formulario">
                                    <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                        <s:if test="tDocExpediente.tdeUser == 'AL'">
                                            <div class="col col-md-6 mb-3">
                                                <div class="card h-100">
                                                    <div class="card-body">
                                                        <h5 class="card-title"><s:property value="tDocExpediente.tdeDes" /></h5>
                                                        <s:if test="id.edeTdoc == 10 ">
                                                            <label class="form-label">Subir Expediente de Grado/Título (firmado)</label>
                                                            <s:if test="edeFile == null">
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true"/>
                                                                <div class="mt-2 alert alert-success">
                                                                    Posee una versión subida previamente.
                                                                    <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><span class="fa fa-file-pdf-o"></span></a>
                                                                </div>
                                                            </s:else>
                                                        </s:if>
                                                        <s:if test="id.edeTdoc == 30">
                                                            <label class="form-label">Subir Certificado de Nacimiento</label>
                                                            <s:if test="edeFile == null">
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true"/>
                                                                <div class="mt-2 alert alert-success">
                                                                    Posee una versión subida previamente.
                                                                    <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><span class="fa fa-file-pdf-o"></span></a>
                                                                </div>
                                                            </s:else>

                                                        </s:if>
                                                        <s:if test="id.edeTdoc == 100">
                                                            <label class="form-label">Subir Solcitud de Genero</label>
                                                            <s:if test="edeFile == null">
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input"  />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true"/>
                                                                <div class="mt-2 alert alert-success">
                                                                    Posee una versión subida previamente.
                                                                    <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><span class="fa fa-file-pdf-o"></span></a>
                                                                </div>
                                                            </s:else>

                                                        </s:if>
                                                        <s:if test="id.edeTdoc == 110">
                                                            <label class="form-label">Subir comprobante de pago</label>
                                                            <s:if test="edeFile == null">
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true"/>
                                                                <div class="mt-2 alert alert-success">
                                                                    Posee una versión subida previamente.
                                                                    <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><span class="fa fa-file-pdf-o"></span></a>
                                                                </div>
                                                            </s:else>

                                                        </s:if>
                                                        <s:if test="id.edeTdoc == 120">
                                                            <label class="form-label">Subir certificado de título</label>
                                                            <s:if test="edeFile == null">
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" />
                                                            </s:if>
                                                            <s:else>
                                                                <input type="file" name="upload-<s:property value="tDocExpediente.tdeCod" />" id="upload-<s:property value="tDocExpediente.tdeCod" />" data-upload-id="<s:property value="tDocExpediente.tdeCod" />" class="form-control upload-input" data-upload-success="true"/>
                                                                <div class="mt-2 alert alert-success">
                                                                    Posee una versión subida previamente.
                                                                    <a class="link" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><span class="fa fa-file-pdf-o"></span></a>
                                                                </div>
                                                            </s:else>
                                                        </s:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </s:if>
                                    </s:iterator>
                                </div>
                            </div>
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </form>

                        <form id="solicitud-form" action="#" method="post" class="form-horizontal" enctype="multipart/form-data">
                            <div id="hidden-input-div">
                                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                            </div>
                        </form>

                        <!--div class="buttons-div">
                            <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                                &nbsp; <span class="hidden-xs">Enviar Solicitud</span>
                            </button>
                        </div-->
                    </div>
                    <div class="card-footer">
                        <div class="text-end my-2">
                            <button class="volver btn btn-primary">Volver</button>
                            <button class="siguiente btn btn-primary" id="btn-next-step-4" disabled>Continuar</button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="paso" data-step="5">
                <div class="d-flex justify-content-between mb-2">
                    <div class="step completed" id="step-1">
                        <div class="circle">1</div>
                    </div>
                    <div class="step completed" id="step-2">
                        <div class="circle">2</div>
                    </div>
                    <div class="step completed" id="step-3">
                        <div class="circle">3</div>
                    </div>
                    <div class="step completed" id="step-4">
                        <div class="circle">4</div>
                    </div>
                </div>

                <div class="card shadow rounded-4">
                    <div class="card-body px-5 py-4">
                        <form id="expediente-form" action="#">
                            <h4 class="mb-4">Paso 4. Envío de Solicitud</h4>
                            <p>Revise la documentación que adjuntó, sí está correcta, finalmente envíe su solicitud.</p>
                            <div class="mb-3">
                                <div class="formulario row" id="formulario">
                                    <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                        <s:if test="tDocExpediente.tdeUser == 'AL'">
                                            <div class="col col-md-6 mb-3">
                                                <div class="card h-100">
                                                    <div class="card-body">
                                                        <h5 class="card-title"><s:property value="tDocExpediente.tdeDes" /></h5>
                                                        <div class="mt-2 alert alert-success">
                                                            <a class="text-success" style="text-decoration: none;" href="AlumnoSolicitudExpedienteDownloadFile?tdoc=<s:property value="id.edeTdoc"/>&amp;key=<s:property value="key"/>"><span class="fa fa-file-pdf-o"></span> Revisar <s:property value="tDocExpediente.tdeDes" /></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </s:if>
                                    </s:iterator>
                                </div>
                            </div>
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </form>

                        <form id="solicitud-form" action="#" method="post" class="form-horizontal" enctype="multipart/form-data">
                            <div id="hidden-input-div">
                                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                            </div>
                        </form>

                        <div class="buttons-div">
                            <button id="save-button" title="Grabar" type="button" class="btn btn-primary btn-lg" ><span class="fa fa-save" aria-hidden="true"></span>
                                &nbsp; <span class="hidden-xs">Enviar Solicitud</span>
                            </button>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="text-end my-2">
                            <button class="volver btn btn-primary">Volver</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Paso 1-->
        <div class="modal fade" id="paso1Modal" tabindex="-1" aria-labelledby="paso1ModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="paso1ModalLabel">Revisión datos personales</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Verifica tus datos personales y actualízalos si es necesario. Si cambiaste tu nombre o apellidos ante el Registro Civil, adjunta el certificado de nacimiento actualizado.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Paso 2-->
        <div class="modal fade" id="paso2Modal" tabindex="-1" aria-labelledby="paso2ModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="paso2ModalLabel">Descarga y completa los documentos requeridos</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Recopilar los siguientes documentos, completarlos y firmarlos según corresponda.
                        <ul>
                            <s:iterator value="#session.genericSession.getWorkSession(key).estadoDocExpList" status="row">
                                <s:if test="tDocExpediente.tdeUser == 'AL'">
                                    <li><s:property value="tDocExpediente.tdeDes" /></li>
                                    </s:if>
                                </s:iterator>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Paso 3-->
        <div class="modal fade" id="paso3Modal" tabindex="-1" aria-labelledby="paso3ModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="paso3ModalLabel">Subir Documentación</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Sube todos los documentos solicitados en en el paso anterior en formato PDF.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Paso 4-->
        <div class="modal fade" id="paso4Modal" tabindex="-1" aria-labelledby="paso4ModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="paso4ModalLabel">Revisión y envío de solicitud</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Revise la documentación que adjuntó, sí está correcta, finalmente puede enviar su solicitud.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>

