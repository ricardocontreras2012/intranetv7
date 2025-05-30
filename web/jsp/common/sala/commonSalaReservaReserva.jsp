<%--
    Document   : commonSalaReservaReserva
    Created on : 09-07-2018, 17:37:16
    Author     : rcontreras
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<p>
<s:property value="#session.genericSession.getWorkSession(key).reserva.rsalMotivo"/>
</p><p>
<s:date name="#session.genericSession.getWorkSession(key).reserva.rsalFechaInicio" format="dd/MM/yyyy"/> al <s:date name="#session.genericSession.getWorkSession(key).reserva.rsalFechaTermino" format="dd/MM/yyyy"/>
</p><p>
<s:property value="#session.genericSession.getWorkSession(key).reserva.rsalUser"/>
</p>