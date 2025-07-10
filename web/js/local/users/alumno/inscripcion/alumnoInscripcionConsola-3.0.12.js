function showCambioMencionDialog()
{
    const data_string = $("#inscripcion-form").serialize();
    //var menDivValue = "";
    jQuery.ajax({
        url: "AlumnoInscripcionCambioMencionGetMencion",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#men-div").html(data);
            //menDivValue = data.trim();
        },
        async: false
    });
    if ($("#men-div").text().trim() === "Ingeniería Comercial en Administración de Empresas")
    {
        $("#cambio-mencion1").modal("show");
    } else
    {
        $("#cambio-mencion2").modal("show");
    }
}

function cambiarMencion()
{
    window.parent.parent.location = "AlumnoInscripcionCambioMencion?key=" + $("#key").val();
    $("#confirmacion").modal("hide");
}

function removeInscripcion() {
    $("#confirmacion").modal('hide');

    // Serializa los datos del formulario para enviarlos como parámetros en la URL
    const data_string = $("#inscripcion-iframe").contents().find("#inscripcion-form").serialize();

    // Obtiene los elementos iframe correspondientes
    const inscripcionIframe = $('#inscripcion-iframe');

    if (inscripcionIframe.length > 0) {
        // Establece un listener para cuando el iframe termine de cargar su nuevo contenido.
        inscripcionIframe.one('load', function () {
            const derechosIframe = $('#derechos-iframe');

            if (derechosIframe.length > 0) {
                derechosIframe.attr("src", 'AlumnoInscripcionGetDerechos?key=' + $("#key").val());
            }
        });

        // Cambia la fuente del primer iframe y espera a que termine su carga.
        if (data_string !== undefined && data_string !== "") {
            inscripcionIframe.attr("src", 'AlumnoInscripcionRemoveInscripcion?' + data_string);
        } else {
            console.log('No hay datos en el formulario.');
        }
    } else {
        console.log('#inscripción-frame no encontrado.');
    }
}

function showProfesor(profesor, pos, source) {
    const random_number = Math.floor(Math.random() * 10000);
    const html_text = "<div><img width=\"70\" height=\"80\" alt=\" \" src=\"dummy/" + random_number + "/intranetv7/CommonInscripcionGetFotoProfesor?key=" + $("#key").val() + "&pos=" + pos + "&source=" + source + "\"/><p>" + profesor.replace('/0/g', ' ') + "</p></div>";

    parent.$("#profesor-div").html(html_text);
    parent.$("#profesor").modal('show');
    return false;
}

function getInscripcion()
{
    const data_string = $("#inscripcion-form").serialize();
    $("#inscripcion-iframe").attr("src", 'AlumnoInscripcionGetInscripcion?' + data_string);
}

$(document).ready(function () {
    getInscripcion();
});
