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
        .genre-btn {
            background-color: #EDEDED;
            color: black;
            border: none;
            margin: 5px;
            padding: 10px 20px;
            border-radius: 20px;
        }

        .genre-btn.selected {
            background-color: #74C1BA; /* 선택된 버튼 색상 */
            color: white;
        }
        #valid {
            font-size: 12px;
            margin:0px;
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
                    <p id="valid">🎶 1개 이상의 특수문자, 대소문자 및 숫자 포함 8자리 이상</p>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="userPassword2" placeholder="confirm password" name="userPassword">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="userNickname" placeholder="nickname" name="userNickname">
                </div>

                <div class="mb-1 mt-3">
                    <h6 >좋아하는 음악 취향을 선택해주세요 💓</h6>
                </div>

                <div class="mt-1 mb-3" id="genreButtons">
                    <button type="button" class="btn genre-btn">발라드</button>
                    <button type="button" class="btn genre-btn">힙합</button>
                    <button type="button" class="btn genre-btn">인디</button>
                    <button type="button" class="btn genre-btn">락/메탈</button>
                    <button type="button" class="btn genre-btn">트로트</button>
                    <button type="button" class="btn genre-btn">댄스</button>
                    <button type="button" class="btn genre-btn">R&B</button>
                    <button type="button" class="btn genre-btn">밴드</button>
                </div>

                <div>
                    <button id="btnSignup" class="btn btn-primary" type="button">회원가입</button>
                </div>


            </form>
        </div>
    </div>
</div>

<script>
    let selectedCategories = new Set();

    window.onload = function(){
        document.querySelector("#userName").focus();
        document.querySelector("#userName").onblur = function () {
            if (validateUserName( this.value )) {
                this.classList.remove("is-invalid");
                this.classList.add('is-valid');
            } else {
                this.classList.remove("is-valid");
                this.classList.add('is-invalid');
            }
        };
        document.querySelector("#userPassword").onblur = function () {
            if (validatePassword( this.value )) {
                this.classList.remove("is-invalid");
                this.classList.add('is-valid');
            } else {
                this.classList.remove("is-valid");
                this.classList.add('is-invalid');
            }
        };
        document.querySelector("#userPassword2").onblur = function () {
            if (validatePassword2( this.value )) {
                this.classList.remove("is-invalid");
                this.classList.add('is-valid');
            } else {
                this.classList.remove("is-valid");
                this.classList.add('is-invalid');
            }
        };
        document.querySelector("#userEmail").onblur = function () {
            if (validateEmail( this.value )) {
                this.classList.remove("is-invalid");
                this.classList.add('is-valid');
            } else {
                this.classList.remove("is-valid");
                this.classList.add('is-invalid');
            }
        };
        document.querySelector('input').onfocus = function () {
            this.classList.remove('is-valid', 'is-invalid');
        };

        //submit
        document.querySelector("#btnSignup").onclick = function(){
            if( document.querySelectorAll("form .is-invalid").length > 0 ){
                alert("입력이 올바르지 않습니다.")
            }else{
                signup();
            }
        };

        // 카테고리 버튼 선택 로직
        document.querySelectorAll(".genre-btn").forEach(button => {
            button.addEventListener("click", () => {
                if (selectedCategories.has(button.innerText)) {
                    selectedCategories.delete(button.innerText);
                    button.classList.remove("selected");
                } else {
                    selectedCategories.add(button.innerText);
                    button.classList.add("selected");
                }
            });
        });

    }

    function validateUserName(userName) {
        if (userName.length >= 4) return true;
        else return false;
    }
    function validatePassword(userPassword) {
        var patternEngAtListOne = new RegExp(/[a-zA-Z]+/);// + for at least one
        var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/);// + for at least one
        var patternNumAtListOne = new RegExp(/[0-9]+/);// + for at least one

        if( patternEngAtListOne.test( userPassword )
            && patternSpeAtListOne.test( userPassword )
            && patternNumAtListOne.test( userPassword )
            && userPassword.length >= 8
        ){
            return true;
        }
        else return false;
    }
    function validatePassword2(userPassword2) {
        if (userPassword2 == document.querySelector("#userPassword").value ) return true;
        else return false;
    }
    function validateEmail(userEmail) {
        let regexp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if (regexp.test(userEmail)) return true;
        else return false;
    }

    async function signup() {
        var userName = document.querySelector("#userName").value;
        var userEmail = document.querySelector("#userEmail").value;
        var userPassword = document.querySelector("#userPassword").value;
        var userNickname = document.querySelector("#userNickname").value;

        const categoryMapping = {
            '발라드': 1,
            '힙합': 2,
            '인디': 3,
            '락/메탈': 4,
            '트로트': 5,
            '댄스': 6,
            'R&B': 7,
            '밴드': 8,
        };

        const categoryIds = Array.from(selectedCategories).map(name => categoryMapping[name]);

        let fetchOptions = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userDto: {
                    userName: userName,
                    userEmail: userEmail,
                    userPassword: userPassword,
                    userNickname: userNickname,
                    userImage: ""
                },
                categories: categoryIds
            }),
        }

        let response = await fetch("/pages/signup", fetchOptions);
        console.log(response);

        let data = await response.json();

        switch (data.result) {
            case "email_exists":
                alertify.alert('중복 이메일', '이미 사용 중인 이메일입니다.');
                break;
            case "nickname_exists":
                alertify.alert('중복 닉네임', '이미 사용 중인 닉네임입니다.');
                break;
            case "success":
                alertify.alert('Welcome!', '송송🎶 회원가입을 축하합니다.', function(){
                    window.location.href="/pages/login";
                });
                break;
            case "fail":
                alert("sorry, server error");
                break;
            default:
                alert("error");
        }
    }
</script>
</body>
</html>
