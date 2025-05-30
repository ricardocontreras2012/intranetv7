<%--
    Document   : commonCursoGetAyudante
    Created on : 17-09-2020, 20:17:51
    Author     : Ricardo
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table id="ayudante-table">
    <s:iterator value="#session.genericSession.getWorkSession(key).docenteHorarioList" status="row" var="ayuVar">
        <tr>
            <td style="width: 5%"><input type="checkbox" id="ayu_ck_<s:property value="#row.count -1"/>"
                                                 name="ayu_ck_<s:property value="#row.count -1"/>"/></td>
            <td style="width: 15%"><input id="ayu_<s:property value="#row.count -1"/>" name="ayu_<s:property value="#row.count -1"/>" value="<s:property value="ayudante.ayuRut"/>-<s:property value="ayudante.ayuDv"/>" readonly="readonly" class="form-control"/></td>
            <td style="width: 70%"><s:property value="ayudante.ayuPaterno"/>&nbsp;<s:property value="ayudante.ayuMaterno"/>&nbsp;<s:property value="ayudante.ayuNombre"/></td>
            <td style="width: 10%">
                <select id="horAyu_<s:property value="#row.count -1"/>" name="horAyu_<s:property value="#row.count -1"/>" class="form-control">
                    <option value="" class="form-control" selected></option>
                    <s:iterator value="#session.genericSession.getWorkSession(key).horarioList" status="row">
                        <s:if test="id.horDia == #ayuVar.dhorDia && id.horModulo == #ayuVar.dhorModulo">
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
    <select id="diamodAyuDummy" name="diamodAyuDummy">
        <s:iterator value="#session.genericSession.getWorkSession(key).horarioList" status="row">
            <option value="<s:property value="id.horDia"/><s:property value="id.horModulo"/>" class="form-control"><s:property value="id.horDia"/><s:property value="id.horModulo"/></option>
        </s:iterator>
    </select>
</div>
