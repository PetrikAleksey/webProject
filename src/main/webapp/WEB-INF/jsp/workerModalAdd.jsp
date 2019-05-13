<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<%--------------------Модальное окно Работника-----------------------%>
<%--<div class="modal fade" id="exampleModal" >--%>
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
<%--</div>--%>

<!-- The Modal -->
<%--<div class="modal" id="myModal">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>

<%--            <!-- Modal Header -->--%>
<%--            <div class="modal-header">--%>
<%--                <h4 class="modal-title">Modal Heading</h4>--%>
<%--                <button type="button" class="close" data-dismiss="modal">&times;</button>--%>
<%--            </div>--%>

<%--            <!-- Modal body -->--%>
<%--            <div class="modal-body">--%>
<%--                Modal body..--%>
<%--            </div>--%>

<%--            <!-- Modal footer -->--%>
<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>--%>
<%--            </div>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
