
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ventana de Selección de Criterios</title>

    <!-- Incluyendo Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Incluyendo jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        /* Estilos personalizados */
        .criteria-group {
            margin-bottom: 1rem;
        }
    </style>

    <script>
        $(document).ready(function() {
            // Al cargar la página, los toggles deben estar deshabilitados
            $('#t1, #t2, #t3').prop('disabled', true);
            updateToggleLabels(); // Actualiza las etiquetas de los toggles al cargar la página

            // Sincroniza el estado de t1 con C1
            $('#c1').change(function() {
                if ($(this).prop('checked')) {
                    $('#t1').prop('disabled', false);  // Habilita el toggle t1
                } else {
                    $('#t1').prop('disabled', true);   // Deshabilita el toggle t1
                }
                updateToggleLabels(); // Actualiza las etiquetas de los toggles después del cambio
            });

            // Sincroniza el estado de t2 con C2 y desactiva C3 y t3 cuando C2 está marcado
            $('#c2').change(function() {
                if ($(this).prop('checked')) {
                    $('#t2').prop('disabled', false);  // Habilita el toggle t2
                    $('#c3').prop('checked', false); // Deselecciona C3
                    $('#t3').prop('disabled', true); // Deshabilita el toggle t3
                } else {
                    $('#t2').prop('disabled', true);  // Deshabilita el toggle t2
                }
                updateToggleLabels(); // Actualiza las etiquetas de los toggles después del cambio
            });

            // Sincroniza el estado de t3 con C3 y desactiva C2 y t2 cuando C3 está marcado
            $('#c3').change(function() {
                if ($(this).prop('checked')) {
                    $('#t3').prop('disabled', false);  // Habilita el toggle t3
                    $('#c2').prop('checked', false); // Deselecciona C2
                    $('#t2').prop('disabled', true); // Deshabilita el toggle t2
                } else {
                    $('#t3').prop('disabled', true); // Deshabilita el toggle t3
                }
                updateToggleLabels(); // Actualiza las etiquetas de los toggles después del cambio
            });

            // Actualiza las etiquetas de los toggles basados en su estado (ON/OFF)
            function updateToggleLabels() {
                // Para T1
                if ($('#t1').prop('checked')) {
                    $('#label-t1').text('Con Inscripción');
                } else {
                    $('#label-t1').text('Sin Inscripción');
                }

                // Para T2
                if ($('#t2').prop('checked')) {
                    $('#label-t2').text('Con Matrícula');
                } else {
                    $('#label-t2').text('Sin Matrícula');
                }

                // Para T3
                if ($('#t3').prop('checked')) {
                    $('#label-t3').text('Plopito');
                } else {
                    $('#label-t3').text('Plop');
                }
            }

            // Actualiza las etiquetas cuando los toggles cambian (de ON a OFF y viceversa)
            $('#t1, #t2, #t3').change(function() {
                updateToggleLabels(); // Actualiza las etiquetas al cambiar el estado de un toggle
            });
        });
    </script>
</head>
<body>
    <!-- Contenedor principal -->
    <div class="container mt-5">
        <h1 class="mb-4 text-center">Selección de Criterios</h1>

        <!-- Formulario con Bootstrap -->
        <div class="card p-4">
            <form action="yourAction" method="post">
                <!-- Criterio 1: Checkbox (c1) y Toggle Switch (t1) -->
                <div class="mb-3 row">
                    <label for="c1" class="col-sm-4 col-form-label">Inscripción</label>
                    <div class="col-sm-8">
                        <!-- Checkbox para C1 -->
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="c1" name="criterio1">
                            <label class="form-check-label" for="c1"></label>
                        </div>

                        <!-- Toggle Switch para t1 -->
                        <div class="form-check form-switch mt-2">
                            <input class="form-check-input" type="checkbox" id="t1" name="toggle1" disabled>
                            <label class="form-check-label" id="label-t1" for="t1">Sin Inscripción</label>
                        </div>
                    </div>
                </div>

                <!-- Criterio 2: Checkbox (c2) y Toggle Switch (t2) -->
                <div class="mb-3 row">
                    <label for="c2" class="col-sm-4 col-form-label">Matrícula</label>
                    <div class="col-sm-8">
                        <!-- Checkbox para C2 -->
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="c2" name="criterio2">
                            <label class="form-check-label" for="c2"></label>
                        </div>

                        <!-- Toggle Switch para t2 -->
                        <div class="form-check form-switch mt-2">
                            <input class="form-check-input" type="checkbox" id="t2" name="toggle2" disabled>
                            <label class="form-check-label" id="label-t2" for="t2">Sin Matrícula</label>
                        </div>
                    </div>
                </div>

                <!-- Criterio 3: Checkbox (c3) y Toggle Switch (t3) -->
                <div class="mb-3 row">
                    <label for="c3" class="col-sm-4 col-form-label">Irregular</label>
                    <div class="col-sm-8">
                        <!-- Checkbox para C3 -->
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="c3" name="criterio3">
                            <label class="form-check-label" for="c3"></label>
                        </div>

                        <!-- Toggle Switch para t3 -->
                        <div class="form-check form-switch mt-2">
                            <input class="form-check-input" type="checkbox" id="t3" name="toggle3" disabled>
                            <label class="form-check-label" id="label-t3" for="t3">Plop</label>
                        </div>
                    </div>
                </div>

                <!-- Botón de Enviar -->
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Enviar Selección</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Incluyendo JavaScript de Bootstrap 5 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
