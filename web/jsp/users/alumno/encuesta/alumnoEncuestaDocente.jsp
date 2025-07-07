<%-- 
    Document   : alumnoEncuestaDocente
    Created on : 07-07-2025, 12:03:31
    Author     : Ricardo Contreras S.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Cuestionario de Evaluación de la Docencia</title>
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />        
        <link rel="stylesheet" href="/intranetv7/css/local/local-encuesta.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/alumno/encuesta/alumnoEncuestaDocente-3.0.5.js"></script>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            color: #222;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 850px;
            margin: 0 auto;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.08);
            padding: 30px;
        }
        .titulo-bg {
            padding: 10px 0px;
            margin-bottom: 30px;
            font-size: 1.2em;
            letter-spacing: 1px;
            border-bottom: solid 4px #ea7600;
            color: #ea7600;
        }
        h3.section-title {
            background-color: #00a499;
            color: #fff;
            padding: 10px 14px;
            border-radius: 4px;
            margin-top: 30px;
            margin-bottom: 18px;
            font-size: 1.15em;
            font-weight: bold;
        }
        .intro-text {
            margin-bottom: 30px;
            font-style: italic;
            color: #444;
            background: #e8f5ee;
            border-left: 5px solid #00a499;
            padding: 10px 18px;
            border-radius: 4px;
        }
        fieldset {
            border: 1px solid #00a499;
            border-radius: 5px;
            margin-bottom: 20px;
            padding: 15px;
        }
        legend {
            font-weight: bold;
            color: #00a499;
            padding: 0 10px;
        }
        label {
            display: block;
            margin: 8px 0;
        }
        input[type="radio"] {
            accent-color: #00a499;
            margin-right: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0 10px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
        }
        th {
            background-color: #00a49950;
            color: #394049;
            text-align: center;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        td:not(:first-child) {
            text-align: center;
            width: 50px;
        }
        .scale-info {
            font-style: italic;
            color: #00a499;
            margin-bottom: 10px;
        }
        textarea {
            width: 100%;
            border-radius: 4px;
            border: 1px solid #ccc;
            padding: 8px;
            resize: vertical;
            font-size: 1em;
        }
        button[type="submit"], .btn-encuesta {
            background: #00a499;
            color: #fff;
            padding: 14px 36px;
            border: none;
            border-radius: 28px;
            font-size: 1.15em;
            font-weight: bold;
            letter-spacing: 1px;
            box-shadow: 0 4px 16px rgba(0, 164, 153, 0.08);
            cursor: pointer;
            transition: background 0.3s, transform 0.2s, box-shadow 0.2s;
            margin-top: 24px;
            outline: none;
            position: relative;
            overflow: hidden;
        }
        button[type="submit"]:hover, .btn-encuesta:hover {
            background: #00a499;
            transform: translateY(-2px) scale(1.03);
            box-shadow: 0 8px 24px rgba(234, 118, 0, 0.15);
        }
        button[type="submit"]:active, .btn-encuesta:active {
            transform: scale(0.98);
        }

    </style>
</head>
<body>       
    <div class="container">
        <div class="titulo-bg">
            CUESTIONARIO DE EVALUACIÓN DE LA DOCENCIA 

        </div>
        <h3 class="section-title">
            <s:property
                value="#session.genericSession.getWorkSession(key).getCursoProfesor().getCurso().getNombreFull()"/>
            <br>
            <s:property
                value="#session.genericSession.getWorkSession(key).getCursoProfesor().getProfesor().getNombre()"/>
        </h3>
        <form id="encuesta-form">
            <h3 class="section-title">I. Aspectos generales</h3>
            <fieldset>
                <legend>¿Cuál es su género?</legend>
                <label><input type="radio" name="P_1_1" value="1"> Masculino</label>
                <label><input type="radio" name="P_1_1" value="2"> Femenino</label>
                <label><input type="radio" name="P_1_1" value="3"> Otro</label>
                <label><input type="radio" name="P_1_1" value="4"> No lo quiero decir</label>
            </fieldset>
            <fieldset>
                <legend>¿A qué porcentaje de las clases de esta asignatura asistió Ud. durante el semestre?</legend>
                <label><input type="radio" name="P_1_2" value="1"> Entre 0% y 25%</label>
                <label><input type="radio" name="P_1_2" value="2"> Entre 26% y 50%</label>
                <label><input type="radio" name="P_1_2" value="3"> Entre 51% y 74%</label>
                <label><input type="radio" name="P_1_2" value="4"> Entre 75% y 100%</label>
            </fieldset>
            <fieldset>
                <legend>¿Con qué frecuencia cumplió Ud. con las actividades académicas y obligaciones de esta asignatura?</legend>
                <label><input type="radio" name="P_1_3" value="1"> Nunca o casi nunca</label>
                <label><input type="radio" name="P_1_3" value="2"> Frecuentemente</label>
                <label><input type="radio" name="P_1_3" value="3"> A veces</label>
                <label><input type="radio" name="P_1_3" value="4"> Siempre o casi siempre</label>
            </fieldset>
            <fieldset>
                <legend>Sin considerar las horas de clases, ¿cuántas horas a la semana, aproximadamente, dedicó Ud. a la preparación y estudio de esta asignatura?</legend>
                <label><input type="radio" name="P_1_4" value="1"> Entre 0 y 1 hora a la semana</label>
                <label><input type="radio" name="P_1_4" value="2"> Entre 2 y 3 horas a la semana</label>
                <label><input type="radio" name="P_1_4" value="3"> Entre 4 y 5 horas a la semana</label>
                <label><input type="radio" name="P_1_4" value="4"> Entre 6 y 7 horas a la semana</label>
                <label><input type="radio" name="P_1_4" value="5"> Entre 8 y 9 horas a la semana</label>
                <label><input type="radio" name="P_1_4" value="6"> 10 o más horas a la semana</label>
            </fieldset>

            <h3 class="section-title">II. Señale con qué frecuencia se realizan las siguientes actividades en la asignatura:</h3>
            <p class="scale-info">1 - Nunca o casi nunca / 2 - A veces / 3 - Frecuentemente / 4 - Siempre o casi siempre / 5 - No aplica</p>
            <div style="overflow: auto;">
                <table>
                    <thead>
                        <tr>
                            <th>Preguntas</th>
                            <th>1</th>
                            <th>2</th>
                            <th>3</th>
                            <th>4</th>
                            <th>5</th>
                        </tr>
                    </thead>
                    <tbody>

                        <s:iterator value="#session.genericSession.getWorkSession(key).preguntasEncuesta" status="row">
                            <tr>
                                <td><s:property value="id.pregNro"/>.&nbsp;<s:property value="pregDes"/></td>
                                <td><input type="radio" name="P_2_<s:property value="#row.count"/>" value="1"></td>
                                <td><input type="radio" name="P_2_<s:property value="#row.count"/>" value="2"></td>
                                <td><input type="radio" name="P_2_<s:property value="#row.count"/>" value="3"></td>
                                <td><input type="radio" name="P_2_<s:property value="#row.count"/>" value="4"></td>
                                <td><input type="radio" name="P_2_<s:property value="#row.count"/>" value="0"></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>

            <fieldset>
                <legend>14. En su opinión, ¿cuáles fueron los aspectos positivos de la docencia en la asignatura?</legend>
                <textarea name="comentarioPositivo" rows="3" style="width:100%; padding:10px; box-sizing: border-box"></textarea>
            </fieldset>
            <fieldset>
                <legend>15. En su opinión, ¿cuáles son los aspectos por mejorar de la docencia en la asignatura?</legend>
                <textarea name="comentarioMejora" rows="3" style="width:100%; padding:10px; box-sizing: border-box"></textarea>
            </fieldset>
            <div style="text-align: center;">
                <button class="btn-encuesta" type="button" onclick="saveEncuesta();">Enviar evaluación</button>
            </div>

            <div id="hidden-input-div">
                <input type="hidden" id="key" name="key" value="<s:property value="key"/>"/>
                <input type="hidden" id="nPregDummy" name="nPregDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).preguntasEncuesta.size"/>"/>
                <input type="hidden" id="carreraDummy" name="carreraDummy"
                       value="<s:property value="#session.genericSession.getWorkSession(key).aluCar.id.acaCodCar"/>"/>
            </div>
        </form>
    </div>
</body>
</html>
