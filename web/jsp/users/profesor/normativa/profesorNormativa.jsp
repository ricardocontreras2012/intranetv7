<%--
    Document   : profesorNormativa
    Created on : 18-03-2011, 12:46:53 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Normativa Mencion - Profesor</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/normativa/profesorNormativa-3.0.0.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.menu.normativa"/> <s:text name="label.title.carreras"/>
        </div>

        <div>
            <s:if test="#session.genericSession.profesorSession.mencionInfoIntranetProfesorViewList.size() > 0">
                <table  class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <s:iterator value="#session.genericSession.profesorSession.mencionInfoIntranetProfesorViewList" status="row">
                                    <p><a onclick="showNormativaProfesor('<s:property value="miniUrlNormativa"/>');"><s:property value="nombreMencion"/></a></p>
                                    </s:iterator>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </s:if>
        </div>
    </body>
</html>
