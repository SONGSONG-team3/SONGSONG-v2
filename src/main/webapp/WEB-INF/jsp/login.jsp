<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/alertify.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/alertify.min.css"/>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/themes/default.min.css"/>
    <title>Test</title>
    <style>
        body, html {
            height: 100%;
        }
        .center-content {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100%;
        }
        .form-control {
            width: 300px;
        }
        #btnLogin {
            width: 300px;
            background-color: #43486e;
            border-color: #43486e;
        }
        #btnLogin:hover {
            background-color: #3a4064;
            border-color: #3a4064;
        }
    </style>
</head>
<body>

<div class="container center-content text-center">
    <div class="mb-3">
        <img src="/assets/img/songsong.jpg" alt="logo" width="180" height="100" />
    </div>

    <form novalidate>
        <div class="mb-3">
            <input type="text" class="form-control" id="userEmail" placeholder="email" name="userEmail">
        </div>
        <div class="mb-3">
            <input type="password" class="form-control" id="userPassword" placeholder="password" name="userPassword">
        </div>
    </form>

    <div>
        <button id="btnLogin" class="btn btn-primary">로그인</button>
    </div>
</div>

</body>
</html>
