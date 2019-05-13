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
    var bankId = "";
    //var bankName = "";
    if ($('.buttonEdit').hasClass("active") === true){
        var tr = $('.selectedToEdit').children();
        if($('#worker').hasClass("active") === true) {
            bankId = $(tr, this).eq(5).attr("id");
            //bankName = $(tr, this).eq(5).text();
        }
        else if ($('#client').hasClass("active") === true){
            bankId = $(tr, this).eq(6).attr("id");
            //bankName = $(tr, this).eq(6).text();
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