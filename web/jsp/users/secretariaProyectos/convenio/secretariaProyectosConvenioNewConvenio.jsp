<%--
    Document   : secretariaProyectosConvenioNewConvenio
    Created on : 02-12-2020, 10:25:04
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
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />        
        <link rel="stylesheet" href="/intranetv7/js/timePicker/timepicker.css" type="text/css" />        
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>                
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.maskedinput.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/secretariaProyectos/convenio/secretariaProyectosConvenioNewConvenio-3.0.4.js"></script>
    </head>
    <body>
        <div class="title-div">
            Contrato
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-1 col-xl-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
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
                        <div><input width="150" id="fecha" name="fecha" class="input-form"/></div>
                    </div>
                </div>
                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        RUT
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <input class="input-form" type="text" id="rutdv" name="rutdv" placeholder="123456789-0" onChange="enableProyecto()"/> 
                        <button id="search-rut-button" title="Buscar" type="button" class="btn btn-light" >
                            <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                        </button>
                    </div>
                </div>
            </div>

            <div id="fun-div" class="container-fluid">
                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        PATERNO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <input class="input-form" type="text" id="paterno" name="paterno" /> 
                        <button id="search-paterno-button" title="Buscar" type="button" class="btn btn-light" >
                            <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                        </button>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        MATERNO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div id="materno"></div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        NOMBRE
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left ">
                        <div id="nombres"></div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        DIRECCIÓN
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <textarea id="direccion" name="direccion" maxlength="200" rows="1" cols="100" class="input-form"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row row-form row-flex">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        PROYECTO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <select class="input-form" id="proyecto" name="proyecto" class="input" onchange="clearBasic();">
                                <option value="">Seleccione Proyecto</option>
                                <s:iterator value="#session.proyectoSession.proyectoList" status="row">
                                    <option value="<s:property value="proyCod"/>"> <s:property value="proyCod"/>&nbsp;(<s:property value="proyNom"/>)</option>
                                </s:iterator>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        RUT FIRMA
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <input class="input-form" type="text" id="rutdvFirma" name="rutdvFirma" placeholder="123456789-0"/>
                        <button id="search-firma-button" title="Buscar" type="button" class="btn btn-light" >
                            <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                        </button>
                        <div id="nombre-firmma-div"></div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        FECHA INICIO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div><input width="150" id="fechaInicio" name="fechaInicio" class="input-form" onchange="checkDates()"/></div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        FECHA TERMINO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div><input width="150" id="fechaTermino" name="fechaTermino" class="input-form" onchange="checkDates()"/></div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        TIPO CONTRATO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <select class="input-form" id="tipoContrato" name="tipoContrato">
                                <option value="">Seleccione Tipo Contrato</option>
                                <option value="AYU">Ayudantía</option>
                                <option value="DPG">Dirección Programa</option>
                                <option value="DOC">Docencia</option>
                                <option value="EXT">Externo</option>                                
                                <option value="SEC">Secretaria</option>
                                <option value="SER">Servicio</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        FUNCION
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <textarea id="funcion" name="funcion" maxlength="600" rows="1" cols="100" class="input-form"></textarea>
                        </div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        MONTO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <input type="text" id="monto" name="monto" class="input-form"/>
                        </div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        TIPO MONTO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <select name="tipoMonto" id="tipoMonto" class="input-form">
                                <option value="G">Global</option>
                                <option value="M">Mensual</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row row-form">
                    <div class="col-sm-6 col-md-1 col-lg-1 col-xl-1 text-left">
                        OBS PAGO
                    </div>
                    <div class="col-sm-6 col-md-11 col-lg-11 col-xl-11 text-left">
                        <div>
                            <textarea id="obsPago" name="obsPago" maxlength="500" rows="1" cols="100" class="input-form"></textarea>
                        </div>
                    </div>
                </div>                                
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>                
                <input type="hidden" id="rut" name="rut" />
                <input type="hidden" id="rutFirma" name="rutFirma" />
                <input type="hidden" id="idCurso" name="idCurso" />
                <input type="hidden" id="dummy" name="dummy" />
                <input type="hidden" id="newRow" name="newRow" value="0"/>
                <div id="jefe-div" style="display: none;"></div>

                <input type="hidden" id="rutAux" name="rutAux" />
                <input type="hidden" id="rutFirmaAux" name="rutFirmaAux" />
                <input type="hidden" id="direccionAux" name="direccionAux" />
                <input type="hidden" id="fechaAux" name="fechaAux" />
                <input type="hidden" id="proyectoAux" name="proyectoAux" />
                <input type="hidden" id="fechaInicioAux" name="fechaInicioAux" />
                <input type="hidden" id="fechaTerminoAux" name="fechaTerminoAux" />
                <input type="hidden" id="tipoContratoAux" name="tipoContratoAux" />
                <input type="hidden" id="funcionAux" name="funcionAux" />
                <input type="hidden" id="montoAux" name="montoAux" />
                <input type="hidden" id="tipoMontoAux" name="tipoMontoAux" />
                <input type="hidden" id="obsPagoAux" name="obsPagoAux" />
            </div>

            <div class="modal fade" id="horario-modal" role="dialog">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">HORARIO CONVENIO</h5>                            
                        </div>
                        <div class="modal-body" id="horario-table-div">                            
                        </div>
                        <div class="modal-footer">
                            <button id="add-horario-button" title="Nuevo" type="button" class="btn btn-light" >
                                <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                            </button>
                            <button id="accept-horario-button" title="Aceptar" type="button" class="btn btn-light" >
                                <span class="fa fa-check"></span>&nbsp; <span class="hidden-xs">Aceptar</span>
                            </button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="error-modal" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">AVISO</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div id="error-div"></div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="funcionario-search-modal" role="dialog">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">BÚSQUEDA FUNCIONARIO</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div id="funcionario-search-modal-div"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="curso-search-modal" role="dialog">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">BÚSQUEDA CURSOS</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div id="curso-search-modal-div">
                                <input type="text" id="agno" name="agno" value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>"/>
                                <input type="text" id="sem" name="sem" value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>"/>
                                <button id="search-curso-button" title="Buscar" type="button" class="btn btn-light" >
                                    <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                                </button>
                            </div>
                            <div id="curso-list-div">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>           

            <div class="modal fade" id="add-horario-modal" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div id="hor-title">Horario</div>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div id="horario-modal-div"></div>
                            <input width="100" id="timeInicio" name="timeInicio" placeholder="hh:mm" class="input-form"/>
                            <input width="100" id="timeTermino" name="timeTermino" placeholder="hh:mm" class="input-form"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="new-horario-event">OK</button>
                        </div>
                    </div>
                </div>
            </div>            
        </form>

        <div style="display:none" id="diaDummy-div">
            <select id="dia" name="dia" class="input-form">
                <s:iterator value="#session.genericSession.diaList" status="row">
                    <option value="<s:property value="diaCod"/>" class="form-control"><s:property value="diaNom"/></option>
                </s:iterator>
            </select>
        </div>
    </body>
</html>
