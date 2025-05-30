<%--
    Document   : alumnoSolicitudPracticaNewSolicitud
    Created on : 14-09-2010, 10:39:01 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Generación de Solicitud de Práctica</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/solicitud/practica/alumnoSolicitudPracticaNewSolicitud-3.0.2.js"></script>
    </head>
    <body class="inner-body">

        <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
        </button>

        <s:form id="solicitud-form" action="#" theme="bootstrap">
            <div class="title-div">
                DATOS DE LA ORGANIZACIÓN
            </div>
            <div class="data-tables-container">
                <table class="table">
                    <tr>
                        <td style="width: 15%">
                            <s:text name="label.rut.empleador"/>
                        </td>
                        <td>
                            <div id="id-emp-div" class="form-group input-sm col-lg-2 col-md-2 col-sm-3 no-padding">
                                <input type="text" id="rutdvEmpleador" name="rutdvEmpleador" size="12" value="" maxlength="12" class="form-control" placeholder="12345678-9"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Nombre
                        </td>
                        <td>
                            <div id="datos-emp-div"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Labor a desempeñar en la práctica
                        </td>
                        <td>
                            <textarea name="labor" id="labor" rows="5" cols="100" maxlength="2500" class="form-control">
                            </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="region"><s:text name="label.region"/></label>
                        </td>
                        <td>
                            <div class="form-group input-sm col-lg-4 col-md-4 col-sm-8 no-padding">
                            <s:select id="region"
                                              name="region"
                                              headerKey=""
                                              headerValue="Seleccione Región"
                                              list="#session.genericSession.getListaRegion()"
                                              listKey="regCod"
                                              listValue="regNom"
                                              cssClass="form-control"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><s:text name="label.comuna"/></td>
                        <td>
                            <div id="comunas" class="form-group input-sm col-lg-4 col-md-4 col-sm-8 no-padding">
                                <s:if test="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod != null">
                                    <s:action name="CommonComunaGetComunas" executeResult="true">
                                        <s:param name="region"><s:property
                                                value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod"/></s:param>
                                        <s:param name="key"><s:property value="key"/></s:param>
                                    </s:action>
                                </s:if>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Dirección
                        </td>
                        <td>
                            <textarea name="direccion" id="labor" rows="5" cols="100" class="form-control">
                            </textarea>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Teléfono
                        </td>
                        <td>
                            <input type="text" id="fonoEmp" name="fonoEmp" size="50" value="" maxlength="50" class="form-control"/>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="title-div">
                DATOS DEL SUPERVISOR O CONTACTO EN LA ORGANIZACIÓN
            </div>
            <div class="data-tables-container">
                <table class="table">
                    <tr>
                        <td style="width: 15%">
                            RUT Autorizador
                        </td>
                        <td>
                            <div id="id-aut-div" class="form-group input-sm col-lg-2 col-md-2 col-sm-3 no-padding">
                                <input type="text" id="rutdvAutoriza" name="rutdvAutoriza" size="12" value="" maxlength="12" class="form-control" placeholder="12345678-9"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Nombre
                        </td>
                        <td>
                            <div id="datos-aut-div"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Teléfono
                        </td>
                        <td>
                            <input type="text" id="fonoAut" name="fonoAut" size="50" value="" maxlength="50" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            e-mail
                        </td>
                        <td>
                            <input type="text" id="email" name="email" size="50" value="" maxlength="50" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            En su calidad de
                        </td>
                        <td>
                            <textarea name="calidad" id="calidad" rows="2" cols="100" maxlength="100" class="form-control">
                            </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Fecha de Inicio
                        </td>
                        <td>
                            <input id="inicio" name="inicio" width="300" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Fecha de Término
                        </td>
                        <td>
                            <input id="termino" name="termino" width="300" />
                        </td>
                    </tr>
                </table>
            </div>

            <div>
                Para consultas enviar correo a ivan.jorquera@usach.cl o llamar al 227180740.
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="rutEmp" name="rutEmp"/>
                <input type="hidden" id="rutAut" name="rutAut"/>
                <input type="hidden" id="comunaDummy" name="comunaDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.comCod"/>"/>
                <input type="hidden" id="regionDummy" name="regionDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod"/>"/>
            </div>
        </s:form>
    </body>
</html>