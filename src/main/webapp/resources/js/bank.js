function sendAjax(url,type,data,callback) {
    $.ajax({
        url : url,
        type: type,
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        data : data,
        success: function (data) {
            callback(data);
        }
    });
}
//------------------Добавить---------------------//
function doAdd() {

    if($("#bank").hasClass("active") === true) {
        var newNameBank = $("#name_bank").val();
        closeModel();
        sendAjax('addBank', 'GET', ({text: newNameBank}), successAdd);
    }
    else if($("#worker").hasClass("active") === true) {
        var fio = $("#fio").val();
        var position = $("#select-position").val();
        var phone = $("#phone").val();
        var idBank = $("#select-bank").val();
        var nameBank = $("#select-bank option:selected").text();
        closeModel();
        var objBank = {};
        objBank.id = idBank;
        objBank.name = nameBank;
        var objWorker = {}
        objWorker.fio = fio;
        objWorker.position = position;
        objWorker.phone = phone;
        objWorker.bank = objBank;
        sendAjax('addWorker', 'POST', JSON.stringify(objWorker), successAdd);
    }
    else if($("#client").hasClass("active") === true) {
        var fio = $("#fio").val();
        var phoneNumber = $("#phoneNumber").val();
        var address = $("#address").val();
        var email = $("#email").val();
        var idBank = $("#select-bank").val();
        var nameBank = $("#select-bank option:selected").text();
        closeModel();
        var objBank = {};
        objBank.id = idBank;
        objBank.name = nameBank;
        var objClient = {};
        objClient.fio = fio;
        objClient.phoneNumber = phoneNumber;
        objClient.address = address;
        objClient.email = email;
        objClient.bank = objBank;
        sendAjax('addClient', 'POST', JSON.stringify(objClient), successAdd);
    }
    else if($("#account").hasClass("active") === true) {
        var login = $("#login").val();
        var password = $("#password").val();
        var currency = $("#currency").val();
        var idClient = $("#select-client").val();
        closeModel();
        sendAjax('getClient', 'POST', JSON.stringify(idClient), function (data) {
            var objBankAccount = {};
            objBankAccount.login = login;
            objBankAccount.password = password;
            objBankAccount.currency = currency;
            var objClient = data;
            objBankAccount.client = objClient;
            sendAjax('addBankAccount', 'POST', JSON.stringify(objBankAccount), successAdd);
        });
    }
}

