function reloadPage()
{
    location.reload(true);
}

function showNewMencion()
{
    $("#modalAddMencion").modal('show');
}

function showModalOpcionAddUnidad(menCodCar, menCodMen, menNom) {
    $("#actual_menCodCar").val(menCodCar);
    $("#actual_menCodMen").val(menCodMen);
    
    $("#addUnidad_uniNom").val(menNom);
    
    $("#modalOpcionAddUnidad").modal('show');
}

function showModalAddUnidad(){
    $("#modalAddUnidad").modal('show');
}

function showModalEditUnidad(menCodCar, menCodMen, uniCod, uniNom, uniCC, uniUrl, uniTipo, uniColorSala, atributoUniAcadMayor, atributoUniAdmMayor, atributoUniMayor, atributoUniSuperior)
{
    $("#actual_menCodCar").val(menCodCar);
    $("#actual_menCodMen").val(menCodMen);
    
    $("#editUnidad_uniCod").val(uniCod);
    $("#editUnidad_uniNom").val(uniNom);
    $("#editUnidad_uniCC").val(uniCC);
    $("#editUnidad_uniUrl").val(uniUrl);
    $("#editUnidad_uniTipo").val(uniTipo);
    $("#editUnidad_uniColorSala").val(uniColorSala);
    $("#editUnidad_uniAcadMayor").val(atributoUniAcadMayor);
    $("#editUnidad_uniAdmMayor").val(atributoUniAdmMayor);
    $("#editUnidad_uniMayor").val(atributoUniMayor);
    $("#editUnidad_uniSuperior").val(atributoUniSuperior);
    
    $("#modalEditUnidad").modal('show');
}

function showModalUsedUnidad(uniCod, uniNom, uniCC, uniUrl, uniTipo, uniColorSala, atributoUniAcadMayor, atributoUniAdmMayor, atributoUniMayor, atributoUniSuperior)
{
    $("#modalListUnidad").modal('hide');
    
    $("#editUnidad_uniCod").val(uniCod);
    $("#editUnidad_uniNom").val(uniNom);
    $("#editUnidad_uniCC").val(uniCC);
    $("#editUnidad_uniUrl").val(uniUrl);
    $("#editUnidad_uniTipo").val(uniTipo);
    $("#editUnidad_uniColorSala").val(uniColorSala);
    $("#editUnidad_uniAcadMayor").val(atributoUniAcadMayor);
    $("#editUnidad_uniAdmMayor").val(atributoUniAdmMayor);
    $("#editUnidad_uniMayor").val(atributoUniMayor);
    $("#editUnidad_uniSuperior").val(atributoUniSuperior);
    
    $("#modalEditUnidad").modal('show');
}

function showModalEliminarUnidad(menCodCar, menCodMen, uniCod){
    $("#actual_menCodCar").val(menCodCar);
    $("#actual_menCodMen").val(menCodMen);
    $("#actual_uniCod").val(uniCod);
    
    $("#modalEliminarUnidad").modal('show');
}

function showModalEditMencion(menCodCar, menCodMen, menNom, menPrefijo, menPlanComun){
    $("#editMencion_menCodCar").val(menCodCar);
    $("#editMencion_menNom").val(menNom);
    $("#editMencion_menPrefijo").val(menPrefijo);
    $("#editMencion_menPlanComun").val(menPlanComun);
    
    $("#actual_menCodCar").val(menCodCar);
    $("#actual_menCodMen").val(menCodMen);
    
    $("#modalEditMencion").modal('show');
}

