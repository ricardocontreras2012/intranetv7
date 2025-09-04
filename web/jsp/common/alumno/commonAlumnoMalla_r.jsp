<%--
    Document   : commonAlumnoMalla
    Created on : 08-05-2009, 08:07:14 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="infrastructure.util.common.CommonMallaUtil"%>
<%@ page import="session.GenericSession" %>
<%@ page import="infrastructure.support.MallaNodoSupport" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Malla Curricular del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-malla-3.0.0.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/alumno/commonAlumnoMalla-3.0.0.js"></script>
    </head>
    <body>
        <div class="">
            <div class="title-div-fixed">
                <s:text name="label.title.malla"/>&nbsp;<s:property
                    value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluRut"/>-<s:property
                    value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluDv"/>&nbsp;&nbsp;<s:property
                    value="#session.genericSession.getWorkSession(key).aluCar.alumno.nombre"/>
            </div>
            <div class="mt-5">
                <form id="malla-form" action="#">
                    <table class="table" style="font-size:11px;">
                        <thead>
                            <tr>
                                <%
                                    GenericSession genericSession = (GenericSession) session.getAttribute("genericSession");
                                    int mallaSize = genericSession.getMallaContainer().getMalla().size();
                                    for (int i = 0; i < mallaSize; i++) {
                                        if ("P".equals(genericSession.getMallaContainer().getMalla().get(i).get(0).getTipo())) {
                                            out.println(
                                                    "<th>NIVEL " + (i + 1) + "</th>");
                                        }
                                        if ("C".equals(genericSession.getMallaContainer().getMalla().get(i).get(0).getTipo())) {
                                            out.println(
                                                    "<th>COPRO</th>");
                                        }
                                        if ("A".equals(genericSession.getMallaContainer().getMalla().get(i).get(0).getTipo())) {
                                            out.println(
                                                    "<th>ADIC</th>");
                                        }
                                    }

                                    int maxFilas = CommonMallaUtil.getMaxLinea(genericSession.getMallaContainer().getMalla());
                                %>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 1; i <= maxFilas; i++) {
                                    out.println("<tr>");
                                    for (int j = 0; j < mallaSize; j++) {
                                        if (i <= CommonMallaUtil.getMaxLineaNivel(genericSession.getMallaContainer().getMalla(), j)) {
                                            MallaNodoSupport nodo = CommonMallaUtil.getNodo(genericSession.getMallaContainer().getMalla(), j, i);
                                            if (nodo != null) {
                                                if (nodo.getReprobaciones() > 0) {
                                                    out.println("<td align=\"center\">");
                                                    out.println("<div class=\"box\">");
                                                    out.println("<div class=\"codigo\">");
                                                    out.println("<table class=\"tableHead\"><tr><td style=\"text-align:left\">" + nodo.getCorrel() + "</td><td style=\"text-align:right\">");
                                                    if (!"A".equals(nodo.getTipo())) {
                                                        out.println("<a onclick=\"showNotas(" + nodo.getAsignatura() + ",'" + nodo.getNombre() + "','" + nodo.getElectiva() + "');\">" + nodo.getAsignatura() + "</a>");
                                                    } else {
                                                        out.println("<a onclick=\"showAdicionales(" + nodo.getAsignatura() + ",'" + nodo.getNombre() + "','" + nodo.getElectiva() + "')\">" + nodo.getAsignatura() + "</a>");
                                                    }
                                                    out.println("</td></tr></table>");
                                                    out.println("</div>");
                                                    out.println("<div class=\"nombre " + nodo.getEstado() + "\">");
                                                    out.println(nodo.getNombre());
                                                    out.println("</div>");
                                                    out.println("<div class=\"requisitos " + nodo.getEstado() + "\">");

                                                    if (nodo.getRequisitos().length() >= 15) {
                                                        out.println("Req: " + nodo.getRequisitos().substring(0, 11) + "<a onClick=\"showRequisitos(" + nodo.getAsignatura() + ",'" + nodo.getNombre() + "'); \">...</a>");
                                                        out.println("<input type=\"hidden\" id=\"asignatura_input" + nodo.getAsignatura() + "\" name=\"asignatura_input" + nodo.getAsignatura() + "\" value=\"" + nodo.getRequisitos() + "\">");
                                                    } else {
                                                        out.println("Req: " + nodo.getRequisitos());
                                                    }

                                                    out.println("</div>");
                                                    out.println("<div class=\"tel-reprobaciones " + nodo.getEstado() + "\">");
                                                    out.println("<table class=\"tableTelRepro\"><tr><td class=\"" + nodo.getEstado() + "\">" + nodo.getTelSct() + "</td><td style=\"width:18px\"><img src=\"/intranetv7/images/local/numbers/0" + nodo.getReprobaciones() + "RedNo_32X32.png\" alt=\"repro\" width=\"17\" height=\"18\"/></td></tr></table>");
                                                    out.println("</div>");
                                                    out.println("</div>");
                                                    out.println("</td>");
                                                } else {
                                                    out.println("<td align=\"center\">");
                                                    out.println("<div class=\"box\">");
                                                    out.println("<div class=\"codigo\">");
                                                    out.println("<table class=\"tableHead\"><tr><td style=\"text-align:left\">" + nodo.getCorrel() + "</td><td style=\"text-align:right\">");
                                                    if (!"A".equals(nodo.getTipo())) {
                                                        out.println("<a onclick=\"showNotas(" + nodo.getAsignatura() + ",'" + nodo.getNombre() + "','" + nodo.getElectiva() + "');\">" + nodo.getAsignatura() + "</a>");
                                                    } else {
                                                        out.println("<a onclick=\"showAdicionales(" + nodo.getAsignatura() + ",'" + nodo.getNombre() + "','" + nodo.getElectiva() + "')\">" + nodo.getAsignatura() + "</a>");
                                                    }
                                                    out.println("</td></tr></table>");
                                                    out.println("</div>");
                                                    out.println("<div class=\"nombre " + nodo.getEstado() + "\">");
                                                    out.println(nodo.getNombre());
                                                    out.println("</div>");
                                                    out.println("<div class=\"requisitos " + nodo.getEstado() + "\">");

                                                    if (nodo.getRequisitos().length() >= 15) {
                                                        out.println("Req: " + nodo.getRequisitos().substring(0, 11) + "<a onClick=\"showRequisitos(" + nodo.getAsignatura() + ",'" + nodo.getNombre() + "'); \">...</a>");
                                                        out.println("<input type=\"hidden\" id=\"asignatura_input" + nodo.getAsignatura() + "\" name=\"asignatura_input" + nodo.getAsignatura() + "\" value=\"" + nodo.getRequisitos() + "\">");
                                                    } else {
                                                        out.println("Req: " + nodo.getRequisitos());
                                                    }

                                                    out.println("</div>");
                                                    out.println("<div class=\"tel-reprobaciones " + nodo.getEstado() + "\">");
                                                    out.println(nodo.getTelSct());
                                                    out.println("</div>");
                                                    out.println("</div>");
                                                    out.println("</td>");
                                                }
                                            } else {
                                                out.println("<td></td>");
                                            }
                                        } else {
                                            out.println("<td></td>");
                                        }
                                    }
                                    out.println("</tr>");
                                }
                            %>
                        </tbody>
                    </table>
                    <!--img src="/Alumno/Cartola/Classes/simbologia.jpg" align=center-->
                    <div id="hidden-input-div">
                        <input type="hidden" id="asignatura" name="asignatura" value="<s:property value="asignatura"/>"/>
                        <input type="hidden" id="electiva" name="electiva" value="<s:property value="electiva"/>"/>
                        <input type="hidden" id="adicional" name="adicional" value="<s:property value="adicional"/>"/>
                        <input type="hidden" id="nombre" name="nombre" value="<s:property value="nombre"/>"/>
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    </div>
                </form>                    
                <s:if test="#session.genericSession.userType==\"AL\" && #session.genericSession.getWorkSession(key).aluCar.getParametros().getBloqueada() == \"SI\" "> 
                    <div class="modal fade" id="inscripcion" role="dialog">
                        <div class="modal-dialog" role="document">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Aviso</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Tu postulación de inscripción se encuentra actualmente en proceso. A partir del <s:date name="#session.genericSession.getWorkSession(key).aluCar.parametros.getTerminoFechaCorte()" format="dd/MM/yyyy HH:mm:ss"/>, podrás acceder al resultado del proceso. Te invitamos a estar atento(a) a la fecha indicada.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <script>
                        // Código para abrir el modal automáticamente al cargar la página
                        document.addEventListener('DOMContentLoaded', function () {
                            $('#inscripcion').modal('show');
                        });
                    </script>
                </s:if>
            </div>
        </div>

        <div class="modal fade" id="modal-notas" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div id="notas-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal-requisitos" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div id="requisitos-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div><
            </div>
        </div>
    </body>
</html>