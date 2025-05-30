<%--
    Document   : commonMaterialListadoMateriales
    Created on : 19-05-2009, 09:37:02 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Materiales</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/material/commonMaterialListadoMateriales-3.0.4.js"></script>
    </head>
    <body class="inner-body">
        <!--iframe style="width:100%;height:100%;" id="material-iframe" name="material-iframe" frameborder="0" src="">
            </iframe-->
        <s:if test="(#session.genericSession.getWorkSession(key).tipoMaterial==#session.genericSession.userType) || (#session.genericSession.isAutoridad() && #session.genericSession.getWorkSession(key).tipoMaterial==\"PR\")">
            <div class="container container-menu">
                <div class="row">
                    <div id="justified-button-bar" class="col-lg-12">
                        <div class="btn-group">
                            <div class="btn-group">
                                <button id="add-button" title="Nuevo" type="button" class="btn btn-light" onclick="addMaterial();">
                                    <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                                </button>
                            </div>
                            <s:if test="(#session.genericSession.userType==\"PR\" || #session.genericSession.getCurso(key).cursoPropio(#session.genericSession.userType, #session.genericSession.userType, #session.genericSession.rut, #session.genericSession.isAutoridad())) && #session.genericSession.getWorkSession(key).tipoMaterial==\"PR\"">
                                <div class="btn-group">
                                    <button id="other-button" title="Reutilizar" type="button" class="btn btn-light"  onclick="searchMaterial();">
                                        <span class="fa fa-recycle"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.reuse"/></span>
                                    </button>
                                </div>
                            </s:if>
                            <div class="btn-group">
                                <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" onclick="deleteMaterial();">
                                    <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:if>

        <s:form id="material-form" action="#" method="post" accept-charset="UTF-8" enctype="multipart/form-data" theme="bootstrap">
            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            <input type="hidden" id="tipoMaterial" name="tipoMaterial" value="<s:property value="tipoMaterial"/>"/>
            <input type="hidden" id="nTiposDummy" name="nTiposDummy" value="<s:property value="#session.genericSession.getWorkSession(key).tmaterial.size"/>"/>

            <s:iterator value="#session.genericSession.getWorkSession(key).tmaterial" var="tipo" status="rowTipo">
                <p class="lead"><s:property value="tmaDes"/></p>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col" style="width:20px">
                                <input style="height: 12px"
                                       id="checkHeadInput<s:property value="#rowTipo.count -1"/>"
                                       name="checkHeadInput<s:property value="#rowTipo.count -1"/>"
                                       type="hidden"
                                       onclick="checkingHeadTipo('materiales-form', <s:property
                                           value="#rowTipo.count -1"/>);"
                                       value="off"/>
                            </th>
                            <th scope="col"><s:text name="label.descripcion"/></th>
                            <th scope="col" style="width:15%; text-align:center"><s:text name="label.date"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="materiales" status="rowMaterial">
                            <tr style="height:20px; vertical-align:bottom !important">
                                <td style="text-align: center; vertical-align: middle">
                                    <s:if test="#session.genericSession.rut == matRutAutor">
                                        <input type="checkbox"
                                               id="ck_<s:property value="#rowTipo.count -1"/>_<s:property value="#rowMaterial.count -1"/>"
                                               name="ck_<s:property value="#rowTipo.count -1"/>_<s:property value="#rowMaterial.count -1"/>"/>
                                    </s:if>
                                </td>
                                <td style="height:20px; vertical-align:middle !important">
                                    <s:if test="(#session.genericSession.rut == matRutAutor)">
                                        <a class="anchor" onclick="showMaterial(<s:property value="#rowTipo.count -1"/>,<s:property value="#rowMaterial.count -1"/>, '<s:property value="key"/>');"><s:property
                                                value="matDescripcion"/></a>
                                    </s:if>
                                    <s:else>
                                        <s:if test="#session.genericSession.getWorkSession(key).tipoMaterial==\"AL\"">
                                            <a href="CommonMaterialDownLoadMaterial?tipo=<s:property value="#rowTipo.count -1"/>&material=<s:property value="#rowMaterial.count -1"/>&key=<s:property value="key"/>"><s:property
                                                    value="getNombreAlumnoAutor()"/>&nbsp;::&nbsp;<s:property
                                                    value="matDescripcion"/></a>
                                        </s:if>
                                        <s:else>
                                            <a href="CommonMaterialDownLoadMaterial?tipo=<s:property value="#rowTipo.count -1"/>&material=<s:property value="#rowMaterial.count -1"/>&key=<s:property value="key"/>"><s:property
                                                    value="matDescripcion"/></a>
                                        </s:else>
                                    </s:else>
                                </td>
                                <td style="width:15%; height:20px; vertical-align:middle !important; text-align:center">
                                    <s:date name="matFechaHabilitacion" format="dd/MM/yyyy HH:mm:ss"/>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:iterator>
        </s:form>

        <s:if test="hasActionMessages()">
            <div class="modal fade" id="aviso" role="dialog">
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
                            <p><s:actionmessage/></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                $('#aviso').modal('show');
            </script>
        </s:if>

        <div class="modal fade" id="confirmacion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="confirmation.eliminacion.material"/></p>
                    </div>
                    <div class="modal-footer">
                        <button id="remove-button" type="button" class="btn btn-light" onclick="removeMaterial();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>