function addMencion() {
    const data_string = {
        'key': $("#key").val(),
        'menCodCar': $("#addMencion_menCodCar").val(),
        'menPrefijo': $("#addMencion_menPrefijo").val(),
        'menPlanComun': $("#addMencion_menPlanComun").val(),
        'menNom': $("#addMencion_menNom").val()
    };

    jQuery.ajax({
        url: "CommonRCurricularCreateMencion",
        type: "POST",
        data: data_string,
        success: function(response) {
            if(response.retValue === "success") {
                $("#modalAddMencion").modal('hide');
                
                $("#addMencion_menCodCar").val("");
                $("#addMencion_menPrefijo").val("N");
                $("#addMencion_menPlanComun").val("N");
                $("#addMencion_menNom").val("");
                
                $("#titleErrorMessage").text("Exito");
                $("#errorMessage").text("Se ha creado la mención exitosamente.");
                $("#avisoCreacion").modal('show');
            }
            
            else if(response.retValue === "error") {
                $("#modalAddMencion").modal('hide');
                
                $("#titleErrorMessage").text("Fallo");
                $("#errorMessage").text("Hubo un error al crear la mención.");
                $("#avisoCreacion").modal('show');
            }
        },
        error: function() {
            $("#modalAddMencion").modal('hide');
            
            $("#titleErrorMessage").text("Fallo");
            $("#errorMessage").text("Hubo un error al crear la mención. Fallo con la petición.");
            $("#avisoCreacion").modal('show');
        },
        async: false
    });
}

function editMencion() {
    const data_string = {
        key: $("#key").val(),
        menCodCar: $("#actual_menCodCar").val(),
        menCodMen: $("#actual_menCodMen").val(),
        menPrefijo: $("#editMencion_menPrefijo").val(),
        menPlanComun: $("#editMencion_menPlanComun").val(),
        menNom: $("#editMencion_menNom").val()
    };

    jQuery.ajax({
        url: "CommonRCurricularEditMencion",
        type: "POST",
        data: data_string,
        success: function(response) {
            if(response.retValue === "success") {
                $("#modalEditMencion").modal('hide');
                
                $("#editMencion_menCodCar").val("");
                $("#editMencion_menPrefijo").val("");
                $("#editMencion_menPlanComun").val("");
                $("#editMencion_menNom").val("");
                
                $("#titleErrorMessage").text("Exito");
                $("#errorMessage").text("Se ha editado la mención exitosamente.");
                $("#avisoCreacion").modal('show');
            }
            
            else if(response.retValue === "error") {
                $("#modalEditMencion").modal('hide');
                
                $("#titleErrorMessage").text("Fallo");
                $("#errorMessage").text("Hubo un error al editar la mención.");
                $("#avisoCreacion").modal('show');
            }
        },
        error: function() {
            $("#modalEditMencion").modal('hide');
            
            $("#titleErrorMessage").text("Fallo");
            $("#errorMessage").text("Hubo un error al crear la mención. Fallo con la petición.");
            $("#avisoCreacion").modal('show');
        },
        async: false
    });
}

