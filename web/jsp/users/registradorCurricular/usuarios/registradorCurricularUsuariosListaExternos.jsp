<%--
    Document   : registradorCurricularUsuariosListaExternos
    Created on : 17-01-2011, 12:01:47 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Usuarios Externos</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/usuarios/registradorCurricularUsuariosListaExternos-2.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.carreras"/>
        </div>
        <div id="central-div">
            <!--BUTTONS-->
            <button id="add-button" name="add-button" class="positive">
                <img src="/intranetv7/images/local/icon/plus.png" height="16" width="16" alt="add" title="Nuevo Usuario"/>
                <s:text name="label.button.new"/>
            </button>
            <button id="delete-button" name="delete-button" class="positive">
                <img src="/intranetv7/images/local/icon/remove.png" height="16" width="16" alt="delete"
                     title="Eliminar Usuarios Seleccionados"/>
                <s:text name="label.button.delete"/>
            </button>
            <form id="users-form" action="#">
                <div id="table-div">
                    <table id="users-table">
                        <thead>
                            <tr>
                                <th scope="col"><label for="checkHeadInput"></label><input style="height: 12px" id="checkHeadInput"
                                                                                           name="checkHeadInput"
                                                                                           type="checkbox"
                                                                                           onclick="checkingHead('users-form');"/>
                                </th>
                                <th scope="col"><s:text name="label.rut"/></th>
                                <th scope="col"><s:text name="label.paterno"/></th>
                                <th scope="col"><s:text name="label.materno"/></th>
                                <th scope="col"><s:text name="label.name"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.genericSession.registradorCurricularSession.externoList" status="row">
                                <tr>
                                    <td style="width:20px; text-align:center"><input style="height: 12px" type="checkbox"
                                                                                     id="ck_<s:property value="#row.count"/>"
                                                                                     name="ck_<s:property value="#row.count"/>"
                                                                                     value="<s:property value="#row.count"/>"/></td>
                                    <td style="width:10%; text-align: right"><s:property value="extRut"/>-<s:property
                                            value="extDv"/>&nbsp;&nbsp;</td>
                                    <td><s:property value="extPaterno"/></td>
                                    <td><s:property value="extMaterno"/></td>
                                    <td><s:property value="extNombre"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
        <div id="confirmacion" title="<s:text name="message.title.confirmacion"/>"><s:text
                name="confirmation.eliminacion.usuario"/></div>
        <div id="msg-div" title="<s:text name="message.title.msg"/>"><s:text name="alert.seleccione.usuario"/></div>
    </body>
</html>