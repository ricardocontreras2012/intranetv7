<%--
    Document   : commonAlumnoDatosCurriculares
    Created on : 14-06-2009, 11:30:37 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Datos Curriculares del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.datos.curriculares"/>
        </div>

        <table>
            <tr valign="top">
                <td>
                    <table class="table">
                        <tr>
                            <td style="width:35%">
                                <s:text name="label.carrera"/>
                            </td>
                            <td>
                                <s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaCodCar"/> <s:property
                                    value="#session.genericSession.getWorkSession(key).aluCar.nombreCarrera"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:35%">
                                <s:text name="label.plan"/>
                            </td>
                            <td>
                                <s:property value="#session.genericSession.getWorkSession(key).aluCar.plan.id.plaCod"/>&nbsp;&nbsp;<s:text
                                    name="label.resolucion"/> <s:property
                                    value="#session.genericSession.getWorkSession(key).aluCar.plan.plaResoluciones"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.ingreso"/>
                            </td>
                            <td>
                                <s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaSemIng"/>/
                                <s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaAgnoIng"/>&nbsp;&nbsp;
                                <s:property
                                    value="#session.genericSession.getWorkSession(key).aluCar.getAaingreso().getAaiViaIng().getViiDes()"/>
                                <s:if test="#session.genericSession.getWorkSession(key).aluCar.getAaingreso().getAaiPtjePond() != null && #session.genericSession.getWorkSession(key).aluCar.getAaingreso().getAaiPtjePond() >0">
                                    <s:property
                                        value="#session.genericSession.getWorkSession(key).aluCar.getAaingreso().getAaiPtjePond()"/>&nbsp;Puntos
                                </s:if>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:35%">
                                <s:text name="label.resolucion"/>
                            </td>
                            <td>
                                <s:if test="#session.genericSession.getWorkSession(key).aluCar.acaResol > 0">
                                    <s:property value="#session.genericSession.getWorkSession(key).aluCar.acaResol"/> <s:text
                                        name="label.date"/> <s:date
                                        name="#session.genericSession.getWorkSession(key).aluCar.acaFecResol" format="dd/MM/yyyy"/>
                                </s:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.calidad"/>
                            </td>
                            <td>
                                <s:property
                                    value="#session.genericSession.getWorkSession(key).aluCar.getTcalidad().getTcaDescrip()"/>
                                (<s:date
                                    name="#session.genericSession.getWorkSession(key).getCcalidad().ccaFecha" format="dd/MM/yyyy"/>)
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.situacion"/>
                            </td>
                            <s:if test="#session.genericSession.getWorkSession(key).aluCar.getTsacademica().tsaCod ==3 ||
                                  #session.genericSession.getWorkSession(key).aluCar.getTsacademica().tsaCod ==4 ||
                                  #session.genericSession.getWorkSession(key).aluCar.getTsacademica().tsaCod ==5 ||
                                  #session.genericSession.getWorkSession(key).aluCar.getTsacademica().tsaCod ==6 ||
                                  #session.genericSession.getWorkSession(key).aluCar.getTsacademica().tsaCod ==10 ||
                                  #session.genericSession.getWorkSession(key).aluCar.getTsacademica().tsaCod ==99">
                                <td style="color:red">
                                    <s:property
                                        value="#session.genericSession.getWorkSession(key).aluCar.getTsacademica().getTsaDescrip()"/>
                                </td>
                            </s:if>
                            <s:else>
                                <td>
                                    <s:property
                                        value="#session.genericSession.getWorkSession(key).aluCar.getTsacademica().getTsaDescrip()"/>
                                </td>
                            </s:else>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.nivel"/>
                            </td>
                            <td>
                                <s:property value="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getNivel()"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.promedio.notas.aprobadas"/>
                            </td>
                            <s:if test="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getPromedioAprobadas() < 4">
                                <td style="color:red">
                                    <s:text name="format.promedio"><s:param name="value"
                                                                            value="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getPromedioAprobados()"/></s:text>
                                    </td>
                            </s:if>
                            <s:else>
                                <td>
                                    <s:text name="format.promedio"><s:param name="value"
                                                                            value="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getPromedioAprobados()"/></s:text>
                                    </td>
                            </s:else>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.promedio.notas"/>
                            </td>
                            <s:if test="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getPromedio() < 4">
                                <td style="color:red">
                                    <s:text name="format.promedio"><s:param name="value"
                                                                            value="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getPromedio()"/></s:text>
                                    </td>
                            </s:if>
                            <s:else>
                                <td>
                                    <s:text name="format.promedio"><s:param name="value"
                                                                            value="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getPromedio()"/></s:text>
                                    </td>
                            </s:else>
                        </tr>

                        <tr>
                            <td>
                                <s:text name="label.reprobaciones"/>
                            </td>
                            <td>
                                <s:property value="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getReprobaciones()"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.progresion"/>
                            </td>
                            <td>
                                <s:property value="#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getProgresion()"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.avance"/>
                            </td>
                            <td>
                                <s:text name="format.factor.alumno"><s:param name="value"
                                                                             value="100*#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getFactorAvance()"/></s:text>
                                    %
                                </td>
                            </tr>
                            <tr>
                                <td>
                                <s:text name="label.eficiencia"/>
                            </td>
                            <td>
                                <s:text name="format.factor.alumno"><s:param name="value"
                                                                             value="100*#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getFactorEficiencia()"/></s:text>
                                    %
                                </td>
                            </tr>

                            <tr>
                                <td>
                                <s:text name="label.matricula"/>
                            </td>
                            <td>
                                <s:property value="#session.genericSession.getWorkSession(key).aluCar.getUltimaMatricula"/>                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <s:text name="label.ultima.actividad"/>&nbsp;&nbsp;
                            </td>
                            <td>
                                <s:date name="#session.genericSession.getWorkSession(key).aluCar.acaUltActAcad"
                                        format="dd/MM/yyyy"/>
                            </td>
                        </tr>
                    </table>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col" style="width:35%"><s:text name="label.ranking"/></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width:35%">
                                    <s:text name="label.promedio.notas"/>&nbsp;&nbsp;
                                </td>
                                <td>
                                    <s:text name="format.promedio"><s:param name="value"
                                                                            value="#session.genericSession.getWorkSession(key).aluCar.acaPromAnt"/></s:text>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                    <s:text name="label.ranking"/>&nbsp;&nbsp;
                                </td>
                                <td>
                                    <s:property value="#session.genericSession.getWorkSession(key).aluCar.acaRanking"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col" style="width: 10%; text-align: right"></th>
                                <th scope="col">
                                    <s:text name="label.requisitos.de.titulacion"/>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 10%; text-align: right">
                                    <s:if test="100*#session.genericSession.getWorkSession(key).aluCar.aluCarFunction.getFactorAvance()==100">
                                        <img src="/intranetv7/images/local/icon/check.png" height="16" width="16" alt="aprobada"/>
                                    </s:if>
                                    <s:else>
                                        <img src="/intranetv7/images/local/icon/cancel-circle.png" height="16" width="16" alt="noaprobada"/>
                                    </s:else>
                                </td>
                                <td>
                                    <s:if test="#session.genericSession.getWorkSession(key).aluCar.plan.mencion.carrera.tcarrera.tcrCtip==16 && #session.genericSession.getWorkSession(key).aluCar.plan.id.plaCod ==7 && #session.genericSession.getWorkSession(key).aluCar.plan.mencion.id.menCodMen==1">
                                        <s:text name="label.asignaturas.de.malla.ingeco"/>
                                    </s:if>
                                    <s:else>
                                        <s:text name="label.asignaturas.de.malla"/>
                                    </s:else>

                                </td>
                            </tr>
                            <s:iterator value="#session.genericSession.getWorkSession(key).aluCar.requisitoLogroAdicionalList" status="row">
                                <tr>
                                    <td style="width: 10%; text-align: right">
                                        <s:if test="getTrequisitoLogroAdicional().estaAprobada()">
                                            <img src="/intranetv7/images/local/icon/check.png" height="16" width="16" alt="aprobada"/>
                                        </s:if>
                                        <s:else>
                                            <img src="/intranetv7/images/local/icon/cancel-circle.png" height="16" width="16" alt="noaprobada"/>
                                        </s:else>
                                    </td>
                                    <td><s:property value="planLogro.logro.logrDes"/>:&nbsp;<s:property value="getTrequisitoLogroAdicional().getTrlaDes()"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
