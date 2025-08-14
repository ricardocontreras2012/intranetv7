<%-- 
    Document   : alumnoSolicitudExpedienteLogroGetLogros
    Created on : 26-05-2025, 12:39:26
    Author     : Ricardo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Logros</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/5.3.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/5.3.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/solicitud/expedienteLogro/alumnoSolicitudExpedienteLogroGetLogros-3.0.2.js"></script>
    </head>
    <body class="bg-light">

        <div class="container-fluid mt-3 mb-5">
            <form id="solicitudes-form" action="#">
                <div class="data-tables-container">
                    <s:if test="fechaSolLogro">
                        <table id="solicitudes-table" class="table table-striped table-bordered dataTable">
                            <thead>
                                <tr>                            
                                    <th scope="col" style="width:100%">Logro</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="#session.genericSession.getWorkSession(key).expedienteLogroList" status="row">
                                    <s:if test="planLogro.logro.logrCod == 20 || planLogro.logro.logrCod == 50">
                                        <tr data-estado="<s:property value="explEstado"/>" data-solicitud="<s:property value="explSol"/>" data-logro="<s:property value="planLogro.logro.logrCod"/>">
                                            <td>
                                                <s:if test="explSol != null">
                                                <a role="button" tabindex="0" data-bs-toggle="modal" data-bs-target="#solicitudEnviadaModal">
                                                    <s:property value="planLogro.plalNomLogro"/>
                                                </a>
                                                </s:if>
                                                <s:else>
                                                <a id="expl_<s:property value="#row.count -1"/>"><s:property value="planLogro.plalNomLogro"/></a>
                                                </s:else>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <s:elseif test="planLogro.logro.logrCod == 80">
                                        <!--tr><td><s:property value="planLogro.plalNomLogro"/></td></tr-->
                                    </s:elseif>    
                                    <s:else>
                                        <tr data-estado="<s:property value="explEstado"/>" data-solicitud="<s:property value="explSol"/>" data-logro="<s:property value="planLogro.logro.logrCod"/>">
                                            <td>
                                                <a role="button" tabindex="0" data-bs-toggle="modal" data-bs-target="#noDisponibleModal">
                                                    <s:property value="planLogro.plalNomLogro"/>
                                                </a>
                                            </td>
                                        </tr>
                                    </s:else>
                                </s:iterator>
                            </tbody>
                        </table>                    
                    </s:if>
                    <s:else>
                        <table id="solicitudes-table" class="table table-striped table-bordered dataTable">
                            <thead>
                                <tr>                            
                                    <th scope="col" style="width:100%">Logro</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="#session.genericSession.getWorkSession(key).expedienteLogroList" status="row">
                                    <tr>
                                        <td>
                                            <a role="button" tabindex="0" data-bs-toggle="modal" data-bs-target="#avisoModal">
                                            <s:property value="planLogro.plalNomLogro"/>
                                            </a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </s:else>
                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                    </div>
                </div>
            </form>
        </div>
                    
        <!-- Modal Aviso si fecha de solicitud cerrada -->
        <div class="modal fade" id="avisoModal" tabindex="-1" aria-labelledby="avisoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="avisoModalLabel">Aviso</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        El proceso de obtención de título está cerrado por el período actual. Cuando se anuncien los nuevos valores de títulación y/o grado en marzo próximo volverá a estar disponible.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal Aviso logro no puede tramitarse vía intranet -->
        <div class="modal fade" id="noDisponibleModal" tabindex="-1" aria-labelledby="noDisponibleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="noDisponibleModalLabel">Aviso</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Logro NO disponible para solicitud vía Intranet.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal Aviso título no puede tramitarse antes de licenciatura -->
        <div class="modal fade" id="requiereLicenciaturaModal" tabindex="-1" aria-labelledby="requiereLicenciaturaModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="requiereLicenciaturaModalLabel">Aviso</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Debe solicitar su Licencitura como requisito para la entrega del Título.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal Aviso solicitud ya enviada -->
        <div class="modal fade" id="solicitudEnviadaModal" tabindex="-1" aria-labelledby="solicitudEnviadaModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="solicitudEnviadaModalLabel">Aviso</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Solicitud de expediente ya enviada.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Aceptar</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
