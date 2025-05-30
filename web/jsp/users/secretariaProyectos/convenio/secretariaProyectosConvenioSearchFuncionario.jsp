<%--
    Document   : secretariaProyectosConvenioSearchFuncionario
    Created on : 04-12-2020, 10:40:17
    Author     : Ricardo
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table id="funcionario-search-table" class="display responsive table table-striped table-bordered dataTable">
    <tr>
        <th>RUT</th>
        <th>Paterno</th>
        <th>Materno</th>
        <th>Nombre</th>
    </tr>
    <s:iterator value="#session.genericSession.getWorkSession(key).funcionarioList" status="row">
        <tr>
            <td style="width: 15%; text-align: right"><a
                    id="fun_<s:property value="#row.count -1"/>" onclick="addFuncionarioList('<s:property value="funRut"/>-<s:property value="funDv"/>','<s:property value="funPaterno"/>','<s:property value="funMaterno"/>','<s:property value="funNombre"/>','<s:property value="funDireccion"/>' );"><s:property value="funRut"/>-<s:property
                        value="funDv"/></a></td>
            <td><s:property value="funPaterno"/></td>
            <td><s:property value="funMaterno"/></td>
            <td><s:property value="funNombre"/></td>
            <td style="display: none;"><s:property value="funDireccion"/></td>
        </tr>
    </s:iterator>
</table>

<script>
    $("#funcionario-search-table").dataTable({
        "sPaginationType": "full_numbers",
        "ordering": false,
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
        "aLengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "iDisplayLength": -1
    });
</script>
