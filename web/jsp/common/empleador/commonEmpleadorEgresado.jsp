<%--
    Document   : commonEmpleadorEgresado
    Created on : 27-07-2017, 11:00:47 AM
    Author     : Álvaro Romero C.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="#session.genericSession.getWorkSession(key).empleador != null">
    <div class="form-group">
        <label for="nombreEmpleador" class="col-sm-3 control-label"><s:text name="label.name.empleador"/></label>
        <div class="col-sm-9">
            <input type="text" class="form-control" id="nombreEmpleador" name="nombreEmpleador"
                   readonly="readonly"
                   value="<s:property value="#session.genericSession.getWorkSession(key).empleador.empNombre"/>">
        </div>
    </div>

    <div class="form-group">
        <label for="tipoEmpleador" class="col-sm-3 control-label"><s:text name="label.sector.empleador"/></label>
        <div class="col-sm-9">
            <input type="text" class="form-control" id="tipoEmpleador" name="tipoEmpleador"
                   readonly="readonly"
                   <s:if test="#session.genericSession.getWorkSession(key).empleador.empTipoEmpleador == 'PR'">
                       value="PRIVADO"
                   </s:if>
                   <s:elseif test="#session.genericSession.getWorkSession(key).empleador.empTipoEmpleador == 'PU'">
                       value="PÚBLICO"
                   </s:elseif>
            />
        </div>
    </div>

    <div class="form-group">
        <label for="actividadEconomica" class="col-sm-3 control-label"><s:text name="label.economy.activity"/></label>
        <div class="col-sm-9">
            <input type="text" class="form-control" id="actividadEconomica" name="actividadEconomica"
                   readonly="readonly"
                   value="<s:property value="#session.genericSession.getWorkSession(key).empleador.actividadEconomica.aceDes"/>"/>
        </div>
    </div>


</s:if>
<s:else>
    <input type="hidden" id="nombreEmpleador" name="nombreEmpleador" size="100" />
</s:else>
