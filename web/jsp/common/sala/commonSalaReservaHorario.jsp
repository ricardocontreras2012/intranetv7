<%--
    Document   : commonSalaReservaHorario
    Created on : 29-11-2010, 01:04:31 PM
    Author     : Ricardo Contreras S.
--%>
<%@ page import="java.util.List" %>
<%@ page import="infrastructure.support.ReservaSalaSupport" %>
<%@ page import="infrastructure.util.SystemParametersUtil" %>
<%@ page import="infrastructure.util.DateUtil" %>
<%@ page import="infrastructure.util.common.CommonSalaUtil" %>
<%@ page import="domain.model.ModuloHorario" %>
<%@ page import="infrastructure.util.common.CommonColorUtil" %>
<%@ page import="infrastructure.util.common.CommonHorarioUtil" %>
<%@ page import="infrastructure.util.common.CommonCursoUtil" %>
<%@ page import="session.WorkSession" %>
<%@ page import="domain.model.Horario" %>
<%@ page import="domain.model.Curso" %>
<%@ page import="domain.model.Sala" %>
<%@ page import="session.GenericSession" %>
<%@ page import="infrastructure.util.ContextUtil" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<link rel="stylesheet" href="/intranetv7/css/local/local-simple.css" type="text/css" />
<script type="text/javascript" src="/intranetv7/js/local/common/sala/commonSalaReservaHorario-3.0.1.js"></script>
<script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
<%         
    String key = request.getParameter("key");
    String inicio = request.getParameter("inicio");
    String termino = request.getParameter("termino");

    GenericSession genericSession = (GenericSession) session.getAttribute("genericSession");
    WorkSession workSession = genericSession.getWorkSession(key);
    List<ModuloHorario> modList = workSession.getModuloHorarioList();

    Sala sala = workSession.getSala();
    int modulos = workSession.getHorarioSala().length;
    int nDias = workSession.getHorarioSala()[0].length;
%>

<div class="title-div">
    <s:text name="label.title.sala"/> <%=sala.getSalaNum() + " (" + inicio + " al " + termino + ')'%>
</div>

<div class="table-responsive">
    <table id="horario" class="table table-bordered">
        <thead>
            <tr class="header">
                <th scope="col" style="width: 70px"></th>
                    <%
                        ReservaSalaSupport[][] horarioMatrix = workSession.getHorarioSala();
                        for (int i = 0; i < nDias; i++) {
                            out.println("<th style=\"width:50px\" scope=\"col\">" + DateUtil.getFormattedDate(horarioMatrix[0][i].getFecha(), SystemParametersUtil.DATE_FORMAT) + "</th>");
                        }
                    %>
            </tr>
        </thead>
        <tbody>
            <%
                for (int i = 0; i < modulos; i++) {
                    int pos = CommonSalaUtil.getPos(modList,i);
                    out.println("<tr>");
                    out.println("<td id=\"h_" + pos + "\" style=\"color:darkblue; background-color:#c4c4c4; text-align:center;padding:2px;width:100px%\">" + modList.get(i).getModDesde() + ' ' + modList.get(i).getModHasta() + "</td>");

                    for (int j = 0; j < nDias; j++) {
                        ReservaSalaSupport reserva = horarioMatrix[pos][j];

                        if (reserva != null) {
                           if (reserva.getCurso() != null) {
                                out.println("<td id='c_" + pos + "_" + j + "' style=\"background-color:#" + reserva.getColor() + ";\"><a style=\"color:" + CommonColorUtil.getTextColor(reserva.getColor()) + "\"onclick=showCurso('" + reserva.getCurso() + "')>" + CommonCursoUtil.getCodigo(reserva.getCurso()) + "</a></td>");
                            } else {
                                if (reserva.getReserva() != null) {
                                    out.println("<td id='c_" + pos + "_" + j + "'>" + "<a onclick=showReserva('" + reserva.getReserva() + "')>" + reserva.getReserva() + "</a></td>");
                                } else {
                                    out.println("<td id='c_" + pos + "_" + j + "' style=\"background-color:#eee\">");
                                }
                            }

                        } else {
                            out.println("<td id='c_" + pos + "_" + j + "' style=\"background-color:#eee\">");
                        }

                        out.println("</td>");
                    }
                    out.println("</tr>");

                }
            %>
        </tbody>        
    </table>
</div>