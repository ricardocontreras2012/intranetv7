<%--
    Document   : teleTrabajoAsignacionGetHorario
    Created on : 11-09-2023, 04:04:31 PM
    Author     : Felipe
--%>
<%@page import="java.util.Map"%>
<%@page import="infrastructure.support.TeleTrabajoSupport"%>
<%@page import="session.Manager"%>
<%@ page import="java.util.List" %>
<%@ page import="infrastructure.util.SystemParametersUtil" %>
<%@ page import="infrastructure.util.DateUtil" %>
<%@ page import="domain.model.ModuloHorario" %>
<%@ page import="infrastructure.util.common.CommonColorUtil" %>
<%@ page import="infrastructure.util.common.CommonHorarioUtil" %>
<%@ page import="session.WorkSession" %>
<%@ page import="session.TeleTrabajoSession" %>
<%@ page import="session.GenericSession" %>
<%@ page import="infrastructure.util.ContextUtil" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<link rel="stylesheet" href="/intranetv7/css/local/local-simple.css" type="text/css" />
<script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/asignacion/teleTrabajoAsignacionGetHorario-1.0.0.js"></script>
<script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
<%
    String inicio = request.getParameter("inicio");
    String termino = request.getParameter("termino");
    int inicioPos = Integer.parseInt(request.getParameter("inicioPos"));
    int terminoPos = Integer.parseInt(request.getParameter("terminoPos"));

    TeleTrabajoSession teleSession =(TeleTrabajoSession) session.getAttribute("teleTrabajoSession");

    int nDias = teleSession.getHorarioFuncionario().length;
    int nSemanas = nDias/7;
%>

<div class="title-div">
    Teletrabajo <%=teleSession.getFuncionarioTeletrabajo().getFtelRut() + " (" + inicio + " al " + termino + ')'%>
</div>

