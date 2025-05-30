<%--
    Document   : commonCursoDefinicionGetProfesor
    Created on : 17-09-2020, 20:17:33
    Author     : Ricardo Contreras S.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table id="profesor-table">
    <s:iterator value="#session.genericSession.getWorkSession(key).docenteHorarioList" status="row" var="profVar">
        <tr>
            <td style="width: 5%"><input type="checkbox" id="prof_ck_<s:property value="#row.count -1"/>"
                       name="prof_ck_<s:property value="#row.count -1"/>"></td>
            <td style="width: 15%"><input id="prof_<s:property value="#row.count -1"/>" name="prof_<s:property value="#row.count -1"/>" value="<s:property value="profesor.profRut"/>-<s:property value="profesor.profDv"/>" readonly="readonly" class="form-control"/></td>
            <td style="width: 70%"><s:property value="profesor.profPat"/>&nbsp;<s:property value="profesor.profMat"/>&nbsp;<s:property value="profesor.profNom"/></td>
            <td style="width: 10%">
                <select id="hor_<s:property value="#row.count -1"/>" name="hor_<s:property value="#row.count -1"/>" class="form-control">
                    <option value="" class="form-control" selected></option>
                    <s:iterator value="#session.genericSession.getWorkSession(key).horarioList" status="row">
                        <s:if test="id.horDia == #profVar.dhorDia && id.horModulo == #profVar.dhorModulo" >
                            <option value="<s:property value="id.horDia"/><s:property value="id.horModulo"/>" class="form-control" selected><s:property value="id.horDia"/><s:property value="id.horModulo"/></option>
                        </s:if>
                        <s:else>
                            <option value="<s:property value="id.horDia"/><s:property value="id.horModulo"/>" class="form-control"><s:property value="id.horDia"/><s:property value="id.horModulo"/></option>
                        </s:else>
                    </s:iterator>
                </select>
            </td>
        </tr>
    </s:iterator>
</table>
<div style="display:none">
    <select id="diamodDummy" name="diamodDummy">
        <s:iterator value="#session.genericSession.getWorkSession(key).horarioList" status="row">
            <option value="<s:property value="id.horDia"/><s:property value="id.horModulo"/>" class="form-control"><s:property value="id.horDia"/><s:property value="id.horModulo"/></option>
        </s:iterator>
    </select>    
</div>
