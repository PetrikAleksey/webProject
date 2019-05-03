//------------------Добавить---------------------//
function doAddBank() {
    if($("#worker").hasClass("active") == true) {
        var newNameBank = $("#name_bank").val();
        $('.addContent').empty();
        sendAjax('addBank', 'GET', ({text: newNameBank}), successAddBank);
    }
}

function successAddBank(data){
    var row = $('<tr id = tr'+data.id+'></tr>').addClass('bar');
    var column0  = $('<td class="td-first"><div class="custom-control custom-checkbox">\n' +
        '  <input type="checkbox" class="custom-control-input" id="customCheck'+data.id+'"/>\n' +
        '  <label class="custom-control-label" for="customCheck'+data.id+'"></label>\n' +
        '</div></td>');
    var column1 = $('<td class="td-next"></td>').text(data.id).addClass('tdId');
    var column2 = $('<td class="td-next"></td>').text(data.name).addClass('tdName');
    row.append(column0);
    row.append(column1);
    row.append(column2);
    $("#result_tbody_id").append(row);
    addListeners(data);
    console.log("Save Bank");
}
//-----------------------------------------------//

//--------------------Удалить--------------------//
function doAllSelectedBankDelete() {
    if($("#bank").hasClass("active") == true) {
        var massBanks = []
        var allToDelete = $('.selectedToDelete');
        for (i = 0; i < allToDelete.length; i++) {
            var bank = {};
            bank.id = $(allToDelete[i], this).children().eq(1).text();
            bank.name = $(allToDelete[i], this).children().eq(2).text();
            massBanks.push(bank);
        }
        sendAjax('deleteBankAll', 'POST', JSON.stringify(massBanks), successDeleteBank);
    }
}

function successDeleteBank(data) {
    $.each(data, function (key, value) {
        $("#tr" + String(value.id)).remove();
    });
    console.log("Delete Bank");
}
//-----------------------------------------------//

//-----------------Загрузить---------------------//
//Срабатывает после загрузки DOM(после загрузкм всей страницы)
$( document ).ready(function(){
    if($("#bank").hasClass("active") == true) {
        sendAjax('loadBank', 'GET', "", successLoadBank);
    }
});

function successLoadBank(data) {
    $.each( data, function( key, value ) {
        var row = $('<tr id = tr'+value.id+'></tr>').addClass('bar');
        //var column0  = $('<td><input type="checkbox"/></td>');
        var column0  = $('<td class="td-first"><div class="custom-control custom-checkbox">\n' +
            '  <input type="checkbox" class="custom-control-input" id="customCheck'+value.id+'"/>\n' +
            '  <label class="custom-control-label" for="customCheck'+value.id+'"></label>\n' +
            '</div></td>');
        var column1 = $('<td class="td-next"></td>').text(value.id).addClass('tdId');
        var column2 = $('<td class="td-next"></td>').text(value.name).addClass('tdName');
        row.append(column0);
        row.append(column1);
        row.append(column2);
        $("#result_tbody_id").append(row);
        addListeners(value);
    });
    console.log("Load Bank");
}
//-----------------------------------------------//

//---------------------Изменить------------------//
function doEditBank() {
    if($("#bank").hasClass("active") == true) {
        var tr = $('.selectedToEdit').children();
        var id = $(tr, this).eq(1).text();
        var newNameBank = $("#name_bank").val();
        $('.addContent').empty();
        var json = new Object();
        json.id = id;
        json.name = newNameBank;
        sendAjax('editBank', 'POST', JSON.stringify(json), successEditBank);
    }
}

function successEditBank(data) {
    $('#tr'+data.id+'').children().eq(2).text(data.name);
    $('#tr'+data.id+'').removeClass('selectedToDelete');
    $('#tr' +data.id +'').removeClass('selectedToEdit');
    $('#customCheck'+data.id+'').prop("checked",false)
    console.log("Edit Bank");
}
//-----------------------------------------------//

//--------------------Листнеры-------------------//
function addListeners(data){
    $('#customCheck'+data.id+'').on('click',function () {
        if($('#customCheck'+data.id+'').prop("checked") == false){
            $('#tr'+data.id+'').removeClass('selectedToDelete');
            $('#tr'+data.id+'').removeClass('selectedToEdit');
        }
        else {
            $('#tr' + data.id + '').addClass('selectedToDelete');
            $('#tr'+data.id+'').addClass('selectedToEdit');
        }
        if (($('.selectedToEdit').length) > 1 || ($('.selectedToEdit').length) == 0){
            $('.buttonEdit').prop('disabled', true);
        }
        else {
            $('.buttonEdit').prop('disabled', false);
        }
        if (($('.selectedToDelete').length) == 0 ){
            $('.buttonDelete').prop('disabled', true);
        }
        else {
            $('.buttonDelete').prop('disabled', false);
        }
    });
}
//-----------------------------------------------//

//----------------Модальное окно-----------------//
function addContent(event) {
    if($("#worker").hasClass("active") == true) {
        if ($('.buttonEdit').hasClass("active") == true){
            //if (event.data.action == "Изменить") {
            console.log("Изменить");
            var tr = $('.selectedToEdit').children();
            var id = $(tr, this).eq(1).text();
            var name = $(tr, this).eq(2).text();
            var nameModal = "Изменение";
            var onClick = "onclick=doEditBank()";
            var nameButton = "Сохранить";
            $('.buttonEdit').removeClass("active");
        } else if($('.buttonAdd').hasClass("active") == true) {
            console.log("Добавить");
            var name = "";
            var nameModal = "Добавление";
            var onClick = "onclick=doAddBank()";
            var nameButton = "Добавить";
            $('.buttonAdd').removeClass("active");
        }
    }
    var modelContent = "\t<div class=\"modal fade\" id=\"exampleModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n" +
        "\t\t<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n" +
        "\t\t\t<div class=\"modal-content\">\n" +
        "\t\t\t\t<div class=\"modal-header\">\n" +
        "\t\t\t\t\t<h5 class=\"modal-title\" id=\"exampleModalLabel\">"+nameModal+"</h5>\n" +
        "\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" onclick=closeModel()>\n" +
        "\t\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\n" +
        "\t\t\t\t\t</button>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t<div class=\"modal-body\">\n" +
        "\t\t\t\t\tНазвание банка: <input id=\"name_bank\" name=\"bank\" value='"+name+"' type=\"text\"/> \n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t<div class=\"modal-footer\">\n" +
        "\t\t\t\t\t<button type=\"button\" id=\"saveModifiedBank\" class=\"btn btn-success\" data-dismiss=\"modal\" "+onClick+">"+nameButton+"</button>\n" +
        "\t\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" onclick=closeModel()>Закрыть</button>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t</div>\n" +
        "\t\t</div>\n" +
        "\t</div>"
    $('.addContent').append(modelContent);
    console.log("Load Modal");
}

function closeModel() {
    $('.addContent').empty();
    console.log("Close Bank");
}
//-----------------------------------------------//