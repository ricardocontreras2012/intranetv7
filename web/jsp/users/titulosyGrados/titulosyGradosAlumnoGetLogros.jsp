<%-- 
    Document   : titulosyGradosAlumnoGetLogros
    Created on : 22-04-2025, 20:26:57
    Author     : Ricardo
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Logros</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>       
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/titulosyGrados/titulosyGradosAlumnoGetLogros-3.0.0.js"></script>
        <style>
            /* Estilo para cambiar el cursor cuando se pasa sobre una fila */
            table.table tbody tr {
                cursor: pointer;  /* Cambia el cursor a mano */
            }
        </style>
    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:property
                value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluRut"/>-<s:property
                value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluDv"/>&nbsp;&nbsp;<s:property
                value="#session.genericSession.getWorkSession(key).aluCar.alumno.nombre"/>
        </div>        

        <form id="logros-form" action="#" method="post">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Seleccione Logro a Certificar</th>                        
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#session.genericSession.getWorkSession(key).expedienteLogroList" status="row">
                        <tr>
                            <td><s:if test="#session.genericSession.getWorkSession(key).aluCar.alumno.aluSexo == \"1\"">
                                    <s:property value="planLogro.plalLin1F"/>&nbsp;<s:property value="planLogro.plalLin2F"/>
                                </s:if>
                                <s:else>
                                    <s:property value="planLogro.plalLin1M"/>&nbsp;<s:property value="planLogro.plalLin2M"/>
                                </s:else>
                            </td>
                            <td style="display: none;">
                                <s:property value="explRol"/>
                            </td>
                            <td style="display: none;">
                                <s:property value="explNumResol"/>
                            </td>
                            <td style="display: none;">
                                <s:property value="explFecResol"/>
                            </td>                            
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/> 
                <input type="hidden" id="pos" name="pos"/>               
            </div>

            <div class="modal fade" id="confirmacion" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Datos Requeridos</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <!-- Formulario -->
                            <form id="formDatos">
                                <div class="form-group">
                                    <label for="rol">Rol</label>
                                    <input type="text" class="form-control" id="rol" name ="rol" placeholder="Ingrese el rol" required>
                                </div>
                                <div class="form-group">
                                    <label for="resolucion">Resolución</label>
                                    <input type="text" class="form-control" id="resolucion" name ="resolucion" placeholder="Ingrese la resolución" required>
                                </div>
                                <div class="form-group">
                                    <label for="fecha">Fecha</label>
                                    <input type="date" class="form-control" id="fecha" name ="fecha" required>
                                </div>
                            </form>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-light">Imprimir</button>
                        </div>
                    </div>
                </div>
            </div>

        </form>

        <%--div class="modal fade" id="confirmacion" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Datos Requeridos</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p></p>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-light">SI</button>
                        <button type="button" class="btn btn-light" data-dismiss="modal">NO</button>
                    </div>
                </div>
            </div>
        </div--%>


    </body>
</html>
