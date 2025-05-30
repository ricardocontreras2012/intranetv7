
function enableRowSelection(tableSelector) {
    let lastSelected = null;

    $(tableSelector + " tbody tr").on("click", function (e) {
        if (e.shiftKey && lastSelected) {
            let start = $(tableSelector + " tbody tr").index(lastSelected);
            let end = $(tableSelector + " tbody tr").index(this);
            let rows = $(tableSelector + " tbody tr");
            let minIndex = Math.min(start, end);
            let maxIndex = Math.max(start, end);
            rows.slice(minIndex, maxIndex + 1).addClass("selected");
        } else {
            $(this).toggleClass("selected");
        }
        lastSelected = this;
    });
}

function removeIzqInscripcion() {
    $("#confirmacionLeft").modal('hide');

    var selectedRows = [];

    $('#nomina-izq-table tbody tr.selected').each(function () {
        var rowData = {
            idRow: $(this).find('td').eq(0).text()
        };
        selectedRows.push(rowData);
    });
    $("#listaIzq").val(JSON.stringify(selectedRows));

    var dataString = $("#lista-izq-form").serialize();
    $.ajax({
        url: "CommonInscripcionAdmRemoveNominaIzq",
        type: "POST",
        data: dataString,
        async: false
    });
    var data_string = {'key': $("#key").val()};
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosDer",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#cursos-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosIzq",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#cursos-izq").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetNominaDer",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#nomina-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetNominaIzq",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#nomina-izq").html(data);
        },
        async: false
    });
}

function removeDerInscripcion() {
    $("#confirmacionRight").modal('hide');
    var selectedRows = [];
    $('#nomina-der-table tbody tr.selected').each(function () {
        var rowData = {
            idRow: $(this).find('td').eq(0).text()
        };
        selectedRows.push(rowData);
    });
    $("#listaDer").val(JSON.stringify(selectedRows));

    var dataString = $("#lista-der-form").serialize();
    $.ajax({
        url: "CommonInscripcionAdmRemoveNominaDer",
        type: "POST",
        data: dataString,
        async: false
    });
    var data_string = {'key': $("#key").val()};
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosDer",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#cursos-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosIzq",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#cursos-izq").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetNominaDer",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#nomina-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetNominaIzq",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#nomina-izq").html(data);
        },
        async: false
    });
}

function traspasarInscripcionDer() {
    var selectedRows = [];
    $('#nomina-izq-table tbody tr.selected').each(function () {
        var rowData = {
            idRow: $(this).find('td').eq(0).text()
        };
        selectedRows.push(rowData);
    });
    $("#listaIzq").val(JSON.stringify(selectedRows));

    var dataString = $("#lista-izq-form").serialize();
    $.ajax({
        url: "CommonInscripcionAdmChangeNominaDer",
        type: "POST",
        data: dataString,
        success: function (data) {
            if (data.retValue === "no_elimina")
            {
                $('#msg-error-div').html("");
                for (let i = 0; i < data.errorMessages.length; i++)
                {
                    $('#msg-error-div').append('<p style="color: red;">' + data.errorMessages[i] + '</p>');
                }
                for (let i = 0; i < data.errors.length; i++)
                {
                    $('#msg-error-div').append('<p style="color: red;">' + data.errors[i] + '</p>');
                }
                $("#msg-error").modal('show');
            }
        },
        async: false
    });

    var data_string = {'key': $("#key").val()};
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosDer",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#cursos-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosIzq",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#cursos-izq").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetNominaDer",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#nomina-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetNominaIzq",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#nomina-izq").html(data);
        },
        async: false
    });
}

function traspasarInscripcionIzq() {
    var selectedRows = [];

    $('#nomina-der-table tbody tr.selected').each(function () {
        var rowData = {
            idRow: $(this).find('td').eq(0).text()
        };
        selectedRows.push(rowData);
    });
    $("#listaDer").val(JSON.stringify(selectedRows));

    var dataString = $("#lista-der-form").serialize();
    $.ajax({
        url: "CommonInscripcionAdmChangeNominaIzq",
        type: "POST",
        data: dataString,
        success: function (data) {
            if (data.retValue === "no_elimina")
            {
                $('#msg-error-div').html("");
                for (let i = 0; i < data.errorMessages.length; i++)
                {
                    $('#msg-error-div').append('<p style="color: red;">' + data.errorMessages[i] + '</p>');
                }
                for (let i = 0; i < data.errors.length; i++)
                {
                    $('#msg-error-div').append('<p style="color: red;">' + data.errors[i] + '</p>');
                }
                $("#msg-error").modal('show');
            }
        },
        async: false
    });

    var data_string = {'key': $("#key").val()};
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosDer",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#cursos-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosIzq",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#cursos-izq").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetNominaDer",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#nomina-der").html(data);
        },
        async: false
    });
    jQuery.ajax({
        url: "CommonInscripcionAdmGetNominaIzq",
        type: "POST",
        data: data_string,
        success: function (data) {
            $("#nomina-izq").html(data);
        },
        async: false
    });
}

$(document).ready(function () {
    var dataString = $("#adm-ins-form").serialize();
    jQuery.ajax({
        url: "CommonInscripcionAdmGetCursosIzq",
        type: "POST",
        data: dataString,
        success: function (data) {
            $("#cursos-izq").html(data);
        },
        async: false
    });

    $("#to-right").click(function () {
        traspasarInscripcionDer();
    });

    $("#to-left").click(function () {
        traspasarInscripcionIzq();
    });

    $("#delete-left").click(function () {
        $("#confirmacionLeft").modal('show');
    });

    $("#delete-right").click(function () {
        $("#confirmacionRight").modal('show');
    });
});