<%--
    Document   : commonAlumnoMalla
    Created on : 08-05-2009, 08:07:14 AM
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="infrastructure.util.common.CommonMallaUtil"%>
<%@ page import="session.GenericSession" %>
<%@ page import="infrastructure.support.MallaNodoSupport" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Malla Curricular del Alumno</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-malla-3.0.0.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/common/alumno/commonAlumnoMalla-3.0.0.js"></script>
        <style>
            /* Contenedor general */
            .contenedor-malla {
                display: grid;
                gap: 0.5rem;
                padding: 0.5rem;
                overflow-x: auto;
                -webkit-overflow-scrolling: touch;
                background: #f1f5f9;
                /* display: flex;
              flex-direction: column;
              width: 100%;
              padding: 10px;
              border: 1px solid #ddd;*/
            }

            /* Cabecera */
            .fila-cabecera {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
                gap: 0.5rem;
                /*display: flex;*/
                /*justify-content: flex-start;*/
                /*margin-bottom: 10px;*/
            }

            .nivel-cabecera {
                font-size: 16px;
                font-weight: bold;
                /*margin-right: 20px;*/
                padding: 5px 10px;
                background-color: #e5e7eb;
                text-align: center;
                /*border: 1px solid #ddd;*/
                /*border-radius: 5px;*/
            }

            /* Fila de la malla */
            .fila-malla {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
                gap: 0.5rem;
                /*  display: flex;
                  justify-content: flex-start;
                  margin-bottom: 10px;
                  padding-bottom: 5px;*/
            }

            /* Estilo de los rectángulos de los nodos */
            .rectangulo-malla {
                box-sizing: border-box;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                border: solid;
                border-width: 1px;
                border-color: rgb(209 213 219);
                background-color: rgb(255 255 255);
                padding: 0.5rem;    

                /*position: relative;
                width: 180px; 
                height: 100px;
                margin-right: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;*/
            }

            /*.rectangulo-malla.texto-blanco {
              color: white;
            }*/

            /* Segmentos de cada rectángulo */
            .segmento-superior {
                /*height: 100%;*/
                padding: 0.125rem;
                /*  display: flex;
                  justify-content: space-between;
                  padding: 5px;*/
                /*background-color: #007bff;*/
                /*border-top-left-radius: 5px;
                border-top-right-radius: 5px;*/
            }

            .label-superior {
                display: flex;
                justify-content: space-between;
                align-items: center;
                width: 100%;
            }

            .numero-correlativo {
                font-weight: bold;
                color: white;
            }

            .codigo-asignatura {
                color: white;
                font-size: 12px;
            }

            /* Segmento inferior */
            .segmento-inferior {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                padding: 5px;
                height: 100%;
                /*background-color: #f9f9f9;
                border-bottom-left-radius: 5px;
                border-bottom-right-radius: 5px;*/
            }

            /* Área central con nombre del nodo */
            .area-central {
                font-size: 13px;
                padding: 2px 0;
            }

            /*.area-central.texto-blanco {
              color: white;
            }*/

            .label-requisitos {
                font-size: 12px;
            }

            /*.label-requisitos.texto-blanco {
              color: white;
            }*/

            .label-inferior {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .creditos {
                font-size: 14px;
            }

            .reprobaciones-rojo {
                font-size: 14px;
                color: #fff;
                background: #b91c1c;
                height: 1.5rem;
                width: 1.5rem;
                border-radius: 9999px;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            /* Color de fondo según la situación */
            .rectangulo-malla-A .segmento-superior {
                background-color: #15803d; /* Verde */
            }
            .rectangulo-malla-R .segmento-superior {
                background-color: #dc2626; /* Rojo */
            }
            .rectangulo-malla-I .segmento-superior {
                background-color: #eab308; /* Amarillo */
            }
            .rectangulo-malla-C .segmento-superior {
                background-color: #10b981; /* Verde Claro*/
            }
            /*.rectangulo-malla-P .segmento-superior {
                background-color: #f97316;
            }*/
            .rectangulo-malla-L .segmento-superior, .rectangulo-malla-S .segmento-superior {
                background-color: #cad5e2; /* Slate 300 / Gris claro */
            }
            .rectangulo-malla-E .segmento-superior, .rectangulo-malla-P .segmento-superior {
                background-color: #00d3f2; /* Amber 900 / Tierra */
            }
.tipo-electivo-H,
.tipo-electivo-C,
.tipo-electivo-E {
  background: #a3b3ff; color:#fff; border-radius:0.5rem; padding: 0 0.5rem;
}
.tipo-electivo-PRO {
  background: #629aed; color:#fff; border-radius:0.5rem; padding: 0 0.5rem;
}
.tipo-electivo-COM {
  background: #3ec8a5; color:#fff; border-radius:0.5rem; padding: 0 0.5rem;
}
.tipo-electivo-PRA {
  background: #ffad52; color:#fff; border-radius:0.5rem; padding: 0 0.5rem;
}
            /* Para cargar mensaje o error */
            .loading-message,
            .not-found {
                text-align: center;
                font-size: 18px;
                color: #888;
                padding: 20px;
            }

            .loading-message {
                color: #007bff;
            }

            .not-found {
                color: #f44336;
            }
/* zoom control */
.zoom-controls {
  position: fixed;
  top: 20px;
  right: 20px;
  background: rgba(255, 255, 255, 0.9);
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 8px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
  z-index: 1000;
  font-family: sans-serif;
}

.zoom-controls button {
  font-size: 18px;
  padding: 5px 10px;
  margin: 0 5px;
  cursor: pointer;
}

#zoom-level {
  font-size: 14px;
  min-width: 40px;
  display: inline-block;
  text-align: center;
}
        </style>
        
        
    </head>
    <body>
        <div class="">
            <div class="title-div-fixed">
                <s:text name="label.title.malla"/>&nbsp;<s:property
                    value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluRut"/>-<s:property
                    value="#session.genericSession.getWorkSession(key).aluCar.alumno.aluDv"/>&nbsp;&nbsp;<s:property
                    value="#session.genericSession.getWorkSession(key).aluCar.alumno.nombre"/>
            </div>
            <div id="contenedor" class="mt-5 ">
                <div class="zoom-controls">
                    <button id="zoom-out">−</button>
                    <span id="zoom-level">100%</span>
                    <button id="zoom-in">+</button>
                </div>
                <div class="contenedor-malla">

                </div>
                <form id="malla-form" action="#">
                    <div id="hidden-input-div">
                        <input type="hidden" id="asignatura" name="asignatura" value="<s:property value="asignatura"/>"/>
                        <input type="hidden" id="electiva" name="electiva" value="<s:property value="electiva"/>"/>
                        <input type="hidden" id="adicional" name="adicional" value="<s:property value="adicional"/>"/>
                        <input type="hidden" id="nombre" name="nombre" value="<s:property value="nombre"/>"/>
                        <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                    </div>
                </form>                    
                <s:if test="#session.genericSession.userType==\"AL\" && #session.genericSession.getWorkSession(key).aluCar.getParametros().getBloqueada() == \"SI\" "> 
                    <div class="modal fade" id="inscripcion" role="dialog">
                        <div class="modal-dialog" role="document">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Aviso</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Tu postulación de inscripción se encuentra actualmente en proceso. A partir del <s:date name="#session.genericSession.getWorkSession(key).aluCar.parametros.getTerminoFechaCorte()" format="dd/MM/yyyy HH:mm:ss"/>, podrás acceder al resultado del proceso. Te invitamos a estar atento(a) a la fecha indicada.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <script>
                        // Código para abrir el modal automáticamente al cargar la página
                        document.addEventListener('DOMContentLoaded', function () {
                            $('#inscripcion').modal('show');
                        });
                    </script>
                </s:if>
            </div>
        </div>

        <div class="modal fade" id="modal-notas" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div id="notas-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal-requisitos" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-body">
                        <div id="requisitos-div"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Cerrar</button>
                    </div>
                </div><
            </div>
        </div>
    </body>
</html>