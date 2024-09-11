<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // 세션에서 로그인된 유저 정보를 확인
    String userName = (String) session.getAttribute("userName"); // 로그인된 유저 이름
%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>다른 사용자 피드</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!--바디 부분-->
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
    </style>

    <!--nav바-->
    <style>
        .navbar {
            margin-bottom: 20px;
            background-color: #43486E;
        }
        .navbar-nav .nav-link {
            color: #fff !important;
        }
        .navbar-brand img {
            height: 40px;
        }
    </style>

    <!--프로필 css-->
    <style>
        /*프로필 css*/
        .profile-container {
            display: flex;
            padding: 30px 0px 20px 50px;
        }

        .profile-info {
            display: flex;
            align-items: center;
            flex: 1; /* 프로필 정보가 공간을 차지하도록 설정 */
            background-color: #fff; /* 배경색 (선택 사항) */
        }

        .profile-icon {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-right: 20px;
        }

        .profile-details {
            margin-left: 30px;
            flex: 1; /* 프로필 세부 정보가 공간을 차지하도록 설정 */
        }

        .profile-details h2 {
            margin: 0;
            font-size: 1.5em;
        }

        .profile-details p {
            margin: 5px 0;
        }

        .like-button-container {
            margin-left: auto; /* 버튼 컨테이너를 오른쪽으로 정렬 */
        }

        .like-button {
            background-color: #43486E;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }

        .like-button:hover {
            background-color: #007bff;
        }
    </style>

    <style>
        .main-container{
            width: 80%;
            margin-left: 10%;
            margin-top: 80px;
        }
    </style>

    <!--플레이리스트 테이블-->
    <style>
        /* 플레이리스트 테이블 스타일 */
        .playlist-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            table-layout: fixed; /* 열 너비 고정 */
            border: none;
        }

        .playlist-table th,
        .playlist-table td {
            padding: 8px;
            text-align: left;
            border:none;
        }

        .playlist-table th {
            background-color: #f4f4f4;
            font-weight: bold;
            border-top: 2px solid #ddd; /* 위 테두리 */
            border-bottom: 2px solid #ddd; /* 아래 테두리 */
            border-left: none; /* 좌 테두리 제거 */
            border-right: none; /* 우 테두리 제거 */
        }

        /* 열 너비 설정 */
        .playlist-table th:nth-child(1),
        .playlist-table td:nth-child(1) {
            width: 30%;
        }

        .playlist-table th:nth-child(2),
        .playlist-table td:nth-child(2) {
            width: 30%;
        }

        .playlist-table th:nth-child(3),
        .playlist-table td:nth-child(3) {
            width: 30%;
        }

        .playlist-table tr:hover {
            background-color: #f1f1f1;
        }

        .playlist-table a {
            color: #007bff;
            text-decoration: none;
        }
        .action-button {
            background-color: #43486E;
            color: white;
            border: none;
            padding: 6px 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }

        .action-button:hover {
            background-color: #c82333;
        }
    </style>

</head>

<body>
<!--nav바-->
<nav class="navbar navbar-expand-lg navbar-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <img src="/assets/img/songsong_color.jpg" alt="logo" />
        </a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <span class="nav-link"><%= userName %>님</span>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pages/myplaylist">My 플레이리스트</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pages/mypage">마이페이지</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">로그아웃</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

    <div class="main-container">
        <!--프로필-->
        <div class="profile-container">
            <div class="profile-info">
                <img src="assets/img/noProfile.png" class="profile-icon">
                <div class="profile-details">
                    <h2>이름</h2>
                    <p>곡 수:</p>
                    <p>좋아요 수:</p>
                    <p>카테고리:</p>
                </div>
                <div class="like-button-container"> <!-- 좋아요 버튼을 감싸는 컨테이너 추가 -->
                    <button class="like-button" id="likeButton">좋아요</button>
                </div>
            </div>
        </div>

        <hr>

        <!--플레이리스트-->
        <table class="playlist-table">
            <thead>
            <tr>
                <th>곡 제목</th>
                <th>가수</th>
                <th>링크</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>곡 제목 1</td>
                <td>가수 1</td>
                <td><a href="링크1" target="_blank">링크</a></td>
                <td><button class="action-button">추가</button></td>
            </tr>
            <tr>
                <td>곡 제목 2</td>
                <td>가수 2</td>
                <td><a href="링크2" target="_blank">링크</a></td>
                <td><button class="action-button">추가</button></td>
            </tr>
            <!-- 추가 곡들 -->
            </tbody>

        </table>



    </div>
</body>

<!-- JavaScript -->
<script>
    document.getElementById('likeButton').addEventListener('click', function() {
        var button = this;
        if (button.classList.contains('liked')) {
            button.textContent = '좋아요';
            button.classList.remove('liked');
        } else {
            button.textContent = '좋아요 취소';
            button.classList.add('liked');
        }
    });
</script>
</html>