function successAdd(data){
    var table = $('#result_table_id').DataTable();
    if($("#bank").hasClass("active") === true) {
        table.row.add({
            "id": data.id,
            "name": data.name
        }).draw();
    }else if($("#worker").hasClass("active") === true) {
        // var column3 = $('<td class="td-next" id ="'+data.positionObjName+'"></td>').text(data.position).addClass('tdPosition');
        // var column5 = $('<td class="td-next" id ="'+data.bankId+'" ></td>').text(data.bankName).addClass('tdBank');
        table.row.add({
            "id": data.id,
            "fio": data.fio,
            "position": data.position,
            "phone": data.phone,
            "bankName": data.bankName
        }).draw();
    }else if($("#client").hasClass("active") === true) {
        //var column6 = $('<td class="td-next" id ="'+data.bankId+'" ></td>').text(data.bankName).addClass('tdBank');
        table.row.add({
            "id": data.id,
            "phoneNumber": data.phoneNumber,
            "address": data.address,
            "email": data.email,
            "bankName": data.bankName
        }).draw();
    }else if($("#account").hasClass("active") === true) {
        //var column5 = $('<td class="td-next" id ="'+data.clientId+'" ></td>').text(data.clientFIO).addClass('tdClient');
        table.row.add({
            "id": data.id,
            "login": data.login,
            "password": data.password,
            "currency": data.currency,
            "clientFIO": data.clientFIO
        }).draw();
    }
    //addListeners(data);
    console.log("Save");
}
//-----------------------------------------------//
//--------------------Удалить--------------------//
function doAllSelectedDelete() {
    var mass = []
    var allToDelete = $('.selectedToDelete');

    if($("#bank").hasClass("active") === true) {
        for (i = 0; i < allToDelete.length; i++) {
            // var bank = {};
            // bank.id = $(allToDelete[i], this).children().eq(1).text();
            // bank.name = $(allToDelete[i], this).children().eq(2).text();
            var id = $(allToDelete[i], this).children().eq(1).text();
            mass.push(id);
        }
        sendAjax('deleteBankAll', 'POST', JSON.stringify(mass), successDelete);
    }
    else if($("#worker").hasClass("active") === true) {
        for (i = 0; i < allToDelete.length; i++) {
            var worker = {};
            var bank = {};
            worker.id = $(allToDelete[i], this).children().eq(1).text();
            worker.fio = $(allToDelete[i], this).children().eq(2).text();
            worker.position = $(allToDelete[i], this).children().eq(3).attr("id");
            worker.phone = $(allToDelete[i], this).children().eq(4).text();
            bank.id = $(allToDelete[i], this).children().eq(5).attr("id");
            bank.name = $(allToDelete[i], this).children().eq(5).text();
            worker.bank = bank;
            mass.push(worker);
        }
        sendAjax('deleteWorkerAll', 'POST', JSON.stringify(mass), successDelete);
    }
    else if($("#client").hasClass("active") === true) {
        for (i = 0; i < allToDelete.length; i++) {
            var client = {};
            var bank = {};
            client.id = $(allToDelete[i], this).children().eq(1).text();
            client.fio = $(allToDelete[i], this).children().eq(2).text();
            client.phoneNumber = $(allToDelete[i], this).children().eq(3).text();
            client.address = $(allToDelete[i], this).children().eq(4).text();
            client.email = $(allToDelete[i], this).children().eq(5).text();
            bank.id = $(allToDelete[i], this).children().eq(6).attr("id");
            bank.name = $(allToDelete[i], this).children().eq(6).text();
            client.bank = bank;
            mass.push(client);
        }
        sendAjax('deleteClientAll', 'POST', JSON.stringify(mass), successDelete);
    }
    else if($("#account").hasClass("active") === true) {
        for (i = 0; i < allToDelete.length; i++) {
            var bankAccount = {};
            bankAccount.id = $(allToDelete[i], this).children().eq(1).text();
            bankAccount.login = $(allToDelete[i], this).children().eq(2).text();
            bankAccount.password = $(allToDelete[i], this).children().eq(3).text();
            bankAccount.currency = $(allToDelete[i], this).children().eq(4).text();
            var client = {};
            var idClient = $(allToDelete[i], this).children().eq(5).attr("id");
            sendAjax('getClient', 'POST', JSON.stringify(idClient), function (data) {
                client = data;
            });
            bankAccount.client = client;
            mass.push(bankAccount);
        }
        sendAjax('deleteBankAccountAll', 'POST', JSON.stringify(mass), successDelete);
    }
}

function successDelete(data) {
    var table = $('#result_table_id').DataTable();
    var rows = table.rows( '.selectedToDelete' );
    rows.remove().draw();
    console.log("Delete");
}
//-----------------------------------------------//

//-----------------Загрузить---------------------//
//Срабатывает после загрузки DOM(после загрузкм всей страницы)
$( document ).ready(function(){
    if($("#bank").hasClass("active") === true) {
        sendAjax('loadBank', 'GET', "", successLoad);
    }
});

