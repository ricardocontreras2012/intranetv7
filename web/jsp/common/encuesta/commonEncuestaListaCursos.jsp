<%--
    Document   : commonCursoListaCursosEncuesta
    Created on : 03-08-2010, 09:02:46 PM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista de Cursos</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.data.tables.sort-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/encuesta/commonEncuestaListaCursos-3.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.cursos.encuestas"/>
        </div>        

        <form id="cursos-form" action="#" method="post">
            <div class="data-tables-container">
                <table id="cursos-table" class="table table-striped table-bordered dataTable">
                    <thead>
                        <tr>
                            <th scope="col"><s:text name="label.code"/></th>
                            <th scope="col"><s:text name="label.coordinacion"/></th>
                            <th scope="col"><s:text name="label.term.short"/> <s:text name="label.year"/></th>
                            <th scope="col"><s:text name="label.asignatura"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).cursoProfesorList" status="row">
                            <tr>
                                <td style="width: 10%;text-align: right"><a
                                        id="curso_<s:property value="#row.count -1"/>"><s:property value="curso.id.curAsign"/>
                                        <s:property value="curso.id.curElect"/></a>
                                </td>
                                <td style="width: 10%;text-align: right"><s:property value="curso.id.curCoord"/>-<s:if
                                        test="curso.id.curSecc != null"><s:text name="format.seccion"><s:param
                                                value="curso.id.curSecc"/></s:text></s:if>
                                        </td>
                                        <td style="width: 10%;text-align: center"><s:property value="curso.id.curSem"/>/<s:property
                                        value="curso.id.curAgno"/>
                                </td>
                                <s:if test="#session.genericSession.userType == \"PR\"">
                                <td><s:property value="curso.curNombre"/></td>
                                </s:if>
                                <s:else>
                                <td><s:property value="curso.curNombre"/> (<s:property value="profesor.nombre"/>)</td>  
                                </s:else>                                
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div id="hidden-input-div">
                <input type="hidden" id="actionCall" name="actionCall" value="<s:property value="actionCall"/>"/>
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="tipo" name="tipo" value="<s:property value="tipo"/>"/>
            </div>
        </form>
    </body>
</html>