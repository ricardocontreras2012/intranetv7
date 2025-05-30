<%--
    Document   : commoActaGetActa
    Created on : 21-05-2009, 09:13:17 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Acta de Calificaciones</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/acta/commonActaGetActa-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.acta"/> <s:property value="#session.genericSession.getWorkSession(key).nombreCarrera"/>
                <s:property value="#session.genericSession.getWorkSession(key).curso.nombreFull"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">                        
                        <div class="btn-group">
                            <button id="print-button" title="Imprimir" type="button" class="btn btn-light" >
                                <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="export-button" title="Exportar" type="button" class="btn btn-light" >
                                <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form id="acta-form" action="#" method="post">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col"><s:text name="label.nro"/></th>
                        <th scope="col"><s:text name="label.rut"/></th>
                        <th scope="col" class="w-75"><s:text name="label.name"/></th>
                        <th scope="col" style="text-align:center"><s:text name="label.nota"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).nominaActa" status="row">
                        <tr>
                            <td style="text-align:right;"><s:property value="#row.count"/>&nbsp;&nbsp;</td>
                            <td style="text-align:right;"><s:property
                                    value="aluRut"/>-<s:property
                                    value="aluDv"/>&nbsp;&nbsp;</td>
                            <td><s:property value="aluPaterno"/> <s:property
                                    value="aluMaterno"/> <s:property
                                    value="aluNombre"/></td>
                            <td style="text-align:center">
                                <s:if test="acalEst==\"G\"">
                                    <input class="form-control no-padding col-lg-6 col-sm-12" size="3"
                                           id="nota_<s:property value="aluRut"/>"
                                           name="nota_<s:property value="aluRut"/>" type="text"
                                           maxlength="3"
                                           value="<s:if test="acanFinal != null"><s:text name="format.calificacion"><s:param name="value" value="acanFinal"/></s:text></s:if>"/>

                                </s:if>
                                <s:else>
                                    <s:if test="acanFinal >= 4">
                                        <span style="text-align:center; font-weight: bold;"
                                              class="aprobado"><s:text
                                                name="format.calificacion"><s:param name="value"
                                                                                value="acanFinal"/></s:text></span>
                                        </s:if>
                                        <s:else>
                                        <span style="text-align:center; font-weight: bold;"
                                              class="reprobado"><s:text
                                                name="format.calificacion"><s:param name="value"
                                                                                value="acanFinal"/></s:text></span>
                                        </s:else>
                                    </s:else>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="contentDisposition" name="contentDisposition"
                       value='attachment;filename="ACTA_<s:property value="#session.genericSession.getWorkSession(key).curso.nombreFull"/>.pdf"'/>
                <input type="hidden" id="format" name="format" value="PDF">
                
            </div>
        </form>

        <div class="modal fade" id="confirmacion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="confirmation.emision.acta"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="emitir();">SI</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">NO</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="warning" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Advertencia</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Si registró en NOTAS PARCIALES el 100% de la calificaciones del curso, para emitir el acta debe ingresar en NOTAS PARCIALES.</p>
                    </div>

                </div>
            </div>
        </div>

    </body>
</html>