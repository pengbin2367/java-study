<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            margin: 0 auto;
            padding: 2rem;
            font-weight: normal;
            display: flex;
            flex-direction: column;
            align-items: center;
            align-content: center;
            text-align: center;
            width: 100vw;
            height: 100vh;
            background: linear-gradient(to right, #12c2e9, #c471ed, #f64f59);
            font-family: "JetBrainsMono Nerd Font Mono", Times, serif !important;
        }

        a,
        .green {
            text-decoration: none;
            color: hsla(160, 100%, 37%, 1);
            transition: 0.4s;
            padding: 8px
        }
        * {
            box-sizing: border-box;
        }
        @media (max-width: 380px) {
            .form-control {
                width: 180px;
            }
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            background-color: rgba(0, 0, 0, 0.1);
            padding: 20px 40px;
            border-radius: 5px;
            width: 400px;
            margin: 200px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1), 0 6px 20px rgba(0, 0, 0, 0.1);
        }
        .container > h1 {
            margin-bottom: 30px;
        }
        .container > form .form-control {
            margin: 20px 0 40px;
            width: 300px;
            position: relative;
        }
        .container > form .form-control > input {
            display: block;
            border: none;
            width: 100%;
            height: 30px;
            border-radius: 5px;
            font-size: 18px;
        }
        .container > form .form-control > input:focus,
        .container > form .form-control > input:valid {
            outline: none;
        }
        .container > form .form-control > label {
            position: absolute;
            top: -20px;
            left: 0;
            pointer-events: none;
            color: white;
        }
        .container > form .btn {
            display: inline-block;
            background-color: lightblue;
            cursor: pointer;
            width: 100%;
            padding: 15px;
            font-size: 16px;
            font-family: inherit;
            border: none;
            border-radius: 5px;
            text-align: center;
            margin: 0 auto;
        }
        .container > form .btn:active {
            transform: scale(0.98);
        }
        .container > form .text {
            margin-top: 30px;
            display: inline-block;
        }
        .container > form .text > a {
            text-decoration: none;
            color: lightblue;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="user.do" method="post">
            <div class="form-control">
                <input type="text" name="username" id="username">
                <label>Username</label>
            </div>
            <div class="form-control">
                <input type="password" name="password" id="password">
                <label>Password</label>
            </div>
            <input class="btn" type="hidden" name="op" value="0x001"/>
            <input class="btn" id="login" type="submit" value="Login" />
            <p class="text">Don't have an account? </p><a href="#">去注册</a>
        </form>
    </div>
</body>
</html>
