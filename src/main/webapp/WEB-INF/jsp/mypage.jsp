<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.songsong.music.user.dto.UserDto" %>
<%@ page import="java.util.List" %>
<%
    UserDto userDto = (UserDto) session.getAttribute("userDto");
    List<Integer> userCategoryIds = (List<Integer>) session.getAttribute("userCategoryIds"); // 사용자 카테고리 정보
    String userName = userDto != null ? userDto.getUserNickname() : "";
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/alertify.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/alertify.min.css"/>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.12.0/build/css/themes/default.min.css"/>
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
                        <span class="nav-link" id="navName"></span>
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
                <img src="/assets/img/goomba.jpg" alt="프로필 이미지" class="rounded-circle profile-img">
            </div>
            <!-- 정보 수정 섹션 -->
            <div class="col-md-8">
                <form id="userUpdateForm">
                    <div class="mb-3">
                        <label for="name" class="form-label">이름</label>
                        <input type="text" class="form-control" id="name" name="name" disabled>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">이메일</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요" disabled>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요">
                    </div>
                    <div class="mb-3">
                        <label for="nickname" class="form-label">닉네임</label>
                        <input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임을 입력하세요">
                    </div>

                    <div class="mt-5" id="genreButtons">
                        <button type="button" class="btn genre-btn" data-category-id="1">발라드</button>
                        <button type="button" class="btn genre-btn" data-category-id="2">힙합</button>
                        <button type="button" class="btn genre-btn" data-category-id="3">인디</button>
                        <button type="button" class="btn genre-btn" data-category-id="4">락/메탈</button>
                        <button type="button" class="btn genre-btn" data-category-id="5">트로트</button>
                        <button type="button" class="btn genre-btn" data-category-id="6">댄스</button>
                        <button type="button" class="btn genre-btn" data-category-id="7">R&B</button>
                        <button type="button" class="btn genre-btn" data-category-id="8">밴드</button>
                    </div>

                    <!-- 회원정보 수정 버튼 -->
                    <button type="button" class="btn btn-primary mt-5" id="updateUserBtn">회원정보 수정</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Updated script section -->
<script>
    window.onload = function () {
        detailMypage();
    }

    // 카테고리 버튼 초기화: 로그인한 사용자의 카테고리 ID에 맞는 버튼 선택 상태 유지
    const userCategoryIds = <%= userCategoryIds != null ? userCategoryIds.toString() : "[]" %>;
    document.querySelectorAll('.genre-btn').forEach(button => {
        const categoryId = parseInt(button.getAttribute('data-category-id'));
        if (userCategoryIds.includes(categoryId)) {
            button.classList.add('selected');
        }
    });

    // 장르 버튼 클릭 이벤트: 선택/해제 토글
    document.querySelectorAll('.genre-btn').forEach(button => {
        button.addEventListener('click', function() {
            this.classList.toggle('selected');
        });
    });

    // 회원정보 수정 버튼 클릭 이벤트
    document.getElementById('updateUserBtn').addEventListener('click', async function() {
        const userPassword = document.getElementById('password').value;
        const userNickname = document.getElementById('nickname').value;

        // 선택된 카테고리 아이디들
        const selectedCategories = Array.from(document.querySelectorAll('.genre-btn.selected')).map(button => parseInt(button.getAttribute('data-category-id')));

        // AJAX 요청: 회원정보 수정
        try {
            let response = await fetch('/pages/mypage/user-update', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ userPassword, userNickname })
            });
            let data = await response.json();

            console.log('User Update Response:', data); // Log response
            console.log('User Update Response:', data.result); // Log response

            if (data.result == "success") {
                alertify.success('회원정보가 수정되었습니다.');
            }else {
                alertify.error('회원정보 수정에 실패했습니다.');
            }
        } catch (error) {
            console.error('User Update Error:', error);
            alertify.error('회원정보 수정 중 오류가 발생했습니다.');
        }

        // AJAX 요청: 카테고리 수정
        try {
            let response = await fetch('/pages/mypage/categories-update', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(selectedCategories)
            });
            let data = await response.json();

            console.log('Category Update Response:', data); // Log response

            if (data.result == "success") {
                alertify.success('카테고리가 수정되었습니다.');
            }else {
                alertify.error('카테고리 수정에 실패했습니다.');
            }
        } catch (error) {
            console.error('Category Update Error:', error);
            alertify.error('카테고리 수정 중 오류가 발생했습니다.');
        }

        // Reload user details
        await detailMypage();
    });

    async function detailMypage() {
        let fetchOptions = {
            headers: {
                'ajax': 'true'
            }
        }

        let url = "/pages/mypage/detail";
        let response = await fetch(url, fetchOptions);
        let data = await response.json();

        console.log(data); // UserResultDto

        if (data.result === "success") {
            makeDetailHtml(data);
        } else if (data.result === "fail") {
            alert("조회 과정에서 오류가 발생했습니다.");
        } else if (data.result === "exception") {
            alert("예외 발생");
        } else if (data.result === "login") {
            window.location.href = "/pages/login";
        }
    }

    function makeDetailHtml(data) {
        let userDto = data.userdto;
        let userCategoryDtoList = data.userCategoryDtoList;

        // Fill user details
        if (userDto) {
            document.querySelector("#navName").innerHTML = userDto.userName + "님";
            document.querySelector("#name").value = userDto.userName;
            document.querySelector("#email").value = userDto.userEmail;
            document.querySelector("#password").value = userDto.userPassword;
            document.querySelector("#nickname").value = userDto.userNickname;
        } else {
            console.error('User DTO is not available in the response.');
        }

        // Initialize genre buttons based on userCategoryDtoList
        if (userCategoryDtoList) {
            // Extract selected category IDs
            const selectedCategoryIds = userCategoryDtoList.map(category => category.categoryId);

            // Debugging
            // console.log('Selected Category IDs:', selectedCategoryIds);

            document.querySelectorAll('.genre-btn').forEach(button => {
                const categoryId = parseInt(button.getAttribute('data-category-id'));

                // Debugging
                // console.log('Button Category ID:', categoryId);

                if (selectedCategoryIds.includes(categoryId)) {
                    button.classList.add('selected');
                    <%--console.log(`Button with ID ${categoryId} is selected.`);--%>
                } else {
                    button.classList.remove('selected'); // Ensure non-selected buttons are correctly handled
                    <%--console.log(`Button with ID ${categoryId} is not selected.`);--%>
                }
            });
        } else {
            console.error('User Category DTO List is not available in the response.');
        }
    }
</script>
</body>
</html>