<%--
    Document   : egresadoLaboralesNewLaboral
    Created on : 09-10-2014, 12:24:13 PM
    Author     : Álvaro Romero
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="session.EgresadoSession" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Creación de Nuevo Trabajo</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/users/egresado/laborales/egresadoLaboralesNewLaboral-3.0.1.js"></script>
    </head>
    <%        
        EgresadoSession egresadoSession = (EgresadoSession) session.getAttribute("egresadoSession");
        Integer agno = egresadoSession.getAgno();
    %>
    <body class="inner-body">
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.mis.antecedentes.laborales"/>
            </div>

            <div id="central-div">
                <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                    <div class="errorBox">
                        <s:actionerror/><s:actionmessage/><s:fielderror/>
                    </div>
                </s:if>
                <div class="buttons-div">
                    <button id="save-button" name="save-button" class="btn btn-light" type="button" title="Grabar">
                        <span class="fa fa-save" aria-hidden="true"></span><span class="hidden-xs"> <s:text name="label.button.save"/></span>
                    </button>

                    <button id="empleadores-button" name="save-button" class="btn btn-light" type="button"  title="Usar Empleador ya Registrado" >
                        <span class="fa fa-list-alt " aria-hidden="true"></span><span class="hidden-xs"> Seleccionar Empleador</span>
                    </button>
                </div>
                <div style="max-width: 750px; overflow: hidden">
                    <form id="egresado-form" action="#" class="form-horizontal">
                        <div class="radio">
                            <label>
                                <input type="radio" id="dependiente" name="dependiente" value="D" checked="checked">Dependiente
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" id="dependiente" name="dependiente" value="I">Independiente
                            </label>
                        </div>
                        <div id="filaDependiente">

                            <h4><s:text name="label.employer.information" /></h4>
                            <div class="form-group">
                                <label for="open-modal-button" class="col-sm-8"><s:text name="label.busqueda.avanzada.por.nombre"/></label>
                                <div class="col-sm-1">
                                    <button id="open-modal-button" name="open-modal-button" class="positive">
                                        <img src="/intranetv7/images/local/icon/search.png" height="16" width="16" alt="add" title="Buscar"/>
                                    </button>
                                </div>
                            </div>

                            <div class="form-group" id="filaRutEmpleador">
                                <label for="cargo" class="col-sm-3 control-label"><s:text name="label.rut.empleador"/></label>
                                <div class="col-xs-10 col-sm-7">
                                    <input type="text" class="form-control" id="rutEmpleador" name="rutEmpleador" value=""/>
                                </div>
                                <div class="col-xs-2 col-sm-1">
                                    <input type="text" class="form-control" id="dvEmpleador" name="dvEmpleador" value=""/>
                                </div>
                            </div>

                            <div id="infoEmpleador">
                                <!-- Rellenado Dinamico -->
                            </div>
                        </div><!-- fin dependiente -->

                        <div class="form-group" id="filaIndepActividadEconomica">
                            <label for="indepActividadEconomica" class="col-sm-3 control-label"><s:text name="label.economy.activity"/></label>
                            <div class="col-xs-10 col-sm-7">
                                <select id="indepActividadEconomica" name="indepActividadEconomica" class="form-control">
                                    <option value="0">-- Seleccione Actividad Económica --</option>
                                    <option value="12">ACTIVIDADES INMOBILIARIAS, EMPRESARIALES Y DE ALQUILER</option>
                                    <option value="13">ADM. PUBLICA Y DEFENSA; PLANES DE SEG. SOCIAL AFILIACIÓN OBLIGATORIA</option>
                                    <option value="1">AGRICULTURA, GANADERÍA, CAZA Y SILVICULTURA</option>
                                    <option value="8">COMERCIO AL POR MAYOR Y MENOR; REP. VEH.AUTOMOTORES/ENSERES DOMÉSTICOS</option>
                                    <option value="17">CONSEJO DE ADMINISTRACIÓN DE EDIFICIOS Y CONDOMINIOS</option>
                                    <option value="7">CONSTRUCCIÓN</option>
                                    <option value="14">ENSEÑANZA</option>
                                    <option value="3">EXPLOTACIÓN DE MINAS Y CANTERAS</option>
                                    <option value="9">HOTELES Y RESTAURANTES</option>
                                    <option value="5">INDUSTRIAS MANUFACTURERAS METÁLICAS</option>
                                    <option value="4">INDUSTRIAS MANUFACTURERAS NO METÁLICAS</option>
                                    <option value="11">INTERMEDIACIÓN FINANCIERA</option>
                                    <option value="18">ORGANIZACIONES Y ÓRGANOS EXTRATERRITORIALES</option>
                                    <option value="16">OTRAS ACTIVIDADES DE SERVICIOS COMUNITARIAS, SOCIALES Y PERSONALES</option>
                                    <option value="2">PESCA</option>
                                    <option value="15">SERVICIOS SOCIALES Y DE SALUD</option>
                                    <option value="6">SUMINISTRO DE ELECTRICIDAD, GAS Y AGUA</option>
                                    <option value="10">TRANSPORTE, ALMACENAMIENTO Y COMUNICACIONES</option>
                                </select>
                            </div>
                        </div>

                        <hr />

                        <h4><s:text name="label.periodo.en.empresa"/></h4>

                        <div class="form-group">
                            <label for="desdeMesEmpresa" class="col-xs-12 col-sm-3 control-label"><s:text name="label.date.from"/></label>
                            <div class="col-xs-7 col-sm-5">
                                <select id="desdeMesEmpresa" name="desdeMesEmpresa" class="form-control">
                                </select>
                            </div>
                            <div class="col-xs-5 col-sm-4">
                                <select id="desdeAgnoEmpresa" name="desdeAgnoEmpresa" class="form-control">
                                    <%                                        
                                        for (int i = agno; i > 1950; i--) {
                                            out.println("<option value=\"" + i + "\">" + i + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="hastaMesEmpresa" class="col-xs-12 col-sm-3 control-label"><s:text name="label.date.to"/></label>
                            <div class="col-xs-7 col-sm-5">
                                <select id="hastaMesEmpresa" name="hastaMesEmpresa" class="form-control">
                                    <option value="" selected="selected">--</option>
                                </select>
                            </div>
                            <div class="col-xs-5 col-sm-4">
                                <select id="hastaAgnoEmpresa" name="hastaAgnoEmpresa" class="form-control">
                                    <option value="" selected="selected">--</option>
                                    <% for (int i = agno; i > 1950; i--) {
                                            out.println("<option value=\"" + i + "\">" + i + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>

                        <hr />

                        <h4><s:text name="label.job.place" /></h4>
                        <label class="radio-inline">
                            <input type="radio" name="lugar" id="lugar" value="Chile"> Chile
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="lugar" id="lugar" value="Extranjero"> Extranjero
                        </label>

                        <div class="form-group" id="filaRegion">
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
                        <div class="form-group" id="filaComuna">
                            <label for="comuna" class="col-sm-3 control-label"><s:text name="label.comuna"/></label>
                            <div class="col-sm-9" id="comunas">
                                <select name="comuna" id="comuna" class="form-control" readonly="readonly">
                                    <option value="">Seleccione Comuna</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" id="filaOtroLugar">
                            <label for="otroLugar" class="col-sm-3 control-label"><s:text name="label.where"/></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="otroLugar" name="otroLugar" value=""/>
                            </div>
                        </div>

                        <h4><s:text name="label.position.description" /></h4>
                        <div class="form-group">
                            <label for="areaTrabajo" class="col-sm-3 control-label"><s:text name="label.area.trabajo"/></label>
                            <div class="col-sm-9">
                                <select id="areaTrabajo" name="areaTrabajo" class="form-control">
                                    <option value="0">-- Seleccione Área de Trabajo --</option>
                                    <option value="2">Administración</option>
                                    <option value="3">Analista</option>
                                    <option value="4">Arte/Creativo</option>
                                    <option value="5">Cadena de Suministro</option>
                                    <option value="6">Calidad</option>
                                    <option value="7">Ciencia</option>
                                    <option value="1">Comercial / Ventas</option>
                                    <option value="8">Compras</option>
                                    <option value="9">Comunicaciones</option>
                                    <option value="40">Construcción</option>
                                    <option value="10">Consultoría</option>
                                    <option value="11">Contabilidad/Auditoría</option>
                                    <option value="12">Control de Gestión</option>
                                    <option value="13">Desarrollo de Negocios</option>
                                    <option value="14">Diseño</option>
                                    <option value="15">Educación</option>
                                    <option value="16">Estrategia / Planificación</option>
                                    <option value="17">Estudios</option>
                                    <option value="18">Exportación / Importación / Internacional</option>
                                    <option value="19">Fabricación</option>
                                    <option value="20">Finanzas</option>
                                    <option value="21">Formación</option>
                                    <option value="22">General de Empresas</option>
                                    <option value="23">Gestión de Productos</option>
                                    <option value="24">Gestión de Proyectos</option>
                                    <option value="25">Informática</option>
                                    <option value="26">Investigación</option>
                                    <option value="27">Jurídico/Fiscal/Social</option>
                                    <option value="28">Legal</option>
                                    <option value="29">Logistica/Transporte</option>
                                    <option value="30">Marketing</option>
                                    <option value="31">Operaciones</option>
                                    <option value="32">Producción</option>
                                    <option value="33">Producción/Servicio Técnico/Ingenería</option>
                                    <option value="34">Publicidad</option>
                                    <option value="35">Recursos Humanos</option>
                                    <option value="36">Relaciones Públicas</option>
                                    <option value="37">Servicio al Cliente</option>
                                    <option value="38">Tecnologías de la Información</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="cargo" class="col-sm-3 control-label"><s:text name="label.cargo"/></label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="cargo" name="cargo" value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="tipoTrabajo" class="col-sm-3 control-label"><s:text name="label.tipo.trabajo"/></label>
                            <div class="col-sm-9">
                                <select id="tipoTrabajo" name="tipoTrabajo" class="form-control">
                                    <option value="0">-- Seleccione Tipo Trabajo --</option>
                                    <option value="1">Jornada Completa</option>
                                    <option value="2">Media Jornada</option>
                                    <option value="3">Part Time</option>
                                    <option value="4">Comisionista</option>
                                    <option value="5">Reemplazo</option>
                                    <option value="6">Práctica Profesional</option>
                                    <option value="7">Por Turnos</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sueldo" class="col-sm-3 control-label"><s:text name="label.rango.sueldo"/></label>
                            <div class="col-sm-9">
                                <select id="sueldo" name="sueldo" class="form-control">
                                    <option value="0">No desea indicar</option>
                                    <option value="250000">Menos de 250.000</option>
                                    <option value="500000">Entre 250.001 a 500.000</option>
                                    <option value="750000">Entre 500.001 a 750.000</option>
                                    <option value="1000000">Entre 750.001 a 1.000.000</option>
                                    <option value="2000000">Entre 1.000.001 a 2.000.000</option>
                                    <option value="5000000">Entre 2.000.001 a 5.000.000</option>
                                    <option value="9999999">Sobre 5.000.000</option>
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="desdeMes" class="col-xs-12 col-sm-3 control-label"><s:text name="label.date.from"/></label>
                            <div class="col-xs-7 col-sm-5">
                                <select id="desdeMes" name="desdeMes" class="form-control">
                                </select>
                            </div>
                            <div class="col-xs-5 col-sm-4">
                                <select id="desdeAgno" name="desdeAgno" class="form-control">
                                    <% for (int i = agno; i > 1950; i--) {
                                            out.println("<option value=\"" + i + "\">" + i + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="hastaMes" class="col-xs-12 col-sm-3 control-label"><s:text name="label.date.to"/></label>
                            <div class="col-xs-7 col-sm-5">
                                <select id="hastaMes" name="hastaMes" class="form-control">
                                    <option value="" selected="selected">--</option>
                                </select>
                            </div>
                            <div class="col-xs-5 col-sm-4">
                                <select id="hastaAgno" name="hastaAgno" class="form-control">
                                    <option value="" selected="selected">--</option>
                                    <% for (int i = agno; i > 1950; i--) {
                                            out.println("<option value=\"" + i + "\">" + i + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="sueldo" class="col-sm-3 control-label"><s:text name="label.descripcion"/></label>
                            <div class="col-sm-9">
                                <textarea name="descripcion" id="descripcion" rows="3" class="form-control" ></textarea>
                            </div>
                        </div>

                        <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                            <div class="errorBox">
                                <s:actionerror/>
                                <s:actionmessage/>
                                <s:fielderror/>
                            </div>
                        </s:if>

                        <div id="hidden-input-div">
                            <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                            <input type="hidden" id="correlAluEmp" name="correlAluEmp" value=""/>
                        </div>
                        <div id="new-div" title="Buscar Empleador" style="display:none;">
                            <label for="nombre" id="nombre-label" ><s:text name="label.name"/></label>
                            <input id="nombre" name="nombre" type="text" />
                            <button type="button" id="search-name-button" name="search-name-button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button">
                                <span class="ui-button-text">Buscar</span>
                            </button>
                            <div id="listadoEmpleadores" style="height:150px;">
                                <!-- Rellenado Dinamico -->
                            </div>
                        </div>
                    </form>

                    <div class="modal fade bs-modal-dialog" id="modal-empleadores" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-md">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="msgModalLabel">Seleccionar Empleador</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body"><div id="modal-empleadores-content"></div>
                                    <s:iterator value="#session.egresadoSession.alumnoEmpleadorList" status="row">
                                        <s:if test="%{empleador.empRut!=0}">
                                            <input type="radio"  name="aemCorrel" id="aemCorrel" class="aemCorrel" value="<s:property value="aemCorrel"/>" checked>
                                            <span><s:property value="empleador.empNombre"/> (<s:property value="aemDesdeMes"/>/<s:property value="aemDesdeAgno"/><s:if test="%{aemHastaMes!=null}"> - <s:property value="aemHastaMes"/>/<s:property value="aemHastaAgno"/></s:if>)</span>
                                            <input type="hidden" name="rut_<s:property value="aemCorrel"/>" id="rut_<s:property value="aemCorrel"/>" value="<s:property value="empleador.empRut"/>" />
                                            <input type="hidden" name="nom_<s:property value="aemCorrel"/>" id="nom_<s:property value="aemCorrel"/>" value="<s:property value="empleador.empNombre"/>" />
                                            <input type="hidden" name="tip_<s:property value="aemCorrel"/>" id="tip_<s:property value="aemCorrel"/>" value="<s:property value="empleador.empTipoEmpleador"/>" />
                                            <input type="hidden" name="act_<s:property value="aemCorrel"/>" id="act_<s:property value="aemCorrel"/>" value="<s:property value="empleador.actividadEconomica.aceDes"/>" />
                                            <input type="hidden" name="dme_<s:property value="aemCorrel"/>" id="dme_<s:property value="aemCorrel"/>" value="<s:property value="aemDesdeMes"/>" />
                                            <input type="hidden" name="dag_<s:property value="aemCorrel"/>" id="dag_<s:property value="aemCorrel"/>" value="<s:property value="aemDesdeAgno"/>" />
                                            <input type="hidden" name="hme_<s:property value="aemCorrel"/>" id="hme_<s:property value="aemCorrel"/>" value="<s:property value="aemHastaMes"/>" />
                                            <input type="hidden" name="hag_<s:property value="aemCorrel"/>" id="hag_<s:property value="aemCorrel"/>" value="<s:property value="aemHastaAgno"/>" />
                                            <br/>
                                        </s:if>
                                    </s:iterator>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-light" onclick="modalEmpleadoresAceptar();">Aceptar</button>
                                    <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>