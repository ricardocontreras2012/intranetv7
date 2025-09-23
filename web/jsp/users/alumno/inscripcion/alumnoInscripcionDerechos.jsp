<%--
    Document   : alumnoInscripcionDerechos
    Created on : 28-04-2010, 10:15:58 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Derechos de Inscripción</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-inscripcion.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/inscripcion/alumnoInscripcionDerechos-3.0.3.js"></script>
    </head>
    <body class="inner-body">        
        <form id="derechos-form" action="#" method="post">
            <!-- OOOOOOOOJJJJOOOOO -->
            <%--s:if test="#session.genericSession.getWorkSession(key).aluCar.parametros.puedeInscribirMalla==\"SI\" || #session.genericSession.getWorkSession(key).aluCar.parametros.puedeModificar==\"SI\"" --%>
                <table class="table table-sm table-striped" style="width: 97%">
                    <thead>
                        <tr style="text-align: center; background-color: #CCCCCC">
                            <th colspan="2"><s:text name="label.asignatura"/></th>
                            <th scope="col"><s:text name="label.creditos"/></th>
                            <th scope="col">&nbsp;<s:text name="label.sct"/></th>
                            <th scope="col">&nbsp;<s:text name="label.nivel"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.derechosInscripcion" status="row">
                            <%--tr--%>
                            <s:if test="derTipo==1">
                                <tr class="<s:if test="#row.odd">odd</s:if><s:else>even</s:else>">
                                        <td style="text-align: right">
                                                <a onClick="searchCursos(<s:property value="#row.count -1"/>);" class="link"
                                           id="derecho_<s:property value="#row.count -1"/>"><s:property value="asignatura.asiCod"/></a>
                                    </td>
                                    <td>
                                        &nbsp;<s:property value="asignatura.asiNom"/>
                                    </td>
                                    <td style="text-align: center">
                                        <s:property value="derCred"/>
                                    </td>
                                    <td style="text-align: center">
                                        <s:property value="asignatura.asiSct"/>
                                    </td>
                                    <td style="text-align: center">
                                        <s:property value="derNiv"/>
                                    </td>
                                </tr>
                            </s:if>
                        </s:iterator>
                    </tbody>
                </table>
            <%--/s:if --%>
            
            <s:if test="#session.genericSession.getWorkSession(key).aluCar.parametros.puedeInscribirFormacionIntegral==\"SI\"">
                <table class="table table-striped" style="width: 97%">
                    <thead>
                        <tr style="text-align: center; background-color: #CCCCCC">
                            <th colspan="2"><s:text name="label.asignatura"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.derechosInscripcion" status="row">
                            <%--tr--%>
                            <s:if test="derTipo==3">
                                <tr class="<s:if test="#row.odd">odd</s:if><s:else>even</s:else>">
                                        <td style="text-align: right">
                                                <a onClick="searchCursos(<s:property value="#row.count -1"/>);" class="link"
                                           id="derecho_<s:property value="#row.count -1"/>"><s:property value="asignatura.asiCod"/></a>
                                    </td>
                                    <td>
                                        &nbsp;<s:property value="asignatura.asiNom"/>
                                    </td>
                                </tr>
                            </s:if>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" />
            </div>
        </form>
    </body>
</html>
