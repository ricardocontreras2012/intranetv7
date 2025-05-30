<%--
    Document   : commonCursoDefinicionGetProfesor
    Created on : 17-09-2020, 20:17:33
    Author     : Ricardo Contreras S.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table id="profesor-estricto-table">
    <s:iterator value="#session.genericSession.getWorkSession(key).docenteHorarioList" status="row" var="profVar">
        <tr>
            <td style="width: 15%"><input id="prof_<s:property value="#row.count -1"/>" name="prof_<s:property value="#row.count -1"/>" value="<s:property value="profesor.profRut"/>-<s:property value="profesor.profDv"/>" readonly="readonly" class="form-control"/></td>
            <td style="width: 75%"><s:property value="profesor.profPat"/>&nbsp;<s:property value="profesor.profMat"/>&nbsp;<s:property value="profesor.profNom"/></td>
            <td style="width: 10%"><input id="hor_<s:property value="#row.count -1"/>" name="hor_<s:property value="#row.count -1"/>" value="<s:property value="dhorDia"/><s:property value="dhorModulo"/>" readonly="readonly" class="form-control"/></td>
        </tr>
    </s:iterator>
</table>
<div style="display:none">
    <select id="diamodEstrictoDummy" name="diamodEstrictoDummy">
        <s:iterator value="#session.genericSession.getWorkSession(key).horarioList" status="row">
            <option value="<s:property value="id.horDia"/><s:property value="id.horModulo"/>" class="form-control"><s:property value="id.horDia"/><s:property value="id.horModulo"/></option>
        </s:iterator>
    </select>    
</div>
