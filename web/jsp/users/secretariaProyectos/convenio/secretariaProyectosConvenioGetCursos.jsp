<%--
    Document   : secretariaProyectosConvenioGetCursos
    Created on : 11-12-2020, 9:51:48
    Author     : Ricardo
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="/intranetv7/js/local/users/secretariaProyectos/convenio/secretariaProyectosConvenioGetCursos-3.0.1.js"></script>
<select id="curso" name="curso" class="input-form">
    <option value="-1">-- Seleccione Curso --</option>
    <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
        <option value="<s:property value="#row.count -1"/>" class="form-control"><s:property value="getNombreFull()"/> (<s:property value="curHorario"/>)</option>
    </s:iterator>
</select>