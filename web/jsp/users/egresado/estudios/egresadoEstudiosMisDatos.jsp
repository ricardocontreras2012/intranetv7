<%--
    Document   : egresadoEstudiosMisDatos
    Created on : 20-08-2014, 05:53:02 PM
    Author     : Alvaro Romero C.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Datos Académicos del Egresado</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/users/egresado/estudios/egresadoEstudiosMisDatos-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.mis.estudios"/>
        </div>

        <div id="central-div">
            <div id="content-div">

                <h4>Estudios Realizados en la Facultad de Administración y Economía.</h4>
                <div id="central-div">
                    <form id="carreras-form" action="#" method="post">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">
                                            <s:text name="label.carrera"/>
                                        </th>
                                        <th scope="col">
                                            <s:text name="label.term.short"/> <s:text name="label.year"/> <s:text name="label.ingreso"/>
                                        </th>
                                        <th scope="col">
                                            <s:text name="label.calidad"/>
                                        </th>
                                        <th scope="col">
                                            <s:text name="label.situacion"/>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).aluCarList" status="row">
                                        <tr>
                                            <td>
                                                <a id="searchAnchor_<s:property value="#row.count -1"/>">
                                                    <%--s:property value="id.acaCodCar"/> - <s:property value="plan.mencion.menNomTit"/--%>
                                                    <!--s:property value="plan.mencion.carrera.tcarrera.tcrNom"/--> <!--s:property value="plan.mencion.menNom"/-->
                                                    <s:property value="id.acaCodCar"/>&nbsp;<s:property value="nombreCarrera"/>
                                                </a>
                                            </td>
                                            <td>
                                                <s:property value="id.acaSemIng"/> <s:property value="id.acaAgnoIng"/>
                                            </td>
                                            <td>
                                                <s:property value="tcalidad.tcaDescrip"/>
                                            </td>
                                            <td>
                                                <s:property value="tsacademica.tsaDescrip"/>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                        <div id="hidden-input-div">
                            <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                        </div>
                    </form>
                </div>
                <br />
                <br />

                <h4>Otros Estudios Realizados Fuera de la Facultad de Administración y Economía.</h4>
                <div class="buttons-div">
                    <button id="add-button" name="add-button" class="btn btn-light" title="Agregar Estudio" type="button">
                        <span class="fa fa-plus" aria-hidden="true"></span><span class="hidden-xs"> <s:text name="label.button.append"/></span>
                    </button>
                </div>

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th><s:text name="label.study.type"/></th>
                                <th><s:text name="label.study.name"/></th>
                                <th><s:text name="label.institution.name"/></th>
                                <th><s:text name="label.date.from"/></th>
                                <th><s:text name="label.date.to"/></th>
                                <th><s:text name="label.calidad"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="#session.egresadoSession.fichaEstudioList" status="row">
                                <tr>
                                    <td>
                                        <a href="EgresadoEstudiosViewEstudio?correl=<s:property value="festCorrel"/>&key=<s:property value="key"/>" title="Editar">
                                            <span class="fa fa-pencil" aria-hidden="true"></span></a>
                                        <a href="EgresadoEstudiosDeleteEstudio?correl=<s:property value="festCorrel"/>&key=<s:property value="key"/>" title="Elimiar">
                                            <span class="fa fa-trash" aria-hidden="true"></span></a>
                                    </td>
                                    <td>
                                        <a href="EgresadoEstudiosViewEstudio?correl=<s:property value="festCorrel"/>&key=<s:property value="key"/>">
                                            <s:property value="testudio.testNombre"/>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="EgresadoEstudiosViewEstudio?correl=<s:property value="festCorrel"/>&key=<s:property value="key"/>">
                                            <s:property value="festNombreEstudio"/>
                                        </a>
                                    </td>
                                    <td>
                                        <s:property value="institucionEducacional.ineNombre"/><s:property value="festOtraInstitucion"/></td>
                                    <td>
                                        <s:property value="festDesdeMes"/> - <s:property value="festDesdeAgno"/></td>
                                    <td>
                                        <s:property value="festHastaMes"/> - <s:property value="festHastaAgno"/></td>
                                    <td>
                                        <s:property value="estadoEstudio.eestNombre"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>


                <form id="reportes-form" action="#">
                    <table>
                        <tr>
                            <td>
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
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>