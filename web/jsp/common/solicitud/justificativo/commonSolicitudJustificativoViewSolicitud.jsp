<%-- 
    Document   : commonSolicitudJustificativoViewSolicitud
    Created on : 22-05-2024, 16:11:19
    Author     : Usach
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Solicitud de Justificativo PEP</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/5.1.3/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/5.1.3/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/common/solicitud/justificativo/commonSolicitudJustificativoViewSolicitud-3.0.1.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <s:if test="#session.genericSession.getWorkSession(key).solicitud.solResolucion == null">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <s:if test="#session.genericSession.userType in {\"SM\"}">
                                <div class="btn-group">
                                    <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                        <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                                    </button>
                                </div>
                            </s:if>
                        </div>
                    </div>
                </div>
            </s:if>

            <form id="solicitud-form" action="#" method="post" accept-charset="UTF-8">
                <div class="row">
                    <div class="col-12">
                        <table id="solicitud-table" class="table table-striped table-bordered">
                            <thead>
                            <th style="width: 50%">Solicitud de <s:property value="#session.genericSession.getWorkSession(key).solicitud.solSolicita.split(':')[0].trim()" /></th>                
                            </thead>
                            <tbody>
                                <s:iterator value="#session.genericSession.getWorkSession(key).justificativoList" status="row">
                                    <tr>
                                        <td style="width: 50%"><s:property value="curso.getNombreFull()"/></td>
                                        <s:if test="#session.genericSession.getWorkSession(key).solicitud.solResolucion != null">
                                            <td style="width: 5%">
                                                <s:text name="label.estado.%{soljEstado}" />
                                            </td>
                                            <td style="width: 40%"><s:property value="soljRespuesta"/></td>
                                        </s:if>
                                        <s:else>
                                            <td style="width: 10%"><select id="sit_<s:property value="#row.count -1"/>" name="sit_<s:property value="#row.count -1"/>" class="form-control"><option value=" "></option><option value="A">Aprobada</option><option value="R">Rechazada</option></select></td>
                                            <td style="width: 40%"><input name="obs_<s:property value="#row.count -1"/>" id="obs_<s:property value="#row.count -1"/>" class="form-control" /></td>
                                        </s:else>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div id="hidden-input-div" style="display:none">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>                
                </div>

                <div class="mb-3 row">
                    <label for="fecha-inicio" class="col-12 col-md-2 col-xxl-1 col-form-label">Fecha Inicio</label>
                    <div class="col-auto">
                        <input type="text" id="fecha-inicio" class="form-control" readonly value="<s:date name="#session.genericSession.getWorkSession(key).solicitud.solFechaInicio" format="dd/MM/yyyy"/>">
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="fecha-termino" class="col-12 col-md-2 col-xxl-1 col-form-label">Fecha Término</label>
                    <div class="col-auto">
                        <input type="text" id="fecha-termino" class="form-control" readonly value="<s:date name="#session.genericSession.getWorkSession(key).solicitud.solFechaTermino" format="dd/MM/yyyy"/>">
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="motivo" class="col-12 col-md-2 col-xxl-1 col-form-label">Motivo</label>
                    <div class="col-sm-10">
                        <textarea id="motivo" class="form-control" readonly><s:property value="#session.genericSession.getWorkSession(key).solicitud.solMotivo"/></textarea>
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col-12">
                        <table class="table table-striped">
                            <tr><th colspan="2">Archivos</th></tr>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).solicitud.solicitudAttachList" status="row">
                                <tr>
                                    <td style="width:28px"><span class="fa fa-paperclip"></span>&nbsp;</td>
                                    <td><a class="link" href="CommonSolicitudDownLoadFile?file=<s:property value="#row.count -1"/>&key=<s:property value="key"/>"><s:property
                                                value="solaAttachFile"/></a></td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                </div>
            </form> 

            <div class="row">
                <table style="width: 40%">
                    <thead>
                        <tr>
                            <th scope="col" style="width:30%"><s:text name="label.estado"/></th>
                            <th scope="col" style="width:40%"><s:text name="label.resolucion"/></th>
                            <th scope="col" style="width:30%"><s:text name="label.date"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).logSolicitudList" status="row">
                            <tr style="height: 14px">
                                <td><s:property value="estadoSolicitud.esolDes"/></td>
                                <td><s:property value="getResolucion()"/></td>
                                <td><s:property value="logFecha"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>

