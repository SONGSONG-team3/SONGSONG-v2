<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    // 세션에서 로그인된 유저 정보를 확인
    Integer userNo = (Integer) session.getAttribute("userNo"); // 로그인된 유저 번호
    // 세션에 값이 없으면 임시로 로그인된 사용자 설정
    if (userNo == null) {
        userNo = 1;  // 임시 유저 번호 설정 (예: userNo = 1)
        session.setAttribute("userNo", userNo);
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page with Bootstrap</title>
    <!-- 부트스트랩 CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 네비바 스타일 */
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

        /* 장르 선택 버튼 스타일 */
        .genre-btn {
            background-color: #EDEDED;
            color: black;
            border: none;
            margin: 10px;
            padding: 20px 40px;
            border-radius: 20px;
            font-size: 1.2rem;
        }

        .genre-btn.selected {
            background-color: #74C1BA; /* 선택된 버튼 색상 */
            color: white;
        }

        /* 카드 스타일 */
        .card-body {
            background-color: #EDEDED;
            padding: 20px;
            text-align: center;
        }

        .card img {
            border-radius: 50%;
            width: 100px;
            height: 100px;
            object-fit: cover;
        }

        .no-image {
            background-color: #43486E;
            border-radius: 50%;
            width: 100px;
            height: 100px;
            display: inline-block;
        }

        .info-text {
            margin-top: 10px;
            font-size: 1rem;
        }

        /* 카드들을 감싸는 컨테이너 가운데 정렬 */
        .playlist-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start;
            gap: 20px;
        }

        /* 고정된 카드 크기 설정 */
        .card-container {
            width: 18%; /* 카드 크기를 고정 (1행에 5개 들어가도록) */
            margin-bottom: 20px; /* 카드 간격 */
        }

        .btn-primary:hover {
            background-color: #1E213B; /* 호버 시 더 진한 색 */
        }

        /* 네비바와 장르 버튼 사이 간격 추가 */
        .button-container {
            margin-top: 100px;
            max-width: calc(100% - 100px);
            text-align: center;

        }

        /* 플레이리스트 스타일 */
        .playlist {
            display: none;
        }

        .playlist.active {
            display: block;
        }
    </style>
</head>
<body>
<!-- 헤더 -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <img src="/assets/img/songsong_color.jpg" alt="logo" />
        </a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <% if (userNo  != null) { %>
                <!-- 로그인된 상태 -->
                <li class="nav-item">
                    <span class="nav-link">유저번호: <%= userNo %></span>
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
                <% } else { %>
                <!-- 로그인되지 않은 상태 -->
                <li class="nav-item">
                    <a class="nav-link" href="/pages/login">로그인</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pages/signup">회원가입</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>


<!-- 장르 선택 버튼들 -->
<div class="container button-container text-center">
    <div class="row justify-content-center">
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('1')">발라드</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('2')">힙합</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('3')">인디</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('4')">락/메탈</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('5')">트로트</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('6')">댄스</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('7')">R&D</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('8')">밴드</button>
        </div>
    </div>
</div>

<!-- 플레이리스트 카드 영역 -->
<div class="container playlist-container">
</div>
<!-- 부트스트랩 및 jQuery JS CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    // 장르 선택 시 버튼 스타일 변경
    document.querySelectorAll('.genre-btn').forEach(button => {
        button.addEventListener('click', function() {
            document.querySelectorAll('.genre-btn').forEach(btn => btn.classList.remove('selected'));
            this.classList.add('selected');
        });
    });

    // 카테고리별 플레이리스트 가져오기 (Ajax 요청)
    function showPlaylist(categoryId) {
        console.log("Selected categoryId:", categoryId);  // categoryId가 제대로 전달되는지 확인
        if (!categoryId || categoryId === 'false') {
            console.error("Invalid categoryId:", categoryId);
            return;
        }
        $.ajax({
            url: "/playlists/" + categoryId,
            method: "GET",
            data: {
                searchCategory: categoryId,
                userNo: <%= userNo %>,
                page: 0,
                size: 15
            },
            success: function (result) {
                console.log("AJAX 성공 응답:", result);  // 응답 확인
                var playlistContainer = document.querySelector('.playlist-container');
                playlistContainer.innerHTML = '';


                if (result.result === "SUCCESS") {

                        result.list.forEach(function (playlistDto) {
                            var userDto = result.userMap[playlistDto.userNo]; // userMap에서 userNo로 UserDto를 가져옴
                            var songCount = result.songCountMap[playlistDto.userNo];

                            console.log("PlaylistDto:", playlistDto);  // PlaylistDto 확인
                            console.log("UserNo:", playlistDto.userNo);  // userNo 확인
                            console.log("UserMap:", result.userMap);  // userMap 확인
                            console.log("UserDto:", userDto);  // userDto 확인

                            var cardHtml = '<div class="card-container">';
                            cardHtml += '<a href="/playlist/detail/' + playlistDto.playlistId + '">';
                            cardHtml += '<div class="card">';
                            cardHtml += '<div class="card-body">';
                            cardHtml += '<img src="' + (userDto.userImage ? userDto.userImage : '/assets/img/noProfile.png') + '" alt="User Image" />';
                            cardHtml += '<div class="info-text">닉네임: ' + userDto.userNickname + '</div>';
                            cardHtml += '<div class="info-text">곡 수: ' + songCount  + ' 좋아요: ' + userDto.userLike + '</div>';
                            cardHtml += '</div>';
                            cardHtml += '</div>';
                            cardHtml += '</a>';
                            cardHtml += '</div>';
                        playlistContainer.innerHTML += cardHtml;
                    });
                } else {
                    playlistContainer.innerHTML = "<p>플레이리스트를 불러올 수 없습니다.</p>";
                }
            },
            error: function (error) {
                console.error("플레이리스트 가져오기 오류: ", error);
                document.querySelector('.playlist-container').innerHTML = "<p>플레이리스트를 가져오는 중 오류가 발생했습니다.</p>";
            }
        });
    }
</script>


</body>
</html>
