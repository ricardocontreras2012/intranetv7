
function uploadMaterial() {      
    $("#material-form").attr("action", "CommonMaterialUploadMaterial").submit();
}

$(document).ready(function () {
    $("#upload-button").click(uploadMaterial);
    //
    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });

    $.validator.addMethod("selectOption", function (value, element) {
        return this.optional(element) || ((value !== '0'));
    }, "Seleccione Tipo de Material");

    $("#material-form").validate({
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
});