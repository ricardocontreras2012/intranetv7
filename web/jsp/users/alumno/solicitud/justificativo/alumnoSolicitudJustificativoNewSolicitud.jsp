<%-- 
    Document   : alumnoSolicitudJustificativoNewSolicitud
    Created on : 22-05-2024, 9:35:57
    Author     : Usach
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Generación de Solicitud de Justificativo PEP</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/solicitud/justificativo/alumnoSolicitudJustificativoNewSolicitud-3.0.8.js"></script>
    </head>

    <body class="inner-body">

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="add-button" title="Curso" type="button" class="btn btn-light" >
                                <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs">Curso</span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.enviar"/></span>
                            </button>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>

        <div class="col-12">
            <form id="solicitud-form" action="#" method="post" accept-charset="UTF-8">

                <div class="row row-form mb-2">
                    <div class="col-12 col-md-3 col-lg-2 col-xl-1">
                        Selección Evaluación
                    </div>
                    <div class="col-12 col-md-6 col-lg-4 col-xl-3">
                        <select id="eval" name="eval" class="form-control">
                            <option value="">-- Seleccione una opción --</option>
                            <option value="PEP1">PEP1</option>
                            <option value="PEP2">PEP2</option>
                        </select>
                    </div>
                </div>

                <table id="solicitud-table" class="table table-striped table-bordered">
                    <thead>
                    <th style="width: 50%">Cursos Seleccionados</th>
                    <th></th>                
                    </thead>
                    <tbody></tbody>
                </table>             

                <div id="hidden-input-div" style="display:none">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <div id="options-curso-div">
                        <s:iterator value="#session.genericSession.getWorkSession(key).cursoSolicitudList" status="row">
                            <option value="<s:property value="#row.count -1"/>"><s:property value="getNombreFull()"/> </option>
                        </s:iterator>
                    </div>

                    <input type="hidden" id="id-row" name="id-row" value="0" />
                </div>

                <div class="row row-form mb-2">
                    <div class="col-12 col-md-3 col-lg-2 col-xl-1">
                        Fecha Inicio
                    </div>
                    <div class="col-12 col-md-9 col-lg-10 col-xl-11">
                        <div><input width="150" id="fechaInicio" name="fechaInicio" class="form-control" /></div>
                    </div>
                </div>

                <div class="row row-form mb-2">
                    <div class="col-12 col-md-3 col-lg-2 col-xl-1">
                        Fecha Término
                    </div>
                    <div class="col-12 col-md-9 col-lg-10 col-xl-11">
                        <div><input width="150" id="fechaTermino" name="fechaTermino" class="form-control" /></div>
                    </div>
                </div>
                <div class="row row-form mb-2">
                    <div class="col-12 col-md-3 col-lg-2 col-xl-1">
                        Motivo
                    </div>
                    <div class="col-12 col-md-9 col-lg-10 col-xl-11">
                        <textarea id="glosa" name="glosa" cols="120 rows=5" class="form-control"></textarea>
                    </div>
                </div>                
            </form>                                                              

            <form id="attach-form" action="#" method="post" enctype="multipart/form-data">
                <div class="row row-form mb-2">
                    <div class="col-12 col-md-3 col-lg-2 col-xl-1">
                        Archivo
                    </div>
                    <div class="col-12 col-md-9 col-lg-10 col-xl-11">
                        <input type="file" id="upload" name="upload"  class="form-control-file" multiple>
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    </div>
                </div>
            </form> 
        </div>

        <div class="modal fade" id="aviso-cursos-error" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">ERROR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="alert.seleccione.curso"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="aviso-fechas-error" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">ERROR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"> 
                        <p></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="aviso-archivo-error" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">ERROR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Debe especificar el o los archivos a subir</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="aviso-duplicate-error" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">ERROR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>No debe seleccionar el mismo curso más de una vez</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="aviso-eval-error" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">ERROR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Debe especificar evaluación</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

