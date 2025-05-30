<%-- 
    Document   : teleTrabajoGetMiEvidencia
    Created on : 05-10-2023, 10:15:21
    Author     : Felipe and Javier
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Tabla de evidencias</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/upload/custominputfile.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/dragNDropStyle.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/upload/custominputfile.min-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/teleTrabajoGetMiEvidencia-3.1.0.js"></script>
    </head>
    <body class="inner-body">
        
        <div class="title-div">
            Lista de Evidencia &nbsp;
        </div>

        <div class="container container-menu">
            <div class="row">
                <s:if test="#session.teleTrabajoSession.actividadTeletrabajo.atelEstado == \"A\" || #session.teleTrabajoSession.actividadTeletrabajo.atelEstado == \"E\"">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <button id="add-button" title="Agregar evidencia" type="button" class="btn btn-light" >
                                <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs">Evidencia</span>
                            </button>
                        </div>
                    </div>
                </s:if>
            </div>
        </div>

        
            <table id="evidencia-table" class="table table-striped table-bordered">
                <thead>
                <th style="width: 70%">Descripción</th>
                <th>Nombre del archivo</th>
                <th></th>
                </thead>
                <tbody>
                    <s:iterator value="#session.teleTrabajoSession.evidenciaList" status="row">
                        <tr id="evidenciaRow_<s:property value="id.etatelCorrel"/>">
                            <s:if test="#session.teleTrabajoSession.actividadTeletrabajo.atelEstado == \"A\" || #session.teleTrabajoSession.actividadTeletrabajo.atelEstado == \"E\"">
                                <td>
                                    <form id="descripcion<s:property value="id.etatelCorrel"/>-form" action="#" method="post" accept-charset="UTF-8">
                                        <input type="text" id="otro_<s:property value="id.etatelCorrel"/>" name="descripcionEvidencia" value="<s:property value="etatelDes"/>" class="form-control" maxlength="200"/>
                                    </form>
                                </td>
                                <td><s:property value="etatelFile"/></td>
                                <td>
                                    <button id="upload-button_<s:property value="id.etatelCorrel"/>" title="Subir evidencia" type="button" onClick="uploadFileToRow(<s:property value="id.etatelCorrel"/>)" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-pencil-square-o"></span>&nbsp; <span class="hidden-xs"></span></button>
                                    <s:if test="etatelFile != null"> 
                                        <button id="download-button_<s:property value="id.etatelCorrel"/>" title="Descargar" type="button" onClick="downloadFileToRow(<s:property value="id.etatelCorrel"/>)" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-download"></span>&nbsp; <span class="hidden-xs"></span></button>
                                    </s:if>
                                    <button id="delete-button_<s:property value="id.etatelCorrel"/>" title="Eliminar" type="button" onClick="delEvidencia(<s:property value="id.etatelCorrel"/>)" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"></span></button>
                                </td>
                            </s:if>
                            <s:else>
                                <td>
                                    <input type="text" readonly="readonly" id="otro_<s:property value="id.etatelCorrel"/>" name="descripcionEvidencia" value="<s:property value="etatelDes"/>" class="form-control" maxlength="200"/>
                                </td>
                                <td><s:property value="etatelFile"/></td>
                                <td>
                                    <s:if test="etatelFile != null"> 
                                        <button id="download-button_<s:property value="id.etatelCorrel"/>" title="Descargar" type="button" onClick="downloadFileToRow(<s:property value="id.etatelCorrel"/>)" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-download"></span>&nbsp; <span class="hidden-xs"></span></button>
                                    </s:if>
                                </td>
                            </s:else>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>                                                         
        <form id="evidencia-form" action="#" method="post" accept-charset="UTF-8">
            <div id="hidden-input-div" style="display:none">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="sizeList" name="sizeList" value="<s:property value="#session.teleTrabajoSession.evidenciaList.size"/>" />
                <input type="hidden" id="formEscogido" name="formEscogido" />
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="posTarea" name="posTarea" />
            </div>
        </form>
        <div class="modal fade" id="creacionEvidencia" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><div id="title_sala_div"></div></h5>
                        <button type="button" class="close-button close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h5 id="ventanaEvidencia"></h5>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form-row">
                                    <label for="motivo">Suba su evidencia</label>
                                    <form id="form_dragndropFiles" class="box" method="post" action="" enctype="multipart/form-data">
                                        <div class="box__input">
                                            <input class="box__file" type="file" name="uploadedFile" id="file" data-multiple-caption="{count} files selected" multiple />
                                            <label for="file"><strong>Escoja un archivo</strong><span class="box__dragndrop"> o arrastrelo aquí</span>!</label>
                                        </div>
                                        <div class="box__uploading">Subiendo…</div>
                                        <div class="box__success">Listo!</div>
                                        <div class="box__error">Error! <span></span>.</div>
                                        <div>
                                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="evidencia-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button id="subirBoton" class="btn btn-light" type="button">Subir</button>
                        <button type="button" class="close-button btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="avisoUpload" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"></h5>
                        <button type="button" class="close-button close close-aviso-upload" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="errorMessage"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="close-button close-aviso-upload btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>