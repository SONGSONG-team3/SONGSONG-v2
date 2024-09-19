<%@ page import="com.songsong.music.user.dto.UserDto" %>
<%@ page import="com.songsong.music.playlist.dto.PlaylistDto" %>
<%@ page import="com.songsong.music.music.dto.MusicDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
    <title>${otherUserName} 피드</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

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

        .profile-container {
            display: flex;
            padding: 30px 0px 20px 50px;
        }

        .profile-info {
            display: flex;
            align-items: center;
            flex: 1;
            background-color: #fff;
        }

        .profile-icon {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-right: 20px;
        }

        .profile-details {
            margin-left: 30px;
            flex: 1;
        }

        .profile-details h2 {
            margin: 0;
            font-size: 1.5em;
        }

        .profile-details p {
            margin: 5px 0;
        }

        .like-button-container {
            margin-left: auto;
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

        .main-container {
            width: 80%;
            margin-left: 10%;
            margin-top: 80px;
        }

        .playlist-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            table-layout: fixed;
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
            border-top: 2px solid #ddd;
            border-bottom: 2px solid #ddd;
            border-left: none;
            border-right: none;
        }

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
                    <span class="nav-link"><%= userDto.getUserName() %>님</span>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pages/myplaylist">My 플레이리스트</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pages/mypage">마이페이지</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/auth/logout">로그아웃</a>
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
                <h2><span>${otherUserName}</span></h2>
                <p>🎵<span>${songCount}</span> ❤️<span id="likeCount">${likeCount}</span></p>
                <p><span>${categories}</span></p>
            </div>
            <div class="like-button-container">
                <button id="likeButton" class="like-button" data-user-to="${userDto.userNo}" data-status="${isLiked ? 1 : 0}">
                    ${isLiked ? "좋아요 취소" : "좋아요"}
                </button>
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
        <% for (PlaylistDto playlist : (List<PlaylistDto>)request.getAttribute("playlists")) { %>
        <% MusicDto music = (MusicDto) ((Map<Integer, MusicDto>)request.getAttribute("musicInfo")).get(playlist.getMusicId()); %>
        <tr>
            <td><%= music != null ? music.getMusicName() : "Unknown" %></td>
            <td><%= music != null ? music.getMusicArtist() : "Unknown" %></td>
            <td><a href="<%= music != null ? music.getMusicLink() : "#" %>" target="_blank">링크</a></td>
            <td><button class="action-button add-button" data-music-id="<%= playlist.getMusicId() %>">추가</button></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<!-- 상대 음악 추가 -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const addButtons = document.querySelectorAll('.add-button');

        addButtons.forEach(button => {
            button.addEventListener('click', function() {
                const musicId = this.getAttribute('data-music-id');
                const userNo = <%= userNo %>;

                // 서버로 POST 요청을 보낼 데이터 객체 생성
                const data = {
                    userNo: userNo,
                    musicId: musicId
                };

                fetch('/pages/addToPlaylist', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                    .then(response => response.json())
                    .then(result => {
                        if (result.success) {
                            alert('음악이 플레이리스트에 추가되었습니다.');
                        } else {
                            alert('해당 음악은 이미 플레이리스트에 있습니다.');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('서버와의 통신 중 문제가 발생했습니다.');
                    });
            });
        });
    });
</script>


<!-- 좋아요 버튼 -->
<script>
    document.querySelector('#likeButton').addEventListener('click', function () {
        const userTo = this.getAttribute('data-user-to');
        const userFrom = <%= userNo %>;
        const currentStatus = parseInt(this.getAttribute('data-status'), 10);
        const newStatus = currentStatus === 1 ? 0 : 1;

        fetch('/pages/like', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userFrom: userFrom, userTo: userTo, status: newStatus })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    this.setAttribute('data-status', newStatus);
                    this.textContent = newStatus === 1 ? '좋아요 취소' : '좋아요';

                    // 좋아요 수 업데이트
                    document.querySelector('#likeCount').textContent = data.likeCount;
                } else {
                    alert('문제가 발생했습니다.');
                }
            })
            .catch(error => console.error('오류 발생:', error));
    });
</script>
</body>
</html>
