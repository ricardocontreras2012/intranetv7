<%--
    Document   : commonHorario
    Created on : 10-06-2009, 10:46:47 PM
    Author     : Ricardo Contreras S and Javier Frez V.
--%>
<%@page import="infrastructure.util.common.CommonHorarioUtil"%>
<%@page import="domain.model.ModuloHorario"%>
<%@page import="session.WorkSession"%>
<%@page import="infrastructure.util.common.CommonSalaUtil"%>
<%@ page import="domain.model.Curso" %>
<%@ page import="domain.model.Horario" %>
<%@ page import="infrastructure.util.common.CommonCursoUtil" %>
<%@ page import="session.GenericSession" %>
<%@ page import="infrastructure.util.ContextUtil" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Horario de Mis Cursos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/horario/commonHorario-3.0.3.js"></script>
    </head>
    <%
        String key = request.getParameter("key");
        GenericSession genericSession = (GenericSession) session.getAttribute("genericSession");
    %>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.horario"/> <%=genericSession.getWorkSession(key).getNombre()%>
        </div>

        <div class="btn-group">
            <s:if test="#session.genericSession.rut==#session.genericSession.getWorkSession(key).aluCar.alumno.aluRut">
                <button id="print-button" title="Print" type="button" class="btn btn-light" >
                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                </button>
            </s:if>
            <s:if test="#session.genericSession.rut==#session.genericSession.getWorkSession(key).profesor.profRut">
                <button id="print-button" title="Print" type="button" class="btn btn-light" >
                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                </button>
            </s:if>
            <s:if test="#session.genericSession.rut==#session.genericSession.getWorkSession(key).ayudante.ayuRut">
                <button id="print-button" title="Print" type="button" class="btn btn-light" >
                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                </button>
            </s:if>
            <s:if test="#session.genericSession.userType in {\"JC\",\"OC\",\"RC\",\"SM\",\"SD\",\"DD\",\"DE\",\"VRA\",\"RE\"}">
                <button id="print-button" title="Print" type="button" class="btn btn-light" >
                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                </button>
            </s:if>
        </div>

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
                        String[] colorCurso = {
                            "#99FFFF",
                            "#99FF99",
                            "#FFFF99",
                            "#FFCC99",
                            "#FFCCFF",
                            "#00CCFF",
                            "#00CC99",
                            "#0099FF",
                            "#339999",
                            "#99CC33"};

                        WorkSession ws = genericSession.getWorkSession(key);
                        List<Curso> carga = CommonHorarioUtil.getCarga(ws);
                        Horario[][] horarioMatrix = ws.getHorario();
                        List<ModuloHorario> modList = ws.getModuloHorarioList();
                        
                        int nCarga = carga.size();
                                              
                        int listSize = modList.size();                        
                        for (int i = 0; i < listSize; i++) {
                            int pos = CommonSalaUtil.getPos(modList, i);
                                                          
                            out.println("<tr style=\"height:60px\">");
                            out.println("<td style=\"text-align:center; vertical-align:middle\">" + modList.get(pos).getModDesde() + ' ' + modList.get(pos).getModHasta() + "</td>");
                                                        
                            for (int j = 0; j < nDias; j++) {
                                Horario horario = horarioMatrix[pos][j];
                                if (horario != null) {                                                                         
                                    int k;
                                    
                                    Curso curso = null;
                                    for (k = 0; k < nCarga; k++) {                                                                               
                                        //if (CommonCursoUtil.iguales(carga.get(k), horario.getCurso(), genericSession.getWorkSession(key).getCargaEspejo())) {
                                        if (CommonCursoUtil.equals(carga.get(k).getId(), horario.getCurso().getId())) {
                                            curso = carga.get(k);

                                            break;
                                        }
                                    }

                                    out.println("<td style=\"background-color:" + colorCurso[k % 10] + "\">");

                                    if (horario.getSala() != null) {
                                        out.println("<a style=\"color:#444444\" onclick=\"showSala('"
                                                + ContextUtil.getDiaList().get(j).getDiaNom() + '-' + modList.get(i).getId().getModCod() + "','" + horario.getSala().getSalaNum() + "','" + curso.getNombreCorto() + "','" + curso.getCurProfesores() + "');\">" + curso.getNombreHorario() + "</a>");

                                    } else {   
                                        out.println("<a style=\"color:#444444\" onclick=\"showSala('" + ContextUtil.getDiaList().get(j).getDiaNom() + '-' + modList.get(i).getId().getModCod() + "',' ','" + curso.getNombreCorto() + "','" + curso.getCurProfesores() + "');\">" + curso.getNombreHorario() + "</a>");
                                    }

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
            <table style="margin-bottom: 20px;">
                <tr>
                    <td colspan="7" style="height: 20px;text-align: center; text-transform: capitalize; color: white"><s:text name="label.menu.mis.cursos"/></td>
                </tr>
                <%                    
                    for (int k = 0; k < nCarga; k++) {
                        out.println("<tr style=\"height: 20px; width:100%\">");
                        out.println("<td colspan=\"7\" style=\"height: 20px; width:100%;background-color:" + colorCurso[k % 10] + "\">" + carga.get(k).getNombreFull() + "</td>");
                        out.println("</tr>");
                    }
                %>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                <input type="hidden" id="id" name="id" value="<s:property value="id"/>"/>
            </div>
        </form>

        <div class="modal fade" id="sala" role="dialog">
            <div class="modal-dialog  modal-md" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">DETALLE HORARIO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="sala-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>        
    </body>    
</html>