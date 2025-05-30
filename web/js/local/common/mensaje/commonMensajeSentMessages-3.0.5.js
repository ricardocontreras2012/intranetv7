function showMessage(pos) {
    $("#pos").val(pos);
    var dataString = $("#sent-messages-form").serialize();
    $('#message-iframe', window.parent.document).attr("src", 'CommonMensajeGetSentMessage?' + dataString);
}

$(document).ready(function () {
    var DtSent = $("#sent-table").DataTable({
        ajax: 'CommonMensajeGetSentDataTable?key='+$("#key").val(),
        columnDefs: [
            { targets: 0, data: "msgCorrel", title: "ID", visible: false, orderable: false },
            { targets: 1, data: null, defaultContent: '<input type="checkbox"/>', orderable: false, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('text-align', 'center');
                    $(td).css('width', '12px');
                }},
            { targets: 2, data: "paratShort", title: $("#sentTo").val(), orderable: true, render: function(data, type)
                {
                    return type === 'display'
                    ? '<a>' + data + '</a>'
                    : data;
                }, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('font-weight', 'normal');
                }},
            { targets: 3, data: "subjectShort", title: $("#subject").val(), orderable: true, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('font-weight', 'normal');
                }},
            { targets: 4, data: "formatedFullDate", title: $("#date").val(), orderable: true, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('text-align', 'center');
                    $(td).css('width', '15%');
                }},
            { targets: 5, data: null, orderable: false, render: function (data, type) 
                {
                    if (type === 'display') {
                        if (data.msgAttach === 'S' || data.msgTipo === 'IMG') {
                            return '<img src="/intranetv7/images/local/icon/attachment.png" height="16" width="16" alt="attach"/>';
                        }
                    }
                    return '';
                }, createdCell: function(td, cellData, rowData, row, col)
                {
                    $(td).css('width', '2%');
                }}
        ],
        rowId: "msgCorrel",
        processing: true,
        serverSide: true,
        order: [[4, 'desc']],
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
   
    $('#sent-table tbody').on('click', 'a', function () {
        var pos = DtSent.row($(this).parents('tr')).index(); // $(this).text();
        showMessage(pos);
    });
   
    window.parent.$.unblockUI();
});