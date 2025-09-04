<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es" style="overflow-y: hidden;">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Inscripción</title>
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
        <script type="text/javascript" src="/intranetv7/js/local/common/inscripcion/commonInscripcion-3.0.9.js"></script>        
    </head>
    <body class="inner-body">
        <form id="inscripcion-form" action="#" method="post">
            <%--s:if test="#session.genericSession.getWorkSession(key).aluCar.getIsAlumnoPropio() || #session.genericSession.userType ==\"CFI\" "--%>
            <div class="btn-group">
                <button id="delete-button" title="Eliminar" type="button" class="btn btn-light"  onclick="deleteInscripcion();">
                    <span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.desinscribir"/></span>
                </button>
            </div>
            <%--/s:if--%>
            <div class="btn-group">
                <button id="print-button" title="Imprimir" type="button" class="btn btn-light"  onclick="printInscripcion();">
                    <span class="fa fa-print"></span>&nbsp; <span class="hidden-xs"><s:text name="label.imprimir"/></span>
                </button>
            </div>

            <div style="background-color: #679FD2;">
                <p colspan="2" style="width: 55%; color: #fff;">
                    &nbsp;Asignaturas de Malla: Créditos Inscritos <s:property value="#session.genericSession.getWorkSession(key).aluCar.getCreditosInscritos()"/>
                    &nbsp;&nbsp;Sct Inscritos <s:property value="#session.genericSession.getWorkSession(key).aluCar.getSctInscritos()"/>
                </p>
            </div>

            <div style="overflow-y: scroll; height: 80vh;">
                <table class="table table-sm table-striped" style="width: 97%">
                    <thead>
                        <tr class="header-table">
                            <th scope="col" style="width: 5%"></th>
                            <th scope="col" style="width: 45%"><s:text name="label.curso"/></th>
                            <th scope="col" style="width: 10%"><s:text name="label.horario"/></th>
                            <th scope="col" style="width: 30%"><s:text name="label.profesor"/></th>
                            <th scope="col" style="width: 5%"></th>
                            <th scope="col" style="width: 5%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.insList" status="row">
                            <tr>                                            
                                <s:if test="curso.puedeEliminar(#session.genericSession.getWorkSession(key).aluCar.id.acaCodCar, #session.genericSession.getWorkSession(key).aluCar.acaCodMen,  id.insAsign, #session.genericSession.userType, #session.genericSession.rut) >0">
                                    <td style="width: 5%; text-align: center">
                                        <input type="checkbox" name="ck_<s:property value="#row.count -1"/>"
                                               id="ck_<s:property value="#row.count -1"/>"/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td style="width: 5%"></td>
                                </s:else>

                                <td style="width: 45%">
                                    <s:property value="id.insAsign"/>
                                    <s:property value="id.insElect"/>
                                    <s:property value="insCoord"/>
                                    <s:property value="insSecc"/>
                                    <s:property value="curso.curNombre"/>
                                </td>
                                <td style="width: 10%"><s:property value="curso.curHorario"/></td>
                                <td style="width: 30%"><s:property value="curso.curProfesores"/></td>
                                <td><img
                                        onclick="showProfesor('<s:property value="curso.curProfesores.trim().replace('/ /g','0')"/>', <s:property
                                            value="#row.count -1"/>, 'inscripcion');"
                                        style="cursor: pointer"
                                        id="profesor_<s:property value="#row.count -1"/>_<s:property value="curso.curProfesores.trim().replace('/ /g','0')"/>"
                                        src="/intranetv7/images/local/icon/user.png" height="16" width="16" alt="prof"/></td>
                                <td>
                                    <s:if test="curso.puedeEliminar(#session.genericSession.getWorkSession(key).aluCar.id.acaCodCar, #session.genericSession.getWorkSession(key).aluCar.acaCodMen,  id.insAsign, #session.genericSession.userType, #session.genericSession.rut) >0">
                                        <select id="force_<s:property value="#row.count -1"/>" name="force_<s:property value="#row.count -1"/>" class="form-control form-select form-select-lg mb-3" onChange="changeForce(<s:property value="#row.count -1"/>, '<s:property value="insForce"/>')">                                    
                                            <s:if test="insForce == \"F\"">
                                                <option value="F" class="form-control" selected>F</option>
                                                <option value="N" class="form-control">N</option>
                                            </s:if>
                                            <s:else>
                                                <option value="F" class="form-control">F</option>
                                                <option value="N" class="form-control" selected>N</option>
                                            </s:else>
                                        </select>
                                    </s:if>
                                    <s:else>
                                        <s:property value="insForce"/>
                                    </s:else>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>

            <div id="msg-dummy" name="msg-dummy" style="display: none;"><s:actionerror/><s:actionmessage/></div>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="pos" name="pos" value="<s:property value="pos"/>"/>
                <input type="hidden" id="force" name="force" />
            </div>
        </form>          

        <script>
            <s:if test="hasActionMessages()">
            const msg = $("#msg-dummy").html().replace(/(\r\n|\n|\r)/g, "");
            const ary = msg.split('ERROR');
            let acum = "";

            for (let i = 0; i < ary.length; i++) {
                if (typeof ary[i] !== "undefined")
                {
                    acum += "<li><span>" + ary[i] + "</li></span>";
                }
            }

            $(window.parent.document).contents().find("#msg-confirmacion-div").html("<div class='actionError'>" + acum + "</div><div><p><s:text name="confirmation.inscripcion.jefe.carrera"/></div>");
            window.parent.$('#msg-confirmacion').modal('show');

            </s:if>
            <s:if test="hasActionErrors()">
            const msgError = $("#msg-dummy").html().replace(/(\r\n|\n|\r)/g, "");
            $(window.parent.document).contents().find("#msg-error-div").html("<div class='actionError'><ul><li><span>" + msgError + "</li></ul></span></div>");
            window.parent.$('#msg-error').modal('show');
            </s:if>
        </script>

    </body>
</html>