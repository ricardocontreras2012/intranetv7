<%--
    Document   : commonEvaluadorPractica
    Created on : 10-06-2011, 10:46:47 PM
    Author     : Ricardo Contreras S.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table style="width: 98%; margin-top: -5px">
    <tr>
        <td style="width: 20%"><s:text name="label.paterno"/></td>
        <td><label for="paternoEvaluador"></label><label for="paternoEvaluador"></label><input id="paternoEvaluador"
                                                                                               name="paternoEvaluador"
                                                                                               size="50"
                                                                                               style="text-transform: uppercase;"
                                                                                               value="<s:property value="#session.genericSession.getWorkSession(key).evaluadorPractica.evapPaterno"/>"/>
        </td>
    </tr>
    <tr>
        <td><s:text name="label.materno"/></td>
        <td><label for="maternoEvaluador"></label><input id="maternoEvaluador" name="maternoEvaluador" size="50"
                                                         style="text-transform: uppercase;"
                                                         value="<s:property value="#session.genericSession.getWorkSession(key).evaluadorPractica.evapMaterno"/>"/>
        </td>
    </tr>
    <tr>
        <td><s:text name="label.name"/></td>
        <td><label for="nombreEvaluador"></label><input id="nombreEvaluador" name="nombreEvaluador" size="50"
                                                        style="text-transform: uppercase;"
                                                        value="<s:property value="#session.genericSession.getWorkSession(key).evaluadorPractica.evapNombre"/>"/>
        </td>
    </tr>
    <tr>
        <td><s:text name="label.email"/></td>
        <td><label for="emailEvaluador"></label><input id="emailEvaluador" name="emailEvaluador" size="50"
                                                       value="<s:property value="#session.genericSession.getWorkSession(key).evaluadorPractica.evapEmail"/>"/>
        </td>
    </tr>
    <tr>
        <td><s:text name="label.cargo"/></td>
        <td><label for="cargoEvaluador"></label><input id="cargoEvaluador" name="cargoEvaluador" size="100"
                                                       value="<s:property value="#session.genericSession.getWorkSession(key).evaluadorPractica.evapCargo"/>"/>
        </td>
    </tr>
</table>