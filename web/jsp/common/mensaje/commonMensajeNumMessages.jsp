<%--
    Document   : commonMensajeNumMessages
    Created on : 27-08-2010, 04:59:13 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<a id="msgMenu" onclick="executeActionMessage();">
    <s:text name="label.menu.mensajes"/>
    <span class="badge badge-secondary">
        <s:property value="#session.genericSession.getWorkSession(key).nuevosMensajes"/>
    </span>
</a>
<script type="text/javascript">
    <s:if test="#session.genericSession.getWorkSession(key).nuevosMensajes > 0 && #session.genericSession.getWorkSession(key).blinkMensajes==true">
        blink($("#msgMenu"), 'red', 500, 7);
    </s:if>
</script>