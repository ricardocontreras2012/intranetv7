function sendMessage() {
    if ($("#new-mensaje-form").validate().form() === true) {

        deleteNullAttach();
        $("#new-mensaje-form :input").each(function () {
            var fieldName = $(this).attr("id");
            if (fieldName !== null && fieldName !== undefined && fieldName.startsWith("upload")) {
                $(this).removeAttr('id');
            }
        });
        $.blockUI();
       
        $("#new-mensaje-form").attr("action", "CommonMensajeSendMessage").attr("method", "post").attr("target", "_self").submit();

        return true;
    } else {
        return false;
    }
}

function deleteNullAttach() {
    var row = $('#attach-table tr:last').attr('id');
    if (!$("#" + row).is(":visible")) {
        if (row !== undefined) {
            $("#" + row).remove();
        }
    }
}

function addAttach() {
    deleteNullAttach();
    var n = $("#attach-table tr").length + 1;
    var sufijo = randomString(16, '#');

    $("#attach-table").append('<tr id="row_' + n + '" style="height:40px;display: none"><td style="width: 20%;"><span class="fa fa-paperclip"></span>&nbsp;Archivo Adjunto:</td><td><input class="form-control" name="upload" id="upload' + sufijo + '" size="45" value="" type="file"></td><td id="size_' + n + '"></td><td><button id="button' + n + '" title="Eliminar" type="button" class="btn btn-default" data-toggle="tooltip" onclick="deleteAttach(' + n + ')"><span class="fa fa-trash" aria-hidden="true"></span>&nbsp; <span class="hidden-xs">Eliminar</span></button></td></tr>');
    $('#upload' + sufijo).bind('change', function () {
        $("#size_" + n).html(getSize(this.files[0].size));
        $("#row_" + n).show();
    });
    $("#upload" + sufijo).trigger("click");
}

function getSize(size) {
    if (size < 1014) {
        return size + " Bytes";
    } else {
        if (size >= 1024 * 1024) {
            return Math.round(size / 1024 / 1024) + " MB";
        } else {
            return Math.round(size / 1024) + " KB";
        }
    }
}

function deleteAttach(n) {
    $("#row_" + n).remove();

    var l = $("#attach-table tr").length;
    for (var i = n; i <= l; i++) {
        $("#row_" + (i + 1)).attr("id", "row_" + i);
        $("#size_" + (i + 1)).attr("id", "size_" + i);
        $("#button" + (i + 1)).attr("onclick", "deleteAttach(" + i + ")");
        $("#button" + (i + 1)).attr("id", "button" + i);
    }
}

function getPage(url)
{
    $.get('CommonMensajeGetPage', {
        'key': $("#key").val(),
        'url': url
    }, function (data) {
        $("#messageHtml").val(data);
    });
}

function clear()
{
    $("#messageText").val("");
    $("#messageHtml").val("");
    $("#url").val("");
    $('#imagen').val("");
    $('#url').attr('readonly', true);
    $("#image-div").find("*").prop('disabled', true);
    $('textarea[name="messageText"]').attr('readonly', true);
}


$(document).ready(function () {
    clear();

    if ( $("#flag").val()==="N")
    {
       $('#messageText').attr('readonly', false);
    }

    $('input:radio').change(function () {
        clear();
        switch (this.value)
        {
            case "TXT":
            {
                $('#messageText').attr('readonly', false).focus();
                break;
            }
            case "HTML":
            {
                $('#url').attr('readonly', false).focus();
                break;
            }
            case "IMG":
            {
                $("#image-div").find("*").prop('disabled', false).focus();
                break;
            }
        }
    });

    $("#url").change(function () {
        $("#messageText").val($("#url").val());
        getPage($("#url").val());
    });

    jQuery("textarea").each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });

});