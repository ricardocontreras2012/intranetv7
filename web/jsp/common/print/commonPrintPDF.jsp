<%--
    Document   : commonPrintPDF
    Created on : 03-08-2020, 9:26:28
    Author     : Ricardo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/print/1.0.61/print.min.js"></script>
        <title>Printing...</title>
    </head>
    <body onload="printJS('/intranetv7/CommonPrintPDFDownload?key=<s:property value="key"/>');">
    </body>
</html>
