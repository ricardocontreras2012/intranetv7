
function saveMaterial() {    
    $("#material-form").attr("action", "CommonMaterialModifyMaterial").submit();
}

$(document).ready(function () {
    //Handlers
    $("#save-button").click(saveMaterial);
    //
    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });

    $.validator.addMethod("selectOption",
            function (value, element) {
                return this.optional(element) || ((value !== '1'));
            }
    , "Seleccione Tipo de Material");

    $("#material-form").validate({
        rules: {
            tipo: {
                required: true,
                selectOption: true
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
            caption: {
                required: jQuery.validator.messages.required,
                maxlength: jQuery.validator.messages.maxlength(500)
            }
        }
    });

    $("#tipo option[value='" + $("#tipoDummy").val() + "']").prop("selected", true);
});