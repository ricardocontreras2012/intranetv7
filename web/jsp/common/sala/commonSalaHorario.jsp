<%--
    Document   : commonSalaHorario
    Created on : 29-11-2010, 01:04:31 PM
    Author     : Ricardo Contreras S.
--%>
<%@page import="java.util.List"%>
<%@page import="domain.model.ModuloHorario"%>
<%@page import="session.WorkSession"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="domain.model.Horario" %>
<%@ page import="domain.model.Sala" %>
<%@ page import="session.GenericSession" %>
<%@ page import="infrastructure.util.ContextUtil" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
<script type="text/javascript" src="/intranetv7/js/local/common/sala/commonSalaHorario-2.0.0.js"></script>
<%
    String key = request.getParameter("key");
    String agno = request.getParameter("agno");
    String sem = request.getParameter("sem");
    GenericSession genericSession = (GenericSession) session.getAttribute("genericSession");
    Sala sala = genericSession.getWorkSession(key).getSala();
%>

<div class="title-div">
    <s:text name="label.title.sala"/> <%=sala.getSalaNum() + " (" + sem + "/" + agno + ")"%>
</div>

<button id="print-button" title="Imprimir" type="button" class="btn btn-light" ><span class="fa fa-print" aria-hidden="true"></span>
    &nbsp; <span class="hidden-xs"><s:text name="label.button.print"/></span>
</button>

<table>
    <thead>
        <tr class="header">
            <th scope="col" style="width: 10%"></th>
                <%
                    int nDias = ContextUtil.getDiaList().size();
                    for (int i = 0; i < nDias; i++) {
                        out.println("<th style=\"width: 15%\" scope=\"col\">" + ContextUtil.getDiaList().get(i).getDiaNom().toUpperCase() + "</th>");
                    }
                %>
        </tr>
    </thead>
    <tbody>
        <%
            WorkSession workSession = genericSession.getWorkSession(key);
            List<ModuloHorario> modList = workSession.getModuloHorarioList();
            Horario[][] horarioMatrix = workSession.getHorario();
            int listSize = modList.size();
            for (int i = 0; i < listSize; i++) {
                out.println("<tr>");
                out.println("<td style=\"color:darkblue; background-color:#c4c4c4; text-align:center;padding:2px;width:10%\">" + modList.get(i).getModDesde() + ' ' + modList.get(i).getModHasta() + "</td>");
                for (int j = 0; j < nDias; j++) {
                    Horario horario = horarioMatrix[i][j];

                    if (horario != null) {
                        out.println("<td style=\"background-color:#" + horario.getColorHexPorAsignatura()+ "\">");
                        out.println(horario.getCurso().getNombreHorario());

                    } else {
                        out.println("<td style=\"background-color:#eee\">");
                    }
                    out.println("</td>");
                }
                out.println("</tr>");
            }
        %>
    </tbody>
</table>