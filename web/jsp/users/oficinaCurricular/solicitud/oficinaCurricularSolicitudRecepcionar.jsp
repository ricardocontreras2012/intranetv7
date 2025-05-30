<%--
    Document   :oficinaCurricularSolicitudRecepcionar
    Created on : 30-09-2010, 07:40:14 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Recepción Solicitud</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-message-bar.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript">
            "use strict";

            function uploadDocumento() {
                if ($("#documentos-form").validate().form() === true) {
                    $("#documentos-form").attr("enctype", "multipart/form-data");
                    $("#documentos-form").attr("action", "OficinaCurricularSolicitudAddDocumento");
                    $("#documentos-form").submit();
                }
            }

            function deleteDocumentos() {
                if (anyChecked("solicitud-form") === true) {
                    $("#confirmacion").dialog("open");
                }
                else {
                    $("#msg-div").dialog("open");
                }
                return false;
            }

            function downLoadDocumento(docto) {
                $("#documento").val(docto);
                $("#solicitud-form").attr("action", "CommonSolicitudDownLoadDocumento");
                $("#solicitud-form").attr("target", "_self");
                $("#solicitud-form").submit();
            }

            $(document).ready(function () {


                //Handlers
                $("#upload-button").click(uploadDocumento);
                $("#delete-button").click(deleteDocumentos);
                $("a").click(function () {
                    var fieldName = $(this).attr("id");
                    downLoadDocumento(fieldName.substr(fieldName.indexOf("_") + 1));
                });

                //
                jQuery("textarea").each(function () {
                    $(this).val(jQuery.trim($(this).val()));
                });

                $.validator.addMethod("selectOption",
                        function (value, element) {
                            return this.optional(element) || ((value !== '0'));
                        }
                , "<s:text name="label.tipo.documento"/>");

                        $("#documentos-form").validate({
                            rules: {
                                tipo: {required: true, selectOption: true},
                                upload: {required: true},
                                caption: {required: true, maxlength: 500}
                            },
                            messages: {
                                tipo: {required: jQuery.validator.messages.required },
                                upload: {required: jQuery.validator.messages.required},
                                caption: {required: jQuery.validator.messages.required, maxlength: jQuery.validator.messages.maxlength(500)}
                            }
                        });

                        $("#confirmacion").dialog({
                            autoOpen: false, modal: true,
                            resizable: false,
                            width: 400,
                            buttons: {
                                "NO": function () {
                                    $(this).dialog("close");
                                },
                                "SI": function () {
                                    $("#solicitud-form").attr("action", "OficinaCurricularSolicitudRemoveDocumento");
                                    $("#solicitud-form").attr("target", "_self");
                                    $("#solicitud-form").submit();
                                    $(this).dialog("close");
                                }
                            }
                        });

                        $("#msg-div").dialog({
                            autoOpen: false,
                            resizable: false,
                            modal: true,
                            width: 300,
                            buttons: {
                                "OK": function () {
                                    $(this).dialog("close");
                                }
                            }
                        });

                    })
        </script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.recepcion.solicitud"/>
        </div>
        <div id="central-div">
            <form id="solicitud-form" action="#" method="post">
                <table>
                    <tr>
                        <td><s:text name="label.rut"/></td>
                        <td><s:property
                                value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.aluRut"/>-<s:property
                                value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.aluDv"/></td>
                    </tr>
                    <tr>
                        <td><s:text name="label.name"/></td>
                        <td><s:property
                                value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.getNombre()"/></td>
                    </tr>
                    <tr>
                        <td><s:text name="label.tipo.solicitud"/></td>
                        <td><s:property value="#session.genericSession.getWorkSession(key).solicitud.tsolicitud.tsolDescrip"/></td>
                    </tr>
                    <tr>
                        <td><s:text name="label.folio"/></td>
                        <td><s:property value="#session.genericSession.getWorkSession(key).solicitud.solFolio"/></td>
                    </tr>
                    <tr>
                        <td><s:text name="label.date"/></td>
                        <td><s:date name="#session.genericSession.getWorkSession(key).solicitud.solFecSol"
                                format="dd/MM/yyyy"/></td>
                    </tr>
                    <tr>
                        <td><s:text name="label.motivo"/></td>
                        <td><s:property value="#session.genericSession.getWorkSession(key).solicitud.solMotivo"/></td>
                    </tr>
                </table>
                <div class="leftmenu" style="height: 20px; color: #A66600; margin-top: 5px">
                    <s:text name="label.title.documentos.adjuntos"/>
                </div>
                <!--BUTTONS-->
                <div class="buttons-div">
                    <button id="upload-button" name="upload-button" class="positive">
                        <img src="/intranetv7/images/local/icon/disk.png" height="16" width="16" alt="save" title="Grabar"/>
                        <s:text name="label.upload.file"/>
                    </button>
                    <button id="delete-button" name="delete-button" class="positive">
                        <img src="/intranetv7/images/local/icon/remove.png" height="16" width="16" alt="delete"
                             title="Eliminar Reportes Seleccionados"/>
                        <s:text name="label.button.delete"/>
                    </button>
                </div>
                <!--FIN BUTTONS-->
                <div style="height: 120px; overflow: auto">
                    <table>
                        <s:iterator value="#session.genericSession.getWorkSession(key).solicitudDocumentoList" status="row">
                            <tr>
                                <td><input type="checkbox" id="ck_<s:property value="#row.count -1"/>"
                                           name="ck_<s:property value="#row.count -1"/>"/></td>
                                <td>
                                    <a id="documento_<s:property value="#row.count -1"/>"><s:property value="sdocArchivo"/></a>
                                </td>
                                <td>
                                    <s:property value="sdocObs"/>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                    <input type="hidden" id="documento" name="documento" value="<s:property value="documento"/>"/>
                </div>
            </form>

            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                <div class="errorBox">
                    <s:actionerror/><s:actionmessage/><s:fielderror/>
                </div>
            </s:if>
            <form id="documentos-form" enctype="multipart/form-data" action="#" method="post">
                <s:text name="label.tipo.documento"/>
                <table>
                    <tr>
                        <td><s:text name="label.tipo.documento"/>
                        </td>
                        <td><s:select
                                name="tipoDocumento"
                                id="tipo"
                                headerKey="0"
                                headerValue="%{seleccioneTipoDocumentos}"
                                list="#session.genericSession.getWorkSession(key).tdocumentoSolicitudList"
                                listKey="tdsCod"
                                listValue="tdsDes"
                                cssClass="input"
                                />
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 20%"><s:text name="label.archivo.maximo"/>
                        </td>
                        <td style="width: 80%"><s:file id="upload" name="upload" size="95" cssStyle="height: 24px; width:80%"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="vertical-align:top; width: 20%"><s:text name="label.descripcion"/>
                        </td>
                        <td><s:textarea id="caption" name="caption" rows="3" cols="80" cssStyle="margin-left: 0; width:100%"/>
                        </td>
                    </tr>
                </table>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
            </form>
        </div>
        <div id="confirmacion" title="<s:text name="message.title.confirmacion"/>"><s:text
                name="confirmation.eliminacion.reporte"/></div>
        <div id="msg-div" title="<s:text name="message.title.msg"/>"><s:text name="alert.seleccione.reporte"/></div>
    </body>
</html>
