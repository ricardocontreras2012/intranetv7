$(document).ready(function () {
    
    const picker = flatpickr("#fecha", {
        dateFormat: "d-m-Y",
        locale: "es",
        allowInput: true,
        clickOpens: false
    });

    document.getElementById("btnCalendario").addEventListener("click", (e) => {
        e.preventDefault();  // evita comportamiento por defecto del botón
        picker.open();
    });
    
    let selectedRowIndex = null;  // Variable global para guardar el índice de la fila seleccionada

    // Captura el clic en cualquier fila de la tabla
    $("table.table tbody tr").click(function () {
        selectedRowIndex = $(this).index(); // Guarda el índice de la fila seleccionada

        // Obtiene el contenido de las celdas de la fila seleccionada
        const rowContent = $(this).find("td").map(function () {
            return $(this).text().trim(); // Obtiene el texto de cada celda de la fila
        }).get();

        // Llenar el contenido del modal con el contenido de la fila seleccionada
        const contenidoModal = rowContent.join(", "); // Une el contenido de las celdas en un solo string
        $("#confirmacion .modal-body p").text("¿Desea emitir el certificado para: " + contenidoModal + "?");

        // Obtiene los valores de las celdas ocultas de la fila seleccionada
        const rol = $(this).find("td:nth-child(2)").text().trim();  // explRol
        const resolucion = $(this).find("td:nth-child(3)").text().trim();  // explNumResol
        const fecha = $(this).find("td:nth-child(4)").text().trim();  // explFecResol
        
        $("#fecha").val(fecha);
        // Asigna los valores obtenidos al modal
        $("#rol").val(rol);
        $("#resolucion").val(resolucion);

        // Muestra el modal
        $("#confirmacion").modal('show');
    });

    // Función de validación
    function validarFormulario() {
        const rol = $("#rol").val().trim();
        const resolucion = $("#resolucion").val().trim();
        const fecha = $("#fecha").val().trim();

        // Validar que los tres campos no estén vacíos
        if (rol === "" || resolucion === "" || fecha === "") {
            alert("Por favor, complete todos los campos: Rol, Resolución y Fecha.");
            return false; // Evita el envío del formulario
        }

        // Si todos los campos son válidos, permite continuar
        return true;
    }

    // Función emitir, llamada cuando el usuario hace clic en "Imprimir"
    function emitir() {
        if (validarFormulario()) {
            // Si la validación pasa, asigna el valor de selectedRowIndex y configura la acción
            $("#pos").val(selectedRowIndex); // Usamos la variable global
            $("#logros-form").attr("action", "TitulosyGradosAlumnoCertificadoPrint");

            // Finalmente, envía el formulario
            $("#logros-form").submit();

            // Actualizar la fila seleccionada con los nuevos valores
            actualizarFilaConDatos();
        }
    }

    // Función para actualizar la fila seleccionada con los valores del formulario
    function actualizarFilaConDatos() {
        // Obtener los nuevos valores
        const rol = $("#rol").val().trim();
        const resolucion = $("#resolucion").val().trim();
        const fecha = $("#fecha").val().trim();

        // Actualizar las celdas de la fila seleccionada
        const filaSeleccionada = $("table.table tbody tr").eq(selectedRowIndex); // Encuentra la fila seleccionada por índice
        filaSeleccionada.find("td:nth-child(2)").text(rol);        // Actualiza el rol
        filaSeleccionada.find("td:nth-child(3)").text(resolucion);  // Actualiza la resolución
        filaSeleccionada.find("td:nth-child(4)").text(fecha);       // Actualiza la fecha
    }

    // Captura el clic en el botón "Imprimir" para verificar la validación
    $("#confirmacion .modal-footer .btn-light").click(function () {
        $("#confirmacion").modal('hide');
        // Llamamos a la función emitir
        emitir(); // Llama a la función emitir sin necesidad de pasar rowIndex
    });
});
