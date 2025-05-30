<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Consola de Administración de la Inscripción</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/dataTables.bootstrap4.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />  
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-adm-inscripcion.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/inscripcion/commonInscripcionAdministracion-3.0.0.js"></script> 
    </head>
    <body>
        <form id="adm-ins-form" action="#" method="post">                        
            <div class="container-fluid">
                <div class="row">
                    <div id="left-block" class="col-6">
                        <div class="table-container">
                            <div id="cursos-izq" class="col-md-12">
                            </div>
                        </div>

                        <div class="table-container">
                            <div id="nomina-izq" class="col-md-12">
                            </div>
                        </div>

                        <div class="button-container">
                            <div class="transfer-row button" id="to-right">
                                <i  class="fa fa-arrow-right"></i> Transferir &nbsp;<i class="fa fa-arrow-right"></i>
                            </div>
                            <div class="delete-row button" id="delete-left">
                                <i class="fa fa-trash"></i> Eliminar
                            </div>
                        </div>
                    </div>  

                    <div id="right-block" class="col-6">
                        <div class="table-container">
                            <div id="cursos-der"  class="col-12">
                            </div>
                        </div>

                        <div class="table-container">
                            <div id="nomina-der" class="col-12">
                            </div>
                        </div>

                        <div class="button-container">
                            <div class="transfer-row button" id="to-left">
                                <i class="fa fa-arrow-left"></i> Transferir &nbsp;<i class="fa fa-arrow-left"></i>
                            </div>
                            <div class="delete-row button" id="delete-right">
                                <i class="fa fa-trash"></i> Eliminar
                            </div>
                        </div>                        
                    </div>
                </div>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value=""/>
            </div>
        </form>
        <div class="modal fade" id="msg-error" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.msg"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="msg-error-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>              

        <!-- Modal para profesor -->
        <div class="modal fade" id="profesor" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Profesor</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div id="profesor-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="confirmacionLeft" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="confirmation.eliminacion.inscripcion"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="removeIzqInscripcion();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="confirmacionRight" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="confirmation.eliminacion.inscripcion"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="removeDerInscripcion();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