<div class="table-responsive">
    <table id="horario" class="table table-bordered">
        <thead>
            <tr class="header">
                <th scope="col" style="width: 70px"></th>
                    <%
                        TeleTrabajoSupport[] horario = teleSession.getHorarioFuncionario();
                        for (int i = (inicioPos-1); i < (nDias + terminoPos -7); i++) {
                            out.println("<th style=\"width:50px\" scope=\"col\">" + DateUtil.getFormattedDate(horario[i].getFecha(), SystemParametersUtil.DATE_FORMAT) + "</th>");
                        }
                    %>
            </tr>
        </thead>
        <tbody>
            <%
                int flag;
                int count = 0;
                int ini = (inicioPos-1);
                int ter = (nDias + terminoPos -7);
                int pos = 0;
                out.println("<tr>");
                out.println("<td id=\"h_" + pos + "\" style=\"color:darkblue; background-color:#c4c4c4; text-align:center;padding:2px;width:100px%\">" + "Día" + "</td>");
                for (int i = 0; i < nSemanas; i++) {
                    flag = 0;
                    for (int j = (7*i); j < (7*(i+1)); j++) {
                        TeleTrabajoSupport support = horario[j];
                        if (support != null) {
                            if (support.getEstado() != null) {
                                flag = 1;
                            }
                        }
                    }
                    if (i == 0 && i == (nSemanas -1)) {
                        for (int j = ini; j < ter; j++) {
                            TeleTrabajoSupport support = horario[j];
                            if (support != null) {
                                if (support.getEstado() != null) {
                                    out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:#eee\">" + "<a onclick=showActividad('" + DateUtil.getFormattedDate(support.getFecha(), SystemParametersUtil.DATE_FORMAT) + "')>" + support.getEstado() + "</a></td>");
                                } else {
                                    if (flag == 1 && !teleSession.getFuncionarioTeletrabajo().getFtelType().equals("S")){
                                        out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:red\">");
                                    } else {
                                        out.println("<td id='c_" + pos + "_" + count + "' class=\"asignar\" " + "onmouseover=\"ChangeBackgroundColor(this)\" onmouseout=\"RestoreBackgroundColor(this)\"" + ">");
                                    }   
                                }
                            } else {
                                if (flag == 1 && !teleSession.getFuncionarioTeletrabajo().getFtelType().equals("S")){
                                    out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:red\">");
                                } else {
                                    out.println("<td id='c_" + pos + "_" + count + "' class=\"asignar\" " + "onmouseover=\"ChangeBackgroundColor(this)\" onmouseout=\"RestoreBackgroundColor(this)\"" + ">");
                                }  
                            }
                            count = count+1;
                            out.println("</td>");
                        }
                    } else if (i == 0 && i != (nSemanas -1)) {
                        for (int j = ini; j < (7*(i+1)); j++) {
                            TeleTrabajoSupport support = horario[j];
                            if (support != null) {
                                if (support.getEstado() != null) {
                                    out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:#eee\">" + "<a onclick=showActividad('" + DateUtil.getFormattedDate(support.getFecha(), SystemParametersUtil.DATE_FORMAT) + "')>" + support.getEstado() + "</a></td>");
                                } else {
                                    if (flag == 1 && !teleSession.getFuncionarioTeletrabajo().getFtelType().equals("S")){
                                        out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:red\">");
                                    } else {
                                        out.println("<td id='c_" + pos + "_" + count + "' class=\"asignar\" " + "onmouseover=\"ChangeBackgroundColor(this)\" onmouseout=\"RestoreBackgroundColor(this)\"" + ">");
                                    }   
                                }
                            } else {
                                if (flag == 1 && !teleSession.getFuncionarioTeletrabajo().getFtelType().equals("S")){
                                    out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:red\">");
                                } else {
                                    out.println("<td id='c_" + pos + "_" + count + "' class=\"asignar\" " + "onmouseover=\"ChangeBackgroundColor(this)\" onmouseout=\"RestoreBackgroundColor(this)\"" + ">");
                                }  
                            }
                            count = count+1;
                            out.println("</td>");
                        }
                    } else if (i != 0 && i == (nSemanas -1)) {
                        for (int j = (7*i); j < ter; j++) {
                            TeleTrabajoSupport support = horario[j];
                            if (support != null) {
                                if (support.getEstado() != null) {
                                    out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:#eee\">" + "<a onclick=showActividad('" + DateUtil.getFormattedDate(support.getFecha(), SystemParametersUtil.DATE_FORMAT) + "')>" + support.getEstado() + "</a></td>");
                                } else {
                                    if (flag == 1 && !teleSession.getFuncionarioTeletrabajo().getFtelType().equals("S")){
                                        out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:red\">");
                                    } else {
                                        out.println("<td id='c_" + pos + "_" + count + "' class=\"asignar\" " + "onmouseover=\"ChangeBackgroundColor(this)\" onmouseout=\"RestoreBackgroundColor(this)\"" + ">");
                                    }   
                                }
                            } else {
                                if (flag == 1 && !teleSession.getFuncionarioTeletrabajo().getFtelType().equals("S")){
                                    out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:red\">");
                                } else {
                                    out.println("<td id='c_" + pos + "_" + count + "' class=\"asignar\" " + "onmouseover=\"ChangeBackgroundColor(this)\" onmouseout=\"RestoreBackgroundColor(this)\"" + ">");
                                }  
                            }
                            count = count+1;
                            out.println("</td>");
                        }
                    } else {
                        for (int j = (7*i); j < (7*(i+1)); j++) {
                            TeleTrabajoSupport support = horario[j];
                            if (support != null) {
                                if (support.getEstado() != null) {
                                    out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:#eee\">" + "<a onclick=showActividad('" + DateUtil.getFormattedDate(support.getFecha(), SystemParametersUtil.DATE_FORMAT) + "')>" + support.getEstado() + "</a></td>");
                                } else {
                                    if (flag == 1 && !teleSession.getFuncionarioTeletrabajo().getFtelType().equals("S")){
                                        out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:red\">");
                                    } else {
                                        out.println("<td id='c_" + pos + "_" + count + "' class=\"asignar\" " + "onmouseover=\"ChangeBackgroundColor(this)\" onmouseout=\"RestoreBackgroundColor(this)\"" + ">");
                                    }   
                                }
                            } else {
                                if (flag == 1 && !teleSession.getFuncionarioTeletrabajo().getFtelType().equals("S")){
                                    out.println("<td id='c_" + pos + "_" + count + "' style=\"background-color:red\">");
                                } else {
                                    out.println("<td id='c_" + pos + "_" + count + "' class=\"asignar\" " + "onmouseover=\"ChangeBackgroundColor(this)\" onmouseout=\"RestoreBackgroundColor(this)\"" + ">");
                                }  
                            }
                            count = count+1;
                            out.println("</td>");
                        }
                    }    
                }
                out.println("</tr>");
            %>
        </tbody>        
    </table>
</div>