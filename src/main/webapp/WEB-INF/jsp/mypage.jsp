<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.songsong.music.user.dto.UserDto" %>
<%
    UserDto userDto = (UserDto) session.getAttribute("userDto");
    System.out.println(userDto);
%>
<%
    // 세션에서 로그인된 유저 정보를 확인
    String userName = (String) session.getAttribute("userName"); // 로그인된 유저 이름
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/alertify.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/alertify.min.css"/>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/themes/default.min.css"/>
    <!-- 스타일 시트 경로 수정 -->
    <link rel="stylesheet" href="/assets/css/mypage.css">
    <title>My Page</title>
</head>
<body>
<div class="container">
    <!-- 상단 네비게이션 -->
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
                        <a class="nav-link" href="/logout">로그아웃</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 마이페이지 컨텐츠 -->
    <div class="mypage-container">
        <div class="row">
            <!-- 프로필 이미지 -->
            <div class="col-md-3 mt-5 text-center">
<%--                <img src="/assets/img/noProfile.png" alt="프로필 이미지" class="rounded-circle profile-img">--%>
                   <img src="/assets/img/goomba.jpg" alt="프로필 이미지" class="rounded-circle profile-img">
<%-- <img src="/assets/img/user/<%=userDto.getUserProfileImage()%>" alt="프로필 이미지" class="rounded-circle profile-img">--%>
</div>
<!-- 정보 수정 섹션 -->
<div class="col-md-8">
<form>
    <div class="mb-3">
        <label for="email" class="form-label">이메일</label>
        <input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">비밀번호</label>
        <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요">
    </div>
    <div class="mb-3">
        <label for="nickname" class="form-label">닉네임</label>
        <input type="text" class="form-control" id="nickname" placeholder="닉네임을 입력하세요">
    </div>

    <!-- 장르 선택 버튼들 -->
    <div class="mt-5">
        <button type="button" class="btn genre-btn">발라드</button>
        <button type="button" class="btn genre-btn">댄스</button>
        <button type="button" class="btn genre-btn">힙합</button>
        <button type="button" class="btn genre-btn">R&B</button>
        <button type="button" class="btn genre-btn">밴드</button>
        <button type="button" class="btn genre-btn">인디</button>
        <button type="button" class="btn genre-btn">락/메탈</button>
        <button type="button" class="btn genre-btn">트로트</button>
    </div>

    <!-- 회원정보 수정 버튼 -->
    <button type="submit" class="btn btn-primary mt-5">회원정보 수정</button>
</form>
</div>
</div>
</div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.querySelectorAll('.genre-btn').forEach(button => {
button.addEventListener('click', function() {
this.classList.toggle('selected');
});
});
</script>
</body>
</html>
