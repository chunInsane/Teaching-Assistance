"use strict"
Vue.config.debug = true;     // 上线后关闭


let app = new Vue({
    el: "#app",
    data:{
        probId:'',
        userId:'',
        languageId:0,
        result:0,
        page:{
            currentPage: 1, // 当前页
            totalPage:1,    // 总页数
            maxPage:1,      // 分页列表中最大页码值
            minPage:1,      // 分页列表中最小页码值
        },
        problemsList:[],    // 题目列表
        searchKeyWord:"",   // 查询关键字(题名/id)
    },
    ready:function() {
        let queryParam = {};
        if (window.location.href.split("?")[1] && window.location.href.split("?")[1].indexOf("problemId") === 0){
            let probId = parseInt(window.location.href.split("?")[1].split("=")[1]);
            this.probId = probId;
        } else {
            console.error("根据 url 无法获取 problemId 参数！");
        }
        if(this.probId){
            //TODO
            let search = {
                userId: this.userId,
                problemId: this.probId,
                languageId: this.languageId,
                result: this.result,
            };
            let pageInfo = getPageList(1, search);
            setPage(pageInfo, this);
        }else {
            let pageInfo = getPageList(1); //正式
            console.log(pageInfo);
            setPage(pageInfo, this);
        }
        /*$.extend({
            getUrlVars: function() {
                var vars = [], hash;
                var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
                for(var i = 0; i < hashes.length; i++)
                {
                    hash = hashes[i].split('=');
                    vars.push(hash[0]);
                    vars[hash[0]] = hash[1];
                }
                return vars;
            },
            getUrlVar: function(name) {
                return $.getUrlVars()[name];
            }
        });
        var problemId = $.getUrlVar("problemId");
        queryParam[problemId] = problemId;*/
        //let pageInfo = getPageList1(1);

    },
    methods:{
        setPage: function(index){
            console.log(index);
            /*this.page = {};
            let pageInfo = getPageList1(index);
            setPage(pageInfo, this);*/
            let pageInfo = getPageList(index);
            setPage(pageInfo, this);
        },
        search: function(){
            /*let keyWord = this.searchKeyWord;
            let pageInfo = searchPageList(keyWord); //正式
            setPage(pageInfo, this);*/
            let search = {
                userId: this.userId,
                problemId: this.probId,
                languageId: this.languageId,
                result: this.result,
            };
            let pageInfo = getPageList(1, search);
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
function getPageList( p, search ){
    let data = {
        currentPage: p || 1,
    };
    if(search){
        data = search;
        data.currentPage = p;
    }
    let result = {};

    $.ajax({
        method:"post",
        data:JSON.stringify(data),
        dataType:"json",
        contentType:"application/json",
        async:false,
        url:"/status/search",
        error: function(msg){
            layer.msg("获取数据失败!" + msg.message);
            return false;
        },
    }).done(function(msg){
        if(msg.status === 200){
            console.log(msg.result);
            result = msg.result;
            return true;
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
    $.ajax({
        method:"post",
        data:JSON.stringify(data),
        dataType:"json",
        contentType:"application/json",
        async:false,
        url:"/status/search",
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
        _this_.problemsList = pageInfo.list;
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

// 根据 probId 获取题目状态
function getProbStatus( id ){

}
