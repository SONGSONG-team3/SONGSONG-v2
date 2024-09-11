<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    // 세션에서 로그인된 유저 정보를 확인
    String userName = (String) session.getAttribute("userName"); // 로그인된 유저 이름
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
            padding: 20px 40px; /* 버튼 크기를 2배로 키움 */
            border-radius: 20px;
            font-size: 1.2rem; /* 텍스트 크기도 확대 */
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
            font-size: 1.1rem;
        }

        .count-likes {
            font-size: 1rem;
            color: #666;
        }

        /* 카드들을 감싸는 컨테이너 가운데 정렬 */
        .playlist-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-start; /* 왼쪽 정렬 */
            gap: 20px; /* 카드 간격 */
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
                <% if (userName != null) { %>
                <!-- 로그인된 상태 -->
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
            <button type="button" class="btn genre-btn" onclick="showPlaylist('ballad')">발라드</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('dance')">댄스</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('hiphop')">힙합</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('rnd')">R&B</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('band')">밴드</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('indie')">인디</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('rock')">락/메탈</button>
        </div>
        <div class="col-auto">
            <button type="button" class="btn genre-btn" onclick="showPlaylist('trot')">트로트</button>
        </div>
    </div>
</div>

<!-- 플레이리스트 카드 영역 -->
<div class="container playlist-container">
    <!-- 1행에 5개씩 카드 표시, 카드 컨테이너는 가운데 정렬 -->
    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <img src="user-image.jpg" alt="User Image" />
                <div class="info-text">닉네임: user123</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 10, 좋아요: 120</div>
            </div>
        </div>
    </div>

    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <div class="no-image"></div>
                <div class="info-text">닉네임: user456</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 8, 좋아요: 90</div>
            </div>
        </div>
    </div>

    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <img src="user-image2.jpg" alt="User Image" />
                <div class="info-text">닉네임: user789</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 5, 좋아요: 50</div>
            </div>
        </div>
    </div>

    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <div class="no-image"></div>
                <div class="info-text">닉네임: user001</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 12, 좋아요: 200</div>
            </div>
        </div>
    </div>

    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <img src="user-image3.jpg" alt="User Image" />
                <div class="info-text">닉네임: user002</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 9, 좋아요: 80</div>
            </div>
        </div>
    </div>

    <!-- 다음 줄에 6번째 카드가 표시 -->
    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <div class="no-image"></div>
                <div class="info-text">닉네임: user003</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 3, 좋아요: 40</div>
            </div>
        </div>
    </div>
    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <div class="no-image"></div>
                <div class="info-text">닉네임: user003</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 3, 좋아요: 40</div>
            </div>
        </div>
    </div>
    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <div class="no-image"></div>
                <div class="info-text">닉네임: user003</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 3, 좋아요: 40</div>
            </div>
        </div>
    </div>
    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <div class="no-image"></div>
                <div class="info-text">닉네임: user003</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 3, 좋아요: 40</div>
            </div>
        </div>
    </div>
    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <div class="no-image"></div>
                <div class="info-text">닉네임: user003</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 3, 좋아요: 40</div>
            </div>
        </div>
    </div>
    <div class="card-container">
        <div class="card">
            <div class="card-body">
                <div class="no-image"></div>
                <div class="info-text">닉네임: user003</div>
                <div class="info-text">카테고리: 발라드</div>
                <div class="count-likes">곡수: 3, 좋아요: 40</div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 JS CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.querySelectorAll('.genre-btn').forEach(button => {
        button.addEventListener('click', function() {
            document.querySelectorAll('.genre-btn').forEach(btn => btn.classList.remove('selected'));
            this.classList.add('selected');
        });
    });

    function showPlaylist(category) {
        var playlists = document.querySelectorAll('.playlist');
        playlists.forEach(function (playlist) {
            playlist.classList.remove('active');
        });
        document.getElementById(category).classList.add('active');
    }
</script>
</body>
</html>
