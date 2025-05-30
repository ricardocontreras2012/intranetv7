<%--
    Document   : secretariaDocenteConvalidacionSolicitudes
    Created on : 11-06-2018, 11:25:07
    Author     : rcontreras
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Solicitudes de Convalidación</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/secretariaDocente/convalidacion/secretariaDocenteConvalidacionSolicitudes-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            SOLICITUDES <s:property value="#session.genericSession.getWorkSession(key).aluCar.alumno.nombre"/>
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                                <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" >
                                <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="data-tables-container">
            <table id="nomina-table" class="table table-striped table-bordered dataTable">
                <thead>
                    <tr>
                        <th scope="col"><s:text name="label.date"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.secretariaSession.convalidaciones" status="row">
                        <tr>
                            <td><a id="sol_<s:property value="#row.count -1"/>"><s:property value="cosFecha"/></a></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
        <form id="solicitudes-form" action="#">
            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            <input type="hidden" id="pos" name="pos"/>
        </form>
    </body>
</html>
