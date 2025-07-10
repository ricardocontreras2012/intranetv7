
function inscribir(pos) {
    $("#pos").val(pos);

    // Serializa los datos del formulario para enviarlos como parámetros en la URL
    const data_string = $("#cursos-form").serialize();

    // Obtiene los elementos iframe correspondientes
    const inscripcionIframe = $('#inscripcion-iframe', window.parent.document);
    const derechosIframe = $('#derechos-iframe', window.parent.document);

    // Asegurarse de que el iframe de inscripcion se haya cargado correctamente
    inscripcionIframe.on('load', function () {
        // Cuando el iframe de inscripción se haya cargado, cargamos el iframe de derechos
        derechosIframe.attr("src", 'AlumnoInscripcionGetDerechos?key=' + $("#key").val());
    });

    // Establecer la URL para el iframe de inscripcion
    inscripcionIframe.attr("src", 'AlumnoInscripcionAddInscripcion?' + data_string);
}

function showProfesor(profesor, pos, source) {
    // Genera un número aleatorio para evitar la cache en la imagen y bloqueos en firewalls
    const random_number = Math.floor(Math.random() * 10000);

    // Crea el contenido HTML con la foto y el nombre del profesor
    const html_text = "<div><img width=\"70\" height=\"80\" alt=\" \" src=\"dummy/" + random_number + "/intranetv7/CommonInscripcionGetFotoProfesor?key=" + $("#key").val() + "&pos=" + pos + "&source=" + source + "\"/><p>" + profesor.replace('/0/g', ' ') + "</p></div>";

    // Inserta el HTML generado en el div correspondiente de la ventana padre
    parent.$("#profesor-div").html(html_text);
    
    // Muestra la ventana modal con la información del profesor
    parent.$("#profesor").modal('show');
    
    return false;
}

$(document).ready(function () {
});