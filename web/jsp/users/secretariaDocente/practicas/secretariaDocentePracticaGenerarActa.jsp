<%--
    Document   : secretariaDocentePracticaGenerarActa.jsp
    Created on : 25-11-2014, 10:57:52 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Generar Acta de Prácticas</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css"/>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/secretariaDocente/practicas/secretariaDocentePracticaGenerarActa-1.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.acta.practica"/>
        </div>

        <div id="central-div">
            <button id="emitir-button" name="emitir-button" class="positive">
                <img src="/intranetv7/images/local/icon/lock.png" height="16" width="16" alt="emitir" title="Emitir Acta"/>
                <s:text name="label.button.emitir"/>
            </button>
            <div id="content-div">
                <form id="convalidacion-form">

                    <label for="agno"><s:text name="label.year"/>&nbsp;&nbsp;</label><input id="agno" name="agno" size="4" value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>"/>
                    <label for="sem"><s:text name="label.term"/>&nbsp;&nbsp;</label><input id="sem" name="sem" size="1" value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>"/>

                    <div id="table-div">
                        <table id="malla-table">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                    <th scope="col"><s:text name="label.code"/></th>
                                    <th scope="col"><s:text name="label.asignatura"/></th>
                                    <th scope="col"><s:text name="label.cursada"/></th>
                                    <th scope="col"><s:text name="label.nota"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="#session.genericSession.secretariaDocenteSession.porAprobar" status="row">
                                    <tr>
                                        <td><s:property value="#row.count"/></td>
                                        <td><input type="checkbox" id="ck_<s:property value="#row.count -1"/>"
                                                   name="ck_<s:property value="#row.count -1"/>"/></td>
                                        <td style="text-align:right; width: 10%"><s:property value="asignatura"/></td>
                                        <td><s:property value="nombre"/></td>
                                        <td><input name="cursada_<s:property value="#row.count -1"/>" id="cursada_<s:property value="#row.count -1"/>"></td>
                                        <td><input name="nota_<s:property value="#row.count -1"/>" id="nota_<s:property value="#row.count -1"/>"></td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    </div>
                </form>
            </div>
        </div>
        <div id="confirmacion" title="<s:text name="message.title.confirmacion"/>"><s:text
                name="confirmation.emision.acta"/></div>
        <div id="msg" title="<s:text name="error.title"/>"></div>
    </body>
</html>
root
