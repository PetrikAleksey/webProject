<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--------------------Модальное окно Работника-----------------------%>
<div class="modal fade" id="exampleModal" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Добавить нового работника</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <label for="fio">ФИО</label>
                <input  class="form-control"  id="fio" name="fio" value='' type="text" placeholder="ФИО" maxlength="50"/>

                <label class="label-modal" for="select-position">Должность</label>
                <select class="custom-select" id="select-position"></select>

                <label class="label-modal" for="phone">Телефон</label>
                <input type="tel" name="phone" id="phone" class="form-control" placeholder="+375293333333" maxlength="13">

                <label class="label-modal" for="select-bank">Банк</label>
                <select class="custom-select" id="select-bank"></select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info">Добавить</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>
