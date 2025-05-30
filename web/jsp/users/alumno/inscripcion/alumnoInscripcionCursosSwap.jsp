<%--
    Document   : alumnoInscripcionCursosSwap
    Created on : 28-04-2010, 11:25:09 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Cursos Swap</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />        
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/inscripcion/alumnoInscripcionCursosSwap-3.0.3.js"></script>
    </head>
    <body>
        <form id="cursos-form" action="#" method="post">
            <table class="table table-striped">
                <thead>
                    <tr style="text-align: center; background-color: #CCCCCC">
                        <th scope="col" style="width:80%"><s:text name="label.curso"/></th>
                        <th scope="col" style="width:15%"><s:text name="label.horario"/></th>
                        <th scope="col" style="width:5%"><s:text name="label.profesor"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.alumnoSession.cursosSwap" status="row">
                        <tr style="height: 14px">
                            <td style="width:80%; text-align:left">
                                <a onClick="swap(<s:property value="#row.count -1"/>, '<s:property value="getNombreFull()"/>');" class="link"
                                   id="curso_<s:property value="#row.count -1"/>"><s:property value="id.curAsign"/></a>
                                <s:property value="id.curElect"/>
                                <s:property value="id.curCoord"/>
                                <s:property value="id.curSecc"/>
                                <s:property value="curNombre"/></td>
                            <td style="width:15%"><s:property value="curHorario"/></td>
                            <td style="width:5%; cursor: pointer"><img
                                    onclick="showProfesor('<s:property value="curProfesores.trim().replace(' ','_')"/>',
                                    <s:property value="#row.count -1"/>, 'cursos');"
                                    id="profesor_<s:property value="#row.count -1"/>_<s:property value="curProfesores.trim().replace(' ','_')"/>"
                                    src="/intranetv7/images/local/icon/user.png" height="16" width="16" alt="prof"/></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
            <input type="hidden" id="curso-title" value="<s:property value="#session.genericSession.getWorkSession(key).curso.getNombreFull()"/>" />
            <input type="hidden" id="pos" value="<s:property value="#session.genericSession.getWorkSession(key).pos"/>" />
        </form>

        <div class="modal fade" id="confirmacion-swap" role="dialog">
            <div class="modal-dialog" role="document">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"><s:text name="message.title.confirmacion"/> </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><s:text name="confirmation.swap.inscripcion"/></p>
                        <div id="conf-title-swap-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" onclick="swapIns();">OK</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>   
    </body>
</html>
