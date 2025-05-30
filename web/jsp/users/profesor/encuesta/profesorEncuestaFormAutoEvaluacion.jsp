<%--
    Document   : profesorEncuestaFormAutoEvaluacion
    Created on : 03-09-2013, 13:05:12 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">      
        <title>Formulario de Respuesta de Autoevaluación Docente</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        
        <link rel="stylesheet" href="/intranetv7/css/local/local-encuesta.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/autoevaluacion/profesorEncuestaFormAutoEvaluacion-3.0.3.js"></script>
    </head>
    <body class="inner-body">
        <div id="title-div" class="middle-top" style="background-color:#F7EDD8;height: 20px">
            <s:text name="label.title.encuesta.docente"/> &nbsp;<s:property
                value="#session.genericSession.getWorkSession(key).getCursosAutoEvaluacion().get(0).getCurso().getNombreFull()"/>
        </div>

        <div id="central-div" style="width: 90%; padding: 20px;">
            <div id="content-div">
                <form id="encuesta-form" accept-charset="UTF-8" action="#" method="post">
                    <p><b>Opciones de respuesta:</b></p>
                    <p>1: Muy en desacuerdo, 2: En desacuerdo, 3: Ni acuerdo ni desacuerdo, 4: De acuerdo, 5: Muy de acuerdo, NA: No aplica o No corresponde</p>
                    <p>Marca solo una respuesta:</p>

                    <table border="1" cellpadding="0" cellspacing="0" class="table table-striped">
                        <tbody>

                            <tr>
                                <td class="dimension"><p><b></b></p></td>
                                <td class="respuesta"><p><b>1</b></p></td>
                                <td class="respuesta"><p><b>2</b></p></td>
                                <td class="respuesta"><p><b>3</b></p></td>
                                <td class="respuesta"><p><b>4</b></p></td>
                                <td class="respuesta"><p><b>5</b></p></td>
                                <td class="respuesta"><p><b>NA</b></p></td>
                            </tr>
                            <s:iterator value="#session.genericSession.getWorkSession(key).preguntasAutoevaluacionList" status="row">
                                <tr>
                                    <td><s:property value="id.padPreg"/>.&nbsp;<s:property value="padDes"/></td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="1"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="2"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="3"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="4"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="5"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="0"/> </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>

                    <p><b>PREGUNTAS ABIERTAS</b></p>

                    <div style="width: 99%">
                        <p>- 1.	Señale brevemente algunos aspectos que considere positivos en el desarrollo de la asignatura.</p><label for="comentarioPositivo"></label><textarea
                            id="comentarioPositivo" name="comentarioPositivo"
                            cols="100" rows="5" maxlength="500"></textarea>&nbsp;&nbsp;
                    </div>
                    <div style="width: 99%">
                        <p>- 2.Señale brevemente algunos aspectos que considere que podrían mejorar en el desarrollo de la asignatura.</p><label for="comentarioMejora"></label><textarea
                            id="comentarioMejora" name="comentarioMejora"
                            cols="100" rows="5" maxlength="500"></textarea>&nbsp;&nbsp;
                    </div>
                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        <input type="hidden" id="nPregDummy" name="nPregDummy"
                               value="<s:property value="#session.genericSession.getWorkSession(key).preguntasEncuesta.size"/>"/>
                        <input type="hidden" id="carreraDummy" name="carreraDummy"
                               value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaCodCar"/>"/>
                    </div>
                </form>

                <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                    &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                </button>

                <div class="modal fade" id="msg" tabindex="-1" role="dialog" aria-labelledby="msgModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="msgModalLabel">Error</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div id="msg-div"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>