"use strict"
/**
 * ---------------------------------------------
 * @purpose 自定义 javascript 文件
 * ---------------------------------------------
 */


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
