<%--
    Document   : secretariaProyectosConvenioShowConvenio
    Created on : 27-11-2020, 9:26:54
    Author     : Ricardo
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Contrato</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-convenio.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/secretariaProyectos/convenio/secretariaProyectosConvenioShowConvenio-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="print-button" title="Imprimir" type="button" class="btn btn-light" >
                                <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.print"/></span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <form id="convenio-form" action="#">
            <div class="container-fluid">
                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        FECHA
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:date name="#session.proyectoSession.convenio.convFecha" format="dd/MM/yyyy"/>
                    </div>
                </div>
                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        RUT
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:property value="#session.proyectoSession.convenio.funcionario.funRut"/>
                    </div>
                </div>
            </div>

            <div id="fun-div" class="container-fluid">
                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        PATERNO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:property value="#session.proyectoSession.convenio.funcionario.funPaterno"/>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        MATERNO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:property value="#session.proyectoSession.convenio.funcionario.funMaterno"/>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        NOMBRE
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left ">
                        <s:property value="#session.proyectoSession.convenio.funcionario.funNombre"/>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        DIRECCIÓN
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:property value="#session.proyectoSession.convenio.funcionario.funDireccion"/>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row row-form row-flex">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        PROYECTO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:property value="#session.proyectoSession.convenio.proyecto.proyCod"/>
                        <s:property value="#session.proyectoSession.convenio.proyecto.proyNom"/>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        FECHA INICIO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:date name="#session.proyectoSession.convenio.convFechaIni" format="dd/MM/yyyy"/>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        FECHA TERMINO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:date name="#session.proyectoSession.convenio.convFechaTer" format="dd/MM/yyyy"/>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        TIPO CONTRATO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                           <s:property value="#session.proyectoSession.convenio.getTipo()"/>
                        </div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        FUNCION
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <textarea id="funcion" name="funcion" maxlength="600" rows="1" cols="100" class="input-form">
                                <s:property value="#session.proyectoSession.convenio.convFuncion"/>
                            </textarea>
                        </div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        MONTO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                       <s:number name="#session.proyectoSession.convenio.convMonto" type="number"/>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        TIPO MONTO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <s:property value="#session.proyectoSession.convenio.convTipoMonto"/>
                        </div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        OBS PAGO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <s:property value="#session.proyectoSession.convenio.convObsPago"/>
                    </div>
                </div>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="rut" name="rut" />
            </div>
        </form>
    </body>
</html>

