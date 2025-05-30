function delRow(fila)
{
    $("#evidenciaRow_" + fila).remove();
}

function delEvidencia(fila)
{
    $("#pos").val(fila);
    $("#evidencia-form").attr("action", "TeleTrabajoDeleteEvidencia");
    $("#evidencia-form").submit();
}

function uploadFileToRow(fila)
{
    $("#formEscogido").val(fila);
    $("#creacionEvidencia").modal('show');
}

function downloadFileToRow(fila)
{
    $("#pos").val(fila);
    $("#evidencia-form").attr("action", "TeleTrabajoDownLoadEvidencia");
    $("#evidencia-form").submit();
}

function addEvidencia()
{
    var id = 0;
    var fila = document.getElementById("evidenciaRow_" + id);
    if(fila)
    {
        
    }
    else
    {
        var otro = '<form id="descripcion' + id + '-form" action="#" method="post" accept-charset="UTF-8"><input type="text" id="otro_' + id + '" name="descripcionEvidencia" class="form-control" maxlength="200"/></form>';
        var upload = '<button id="upload-button_' + id + '" title="Subir evidencia" type="button" onClick="uploadFileToRow(' + id + ')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-upload"></span>&nbsp; <span class="hidden-xs"></span></button>';
        var del = '<button id="delete-button_' + id + '" title="Eliminar" type="button" onClick="delRow(' + id + ')" class="btn btn-light" data-toggle="tooltip"><span class="fa fa-trash"></span>&nbsp; <span class="hidden-xs"></span></button>';
        var row = "<tr id=\"evidenciaRow_" + id + "\" ><td>" + otro + "</td><td></td><td>" + upload + del + "</td></tr>";
        $('#evidencia-table > tbody:last-child').append(row);
    }
}

function reloadPage()
{
    $("#evidencia-form").attr("action", "TeleTrabajoGetMiEvidencia");
    $("#evidencia-form").submit();
}

$(document).ready(function () {
    
    $("#posTarea").val($("#pos").val());
    
    $("#add-button").click(addEvidencia);
    
    $(".close-aviso-upload").click(reloadPage);
    
    $('#avisoUpload').on('click', function(event){
        if($(event.target).is('#avisoUpload'))
        {
            reloadPage();
        }
    });
    
    var isAdvancedUpload = function() {
    var div = document.createElement('div');
    return (('draggable' in div) || ('ondragstart' in div && 'ondrop' in div)) && 'FormData' in window && 'FileReader' in window;
    }();
    
    var $input = $('#form_dragndropFiles').find('input[type="file"]');
    var $label = $('#form_dragndropFiles').find('label');
    showFiles = function(files) {
        $label.text(files.length > 1 ? ($input.attr('data-multiple-caption') || '').replace('{count}', files.length) : files[0].name);
    };

    if (isAdvancedUpload) {

        var droppedFiles = false;

        $('#form_dragndropFiles').on('drag dragstart dragend dragover dragenter dragleave drop', function(e) {
            e.preventDefault();
            e.stopPropagation();
        })
        .on('dragover dragenter', function() {
            $('#form_dragndropFiles').addClass('is-dragover');
        })
        .on('dragleave dragend drop', function() {
            $('#form_dragndropFiles').removeClass('is-dragover');
        })
        .on('drop', function(e) {
            droppedFiles = e.originalEvent.dataTransfer.files;
            showFiles(droppedFiles);
        });
        
        $input.on('change', function(e) {
            showFiles(e.target.files);
        });
    }

    if (isAdvancedUpload) {
        $('#form_dragndropFiles').addClass('has-advanced-upload');
    }
    
    $('#subirBoton').on('click', function(e) {
        if ($('#form_dragndropFiles').hasClass('is-uploading')) return false;
        $('#form_dragndropFiles').addClass('is-uploading').removeClass('is-error');

        if (isAdvancedUpload) {
            e.preventDefault();

            var ajaxData = new FormData($('#form_dragndropFiles').get(0));

            if (droppedFiles) {
                $.each(droppedFiles, function(i, file) {
                    ajaxData.append($input.attr('name'), file);
                });
            }
            
            var data_string = $("#descripcion" + $("#formEscogido").val() + "-form").serialize();
            data_string = data_string + '&' + $("#form_dragndropFiles").serialize();

            $.ajax({
                url: 'TeleTrabajoUpLoadEvidencia' + '?key=' + $("#key").val() + '&idList=' + $("#formEscogido").val() + '&posT=' + $("#posTarea").val() + '&' + data_string,
                type: 'post',
                data: ajaxData,
                dataType: 'json',
                cache: false,
                contentType: false,
                processData: false,
                complete: function() {
                    $('#form_dragndropFiles').removeClass('is-uploading');
                },
                success: function() {
                    $(".modal-title").text("Exito");
                    $("#errorMessage").text("Los archivos se han enviado exitosamente.");
                    $("#avisoUpload").modal('show');
                    $("#pos").val($("#posTarea").val());
                },
                error: function() {
                    $(".modal-title").text("Fallo");
                    $("#errorMessage").text("Hubo un error al subir los archivos.");
                    $("#avisoUpload").modal('show');
                    $("#pos").val($("#posTarea").val());
                }
            });
        }
        else {
            var iframeName  = 'uploadiframe' + new Date().getTime();
            $iframe   = $('<iframe name="' + iframeName + '" style="display: none;"></iframe>');

            $('body').append($iframe);
            $('#form_dragndropFiles').attr('target', iframeName);

            $iframe.one('load', function() {
                var data = JSON.parse($iframe.contents().find('body' ).text());
                $('#form_dragndropFiles')
                    .removeClass('is-uploading')
                    .addClass(data.success === true ? 'is-success' : 'is-error')
                    .removeAttr('target');
                if (!data.success) $errorMsg.text(data.error);
                $('#form_dragndropFiles').removeAttr('target');
                $iframe.remove();
            });
        }
    });
});