<%--
    Document   : commonSalaReservaListaSalas
    Created on : 09-09-2010, 09:00:26 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<script type="text/javascript" src="/intranetv7/js/local/common/sala/commonSalaReservaListaSalas-3.0.1.js"></script>

<div style="margin-top: 10px" class="data-tables-container table-responsive">
    <table id="salas-table" class="table">
        <thead>
            <tr>
                <th scope="col"><s:text name="label.sala"/></th>
                <th scope="col"><s:text name="label.descripcion"/></th>
                <th scope="col"><s:text name="label.sala.capacidad"/></th>
                <th scope="col"><s:text name="label.sala.administrada.por"/></th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="#session.genericSession.getWorkSession(key).salaList" status="row">
                <tr>
                    <td><a id="sala_<s:property value="#row.count -1"/>"><s:property value="salaNum"/></a></td>
                    <td><s:property value="salaDescrip"/></td>
                    <td><s:property value="salaCapacidad"/></td>
                    <td><s:property value="getAdministrada()"/></td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
</div>