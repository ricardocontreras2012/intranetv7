<%--
    Document   : commonMensajeAlumnoSearch
    Created on : 06-06-2009, 07:23:55 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Búsqueda de Alumnos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.rut-3.0.0.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeAlumnoSearch-3.0.3.js"></script>
    </head>
    <body class="inner-body">        
        <div class="title-div">&nbsp;&nbsp;&nbsp;<s:text name="label.title.search.alumno"/></div>

        <div class="container container-menu">
            <div class="row">
                <div id="justified-button-bar" class="col-lg-12">
                    <div class="btn-group">
                        <div class="btn-group">
                            <button id="search-button" title="Iniciar la Búsqueda" type="button" class="btn btn-light"  onclick="searchMessageAlumno(); return false;">
                                <span class="fa fa-search"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.search"/></span>
                            </button>
                        </div>
                        <div class="btn-group">
                            <button id="clear-button" title="Limpiar Datos" type="button" class="btn btn-light"  onclick="otroAlumno(); return false;">
                                <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.clear"/></span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <form id="alumno-search-form" action="#" method="post" accept-charset="UTF-8">
            <div class="form-row">
                <div class="col-md-1">
                    <label for="rutdv"><s:text name="label.rut"/></label>
                    <input type="text" class="form-control text-right" id="rutdv" name="rutdv" placeholder="12345678-9">
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
            </div>

            <iframe id="search-content-iframe" style="height: 60vh; width: 70vw" frameBorder="0"></iframe>
            <input type="hidden" id="keySearch" name="keySearch" value="<s:property value="key"/>"/>
            <input type="hidden" id="rut" name="rut" />
            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>

        </form>   
    </body>
</html>