function executeAction(posValue) {
    $("#pos").val(posValue);    
    $("#carreras-form").attr("action", $("#actionCall").val());
    $("#carreras-form").attr("target", "_self").submit();
}

$(document).ready(function () {
    //Handler
    $("a").click(function () {
        const field_name = $(this).attr("id");
        executeAction(field_name.substr(field_name.indexOf("_") + 1));
    });
});

