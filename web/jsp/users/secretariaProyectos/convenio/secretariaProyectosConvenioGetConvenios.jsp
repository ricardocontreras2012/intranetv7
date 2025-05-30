<%--
    Document   : secretariaProyectosConvenioGetConvenios
    Created on : 11-06-2018, 11:25:07
    Author     : rcontreras
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Contratos</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/users/secretariaProyectos/convenio/secretariaProyectosConvenioGetConvenios-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            Contratos
        </div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                                <span class="fa fa-plus"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="delete-button" title="Eliminar" type="button" class="btn btn-light" >
                                <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.delete"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form id="convenios-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="convenios-table" class="display responsive table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col" style="text-align:center;"></th>
                            <th scope="col">Número</th>
                            <th scope="col">Prestador</th>
                            <th scope="col">Código</th>
                            <th scope="col">Proyecto</th>
                            <th scope="col">Labor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.proyectoSession.convenioList" status="row">
                            <tr>
                                <td style="text-align:center;"><input type="checkbox"
                                                                      id="ck_<s:property value="#row.count -1"/>"
                                                                      name="ck_<s:property value="#row.count -1"/>"/></td>
                                <td><a id="conv_<s:property value="#row.count -1"/>"><s:property value="convNro"/></a></td>
                                <td><s:property value="funcionario.getNombre()"/></td>
                                <td><s:property value="proyecto.proyCod"/></td>
                                <td><s:property value="proyecto.proyNom"/></td>
                                <td><s:property value="convFuncion"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>

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
                            <p>Desea eliminar convenio(s)</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-light" onclick="remove();">OK</button>
                            <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            <input type="hidden" id="pos" name="pos" value=""/>

        </form>
    </body>
</html>