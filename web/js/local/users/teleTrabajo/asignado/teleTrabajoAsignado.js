function showActividad(pos) {
    $("#pos").val(pos);
    var dataString = $("#actividades-form").serialize();
    $('#main-content-iframe', window.parent.document).attr("src", 'TeleTrabajoGetTareas?'+ dataString);
}

$(document).ready(function () {
    
    var dt = $("#actividades-table").DataTable({
        ajax: 'TeleTrabajoDataTableActividades?key='+$("#key").val(),
        columnDefs: [
            { targets: 0, data: null, defaultContent: "Tiene una actividad programada para: ", title: "Actividades:", orderable: false, render: function(data, type)
                {
                    return type === 'display'
                    ? '<a>' + 'Tiene una actividad programada para: ' + '</a>'
                    : data;
                }, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('font-weight', 'normal');
                }},
            { targets: 1, data: "id.formatedStandardDate", title: "Fecha", orderable: false, render: function(data, type)
                {
                    return type === 'display'
                    ? '<a>' + data + '</a>'
                    : data;
                }, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('font-weight', 'normal');
                }},
            { targets: 2, data: "atelEstado", title: "Estado", orderable: false}
        ],
        processing: true,
        serverSide: true,
        drawCallback: function (settings) {
            var api = new $.fn.dataTable.Api(settings);
            var data = api.ajax.json();

            var length = data.length;
            var start = data.start;
            var searchValue = data.searchValue;
            var tipoOrder = data.tipoOrder;
            var nombreDataColumnaActual = data.nombreDataColumnaActual;
            
            $("#length").val(length);
            $("#start").val(start);
            $("#searchValue").val(searchValue);
            $("#tipoOrder").val(tipoOrder);
            $("#nombreDataColumnaActual").val(nombreDataColumnaActual);
        }
    });
    
    
    $('#actividades-table tbody').on('click', 'a', function () {
        var pos = dt.row($(this).parents('tr')).index(); // $(this).text();
        showActividad(pos);
    });
});