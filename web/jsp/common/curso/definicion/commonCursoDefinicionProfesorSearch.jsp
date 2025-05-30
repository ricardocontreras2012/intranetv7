<%--
    Document   : commonCursoProfesorSearch
    Created on : 26-10-2020, 17:13:15
    Author     : Ricardo
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table id="profesor-search-table" class="display responsive table table-striped table-bordered dataTable">
    <tr>
        <th>RUT</th>
        <th>Paterno</th>
        <th>Materno</th>
        <th>Nombre</th>
    </tr>
    <s:iterator value="#session.genericSession.getWorkSession(key).profesorList" status="row">
        <tr>
            <td style="width: 15%; text-align: right"><a
                    id="prof_<s:property value="#row.count -1"/>" onclick="addProfesorList('<s:property value="profRut"/>-<s:property value="profDv"/>','<s:property value="profPat"/>','<s:property value="profMat"/>','<s:property value="profNom"/>' );"><s:property value="profRut"/>-<s:property
                        value="profDv"/></a></td>
            <td><s:property value="profPat"/></td>
            <td><s:property value="profMat"/></td>
            <td><s:property value="profNom"/></td>
        </tr>
    </s:iterator>
</table>

<script>
    $("#profesor-search-table").dataTable({
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sInfo": "Mostrando desde _START_ hasta _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando desde 0 hasta 0 de 0 registros",
            "sInfoFiltered": "(filtrado de _MAX_ registros en total)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "Primero",
                "sPrevious": "Anterior",
                "sNext": "Siguiente",
                "sLast": "\u00daltimo"
            }
        },
        "aoColumns": [
            {"sType": null},
            {"sType": "string"},
            {"sType": "string"},
            {"sType": "string"}
        ],
        "aaSorting": [
            [1, "asc"],
            [2, "asc"],
            [3, "asc"]
        ], "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": -1
    });
</script>
