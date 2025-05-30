<%-- 
    Document   : commonInscripcionAdministracionGetCursosIzq
    Created on : 08-04-2023, 16:43:21
    Author     : Felipe
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
<link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
<link rel="stylesheet" href="/intranetv7/css/local/local-adm-inscripcion.css" type="text/css" />
<script type="text/javascript" src="/intranetv7/js/jquery/jquery.scrollintoview.min.js"></script>
<script type="text/javascript" src="/intranetv7/js/local/common/inscripcion/commonInscripcionAdmGetCursosIzq-3.0.2.js"></script>        

<form id="cursos-izq-form" action="#" method="post">
    <div style="height: 350px; overflow-y: scroll; overflow-x: hidden;">
        <table id="cursos-izq-table" class="table table-striped">
            <thead>
                <tr>
                    <th scope="col" style="width:80%">
                        <s:text name="label.curso"/>
                    </th>
                    <th scope="col" style="width:15%">
                        <s:text name="label.horario"/>
                    </th>
                    <th scope="col" style="width:5%">
                        <s:text name="label.profesor"/>
                    </th>
                    <th scope="col" style="width:5%">
                        Ini
                    </th>
                    <th scope="col" style="width:5%">
                        Dis
                    </th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="#session.genericSession.getWorkSession(key).cursoList" status="row">
                    <tr style="height: 14px">
                        <td style="width:80%; text-align:left">
                            <a class="link" id="curso_<s:property value="#row.count -1"/>"><s:property value="id.curAsign"/></a>
                            <s:property value="id.curElect"/>
                            <s:property value="id.curCoord"/>
                            <s:property value="id.curSecc"/>
                            <s:property value="curNombre"/>
                        </td>
                        <td style="width:15%">
                            <s:property value="curHorario"/>
                        </td>
                        <td style="width:5%; cursor: pointer">
                            <img onclick="showProfesor('<s:property value="curProfesores.trim().replace('/ /g','0')"/>', <s:property value="#row.count -1"/>, 'cursos');" id="profesor_<s:property value="#row.count -1"/>_<s:property value="curProfesores.trim().replace('/ /g','0')"/>" src="/intranetv7/images/local/icon/user.png" height="16" width="16" alt="prof"/>
                        </td>
                        <td>
                            <s:property value="curCupoIni"/>
                        </td>                             
                        <td <s:if test="curCupoDis < 0" >style="color:red"</s:if>>
                            <s:property value="curCupoDis"/>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
</form>