<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Certificados x Emitir</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/registradorCurricular/certificacion/registradorCurricularCertificadosGetGlosa-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            Certificados x Emitir
        </div>

        <form id="nomina-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="nomina-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col">RUT</th>
                            <th scope="col">Paterno</th>
                            <th scope="col">Materno</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Carrera</th>
                            <th scope="col">Certificado</th>
                            <th scope="col" style="display:none;"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.registradorSession.certificadoList" status="row">
                            <tr>
                                <td><a onclick="getObs('<s:property value="solicitud.aluCar.alumno.aluPaterno"/> <s:property value="solicitud.aluCar.alumno.aluMaterno"/> <s:property value="solicitud.aluCar.alumno.aluNombre"/>', '<s:property value="tcertificado.tceDes"/>','<s:property value="getTramite()"/>','<s:property value="getObs()"/>', <s:property value="#row.count -1"/>)"><s:property value="solicitud.aluCar.alumno.aluRut"/>-<s:property value="solicitud.aluCar.alumno.aluDv"/></a></td>
                                <td><s:property value="solicitud.aluCar.alumno.aluPaterno"/></td>
                                <td><s:property value="solicitud.aluCar.alumno.aluMaterno"/></td>
                                <td><s:property value="solicitud.aluCar.alumno.aluNombre"/></td>
                                <td><s:property value="solicitud.aluCar.id.acaCodCar"/></td>
                                <td><s:property value="tcertificado.tceDes"/></td>
                                <td style="display:none;"><s:property value="getObs()"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <div class="modal fade" id="glosa-modal" role="dialog">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Glosa de Certificado</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div id="select-div">                                    
                                    <p><input id="alu_id" readonly size="80"></p>
                                    <p><input id="cert_id" readonly size="80"></p>
                                    <p><input id="tramite_id" readonly size="80"></p>
                                    <p><input id="obs" readonly size="80"></p>
                                    <input type="hidden" id="pos" name="pos">
                                    <p><textarea id="glosa" name="glosa" rows="2" cols="80"></textarea></p>                                    
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-light" onclick="emitir();">Emitir</button>
                                    <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            </div>
        </form>
    </body>
</html>