<%@ page import="java.util.List" %>
<%@ page import="toyproject.annonymouschat.chat.model.Chat" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>편지함</title>

    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/jumbotron-narrow.css">
</head>
<body>
<div class="container">
    <div class="header">
        <ul class="nav nav-pills pull-right">
            <li><a href="/">ToyProject</a></li>
        </ul>
        <a href="/"><h3 class="text-muted">RandomChat</h3></a>
    </div>
    <div class="jumbotron">
        <h1>RandomChat</h1>
        <p class="lead">편지함</p>
        <p class="lead"><%=request.getRequestURI()%></p>
        <div id="carouselExampleControls" class="carousel carousel-dark slide carousel-fade">
            <div class="carousel-inner">
                <c:forEach var="chat" items='<%=request.getAttribute("findChats")%>' varStatus="status" end='<%=((ArrayList<Chat>)request.getAttribute("findChats")).size()%>'>
                    <div class="carousel-item">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${chat.id}</h5>
                                <p class="card-text">${chat.content}</p>
                            </div>
                            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Previous</span>
                            </button>
                            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="visually-hidden">Next</span>
                            </button>
                        </div>
                        <div class="row">
                            <div class="col mt-3 text-end">
                                <ol class="breadcrumb float-start">
                                    <li class="breadcrumb-item"><a href="#">${status.count}</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">${status.end}</li>
                                </ol>
                                <button onclick="deleteChat(this.id)" id="deleteButton_${chat.id}" type="button" class="w-25 btn btn-outline-warning btn-lg">삭제</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <script>
                    document.getElementsByClassName('carousel-item')[0].className += ' active';
                </script>
            </div>

        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
    function deleteChat(btnId) {
        const deleteDto = {
            'id' : btnId.split('_')[1]
        }

        $.ajax({
            type: 'post',
            url: '/chat/post/delete',
            headers: {'Content-Type': 'application/json'},
            data: JSON.stringify(deleteDto),
            success: function (result) {
                const parsed = JSON.parse(result);
                if (parsed.ok) {
                    alert(parsed.message);
                    window.location.href = parsed.redirect;
                }
            }
        })
    }
</script>
</html>