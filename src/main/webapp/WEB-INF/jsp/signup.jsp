<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/alertify.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/alertify.min.css"/>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/themes/default.min.css"/>
    <title>회원가입</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .content {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 40px;
            width: 100%;
            max-width: 1200px;
        }
        .rounded-circle {
            width: 250px;
            height: 250px;
            border: 2px solid #ccc;
            object-fit: cover;
            margin-top: 60px;
        }
        .form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .form-control {
            width: 300px;
        }
        #btnSignup {
            width: 300px;
            background-color: #43486e;
            border-color: #43486e;
        }
        #btnSignup:hover {
            background-color: #3a4064;
            border-color: #3a4064;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="content">
        <!-- 왼쪽 이미지 -->
        <img src="/assets/img/noProfile.png" alt="new-image" class="rounded-circle"/>

        <!-- 오른쪽 콘텐츠 -->
        <div class="form-container">
            <div class="mb-3">
                <img src="/assets/img/songsong.jpg" alt="logo" width="100" height="60" />
            </div>

            <form novalidate class="w-100 d-flex flex-column align-items-center">
                <div class="mb-3">
                    <input type="text" class="form-control" id="userName" placeholder="name" name="userName">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="userEmail" placeholder="email" name="userEmail">
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="userPassword" placeholder="password" name="userPassword">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="userNickname" placeholder="nickname" name="userNickname">
                </div>

                <div>
                    <button id="btnSignup" class="btn btn-primary">회원가입</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
