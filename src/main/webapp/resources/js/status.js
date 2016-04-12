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
        problemsList:[],    // 题目列表
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
            //let pageInfo = getPageList1(6);
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
                        "caseNumber": 1,
                        "email": "virtual.judge.5@gmail.com",
                        "language": "C++",
                        "length": 2755,
                        "name": "黄汉升",
                        "nickName": "黄汉升",
                        "problemId": 360,
                        "returnType": "Wrong Answer on test 1",
                        "returnTypeId": 5,
                        "statusId": 185726,
                        "time": 1460379379000,
                        "userName": "Vjudge5"
                    },
                    {
                        "caseNumber": 1,
                        "email": "1125442950@qq.com",
                        "language": "C++",
                        "length": 1141,
                        "name": "晴天",
                        "nickName": "哈哈哈",
                        "problemId": 1134,
                        "returnType": "Wrong Answer on test 1",
                        "returnTypeId": 5,
                        "statusId": 185725,
                        "time": 1460379190000,
                        "userName": "smilesun"
                    },
                    {
                        "caseNumber": 3,
                        "email": "virtual.judge.1@gmail.com",
                        "language": "C++",
                        "length": 634,
                        "name": "关云长",
                        "nickName": "关云长",
                        "problemId": 1063,
                        "returnType": "Memory Limit Exceeded on test 3",
                        "returnTypeId": 4,
                        "statusId": 185724,
                        "time": 1460379019000,
                        "userName": "Vjudge1"
                    }
                ]
            },
            "status":200
        };
    return page;
}
