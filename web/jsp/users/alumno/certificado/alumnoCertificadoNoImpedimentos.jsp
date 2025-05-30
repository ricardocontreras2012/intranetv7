<%-- 
    Document   : alumnoCertificadoAlumnoRegular
    Created on : 05-12-2022, 13:29:31
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Certificación de No Impedimentos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/certificado/alumnoCertificadoNoImpedimentos-3.0.5.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            NO IMPEDIMENTOS->SELECCIONE TRÁMITE
        </div>

        <div>
            <button id="print-button" title="Print" type="button" class="btn btn-light" ><span class="fa fa-print" aria-hidden="true"></span>
                &nbsp; <span class="hidden-xs"><s:text name="label.button.emitir.certificado"/></span>
            </button>
        </div>

        <s:form id="certificacion-form" action="#" method="post" theme="bootstrap">
            <table>                
                <tr>
                    <td align="left">
                        <s:select id="tramite"
                                  name="tramite"
                                  headerKey=""
                                  headerValue="-- Seleccione Tramite --"
                                  list="#session.genericSession.getWorkSession(key).tramites"
                                  listKey="traTramite"
                                  listValue="traDescripcion"
                                  cssClass="input"/>
                    </td>
                </tr>
            </table>
            <p></p>
            <p>Indique aquí si solicita una glosa adicional al final del certificado. Al usar esta opción su certificado será enviado por correo una vez que sea revizada por Registro Curricular</p>
            <textarea name="obs" id="obs" rows="3" cols="110" style="margin-left: 0; width: 100%" class="form-control"></textarea>
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
            </div>
        </s:form>
        <div class="modal fade" id="confirm-dialog" role="dialog">
            <div class="modal-dialog modal-lg" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div  style="padding-bottom:25px">Desea emitir certificado con la siguiente información?</div>
                        <table class="table-striped">
                            <tr style="padding-top: 10px"><td style="padding-right: 25px;">Tipo de Certificado</td><td>No Impedimentos Académicos</td></tr>
                            <tr style="padding-top: 10px"><td style="padding-right: 25px;">Trámite</td><td><div id="tram-div"></div></td></tr>
                            <tr style="padding-top: 10px"><td style="padding-right: 25px;">Glosa adicional solicitada</td><td><div id="glosa-div"></div></td></tr>
                        </table> 
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="emitirCertificado();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

