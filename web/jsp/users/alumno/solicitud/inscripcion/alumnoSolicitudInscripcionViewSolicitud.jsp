<%--
    Document   : alumnoSolicitudViewSolicitud
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
        <title>Detalle de Solicitud</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/solicitud/alumnoSolicitudInscripcionViewSolicitud-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.solicitud.inscripcion"/>
        </div>

        <form id="solicitud-form" action="#" method="post">
            <table class="table table-striped table-bordered">
                <%--tr>
                <div id="fotoDiv" style="margin-left: 10px; top:10px; width:114px; height:128px">
                    <img id="fotoImg"
                         src="CommonAlumnoGetFoto?rut=<s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.aluRut"/>&key=<s:property value="key"/>"
                         height="125" width="110"
                         alt="<s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.aluRut"/>"/>
                </div>
                </tr--%>
                <tr>
                    <td><s:text name="label.rut"/></td>
                    <td><s:property
                            value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluRut"/>-<s:property
                            value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluDv"/></td>
                </tr>
                <tr>
                    <td><s:text name="label.name"/></td>
                    <td><s:property
                            value="#session.genericSession.getWorkSession(key).aluCar.alumno.getNombre()"/></td>
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
                    <td><s:text name="label.solicita"/></td>                    
                        <s:if test="#session.genericSession.getWorkSession(key).solicitudInscripcionList.size() > 0">
                            <td>
                            <table class="table table-striped table-bordered">
                                <s:iterator value="#session.genericSession.getWorkSession(key).solicitudInscripcionList" status="row">
                                <tr>
                                    <td style="width: 30%"><s:property value="curso.getNombreFull()"/></td>
                                    <td><s:property value="motivo.tmsiDes"/>&nbsp;<s:property value="soliOtroGlosa"/></td>
                                </tr>
                                </s:iterator>
                            </table>
                            </td>
                        </s:if>
                        <s:else>
                            <td><s:property value="#session.genericSession.getWorkSession(key).solicitud.solSolicita"/></td>
                        </s:else>
                </tr>
                
                <tr>
                    <td><s:text name="label.resolucion"/></td>
                    <td><s:property value="#session.genericSession.getWorkSession(key).solicitud.getResolucion()"/></td>
                </tr>
                <tr>
                    <td><s:text name="label.respuesta"/></td>
                    <td><textarea name="respuesta" id="respuesta" rows="3" cols="120" readonly="readonly"><s:property value="#session.genericSession.getWorkSession(key).solicitud.solRespuesta"/></textarea>
                    </td>
                </tr>
            </table>

            <p></p>
            <table style="width: 98%">
                <s:iterator value="#session.genericSession.getWorkSession(key).solicitud.solicitudAttachList" status="row">
                    <tr>
                        <td style="width:5%"><span class="fa fa-paperclip"></span>&nbsp;</td>
                        <td><a class="link" href="CommonSolicitudDownLoadFile?file=<s:property value="#row.count -1"/>&key=<s:property value="key"/>"><s:property
                                    value="solaAttachFile"/></a></td>
                    </tr>
                </s:iterator>
            </table>

            <p></p>
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

            <div id="hidden-input1-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="documento" name="documento" value="<s:property value="documento"/>"/>
            </div>
        </form>
    </body>
</html>
