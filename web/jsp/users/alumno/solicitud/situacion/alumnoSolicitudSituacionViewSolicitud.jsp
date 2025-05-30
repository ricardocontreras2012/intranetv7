<%--
    Document   : alumnoSolicitudPracticaViewSolicitud
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
        <title>Solicitud de Cambio de Situación</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />       
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>               
    </head>
    <body class="inner-body">        
        <s:form id="solicitud-form" action="#" theme="bootstrap">
            <div class="h5">
                SOLICITUD: <s:property value="#session.genericSession.getWorkSession(key).solicitud.solSolicita"/>
            </div>

            <div style="box-shadow: 2px 1px 3px 1px rgba(0, 0, 0, 0.1);margin: 10px;border: 1px solid #d7dee1;padding: 5px 0px;">
                <s:if test="#session.genericSession.getWorkSession(key).solicitud.solicitudAttachList.size() >0">
                    <table style="width: 98%">
                        <s:iterator value="#session.genericSession.getWorkSession(key).solicitud.solicitudAttachList" status="row">
                            <tr>
                                <td style="width:5%"><img src="/intranetv7/images/local/icon/attachment.png" alt="attach"/></td>
                                <td><a class="link" href="CommonSolicitudDownLoadFile?file=<s:property value="#row.count -1"/>&key=<s:property value="key"/>"><s:property
                                            value="solaAttachFile"/></a></td>                                
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
                            Respuesta
                        </td>
                        <td width="90%">
                            <s:property value="#session.genericSession.getWorkSession(key).solicitud.solRespuesta"/>
                        </td>
                    </tr>
                </table>
            </div>
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

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>                
            </div>
        </s:form>
    </body>
</html>

