<%--
    Document   : commonSalaListaSalas
    Created on : 09-09-2010, 09:00:26 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
<link rel="stylesheet" href="/intranetv7/css/local/local-simple.css" type="text/css" />
<script type="text/javascript" src="/intranetv7/js/local/common/sala/commonSalaListaSalas-3.0.0.js"></script>

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

<div style="height: 40vh;overflow: auto; padding: 0;">

    <div style="margin-top: 10px; line-height: 70%;" class="data-tables-container">
        <table id="salas-table" class="table table-striped">
            <thead>
                <tr>
                    <th scope="col"><s:text name="label.sala"/></th>
                    <th scope="col"><s:text name="label.descripcion"/></th>
                    <th scope="col"><s:text name="label.sala.capacidad"/></th>
                    <th scope="col"><s:text name="label.sala.administrada.por"/></th>
                    <th scope="col"><s:text name="label.sala.administrada.por"/>(Vesp)</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="#session.genericSession.getWorkSession(key).salaList" status="row">
                    <tr>
                        <td><a id="sala_<s:property value="#row.count -1"/>"><s:property value="salaNum"/></a></td>
                        <td><s:property value="salaDescrip"/></td>
                        <td><s:property value="salaCapacidad"/></td>
                        <td><s:property value="unidad.uniNom"/></td>
                        <td><s:property value="unidadVesp.uniNom"/></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
</div>

<div id="horario-div" style="height: 48vh; overflow: auto;"></div>