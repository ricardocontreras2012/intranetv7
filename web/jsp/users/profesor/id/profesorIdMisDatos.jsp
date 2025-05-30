<%--
    Document   : profesorIdMisDatos
    Created on : 06-06-2009, 06:32:41 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Mis Datos Personales Profesor</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/id/profesorIdMisDatos-3.0.3.js"></script>
    </head>
    <body class="inner-body">
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.mis.datos.personales"/>
            </div>

            <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
                &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
            </button>

            <s:form id="profesor-form" action="#" method="post" class="form-horizontal">
                <table width="99%">
                    <tr>
                        <td style="width: 75%">

                            <div class="form-group">
                                <label for="paterno" class="col-sm-2 control-label">Primer Apellido</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="paterno" name="paterno" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profPat"/>" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="materno" class="col-sm-2 control-label">Segundo Apellido</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="materno" name="materno" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profMat"/>" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label"><s:text name="label.name"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="nombre" name="nombre" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profNom"/>" />
                                </div>
                            </div>

                            <%--div class="form-group">
                                <label for="fechaNac" class="col-sm-2 control-label"><s:text name="label.fecha.nacimiento"/></label></label>
                                <div class="col-sm-9">
                                    <input type="date" class="form-control" id="fechaNac" name="fechaNac" size="10" placeholder="<s:text
                                               name="label.date.format"/>"
                                           value="<s:date name="#session.genericSession.getWorkSession(key).profesor.profFechaNac" format="yyyy-MM-dd"/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="direccion" class="col-sm-2 control-label"><s:text name="label.direccion"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="direccion" name="direccion" maxlength="70"
                                           value="<s:property value="#session.genericSession.getWorkSession(key).profesor.profDireccion"/>"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="region" class="col-sm-2 control-label"><s:text name="label.region"/></label>
                                <div class="col-sm-9">
                                    <s:select id="region"
                                              name="region"
                                              headerKey=""
                                              headerValue="Seleccione Región"
                                              list="#session.genericSession.getListaRegion()"
                                              listKey="regCod"
                                              listValue="regNom"
                                              cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="comuna" class="col-sm-2 control-label"><s:text name="label.comuna"/></label>
                                <div class="col-sm-9" id="comunas">
                                    <s:if test="#session.genericSession.getWorkSession(key).profesor.comuna.region.regCod != null">
                                        <s:action name="CommonComunaGetComunas" executeResult="true">
                                            <s:param name="region"><s:property
                                                    value="#session.genericSession.getWorkSession(key).profesor.comuna.region.regCod"/></s:param>
                                            <s:param name="key"><s:property value="key"/></s:param>
                                        </s:action>
                                    </s:if>
                                </div>
                            </div--%>

                            <div class="form-group">
                                <label for="fono" class="col-sm-2 control-label">Celular</label>
                                <div class="input-group col-sm-9">
                                    <span class="input-group-text" id="basic-addon1">+56 9</span>
                                    <input type="text" class="form-control" id="fono" name="fono" size="8"
                                           value="<s:property value='#session.genericSession.getWorkSession(key).profesor.profFono'/>" 
                                           maxlength="8" oninput="formatPhoneNumber(this)">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label"><s:text name="label.email"/>&nbsp;USACH</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="emailUsach" name="emailUsach" size="57" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).profesor.getEmailUsach()"/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label"><s:text name="label.email"/> Secundario <span style="color:#FF0000">*</span></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="email" name="email" size="57"
                                           value="<s:property value="#session.genericSession.getWorkSession(key).profesor.getEmailNoUsach()"/>"/>
                                </div>
                            </div>

                            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                <div class="errorBox">
                                    <s:actionerror/>
                                    <s:actionmessage/>
                                    <s:fielderror/>
                                </div>
                            </s:if>

                            <%--div class="form-group">
                                <label for="jerarquia" class="col-sm-2 control-label"><s:text name="label.jerarquia"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="jerarquia" name="jerarquia" readonly value="<s:property
                                               value="#session.genericSession.getWorkSession(key).profesor.jerarquia.jerDes"/>" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="categoria" class="col-sm-2 control-label"><s:text name="label.categoria"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="categoria" name="categoria" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).profesor.categoria.catDes"/>" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="jornada" class="col-sm-2 control-label"><s:text name="label.jornada"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="jornada" name="jornada" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).profesor.jornada.jorDes"/>" />
                                </div>
                            </div--%>

                        </td>
                        <td style="vertical-align: top"><img id="foto"
                                                             src="CommonProfesorGetFoto?key=<s:property value="key"/>&rut=<s:property value="#session.genericSession.getWorkSession(key).profesor.profRut"/>"
                                                             alt="<s:property value="#session.genericSession.getWorkSession(key).profesor.profRut"/>"
                                                             height="125" width="110"/></td>
                    </tr>
                </table>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="comunaDummy" name="comunaDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).profesor.comuna.comCod"/>"/>
                <input type="hidden" id="regionDummy" name="regionDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).profesor.comuna.region.regCod"/>"/>
            </div>
        </s:form>

        <div class="modal fade" id="info-modal" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Información</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Estimado/a docente, <br/>
Espero que al recibo de este mensaje se encuentre bien. Estamos realizando una actualización general de los datos de contacto del equipo docente para asegurar una comunicación eficiente y precisa. Le agradeceríamos que dedicara unos minutos a completar o verificar sus datos actuales. Por favor, acceda al siguiente formulario para actualizar su información.
En caso de tener dudas o necesitar asistencia, no dude en escribirnos a docencia.fae@usach.cl<br/>
Agradecemos de antemano su colaboración para mantener nuestra base de datos al día.<br/>
Un cordial saludo,</p>
<p>Carolina Nicolas<br/>
Vicedecana de Docencia<br/>
Facultad de Administración y Economía,<br/>
Universidad de Santiago de Chile</p>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>