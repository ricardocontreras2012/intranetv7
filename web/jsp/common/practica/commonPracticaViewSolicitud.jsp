<%--
    Document   : commonPracticaVewSolicitud
    Created on : 14-09-2010, 10:39:01 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="session.GenericSession" %>
<%@ page import="session.WorkSession" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Solicitud de Práctica</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/practica/commonPracticaViewSolicitud-3.0.0.js"></script>
    </head>

    <body class="inner-body">

        <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
        </button>

        <div style="box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.1);margin: 10px;border: 1px solid #d7dee1;padding: 5px 0px;">
            <table class="table" cellpadding="10">
                <tr>
                    <td width="10%">
                        RUT Alumno
                    </td>
                    <td width="90%">
                        <s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.aluRut"/>-<s:property
                            value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.aluDv"/>
                    </td>
                </tr>
                <tr>
                    <td width="10%">
                        Nombre
                    </td>
                    <td width="90%">
                        <s:property
                            value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.getNombre()"/>
                    </td>
                </tr>
            </table>
        </div>

        <form id="solicitud-form" action="#" class="form-horizontal">
            <div class="title-div" style="margin-top: 15px; margin-left: 10px">
                DATOS DE LA ORGANIZACIÓN
            </div>
            <div style="box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.1);margin: 10px;border: 1px solid #d7dee1;padding: 5px 0px;">
                <table class="table" cellpadding="10">
                    <tr>
                        <td width="10%">
                            <s:text name="label.rut.empleador"/>
                        </td>
                        <td width="90%">
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
                            <textarea name="labor" id="labor" rows="3" cols="120">
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

            <div class="title-div"  style="margin-top: 15px; margin-left: 10px">
                DATOS DEL SUPERVISOR O CONTACTO EN LA ORGANIZACIÓN
            </div>

            <div style="box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.1);margin: 10px;border: 1px solid #d7dee1;padding: 5px 0px;">
                <table class="table" cellpadding="10">
                    <tr>
                        <td width="10%">
                            Autoriza
                        </td>
                        <td width="90%">
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
                            <s:property value="#session.genericSession.getWorkSession(key).practica.praFechaInicio"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Fecha de Término
                        </td>
                        <td>
                            <s:property value="#session.genericSession.getWorkSession(key).practica.praFechaTermino"/>
                        </td>
                    </tr>

                    <tr>
                        <td>Requisitos</td>
                        <s:if test="#session.genericSession.getWorkSession(key).status==\"NO Cumple requisitos\"">
                            <td style="color: #FF0000;">
                            </s:if>
                            <s:else>
                            <td style="color: #088A68;">
                            </s:else>

                            <s:property value="#session.genericSession.getWorkSession(key).status"/></td>
                    </tr>
                    <tr>
                        <td><s:text name="label.term.short"/>/<s:text name="label.year"/>&nbsp; Inscripción</td>
                        <td><input id="sem" name="sem"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).practica.id.praSem"/>" maxlength="1"
                                   size="1" class="form-control input-sm input-sem input-inline"/>
                                   <input id="agno" name="agno"
                                   value="<s:property value="#session.genericSession.getWorkSession(key).practica.id.praAgno"/>" maxlength="4"
                                   size="4" class="form-control input-sm input-agno input-inline"/></td>
                    </tr>
                </table>
            </div>

            <s:if test="#session.genericSession.getWorkSession(key).solicitud.solicitudAttachList.size() >0">

                <div style="box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.1);margin: 10px;border: 1px solid #d7dee1;padding: 5px 0px;">

                    <table style="width: 50%">
                        <s:iterator value="#session.genericSession.getWorkSession(key).solicitud.solicitudAttachList" status="row">
                            <tr>
                                <td style="width:25%"><img src="/intranetv7/images/local/icon/attachment.png" alt="attach"/></td>
                                <td><a class="link" href="CommonSolicitudDownLoadFile?file=<s:property value="#row.count -1"/>&key=<s:property value="key"/>"><s:property
                                            value="solaAttachFile"/></a></td>
                                <td><s:property value="tdocumentoSolicitud.tdsDes"/></td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </s:if>
            <div style="box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.1);margin: 10px;border: 1px solid #d7dee1;padding: 5px 0px;">
                <table cellpadding="10">
                    <tr>
                        <td width="25%">
                            Estado
                        </td>
                        <td width="75%">
                            <select id="estado" name="estado">
                                <option value="S">Solicitada</option>
                                <option value="A">Aprobada</option>
                                <option value="R">Rechazada</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td width="10%">
                            Motivo
                        </td>
                        <td width="90%">
                            <textarea name="respuesta" id="respuesta" rows="3" cols="120"><s:property value="#session.genericSession.getWorkSession(key).solicitud.solRespuesta"/></textarea>
                        </td>
                    </tr>
                </table>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="status" name="status" value="<s:property value="#session.genericSession.getWorkSession(key).practica.praEstado"/>"/>
            </div>
        </form>
    </body>
</html>

