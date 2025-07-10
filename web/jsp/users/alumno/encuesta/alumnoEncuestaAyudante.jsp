<%--
    Document   : alumnoEncuestaAyudante
    Created on : 24-12-2014, 09:34:45 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Respuesta de Encuesta Ayudante</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/encuesta/alumnoEncuestaAyudante-3.0.0.js"></script>
        <style>
            .row{
                margin-top:40px;
                padding: 0 10px;
            }

            .clickable{
                cursor: pointer;
            }

            .panel-heading span {
                margin-top: -20px;
                font-size: 15px;
            }
        </style>
        <script>
            $(document).on('click', '.panel-heading span.clickable', function (e) {
                const $this = $(this);
                if (!$this.hasClass('panel-collapsed')) {
                    $this.parents('.panel').find('.panel-body').slideUp();
                    $this.addClass('panel-collapsed');
                    $this.find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
                } else {
                    $this.parents('.panel').find('.panel-body').slideDown();
                    $this.removeClass('panel-collapsed');
                    $this.find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
                }
            })
        </script>
    </head>
    <body class="inner-body">
        <div id="title-div" class="middle-top" style="background-color:#F7EDD8;height: 20px">
            <s:text name="label.title.encuesta.ayudante"/> <s:property
                value="#session.genericSession.getWorkSession(key).getCursoAyudante().getCurso().getNombreFull()"/> (<s:property
                value="#session.genericSession.getWorkSession(key).getCursoAyudante().getAyudante().getNombre()"/>)
        </div>
        <div style="width: 95%">
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title"><s:text name="message.title.escala"/></h3>
                            <span class="pull-right clickable"><i class="glyphicon glyphicon-chevron-up"></i></span>
                        </div>
                        <div class="panel-body" style="display: block;">
                            <table>
                                <tr>
                                    <td>1. Muy en desacuerdo</td>
                                </tr>
                                <tr>
                                    <td>2. En desacuerdo</td>
                                </tr>
                                <tr>
                                    <td>3. Ni acuerdo, ni desacuerdo</td>
                                </tr>
                                <tr>
                                    <td>4. De acuerdo</td>
                                </tr>
                                <tr>
                                    <td>5. Muy de acuerdo</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div id="central-div" style="width: 90%; padding: 20px;">
            <div id="content-div">
                <form id="encuesta-form" accept-charset="UTF-8" action="#" method="post">
                    <p><b>Opciones de respuesta:</b></p>

                    <p>1: Muy en desacuerdo 2: En desacuerdo 3: Ni acuerdo, ni desacuerdo 4: De acuerdo 5: Muy de acuerdo</p>

                    <table border="1" cellpadding="0" cellspacing="0" class="table table-striped">
                        <tbody>
                            <tr>
                                <td class="pregunta"> </td>
                                <td class="respuesta"><p><b>1</b></p></td>
                                <td class="respuesta"><p><b>2</b></p></td>
                                <td class="respuesta"><p><b>3</b></p></td>
                                <td class="respuesta"><p><b>4</b></p></td>
                                <td class="respuesta"><p><b>5</b></p></td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>1. Muestra dominio del material que expone.</p></td>
                                <td class="respuesta"><input type="radio" name="P_1" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_1" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_1" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_1" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_1" value="5"/> </td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>2. Entrega documentos (apuntes, guías, otros) que complementan el desarrollo de las actividades.</p></td>
                                <td class="respuesta"><input type="radio" name="P_2" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_2" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_2" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_2" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_2" value="5"/> </td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>3. Comunica los contenidos de forma clara.</p></td>
                                <td class="respuesta"><input type="radio" name="P_3" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_3" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_3" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_3" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_3" value="5"/> </td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>4. Utiliza el horario de ayudantía eficientemente, optimizando el tiempo disponible.</p></td>
                                <td class="respuesta"><input type="radio" name="P_4" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_4" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_4" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_4" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_4" value="5"/> </td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>5. Favorece un clima de respeto en la relación con los estudiantes.</p></td>
                                <td class="respuesta"><input type="radio" name="P_5" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_5" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_5" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_5" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_5" value="5"/> </td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>6. Lo recomendaría para que siguiera como ayudante en el futuro.</p></td>
                                <td class="respuesta"><input type="radio" name="P_6" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_6" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_6" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_6" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_6" value="5"/> </td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>7. El ayudante se coordina con el profesor para preparar sus ayudantías.</p></td>
                                <td class="respuesta"><input type="radio" name="P_7" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_7" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_7" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_7" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_7" value="5"/> </td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>8. Cumple puntualmente con el horario asignado a la ayudantía.</p></td>
                                <td class="respuesta"><input type="radio" name="P_8" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_8" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_8" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_8" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_8" value="5"/> </td>
                            </tr>

                            <tr>
                                <td class="pregunta"><p>9. La ayudantía ha servido como eficiente complemento de las clases.</p></td>
                                <td class="respuesta"><input type="radio" name="P_9" value="1"/> </td>
                                <td class="respuesta"><input type="radio" name="P_9" value="2"/> </td>
                                <td class="respuesta"><input type="radio" name="P_9" value="3"/> </td>
                                <td class="respuesta"><input type="radio" name="P_9" value="4"/> </td>
                                <td class="respuesta"><input type="radio" name="P_9" value="5"/> </td>
                            </tr>
                        </tbody>
                    </table>

                    <p><b>PREGUNTA ABIERTA</b></p>

                    <div style="width: 99%">
                        <p>- Señale brevemente aspectos que considere positivo en el desarrollo de la asignatura (máximo 200 caracteres).</p><label for="comentarioPositivo"></label><textarea
                            id="comentarioPositivo" name="comentarioPositivo"
                            cols="100" rows="2" maxlength="200"></textarea>&nbsp;&nbsp;
                    </div>
                    <div style="width: 99%">
                        <p>- Señale brevemente aspectos que podría mejorar el desarrollo de la asignatura (máximo 200 caracteres)</p><label for="comentarioMejora"></label><textarea
                            id="comentarioMejora" name="comentarioMejora"
                            cols="100" rows="2" maxlength="200"></textarea>&nbsp;&nbsp;
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

                <div class="modal fade bs-modal-dialog" id="msg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="msgModalLabel">Error</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body"><div id="msg-div"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>