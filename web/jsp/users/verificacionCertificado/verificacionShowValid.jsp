<%--
    Document   :verificacionShowValid
    Created on : 07-12-2009, 10:31:31 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Aviso: Certificado <s:property value="#session.verificacionCertificadoSession.getLogCertificacion().tcertificado.tceDes"/> es Válido</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/verificacionCertificado/verificacionShowValid-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/verificacionCertificado/verificacionCertificadoDownload-3.0.0.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row justify-content-center my-3">
                <div class="col-12 col-md-6 col-lg-4">
                    <form id="certificado-form" action="#">            
                        <table class="table table-striped">
                            <tr>
                                <td><s:text name="label.rut"/>
                                </td>
                                <td><s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().aluCar.Alumno.aluRut"/>-<s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().aluCar.alumno.aluDv"/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="label.name"/>
                                </td>
                                <td><s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().aluCar.alumno.aluPaterno"/>&nbsp;<s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().aluCar.alumno.aluMaterno"/>&nbsp;<s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().aluCar.alumno.aluNombre"/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="label.carrera"/>
                                </td>
                                <td><s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().aluCar.getNombreCarrera()"/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="label.ingreso"/>
                                </td>
                                <td><s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().aluCar.id.acaSemIng"/>/<s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().aluCar.id.acaAgnoIng"/>
                                </td>
                            </tr>
                            <tr>
                                <td>Certificado</td>
                                <td><s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().tcertificado.tceDes"/>
                                </td> 
                            </tr>                
                            <tr>
                                <td><s:text name="label.tramite"/></td>
                                <td><s:property
                                        value="#session.verificacionCertificadoSession.getLogCertificacion().tramite.traDescripcion"/></td>
                            </tr>
                            <tr>
                                <td>Obs.
                                </td>
                                <td><s:property value="#session.verificacionCertificadoSession.getLogCertificacion().lcertObs"/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="label.date.emision"/>
                                </td>
                                <td><s:date name="#session.verificacionCertificadoSession.getLogCertificacion().lcertFecha"
                                        format="dd/MM/yyyy"/>
                                </td>
                            </tr>
                            <tr style="background: #00a499;">
                                <td colspan="2" style="text-align: center">
                                    <s:if test ="#session.verificacionCertificadoSession.download">
                                        <button onClick="downLoadDocumento(); return false;" id="download-button" name="download-button" class="btn btn-light">
                                            <span class="fa fa-download" aria-hidden="true"></span>
                                            <s:text name="label.button.download"/>
                                        </button>

                                    </s:if>
                                    <s:else>
                                        &nbsp;
                                    </s:else>

                                    <button id="back-button" name="back-button" class="btn btn-light" style="float: none">
                                        <span class="fa fa-backward" aria-hidden="true"></span>
                                        <s:text name="label.button.back"/>
                                    </button>

                                    <!--button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                                        &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                                    </button-->                                    
                                </td>
                            </tr>

                        </table>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>