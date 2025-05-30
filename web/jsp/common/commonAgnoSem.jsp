<%-- 
    Document   : commonAgnoSem
    Created on : 15-11-2022, 14:54:58
    Author     : Ricardo Contreras S.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Año/Sem</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/commonAgnoSem-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <form id="agno-sem-form" action="#">
            <%--div class="modal fade" id="sem-agno-modal" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">SEM/AÑO</h5>

                        </div>
                        <div class="modal-body">
                            <div class="container-fluid pt-2 pb-2">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-inline row">
                                            <div class="col-md-3">                                
                                                Sem/Año
                                            </div>
                                            <div class="col-md-9">
                                                <input id="sem" name="sem"
                                                       value="<s:property value="#session.genericSession.getWorkSession(key).semAct"/>"
                                                       maxlength="1" size="1" class="form-control input-sm"/>
                                                <input id="agno" name="agno"
                                                       value="<s:property value="#session.genericSession.getWorkSession(key).agnoAct"/>"
                                                       maxlength="4" size="4" class="form-control input-sm"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-9"></div>
                            </div>
                            <div id="justified-button-bar" class="col-lg-12">
                                <div class="btn-group">

                                    <div class="btn-group">
                                        <button id="next-button" title="Next" type="button" class="btn btn-light" >
                                            <span class="fa fa-step-forward"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.next"/></span>
                                        </button>
                                    </div>

                                </div>
                            </div>
                            <div class="modal-footer">

                                <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div--%>

            <div class="modal fade" id="sem-agno-modal" tabindex="-1" role="dialog" aria-labelledby="semAgnoModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="semAgnoModalLabel">SEM/AÑO</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group row">
                                <label for="sem" class="col-md-3 col-form-label">Sem/Año</label>
                                <div class="col-md-9">
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <input id="sem" name="sem" type="text"
                                                   value="<s:property value='#session.genericSession.getWorkSession(key).semAct'/>"
                                                   maxlength="1" class="form-control"
                                                   placeholder="Semestre"/>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <input id="agno" name="agno" type="text"
                                                   value="<s:property value='#session.genericSession.getWorkSession(key).agnoAct'/>"
                                                   maxlength="4" class="form-control"
                                                   placeholder="Año"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="next-button" type="button" class="btn btn-primary">
                                <span class="fa fa-step-forward"></span>&nbsp; <s:text name="label.button.next"/>
                            </button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            <input type="hidden" id="actionName" name="actionName" value="<s:property value="actionName"/>"/>
        </form>
    </body>
</html>
