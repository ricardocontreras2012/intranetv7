<%--
    Document   : profesorCalificacionActaConcepto
    Created on : 03-02-2010, 09:45:15 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Emisión de Acta con Calificación Conceptual</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/calificacion/profesorCalificacionActaConcepto-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.acta"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <s:if test="#session.genericSession.getWorkSession(key).puedeEmitir">
                            <div class="btn-group">
                                <button id="emitir-button" title="Emitir" type="button" class="btn btn-light" >
                                    <span class="fa fa-lock"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.emitir"/></span>
                                </button>
                            </div>

                        </s:if>
                        <s:else>
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
                        </s:else>
                    </div>
                </div>
            </div>
        </div>

        <!--END BUTTONS-->
        <form id="acta-form" action="#">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col"><s:text name="label.nro"/></th>
                        <th scope="col"><s:text name="label.rut"/></th>
                        <th scope="col"><s:text name="label.name"/></th>
                        <th scope="col" style="text-align:center"><s:text name="label.nota"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).nominaActa" status="row">
                        <tr>
                            <td style="text-align:right;"><s:property value="#row.count"/>&nbsp;&nbsp;</td>
                            <td style="text-align:right;"><s:property value="aluRut"/>-<s:property
                                    value="aluDv"/>&nbsp;&nbsp;</td>
                            <td><s:property value="aluPaterno"/> <s:property
                                    value="aluMaterno"/> <s:property value="aluNombre"/></td>
                            <td style="text-align:center">
                                <s:if test="acalEst==\"G\"">
                                    <select class="form-control" id="concepto_<s:property value="aluRut"/>" name="concepto_<s:property value="aluRut"/>">
                                        <option value="AD">AD</option>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="SC">SC</option>
                                    </select>
                                </s:if>
                                <s:else>
                                    <s:if test="acanConcep==\"AD\" || acanConcep==\"A\" || acanConcep==\"B\"">
                                        <span style="text-align:center; font-weight: bold;"
                                              class="aprobado"><s:property
                                                value="acanConcep"/></span>
                                        </s:if>
                                        <s:else>
                                            <s:if test="acanConcep==\"SC\"">
                                            <span style="text-align:center; font-weight: bold;"><s:property
                                                    value="acanConcep"/></span>
                                            </s:if>
                                            <s:else>
                                            <span style="text-align:center; font-weight: bold;"
                                                  class="reprobado"><s:property
                                                    value="acanConcep"/></span>
                                            </s:else>
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
                       value='attachment;filename="NOTAS_FINALES_<s:property value="#session.genericSession.getWorkSession(key).curso.nombreFull"/>.XLS"'/>
                <input type="hidden" id="format" name="format"/>
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
    </body>
</html>