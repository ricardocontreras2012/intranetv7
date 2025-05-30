<%--
    Document   : commonReqLogroAdicionalGetListaxCalificar
    Created on : 15-abr-2012, 17:36:02
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Lista Alumnos con Inscripcion Adicional x Calificar</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-tablas.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/jQuery/jquery-ui-1.10.4.custom.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-ui-1.11.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript"
        src="/intranetv7/js/local/common/requisitoAdicionalTitulacion/commonReqLogroAdicionalGetListaxCalificar-2.0.1.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.inscripcion.adicional"/> <s:property
                value="#session.genericSession.getWorkSession(key).trequisitoLogroAdicional.tralDes"/> <s:property
                value="#session.genericSession.getWorkSession(key).semAct"/>/<s:property
                value="#session.genericSession.getWorkSession(key).agnoAct"/>
        </div>

        <div id="central-div">
            <div class="buttons-div">
                <button id="emitir-button" name="emitir-button" class="positive">
                    <img src="/intranetv7/images/local/icon/plus.png" height="16" width="16" alt="add" title="add"/>
                    <s:text name="label.button.emitir"/>
                </button>

            </div>
            <div id="content-div">
                <form id="acta-form" action="#" method="post">
                    <table style="width: 10%">
                        <tr>
                            <td align="left">
                                <s:select id="trequisitoLogroAdicional"
                                          name="trequisitoLogroAdicional"
                                          headerKey="0"
                                          headerValue="-- Seleccione Adicional --"
                                          list="#session.genericSession.getWorkSession(key).trequisitoLogroAdicionalList"
                                          listKey="tralCod"
                                          listValue="tralDes"
                                          cssClass="input"/>
                            </td>
                        </tr>
                    </table>
                    <div style="margin-top: 20px;">
                        <table id="inscripciones-table">
                            <thead>
                                <tr>
                                    <th scope="col" style="width:10%"><s:text name="label.paterno"/></th>
                                    <th scope="col" style="width:10%"><s:text name="label.materno"/></th>
                                    <th scope="col" style="width:20%"><s:text name="label.name"/></th>
                                    <th scope="col" style="width:50%"><s:text name="label.tema"/></th>
                                    <th scope="col" style="width:5%"><s:text name="label.nota"/></th>
                                    <th scope="col" style="width:5%"><s:text name="label.date"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="#session.genericSession.getWorkSession(key).inscripcionRequisitoAdicionalLogroList"
                                            status="row">
                                    <tr>
                                        <td style="width:10%"><s:property value="aluCar.alumno.aluPaterno"/></td>
                                        <td style="width:10%"><s:property value="aluCar.alumno.aluMaterno"/></td>
                                        <td style="width:20%"><s:property value="aluCar.alumno.aluNombre"/></td>
                                        <td style="width:50%"><a id="a_<s:property value="#row.count"/>"><s:property value="iratGlosa"/></a></td>
                                        <td style="width:5%">
                                            <s:if test="#row.odd">
                                                <input size="3"
                                                       id="nota_<s:property value="aluCar.alumno.aluRut"/>"
                                                       name="nota_<s:property value="aluCar.alumno.aluRut"/>" type="text"
                                                       maxlength="3" class="odd"
                                                       value=""/>
                                            </s:if>
                                            <s:else>
                                                <input size="3"
                                                       id="nota_<s:property value="aluCar.alumno.aluRut"/>"
                                                       name="nota_<s:property value="aluCar.alumno.aluRut"/>" type="text"
                                                       maxlength="3" class="even"
                                                       value=""/>
                                            </s:else>
                                        </td>
                                        <td style="width:5%">
                                            <s:if test="#row.odd">
                                                <input size="10"
                                                       id="fecha_<s:property value="aluCar.alumno.aluRut"/>"
                                                       name="fecha_<s:property value="aluCar.alumno.aluRut"/>" type="text"
                                                       maxlength="10" class="odd"
                                                       value=""/>
                                            </s:if>
                                            <s:else>
                                                <input size="10"
                                                       id="fecha_<s:property value="aluCar.alumno.aluRut"/>"
                                                       name="fecha_<s:property value="aluCar.alumno.aluRut"/>" type="text"
                                                       maxlength="10" class="even"
                                                       value=""/>
                                            </s:else>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </div>

                    <div id="hidden-input-div">
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        <input type="hidden" id="pos" name="pos" value=""/>
                        <input type="hidden" id="tipoDummy" name="tipoDummy"
                               value="<s:property value="#session.genericSession.getWorkSession(key).trequisitoLogroAdicional.tralCod"/>"/>
                    </div>
                </form>
            </div>
        </div>
        <div id="confirmacion" title="<s:text name="message.title.confirmacion"/>"><s:text
                name="confirmation.emision.acta"/></div>
        <div id="msg-div" title="<s:text name="message.title.msg"/>"><s:text name="alert.seleccione.inscripciones"/></div>
        <div id="msgError" title="<s:text name="message.title.msg"/>"><s:actionerror cssClass="actionError"/></div>
    </body>
</html>