function addHeadListeners() {
    $('#customCheck').on('click',function () {
        //var allIdToDelete = $('#result_table_id tbody tr td div input');
        var allTR = $('#result_table_id tbody tr');
        var allcheckbox = $('#result_table_id tbody tr td input');

        if($('#customCheck').prop("checked") === false){
            allTR.removeClass("selectedToDelete");
            allTR.removeClass("selectedToEdit");
            allcheckbox.prop("checked",false);
        }
        else {
            allTR.addClass("selectedToDelete");
            allTR.addClass("selectedToEdit");
            allcheckbox.prop("checked",true);
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

function initTable(tableId,data,cols){
    var headCheckbox ="<div class=\"custom-control custom-checkbox\">\n" +
        "<input type=\"checkbox\" class=\"custom-control-input\" id=\"customCheck\">\n" +
        "<label class=\"custom-control-label\" for=\"customCheck\"></label>\n" +
        "</div>\n";
    var checkbox = "<input type=\"checkbox\" autocomplete=\"off\">";
    var table = "";

     if($('#' + tableId).hasClass("dataTable")){
         $('#content').empty();
         $('#content').append("<table id = \"result_table_id\" class = \"result_table\"></table>")
     }
    if($("#bank").hasClass("active") === true) {
        table = $('#' + tableId).DataTable({
            "columnDefs": [
                {"width": "5%", orderable: false, "className": "text-center", "targets": 0}
            ],
            select: {
                style: 'os',
                selector: 'td:first-child'
            },
            data: data,
            rowId: "id",
            aoColumns: [
                {"defaultContent": checkbox, "sTitle": headCheckbox},
                {"sTitle": "ID", "mData": "id"},
                {"sTitle": "Банк", "mData": "name"}
            ]
        });
    }else if($("#worker").hasClass("active") === true) {
        table = $('#' + tableId).DataTable({
            "columnDefs": [
                {"width": "5%", orderable: false, "className": "text-center", "targets": 0}
            ],
            select: {
                style: 'os',
                selector: 'td:first-child'
            },
            data: data,
            rowId: "id",
            aoColumns: [
                {"defaultContent": checkbox, "sTitle": headCheckbox},
                {"sTitle": "ID", "mData": "id"},
                {"sTitle": "ФИО", "mData": "fio"},
                {"sTitle": "Должность", "mData": "position"},
                {"sTitle": "Телефон", "mData": "phone"},
                {"sTitle": "Банк", "mData": "bankName"}
            ]
        });
    }else if($("#client").hasClass("active") === true) {
        table = $('#' + tableId).DataTable({
            "columnDefs": [
                {"width": "5%", orderable: false, "className": "text-center", "targets": 0}
            ],
            select: {
                style: 'os',
                selector: 'td:first-child'
            },
            data: data,
            rowId: "id",
            aoColumns: [
                {"defaultContent": checkbox, "sTitle": headCheckbox},
                {"sTitle": "ID", "mData": "id"},
                {"sTitle": "Телефон", "mData": "phoneNumber"},
                {"sTitle": "Адрес", "mData": "address"},
                {"sTitle": "Email", "mData": "email"},
                {"sTitle": "Банк", "mData": "bankName"}
            ]
        });
    }else if($("#account").hasClass("active") === true) {
        table = $('#' + tableId).DataTable({
            "columnDefs": [
                {"width": "5%", orderable: false, "className": "text-center", "targets": 0}
            ],
            select: {
                style: 'os',
                selector: 'td:first-child'
            },
            data: data,
            rowId: "id",
            aoColumns: [
                {"defaultContent": checkbox, "sTitle": headCheckbox},
                {"sTitle": "ID", "mData": "id"},
                {"sTitle": "Логин", "mData": "login"},
                {"sTitle": "Пароль", "mData": "password"},
                {"sTitle": "Денежные средства", "mData": "currency"},
                {"sTitle": "Клиент", "mData": "clientFIO"}
            ]
        });
    }
    // $('#'+tableId+' tbody').on( 'click', 'tr', function () {
    //     $(this).toggleClass('selected');
    // });
    return table;
}


function successLoad(data) {
    initTable("result_table_id",data,data);
    addHeadListeners();
    addBodyListeners();
}
//-----------------------------------------------//

//---------------------Изменить------------------//
function doEdit() {
    var tr = $('.selectedToEdit').children();
    var id = $(tr, this).eq(1).text();

    if($("#bank").hasClass("active") === true) {
        var newNameBank = $("#name_bank").val();
        closeModel();
        var objBank = new Object();
        objBank.id = id;
        objBank.name = newNameBank;
        sendAjax('editBank', 'POST', JSON.stringify(objBank), successEdit);
    }
    else if($("#worker").hasClass("active") === true) {
        var fio = $("#fio").val();
        var position = $("#select-position").val();
        var phone = $("#phone").val();
        var idBank = $("#select-bank").val();
        var nameBank = $("#select-bank option:selected").text();
        closeModel();
        var objBank = {};
        objBank.id = idBank;
        objBank.name = nameBank;
        var objWorker = {}
        objWorker.id = id;
        objWorker.fio = fio;
        objWorker.position = position;
        objWorker.phone = phone;
        objWorker.bank = objBank;
        sendAjax('editWorker', 'POST', JSON.stringify(objWorker), successEdit);
    }
    else if($("#client").hasClass("active") === true) {
        var fio = $("#fio").val();
        var phoneNumber = $("#phoneNumber").val();
        var address = $("#address").val();
        var email = $("#email").val();
        var idBank = $("#select-bank").val();
        var nameBank = $("#select-bank option:selected").text();
        closeModel();
        var objBank = {};
        objBank.id = idBank;
        objBank.name = nameBank;
        var objClient = {}
        objClient.id = id;
        objClient.fio = fio;
        objClient.phoneNumber = phoneNumber;
        objClient.address = address;
        objClient.email = email;
        objClient.bank = objBank;
        sendAjax('editClient', 'POST', JSON.stringify(objClient), successEdit);
    }
    else if($("#account").hasClass("active") === true) {
        var login = $("#login").val();
        var password = $("#password").val();
        var currency = $("#currency").val();
        var idClient = $("#select-client").val();
        closeModel();
        sendAjax('getClient', 'POST', JSON.stringify(idClient), function (data) {
            var objBankAccount = {};
            objBankAccount.id = id;
            objBankAccount.login = login;
            objBankAccount.password = password;
            objBankAccount.currency = currency;
            var objClient = data;
            objBankAccount.client = objClient;
            sendAjax('editBankAccount', 'POST', JSON.stringify(objBankAccount), successEdit);
        });
    }
}

function successEdit(data) {

    var table = $('#result_table_id').DataTable();
    var rows = table.row( '.selectedToEdit' );
    var row = $('#result_table_id tbody').children('.selectedToEdit')

    console.log(row);

    //var row =  $('#tr'+data.id+'');
    //var table = $('#result_table_id').DataTable();
    if($("#bank").hasClass("active") === true) {
        // row.children().eq(2).text(data.name);
        // row.data({
        //         //     "name": data.name
        //         // }).draw();
         rows.data(data).draw();
    }
    else if($("#worker").hasClass("active") === true) {
        row.children().eq(2).text(data.fio);
        row.children().eq(3).text(data.position);
        row.children().eq(3).attr("id",data.positionObjName);
        row.children().eq(4).text(data.phone);
        row.children().eq(5).attr("id",data.bankId)
        row.children().eq(5).text(data.bankName);
    }
    else if($("#client").hasClass("active") === true) {
        row.children().eq(2).text(data.fio);
        row.children().eq(3).text(data.phoneNumber);
        row.children().eq(4).text(data.address);
        row.children().eq(5).text(data.email);
        row.children().eq(6).attr("id",data.bankId)
        row.children().eq(6).text(data.bankName);
    }
    else if($("#account").hasClass("active") === true) {
        row.children().eq(2).text(data.login);
        row.children().eq(3).text(data.password);
        row.children().eq(4).text(data.currency);
        row.children().eq(5).attr("id",data.clientId)
        row.children().eq(5).text(data.clientFIO);
    }
    row.removeClass('selectedToDelete');
    row.removeClass('selectedToEdit');
    //$('#customCheck'+data.id+'').prop("checked",false);
    console.log("Edit");
}
//-----------------------------------------------//
//--------------------Списки---------------------//
function successlistPosition(data) {
    var option = "";
    if ($('.buttonEdit').hasClass("active") === true && $('#worker').hasClass("active") === true) {
        var tr = $('.selectedToEdit').children();
        var positionId = $(tr, this).eq(3).attr("id");
    }
    $.each(data, function( key, value ) {
        if ($('.buttonEdit').hasClass("active") === true && $('#worker').hasClass("active") === true && key === positionId){
            option = "<option selected value="+key+">"+value+"</option>";
        }else
            option = "<option value="+key+">"+value+"</option>";
        $('#select-position').append(option);
    });
}

function successlistBank(data) {
    var option = "";
    //if ($('.buttonEdit').hasClass("active") === true && ($('#worker').hasClass("active") === true || $('#client').hasClass("active") === true)) {
    if ($('.buttonEdit').hasClass("active") === true){
        var tr = $('.selectedToEdit').children();
        if($('#worker').hasClass("active") === true) {
            var bankId = $(tr, this).eq(5).attr("id");
            var bankName = $(tr, this).eq(5).text();
        }
        else if ($('#client').hasClass("active") === true){
            var bankId = $(tr, this).eq(6).attr("id");
            var bankName = $(tr, this).eq(6).text();
        }
    }
    $.each(data, function( key, value ) {
        if ($('.buttonEdit').hasClass("active") === true && (($('#worker').hasClass("active") === true) || ($('#client').hasClass("active") === true)) && value.id === bankId) {
            option = "<option selected value=" + value.id + ">" + value.name + "</option>";
        }else
            option = "<option value="+value.id+">"+value.name+"</option>";
        $('#select-bank').append(option);
    });
}

function successlistClient(data) {
    var option = "";
    if ($('.buttonEdit').hasClass("active") === true && $('#account').hasClass("active") === true) {
        var tr = $('.selectedToEdit').children();
        var clientId = $(tr, this).eq(5).attr("id");
    }
    $.each(data, function( key, value ) {
        if ($('.buttonEdit').hasClass("active") === true && $('#account').hasClass("active") === true && value.id === clientId){
            option = "<option selected value="+value.id+">"+value.fio+"</option>";
        }else
            option = "<option value="+value.id+">"+value.fio+"</option>";
        $('#select-client').append(option);
    });
}
//-----------------------------------------------//
//--------------------Листнеры-------------------//
function addBodyListeners(){
    $('#result_table_id tbody').on('click','tr',function () {
        var checkbox =  $(this).children().children().prop("checked");
        console.log(checkbox);
        if(checkbox === false){
            $(this).children().children().prop('checked', false);
            $(this).removeClass('selectedToDelete');
            $(this).removeClass('selectedToEdit');
        }
        else {
            $(this).children().children().prop('checked', true);
            $(this).addClass('selectedToDelete');
            $(this).addClass('selectedToEdit');
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
$('#bank').on('click',function () {
    $('.vertical-menu a').removeClass("active");
    $('#bank').addClass("active");
    sendAjax('loadBank', 'GET', "", successLoad);
});
$('#worker').on('click',function () {
    $('.vertical-menu a').removeClass("active");
    $('#worker').addClass("active");
    sendAjax('loadWorker', 'GET', "", successLoad);
});
$('#client').on('click',function () {
    $('.vertical-menu a').removeClass("active");
    $('#client').addClass("active");
    sendAjax('loadClient', 'GET', "", successLoad);
});
$('#account').on('click',function () {
    $('.vertical-menu a').removeClass("active");
    $('#account').addClass("active");
    sendAjax('loadBankAccount', 'GET', "", successLoad);
});

$('.buttonAdd').on('click',function () {
    $('.buttonAdd').addClass("active");
    addContent();
});

$('.buttonEdit').on('click',function () {
    $('.buttonEdit').addClass("active");
    addContent();
});
//-----------------------------------------------//

//----------------Модальное окно-----------------//
function addContent() {
    var modelContent = "\t<div class=\"modal fade\" id=\"exampleModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n" +
        "\t\t<div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\n" +
        "\t\t\t<div class=\"modal-content\">\n" +
        "\t\t\t\t<div class=\"modal-header\">\n" +
        "\t\t\t\t\t<h5 class=\"modal-title\" id=\"exampleModalLabel\"></h5>\n" +
        "\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" onclick=closeModel()>\n" +
        "\t\t\t\t\t\t<span aria-hidden=\"true\">&times;</span>\n" +
        "\t\t\t\t\t</button>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t<div class=\"modal-body\">\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t<div class=\"modal-footer\">\n" +
        "\t\t\t\t\t<button type=\"button\" id=\"saveModified\" class=\"btn btn-success\" data-dismiss=\"modal\"></button>\n" +
        "\t\t\t\t\t<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\" onclick=closeModel()>Закрыть</button>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t</div>\n" +
        "\t\t</div>\n" +
        "\t</div>"
    $('.addContent').append(modelContent);

    $('#saveModified').on('click',function () {
        if ($('.buttonEdit').hasClass("active") === true){
            doEdit();
        } else if($('.buttonAdd').hasClass("active") === true) {
            doAdd();
        }
    });
    var input = "";
    var nameModal = "";
    var nameButton = "";

    if ($('.buttonEdit').hasClass("active") === true){
        console.log("Изменить");
        var tr = $('.selectedToEdit').children();
        nameModal = "Изменить";
        nameButton = "Сохранить изменения";
        $('#exampleModalLabel').text(nameModal);
        $('#saveModified').text(nameButton);
        if($("#bank").hasClass("active") === true) {
            var id = $(tr, this).eq(1).text();
            var name = $(tr, this).eq(2).text();

            input += "<label for=\"name_bank\">Название банка</label>" +
                "<input class=\"form-control\" id=\"name_bank\" name=\"bank\" value='"+name+"' type=\"text\" maxlength=\"50\"/>";
        }
        else if($("#worker").hasClass("active") === true) {
            var id = $(tr, this).eq(1).text();
            var fio = $(tr, this).eq(2).text();
            var phone = $(tr, this).eq(4).text();

            input += "<label for=\"fio\">ФИО</label>" +
                "<input  class=\"form-control\"  id=\"fio\" name=\"fio\" value='"+fio+"' type=\"text\" placeholder=\"ФИО\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"select-position\">Должность</label>" +
                "<select class=\"custom-select\" id=\"select-position\"></select>";
            $('.modal-body').append(input);
            sendAjax('listPosition', 'GET', "", successlistPosition);
            input = "<label class='label-modal' for=\"phone\">Телефон</label>" +
                "<input type=\"tel\" name=\"phone\" id=\"phone\" class=\"form-control\" value='"+phone+"' placeholder=\"+375293333333\" maxlength=\"13\">";
            input += "<label class='label-modal' for=\"select-bank\">Банк</label>" +
                "<select class=\"custom-select\" id=\"select-bank\"></select>";
            $('.modal-body').append(input);
            sendAjax('loadBank', 'GET', "", successlistBank);
            input = "";
        }
        else if($("#client").hasClass("active") == true) {
            var id = $(tr, this).eq(1).text();
            var fio = $(tr, this).eq(2).text();
            var phoneNumber = $(tr, this).eq(3).text();
            var address = $(tr, this).eq(4).text();
            var email = $(tr, this).eq(5).text();

            input = "<label for=\"fio\">ФИО</label>" +
                "<input  class=\"form-control\"  id=\"fio\" name=\"fio\" value='"+fio+"' type=\"text\" placeholder=\"ФИО\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"phoneNumber\">Телефон</label>" +
                "<input type=\"tel\" name=\"phoneNumber\" id=\"phoneNumber\" value='"+phoneNumber+"' class=\"form-control\" placeholder=\"+375293333333\" maxlength=\"13\">";
            input += "<label class='label-modal' for=\"address\">Адрес</label>" +
                "<input  class=\"form-control\"  id=\"address\" name=\"address\" value='"+address+"' type=\"text\" placeholder=\"Адрес\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"email\">Email</label>" +
                "<input  class=\"form-control\"  id=\"email\" name=\"email\" value='"+email+"' type=\"email\" placeholder=\"test@mail.ru\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"select-bank\">Банк</label>" +
                "<select class=\"custom-select\" id=\"select-bank\"></select>";
            $('.modal-body').append(input);
            sendAjax('loadBank', 'GET', "", successlistBank);
            input = "";
        }
        else if($("#account").hasClass("active") === true) {
            var id = $(tr, this).eq(1).text();
            var login = $(tr, this).eq(2).text();
            var password = $(tr, this).eq(3).text();
            var currency = $(tr, this).eq(4).text();

            input = "<label for=\"login\">Логин</label>" +
                "<input  class=\"form-control\"  id=\"login\" name=\"login\" value='"+login+"' type=\"text\" placeholder=\"\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"password\">Пароль</label>" +
                "<input  class=\"form-control\"  id=\"password\" name=\"password\" value='"+password+"' type=\"text\" placeholder=\"\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"currency\">Кол-во денежных средств</label>" +
                "<input  class=\"form-control\"  id=\"currency\" name=\"currency\" value='"+currency+"' type=\"number\" placeholder=\"\" maxlength=\"20\"/>";
            input += "<label class='label-modal' for=\"select-client\">Клиент</label>" +
                "<select class=\"custom-select\" id=\"select-client\"></select>";
            $('.modal-body').append(input);
            sendAjax('loadClient', 'GET', "", successlistClient);
            input = "";
        }
    } else if($('.buttonAdd').hasClass("active") === true || $('.buttonSearch').hasClass("active") === true) {
        if($('.buttonAdd').hasClass("active") === true){
            console.log("Добавить");
            nameModal = "Добавление";
            nameButton = "Добавить";
        }else if ($('.buttonSearch').hasClass("active") === true){
            console.log("Поиск");
            nameModal = "Поиск";
            nameButton = "Найти";
        }
        if($("#bank").hasClass("active") === true) {
            //var name = "";
            input += "<label for=\"name_bank\">Название банка</label>" +
                "<input class=\"form-control\" id=\"name_bank\" name=\"bank\" value='' type=\"text\" maxlength=\"50\"/>";
        }
        else if($("#worker").hasClass("active") === true) {
            input += "<label for=\"fio\">ФИО</label>" +
                "<input  class=\"form-control\"  id=\"fio\" name=\"fio\" value='' type=\"text\" placeholder=\"ФИО\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"select-position\">Должность</label>" +
                "<select class=\"custom-select\" id=\"select-position\"></select>";
            $('.modal-body').append(input);
            sendAjax('listPosition', 'GET', "", successlistPosition);
            input = "<label class='label-modal' for=\"phone\">Телефон</label>" +
                "<input type=\"tel\" name=\"phone\" id=\"phone\" class=\"form-control\" placeholder=\"+375293333333\" maxlength=\"13\">";
            input += "<label class='label-modal' for=\"select-bank\">Банк</label>" +
                "<select class=\"custom-select\" id=\"select-bank\"></select>";
            $('.modal-body').append(input);
            sendAjax('loadBank', 'GET', "", successlistBank);
            input = "";
        }
        else if($("#client").hasClass("active") === true) {
            input = "<label for=\"fio\">ФИО</label>" +
                "<input  class=\"form-control\"  id=\"fio\" name=\"fio\" value='' type=\"text\" placeholder=\"ФИО\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"phoneNumber\">Телефон</label>" +
                "<input type=\"tel\" name=\"phoneNumber\" id=\"phoneNumber\" class=\"form-control\" placeholder=\"+375293333333\" maxlength=\"13\">";
            input += "<label class='label-modal' for=\"address\">Адрес</label>" +
                "<input  class=\"form-control\"  id=\"address\" name=\"address\" value='' type=\"text\" placeholder=\"Адрес\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"email\">Email</label>" +
                "<input  class=\"form-control\"  id=\"email\" name=\"email\" value='' type=\"email\" placeholder=\"test@mail.ru\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"select-bank\">Банк</label>" +
                "<select class=\"custom-select\" id=\"select-bank\"></select>";
            $('.modal-body').append(input);
            sendAjax('loadBank', 'GET', "", successlistBank);
            input = "";
        }
        else if($("#account").hasClass("active") === true) {
            input = "<label for=\"login\">Логин</label>" +
                "<input  class=\"form-control\"  id=\"login\" name=\"login\" value='' type=\"text\" placeholder=\"\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"password\">Пароль</label>" +
                "<input  class=\"form-control\"  id=\"password\" name=\"password\" value='' type=\"text\" placeholder=\"\" maxlength=\"50\"/>";
            input += "<label class='label-modal' for=\"currency\">Кол-во денежных средств</label>" +
                "<input  class=\"form-control\"  id=\"currency\" name=\"currency\" value='' type=\"number\" placeholder=\"\" maxlength=\"20\"/>";
            input += "<label class='label-modal' for=\"select-client\">Клиент</label>" +
                "<select class=\"custom-select\" id=\"select-client\"></select>";
            $('.modal-body').append(input);
            sendAjax('loadClient', 'GET', "", successlistClient);
            input = "";
        }
    }
    $('#exampleModalLabel').text(nameModal);
    $('#saveModified').text(nameButton);
    $('.modal-body').append(input);

    console.log("Load Modal");
}

function closeModel() {
    $('.addContent').empty();
    $('.buttonEdit').removeClass("active");
    $('.buttonAdd').removeClass("active");
    console.log("Close Modal");
}
//-----------------------------------------------//