<%--
    Document   : commonMaterialNominaAlumnos
    Created on : 24-09-2014, 07:06:23 AM
    Author     : Ricardo Contreras S.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript"
src="/intranetv7/js/local/common/material/commonMaterialNominaAlumnos-3.0.0.js"></script>

<div class="title-div">
    &nbsp;&nbsp;&nbsp;&nbsp;<s:text name="label.title.listaCurso"/> <s:property value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
</div>

<div id="central-div">
    <div id="content-div">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col"><s:text name="label.rut"/></th>
                    <th scope="col"><s:text name="label.paterno"/></th>
                    <th scope="col"><s:text name="label.materno"/></th>
                    <th scope="col"><s:text name="label.name"/></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="#session.genericSession.getWorkSession(key).nominaCurso" status="row">
                    <tr>
                        <td style="text-align: right">
                            <s:property value="#row.count"/>&nbsp;&nbsp;&nbsp;
                        </td>
                        <td>
                            <a id="alumno_<s:property value="#row.count -1"/>"><s:property
                                    value="alumno.aluRut"/>-<s:property value="alumno.aluDv"/></a>
                        </td>
                        <td>
                            <s:property value="alumno.aluPaterno"/>
                        </td>
                        <td>
                            <s:property value="alumno.aluMaterno"/>
                        </td>
                        <td>
                            <s:if test="alumno.aluNombreSocial != null && alumno.aluNombreSocial.trim() != ''">
                                <s:property value="alumno.aluNombreSocial"/>
                            </s:if>
                            <s:else>
                                <s:property value="alumno.aluNombre"/>
                            </s:else>
                        </td>
                        <td style="text-align: center">
                            <img height="60" width="50" id="foto<s:property value="#row.count"/>"
                                 src="dummy/<s:property value="#row.count -1"/>/CommonCursoGetFotoAlumno?pos=<s:property value="#row.count -1"/>&key=<s:property value="key"/>"
                                 alt="foto"/>
                        </td>
                    </tr>
                </s:iterator>
        </table>
    </div>
</div>