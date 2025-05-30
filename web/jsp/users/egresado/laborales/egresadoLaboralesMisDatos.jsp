<%--
    Document   : egresadoLaboralesMisDatos
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
        <title>Datos Laborales del Egresado</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/users/egresado/laborales/egresadoLaboralesMisDatos-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.mis.antecedentes.laborales"/>
        </div>

        <div id="central-div">
            <div id="content-div">
                <div class="buttons-div">
                    <button id="add-button" name="add-button" class="btn btn-light" title="Agregar Laboral" type="button">
                        <span class="fa fa-plus" aria-hidden="true"></span><span class="hidden-xs"> <s:text name="label.button.append"/></span>
                    </button>
                </div>

                <br /><br />
                <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>&nbsp;</th>
                            <th><s:text name="label.name.empleador"/></th>
                            <th><s:text name="label.cargo"/></th>
                            <th><s:text name="label.date.from"/></th>
                            <th><s:text name="label.date.to"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.egresadoSession.fichaLaboralList" status="row">
                            <tr>
                                <td>
                                    <a href="EgresadoLaboralesViewLaboral?pos=<s:property value="#row.count -1"/>&key=<s:property value="key"/>" title="Editar">
                                        <span class="fa fa-pencil" aria-hidden="true"></span></a>
                                    <a href="EgresadoLaboralesDeleteLaboral?pos=<s:property value="#row.count -1"/>&key=<s:property value="key"/>" title="Elimiar">
                                        <span class="fa fa-trash" aria-hidden="true"></span></a>
                                </td>
                                <td>
                                    <a href="EgresadoLaboralesViewLaboral?pos=<s:property value="#row.count -1"/>&key=<s:property value="key"/>">
                                            <s:property value="alumnoEmpleador.empleador.empNombre"/>
                                    </a>
                                </td>
                                <td>
                                    <s:property value="flabCargo"/>
                                </td>
                                <td>
                                    <s:property value="flabDesdeMes"/> - <s:property value="flabDesdeAgno"/>
                                </td>
                                <td>
                                    <s:property value="flabHastaMes"/> - <s:property value="flabHastaAgno"/>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                        </div>



                <form id="ficha-form" action="#">
                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>