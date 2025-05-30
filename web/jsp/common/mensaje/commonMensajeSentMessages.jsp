<%--
    Document   : commonMensajeSentMessages
    Created on : 09-11-2009, 11:53:30 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Mensajes Enviados</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <!--script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script-->
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/mensaje/commonMensajeSentMessages-3.0.5.js"></script>
    </head>
    <body class="inner-body">
        <form id="sent-messages-form" action="#" accept-charset="UTF-8">
            <div id="table-div" style="overflow-x: hidden">
                <table id="sent-table" class="table table-striped table-bordered dataTable" style="width:100%">
                </table>
            </div>
            <div id="hidden-input-sent-div">                
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" />
                <input type="hidden" id="tipo" name="tipo" value="S" />
                <input type="hidden" id="start" name="start" />
                <input type="hidden" id="length" name="length" />
                <input type="hidden" id="searchValue" name="searchValue" />
                <input type="hidden" id="tipoOrder" name="tipoOrder" />
                <input type="hidden" id="nombreDataColumnaActual" name="nombreDataColumnaActual" />
            </div>
        </form>
        <form id="nombresColumnas" action="#" accept-charset="UTF-8">
            <input type="hidden" id="sentTo" name="sentTo" value="<s:text name="label.sentTo"/>" />
            <input type="hidden" id="subject" name="subject" value="<s:text name="label.subject"/>" />
            <input type="hidden" id="date" name="date" value="<s:text name="label.date"/>" />
        </form>
    </body>
</html>