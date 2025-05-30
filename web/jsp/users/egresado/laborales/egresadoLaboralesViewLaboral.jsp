<%--
    Document   : egresadoLaboralesViewLaboral
    Created on : 02-10-2014, 12:44:41
    Author     : Álvaro Romero
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Edición Laboral</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-message-bar.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/egresado/laborales/egresadoLaboralesViewLaboral-3.0.4.js"></script>
    </head>
    <body class="inner-body">
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
                <button id="save-button" name="save-button" class="positive">
                    <img src="/intranetv7/images/local/icon/disk.png" height="16" width="16" alt="save" title="Grabar"/>
                    <s:text name="label.button.save"/>
                </button>
            </div>
            <div style="width: 100%; overflow: hidden">
                <form id="egresado-form" action="#">
                    <table>
                        <tr>
                            <td>
                                <input type="radio" id="dependiente" name="dependiente" value="D" />Dependiente<br />
                                <input type="radio" id="dependiente" name="dependiente" value="I" />Independiente
                                <table cellspacing="3" cellpadding="5">
                                    <tr id="filaTitulo">
                                        <th colspan="2"><s:text name="label.employer.information" /></th>
                                    </tr>
                                    <tr id="filaRutEmpleador">
                                        <td style="width: 200px;"><s:text name="label.rut.empleador"/> (Sin DV)</td>
                                        <td>
                                            <input type="text" id="rutEmpleador" name="rutEmpleador" size="8"
                                                   value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.empleador.empRut"/>" maxlength="8" />
                                            <!--button id="accept-button" name="accept-button">
                                                <img src="/intranetv7/images/local/icon/white.png" height="16" width="16" alt="add" title="White"/>
                                                <!--s:text name="label.button.accept"/-->
                                            <!--/button-->
                                        </td>
                                    </tr>
                                    <tr id="filaBuscarEmpleador">
                                        <td style="width: 200px;">&nbsp;</td>
                                        <td>
                                            <s:text name="label.busqueda.avanzada.por.nombre"/>
                                            <button id="open-modal-button" name="open-modal-button" class="positive">
                                                <img src="/intranetv7/images/local/icon/search.png" height="16" width="16" alt="add" title="Buscar"/>
                                            </button>
                                        </td>
                                    </tr>
                                </table>

                                <table id="infoEmpleador">
                                    <tr id="filaNombreEmpleador">
                                        <td style="width: 200px;">
                                            <s:text name="label.name.empleador"/>
                                        </td><td>
                                            <input id="nombreEmpleador" name="nombreEmpleador" size="100" style="text-transform: uppercase;"
                                                   readonly="readonly"
                                                   value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.empleador.empNombre"/>"/>
                                        </td>
                                    </tr>
                                    <tr id="filaTipoEmpleador">
                                        <td>
                                            <s:text name="label.sector.empleador"/>
                                        </td>
                                        <td>
                                            <input id="tipoEmpleador" name="tipoEmpleador" size="100" style="text-transform: uppercase;"
                                                   readonly="readonly"
                                                   <s:if test="#session.egresadoSession.fichaLaboral.alumnoEmpleador.empleador.empTipoEmpleador == 'PR'">
                                                       value="PRIVADO"
                                                   </s:if>
                                                   <s:elseif test="#session.egresadoSession.fichaLaboral.alumnoEmpleador.empleador.empTipoEmpleador == 'PU'">
                                                       value="PÚBLICO"
                                                   </s:elseif>/>
                                        </td>
                                    </tr>
                                    <tr id="filaActividadEconomica">
                                        <td><s:text name="label.economy.activity"/></td>
                                        <td>
                                            <input id="actividadEconomica" name="actividadEconomica" size="100" style="text-transform: uppercase;"
                                                   readonly="readonly"
                                                   value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.empleador.actividadEconomica.aceDes"/>"/>
                                        </td>
                                    </tr>
                                </table>

                                <table>
                                    <tr id="filaIndepActividadEconomica">
                                        <td style="width: 200px;"><s:text name="label.economy.activity"/></td>
                                        <td>
                                            <select id="indepActividadEconomica" name="indepActividadEconomica" class="input">
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
                                        </td>
                                    </tr>
                                </table>

                                <table>
                                    <tr>
                                        <th colspan="2"><s:text name="label.periodo.en.empresa" /></th>
                                    </tr>
                                    <tr>
                                        <td style="width: 200px;"><s:text name="label.date.from"/></td>
                                        <td>
                                            <select id="desdeMesEmpresa" name="desdeMesEmpresa" class="input">
                                                <option value="1">Enero</option>
                                                <option value="2">Febrero</option>
                                                <option value="3">Marzo</option>
                                                <option value="4">Abril</option>
                                                <option value="5">Mayo</option>
                                                <option value="6">Junio</option>
                                                <option value="7">Julio</option>
                                                <option value="8">Agosto</option>
                                                <option value="9">Septiembre</option>
                                                <option value="10">Octubre</option>
                                                <option value="11">Noviembre</option>
                                                <option value="12">Diciembre</option>
                                            </select>
                                            <select id="desdeAgnoEmpresa" name="desdeAgnoEmpresa" class="input">
                                                <% for (int i = 2017; i > 1950; i--) {
                                                        out.println("<option value=\"" + i + "\">" + i + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><s:text name="label.date.to"/></td>
                                        <td>
                                            <select id="hastaMesEmpresa" name="hastaMesEmpresa" class="input">
                                                <option value="" selected="selected">--</option>
                                                <option value="1">Enero</option>
                                                <option value="2">Febrero</option>
                                                <option value="3">Marzo</option>
                                                <option value="4">Abril</option>
                                                <option value="5">Mayo</option>
                                                <option value="6">Junio</option>
                                                <option value="7">Julio</option>
                                                <option value="8">Agosto</option>
                                                <option value="9">Septiembre</option>
                                                <option value="10">Octubre</option>
                                                <option value="11">Noviembre</option>
                                                <option value="12">Diciembre</option>
                                            </select>
                                            <select id="hastaAgnoEmpresa" name="hastaAgnoEmpresa" class="input">
                                                <option value="" selected="selected">--</option>
                                                <% for (int i = 2017; i > 1950; i--) {
                                                        out.println("<option value=\"" + i + "\">" + i + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                </table>

                                <table cellspacing="3" cellpadding="5">
                                    <tr>
                                        <th colspan="2"><s:text name="label.job.place" /></th>
                                    </tr>
                                    <tr>
                                        <td style="width: 200px;"><s:text name="label.place"/></td>
                                        <td><input type="radio" id="lugar" name="lugar" value="Chile" checked="checked"/>Chile
                                            <input type="radio" id="lugar" name="lugar" value="Extranjero" />Extranjero</td>
                                    </tr>
                                    <tr id="filaRegion">
                                        <td><s:text name="label.region"/></td>
                                        <td>
                                            <s:select id="region"
                                                      name="region"
                                                      headerKey=""
                                                      headerValue="-- Seleccione Región --"
                                                      list="#session.genericSession.getListaRegion()"
                                                      listKey="regCod"
                                                      listValue="regNom"
                                                      cssClass="input"/>
                                        </td>
                                    </tr>
                                    <tr id="filaComuna">
                                        <td><s:text name="label.comuna"/></td>
                                        <td>
                                            <div id="comunas">
                                                <s:if test="#session.egresadoSession.fichaLaboral.region.regCod != null">
                                                    <s:action name="CommonComunaGetComunas" executeResult="true">
                                                        <s:param name="region"><s:property
                                                                value="#session.egresadoSession.fichaLaboral.region.regCod"/></s:param>
                                                        <s:param name="key"><s:property value="key"/></s:param>
                                                    </s:action>
                                                </s:if>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr id="filaOtroLugar">
                                        <td><s:text name="label.where"/></td>
                                        <td><input type="text" id="otroLugar" name="otroLugar" size="57"
                                                   value="<s:property value="#session.egresadoSession.fichaLaboral.flabOtroLugar"/>" maxlength="499" /></td>
                                    </tr>
                                </table>

                                <table cellspacing="3" cellpadding="5">
                                    <tr>
                                        <th colspan="2"><s:text name="label.position.description" /></th>
                                    </tr>
                                    <tr>
                                        <td style="width: 200px;"><s:text name="label.area.trabajo"/></td>
                                        <td>
                                            <select id="areaTrabajo" name="areaTrabajo" class="input">
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
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><s:text name="label.cargo"/></td>
                                        <td><input type="text" id="cargo" name="cargo" size="57"
                                                   value="<s:property value="#session.egresadoSession.fichaLaboral.flabCargo"/>" maxlength="499" /></td>
                                    </tr>
                                    <tr>
                                        <td><s:text name="label.tipo.trabajo"/></td>
                                        <td>
                                            <select id="tipoTrabajo" name="tipoTrabajo" class="input">
                                                <option value="0">-- Seleccione Tipo Trabajo --</option>
                                                <option value="1">Jornada Completa</option>
                                                <option value="2">Media Jornada</option>
                                                <option value="3">Part Time</option>
                                                <option value="4">Comisionista</option>
                                                <option value="5">Reemplazo</option>
                                                <option value="6">Práctica Profesional</option>
                                                <option value="7">Por Turnos</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><s:text name="label.rango.sueldo"/></td>
                                        <td>
                                            <select id="sueldo" name="sueldo" class="input">
                                                <option value="0">No desea indicar</option>
                                                <option value="250000">Menos de 250.000</option>
                                                <option value="500000">Entre 250.001 a 500.000</option>
                                                <option value="750000">Entre 500.001 a 750.000</option>
                                                <option value="1000000">Entre 750.001 a 1.000.000</option>
                                                <option value="2000000">Entre 1.000.001 a 2.000.000</option>
                                                <option value="5000000">Entre 2.000.001 a 5.000.000</option>
                                                <option value="9999999">Sobre 5.000.000</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><s:text name="label.date.from"/></td>
                                        <td>
                                            <select id="desdeMes" name="desdeMes" class="input">
                                                <option value="1">Enero</option>
                                                <option value="2">Febrero</option>
                                                <option value="3">Marzo</option>
                                                <option value="4">Abril</option>
                                                <option value="5">Mayo</option>
                                                <option value="6">Junio</option>
                                                <option value="7">Julio</option>
                                                <option value="8">Agosto</option>
                                                <option value="9">Septiembre</option>
                                                <option value="10">Octubre</option>
                                                <option value="11">Noviembre</option>
                                                <option value="12">Diciembre</option>
                                            </select>
                                            <select id="desdeAgno" name="desdeAgno" class="input">
                                                <% for (int i = 2017; i > 1950; i--) {
                                                        out.println("<option value=\"" + i + "\">" + i + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><s:text name="label.date.to"/></td>
                                        <td>
                                            <select id="hastaMes" name="hastaMes" class="input">
                                                <option value="" selected="selected">--</option>
                                                <option value="1">Enero</option>
                                                <option value="2">Febrero</option>
                                                <option value="3">Marzo</option>
                                                <option value="4">Abril</option>
                                                <option value="5">Mayo</option>
                                                <option value="6">Junio</option>
                                                <option value="7">Julio</option>
                                                <option value="8">Agosto</option>
                                                <option value="9">Septiembre</option>
                                                <option value="10">Octubre</option>
                                                <option value="11">Noviembre</option>
                                                <option value="12">Diciembre</option>
                                            </select>
                                            <select id="hastaAgno" name="hastaAgno" class="input">
                                                <option value="" selected="selected">--</option>
                                                <% for (int i = 2017; i > 1950; i--) {
                                                        out.println("<option value=\"" + i + "\">" + i + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><s:text name="label.descripcion"/></td>
                                        <td>
                                            <textarea name="descripcion" id="descripcion" rows="3" cols="120"><s:property value="#session.egresadoSession.fichaLaboral.flabDescripcion"/></textarea>
                                        </td>
                                    </tr>
                                </table>
                                <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                                    <div class="errorBox">
                                        <s:actionerror/>
                                        <s:actionmessage/>
                                        <s:fielderror/>
                                    </div>
                                </s:if>
                            </td>

                        </tr>
                    </table>
                    <div id="hidden-input-div">
                        <input type="hidden" id="correlAluEmp" name="correlAluEmp" value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.aemCorrel"/>"/>
                        <input type="hidden" id="correlFicha" name="correlFicha" value="<s:property value="#session.egresadoSession.fichaLaboral.flabCorrelFicha"/>"/>
                        <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>

                        <input type="hidden" id="rutEmpleadorDummy" name="rutEmpleadorDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.empleador.empRut"/>"/>
                        <input type="hidden" id="dependienteDummy" name="dependienteDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.flabDependiente"/>"/>
                        <input type="hidden" id="tipoEmpleadorDummy" name="tipoEmpleadorDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.empleador.flabTipoEmpleador"/>"/>
                        <input type="hidden" id="indepActividadEconomicaDummy" name="indepActividadEconomicaDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.actividadEconomica.aceCod"/>"/>
                        <input type="hidden" id="regionDummy" name="regionDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.region.regCod"/>"/>
                        <input type="hidden" id="comunaDummy" name="comunaDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.comuna.comCod"/>"/>
                        <input type="hidden" id="areaTrabajoDummy" name="areaTrabajoDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.areaTrabajo.atraId"/>"/>
                        <input type="hidden" id="tipoTrabajoDummy" name="tipoTrabajoDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.tipoTrabajo.ttraId"/>"/>
                        <input type="hidden" id="sueldoDummy" name="sueldoDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.flabSueldo"/>"/>
                        <input type="hidden" id="desdeAgnoDummy" name="desdeAgnoDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.flabDesdeAgno"/>"/>
                        <input type="hidden" id="desdeMesDummy" name="desdeMesDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.flabDesdeMes"/>"/>
                        <input type="hidden" id="hastaAgnoDummy" name="hastaAgnoDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.flabHastaAgno"/>"/>
                        <input type="hidden" id="hastaMesDummy" name="hastaMesDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.flabHastaMes"/>"/>
                        <input type="hidden" id="desdeAgnoEmpresaDummy" name="desdeAgnoEmpresaDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.aemDesdeAgno"/>"/>
                        <input type="hidden" id="desdeMesEmpresaDummy" name="desdeMesEmpresaDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.aemDesdeMes"/>"/>
                        <input type="hidden" id="hastaAgnoEmpresaDummy" name="hastaAgnoEmpresaDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.aemHastaAgno"/>"/>
                        <input type="hidden" id="hastaMesEmpresaDummy" name="hastaMesEmpresaDummy" value="<s:property value="#session.egresadoSession.fichaLaboral.alumnoEmpleador.aemHastaMes"/>"/>

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
                <div id="msg-div" title="<s:text name="message.title.msg"/>"><s:text name="alert.seleccione.comuna"/></div>

            </div>
        </div>
    </body>
</html>