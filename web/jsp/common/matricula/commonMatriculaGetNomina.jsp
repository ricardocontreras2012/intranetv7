<%-- 
    Document   : commonMatriculaGetNomina.jsp
    Created on : 03-01-2023, 9:21:58
    Author     : Ricardo Contreras S.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Nómina de Alumnos Matriculados</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-form.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tooltip.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/popper/1.16.1/popper.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/matricula/commonMatriculaGetNomina-3.0.0.js"></script>
    </head>
    <body class="inner-body" style="overflow: hidden">
        <div class="container-fluid d-flex flex-column vh-100">
            <row>
                <div class="navbar-brand container-fluid">
                    <div class="title-div">
                        NÓMINA DE MATRICULAS &nbsp;<s:property
                            value="#session.genericSession.getWorkSession(key).nombreCarrera"/> 
                        &nbsp;<s:property
                            value="#session.genericSession.getWorkSession(key).agnoAct"/>
                        /<s:property
                            value="#session.genericSession.getWorkSession(key).semAct"/>
                    </div>
                </div>
            </row>
            <row>
                <div class="container-fluid container-menu">
                    <div class="row">
                        <div id="justified-button-bar" class="col-lg-12">
                            <div class="btn-group">

                                <div class="btn-group">
                                    <button id="export-button" title="Excel" type="button" class="btn btn-light" >
                                        <span class="fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.excel"/></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </row>

            <row class="overflow-auto">
                <div class="container-fluid overflow-auto">
                    <form id="nomina-form" action="#">
                        <div class="data-tables-container container-fluid" style="margin: 0; padding: 0;">
                            <table id="nomina-table" class="display responsive table table-striped table-bordered dataTable" style="overflow-y: auto">
                                <thead>
                                    <tr> 
                                        <th></th>
                                        <th scope="col">RUN</th>
                                        <th scope="col">Paterno</th>
                                        <th scope="col">Materno</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Carrera</th>
                                        <th scope="col">Año Ing</th>
                                        <th scope="col">Sem Ing</th>
                                        <th scope="col">Fecha</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="#session.genericSession.getWorkSession(key).matriculaList" status="row">
                                        <tr>    
                                            <td><s:property value="#row.count"/></td>
                                            <td><s:property value="aluCar.id.acaRut"/>-<s:property value="aluCar.alumno.aluDv"/></td>
                                            <td><s:property value="aluCar.alumno.aluPaterno"/></td>
                                            <td><s:property value="aluCar.alumno.aluMaterno"/></td>
                                            <td><s:property value="aluCar.alumno.aluNombre"/></td> 
                                            <td><s:property value="aluCar.id.acaCodCar"/></td>
                                            <td><s:property value="aluCar.id.acaAgnoIng"/></td>
                                            <td><s:property value="aluCar.id.acaSemIng"/></td>
                                            <td><s:property value="mathFecha"/></td>                                            
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
            </row>
        </div>
    </div>
</body>
</html>
