<%--
    Document   :verificacionShowValidAlumnoEnPractica
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
        <title>Aviso: Certificado de Alumno en Práctica es Válido</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-verificacion-certificado.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/verificacionCertificado/verificacionShowValid-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <form id="certificado-form" action="#">            
            <table
                   style="width: 470px; margin-top: -5px; margin-left: 5px; background-color: white; text-transform: uppercase">
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
                    <td>OBS
                    </td>
                    <td><s:text name="label.para.ser.prentado"/> <s:property
                            value="#session.verificacionCertificadoSession.getLogCertificacion().lcertObs"/>
                    </td>
                </tr>
                <tr>
                    <td><s:text name="label.date.emision"/>
                    </td>
                    <td><s:date name="#session.verificacionCertificadoSession.getLogCertificacion().lcertFecha"
                            format="dd/MM/yyyy"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <div class="buttons-div">
                            <button id="back-button" name="back-button" class="positive" style="float: none">
                                <img src="/intranetv7/images/local/icon/backward.png" height="16" width="16" alt="back" title="Volver"/>
                                <s:text name="label.button.back"/>
                            </button>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>