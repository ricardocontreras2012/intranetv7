<%-- 
    Document   : teleTrabajoGetFuncionarios
    Created on : 01-10-2023, 14:07:20
    Author     : Ricardo
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Funcionarios Teletrabajo</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/teleTrabajo/teleTrabajoGetFuncionarios-3.0.0.js"></script>
    </head>
    <body> 
        <div class="title-div">
            FUNCIONARIOS
        </div>
        <div style="margin-top: 10px">
            <form id="funcionarios-form" method="post" action="#">
                <table id="funcionario-table" class="table">
                    <thead>
                        <tr>
                            <th scope="col">RUT</th>                        
                            <th scope="col">Paterno</th>
                            <th scope="col">Materno</th>
                            <th scope="col">Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.teleTrabajoSession.funcionarioList" status="row">
                            <tr>
                                <td><a id="fun_<s:property value="#row.count -1"/>"><s:property value="ftelRut"/>-<s:property value="funcionario.funDv"/></a></td>                            
                                <td><s:property value="funcionario.funPaterno"/></td>
                                <td><s:property value="funcionario.funMaterno"/></td>
                                <td><s:property value="funcionario.funNombre"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>

                <div id="hidden-input-div">
                    <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    <input type="hidden" id="pos" name="pos" />
                </div>
            </form>
        </div>
    </body>
</html>

