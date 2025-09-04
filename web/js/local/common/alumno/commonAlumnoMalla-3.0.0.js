
function showNotas(asignatura, nombre, electiva) {
    $("#asignatura").val(asignatura);
    $("#nombre").val(nombre);
    $("#electiva").val(electiva);
    const data_string = $("#malla-form").serialize();
    jQuery.ajax({
        url: "CommonAlumnoGetNotasAsignatura",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#notas-div").html(data);
        },
        async: false
    });

    $('#modal-notas').modal('show');
    return false;
}

function showAdicionales(adicional, nombre) {
    $("#adicional").val(adicional);
    $("#nombre").val(nombre);
    const data_string = $("#malla-form").serialize();
    jQuery.ajax({
        url: "CommonAlumnoGetNotasAdicional",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#notas-div").html(data);
        },
        async: false
    });

    $("#modal-notas").modal("show");
    return false;
}

function showRequisitos(asignatura, nombre) {
    $("#requisitos-div").text($("#asignatura_input" + asignatura).val());
    $("#modal-requisitos").modal("show");
    return false;
}

// Función para renderizar la malla con los nodos
function renderMalla(datosMalla) {
    const nivelesMalla = Array.from(new Set(datosMalla.map((item) => item.nivel)));
    nivelesMalla.sort((a, b) => a - b);

    const tipoElectivo = {
        PRO: 'Profundización',
        COM: 'Complementario',
        PRA: 'Práctico',
        E: 'Tipo E',
        C: 'Tipo C',
        H: 'Tipo H',
    };

    const mayor = Math.max(...nivelesMalla);
    console.log('Mayor nivel:', mayor);

    const filasMalla = Array.from(new Set(datosMalla.map((item) => item.linea)));
    filasMalla.sort((a, b) => a - b);

    $("#contenedor").css("width", `${mayor * 200}px`);

    const contenedor = $(".contenedor-malla");
    contenedor.empty();  // Limpiamos cualquier contenido previo

    // Cabecera de la malla
    let cabeceraHTML = '<div class="fila-cabecera">';
    nivelesMalla.forEach((nivel) => {
        cabeceraHTML += `<div class="nivel-cabecera">Nivel ${nivel}</div>`;
    });
    cabeceraHTML += '</div>';
    contenedor.append(cabeceraHTML);

    // Filas de la malla
    filasMalla.forEach((fila) => {
        let filaHTML = '<div class="fila-malla">';
        nivelesMalla.forEach((nivel) => {
            const nodo = datosMalla.find((item) => item.nivel === nivel && item.linea === fila);
            if (nodo) {
                const colorClass = getColorClass(nodo.situacion);
                let footerHTML = '';
                if (['PRO', 'COM', 'PRA', 'E', 'C', 'H'].includes(nodo.tipo)) {
                    footerHTML = `
                        <div class="segmento-footer">
                            <div class="tipo-electivo-${nodo.tipo}">${tipoElectivo[nodo.tipo]}</div>
                        </div>`;
                }

                filaHTML += `
                    <div class="rectangulo-malla rectangulo-malla-${colorClass}">
                        <div class="segmento-superior">
                            <div class="label-superior">
                                <div class="numero-correlativo">${nodo.correl}</div>
                                <div class="codigo-asignatura"><a onclick="showNotas(${nodo.asig},'${nodo.nombre}','${nodo.electivo}');">${nodo.asig}</a></div>
                
                            </div>
                        </div>
                        <div class="segmento-inferior">
                            <div class="area-central area-central-${colorClass}">${nodo.nombre}</div>
                            <div class="label-requisitos label-requisitos-${colorClass}">Req:${nodo.requisitos}</div>
                            <div class="label-inferior">
                                <div class="creditos creditos-${colorClass}">${nodo.tel_sct}</div>
                                ${nodo.reprobaciones > 0 ? `<div class="reprobaciones-rojo">${nodo.reprobaciones}</div>` : ''}
                            </div>
                        </div>
                        ${footerHTML}
                    </div>
                `;
            } else {
                filaHTML += `<div class="rectangulo-malla" style="visibility: hidden"></div>`;
            }
        });
        filaHTML += '</div>';
        contenedor.append(filaHTML);
    });
}

// Función para obtener el color de la clase de acuerdo a la situación
function getColorClass(situacion) {
    let colorClass = situacion;
    if (situacion === 'R' || situacion === 'A') {
        colorClass += ' texto-blanco';  // Asignar color blanco si la situación es 'R' o 'A'
    }
    return colorClass;
}

let currentZoom = 1;

function updateZoom() {
    console.log("update zoom")
    $('.contenedor-malla').css({
        'transform': `scale(${currentZoom})`,
        'transform-origin': 'top left'
    });
    $('#zoom-level').text(`${Math.round(currentZoom * 100)}%`);
}

$(document).ready(function () {

    $('#zoom-in').on('click', function () {
        console.log("zoom in");
        if (currentZoom < 2) {
            currentZoom += 0.1;
            updateZoom();
        }
    });

    $('#zoom-out').on('click', function () {
        console.log("zoom out");
        if (currentZoom > 0.5) {
            currentZoom -= 0.1;
            updateZoom();
        }
    });

    updateZoom(); // Inicializa zoom

    // Llamada AJAX para obtener los datos desde el servidor
    const data_string = $("#malla-form").serialize();
    jQuery.ajax({
        url: "CommonAlumnoGetMallaJson",
        type: "POST", // o "POST", dependiendo de cómo esté configurado el backend
        dataType: "json", // asumiendo que devuelve JSON
        data: data_string,
        success: function (response) {
            renderMalla(response.malla);
        },
        error: function (xhr, status, error) {
            console.error("Error al obtener los datos:", error);
        }
    });
});
