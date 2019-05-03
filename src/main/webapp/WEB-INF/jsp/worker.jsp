<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="headConfig.jsp" />
    <title>CRUD Bank</title>
    <script type="text/javascript">

        function doAddBank() {

            var newNameBank = $("#name_bank").val();
            $('.addContent').empty();

            $.ajax({
                url : 'addBank',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data : ({
                    text: newNameBank
                }),
                success: function (data) {
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

                    $('#customCheck'+data.id+'').on('click',function () {
                        if($('#customCheck'+data.id+'').prop("checked") == false){
                            $('#tr'+data.id+'').removeClass('selectedToDelete');
                            $('#tr' + data.id + '').removeClass('selectedToEdit');
                        }
                        else {
                            $('#tr' + data.id + '').addClass('selectedToDelete');
                            $('#tr' + data.id + '').addClass('selectedToEdit');
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
                    console.log("Save Bank");
                }
            });
        }

        function doAllSelectedBankDelete() {

            var massBanks = []
            var allToDelete = $('.selectedToDelete');
            for(i = 0; i < allToDelete.length;i++){
                var bank = {};
                bank.id = $(allToDelete[i],this).children().eq(1).text();
                bank.name = $(allToDelete[i],this).children().eq(2).text();
                massBanks.push(bank);
            }

            $.ajax({
                url : 'deleteBankAll',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                //data: JSON.stringify(json),
                data: JSON.stringify(massBanks),
                success: function (data) {
                    $.each(data, function( key, value ) {
                        $("#tr"+String(value.id)).remove();
                    });
                    console.log("Delete Bank");
                }
            });
        }
        //Срабатывает после загрузки DOM(после загрузкм всей страницы)
        $( document ).ready(function(){

            $.ajax({
                url : 'loadBank',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
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

                        $('#customCheck'+value.id+'').on('click',function () {
                            if($('#customCheck'+value.id+'').prop("checked") == false){
                                $('#tr'+value.id+'').removeClass('selectedToDelete');
                                $('#tr'+value.id+'').removeClass('selectedToEdit');
                                //$('#customCheck'+value.id+'').prop("checked",false)
                            }
                            else {
                                $('#tr' + value.id + '').addClass('selectedToDelete');
                                $('#tr'+value.id+'').addClass('selectedToEdit');
                                //$('#customCheck'+value.id+'').prop("checked",true)
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
                    });
                    console.log("Load Bank");
                }
            });
        });

        function addContent(event) {
            if (event.data.action == "Изменить"){
                var tr = $('.selectedToEdit').children();
                var id = $(tr,this).eq(1).text();
                var name = $(tr,this).eq(2).text();
                var nameModal = "Изменение";
                //var onClick = "onclick=doEditBank("+id+")";
                var onClick = "onclick=doEditBank()";
                var nameButton = "Сохранить";
            }
            else{
                var name = "";
                var nameModal = "Добавление";
                var onClick = "onclick=doAddBank()";
                var nameButton = "Добавить";
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

        function doEditBank(id) {
            var tr = $('.selectedToEdit').children();
            var id = $(tr,this).eq(1).text();
            var newNameBank = $("#name_bank").val();
            $('.addContent').empty();
            var json = new Object();
            json.id = id;
            json.name = newNameBank;
            $.ajax({
                url : 'editBank',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(json),
                //data: JSON.stringify({id: id,name: newNameBank}),
                success: function (data) {
                    $('#tr'+data.id+'').children().eq(2).text(data.name);
                    $('#tr'+data.id+'').removeClass('selectedToDelete');
                    $('#tr' +data.id +'').removeClass('selectedToEdit');
                    $('#customCheck'+data.id+'').prop("checked",false)
                    console.log("Edit Bank");
                }
            });
        }
    </script>
</head>
<body>
<div id="topButton">
    <div id="logo">Рабочие</div>
    <input type="button" class="btn btn-secondary topButton buttonAdd" data-toggle='modal' data-target='#exampleModal' value="Добавить">
    <input type="button" disabled class="btn btn-secondary topButton buttonDelete" value="Удалить" onclick="doAllSelectedBankDelete()">
    <input type="button" disabled class="btn btn-secondary topButton buttonEdit" data-toggle='modal' data-target='#exampleModal' value="Изменить">
    <input type="search" class = "search" name="search" placeholder="Поиск">
</div>
<div id="leftButton">
    <div class="vertical-menu">
        <a href="bank" class="active">Банки</a>
        <a href="worker">Рабочие</a>
        <a href="#">Клиенты</a>
        <a href="#">Аккаунты</a>
    </div>
</div>
<div id="content">
    <table id = "result_table_id" class = "result_table">
        <thead>
        <tr>
            <td class="td-first">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="customCheck">
                    <label class="custom-control-label" for="customCheck"></label>
                </div>
            </td>
            <td class="td-next">ID</td>
            <td class="td-next">Название банка</td>
        </tr>
        </thead>
        <tbody id = "result_tbody_id" class = "result_tbody">
        </tbody>
    </table>
</div>
<!-- Модальное окно -->
<div class="addContent"></div>
</body>
<script>
    $('.buttonAdd').on('click',{action: "Добавить"} ,addContent);
    $('.buttonEdit').on('click',{action: "Изменить"} ,addContent);

    $('#customCheck').on('click',function () {

        var allIdToDelete = $('#result_table_id tbody tr td div input');

        if($('#customCheck').prop("checked") == false){
            $('#result_table_id tbody tr').removeClass("selectedToDelete");
            $('#result_table_id tbody tr').removeClass("selectedToEdit");
            $.each(allIdToDelete, function( key, value){
                $('#'+value.id).prop("checked",false);
            });
        }
        else {
            $('#result_table_id tbody tr').addClass("selectedToDelete");
            $('#result_table_id tbody tr').addClass("selectedToEdit");
            $.each(allIdToDelete, function( key, value){
                $('#'+value.id).prop("checked",true);
            });
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
    })
</script>
</html>