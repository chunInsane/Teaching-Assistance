"use strict"
Vue.config.debug = true;     // 上线后关闭


let app = new Vue({
    el: "#app",
    data:{
        page:{
            currentPage: 1, // 当前页
            totalPage:1,    // 总页数
            maxPage:1,      // 分页列表中最大页码值
            minPage:1,      // 分页列表中最小页码值
        },
        contestsList:[],    // 题目列表
        searchKeyWord:"",   // 查询关键字(题名/id)
    },
    ready:function(){
        //let pageInfo = getPageList1(1);
        let pageInfo = getPageList(1); //正式
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
            //let pageInfo = getPageList1(2);
            let pageInfo = searchPageList(keyWord); //正式
            setPage(pageInfo, this);
        },
    },
});

/**
 * 根据 页码 获取题目列表详情
 *
 * @param <Number> pagination
 * @returns <Object> result
 */
function getPageList(p){
    let data = {
        currentPage: p,
    };

    let result = {};

    $.ajax({
        method:"post",
        data:JSON.stringify(data),
        dataType:"json",
        contentType:"application/json",
        url:"/contest/search",
        async:false,
        error: function(msg){
            layer.msg("获取数据失败!" + msg.message);
            return false;
        },
    }).done(function(msg){
        if(msg.status === 200){
            result = msg.result;
            return true
        }else{
            showAjaxMsg(msg);
            return false;
        }
    });

    return result;
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

    let result = {};

    console.log(k);
    $.ajax({
        method:"post",
        data:JSON.stringify(data),
        dataType:"json",
        contentType:"application/json",
        url:"/contest/search",
        async:false,
        error: function(msg){
            layer.msg("获取数据失败!" + msg.message);
            return false;
        },
    }).done(function(msg){
        if(msg.status === 200){
            result = msg.result;
            return true;
        }else{
            showAjaxMsg(msg);
            return false;
        }
    });

    return result;
}

function setPage(pageInfo, _this ){
    let _this_ = _this;
    if(pageInfo){
        _this_.page.currentPage = pageInfo.pageInfo.currentPage;
        _this_.page.totalPage = pageInfo.pageInfo.totalPages;
        _this_.contestsList = pageInfo.list;
        console.log(pageInfo.list);
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
                    "totalPages":2
                },
                "list":[
                    {
                        "contestId":92,"isVisible":true,"length":60000,"status":"Pending","time":1467385200000,"title":"2016年ACM-ICPC暑假前集训报名","type":3,"typeName":"Invited"},
                    {
                        "contestId":94,"isVisible":true,"length":18000000,"status":"Ended","time":1460174400000,"title":"The 14th UESTC Programming Contest Final 重现赛","type":0,"typeName":"Public"},
                    {
                        "contestId":90,"isVisible":true,"length":18000000,"status":"Ended","time":1459569600000,"title":"The 14th UESTC Programming Contest Final","type":5,"typeName":"Onsite"},
                    {
                        "contestId":91,"isVisible":true,"length":3600000,"status":"Ended","time":1459562400000,"title":"The 14th UESTC Programming Contest Final Warmup","type":0,"typeName":"Public"},
                    {
                        "contestId":89,"isVisible":true,"length":43200000,"status":"Ended","time":1458954000000,"title":"The 14th UESTC Programming Contest Preliminary","type":3,"typeName":"Invited"},
                    {
                        "contestId":86,"isVisible":true,"length":43200000,"status":"Ended","time":1458349200000,"title":"The 14th UESTC Programming Contest Warmup #1","type":0,"typeName":"Public"},
                    {
                        "contestId":88,"isVisible":true,"length":864000000,"status":"Ended","time":1457311440000,"title":"练习","type":1,"typeName":"Private"},
                    {
                        "contestId":87,"isVisible":true,"length":10800000,"status":"Ended","time":1450591200000,"title":"第七届ACM趣味程序设计竞赛第四场（正式赛）","type":0,"typeName":"Public"},
                    {
                        "contestId":80,"isVisible":true,"length":86400000,"status":"Ended","time":1450332000000,"title":"人要有梦想，万一实现了呢？","type":1,"typeName":"Private"},
                    {
                        "contestId":79,"isVisible":true,"length":11400000,"status":"Ended","time":1450090200000,"title":"数学科学学院2015级C语言第七次上机","type":1,"typeName":"Private"},
                    {
                        "contestId":52,"isVisible":true,"length":10800000,"status":"Ended","time":1449900000000,"title":"第七届ACM趣味程序设计竞赛第三场（正式赛）","type":0,"typeName":"Public"},
                    {
                        "contestId":85,"isVisible":true,"length":86400000,"status":"Ended","time":1449727200000,"title":"山有榛，隰有苓。云谁之思？","type":1,"typeName":"Private"},
                    {
                        "contestId":78,"isVisible":true,"length":11400000,"status":"Ended","time":1449485400000,"title":"数学科学学院2015级C语言第六次上机","type":1,"typeName":"Private"},
                    {
                        "contestId":83,"isVisible":true,"length":10800000,"status":"Ended","time":1449295200000,"title":"第七届ACM趣味程序设计竞赛第二场（正式赛）","type":0,"typeName":"Public"},
                    {
                        "contestId":77,"isVisible":true,"length":11400000,"status":"Ended","time":1448880600000,"title":"数学科学学院2015级C语言第五次上机","type":1,"typeName":"Private"},
                    {
                        "contestId":81,"isVisible":true,"length":10800000,"status":"Ended","time":1448690400000,"title":"第七届ACM趣味程序设计竞赛第一场（热身赛）","type":0,"typeName":"Public"},
                    {
                        "contestId":76,"isVisible":true,"length":11400000,"status":"Ended","time":1448275800000,"title":"数学科学学院2015级C语言第四次上机","type":1,"typeName":"Private"},
                    {
                        "contestId":72,"isVisible":true,"length":18000000,"status":"Ended","time":1448271300000,"title":"The 2015 China Collegiate Programming Contest","type":1,"typeName":"Private"},
                    {
                        "contestId":71,"isVisible":true,"length":18000000,"status":"Ended","time":1448164800000,"title":"人生苦短","type":1,"typeName":"Private"},
                    {
                        "contestId":75,"isVisible":true,"length":11400000,"status":"Ended","time":1447066200000,"title":"数学科学学院2015级C语言第三次上机","type":1,"typeName":"Private"}
                ]
            },
            "status":200
        };
    return page;
}
