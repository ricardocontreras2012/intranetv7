<%-- 
    Document   : CommonRCurricularGetMencion
    Created on : 13-12-2023, 16:54:18
    Author     : Javier Frez V.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Menciones</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/datePicker/gijgo-1.9.13.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-1.9.13.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/datePicker/gijgo-messages.es-es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/mencion/CommonRCurricularGetMencion-1.0.0.js"></script>
    </head>
    <body class="inner-body" onload="blockBack();onLoad();">
        <div class="title-div">
            <p>Menciones</p>
        </div>
        <div class="container container-menu" style="padding-bottom: 10px;">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <button id="add-button" title="Agregar mención" type="button" class="btn btn-light" >
                            <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"> Mención</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <s:form id="menciones-form" action="#" theme="bootstrap">
            <div class="data-tables-container" style="margin-top: 10px;">
                <table id="menciones-table" class="table table-striped dataTable table-bordered" style="width:100%">
                    <thead>
                        <tr>
                            <th scope="col">
                                Codigo de carrera
                            </th>
                            <th scope="col">
                                Codigo de mención
                            </th>
                            <th scope="col">
                                Nombre
                            </th>
                            <th scope="col">
                                Unidad
                            </th>
                            <th scope="col">
                                Opciones de mención
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).mencionList" status="row">
                            <tr>
                                <td style="width: 10%;">
                                    <a id="men0_<s:property value="#row.count -1"/>">
                                        <s:property value="id.menCodCar"/>
                                    </a>
                                </td>
                                <td style="width: 10%;">
                                    <a id="men1_<s:property value="#row.count -1"/>">
                                        <s:property value="id.menCodMen"/>
                                    </a>
                                </td>
                                <td style="width: 30%;">
                                    <a id="men2_<s:property value="#row.count -1"/>">
                                        <s:property value="menNom"/>
                                    </a>
                                </td>
                                <td style="width: 40%;">
                                    <a id="men3_<s:property value="#row.count -1"/>">
                                        <s:property value="menUnidad.uniNom"/>
                                    </a>
                                    <s:if test="menUnidad == null">
                                        <div style="float: right;">
                                            <button title="Agregar unidad" type="button" onClick="showModalOpcionAddUnidad('<s:property value="id.menCodCar"/>', '<s:property value="id.menCodMen"/>', '<s:property value="menNom"/>')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-plus-square"></span></button>
                                        </div>
                                    </s:if>
                                    <s:else>
                                    <div style="float: right;">
                                        <button title="Editar unidad" type="button" onClick="showModalEditUnidad('<s:property value="id.menCodCar"/>', '<s:property value="id.menCodMen"/>', '<s:property value="menUnidad.uniCod"/>', '<s:property value="menUnidad.uniNom"/>', '<s:property value="menUnidad.uniCC"/>', '<s:property value="menUnidad.uniUrl"/>', '<s:property value="menUnidad.uniTipo"/>', '<s:property value="menUnidad.uniColorSala"/>', '<s:property value="menUnidad.atributoUniAcadMayor"/>', '<s:property value="menUnidad.atributoUniAdmMayor"/>', '<s:property value="menUnidad.atributoUniMayor"/>', '<s:property value="menUnidad.atributoUniSuperior"/>')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-pencil-square-o"></span></button>
                                        <button title="Eliminar unidad" type="button" onClick="showModalEliminarUnidad('<s:property value="id.menCodCar"/>', '<s:property value="id.menCodMen"/>', '<s:property value="menUnidad.uniCod"/>')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-trash"></span></button>
                                    </div>
                                    </s:else>
                                </td>
                                <td style="width: 10%;">
                                    <button title="Editar mención" type="button" onClick="showModalEditMencion('<s:property value="id.menCodCar"/>', '<s:property value="id.menCodMen"/>', '<s:property value="menNom"/>', '<s:property value="menPrefijo"/>', '<s:property value="menPlanComun"/>')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-pencil-square-o"></span></button>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </s:form>
        <div id="hidden-input-div">
            <input type="hidden" id="actual_menCodCar" name="actual_menCodCar" />
            <input type="hidden" id="actual_menCodMen" name="actual_menCodMen" />
            <input type="hidden" id="actual_uniCod" name="actual_uniCod" />
        </div>
        <div class="modal fade" id="modalAddMencion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header"> 
                        <h5 class="modal-title">Agregar mención</h5>
                        <button id="close-button" type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12" style="flex: 50%;">
                                <div class="form-group">
                                    <label for="addMencion_menCodCar">Codigo de carrera</label>
                                    <input style="width:70%;" size="3" class="form-control" id="addMencion_menCodCar" name="addMencion_menCodCar" oninput="this.value=this.value.slice(0, 5);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 50%;">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12" style="flex: 50%;">
                                <div class="form-group">
                                    <label for="addMencion_menPrefijo">Prefijo</label>
                                    <select class="form-control" id="addMencion_menPrefijo" name="addMencion_menPrefijo">
                                        <option value="N" selected>
                                            N
                                        </option>
                                        <option value="S">
                                            S
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 50%;">
                                <div class="form-group">
                                    <label for="addMencion_menPlanComun">Plan común</label>
                                    <select class="form-control" id="addMencion_menPlanComun" name="addMencion_menPlanComun">
                                        <option value="N" selected>
                                            N
                                        </option>
                                        <option value="S">
                                            S
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="addMencion_menNom">Nombre de la mención</label>
                                    <textarea name="addMencion_menNom" id="addMencion_menNom" class="form-control" maxlength="300" rows="5" /></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="crearMencion-button" title="Crear mención" type="button" class="btn btn-light" >
                            <span class="fa fa-lock" aria-hidden="true"></span>
                            &nbsp; <span class="hidden-xs">Crear</span>
                        </button>
                        <button id="close-button" type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalEditMencion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Editar mención</h5>
                        <button id="close-button" type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12" style="flex: 50%;">
                                <div class="form-group">
                                    <label for="editMencion_menCodCar">Codigo de carrera</label>
                                    <input disabled="disabled" style="width:70%;" size="3" class="form-control" id="editMencion_menCodCar" name="editMencion_menCodCar" oninput="this.value=this.value.slice(0, 5);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 50%;">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12" style="flex: 50%;">
                                <div class="form-group">
                                    <label for="editMencion_menPrefijo">Prefijo</label>
                                    <select class="form-control" id="editMencion_menPrefijo" name="editMencion_menPrefijo">
                                        <option value="N" selected>
                                            N
                                        </option>
                                        <option value="S">
                                            S
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 50%;">
                                <div class="form-group">
                                    <label for="editMencion_menPlanComun">Plan común</label>
                                    <select class="form-control" id="editMencion_menPlanComun" name="editMencion_menPlanComun">
                                        <option value="N" selected>
                                            N
                                        </option>
                                        <option value="S">
                                            S
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="editMencion_menNom">Nombre de la mención</label>
                                    <textarea name="editMencion_menNom" id="editMencion_menNom" class="form-control" maxlength="300" rows="5" /></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="editarMencion-button" title="Editar mención" type="button" class="btn btn-light" >
                            <span class="fa fa-lock" aria-hidden="true"></span>
                            &nbsp; <span class="hidden-xs">Guardar</span>
                        </button>
                        <button id="close-button" type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalAddUnidad" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header"> 
                        <h5 class="modal-title">Agregar Unidad</h5>
                        <button id="close-button" type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="addUnidad_uniCod">Cod. Unidad</label>
                                    <input style="width:100%;" size="3" class="form-control" id="addUnidad_uniCod" name="addUnidad_uniCod" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="addUnidad_uniCC">Centro costo</label>
                                    <input style="width:100%;" size="3" class="form-control" id="addUnidad_uniCC" name="addUnidad_uniCC" oninput="this.value=this.value.slice(0, 3);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="addUnidad_uniMayor">Mayor</label>
                                    <input style="width:100%;" size="3" class="form-control" id="addUnidad_uniMayor" name="addUnidad_uniMayor" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="addUnidad_uniAcadMayor">Acad. Mayor</label>
                                    <input style="width:100%;" size="3" class="form-control" id="addUnidad_uniAcadMayor" name="addUnidad_uniAcadMayor" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="addUnidad_uniAdmMayor">Adm. Mayor</label>
                                    <input style="width:100%;" size="3" class="form-control" id="addUnidad_uniAdmMayor" name="addUnidad_uniAdmMayor" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="addUnidad_uniTipo">Tipo</label>
                                    <input style="width:100%;" size="3" class="form-control" id="addUnidad_uniTipo" name="addUnidad_uniTipo" oninput="this.value=this.value.slice(0, 4);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="addUnidad_uniSuperior">Superior</label>
                                    <input style="width:100%;" size="3" class="form-control" id="addUnidad_uniSuperior" name="addUnidad_uniSuperior" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="addUnidad_uniColorSala">Color Sala</label>
                                    <input style="width:100%;" class="form-control" id="addUnidad_uniColorSala" name="addUnidad_uniColorSala" type="text" maxlength="6" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="addUnidad_uniNom">Nombre de la unidad</label>
                                    <input style="width:100%;" class="form-control" id="addUnidad_uniNom" name="addUnidad_uniNom" type="text" maxlength="500" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="addUnidad_uniUrl">URL de la unidad</label>
                                    <input style="width:100%;" class="form-control" id="addUnidad_uniUrl" name="addUnidad_uniUrl" type="text" maxlength="1000" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="agregarUnidad-button" title="Agregar unidad" type="button" class="btn btn-light" >
                            <span class="fa fa-lock" aria-hidden="true"></span>
                            &nbsp; <span class="hidden-xs">Agregar</span>
                        </button>
                        <button id="close-button" type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalEditUnidad" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header"> 
                        <h5 class="modal-title">Editar Unidad</h5>
                        <button id="close-button" type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12" style="flex: 30%;">
                                <div class="form-group">
                                    <label for="editUnidad_uniCod">Cod. Unidad</label>
                                    <input disabled="disabled" style="width:100%;" size="3" class="form-control" id="editUnidad_uniCod" name="editUnidad_uniCod" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="editUnidad_uniCC">Centro costo</label>
                                    <input style="width:100%;" size="3" class="form-control" id="editUnidad_uniCC" name="editUnidad_uniCC" oninput="this.value=this.value.slice(0, 3);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 20%;">
                                <div class="form-group">
                                    <label for="editUnidad_uniMayor">Mayor</label>
                                    <input style="width:100%;" size="3" class="form-control" id="editUnidad_uniMayor" name="editUnidad_uniMayor" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="editUnidad_uniAcadMayor">Acad. Mayor</label>
                                    <input style="width:100%;" size="3" class="form-control" id="editUnidad_uniAcadMayor" name="editUnidad_uniAcadMayor" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12" style="flex: 30%;">
                                <div class="form-group">
                                    <label for="editUnidad_uniTipo">Tipo</label>
                                    <input style="width:100%;" size="3" class="form-control" id="editUnidad_uniTipo" name="editUnidad_uniTipo" oninput="this.value=this.value.slice(0, 4);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="editUnidad_uniAdmMayor">Adm. Mayor</label>
                                    <input style="width:100%;" size="3" class="form-control" id="editUnidad_uniAdmMayor" name="editUnidad_uniAdmMayor" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 20%;">
                                <div class="form-group">
                                    <label for="editUnidad_uniSuperior">Superior</label>
                                    <input style="width:100%;" size="3" class="form-control" id="editUnidad_uniSuperior" name="editUnidad_uniSuperior" oninput="this.value=this.value.slice(0, 10);" type="number" />
                                </div>
                            </div>
                            <div class="col-sm-12" style="flex: 25%;">
                                <div class="form-group">
                                    <label for="editUnidad_uniColorSala">Color sala</label>
                                    <input style="width:100%;" class="form-control" id="editUnidad_uniColorSala" name="editUnidad_uniColorSala" type="text" maxlength="6" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="editUnidad_uniNom">Nombre de la unidad</label>
                                    <input style="width:100%;" class="form-control" id="editUnidad_uniNom" name="editUnidad_uniNom" type="text" maxlength="500" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="editUnidad_uniUrl">URL de la unidad</label>
                                    <input style="width:100%;" class="form-control" id="editUnidad_uniUrl" name="editUnidad_uniUrl" type="text" maxlength="1000" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="editarUnidad-button" title="Editar unidad" type="button" class="btn btn-light" >
                            <span class="fa fa-lock" aria-hidden="true"></span>
                            &nbsp; <span class="hidden-xs">Guardar</span>
                        </button>
                        <button id="close-button" type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="avisoCreacion" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="titleErrorMessage" class="modal-title"></h5>
                        <button type="button" class="close-button close close-aviso-creacion" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="errorMessage"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="close-button close-aviso-creacion btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalEliminarUnidad" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Confirmación</h5>
                        <button type="button" class="close-button close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>¿Esta seguro que desea desligar la Unidad?</p>
                    </div>
                    <div class="modal-footer">
                        <button id="modalEliminarUnidad_Desligar" type="button" class="close-button btn btn-light" data-dismiss="modal">Desligar</button>
                        <button type="button" class="close-button btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalOpcionAddUnidad" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Confirmación</h5>
                        <button type="button" class="close-button close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>¿Desea crear una nueva Unidad o utiliza una ya existente?</p>
                    </div>
                    <div class="modal-footer">
                        <button id="modalOpcionAddUnidad_Crear" type="button" class="close-button btn btn-light" data-dismiss="modal">Crear una Unidad</button>
                        <button id="modalOpcionAddUnidad_Utilizar" type="button" class="close-button btn btn-light" data-dismiss="modal">Utilizar una Unidad</button>
                        <button type="button" class="close-button btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalListUnidad" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Seleccione una Unidad</h5>
                        <button type="button" class="close-button close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" style="overflow-y: scroll; height: 500px;">
                        <div class="data-tables-container">
                            <table id="unidades-table" class="table table-striped dataTable table-bordered" style="width:100%">
                                <thead>
                                    <tr>
                                        <th scope="col">
                                            Codigo de unidad
                                        </th>
                                        <th scope="col">
                                            Nombre
                                        </th>
                                        <th scope="col">
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).unidadList" status="row">
                                        <tr>
                                            <td>
                                                <a id="uni0_<s:property value="#row.count -1"/>">
                                                    <s:property value="uniCod"/>
                                                </a>
                                            </td>
                                            <td>
                                                <a id="uni1_<s:property value="#row.count -1"/>">
                                                    <s:property value="uniNom"/>
                                                </a>
                                            </td>
                                            <td>
                                                <button title="Usar Unidad" type="button" onClick="showModalUsedUnidad('<s:property value="uniCod"/>', '<s:property value="uniNom"/>', '<s:property value="uniCC"/>', '<s:property value="uniUrl"/>', '<s:property value="uniTipo"/>', '<s:property value="uniColorSala"/>', '<s:property value="atributoUniAcadMayor"/>', '<s:property value="atributoUniAdmMayor"/>', '<s:property value="atributoUniMayor"/>', '<s:property value="atributoUniSuperior"/>')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-paper-plane"></span></button>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="close-button btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