function addUnidad()
{
    const data_string = {
        key: $("#key").val(),
        uniCod: $("#addUnidad_uniCod").val(),
        uniNom: $("#addUnidad_uniNom").val(),
        uniCC: $("#addUnidad_uniCC").val(),
        uniUrl: $("#addUnidad_uniUrl").val(),
        uniTipo: $("#addUnidad_uniTipo").val(),
        uniColorSala: $("#addUnidad_uniColorSala").val(),
        uniAcadMayor: $("#addUnidad_uniAcadMayor").val(),
        uniAdmMayor: $("#addUnidad_uniAdmMayor").val(),
        uniMayor: $("#addUnidad_uniMayor").val(),
        uniSuperior: $("#addUnidad_uniSuperior").val(),
        menCodCar: $("#actual_menCodCar").val(),
        menCodMen: $("#actual_menCodMen").val(),
        duty: 'create'
    };

    jQuery.ajax({
        url: "CommonRCurricularAddUnidad",
        type: "POST",
        data: data_string,
        success: function(response) {
            if(response.retValue === "success") {
                $("#modalAddUnidad").modal('hide');
                
                $("#addUnidad_uniCod").val("");
                $("#addUnidad_uniNom").val("");
                $("#addUnidad_uniCC").val("");
                $("#addUnidad_uniUrl").val("");
                $("#addUnidad_uniTipo").val("");
                $("#addUnidad_uniColorSala").val("");
                $("#addUnidad_uniAcadMayor").val("");
                $("#addUnidad_uniAdmMayor").val("");
                $("#addUnidad_uniMayor").val("");
                $("#addUnidad_uniSuperior").val("");
                
                $("#actual_menCodCar").val("");
                $("#actual_menCodMen").val("");
                
                $("#titleErrorMessage").text("Exito");
                $("#errorMessage").text("Se ha creado la unidad exitosamente.");
                $("#avisoCreacion").modal('show');
            }
            
            else if(response.retValue === "error") {
                $("#modalAddUnidad").modal('hide');
                
                $("#actual_menCodCar").val("");
                $("#actual_menCodMen").val("");
                
                $("#titleErrorMessage").text("Fallo");
                $("#errorMessage").text("Hubo un error al crear la unidad.");
                $("#avisoCreacion").modal('show');
            }
        },
        error: function() {
            $("#modalAddUnidad").modal('hide');
            
            $("#actual_menCodCar").val("");
            $("#actual_menCodMen").val("");

            $("#titleErrorMessage").text("Fallo");
            $("#errorMessage").text("Hubo un error al crear la mención. Fallo con la petición.");
            $("#avisoCreacion").modal('show');
        },
        async: false
    });
}

function editUnidad()
{
    const data_string = {
        key: $("#key").val(),
        uniCod: $("#editUnidad_uniCod").val(),
        uniNom: $("#editUnidad_uniNom").val(),
        uniCC: $("#editUnidad_uniCC").val(),
        uniUrl: $("#editUnidad_uniUrl").val(),
        uniTipo: $("#editUnidad_uniTipo").val(),
        uniColorSala: $("#editUnidad_uniColorSala").val(),
        uniAcadMayor: $("#editUnidad_uniAcadMayor").val(),
        uniAdmMayor: $("#editUnidad_uniAdmMayor").val(),
        uniMayor: $("#editUnidad_uniMayor").val(),
        uniSuperior: $("#editUnidad_uniSuperior").val(),
        menCodCar: $("#actual_menCodCar").val(),
        menCodMen: $("#actual_menCodMen").val(),
        duty: 'update'
    };

    jQuery.ajax({
        url: "CommonRCurricularAddUnidad",
        type: "POST",
        data: data_string,
        success: function(response) {
            if(response.retValue === "success") {
                $("#modalEditUnidad").modal('hide');
                
                $("#editUnidad_uniCod").val("");
                $("#editUnidad_uniNom").val("");
                $("#editUnidad_uniCC").val("");
                $("#editUnidad_uniUrl").val("");
                $("#editUnidad_uniTipo").val("");
                $("#editUnidad_uniColorSala").val("");
                $("#editUnidad_uniAcadMayor").val("");
                $("#editUnidad_uniAdmMayor").val("");
                $("#editUnidad_uniMayor").val("");
                $("#editUnidad_uniSuperior").val("");
                
                $("#actual_menCodCar").val("");
                $("#actual_menCodMen").val("");
                
                $("#titleErrorMessage").text("Exito");
                $("#errorMessage").text("Se ha modificado la unidad sin ningun problemas.");
                $("#avisoCreacion").modal('show');
            }
            
            else if(response.retValue === "error") {
                $("#modalEditUnidad").modal('hide');
                
                $("#actual_menCodCar").val("");
                $("#actual_menCodMen").val("");
                
                $("#titleErrorMessage").text("Fallo");
                $("#errorMessage").text("Hubo un error al modificar la unidad.");
                $("#avisoCreacion").modal('show');
            }
        },
        error: function() {
            $("#modalEditUnidad").modal('hide');
            
            $("#actual_menCodCar").val("");
            $("#actual_menCodMen").val("");

            $("#titleErrorMessage").text("Fallo");
            $("#errorMessage").text("Hubo un error al modificar la mención. Fallo con la petición.");
            $("#avisoCreacion").modal('show');
        },
        async: false
    });
}

