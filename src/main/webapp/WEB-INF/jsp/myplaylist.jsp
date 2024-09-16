<%@ page import="com.songsong.music.music.dto.MusicDto" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.songsong.music.user.dto.UserDto" %>
<%
    // 세션에서 userDto를 가져옴
    UserDto userDto = (UserDto) session.getAttribute("userDto");

    // 사용자 정보가 있을 경우 유저 이름을 설정
    String userName = (userDto != null) ? userDto.getUserName() : null;
    int userNo = (userDto != null) ? userDto.getUserNo() : 0;  // userNo가 필요한 경우 0으로 초기화
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My 피드</title>
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
        .profile-container {
            display: flex;
            padding: 20px;
        }

        .profile-info {
            margin-top: 10px;
            display: flex;
            align-items: center;
            padding-left: 30px;
            background-color: #fff; /* 배경색 (선택 사항) */
        }

        .profile-icon {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-right: 20px;
        }

        .profile-details h2 {
            margin: 0;
            font-size: 1.5em;
        }
        .profile-details{
            margin-left: 30px;
        }

        .profile-details p {
            margin: 5px 0;
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

    <!--추가 버튼-->
    <style>
        /* + 버튼 스타일 */
        .add-song-button-container {
            text-align: center; /* 버튼을 가운데 정렬 */
            margin-top: 100px;
            margin-bottom: 10px;
        }

        .add-song-button {
            background-color: #74C1BA;
            color: #fff;
            border: none;
            width: 700px;
            padding: 10px 10px;
            border-radius: 50px;
            cursor: pointer;
            font-size: 15px;
        }

        .add-song-button:hover {
            background-color: #5CFFD1;
        }

        /* 폼 스타일 */
        .add-song-container {
            margin-top: 20px;
            text-align: center; /* 폼을 가운데 정렬 */
        }

        .add-song-form {
            display: none; /* 기본적으로 폼을 숨김 */
            margin: 0 auto; /* 자동 마진으로 수평 가운데 정렬 */
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #f9f9f9;
            max-width: 800px; /* 폼 너비 설정 */
        }

        .form-row {
            display: flex;
            justify-content: space-between; /* 필드를 공간에 맞게 정렬 */
            gap: 20px; /* 필드 간의 간격 조정 */
            flex-wrap: wrap; /* 화면 크기에 따라 자동 줄 바꿈 */
        }

        .form-group {
            flex: 1;
            min-width: 200px; /* 필드의 최소 너비 설정 */
        }

        .form-group label {
            display: block;
            margin-bottom: 10px; /* 레이블과 입력 필드 사이의 간격 조정 */
            font-weight: bold;
        }

        .form-group input {
            display: block;
            padding: 10px;
            width: 90%;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .form-actions {
            margin-top: 10px;
        }

        .form-actions button {
            margin-right: 10px;
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-actions button[type="submit"] {
            background-color: #007bff;
            color: #fff;
        }

        .form-actions button[type="submit"]:hover {
            background-color: #0056b3;
        }

        .form-actions button[type="button"] {
            background-color: #6c757d;
            color: #fff;
        }

        .form-actions button[type="button"]:hover {
            background-color: #5a6268;
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
            <img src="/assets/img/goomba.jpg" class="profile-icon" alt="Profile Image">
            <div class="profile-details">
                <h2><span>${nickName}</span></h2>
                <p>🎵<span>${songCount}</span> ❤️<span>${likeCount}</span></p>
                <p><span>${categories}</span></p>
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
            <th>삭제</th>
        </tr>
        </thead>
        <tbody>
        <%
            Map<Integer, MusicDto> musicMap = (Map<Integer, MusicDto>) request.getAttribute("musicInfo");
            if (musicMap != null) {
                for (Map.Entry<Integer, MusicDto> entry : musicMap.entrySet()) {
                    MusicDto music = entry.getValue();
        %>
        <tr>
            <td><%= music.getMusicName() %></td>
            <td><%= music.getMusicArtist() %></td>
            <td><a href="<%= music.getMusicLink() %>">링크</a></td>
            <td><button class="action-button" data-music-id="<%= music.getMusicId() %>" data-user-no="<%= userDto.getUserNo() %>">삭제</button></td>
        </tr>
        <%
            }
        }
        %>
        </tbody>
    </table>

    <!-- + 버튼 및 폼 -->
    <div class="add-song-container">
        <div class="add-song-button-container">
            <button class="add-song-button" onclick="toggleForm()">+</button>
        </div>

        <div class="add-song-form" id="addSongForm" style="display: none;">
            <h3>노래 추가</h3>
            <form id="songForm">
                <div class="form-row">
                    <div class="form-group">
                        <input type="text" id="songTitle" name="songTitle" placeholder="곡 제목을 입력하세요" required>
                    </div>
                    <div class="form-group">
                        <input type="text" id="artist" name="artist" placeholder="가수를 입력하세요" required>
                    </div>
                    <div class="form-group">
                        <input type="url" id="songLink" name="songLink" placeholder="곡 링크를 입력하세요" required>
                    </div>
                    <!-- hidden input for user_no -->
                    <input type="hidden" id="userNo" name="userNo" value="${userDto.userNo}">
                </div>
                <div class="form-actions">
                    <button type="submit">추가</button>
                    <button type="button" onclick="toggleForm()">취소</button>
                </div>
            </form>
        </div>
    </div>
</div>

    <!-- 추가 버튼 스크립트 -->
    <script>
        function toggleForm() {
            var form = document.getElementById("addSongForm");
            if (form.style.display === "none" || form.style.display === "") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }

        document.getElementById("songForm").addEventListener("submit", function(event) {
            event.preventDefault(); // 기본 제출 동작 방지

            let title = document.getElementById("songTitle").value;
            let artist = document.getElementById("artist").value;
            let link = document.getElementById("songLink").value;
            let userNo = document.getElementById("userNo").value;

            fetch('/pages/addSong', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    musicName: title,
                    musicArtist: artist,
                    musicLink: link,
                    userNo: userNo
                })
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert('노래가 추가되었습니다.');
                        window.location.reload(); // 노래가 추가된 후 페이지 새로고침
                    } else {
                        alert('노래 추가에 실패했습니다.');
                    }
                    document.getElementById("addSongForm").style.display = "none";
                    document.getElementById("songForm").reset();
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('서버 오류');
                });
        });
    </script>

    <!--삭제버튼 동작-->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            document.querySelectorAll('.action-button').forEach(function(button) {
                button.addEventListener('click', function() {
                    var userNo = this.getAttribute('data-user-no');
                    var musicId = this.getAttribute('data-music-id');

                    // 사용자에게 삭제 확인 요청
                    if (confirm('정말로 이 음악을 삭제하시겠습니까?')) {
                        fetch('/pages/deleteMusic', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded'
                            },
                            body: new URLSearchParams({
                                userNo: userNo,
                                musicId: musicId
                            })
                        })
                            .then(response => response.text())
                            .then(result => {
                                if (result === '삭제 성공') {
                                    alert('삭제되었습니다.');
                                    location.reload(); // 페이지 새로고침
                                } else {
                                    alert('삭제에 실패했습니다.');
                                }
                            })
                            .catch(error => {
                                console.error('삭제 요청 중 오류 발생:', error);
                            });
                    }
                });
            });
        });
    </script>
</body>
</html>