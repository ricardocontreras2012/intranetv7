<%--
    Document   : jefeCarreraConvalidacionComision
    Created on : 06-06-2018, 11:24:44
    Author     : rcontreras
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Definición de Comisión de Convalidación</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/convalidacion/commonConvalidacionComision-3.0.0.js"></script>
    </head>
    <body>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="save-button" title="Grabar" type="button" class="btn btn-light" >
                                <span class="fa fa-save"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.save"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="add-button" title="Nuevo" type="button" class="btn btn-light" >
                                <span class="fa fa-pencil"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.new"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form id="comision-form" action="#" method="post">
            <table class="table table-striped">
                <s:iterator value="#session.genericSession.getWorkSession(key).comision" status="row">
                    <tr>
                        <td><s:property value="profPat"/></td>
                        <td><s:property value="profMat"/></td>
                        <td><s:property value="profNom"/></td>
                    </tr>
                </s:iterator>
            </table>
            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            <input type="hidden" id="actionCall" name="actionCall" value="CommonConvalidacionComisionAddProf"/>
        </form>

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
                        <p>xxxxx</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="yyyy;">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
