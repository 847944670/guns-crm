<%--
  Created by IntelliJ IDEA.
  User: mayn
  Date: 2018/10/16
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Guns - 登录</title>

    <link rel="shortcut icon" href="${ctxPath}/static/favicon.ico">
    <link href="${ctxPath}/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctxPath}/static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctxPath}/static/css/style.css?v=4.1.0" rel="stylesheet">
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>
    <script src="${ctxPath}/static/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctxPath}/static/js/bootstrap.min.js?v=3.3.6"></script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen">
    <div style="padding: 100px 0px;">
        <div>
            <h1 class="logo-name">GS</h1>
        </div>
        <h3>欢迎使用 Guns</h3>
        <br/>
        <h4 style="color: red;">${tips!}</h4>
        <form class="m-t" role="form" action="${ctxPath}/login" method="post">
            <div class="form-group">
                <input type="text" name="username" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="密码" required="">
            </div>
            @if(kaptcha.getKaptchaOnOff() == true){
            <div class="form-group" style="float: left;">
                <div class="col-sm-8" style="padding-left: 0px; padding-right: 0px;">
                    <input class="form-control" type="text" name="kaptcha" placeholder="验证码" required="">
                </div>
                <div class="col-sm-4" style="padding-left: 0px; padding-right: 0px;">
                    <img src="${ctxPath}/kaptcha" id="kaptcha" width="100%" height="100%"/>
                </div>
            </div>
            @}
            <div class="form-group" style="float: left;">
                <div class="checkbox" style="text-align: left">
                    <label>
                        <input type="checkbox" name="remember" style="margin-top: 2px;">记住我
                    </label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            </p>
        </form>
    </div>
</div>

<script>
    $(function () {
        $("#kaptcha").on('click', function () {
            $("#kaptcha").attr('src', '${ctxPath}/kaptcha?' + Math.floor(Math.random() * 100)).fadeIn();
        });
    });
</script>

</body>
</html>