function desligarUnidad(){
    const data_string = {
        key: $("#key").val(),
        menCodCar: $("#actual_menCodCar").val(),
        menCodMen: $("#actual_menCodMen").val(),
        uniCod: $("#editUnidad_uniCod").val(),
        duty: "Desligar"
    };

    jQuery.ajax({
        url: "CommonRCurricularDeleteUnidad",
        type: "POST",
        data: data_string,
        success: function(response) {
            if(response.retValue === "success") {
                $("#modalEliminarUnidad").modal('hide');
                
                $("#actual_uniCod").val("");
                $("#actual_menCodCar").val("");
                $("#actual_menCodMen").val("");
                
                $("#titleErrorMessage").text("Exito");
                $("#errorMessage").text("Se ha desligado la Unidad de la Mención.");
                $("#avisoCreacion").modal('show');
            }
            
            else if(response.retValue === "error") {
                $("#modalEliminarUnidad").modal('hide');
                
                $("#actual_uniCod").val("");
                $("#actual_menCodCar").val("");
                $("#actual_menCodMen").val("");
                
                $("#titleErrorMessage").text("Fallo");
                $("#errorMessage").text("Hubo un error al desligar la Unidad.");
                $("#avisoCreacion").modal('show');
            }
        },
        error: function() {
            $("#modalEliminarUnidad").modal('hide');
            
            $("#actual_uniCod").val("");
            $("#actual_menCodCar").val("");
            $("#actual_menCodMen").val("");

            $("#titleErrorMessage").text("Fallo");
            $("#errorMessage").text("Hubo un error al desligar la Unidad. Fallo con la petición.");
            $("#avisoCreacion").modal('show');
        },
        async: false
    });
}

function showModalListUnidad(){
    $("#modalListUnidad").modal('show');
}

$(document).ready(function () {
    $("#add-button").click(showNewMencion);
    
    $("#crearMencion-button").click(addMencion);
    $("#editarMencion-button").click(editMencion);
    
    $("#agregarUnidad-button").click(addUnidad);
    $("#editarUnidad-button").click(editUnidad);
    
    $("#modalOpcionAddUnidad_Crear").click(showModalAddUnidad);
    $("#modalOpcionAddUnidad_Utilizar").click(showModalListUnidad);
    
    $("#modalEliminarUnidad_Desligar").click(desligarUnidad);
    
    $(".close-aviso-creacion").click(reloadPage);
    $('#avisoCreacion').on('click', function(event){
        if($(event.target).is('#avisoCreacion'))
        {
            reloadPage();
        }
    });
    
    //###########################
    
    $("#menciones-table").DataTable({
        columnDefs:[
            { targets: 4, orderable: false}
        ],
        scrollCollapse: true,
        scrollY: '60vh',
        language: {
            info: 'Mostrando página _PAGE_ de _PAGES_',
            infoEmpty: 'Mostrando desde 0 hasta 0 de 0 registros',
            infoFiltered: '(filtrado de _MAX_ registros en total)',
            lengthMenu: 'Mostrar _MENU_ registros por página',
            zeroRecords: 'No se encontraron resultados',
            processing: 'Procesando...',
            search: "Buscar:",
            "paginate": {
                "first": "Primero",
                "last": "\u00daltimo",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });
    
    $("#unidades-table").DataTable({
        columnDefs:[
            { targets: 2, orderable: false}
        ],
        language: {
            info: '',
            infoEmpty: '',
            infoFiltered: '(filtrado de _MAX_ registros en total)',
            lengthMenu: '_MENU_',
            zeroRecords: 'No se encontraron resultados',
            processing: 'Procesando...',
            search: "Buscar:",
            "paginate": {
                "first": "Primero",
                "last": "\u00daltimo",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });
    
    
});