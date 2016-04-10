"use strict"
/**
 * ---------------------------------------------
 * @purpose 自定义 javascript 文件
 * ---------------------------------------------
 */
 var browser = {"name":"unknow","version":"unknow"};
 var agent  = navigator.userAgent;
 //Chrome
 if(agent.indexOf("Chrome/")>-1){
   browser["name"] = "Chrome";
   browser["version"] = agent.split("Chrome/")[1].split(" ")[0];
 }
 //Firefox
 else if(agent.indexOf("Firefox/")>-1){
   browser["name"] = "Firefox";
   browser["version"] = agent.split("Firefox/")[1];
 }
 //IE
 else if(agent.indexOf("MSIE ")>-1){
   browser["name"] = "Internet Explorer";
   browser["version"] = parseInt(agent.split("MSIE ")[1])+".0";
 }
 console.log(browser);
 if(browser.name !== "Chrome" && browser.name !== "Firefox"){
     layer.alert("请使用 Chrome / Firefox 浏览器!");
     setTimeout(leave,6000);
     function leave(){
        window.location = "/index.html";
     }
 }
/**
 * 输出 异步请求 返回信息
 *
 * @param <Object> mag
 * @returns none
 */
function showAjaxMsg(msg){
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
            layer.alert("<p><b>" + msg.message + "</b></p>" + JSON.stringify(msg.errors));
            break;

        default:
            break;
    }
}
