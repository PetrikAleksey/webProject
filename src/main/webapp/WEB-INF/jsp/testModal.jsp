<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 14.05.2019
  Time: 0:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap - Демонстрация работы компонента modal</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        html {
            font-size: 16px !important;
            -ms-overflow-style: scrollbar !important;
            -webkit-tap-highlight-color: transparent !important;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif !important;
            font-size: 1rem !important;
            line-height: 1.5 !important;
            color: #373a3c !important;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="text-center">Bootstrap - Демонстрация работы компонента modal</h1>
    <hr>
</div>
<div class="container">
    <p>1. Активирование модального окна с помощью атрибутов data</p>
    <p><a href="#myModal1" class="btn btn-primary" data-toggle="modal">Открыть модальное окно 1</a></p>
    <div id="myModal1" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Заголовок модального окна 1</h4>
                </div>
                <div class="modal-body">
                    Содержимое модального окна 1...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary">Сохранить изменения</button>
                </div>
            </div>
        </div>
    </div>

    <p>2. Открытие модального окна с помощью JavaScript</p>
    <p><a href="#myModal2" id="btn2" class="btn btn-primary">Открыть модальное окно 2</a></p>
    <div id="myModal2" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Заголовок модального окна 2</h4>
                </div>
                <div class="modal-body">
                    Содержимое модального окна 2...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary">Сохранить изменения</button>
                </div>
            </div>
        </div>
    </div>


    <p>3. Изменение размеров модального окна.</p>
    <p>
        <button class="btn btn-primary" data-toggle="modal" data-target="#largeModal">Открыть ширикое модальное окно
        </button>
    </p>
    <p>
        <button class="btn btn-primary" data-toggle="modal" data-target="#smallModal">Открыть узкое модальное окно
        </button>
    </p>
    <div id="largeModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Заголовок модального окна</h4>
                </div>
                <div class="modal-body">
                    Содержимое модального окна...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary">Сохранить изменения</button>
                </div>
            </div>
        </div>
    </div>
    <div id="smallModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Заголовок модального окна</h4>
                </div>
                <div class="modal-body">
                    Содержимое модального окна...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary">Сохранить изменения</button>
                </div>
            </div>
        </div>
    </div>
    <hr>
</div>

<!-- jQuery -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script>
    $(function () {
        $("#btn2").click(function () {
            $("#myModal2").modal('show');
        });
    });
</script>
</body>
</html>
