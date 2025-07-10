<%--
    Document   : alumnoEncuestaDocente
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
        <title>Formulario de Respuesta de Encuesta Docente</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/encuesta/alumnoEncuestaDocente5taSemana-3.0.4.js"></script>

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
    <body class="inner-body" style="font-size: 90%;">

        <div style="background-color:#00a499; color: #fff; padding: 0px 10px; font-size: 120%;">
            <b><s:text name="label.title.encuesta.docente"/> <s:property
                value="#session.genericSession.getWorkSession(key).getCursoProfesor().getCurso().getNombreFull()"/> (<s:property
                value="#session.genericSession.getWorkSession(key).getCursoProfesor().getProfesor().getNombre()"/>)</b>
        </div>

        <div class="container-flow" >
            <div class="row mt-1" style="font-size: 88%;">
                <div class="col-12 ">
                    <div class="alert alert-primary">
                    <b>Estimad@s estudiantes,
                    <br>Tu opinión es fundamental para nosotros. Por favor, dedica unos minutos de tu tiempo para completar esta encuesta para cada una de tus asignaturas. Tu participación es vital para ayudarnos a identificar áreas de mejora y fortaleza en nuestro equipo docente.
                    <br>Toda la información que nos proporciones será tratada con absoluta confidencialidad y se utilizará exclusivamente con el propósito de mejorar tu experiencia académica.
                    <br>Agradecemos sinceramente tu compromiso con tu educación y te animamos a compartir tus comentarios y sugerencias.
                    <br>¡Tu voz es importante para nosotros! ¡Gracias por tu participación!</b>
                    </div>
                </div>
            </div>

            <div  class="row mt-1">
                <div class="col-12">
                <form id="encuesta-form" accept-charset="UTF-8" action="#" method="post">
                    <p class="apartado"><b>I. EVALUACIÓN INTERMEDIA - <s:property value="#session.genericSession.getWorkSession(key).getCursoProfesor().getCurso().getNombreFull()"/></b></p>

                    
                    <p><b>Opciones de respuesta:</b>
                    1: Totalmente en desacuerdo, 2: Muy en desacuerdo, 3: En desacuerdo, 4: Ni acuerdo ni desacuerdo, 5: De acuerdo, 6: Muy de acuerdo, 7: Totalmente de acuerdo </p>
                    <p>Marca solo una respuesta:</p>

                    <table cellpadding="0" cellspacing="0" class="table table-striped table-bordered" style="border: 1px solid grey;">
                        <thead>
                            <tr style="height: 90%;">
                                <td class="dimension">&nbsp;</td>
                                <td class="respuesta">1</td>
                                <td class="respuesta">2</td>
                                <td class="respuesta">3</td>
                                <td class="respuesta">4</td>
                                <td class="respuesta">5</td>
                                <td class="respuesta">6</td>
                                <td class="respuesta">7</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.genericSession.getWorkSession(key).preguntasEncuesta" status="row">
                                <tr style="height: 90%;">
                                    <td><s:property value="id.pregNro"/>.&nbsp;<s:property value="pregDes"/></td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="1"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="2"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="3"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="4"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="5"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="6"/> </td>
                                    <td class="respuesta"><input type="radio" name="P_<s:property value="#row.count"/>" value="7"/> </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>

                    <p><b>PREGUNTAS ABIERTAS</b></p>

                    <ol>
                        <li>
                            <p class="mb-1">Señale brevemente algunos aspectos que considere positivos del docente.</p>
                            <textarea class="form-control" id="comentarioPositivo" name="comentarioPositivo" cols="100" rows="2" maxlength="500"></textarea>
                        </li>
                        <li class="mt-3">
                            <p class="mb-1">Señale brevemente algunos aspectos que considere que podrían mejorar del docente.</p>
                            <textarea class="form-control" id="comentarioMejora" name="comentarioMejora" cols="100" rows="2" maxlength="500"></textarea>
                        </li>
                    </ol>
                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        <input type="hidden" id="nPregDummy" name="nPregDummy"
                               value="<s:property value="#session.genericSession.getWorkSession(key).preguntasEncuesta.size"/>"/>
                        <input type="hidden" id="carreraDummy" name="carreraDummy"
                               value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaCodCar"/>"/>
                        <input type="hidden" id="bienvenidaDummy" name="nPregDummy"
                               value="<s:property value="#session.alumnoSession.bienvenida"/>"/>
                    </div>
                </form>
                <s:if test="#session.alumnoSession.cantEncuestasPorContestar > 1">
                    <button id="save-button" title="Grabar" type="button" class="btn btn-light" style="font-size: 90%;"><span class="fa fa-paper-plane" aria-hidden="true"></span>
                        &nbsp; <span class="hidden-xs">Enviar Respuesta y pasar a la Siguiente Encuesta</span>
                    </button>
                </s:if>
                <s:else>
                    <button id="save-button" title="Grabar" type="button" class="btn btn-light" style="font-size: 90%;"><span class="fa fa-paper-plane" aria-hidden="true"></span>
                        &nbsp; <span class="hidden-xs">Enviar Respuesta y Finalizar Encuesta</span>
                    </button>
                </s:else>
            </div>
        </div>
        </div>

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
                    
        <div class="modal fade bs-modal-dialog" id="msg-bienvenida" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="msgModalLabel">Evaluación Intermedia</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Estimad@s estudiantes,
                        </p><p>En nuestro continuo esfuerzo por ofrecer una educación de calidad y mejorar constantemente tu experiencia académica, nos complace invitarte a participar en nuestra Evaluación Intermedia de Experiencia con Equipo Docente.
                        </p><p>Tu opinión es fundamental para nosotros. Queremos conocer tus percepciones y experiencias en cada una de tus asignaturas para poder brindarte un entorno educativo aún más enriquecedor y adaptado a tus necesidades.
                        </p><p>Por favor, dedica unos minutos de tu tiempo para completar esta encuesta para cada una de tus asignaturas. Tu participación es vital para ayudarnos a identificar áreas de mejora y fortaleza en nuestro equipo docente.
                        </p><p>Toda la información que nos proporciones será tratada con absoluta confidencialidad y se utilizará exclusivamente con el propósito de mejorar tu experiencia académica.
                        </p><p>Agradecemos sinceramente tu compromiso con tu educación y te animamos a compartir tus comentarios y sugerencias.
                        </p><p>¡Tu voz es importante para nosotros! ¡Gracias por tu participación!
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>