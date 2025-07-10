<%-- 
    Document   : commonInscripcionResumen.jsp
    Created on : 16-04-2025, 9:07:30
    Author     : Ricardo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Resumen de Inscripción</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/dataTables/1.10.24/datatables.min.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/datatables.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/dataTables/1.10.24/dataTables.bootstrap4.min.js"></script>
        <style>
            #loadingModal {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);  /* Fondo oscuro semitransparente */
                z-index: 9999;
                display: none; /* Inicialmente oculto */
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .modal-content {
                background-color: white;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
                text-align: center;
                min-width: 200px; /* Ancho mínimo para evitar que sea muy pequeño */                
                width: 300px;  /* Establecer un ancho fijo (ajustar según sea necesario) */
                max-width: 50%;  /* El modal no debe exceder el 90% del ancho de la ventana */
            }

            .spinner {
                border: 4px solid #f3f3f3;
                border-top: 4px solid #3498db;
                border-radius: 50%;
                width: 50px;
                height: 50px;
                animation: spin 2s linear infinite;
                margin: 20px auto 0 auto;
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
        </style>
    </head>
    <body>
        <div class="container-fluid d-flex flex-column vh-100">
            <row>
                <div class="navbar-brand container-fluid">
                    <div class="title-div">
                        Alumnos Inscritos&nbsp;<s:property
                            value="#session.genericSession.getWorkSession(key).nombreCarrera"/>
                        &nbsp;<s:property
                            value="#session.genericSession.getWorkSession(key).agnoAct"/>
                        /<s:property
                            value="#session.genericSession.getWorkSession(key).semAct"/>
                    </div>
                </div>
            </row>
            <row>
                <div class="container-fluid container-menu">
                    <div class="row">
                        <div id="justified-button-bar" class="col-lg-12">
                            <div class="btn-group">
                                <div class="btn-group">
                                    <button id="print-button" title="Exportar" type="button" class="btn btn-light" onclick="exportResumen()">
                                        <span class="fa fa fa-file-excel-o"></span>&nbsp; <span class="hidden-xs"><s:text name="label.button.export"/></span>
                                    </button>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>
            </row>

            <row class="overflow-auto">
                <div class="container-fluid overflow-auto">
                    <div class="container mt-5">
                        <table id="tablaNomina" class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th> 
                                    <th>RUN</th>
                                    <th>Nivel</th>
                                    <th>Nivel Más Bajo</th>
                                    <th>Nivel Más Alto</th>
                                    <th>Inscritas</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <!-- Modal de "Cargando..." -->
                    <div id="loadingModal">
                        <div class="modal-content">
                            <h4>Cargando...</h4>
                            <div class="spinner"></div>
                        </div>
                    </div>

                    <script type="text/javascript">

                        function decodeHtml(html) {
                            const txt = document.createElement("textarea");
                            txt.innerHTML = html;
                            return txt.value;
                        }

                        showLoadingModal();

                        // Recuperamos directamente el JSON completo con corchetes
                        const jsonString = '<s:property value="#session.jefeCarreraSession.json"/>';
                        const decodedJsonString = decodeHtml(jsonString);

                        let nomina = [];

                        try {
                            nomina = JSON.parse(decodedJsonString);
                        } catch (e) {
                            console.error("Error al parsear JSON:", e);
                        }

                        // Funciones para mostrar y ocultar el modal con animación fade
                        function showLoadingModal() {
                            $('#loadingModal').fadeIn(200);
                        }

                        function hideLoadingModal() {
                            $('#loadingModal').fadeOut(200);
                        }

                        function exportResumen()
                        {
                            $("#resumen-form").attr("action", "CommonInscripcionResumenExport");
                            $("#resumen-form").attr("target", "_self");
                            $("#resumen-form").submit();
                        }

                        $(document).ready(function () {
                            // Mostrar modal antes de cargar datos
                            showLoadingModal();

                            // Dar tiempo al navegador para renderizar el modal antes de cargar la tabla
                            setTimeout(function () {
                                const tabla = $('#tablaNomina').DataTable({
                                    paging: false,
                                    searching: true,
                                    ordering: true,
                                    info: false,
                                    lengthChange: false,
                                    drawCallback: function () {
                                        const api = this.api();
                                        api.rows({page: 'current'}).every(function (rowIdx, tableLoop, rowLoop) {
                                            const row = this.node();
                                            $(row).find('td').eq(0).html(rowLoop + 1);
                                        });
                                        $('#tablaNomina tbody tr').css('height', '13px');
                                        $('#tablaNomina td').css('padding', '2px');

                                        // Ocultar modal cuando termine de dibujar la tabla
                                        hideLoadingModal();
                                    }
                                });

                                // Agregar filas a la tabla
                                nomina.forEach((item, index) => {
                                    tabla.row.add([
                                        index + 1,
                                        item.rut,
                                        item.niv,
                                        item.niv_mas_bajo,
                                        item.niv_mas_alto,
                                        item.total
                                    ]).draw(false);
                                });
                            }, 100); // Retraso de 100ms para que se muestre el modal primero
                        });
                    </script>
                    <form id="resumen-form" action="#" method="post">
                        <div id="hidden-input-div">
                            <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                        </div>
                    </form>
                </div>
            </row>
        </div>
    </body>
</html>
