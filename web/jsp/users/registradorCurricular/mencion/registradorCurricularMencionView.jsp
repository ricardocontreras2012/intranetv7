<%-- 
    Document   : registradorCurricularMencionView
    Created on : 11-12-2024, 13:14:36
    Author     : Usach
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Parámetros x Mención</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/mencion/registradorCurricularMencionView-3.0.1.js"></script>
    </head>
    <body>
        <div class="title-div">
            Parámetros de <s:property value="#session.registradorSession.mencion.getNombreCarreraFull()"/>&nbsp;<s:property value="#session.registradorSession.mencion.getId().getMenCodCar()"/>&nbsp;<s:property value="#session.registradorSession.mencion.getId().getMenCodMen()"/>
        </div>

        <button id="save-button" title="Grabar" type="button" class="btn btn-light" ><span class="fa fa-save" aria-hidden="true"></span>
            &nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
        </button>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <form id="parametros-form" action="#" method="post">                       
                        <!-- Fila para Año Actual -->
                        <div class="form-group row mb-1">
                            <label for="pmenAgnoAct" class="col-sm-2 col-form-label">Año Actual</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="pmenAgnoAct" id="pmenAgnoAct" value="<s:property value="#session.registradorSession.parametroMencion.pmenAgnoAct"/>" />
                            </div>
                        </div>

                        <!-- Fila para Semestre Actual -->
                        <div class="form-group row mb-1">
                            <label for="pmenSemAct" class="col-sm-2 col-form-label">Semestre Actual</label>
                            <div class="col-sm-1">
                                <input class="form-control" name="pmenSemAct" id="pmenSemAct" value="<s:property value="#session.registradorSession.parametroMencion.pmenSemAct"/>" />
                            </div>
                        </div>

                        <!-- Fila para Año Calificaciones -->
                        <div class="form-group row mb-1">
                            <label for="pmenAgnoCal" class="col-sm-2 col-form-label">Año Calificaciones</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="pmenAgnoCal" id="pmenAgnoCal" value="<s:property value="#session.registradorSession.parametroMencion.pmenAgnoCal"/>" />
                            </div>
                        </div>

                        <!-- Fila para Semestre Calificaciones -->
                        <div class="form-group row mb-1">
                            <label for="pmenSemCal" class="col-sm-2 col-form-label">Semestre Calificaciones</label>
                            <div class="col-sm-1">
                                <input class="form-control" name="pmenSemCal" id="pmenSemCal" value="<s:property value="#session.registradorSession.parametroMencion.pmenSemCal"/>" />
                            </div>
                        </div>

                        <!-- Fila para Año Encuesta -->
                        <div class="form-group row mb-1">
                            <label for="pmenAgnoEnc" class="col-sm-2 col-form-label">Año Encuesta</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="pmenAgnoEnc" id="pmenAgnoEnc" value="<s:property value="#session.registradorSession.parametroMencion.pmenAgnoEnc"/>" />
                            </div>
                        </div>

                        <!-- Fila para Semestre Encuesta -->
                        <div class="form-group row mb-1">
                            <label for="pmenSemEnc" class="col-sm-2 col-form-label">Semestre Encuesta</label>
                            <div class="col-sm-1">
                                <input class="form-control" name="pmenSemEnc" id="pmenSemEnc" value="<s:property value="#session.registradorSession.parametroMencion.pmenSemEnc"/>" />
                            </div>
                        </div>

                        <!-- Fila para Año Inscripción -->
                        <div class="form-group row mb-1">
                            <label for="pmenAgnoIns" class="col-sm-2 col-form-label">Año Inscripción</label>
                            <div class="col-sm-2">
                                <input class="form-control" name="pmenAgnoIns" id="pmenAgnoIns" value="<s:property value="#session.registradorSession.parametroMencion.pmenAgnoIns"/>" />
                            </div>
                        </div>

                        <!-- Fila para Semestre Inscripción -->
                        <div class="form-group row mb-1">
                            <label for="pmenSemIns" class="col-sm-2 col-form-label">Semestre Inscripción</label>
                            <div class="col-sm-1">
                                <input class="form-control" name="pmenSemIns" id="pmenSemIns" value="<s:property value="#session.registradorSession.parametroMencion.pmenSemIns"/>" />
                            </div>
                        </div>

                        <!-- Fila para Inicio Postulación -->
                        <div class="form-group row mb-1">
                            <label for="pmenInsPostInicio" class="col-sm-2 col-form-label">Inicio Postulación</label>
                            <div class="col-sm-3">
                                <input class="form-control" type="date" name="pmenInsPostInicio" id="pmenInsPostInicio" value="<s:date name="#session.registradorSession.parametroMencion.pmenInsPostInicio" format="yyyy-MM-dd"/>"/>
                            </div>
                        </div>

                        <!-- Fila para Término Postulación -->
                        <div class="form-group row mb-1">
                            <label for="pmenInsPostTermino" class="col-sm-2 col-form-label">Término Postulación</label>
                            <div class="col-sm-3">
                                <input class="form-control" type="date" name="pmenInsPostTermino" id="pmenInsPostTermino" value="<s:date name="#session.registradorSession.parametroMencion.pmenInsPostTermino" format="yyyy-MM-dd"/>"/>
                            </div>
                        </div>

                        <!-- Fila para Termino de la Administración -->
                        <div class="form-group row mb-1">
                            <label for="pmenInsAdmTermino" class="col-sm-2 col-form-label">Término Administración</label>
                            <div class="col-sm-3">
                                <input class="form-control" type="date" name="pmenInsAdmTermino" id="pmenInsModInicio" value="<s:date name="#session.registradorSession.parametroMencion.pmenInsAdmTermino" format="yyyy-MM-dd"/>"/>
                            </div>
                        </div>

                        <!-- Fila para Término Modificación -->
                        <div class="form-group row mb-1">
                            <label for="pmenInsModTermino" class="col-sm-2 col-form-label">Término Modificación</label>
                            <div class="col-sm-3">
                                <input class="form-control" type="date" name="pmenInsModTermino" id="pmenInsModTermino" value="<s:date name="#session.registradorSession.parametroMencion.pmenInsModTermino" format="yyyy-MM-dd"/>"/>
                            </div>
                        </div>

                        <!-- Fila para Término Eliminación -->
                        <div class="form-group row mb-1">
                            <label for="pmenInsEliminTermino" class="col-sm-2 col-form-label">Término Eliminación</label>
                            <div class="col-sm-3">
                                <input class="form-control" type="date" name="pmenInsEliminTermino" id="pmenInsEliminTermino" value="<s:date name="#session.registradorSession.parametroMencion.pmenInsEliminTermino" format="yyyy-MM-dd"/>"/>
                            </div>
                        </div>

                        <!-- Fila para Lock de la inscripción -->
                        <div class="form-group row mb-1">
                            <label for="pmenInsLock">Lock de Inscripción:</label>
                            <select class="form-control" name="pmenInsLock" id="pmenInsEliminTermino">
                                <!-- Opción nula representada por valor vacío -->
                                <option value="" <s:if test="#session.registradorSession.parametroMencion.pmenInsLock == null || #session.registradorSession.parametroMencion.pmenInsLock == ''">selected</s:if>></option>
                                    <!-- Opción "L" -->
                                    <option value="L" <s:if test="#session.registradorSession.parametroMencion.pmenInsLock == \"L\"">selected</s:if>>L</option>
                                </select>

                                <div id="hidden-input-div">
                                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                                <input type="hidden" id="pmenCodCar" name="pmenCodCar" value="<s:property value="#session.registradorSession.mencion.id.menCodCar"/>"/>
                                <input type="hidden" id="pmenCodMen" name="pmenCodMen" value="<s:property value="#session.registradorSession.mencion.id.menCodMen"/>"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
