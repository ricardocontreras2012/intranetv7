<%--
    Document   : commonProfesorSearch
    Created on : 16-02-2010, 09:07:38 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Búsqueda de Profesor</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/profesor/commonProfesorSearch-3.0.2.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="title-div">
                <s:text name="label.title.consulta.profesor"/>
            </div>

            <div class="mb-3">
                <div class="btn-group">
                    <button id="search-button" title="Buscar" type="button" class="btn btn-light" >
                        <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                    </button>
                    <button id="clear-button" title="Limpiar" type="button" class="btn btn-light" >
                        <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.clear"/></span>
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <form id="profesores-form" accept-charset="UTF-8" action="#">
                        <div class="form-row">
                            <div class="col-md-1">
                                <label for="rutdv"><s:text name="label.rut"/></label>
                                <input type="text" class="form-control text-right" id="rutdv" name="rutdv" placeholder="RUT">
                            </div>
                            <div class="col-md-3">
                                <label for="paterno"><s:text name="label.paterno"/></label>
                                <input type="text" class="form-control" id="paterno" name="paterno" placeholder="Paterno">
                            </div>
                            <div class="col-md-3">
                                <label for="materno"><s:text name="label.materno"/></label>
                                <input type="text" class="form-control" id="materno" name="materno" placeholder="Materno">
                            </div>
                            <div class="col-md-5">
                                <label for="nombre"><s:text name="label.name"/></label>
                                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
                            </div>

                            <div id="hidden-input-div">
                                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                                <input type="hidden" id="rut" name="rut" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div id="search-content-div" style="height:70vh;width: 70vw;">
                <iframe id="search-content-iframe" name="search-content-iframe" style="width: 95vw;height: 70vh;position: relative;" frameborder="0"></iframe>
            </div>
        </div>
    </body>
</html>