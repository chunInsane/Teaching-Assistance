<%--
  Created by IntelliJ IDEA.
  User: zhangliang
  Date: 16/4/4
  Time: 下午4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" autoFlush="true" %>
<!DOCTYPE html>
<html lang="cmn-Hans-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Teach-Assistance</title>

    <!-- Bootstrap -->
    <link href="/resources/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- <link href="/resources/css/main.css" rel="stylesheet"> -->
    <link href="/resources/css/login.css" rel="stylesheet">
    <link rel="shortcut icon" href="/resources/img/head.ico">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        #activate {
            padding-top: 80px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top elevation-4">
    <div class="container" class="elevation-4" translate="my-transition">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header ">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href=" ">Teach-Assistance</a >
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/index.html">首页 <span class="sr-only">(current)</span></a ></li>
                <li><a href="#">考前练习</a ></li>
                <li><a href="#">编程比赛</a ></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!-- nav bar end -->

<div class="container">
    <div id="activate" class="col-md-4 col-md-offset-4">

        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-md-3" for="inputUsername">用户邮箱</label>
                <div class="controls col-md-9">
                    <input v-model="activate_username" class="form-control" type="text" id="inputUsername" value="${username}" readonly placeholder="用户邮箱">
                </div>
            </div>
            <input v-model="activate_key" type="hidden" id="inputKey" value="${key}" readonly>
            <div class="form-group">
                <label class="control-label col-md-3" for="inputPassword">密码</label>
                <div class="controls col-md-9">
                    <input v-model="activate_pwd" class="form-control" type="password" id="inputPassword" minlength="6" maxlength="14">
                    <span class="red" for="inputPassword">{{ errorInfoPwd }}</span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3" for="inputRepeatPassword">重复密码</label>
                <div class="controls col-md-9">
                    <input v-model="activate_repeat_pwd" class="form-control" type="password" id="inputRepeatPassword" minlength="6" maxlength="14">
                    <span class="red" for="inputRepeatPassword">{{ errorInfoRepeatPwd }}</span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3" for="inputNickname">昵称</label>
                <div class="controls col-md-9">
                    <input v-model="activate_nickname" class="form-control" type="text" id="inputNickname" placeholder="昵称">
                    <span class="red" for="inputNickname">{{ errorInfoNickname }}</span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">性别</label>
                <div class="controls col-md-9">
                    <label class="radio-inline">
                        <input type="radio" v-model="activate_gender" name="inlineRadioOptions" id="inlineRadio1" value="0" checked>
                        Female
                    </label>
                    <label class="radio-inline">
                        <input type="radio" v-model="activate_gender" name="inlineRadioOptions" id="inlineRadio2" value="1">
                        Male
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3" for="inputSchool">学校</label>
                <div class="controls col-md-9">
                    <input v-model="activate_school" class="form-control" type="text" id="inputSchool" placeholder="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3" for="inputMobilePhone">联系电话</label>
                <div class="controls col-md-9">
                    <input v-model="activate_phone" class="form-control" type="text" id="inputMobilePhone" placeholder="联系电话">
                    <span class="red" for="inputMobilePhone">{{ errorInfoPhone }}</span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3" for="inputMotto">个性签名</label>
                <div class="controls col-md-9">
                    <input v-model="activate_motto" class="form-control" type="text" id="inputMotto" placeholder="个性签名">
                </div>
            </div>
            <div class="form-group">
                <button v-on:click="activate" type="button" class="btn btn-primary col-md-offset-4">activate</button>
                <button type="reset" class="btn btn-primary col-md-offset-1">reset</button>
            </div>
        </form>
    </div>
</div>


<!-- footer start -->
<footer class="footer col-xs-12">
    <!-- <hr class="colorful"> -->
    <div class="container text-center">
        <span>All Rights Reversed.</span>
        <span>邮箱：<a href="mailto:teach_assistance@163.com">teach_assistance@163.com</a ></span>
        <span>联系地址：山西省太原市尖草坪区学院路3号</span>
    </div>
</footer>
<!-- footer end -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/resources/lib/vue/dist/vue.js"></script>
<script src="/resources/lib/jquery/dist/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/lib/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/resources/lib/layer/layer.js"></script>
<script src="/resources/js/main.js"></script>
<script>
    "use strict"
    Vue.config.debug = true;
    let app = new Vue({
        el: "#activate",
        data:{
            activate_username: "",
            activate_key: "",
            activate_pwd: "",
            activate_repeat_pwd: "",
            activate_nickname: "",
            activate_gender: "",
            activate_school: "",
            activate_phone: "",
            activate_motto: "",
            errorInfoPwd: "",
            errorInfoRepeatPwd: "",
            errorInfoNickname: "",
            errorInfoPhone: "",
        },
        methods:{
            /**
             * 激活事件
             */
            activate: function(){
                let data = {
                    "username" : this.activate_username,
                    "password" : this.activate_pwd,
                    "repeatPassword" : this.activate_repeat_pwd,
                    "sex" : this.activate_gender,
                    "nickname" : this.activate_nickname,
                    "school" : this.activate_school,
                    "mobilePhone" : this.activate_phone,
                    "motto" : this.activate_motto,
                    "key" : this.activate_key,
                };
                if( typeof data.password === "undefined" ||
                        typeof data.repeatPassword === "undefined" ||
                        typeof data.nickname === "undefined" ||
                        typeof data.key === "key" ||
                        data.password.length <= 0 ||
                        data.repeatPassword.length <= 0 ||
                        data.nickname.length <= 0 ||
                        data.key.length <= 0 ){
                    layer.msg("输入不能为空!");
                    return false;
                }
                $.ajax({
                    method:"post",
                    data:JSON.stringify(data),
                    dataType:"json",
                    contentType:"application/json",
                    url:"/user/activate",
                    async:false,
                    error: function(msg){
                        layer.msg("请求失败!" + msg.message);
                        return false;
                    },
                }).done(function(msg){
                    if(msg.status === 200){
                        layer.msg("激活成功!")
                        window.location.href = "/index.html";
                    }else{
                        switch (msg.status) {
                            case 401:
                                layer.alert("未授权的操作!");
                                break;

                            case 402:
                                layer.msg("登录超时!");
                                window.location = "/"; // 回到首页
                                break;

                            case 403:
                                layer.msg("未登录!");
                                window.location = "/"; // 回到首页
                                break;

                            case 404:
                                layer.msg("页面不存在!");
                                window.location = "/"; // 回到首页
                                break;

                            case 500:
                                app.errorInfoPwd = msg.errors.password;
                                app.errorInfoRepeatPwd = msg.errors.repeatPassword;
                                app.errorInfoNickname = msg.errors.nickname;
                                app.errorInfoPhone = msg.errors.mobilePhone;
                                break;

                            default:
                                break;
                        }
                    }
                });

            }
        },
    });
</script>
</body>
</html>