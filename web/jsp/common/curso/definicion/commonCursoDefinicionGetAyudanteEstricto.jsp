<%--
    Document   : commonCursoGetAyudante
    Created on : 17-09-2020, 20:17:51
    Author     : Ricardo
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table id="ayudante-estricto-table">
    <s:iterator value="#session.genericSession.getWorkSession(key).docenteHorarioList" status="row" var="ayuVar">
        <tr>
            <td style="width: 15%"><input id="ayu_<s:property value="#row.count -1"/>" name="ayu_<s:property value="#row.count -1"/>" value="<s:property value="ayudante.ayuRut"/>-<s:property value="ayudante.ayuDv"/>" readonly="readonly" class="form-control"/></td>
            <td style="width: 75%"><s:property value="ayudante.ayuPaterno"/>&nbsp;<s:property value="ayudante.ayuMaterno"/>&nbsp;<s:property value="ayudante.ayuNombre"/></td>
            <td style="width: 10%"><input id="horAyu_<s:property value="#row.count -1"/>" name="horAyu_<s:property value="#row.count -1"/>" value="<s:property value="dhorDia"/><s:property value="dhorModulo"/>" readonly="readonly" class="form-control"/></td>
        </tr>
    </s:iterator>
</table>
<div style="display:none">
    <select id="diamodAyuEstrictoDummy" name="diamodAyuEstrictoDummy">
        <s:iterator value="#session.genericSession.getWorkSession(key).horarioList" status="row">
            <option value="<s:property value="id.horDia"/><s:property value="id.horModulo"/>" class="form-control"><s:property value="id.horDia"/><s:property value="id.horModulo"/></option>
        </s:iterator>
    </select>
</div>
