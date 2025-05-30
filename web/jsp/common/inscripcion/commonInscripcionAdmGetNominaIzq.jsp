<%--
    Document   : commonInscripcionAdmGetNominaIzq
    Created on : 08-04-2023, 17:43:21
    Author     : Felipe and Javier
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
<script type="text/javascript" src="/intranetv7/js/local/common/inscripcion/commonInscripcionAdmGetNominaIzq-3.0.2.js"></script>

<div class="title-div" style="line-height: 1.5;">
    &nbsp;<s:text name="label.title.listaCurso"/> <s:property
        value="#session.jefeCarreraSession.cursoAdmIzq.nombreCorto"/>
    &nbsp;Ini&nbsp;
    &nbsp;<s:property value="#session.jefeCarreraSession.cursoAdmIzq.curCupoIni"/>&nbsp;
    &nbsp;Dis&nbsp;
    &nbsp;<span <s:if test="#session.jefeCarreraSession.cursoAdmIzq.curCupoDis < 0" >style="color:red"</s:if>>
        <s:property value="#session.jefeCarreraSession.cursoAdmIzq.curCupoDis"/>
    </span>
</div>
<div id="content-div" style="height: 350px; overflow-y: scroll; overflow-x: hidden;">
    <form id="lista-izq-form" action="#" method="post">
        <table id="nomina-izq-table" class="table childgrid childgrid-izq" >
            <thead>
                <tr>
                    <th>N°</th>
                    <th><s:text name="label.rut"/></th>
                    <th><s:text name="label.paterno"/></th>
                    <th><s:text name="label.materno"/></th>
                    <th><s:text name="label.name"/></th>
                    <th>RNK</th>
                </tr>
            </thead>
            <tbody class="dragFrameBodyIzq dropFrameBodyIzq">
            <s:iterator value="#session.jefeCarreraSession.nominaCursoAdmIzq" status="row">
                <s:if test="#row.count <= #session.jefeCarreraSession.cursoAdmIzq.curCupoIni">
                <tr id = "row_<s:property value="#row.count -1"/>" >
                </s:if>
                <s:else>
                <tr id = "row_<s:property value="#row.count -1"/>" class="sobreCupo" >
                </s:else>                        
                    <td><s:property value="#row.count"/></td>
                    <td><s:property value="alumno.aluRut"/>-<s:property value="alumno.aluDv"/></td>
                    <td><s:property value="alumno.aluPaterno"/></td>
                    <td><s:property value="alumno.aluMaterno"/></td>
                    <td><s:property value="alumno.aluNombre"/></td>
                    <td><s:property value="acaRanking"/></td>    
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <div id="msg-dummy" name="msg-dummy" style="display: none;"><s:actionerror/><s:actionmessage/></div>
        <div id="hidden-input-div">
            <input type="hidden" id="listaIzq" name="listaIzq" />
            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            <input type="hidden" id="nombreDummy" name="nombreDummy" value="<s:property value="#session.jefeCarreraSession.cursoAdmIzq.nombreNormalizado"/>"/>
        </div>
    </form>
</div>
<div>
    <%--s:if test="hasActionErrors()">
        <script>
            var msgError = $("#msg-dummy").html().replace(/(\r\n|\n|\r|\t)/g, "");
            $(document).contents().find("#msg-error-div").html("<div class='actionError'><ul><li><span>" + msgError + "</li></ul></span></div>");
            $('#msg-error').modal('show');
        </script>    
    </s:if--%>
</div>