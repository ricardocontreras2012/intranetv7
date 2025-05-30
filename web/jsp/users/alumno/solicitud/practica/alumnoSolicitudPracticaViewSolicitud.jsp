<%--
    Document   : alumnoSolicitudPracticaViewSolicitud
    Created on : 14-09-2010, 10:39:01 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Solicitud de Práctica</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/solicitud/practica/alumnoSolicitudPracticaViewSolicitud-3.0.4.js"></script>
    </head>
    <body class="inner-body">

        <%--s:if test="#session.genericSession.getWorkSession(key).practica.praEstado == \"A\""--%>
            <div class="container container-menu">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <div class="btn-group">
                                <button id="upload-button" title="Subir Documento" type="button" class="btn btn-light" ><span class="fa fa-upload" aria-hidden="true"></span>
                                    &nbsp; <span class="hidden-xs"><s:text name="label.upload.file"/></span>
                                </button>
                            </div>
                            <div class="btn-group">
                                <button id="print-presentacion-button" title="Imprimir Carta de Presentación" type="button" class="btn btn-light" >
                                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir.carta.presentacion"/></span>
                                </button>
                            </div>
                            <div class="btn-group">
                                <button id="print-autorizacion-button" title="Imprimir Carta de Autorización" type="button" class="btn btn-light" >
                                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir.carta.autorizacion"/></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <%--/s:if--%>

        <s:form id="solicitud-form" action="#" theme="bootstrap" enctype="multipart/form-data">
            <div class="title-div">
                DATOS DE LA ORGANIZACIÓN
            </div>
            <div class="data-tables-container">
                <table class="table">
                    <tr>
                        <td width="15%">
                            <s:text name="label.rut.empleador"/>
                        </td>
                        <td>
                            <div id="id-emp-div">
                                <s:property value="#session.genericSession.getWorkSession(key).practica.empleador.empNombre"/>
                            </div>
                            <div id="datos-emp-div">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Labor
                        </td>
                        <td>
                            <textarea name="labor" id="labor" rows="3" cols="120" readonly="readonly">
                                <s:property value="#session.genericSession.getWorkSession(key).practica.praLabor"/>
                            </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="region"><s:text name="label.region"/></label>
                        </td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.comuna.region.regNom"/>
                        </td>
                    </tr>
                    <tr>
                        <td><s:text name="label.comuna"/></td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.comuna.comNom"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Dirección
                        </td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.praDireccion"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Teléfono
                        </td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.praTelefonoEmp"/>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="title-div">
                DATOS DEL SUPERVISOR O CONTACTO EN LA ORGANIZACIÓN
            </div>
            <div class="data-tables-container">
                <table class="table">
                    <tr>
                        <td style="width: 15%">
                            Autoriza
                        </td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.autoriza.perFull"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Teléfono
                        </td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.praTelefonoAut"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            e-mail
                        </td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.praEmail"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            En su calidad de
                        </td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.praCalidadAut"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Fecha de Inicio
                        </td>
                        <td>
                            <s:date name="#session.genericSession.getWorkSession(key).practica.praFechaInicio" format="dd/MM/yyyy"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Fecha de Término
                        </td>
                        <td>
                            <s:date name="#session.genericSession.getWorkSession(key).practica.praFechaTermino" format="dd/MM/yyyy"/>
                        </td>
                    </tr>
                </table>
            </div>

            <div style="box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.1);margin: 10px;border: 1px solid #d7dee1;padding: 5px 0px;">
                <s:if test="#session.genericSession.getWorkSession(key).solicitud.solicitudAttachList.size() >0">
                    <table style="width: 98%">
                        <s:iterator value="#session.genericSession.getWorkSession(key).solicitud.solicitudAttachList" status="row">
                            <tr>
                                <td style="width:5%"><img src="/intranetv7/images/local/icon/attachment.png" alt="attach"/></td>
                                <td><a class="link" href="CommonSolicitudDownLoadFile?file=<s:property value="#row.count -1"/>&key=<s:property value="key"/>"><s:property
                                            value="solaAttachFile"/></a></td>
                                <td><s:property value="tdocumentoSolicitud.tdsDes"/></td>
                                <td>
                                    <button id="delete-button" title="Eliminar" type="button" class="btn btn-light"  onclick="deleteAttachSolicitud('<s:property value="key"/>', <s:property value="#row.count -1"/>);">
                                        <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                                    </button>
                                </td>

                            </tr>
                        </s:iterator>
                    </table>
                </s:if>
            </div>

            <div style="box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.1);margin: 10px;border: 1px solid #d7dee1;padding: 5px 0px;">
                <table cellpadding="10">
                    <tr>
                        <td width="10%">
                            Estado
                        </td>
                        <td width="90%">
                            <s:property value="#session.genericSession.getWorkSession(key).solicitud.estadoSolicitud.esolDes"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="10%">
                            Resolución
                        </td>
                        <td width="90%">
                            <s:property value="#session.genericSession.getWorkSession(key).solicitud.getResolucion()"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="10%">
                            Motivo
                        </td>
                        <td width="90%">
                            <s:property value="#session.genericSession.getWorkSession(key).solicitud.solRespuesta"/>
                        </td>
                    </tr>
                </table>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="rutEmp" name="rutEmp"/>
                <input type="hidden" id="rutAut" name="rutAut"/>
                <input type="hidden" id="comunaDummy" name="comunaDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.comCod"/>"/>
                <input type="hidden" id="regionDummy" name="regionDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod"/>"/>
            </div>
        </s:form>
    </body>
</html>

