<%--
    Document   : profesorCalificacionEvaluacionSeleccionModalidad
    Created on : 15-ago-2014, 15:32:29
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Definición de Modalidad de Calificación</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/profesor/calificacion/profesorCalificacionEvaluacionSeleccionModalidad-3.0.0.js"></script>
        <style>
            /*body{
                background-color: #F0FAFF;
                font-family: Arial, verdana, sans-serif;
                text-decoration: none;
                font-size: 12px;
            }*/

            .tabla-relativa, .tabla-absoluta{
                width:400px;
                border-collapse: collapse;
                text-align: center;
                color: white !important;
            }
            .tabla-relativa tbody td, .tabla-absoluta tbody td{
                border: 1px solid #F0FAFF;
                padding: 3px 6px;
                color: white !important;
            }


            /* relativa */
            .tabla-relativa td:nth-child(1) {
                font-weight: 700;
                text-align: left;
            }
            .tabla-relativa td:nth-child(3) {
                font-size: 18px;
                text-align: center;
            }
            .tabla-relativa tr:nth-child(1) {
                background-color: red;
                color: white !important;
            }
            .tabla-relativa tr:nth-child(2), .tabla-relativa tr:nth-child(3) {
                background-color: #66a3d2;
                color: white !important;
            }
            .tabla-relativa tr:nth-child(4), .tabla-relativa tr:nth-child(5), .tabla-relativa tr:nth-child(6) {
                background-color: #25567b;
                color: white !important;
            }

            /* abosoluta 2*/

            .tabla-absoluta td:nth-child(1) {
                font-weight: 700;
                text-align: left;
            }

            .tabla-absoluta tr:nth-child(2), .tabla-absoluta tr:nth-child(3), .tabla-absoluta tr:nth-child(4) {
                background-color: #66a3d2;
                color: white !important;
            }
            .tabla-absoluta tr:nth-child(5), .tabla-absoluta tr:nth-child(6) {
                background-color: #25567b;
                color: white !important;
            }
        </style>

    </head>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.seleccione.modalidad.evaluacion" />
        </div>

        <form id="modalidad-form" action="#">
            
            <button id="absoluta-button" title="Estructura Absoluta" type="button" class="btn btn-light" ><span class="fa fa-list-ul" aria-hidden="true"></span>
                &nbsp; <span class="hidden-xs"><s:text name="label.button.absoluta" /></span>
            </button>

            <table class="table tabla-absoluta">
                <tbody>
                    <tr><td colspan="2" style="text-align: center; color:#00529B !important; background-color: #D3E5FA !important;">EJEMPLO</td></tr>
                    <tr>
                        <td width="250px">PEP #1</td><td width="150px">20%</td>
                    </tr>
                    <tr>
                        <td>PEP #2</td><td>20%</td>
                    </tr>
                    <tr>
                        <td>PEP #3</td><td>30%</td>
                    </tr>
                    <tr>
                        <td>Ayudantía #1</td><td>15%</td>
                    </tr>
                    <tr>
                        <td>Ayudantía #2</td><td>15%</td>
                    </tr>
                </tbody>
            </table>
            
            <button id="relativa-button" title="Estructura Relativa" type="button" class="btn btn-light" ><span class="fa fa-sitemap" aria-hidden="true"></span>
                &nbsp; <span class="hidden-xs"><s:text name="label.button.relativa" /></span>
            </button>

            <table class="table tabla-relativa">
                <tbody>
                    <tr><th colspan="3" style="text-align: center; color:#00529B !important; background-color: #D3E5FA !important;">EJEMPLO</th></tr>
                    <tr>
                        <td width="200px">PEP #1</td><td width="100px">40%</td><td rowspan="2" width="100px">70%</td>
                    </tr>
                    <tr>
                        <td>PEP #2</td><td>60%</td>
                    </tr>
                    <tr>
                        <td>Control #1</td><td>20%</td><td rowspan="3">30%</td>
                    </tr>
                    <tr>
                        <td>Control #2</td><td>40%</td>
                    </tr>
                    <tr>
                        <td>Control #3</td><td>40%</td>
                    </tr>
                </tbody>
            </table>
            
            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="modalidad" name="modalidad" value=""/>
            </div>
        </form>
    </body>
</html>
