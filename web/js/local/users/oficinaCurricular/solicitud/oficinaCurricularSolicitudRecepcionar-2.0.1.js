

function uploadDocumento() {
    if ($("#documentos-form").validate().form() === true) {
        $("#documentos-form").attr("enctype", "multipart/form-data");
        $("#documentos-form").attr("action", "OficinaCurricularSolicitudAddDocumento");
        $("#documentos-form").submit();

    }
}

function deleteDocumentos() {
    if (anyChecked("solicitud-form") === true) {
        $("#confirmacion").dialog("open");
    }
    else {
        $("#msg-div").dialog("open");
    }
    return false;
}

function downLoadDocumento(docto) {
    $("#documento").val(docto);
    $("#solicitud-form").attr("action", "CommonSolicitudDownLoadDocumento");
    $("#solicitud-form").attr("target", "_self");
    $("#solicitud-form").submit();
}

$(document).ready(function () {


    //Handlers
    $("#upload-button").click(uploadDocumento);
    $("#delete-button").click(deleteDocumentos);
    $("a").click(function () {
        const field_name = $(this).attr("id");
        downLoadDocumento(field_name.substr(field_name.indexOf("_") + 1));
    });

    //
    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });

    $.validator.addMethod("selectOption",
        function (value, element) {
            return this.optional(element) || ((value !== '0'));
        }
        , "Tipo Documento");

    $("#documentos-form").validate({
        rules: {
            tipo: {
                required: true,
                selectOption: true
            },
            upload: {
                required: true
            },
            caption: {
                required: true,
                maxlength: 500
            }
        },
        messages: {
            tipo: {
                required: jQuery.validator.messages.required
            },
            upload: {
                required: jQuery.validator.messages.required
            },
            caption: {
                required: jQuery.validator.messages.required,
                maxlength: jQuery.validator.messages.maxlength(500)
            }
        }
    });

    $("#confirmacion").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 400,
        buttons: {
            "NO": function () {
                $(this).dialog("close");
            },
            "SI": function () {
                $("#solicitud-form").attr("action", "OficinaCurricularSolicitudRemoveDocumento");
                $("#solicitud-form").attr("target", "_self");
                $("#solicitud-form").submit();
                $(this).dialog("close");
            }
        }
    });

    $("#msg-div").dialog({
        autoOpen: false,
        resizable: false,
        modal: true,
        width: 300,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        }
    });
});