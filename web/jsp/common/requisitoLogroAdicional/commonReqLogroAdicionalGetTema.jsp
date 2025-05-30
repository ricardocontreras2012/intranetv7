<%--
    Document   : commonReqLogroAdicionalGetTema
    Created on : 09-abr-2012, 16:00:24
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Tema de la Inscripción Adicional</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datepicker/jquery-ui-1.8.12.custom.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.datepicker-es-3.0.0.min.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/common/requisitoAdicionalTitulacion/commonReqLogroAdicionalGetTema-2.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.inscripcion.adicional"/> <s:property
                value="#session.genericSession.getWorkSession(key).trequisitoLogroAdicional.tralDes"/> <s:property
                value="#session.genericSession.getWorkSession(key).semAct"/>/<s:property
                value="#session.genericSession.getWorkSession(key).agnoAct"/>
        </div>

        <div id="central-div">
            <div class="buttons-div">
                <button id="next-button" name="next-button" class="positive">
                    <img src="/intranetv7/images/local/icon/next.png" height="16" width="16" alt="add" title="Iniciar la Búsqueda"/>
                    <s:text name="label.button.next"/>
                </button>
            </div>
            <div id="content-div">
                <form id="inscripcion-form" action="#" method="post">
                    <div>
                        <table>
                            <tr>
                                <td><s:text name="label.date"/></td>
                                <td><label for="fecha"></label><input id="fecha" name="fecha" size="10"
                                                                      value="<s:date name="#session.genericSession.getWorkSession(key).InscripcionAdicionalLogro.iratFecha" format="dd/MM/yyyy"/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="label.tema"/></td>
                                <td><label for="tema"></label><textarea id="tema" name="tema" cols="50" rows="10"><s:property
                                            value="#session.genericSession.getWorkSession(key).InscripcionAdicionalLogro.iratGlosa"/></textarea>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="trequisitoLogroAdicional" name="trequisitoLogroAdicional"
                       value="<s:property value="#session.genericSession.getWorkSession(key).trequisitoLogroAdicional.tralCod"/>"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
