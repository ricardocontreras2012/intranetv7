<%--
    Document   : egresadoIdMisDatos
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
        <title>Mis Datos Personales del Egresado</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/users/egresado/id/egresadoIdMisDatos-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.mis.datos.personales"/>
        </div>

        <div id="central-div">
            <div class="buttons-div">
                <button id="save-button" name="save-button" title="<s:text name="label.button.save"/>" type="button" class="btn btn-light" >
                    <span class="fa fa-save" aria-hidden="true"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                </button>
            </div>
            <div id="content-div">
                <form id="egresado-form" action="#" theme="bootstrap" class="form-horizontal">
                    <table>
                        <tr>
                            <td style="width: 75%">
                                <div class="form-group">
                                    <label for="paterno" class="col-sm-3 control-label"><s:text name="label.paterno"/></label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="paterno" name="paterno" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluPaterno"/>" />
                                    </div>
                                </div>
                                    <div class="form-group">
                                <label for="materno" class="col-sm-3 control-label"><s:text name="label.materno"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="materno" name="materno" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluMaterno"/>" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-3 control-label"><s:text name="label.name"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="nombre" name="nombre" readonly
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluNombre"/>" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fechaNac" class="col-sm-3 control-label"><s:text name="label.fecha.nacimiento"/></label></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="fechaNac" name="fechaNac" size="10" readonly placeholder="<s:text
                                               name="label.date.format"/>"
                                           value="<s:date name="#session.genericSession.getWorkSession(key).aluCar.alumno.aluFechaNac" format="dd/MM/yyyy"/>"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="estadoCivil" class="col-sm-3 control-label"><s:text name="label.estado.civil"/></label></label>
                                <div class="col-sm-9">
                                    <s:select id="estadoCivil"
                                                      name="estadoCivil"
                                                      value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEstCiv"
                                                      list="#session.genericSession.getListaEstadoCivil()"
                                                      listKey="ecivCod"
                                                      listValue="ecivDes"
                                                      cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="direccion" class="col-sm-3 control-label"><s:text name="label.direccion"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="direccion" name="direccion" maxlength="70"
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluDirecAlu"/>"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="region" class="col-sm-3 control-label"><s:text name="label.region"/></label>
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
                                <label for="comuna" class="col-sm-3 control-label"><s:text name="label.comuna"/></label>
                                <div class="col-sm-9" id="comunas">
                                    <s:if test="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod != null">
                                        <s:action name="CommonComunaGetComunas" executeResult="true">
                                            <s:param name="region"><s:property
                                                    value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod"/></s:param>
                                            <s:param name="key"><s:property value="key"/></s:param>
                                        </s:action>
                                    </s:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fono" class="col-sm-3 control-label"><s:text name="label.telefono"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="fono" name="fono"
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluFonoAlu"/>"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-3 control-label"><s:text name="label.email"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="email" name="email"
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmail"/>"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="emailLaboral" class="col-sm-3 control-label"><s:text name="label.email.laboral"/></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="emailLaboral" name="emailLaboral"
                                           value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluEmailLaboral"/>"/>
                                </div>
                            </div>

                                <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                    <div class="errorBox">
                                        <s:actionerror/>
                                        <s:actionmessage/>
                                        <s:fielderror/>
                                    </div>
                                </s:if>
                            </td>
                            <td style="vertical-align: top"><img id="foto"
                                                                 src="CommonAlumnoGetFoto?key=<s:property value="key"/>&rut=<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluRut"/>"
                                                                 height="125" width="110"
                                                                 alt="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluRut"/>"/>
                            </td>
                        </tr>
                    </table>
                    <div id="hidden-input-div">
                        <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                        <input type="hidden" id="comunaDummy" name="comunaDummy"
                               value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.comCod"/>"/>
                        <input type="hidden" id="regionDummy" name="regionDummy"
                               value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.comunaAlu.region.regCod"/>"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>