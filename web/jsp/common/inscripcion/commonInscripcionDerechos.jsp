<%--
    Document   : commonInscripcionDerechos
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
        <script type="text/javascript" src="/intranetv7/js/local/common/inscripcion/commonInscripcionDerechos-3.0.1.js"></script>
    </head>
    <body class="inner-body">        
        <form id="derechos-form" action="#" method="post">
            <table class="table table-sm" style="width: 97%">
                <thead>
                    <tr class="header-table">
                        <th colspan="2"><s:text name="label.asignatura"/></th>
                        <th scope="col"><s:text name="label.creditos"/></th>
                        <th scope="col">&nbsp;<s:text name="label.sct"/></th>
                        <th scope="col"><s:text name="label.nivel"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.derechosCoordinadorInscripcion" status="row">
                        <tr class="<s:if test="derReq==0">conRequisito</s:if><s:else>sinRequisito</s:else>">
                                <td>
                                        <a style="color:white;text-align: right" onClick="searchCursos(<s:property value="#row.count -1"/>);"
                                   id="derecho_<s:property value="#row.count -1"/>"><s:property value="derAsign"/></a>
                            </td>
                            <td style="color:white;">
                                <s:property value="derNom"/>
                            </td>
                            <td style="color:white">
                                <s:property value="derCred"/>
                            </td>
                            <td style="text-align: center">
                                <s:property value="derSct"/>
                            </td>
                            <td style="color:white">
                                <s:property value="derNivel"/>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" />
            </div>
        </form>
    </body>
</html>
