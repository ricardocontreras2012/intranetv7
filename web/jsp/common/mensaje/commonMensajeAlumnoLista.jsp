<%--
    Document   : commonMensajeAlumnoLista
    Created on : 05-02-2010, 03:28:26 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Alumnos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeAlumnoLista-3.0.2.js"></script>
    </head>    
    <body class="inner-body"> 

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

        <s:if test="%{#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeList==null}">
            <s:set var="largoLista" value="0"/>
        </s:if>
        <s:else>
            <s:set var="largoLista"
                   value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeList.size"/>
        </s:else>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="search-button" title="Buscar" type="button" class="btn btn-light"  onClick="otroAlumno();return false;">
                                <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search.other"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="next-button" title="Next" type="button" class="btn btn-light"  onClick="nextDestinySearch();return false;">
                                <span class="fa fa-step-forward"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.next"/></span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <form id="alumno-lista-form" action="#" method="post" accept-charset="UTF-8">
            <div>
                <s:property value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.value.toUpperCase()"/>
            </div>
            <table class="table table-striped">
                <s:iterator value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.nodeList" status="row">
                    <tr>
                        <td>
                            <input type="checkbox" name="ck_<s:property value="#row.count -1"/>" id="ck_<s:property value="#row.count -1"/>"
                                   value="<s:property value="id"/>" checked="checked"  class="form-group-sm" />&nbsp;<s:property
                                value="value"/>
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <div id="hidden-input-div">
                <input id="key" name="key" type="hidden" value="<s:property value="key"/>"/>
                <input id="actionCall" name="actionCall" type="hidden" value="CommonMensajeAddAlumno"/>
                <input type="hidden" id="largoLista" name="largoLista" value="<s:property value="#largoLista"/>"/>
                <input type="hidden" id="largoBarra" name="largoBarra" value="0"/>
                <input type="hidden" id="nombreLista" name="nombreLista" value="Alumno(s)"/>
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                <input type="hidden" id="destDummy" name="destDummy"
                       value="<s:text name="label.select.lista"/> <s:property value="#session.genericSession.getWorkSession(key).mensajeSupport.currentNode.value.toUpperCase()"/>"/>
            </div>
            <div id="msg-div" title="<s:text name="message.title.msg"/>"></div>

        </form>   
    </body>
</html>