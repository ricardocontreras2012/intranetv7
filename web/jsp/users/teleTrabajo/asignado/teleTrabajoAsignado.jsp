<%-- 
    Document   : teletrabajoAsignado
    Created on : 26-09-2023, 16:09:58
    Author     : Javier
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Teletrabajo asignado</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/blockui/jquery.blockUI-3.0.1.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/asignado/teleTrabajoAsignado.js"></script>
    </head>
    <body class="inner-body">
        <form id="actividades-form" action="#" accept-charset="UTF-8">  
            <div id="table-div" style="overflow-x: hidden">
                <table id="actividades-table" class="table table-striped table-bordered dataTable" style="width:100%">
                </table>
            </div>
            <div id="hidden-input-sent-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" />
                <input type="hidden" id="start" name="start" />
                <input type="hidden" id="length" name="length" />
                <input type="hidden" id="searchValue" name="searchValue" />
                <input type="hidden" id="tipoOrder" name="tipoOrder" />
                <input type="hidden" id="nombreDataColumnaActual" name="nombreDataColumnaActual" />
            </div>
        </form>
    </body>
</html>