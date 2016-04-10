"use strict"
Vue.config.debug = true;     // 上线后关闭

// 定义题目详情组件
let probDetails = Vue.extend({
    data:function(){
        return {
            probId:1,
            probDetails:{
                title:"AAAAAAA",
                language:"c",
            },
        };
    },
    ready:function(){

    },
    template:"#problem-details",
});

let list = Vue.extend({
    data: function(){
        return {
            page:{
                currentPage: 1, // 当前页
                totalPage:1,    // 总页数
                maxPage:1,      // 分页列表中最大页码值
                minPage:1,      // 分页列表中最小页码值
            },
            problemsList:[],    // 题目列表
            searchKeyWord:"",   // 查询关键字(题名/id)
        }
    },
    ready:function(){
        let pageInfo = getPageList1(1);
        // let pageInfo = getPageList(1); 正式
        setPage(pageInfo, this);
    },
    methods:{
        setPage: function(index){
            console.log(index);
            this.page = {};
            let pageInfo = getPageList1(index);
            setPage(pageInfo, this);
        },
        search: function(){
            let keyWord = this.searchKeyWord;
            let pageInfo = getPageList1(6);
            // let pageInfo = searchPageList(keyWord); 正式
            setPage(pageInfo, this);
        },
    },
    template:"#list"
});

var App = Vue.extend({})

// 创建一个路由器实例
// 创建实例时可以传入配置参数进行定制，为保持简单，这里使用默认配置
var router = new VueRouter()

router.map({
    '/list': {
        name:'list',
        component: list
    },
    '/probDetails': {
        name:'probDetails',
        component: probDetails
    },
})

// 现在我们可以启动应用了！
// 路由器会创建一个 App 实例，并且挂载到选择符 #app 匹配的元素上。
router.start(App, '#app');
router.go({name:'list'});
/*
let app = new Vue({
    el: "#app",
    data:{
        page:{
            currentPage: 1, // 当前页
            totalPage:1,    // 总页数
            maxPage:1,      // 分页列表中最大页码值
            minPage:1,      // 分页列表中最小页码值
        },
        problemsList:[],    // 题目列表
        searchKeyWord:"",   // 查询关键字(题名/id)
    },
    ready:function(){
        let pageInfo = getPageList1(1);
        // let pageInfo = getPageList(1); 正式
        setPage(pageInfo, this);
    },
    methods:{
        setPage: function(index){
            console.log(index);
            this.page = {};
            let pageInfo = getPageList1(index);
            setPage(pageInfo, this);
        },
        search: function(){
            let keyWord = this.searchKeyWord;
            let pageInfo = getPageList1(6);
            // let pageInfo = searchPageList(keyWord); 正式
            setPage(pageInfo, this);
        },
    },
});*/

/**
 * 根据 页码 获取题目列表详情
 *
 * @param <Number> pagination
 * @returns <Object> result
 */
function getPageList(p){
    let data = {
        pagination: p,
    };
    $.ajax({
        mehtod:"get",
        data:data,
        dataType:"json",
        url:"",
        error: function(msg){
            layer.msg("获取数据失败!" + msg.message);
            return false;
        },
    }).done(function(msg){
        if(msg.status === 200){
            return msg.result;
        }else{
            showAjaxMsg(msg);
            return false;
        }
    });
}

/**
 * 根据关键词查询题目
 *
 * @param <String> keyword
 * @returns <Object> result
 */
function searchPageList(k){
    let data = {
        keyword: k,
    };
    console.log(k);
    $.ajax({
        mehtod:"post",
        data:data,
        dataType:"json",
        url:"",
        error: function(msg){
            layer.msg("获取数据失败!" + msg.message);
            return false;
        },
    }).done(function(msg){
        if(msg.status === 200){
            return msg.result;
        }else{
            showAjaxMsg(msg);
            return false;
        }
    });
}

function setPage(pageInfo, _this ){
    let _this_ = _this;
    if(pageInfo){
        _this_.page.currentPage = pageInfo.result.pageInfo.currentPage;
        _this_.page.totalPage = pageInfo.result.pageInfo.totalPages;
        _this_.problemsList = pageInfo.result.list;
        console.log(pageInfo.result.list);
    }
    if(_this_.page.currentPage > _this_.page.totalPage){
        console.log("页码参数错误!");
        _this_.page.minPage = 1;
        _this_.page.currentPage = _this_.page.totalPage;
        // this.page
        return false;
    }
    if(_this_.totalPages > 10){
        _this_.page.maxPage = _this_.page.currentPage;
    }else{
        if(_this_.page.currentPage > 4){
            if(_this_.page.currentPage + 5 <= _this_.page.totalPage){
                _this_.page.minPage = _this_.page.currentPage - 4;
                _this_.page.maxPage = _this_.page.minPage + 10;
            }else{
                _this_.page.maxPage = _this_.page.totalPage;
                _this_.page.minPage = _this_.page.totalPage - 9;
            }
        }else{
            _this_.page.minPage = 1;
            _this_.page.maxPage = 10;
        }
    }
}

// 测试
function getPageList1(index){
    let page = {
        "errors":{},
        "result":
            {
                "pageInfo":{
                    "countPerPage":15,
                    "currentPage":index,
                    "displayDistance":2,
                    "firstNo":0,
                    "totalItems":2,
                    "totalPages":32
                },
                "list":[
                    {
                        "difficulty":1,
                        "isSpj":false,
                        "isVisible":true,
                        "problemId":1,
                        "solved":0,
                        "source":"Classic Problem",
                        "title":"A+B Problem",
                        "tried":0
                    },
                    {
                        "difficulty":1,
                        "isSpj":false,
                        "isVisible":false,
                        "problemId":2,
                        "solved":0,
                        "source":"中北大学校赛!",
                        "title":"a - b",
                        "tried":0
                    }
                ]
            },
            "status":200
        };
    return page;
}
