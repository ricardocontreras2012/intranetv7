<%--
    Document   : commonMensajeListaDestinatarios
    Created on : 28-10-2009, 12:50:00 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Mensajes Enviados</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeDestinatarios-3.0.2.js"></script>        
    </head>
    <body class="inner-body">
        <form id="destinatarios-form" action="#" accept-charset="UTF-8">
            <%--
            No mover de posición este código debo a que se requiere para setear valores en las
            funciones.
            --%>
            <s:if test="%{#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeListBar==null}">
                <s:set var="largoBarra" value="0"/>
            </s:if>
            <s:else>
                <s:set var="largoBarra"
                       value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeListBar.size"/>
            </s:else>
            <s:if test="%{#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeList==null}">
                <s:set var="largoLista" value="0"/>
            </s:if>
            <s:else>
                <s:set var="largoLista"
                       value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeList.size"/>
            </s:else>
            <%--
            --%>

            <div>
                <button id="next-button" title="Next" type="button" class="btn btn-light"  onclick="nextDestiny(); return false;"><span class="fa fa-step-forward" aria-hidden="true"></span>
                    &nbsp; <span class="hidden-xs"><s:text name="label.button.next"/></span>
                </button>
            </div>

            <s:if test="%{#largoBarra >0}">
                <!--BARRA-->
                <div class="title-div"><p><s:text name="label.bar"/></p></div>
                <table class="table table-striped">
                    <s:iterator value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeListBar" status="row">
                        <tr>
                            <td>
                                <input type="checkbox"
                                       name="barraCk_<s:property value="#row.count -1"/>"
                                       id="barraCk_<s:property value="#row.count -1"/>" value="<s:property value="id"/>" class="form-group-sm"/>&nbsp;<s:property value="value"/>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
                <!--BARRA-->
            </s:if>

            <div class="title-div">
                <s:property value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.value.toUpperCase()"/>
            </div>

            <table style="margin-left: 8px">
                <tr>
                    <th>
                        <input
                            type="checkbox" id="checkHeadInput"
                            name="checkHeadInput" value="off"
                            onclick="checkingHead('destinatarios-form');"/>
                    </th>
                    <th align="left" style="text-align: left !important">&nbsp;Marcar / Desmarcar Todos
                    </th>
                </tr>
            </table>

            <table style="width: 98%" class="table table-striped">
                <s:iterator value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeList" status="row">
                    <tr>
                        <td>
                            <input type="checkbox" name="ck_<s:property value="#row.count -1"/>" id="ck_<s:property value="#row.count -1"/>" value="<s:property value="id"/>"/>
                            &nbsp;
                            <s:property value="value"/>
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <div id="hidden-input-dest-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="largoLista" name="largoLista" value="<s:property value="#largoLista"/>"/>
                <input type="hidden" id="largoBarra" name="largoBarra" value="<s:property value="#largoBarra"/>"/>
                <input type="hidden" id="nombreLista" name="nombreLista"
                       value="<s:property value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.id"/>"/>
                <input type="hidden" id="nombreDummy" name="nombreDummy"
                       value="<s:text name="label.select.lista"/> <s:property value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.value.toUpperCase()"/>"/>
            </div>

        </form>
        <div class="modal fade" id="destinatarios" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="alert.seleccione.destinatarios"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="barra" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="alert.seleccione.destinatarios.check"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>               
    </body>
</html>