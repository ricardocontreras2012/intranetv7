<%-- 
    Document   : viceDecanoSolicitudSituacionViewSolicitud
    Created on : 08-05-2023, 15:00:41
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
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>        
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/viceDecano/solicitud/viceDecanoSolicitudViewSolicitud-3.0.4.js"></script>
    </head>
    <body class="inner-body">

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">                        
                        <div class="btn-group">
                            <s:if test="#session.genericSession.userType in {\"VDD\"} || (#session.genericSession.userType in {\"SF\"} && #session.genericSession.getWorkSession(key).solicitud.solResolucion ==\"TR\")">
                                <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                    <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                                </button>
                            </s:if>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>

        <div class="student-info">
            <header>                
            </header>
            <section>
                <p>
                    <strong>RUN:</strong> 
                    <s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.aluRut"/>-<s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.aluDv"/>
                </p>
                <p>
                    <strong>Nombre:</strong> 
                    <s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.alumno.getNombre()"/>
                </p>
                <p>
                    <strong>Carrera:</strong> 
                    <s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.id.acaCodCar"/> 
                    <s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.plan.mencion.getNombreCarreraFull()"/>
                    <span class="academic-year">
                        <s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.id.acaSemIng"/>/
                        <s:property value="#session.genericSession.getWorkSession(key).solicitud.aluCar.id.acaAgnoIng"/>
                    </span>
                </p>
            </section>
        </div>                            

        <s:form id="solicitud-form" action="#" theme="bootstrap">
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
                        <td>Motivo</td>
                        <td><s:property value="#session.genericSession.getWorkSession(key).solicitud.solMotivo"/></td>
                    </tr>

                    <s:if test="(#session.genericSession.getWorkSession(key).solicitud.solResolucion == null) || (#session.genericSession.getWorkSession(key).solicitud.tsolicitud.tsolCodigo == 40 && #session.genericSession.getWorkSession(key).solicitud.solResolucion ==\"TR\")">
                        <tr>                       
                            <td><s:text name="label.resolucion"/></td> 

                            <s:if test="#session.genericSession.getWorkSession(key).solicitud.tsolicitud.tsolCodigo == 11">
                                <td>
                                    <select id="resolucion" name="resolucion" class="form-control">
                                        <option value=""></option>
                                        <option value="A">Aceptada</option>
                                        <option value="R">Rechazada</option>
                                    </select>
                                </td>
                            </s:if>

                            <s:if test="#session.genericSession.getWorkSession(key).solicitud.tsolicitud.tsolCodigo == 25">
                                <td>
                                    <select id="resolucion" name="resolucion" class="form-control">
                                        <option value=""></option>
                                        <option value="A">Aceptada</option>
                                        <option value="AE">Aceptada Excepcionalmente</option>
                                        <option value="R">Rechazada</option>
                                        <option value="C">Condicional</option>
                                    </select>
                                </td>
                            </s:if>

                            <s:if test="#session.genericSession.getWorkSession(key).solicitud.tsolicitud.tsolCodigo == 30">
                                <td>
                                    <select id="resolucion" name="resolucion" class="form-control">
                                        <option value=""></option>
                                        <option value="A">Aceptada</option>
                                        <option value="R">Rechazada</option>
                                    </select>
                                </td>
                            </s:if>

                            <s:if test="#session.genericSession.getWorkSession(key).solicitud.tsolicitud.tsolCodigo == 35">
                                <td>
                                    <select id="resolucion" name="resolucion" class="form-control">
                                        <option value=""></option>
                                        <option value="A">Aceptada</option>
                                        <option value="R">Rechazada</option>
                                    </select>
                                </td>
                            </s:if>

                            <s:if test="#session.genericSession.getWorkSession(key).solicitud.tsolicitud.tsolCodigo == 40">
                                <s:if test="#session.genericSession.getWorkSession(key).solicitud.solResolucion == null && #session.genericSession.userType in {\"VDD\"}">
                                    <td>
                                        <select id="resolucion" name="resolucion" class="form-control">
                                            <option value=""></option>
                                            <option value="TR">En Trámite</option>
                                            <option value="R">Rechazada</option>
                                        </select>
                                    </td>
                                </s:if>
                                <s:if test="#session.genericSession.getWorkSession(key).solicitud.solResolucion ==\"TR\" ">
                                    <td>
                                        <select id="resolucion" name="resolucion" class="form-control">
                                            <option value=""></option>
                                            <option value="A">Aceptada</option>
                                        </select>
                                    </td>
                                </s:if>
                            </s:if>
                        </tr>
                        <tr>
                            <td><s:text name="label.respuesta"/></td>
                            <td><textarea name="respuesta" id="respuesta" name="respuesta" rows="3" cols="120" class="form-control"></textarea>
                            </td>
                        </tr>
                    </s:if>
                    <s:else>
                        <tr><td><s:text name="label.resolucion"/></td> <td><s:property value="#session.genericSession.getWorkSession(key).solicitud.solResolucion"/></td></tr>
                        <tr><td><s:text name="label.respuesta"/></td><td><s:property value="#session.genericSession.getWorkSession(key).solicitud.solRespuesta"/></td></tr>
                    </s:else>
                </table>
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
                <input type="hidden" id="tipo" name="tipo" value="<s:property value="#session.genericSession.getWorkSession(key).solicitud.tsolicitud.tsolCodigo"/>"/>                 
            </div>
        </s:form>
    </body>
</html>