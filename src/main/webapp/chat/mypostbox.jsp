<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    </div>
    <div class="row">
        <div class="col mt-3 text-end">
            <button id="submitButton" type="button" class="w-25 btn btn-outline-warning btn-lg">삭제</button>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</html>