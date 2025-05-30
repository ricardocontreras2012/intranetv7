<%--
    Document   : commonCursoGetHorario
    Created on : 17-09-2020, 20:17:19
    Author     : Ricardo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table id="horario-table">
    <s:iterator value="#session.genericSession.getWorkSession(key).horarioList" status="row" var="horVar">
        <tr>
            <td width="5%"><input type="checkbox" id="hor_ck_<s:property value="#row.count -1"/>"
                       name="hor_ck_<s:property value="#row.count -1"/>"  /></td>
            <td width="15%">
                <select id="dia_<s:property value="#row.count -1"/>" name="dia_<s:property value="#row.count -1"/>" class="form-control">
                    <s:iterator value="#session.genericSession.diaList" status="rowDia">
                        <s:if test="diaCod == id.horDia" >
                            <option value="<s:property value="diaCod"/>" class="form-control" selected ><s:property value="diaCod"/></option>
                        </s:if>
                        <s:else>
                            <option value="<s:property value="diaCod"/>" class="form-control"><s:property value="diaCod"/></option>
                        </s:else>
                    </s:iterator>
                </select>
            </td>

            <td width="15%">
                <select id="mod_<s:property value="#row.count -1"/>" name="mod_<s:property value="#row.count -1"/>" class="form-control">
                    <s:iterator value="#session.genericSession.getWorkSession(key).moduloHorarioList" status="rowMod">
                        <s:if test="id.modCod == #horVar.id.horModulo" >
                            <option value="<s:property value="id.modCod"/>" class="form-control" selected ><s:property value="id.modCod"/></option>
                        </s:if>
                        <s:else>
                            <option value="<s:property value="id.modCod"/>" class="form-control"><s:property value="id.modCod"/></option>
                        </s:else>
                    </s:iterator>
                </select>
            </td>

            <td width="45%">
                <select id="sala_<s:property value="#row.count -1"/>" name="sala_<s:property value="#row.count -1"/>" class="form-control">
                    <option value="" class="form-control" selected></option>
                    <s:iterator value="#session.genericSession.getWorkSession(key).salaList" status="rowSala">
                        <s:if test="salaNum == #horVar.sala.salaNum" >
                            <option value="<s:property value="salaNum"/>" class="form-control" selected><s:property value="salaNum"/></option>
                        </s:if>
                        <s:else>
                            <option value="<s:property value="salaNum"/>" class="form-control"><s:property value="salaNum"/></option>
                        </s:else>
                    </s:iterator>
                </select>
            </td>
            
            <td width="20%">
                <select id="tipo_<s:property value="#row.count -1"/>" name="tipo_<s:property value="#row.count -1"/>" class="form-control">
                    <s:if test="horTipoClase == 'C'">
                        <option value="C" selected>T</option>
                        <option value="A">E</option>
                    </s:if>
                    <s:else>
                        <option value="C">T</option>
                        <option value="A" selected>E</option>
                    </s:else>
                </select>
            </td>
        </tr>
    </s:iterator>
</table>
<input type="hidden" id="asiHcredTeo" name="asiHcredTeo" value="<s:property value="#session.genericSession.getWorkSession(key).curso.asignatura.asiHcredTeo"/>"/>
<input type="hidden" id="asiHcredEje" name="asiHcredEje" value="<s:property value="#session.genericSession.getWorkSession(key).curso.asignatura.asiHcredEje"/>"/>
<%--input type="hidden" id="asiHcredLab" name="asiHcredLab" value="<s:property value="#session.genericSession.getWorkSession(key).curso.asignatura.asiHcredLab"/>"/--%>
<div style="display:none">
    <select id="salaDummy" name="salaDummy">
        <option value="" class="form-control"></option>
        <s:iterator value="#session.genericSession.getWorkSession(key).salaList" status="row">
            <option value="<s:property value="salaNum"/>" class="form-control"><s:property value="salaNum"/></option>
        </s:iterator>
    </select>
</div>
