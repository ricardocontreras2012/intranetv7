/*
(document).ready(function() {

    var $tabs=$('#table-draggable2');
    $( "tbody.connectedSortable" )
        .sortable({
            connectWith: ".connectedSortable",
            items: "> tr:not(:first)",
            appendTo: $tabs,
            helper:"clone",
            zIndex: 999990
        })
        .disableSelection()
    ;
    
    var $tab_items = $( ".nav-tabs > li", $tabs ).droppable({
      accept: ".connectedSortable tr",
      hoverClass: "ui-state-hover",
      
      drop: function( event, ui ) {
        return false;
      }
    });
    
});*/

$("#table1 .childgrid tr, #table2 .childgrid tr").draggable({
      helper: function(){
          var selected = $('.childgrid tr.selectedRow');
        if (selected.length === 0) {
          selected = $(this).addClass('selectedRow');
        }
        var container = $('<div/>').attr('id', 'draggingContainer');
    container.append(selected.clone().removeClass("selectedRow"));
    return container;
      }
 });

$("#table1 .childgrid, #table2 .childgrid").droppable({
    drop: function (event, ui) {
    $(this).append(ui.helper.children());
    $('.selectedRow').remove();
    }
});

$(document).on("click", ".childgrid tr", function () {
    $(this).toggleClass("selectedRow");
});
