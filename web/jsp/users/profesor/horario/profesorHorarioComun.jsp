<%--
    Document   : profesorHorarioComun
    Created on : 10-06-2009, 10:46:47 PM
    Author     : Ricardo Contreras S and Javier Frez V.
--%>
<%@page import="infrastructure.util.common.CommonSalaUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.IntStream"%>
<%@page import="domain.model.Dia"%>
<%@page import="com.google.gson.JsonParser"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="session.ProfesorSession"%>
<%@page import="domain.model.Profesor"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="infrastructure.util.common.CommonHorarioUtil"%>
<%@page import="domain.model.ModuloHorario"%>
<%@page import="session.WorkSession"%>
<%@ page import="session.GenericSession" %>
<%@ page import="infrastructure.util.ContextUtil" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Horario Común</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
    </head>

    <%
        String key = request.getParameter("key");
        GenericSession genericSession = (GenericSession) session.getAttribute("genericSession");
        ProfesorSession ps = (ProfesorSession) session.getAttribute("profesorSession");
    %>

    <s:if test="#session.genericSession.userType in {\"JC\"}">
        <div class="title-div">
            HORARIO COMÚN <s:property value="#session.genericSession.getWorkSession(key).curso.nombreCorto"/>
        </div>
    </s:if>

    <body class="inner-body">
        <form id="horario-form" action="#">
            <table class="table" style="margin-bottom: 0px;">
                <thead>
                    <tr class="header">
                        <th scope="col" style="width: 10%"></th>
                            <%
                                int nDias = ContextUtil.getDiaList().size();
                                for (int i = 0; i < nDias; i++) {
                                    out.println("<th scope=\"col\" style=\"width: 15%\">" + ContextUtil.getDiaList().get(i).getDiaNom().toUpperCase() + "</th>");
                                }
                            %>
                    </tr>
                </thead>
                <tbody>
                    <%
                        WorkSession ws = genericSession.getWorkSession(key);
                        List<ModuloHorario> modList = ws.getModuloHorarioList();

                        // Clase de soporte extendida
                        class HorarioComunSupport {

                            private int catedra = 0;
                            private int ayudantia = 0;

                            public void setCatedra(int c) {
                                this.catedra = c;
                            }

                            public void setAyudantia(int a) {
                                this.ayudantia = a;
                            }

                            public int getCatedra() {
                                return this.catedra;
                            }

                            public int getAyudantia() {
                                return this.ayudantia;
                            }

                            public boolean isOcupado() {
                                return catedra > 0 || ayudantia > 0;
                            }
                        }

                        HorarioComunSupport[][] horarioMatrix = new HorarioComunSupport[modList.size()][nDias];

                        // Inicializar matriz
                        for (int i = 0; i < modList.size(); i++) {
                            for (int j = 0; j < nDias; j++) {
                                horarioMatrix[i][j] = new HorarioComunSupport();
                            }
                        }

                        // Mapeo días
                        List<Dia> dias = ContextUtil.getDiaList();
                        Map<String, Integer> diaMap = new HashMap<>();
                        for (int i = 1; i <= dias.size(); i++) {
                            diaMap.put(dias.get(i - 1).getDiaCod().toUpperCase(), i);
                        }

                        // Leer y parsear JSON
                        String horarioJson = ps.getHorarioComun();
                        JsonArray jsonArray = JsonParser.parseString(horarioJson).getAsJsonArray();

                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonObject entrada = jsonArray.get(i).getAsJsonObject();

                            String dia = entrada.get("dia").getAsString().toUpperCase();
                            int modulo = Integer.parseInt(entrada.get("modulo").getAsString());
                            int catedra = entrada.get("catedra").getAsInt();
                            int ayudantia = entrada.get("ayudantia").getAsInt();

                            Integer columna = diaMap.get(dia);
                            int fila = modulo - 1;

                            if (columna != null && fila >= 0 && fila < modList.size()) {
                                HorarioComunSupport celda = horarioMatrix[fila][columna - 1];
                                celda.setCatedra(catedra);
                                celda.setAyudantia(ayudantia);
                            }
                        }

                        for (int i = 0; i < modList.size(); i++) {
                            int pos = CommonSalaUtil.getPos(modList, i);
                            out.println("<tr style=\"height:60px\">");
                            out.println("<td style=\"text-align:center; vertical-align:middle\">"
                                    + modList.get(pos).getModDesde() + " " + modList.get(pos).getModHasta() + "</td>");
                            for (int j = 0; j < nDias; j++) {
                                HorarioComunSupport celda = horarioMatrix[pos][j];
                                if (celda.isOcupado()) {
                                    //out.println("<td style=\"background-color:#ff7961; text-align:center; vertical-align:middle; font-weight:bold\">");
                                    out.println("<td style=\"background-color:#ff7961; color: white; text-align:center; vertical-align:middle; font-weight:bold; font-size: 14px;\">");
                                    out.println("C: " + celda.getCatedra() + "<br/>A: " + celda.getAyudantia());
                                } else {
                                    out.println("<td style=\"background-color:#33cc66\">");
                                }
                                out.println("</td>");
                            }
                            out.println("</tr>");
                        }
                    %>
                </tbody>
            </table>
            <p></p>
            <p></p>

            <table border="1" cellspacing="0" cellpadding="10">
                <tr>
                    <td style="background-color: #ff7961; width: 100px; color: white; font-weight: bold; text-align: center;">C:<br>A:</td>
                    <td>
                        Horario ocupado por los alumnos del curso.<br>
                        <strong>C</strong>: Cantidad de alumnos con Cátedra en ese módulo.<br>
                        <strong>A</strong>: Cantidad de alumnos con Ayudantía en ese módulo.
                    </td>
                </tr>
                <tr>
                    <td style="background-color: #33cc66; width: 100px;"></td>
                    <td>Horario disponible</td>
                </tr>
            </table>

            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                <input type="hidden" id="id" name="id" value="<s:property value="id"/>"/>
            </div>
        </form>
    </body>
</html>