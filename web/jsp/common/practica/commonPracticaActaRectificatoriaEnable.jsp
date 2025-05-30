<%--
    Document   : commonPracticaActaRectificatoriaEnable.jsp
    Created on : 25-11-2014, 10:57:52 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Generación de Acta de Práctica</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/practica/commonPracticaActaRectificatoriaEnable-3.0.1.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                Acta Rectificatoria <s:property value="#session.genericSession.getWorkSession(key).practicaList.get(0).asignatura.asiNom"/>
            </div>

            <div class="mb-3">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-light" id="buscar-button" name="buscar-button">
                        <span class="fa fa-search" aria-hidden="true"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                    </button>
                    <button type="button" class="btn btn-light" id="emitir-button" name="emitir-button">
                        <span class="fa fa-lock" aria-hidden="true"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.emitir"/></span>
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <form class="form-horizontal" id="acta-form" action="#" method="post">
                        <div class="form-row">
                            <div class="col-6 col-md-3 col-lg-1 mb-3">
                                <label for="agno"><s:text name="label.year"/></label>
                                <input class="form-control" type="text" id="agno" name="agno" value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>">
                            </div>
                            <div class="col-6 col-md-3 col-lg-1 mb-3">
                                <label for="sem"><s:text name="label.term"/></label>
                                <input class="form-control" type="text" id="sem" name="sem" value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>"></div>
                            <div class="col-12 col-md-5 col-lg-4 mb-3">
                                <label for="practica"><s:text name="label.tipo"/></label>
                                <s:select name="practica"
                                          id="practica"
                                          list="#session.genericSession.getWorkSession(key).asignaturaList"
                                          listKey="asiCod"
                                          listValue="asiNom"
                                          cssClass="form-control"
                                          />
                            </div>
                            <div class="col-6 col-md-4 col-lg-2 mb-3">
                                <label for="porc_emp">Porcentaje Empleador</label>
                                <input class="form-control" type="text" id="porc_emp" name="porc_emp" value="50">
                            </div>
                            <div class="col-6 col-md-4 col-lg-2 mb-3">
                                <label for="porc_coord">Porcentaje Coordinador</label>
                                <input class="form-control" type="text" id="porc_coord" name="porc_coord" value="50">
                            </div>
                        </div>

                        <div class="table-responsive" id="table-div">
                            <table id="nomina-table" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">RUN</th>
                                        <th scope="col">Paterno</th>
                                        <th scope="col">Materno</th>
                                        <th scope="col">Nombres</th>
                                        <th scope="col">Nota Empleador</th>
                                        <th scope="col">Nota Coordinador</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).practicaList" status="row">
                                        <tr>
                                            <td><s:property value="aluCar.alumno.aluRut"/>-<s:property value="aluCar.alumno.aluDv"/></td>
                                            <td><s:property value="aluCar.alumno.aluPaterno"/></td>
                                            <td><s:property value="aluCar.alumno.aluMaterno"/></td>
                                            <td><s:property value="aluCar.alumno.aluNombre"/></td>
                                            <td><input class="form-control" name="emp_<s:property value="#row.count -1"/>" id="emp_<s:property value="#row.count -1"/>"></td>
                                            <td><input class="form-control" name="coord_<s:property value="#row.count -1"/>" id="coord_<s:property value="#row.count -1"/>"></td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                        <div id="hidden-input-div">
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </div>
                    </form>
                </div>
            </div>

            <div class="modal fade" id="modal-confirmacion" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 id="mySmallModalLabel" class="modal-title">Emisión de Acta</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p><s:text name="confirmation.emision.acta"/></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="emitirActaAceptar();">Aceptar</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="modal-error" role="dialog">
                <div class="modal-dialog" role="document">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Aviso</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Porcentajes deben sumar 100</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" data-dismiss="modal">Aceptar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
