<%--
    Document   : alumnoInscripcionCursos
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
        <title>Cursos Programados</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-inscripcion.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.std.ready-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/users/alumno/inscripcion/alumnoInscripcionCursos-3.1.0.js"></script>        
    </head>
    <body class="inner-body">
        <form id="cursos-form" action="#" method="post">
            <table class="table table-sm table-striped">
                <thead>
                    <tr class="header-table">
                        <th scope="col" style="width:80%"><s:text name="label.curso"/></th>
                        <th scope="col" style="width:15%"><s:text name="label.horario"/></th>
                        <th scope="col" style="width:5%"><s:text name="label.profesor"/></th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="lCurso" status="row">
                        <tr style="height: 14px">
                            <td style="width:80%; text-align:left">
                                
                                <div id="<s:property value='#row.count - 1'/>_curAsignDiv" style="display: none;">
                                    <s:property value="id.curAsign" />
                                </div>
                                
                                <div id="<s:property value='#row.count - 1'/>_curElectDiv" style="display: none;">
                                    <s:property value="id.curElect" />
                                </div>

                                <div id="<s:property value='#row.count - 1'/>_curCoordDiv" style="display: none;">
                                    <s:property value="id.curCoord" />
                                </div>

                                <div id="<s:property value='#row.count - 1'/>_curSeccDiv" style="display: none;">
                                    <s:property value="id.curSecc" />
                                </div>

                                <div id="<s:property value='#row.count - 1'/>_curAgnoDiv" style="display: none;">
                                    <s:property value="id.curAgno" />
                                </div>

                                <div id="<s:property value='#row.count - 1'/>_curSemDiv" style="display: none;">
                                    <s:property value="id.curSem" />
                                </div>

                                <div id="<s:property value='#row.count - 1'/>_curCompDiv" style="display: none;">
                                    <s:property value="id.curComp" />
                                </div>

                                <a onClick="inscribir(<s:property value="#row.count -1"/>);" class="link"
                                   id="curso_<s:property value="#row.count -1"/>"><s:property value="id.curAsign"/></a>
                                <s:property value="id.curElect"/>
                                <s:property value="id.curCoord"/>
                                <s:property value="id.curSecc"/>
                                <s:property value="curNombre"/></td>
                            <td style="width:15%"><s:property value="curHorario"/></td>
                            <td style="width:5%; cursor: pointer"><img
                                    onclick="showProfesor('<s:property value="curProfesores.trim().replace('/ /g','0')"/>',
                                    <s:property value="#row.count -1"/>, 'cursos');"
                                    id="profesor_<s:property value="#row.count -1"/>_<s:property value="curProfesores.trim().replace('/ /g','0')"/>"
                                    src="/intranetv7/images/local/icon/user.png" height="16" width="16" alt="prof"/></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="curAsign" name="curAsign"/>
                <input type="hidden" id="curElect" name="curElect"/>
                <input type="hidden" id="curCoord" name="curCoord"/>
                <input type="hidden" id="curSecc" name="curSecc"/>
                <input type="hidden" id="curAgno" name="curAgno"/>
                <input type="hidden" id="curSem" name="curSem"/>
                <input type="hidden" id="curComp" name="curComp"/>
            </div>
        </form>        
    </body>
</html>