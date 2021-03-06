"use strict"
Vue.config.debug = true;     // 上线后关闭
// var  aa= {};

// 定义题目详情组件
let probDetails = Vue.extend({
    data:function(){
        return {
            probId:1,
            problem:{},
            code:"",
            language:"",
            problemId:null,
        };
    },
    ready:function(){
        let pid = '';
        if (this.$route.params.probId) {
            pid =  this.$route.params.probId;
        } else {
            pid = window.location.href.split("probDetails/")[1];
        }
        this.problemId = pid;
        this.problem = getProbDetails(pid);
        if(this.problem.problemId) {
            this.problem.hint = transMd(this.problem.hint);
            this.problem.description = transMd(this.problem.description);
            this.problem.input = transMd(this.problem.input);
            this.problem.output = transMd(this.problem.output);
            this.problem.sampleInput = JSON.parse(this.problem.sampleInput);
            this.problem.sampleOutput = JSON.parse(this.problem.sampleOutput);
            this.problem.sampleLen = this.problem.sampleOutput.length;
            $('#hint').html(this.problem.hint);
            $('#description').html(this.problem.description);
            $('#input').html(this.problem.input);
            $('#output').html(this.problem.output);
        }
    },
    methods:{
        /**
         * 提交题目
         *
         * @param
         * @returns
         */
        submit: function(problemId, contestId){
            let data = {
                problemId:problemId,
                contestId:contestId,
                languageId:this.language,
                codeContent:this.code,
            };
            console.log(data);
            if(typeof data.problemId === "undefined" || data.problemId === ""){
                layer.msg("未选择题目!");
                return false;
            }
            if(typeof data.languageId === "undefined" || data.languageId === ""){
                layer.msg("选择语言类型!");
                return false;
            }
            if(typeof data.codeContent === "undefined" || data.codeContent === ""){
                layer.msg("请填写代码!");
                return false;
            }
            $.ajax({
                method:"post",
                data:JSON.stringify(data),
                dataType:"json",
                contentType:"application/json",
                async:false,
                url:"/status/submit",
                error: function(msg){
                    layer.msg("提交数据失败!" + msg.message);
                    return false;
                },
            }).done(function(msg){
                if(msg.status === 200){
                    layer.msg("提交成功!");
                    setTimeout(function () {
                        window.location.href = "/status/index.html?probId="+data.problemId;
                        //console.log("status");
                    },2000)
                }else{
                    showAjaxMsg(msg);
                    return false;
                }
            });
        }
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
        //let pageInfo = getPageList1(1); //test
        let pageInfo = getPageList(1); //正式
        setPage(pageInfo, this);
    },
    methods:{
        setPage: function(index){
            console.log(index);
            this.page = {};
            let pageInfo = getPageList(index);
            console.log(pageInfo);
            setPage(pageInfo, this);
        },
        search: function(){
            let keyWord = this.searchKeyWord;
            //let pageInfo = getPageList1(6); //test
            let pageInfo = searchPageList(keyWord);// 正式
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
    "/": {
        name:'list',
        component: list
    },
    '/list': {
        name:'list',
        component: list
    },
    '/probDetails/:probId': {
        name:'probDetails',
        component: probDetails
    },
})

// 现在我们可以启动应用了！
// 路由器会创建一个 App 实例，并且挂载到选择符 #app 匹配的元素上。
router.start(App, '#app');
//router.go({name:'list'});
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
        async:false,
        url:"/problem/search",
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

function t(){
    let a = {};
    return a;
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
        async:false,
        url:"/problem/search",
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

/**
 * 根据 pageinfo 设置页码
 *
 * @param <Object> pageinfo
 * @param <Object> _this
 * @returns none
 */
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

// 测试 返回假数据
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


/**
 * 根据 prob Id 获取 题目详情
 *
 * @param <Number> pid
 * @returns <Object> probDetails
 */
function getProbDetails(pid){
    let data = {
        problemId: pid,
    };

    let result = {};
    $.ajax({
        method:"post",
        dataType:"json",
        contentType:"application/json",
        async:false,
        url:"/problem/data/" + data.problemId,
        error: function(msg){
            layer.msg("获取数据失败!" + msg.message);
            return false;
        },
    }).done(function(msg){
        if(msg.status === 200){
            result = msg.result.problem;
            return true;
        }else{
            showAjaxMsg(msg);
            return false;
        }
    });
    return result;
}
