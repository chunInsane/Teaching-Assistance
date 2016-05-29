"use strict"
Vue.config.debug = true;     // 上线后关闭

// 比赛列表 组件
let contestsList = Vue.extend({
    data:function (){
        return {
            page:{
                currentPage: 1, // 当前页
                totalPage:1,    // 总页数
                maxPage:1,      // 分页列表中最大页码值
                minPage:1,      // 分页列表中最小页码值
            },
            contestsList:[],    // 题目列表
        };
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
        //  根据 $("#tab_1 a").eq().tab('show')
        //  切换 tab 选项卡
    }, 
    template:"#contestsList",
});

// 定义题目详情组件
let contestDetails = Vue.extend({
    data:function(){
        return {
            contestId: this.$route.params.contestId || 0, // url 传入 题目 id
            userId: 0,
            privateCode: "", // private contest login password
            problemList: [],
            problemListLen: 1,
            hightlightProb: 0,
            problem: {},
            contest: {},         // 向服务器请求的 比赛题目 详情
            submitProbId: this.problem ? this.problem.problemId : 0,
        };
    },
    ready:function(){
        // 初始加载页面的数据

        let _this = this;
        let data = {
            contestId: _this.contestId,
            password: _this.privateCode,
        };
        let isFrist = 0;
        // let self = {
        //     self:_this,
        //     data: {},
        //     isFrist:isFrist,
        // };
        function isPrivate(self){
            $.ajax({
                url:'/contest/loginContest',
                data: JSON.stringify(data),
                method:'post',
                dataType:'json',
                contentType:'application/json',
                async:false,
                error: function(msg){
                    layer.msg("获取数据失败!" + msg.message+_this.userId);
                    return false;
                },
            }).done(function(msg){
                if (msg.status === 200){
                    console.log("ready function 1");
                    $.ajax({
                        url:'/contest/data/' + _this.contestId,
                        data: JSON.stringify(data),
                        method:'post',
                        dataType:'json',
                        contentType:'application/json',
                        async:false,
                        error: function(msg){
                            layer.msg("获取数据失败!" + msg.message+_this.userId);
                            return false;
                        },
                    }).done(function(msg){
                        if (msg.status === 200){
                            _this.contest = msg.result.contest;
                            _this.problemList = msg.result.problemList;
                            _this.problemListLen = msg.result.problemList.length;
                            _this.problem = msg.result.problemList[_this.hightlightProb];
                            _this.submitProbId = _this.problem.problemId;
                            console.log (_this.problem);
                            setProbAttr(_this.problem);
                            
                            // test
                            // $("#isPrivate").click();

                            return true;
                        }else {
                            showAjaxMsg(msg);
                            return false;
                        }
                    });

                    return true;
                }else {
                    console.log("ready function 0");
                    if( isFrist ){
                        showAjaxMsg(msg);
                        isFrist ++;
                    }
                    $("#isPrivate").click();
                    // isPrivate();
                    return false;
                }
            });
        }
        
        isPrivate();
        
    },
    methods:{
        /*
         * 根据 索引号 显示比赛题目详情
         */
        checkPrivate: function(){
            // 初始加载页面的数据

            let _this = this;
            let data = {
                contestId: _this.contestId,
                password: _this.privateCode,
            };
            let isFrist = 0;
            // let self = {
            //     self:_this,
            //     data: {},
            //     isFrist:isFrist,
            // };
            function isPrivate(self){
                $.ajax({
                    url:'/contest/loginContest',
                    data: JSON.stringify(data),
                    method:'post',
                    dataType:'json',
                    contentType:'application/json',
                    async:false,
                    error: function(msg){
                        layer.msg("获取数据失败!" + msg.message+_this.userId);
                        return false;
                    },
                }).done(function(_msg){
                    if (_msg.status === 200){
                        $.ajax({
                            url:'/contest/data/' + _this.contestId,
                            data: JSON.stringify(data),
                            method:'post',
                            dataType:'json',
                            contentType:'application/json',
                            async:false,
                            error: function(msg){
                                layer.msg("获取数据失败!" + msg.message+_this.userId);
                                return false;
                            },
                        }).done(function(msg){
                            if (msg.status === 200){
                                _this.contest = msg.result.contest;
                                _this.problemList = msg.result.problemList;
                                _this.problemListLen = msg.result.problemList.length;
                                _this.problem = msg.result.problemList[_this.hightlightProb];
                                _this.submitProbId = _this.problem.problemId;
                                console.log (_this.problem);
                                setProbAttr(_this.problem);
                                return true;
                            }else {
                                showAjaxMsg(msg);
                                return false;
                            }
                        });
                        $("#isPrivate").click(); // 此次点击则模态框隐藏
                        return true;
                    }else {
                        showAjaxMsg(_msg);
                        return false;
                    }
                });
                console.log("checkPrivate function");
                
            }
            isPrivate();       
        },
        /*
         * 根据 索引号 显示比赛题目详情
         */
        checkProbDetails: function (index){
            this.hightlightProb = index; // from 0
            console.log(index);
            $("#tab_1 a").eq(1).tab('show');
            this.problem = this.problemList[index];
            this.submitProbId = this.problem.problemId;
            setProbAttr(this.problem);
        },
        /**
         * 提交题目
         *
         * @param
         * @returns
         */
        submit: function(problemId, contestId){
            let data = {
                problemId: this.submitProbId,
                contestId: this.contestId,
                languageId:this.language,
                codeContent:this.code,
            };
            console.log(data);
            if(typeof data.contestId === "undefined" || data.contestId === "" || data.contestId == 0){
                layer.msg("未选择比赛!");
                return false;
            }

            if(typeof data.problemId === "undefined" || data.problemId === "" || data.problemId == 0){
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
    template:"#contestDetails",
});


let App = Vue.extend({});
let router = new VueRouter();
router.map({
    "/": {
        name:"list",
        component:contestsList
    },
    "/list": {
        name:"List",
        component:contestsList
    },
    "/detail/:contestId/": {
        name:"Details",
        component:contestDetails
    }
})
router.start(App, "#app");

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



/**
 *
 * 将 problem 指定属性 转换由 MD格式 转为 HTML
 * @parma <Object> problem
 * @return <Object> problem
 */
function setProbAttr(problem){
    if(typeof problem === 'object'){
        if(problem.problemId) {
            problem.hint = transMd(problem.hint);
            problem.description = transMd(problem.description);
            problem.input = transMd(problem.input);
            problem.output = transMd(problem.output);
            problem.sampleInput = JSON.parse(problem.sampleInput);
            problem.sampleOutput = JSON.parse(problem.sampleOutput);
            problem.sampleLen = problem.sampleOutput.length;
            $('#hint').html(problem.hint);
            $('#description').html(problem.description);
            $('#input').html(problem.input);
            $('#output').html(problem.output);
            return true;
        }else{
            console.error(" problem is not exist");
            return false;
        }
    }else{
        console.error("传入参数不是对象！");
        return false;
    }
}
/**
 *
 * 检测比赛是否是 私有的 <private>
 * @parma <Object> contestAttribute
 * @return <Object> contest
 */
function isPrivate(self){
            $.ajax({
                url:'/contest/data/' + _this.contestId,
                data: JSON.stringify(data),
                method:'post',
                dataType:'json',
                contentType:'application/json',
                async:true,
                error: function(msg){
                    layer.msg("获取数据失败!" + msg.message+_this.userId);
                    return false;
                },
            }).done(function(msg){
                if (msg.status === 200){
                    _this.contest = msg.result.contest;
                    _this.problemList = msg.result.problemList;
                    _this.problemListLen = msg.result.problemList.length;
                    _this.problem = msg.result.problemList[_this.hightlightProb];
                    _this.submitProbId = _this.problem.problemId;
                    console.log (_this.problem);
                    setProbAttr(_this.problem);
                    
                    // test
                    // $("#isPrivate").click();

                    return true;
                }else {
                    if( isFrist ){
                        showAjaxMsg(msg);
                        isFrist ++;
                    }
                    $("#isPrivate").click();
                    isPrivate();
                    return false;
                }
            });
        }


