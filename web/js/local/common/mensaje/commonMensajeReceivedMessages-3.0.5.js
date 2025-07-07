function showMessage(pos) {
    $("#pos").val(pos);
    var dataString = $("#received-messages-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeGetReceivedMessage?' + dataString);
}

$(document).ready(function () {
    
    var dt = $("#received-table").DataTable({
        ajax: 'CommonMensajeGetDataTable?key='+$("#key").val(),
        columnDefs: [
            { targets: 0, data: "mensaje.msgCorrel", title: "ID", visible: false, orderable: false },
            { targets: 1, data: null, defaultContent: '<input type="checkbox"/>', orderable: false, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('text-align', 'center');
                    $(td).css('width', '12px');
                }},
            { targets: 2, data: "msgdEstado", orderable: false, render: function (data, type) 
                {
                    if (type === 'display') {
                        if (data[0] === 'M') {
                            return '<img src="/intranetv7/images/local/icon/email_open_image.png" height="16" width="16" alt="email"/>';
                        }
                    }
                    return '';
                }},
            { targets: 3, data: "mensaje.nombreEnvShort", title: $("#sentBy").val(), orderable: true, render: function(data, type)
                {
                    return type === 'display'
                    ? '<a>' + data + '</a>'
                    : data;
                }, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('font-weight', 'normal');
                }},
            { targets: 4, data: "mensaje.subjectShort", title: $("#subject").val(), orderable: true, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('font-weight', 'normal');
                }},
            { targets: 5, data: "mensaje.formattedFullDate", title: $("#date").val(), orderable: true, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('text-align', 'center');
                    $(td).css('width', '15%');
                }},
            { targets: 6, data: null, orderable: false, render: function (data, type) 
                {
                    if (type === 'display') {
                        if (data.mensaje.msgAttach === 'S' || data.mensaje.msgTipo === 'IMG') {
                            return '<img src="/intranetv7/images/local/icon/attachment.png" height="16" width="16" alt="attach"/>';
                        }
                    }
                    return '';
                }, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('width', '2%');
                }}
        ],
        processing: true,
        serverSide: true,
        rowId: "mensaje.msgCorrel",
        order: [[5, 'desc']],
        rowCallback: function (row, data, index) {
            $('td:eq(0)', row).html('<input type="checkbox" id="ck_' + index + '" name="ck_' + index + '"/>');
        },
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
    
    
    $('#received-table tbody').on('click', 'a', function () {
        var pos = dt.row($(this).parents('tr')).index(); // $(this).text();
        showMessage(pos);
    });

    window.parent.$.unblockUI();
});