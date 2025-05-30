jQuery.extend(jQuery.validator.messages, {
    required: "Campo obligatorio.",
    email: "Formato de dirección de correo válido",
    url: "Formato de URL válido.",
    date: "Formato de fecha válido.",
    number: "Valor debe se numérico.",
    digits: "Valor debe ser número entero.",
    equalTo: "Valores no coinciden, por favor, confirme el mismo valor.",
    maxlength: jQuery.validator.format("Campo no debe tener más de {0} caracteres."),
    minlength: jQuery.validator.format("Campo no debe tener menos de {0} caracteres."),
    rangelength: jQuery.validator.format("Campo debe tener entre {0} y {1} caracteres."),
    range: jQuery.validator.format("Valor debe estar entre {0} y {1}."),
    max: jQuery.validator.format("Valor debe ser menor o igual que {0}."),
    min: jQuery.validator.format("Valor debe ser mayor o igual que {0}.")
});



