<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photogram</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>

<body>
    <div class="login">
        <div class="left-page">

        </div>
        <div class="middle-page">
            <div class="up-page"></div>
            <div class="login__form__container">
                <div class="login__form">
                    <div class="login-title">
                        <div class="login-text">회원가입</div>
                        <div class="login-register">계정이 있으신가요?<span><a href="/auth/signin">로그인</a></span></div>
                    </div>


                    <form class="login__input"  action="/auth/signup" method="POST">
                        <input type="text" name="name" placeholder="이름" required="required" />
                        <input type="email" name="email" placeholder="이메일" required="required" />
                        <input type="text" name="username" placeholder="아이디" required="required"/>
                        <input type="password" name="password" placeholder="비밀번호" required="required" />
                        <button>회원가입</button>
                    </form>
                </div>

            </div>
        </div>

        <div class="right-page">

        </div>
    </div>

    <div class="page-bottom"></div>
</body>

</html>