function getDoc(pos) {
    $("#pos").val(pos);
    $("#solicitudes-form").attr("action", "AlumnoSolicitudExpedienteNew");
    $("#solicitudes-form").attr("target", "_self");
    $("#solicitudes-form").submit();
}

/*$(document).ready(function () {
    //Handlers
    $("a").click(function () {
        var fieldName = $(this).attr("id");
        getDoc(fieldName.substr(fieldName.indexOf("_") + 1));
    });
});*/

$(document).ready(function () {
    $("a[id^='expl_']").click(function (e) {
        var $clickedRow = $(this).closest("tr");
        var logro = $clickedRow.data("logro");
        var pos = $(this).attr("id").split("_")[1];

        // Si se hace clic sobre un logro 50
        if (logro === 50) {
            var valid = false;
            var foundLogro20 = false;

            // Buscar la fila con logro 20
            $("#solicitudes-table tr").each(function () {
                var $row = $(this);
                var rowLogro = $row.data("logro");

                if (rowLogro === 20) {
                    foundLogro20 = true;

                    var estado = $row.data("estado");
                    var solicitud = $row.data("solicitud");

                    if (estado !== "GE" || (solicitud && solicitud !== "")) {
                        valid = true;
                    } else {
                        valid = false;
                    }

                    // Salimos del loop ya que encontramos la fila y validamos
                    return false;
                }
            });

            // Si no cumple la condición, mostrar modal y cancelar el clic
            if (foundLogro20 && !valid) {

                var modal = new bootstrap.Modal(document.getElementById('requiereLicenciaturaModal'));
                modal.show();

                e.preventDefault(); // Evita que se dispare el link
                return;
            }
        }
        
        getDoc(pos);
    });
});