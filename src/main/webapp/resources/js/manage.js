"use strict"

Vue.config.debug = true;     // 上线后关闭
/**
 * 默认首页
 */
let home = Vue.extend({
    data: function(){
        return {

        };
    },
    ready: function(){

    },
    template: "#home",
})

/**
 * 新建题目
 */
let addProb = Vue.extend({
    data: function(){
        return {
            canSubmit: true,
            sample_input: [
                "",
            ],
            sample_output: [
                "",
            ],
            // 新题目数据
            title: "",      // 题目名
            TimeLimit: 1000,
            javaTimeLimit: 3000,
            MemoryLimit: 65535,
            JavaMemoryLimit: 65535,
            OutputLimit: 65535,
            TotalData: "",
            isSpj: false,
            isVisible: false,
            description: "",
            input: "",
            output: "",
            hint: "",
            source: "",
        };
    },
    ready: function(){
        initMD();
    },
    methods: {
        /**
         * 添加输入输出实例
         */
        addSample: function() {
            this.sample_input.push('input '+this.sample_input.length); // https://github.com/vuejs/vue/issues/1552
            this.sample_output.push('output '+this.sample_input.length);
        },
        /**
         * 删除输入输出实例
         */
        deleteSample: function(index){
            this.sample_input.splice(index, 1);
            this.sample_output.splice(index, 1);
        },
        /**
         * 上传新题目
         * {
             action: "new",
             description: "cacad",
             hint: "cadscas",
             input: "cacdac",
             isSpj: false,
             isVisible: false,
             javaMemoryLimit: 65535,
             javaTimeLimit: 3000,
             memoryLimit :65535,
             output: "cacdac",
             outputLimit: 65535,
             sampleInput: ["cads"],
             sampleOutput: ["cadsc"],
             source: "caca",
             timeLimit: 1000,
             title: "hello",
             }
         */
        addproblems: function(){
            let prob = {};
            this.canSubmit = true; // 初始化
            prob.action = "new";
            prob.description = this.description;
            prob.hint = this.hint;
            prob.input = this.input;
            prob.isSpj = this.isSpj;
            prob.isVisible = this.isVisible;
            prob.javaMemoryLimit = this.JavaMemoryLimit;
            prob.javaTimeLimit = this.javaTimeLimit;
            prob.memoryLimit = this.MemoryLimit;
            prob.output = this.output;
            prob.outputLimit = this.OutputLimit;
            prob.sampleInput = this.sample_input;
            prob.sampleOutput = this.sample_output;
            prob.source = this.source;
            prob.timeLimit = this.TimeLimit;
            prob.title = this.title;
            prob.TotalData = this.TotalData;    // 上传文件名
            console.log(prob);
            for(let item in prob){
                if(!(item === "isSpj" || item === "isVisible")){
                    if(prob[item] == ""){
                        layer.msg(item + "不能为空！");
                        this.canSubmit = false;
                    }
                }
            }
            if(prob.sampleInput.length > 0){
                for(let item in prob.sampleInput){
                    if(prob.sampleInput[item] === ""){
                        layer.msg("第" + (item) + "个SampleInput不能为空！");
                        this.canSubmit = false;
                    }
                }
                for(let item in prob.sampleOutput){
                    if(prob.sampleOutput[item] === ""){
                        layer.msg("第" + (item) + "个sampleOutput不能为空！");
                        this.canSubmit = false;
                    }
                }
            }else{
                layer.msg("请添加sampleInput、sampleOutput！");
                this.canSubmit = false;
            }
            prob.file = document.getElementById('TotalData').files[0];
            if(prob.file && prob.file.type !== "application/x-zip-compressed"){
                layer.msg("文件格式必须为zip");
                this.canSubmit = false;
            }
            if(this.canSubmit){
                // TODO 提交
                $.ajax({
                    method: "post",
                    url: "/problem/edit",
                    data: JSON.stringify(prob),
                    dataType: "json",
                    contentType: "application/json",
                    async: false,
                    error: function(msg){
                        layer.msg("获取数据失败!" + msg.message);
                        return false;
                    },
                }).done(function(msg){
                    if(msg.status === 200){
                        layer.msg("新题目提交成功!");
                        return true;
                    }else{
                        showAjaxMsg(msg);
                        return false;
                    }
                });
            }else{
                return false;
            }
        },
    },
    template:"#add-problems",
});


/**
 * 新建比赛
 */
let addContests = Vue.extend({
    data: function(){
        return {
            isPublic: true,         // type 0
            isPrivate: false,       // type 1
            canSubmit: true,

            action: "new",
            description: "",
            frozenLengthDays: 0,
            frozenLengthHours: 0,
            frozenLengthMinutes: 0,
            lengthDays: 0,
            lengthHours: 0,
            lengthMinutes: 0,
            needFrozen: false,
            passwordRepeat: "",
            password: "",
            problemList: ["",],
            problemNameList: ["",],
            time: "",
            type: 0,
        };
    },
    ready: function(){
        initMD();
        $("#Begintime").datetimepicker({
            format: 'yyyy-mm-dd hh:mm',
            autoclose: true,
            todayBtn: true,
            startDate: "2013-02-14 10:00",
            minuteStep: 10
        });
    },
    methods: {
        /**
         * 设置比赛类型
         */
        setContestType: function(type){
            if(type == 0){
                this.isPublic = true;
                this.isPrivate = false;
                this.type = 0;
            }else{
                this.isPublic = false;
                this.isPrivate = true;
                this.type = 1;
            }
        },
        /**
         * 添加比赛题目
         */
        addProb: function(){
            this.problemList.push(this.problemList.length);
            this.problemNameList.push("");
        },
        /**
         * 删除比赛题目
         */
        delProb: function(i){
                this.problemList.splice(i, 1);
        },
        /**
         * 根据 ID 查找题目
         */
        findProb: function(id, index){
            $.ajax({
                method: 'get',
                url: "/data/"+id,
                async: true,
                error: function(msg){
                    layer.msg("获取数据失败!" + msg.message);
                    return false;
                },
            }).done(function(msg){
                if(msg.status === 200){
                    this.problemNameList[index] = msg.data.problemTitle || "题目存在";    // 自己设置下请求到的 title
                    return true;
                }else{
                    this.problemNameList[index] = "不存在ID为"+id+"的题目！";
                    return false;
                }
            });
            // if(0){
            //     this.problemNameList[index] = "name";
            // }else{
            //     this.problemNameList[index] = "不存在ID为"+id+"的题目！";
            // }
            // 使用此方法使 vue 检测到属性变化
            let temp = this.problemList;
            this.problemList = [];
            this.problemList = temp;
            console.log(this.problemNameList);
        },
        /**
         * 上传比赛
         */
        addContest: function(){
            let contest = {};
            this.canSubmit = true;
            contest.action = this.action;
            contest.description = this.description;

            contest.lengthDays = this.lengthDays;
            contest.lengthHours = this.lengthHours;
            contest.lengthMinutes = this.lengthMinutes;
            contest.needFrozen =  this.needFrozen;

            contest.problemList = this.problemList;
            contest.time =  this.time;
            contest.title = this.title;
            contest.type = this.type;
            if(this.type === 1){
                contest.password = this.password;
                // contest.passwordRepeat = this.passwordRepeat;
                if(this.password !== this.passwordRepeat){
                    layer.msg("两次输入的密码不相同！");
                    this.canSubmit = false;
                }else{
                    if(this.password.length < 6 || this.password.length > 20 ){
                        layer.msg("密码长度在6-20！");
                        this.canSubmit = false;
                    }
                }

            }
            if(this.needFrozen){
                contest.frozenLengthDays = this.frozenLengthDays;
                contest.frozenLengthHours = this.frozenLengthHours;
                contest.frozenLengthMinutes = this.frozenLengthMinutes;
            }
            for(let item in contest){
                if(contest[item] === undefined || contest[item] === ""){
                    layer.msg(item + "不能为空！");
                    this.canSubmit = false;
                }
            }
            if(contest.problemList.length > 0){
                for(let item in contest.problemList){
                    if(contest.problemList[item] === ""){
                        layer.msg("第" + (item) + "个Problem ID不能为空！");
                        this.canSubmit = false;
                    }
                }
                for(let item in contest.problemList){
                    if(contest.problemList[item] === ""){
                        layer.msg("第" + (item) + "个Problem ID不能为空！");
                        this.canSubmit = false;
                    }
                }
            }else{
                layer.msg("请添加Problem ID！");
                this.canSubmit = false;
            }
            contest.time =  + new Date(this.time);

            console.log(contest);
            if(this.canSubmit){
                // TODO 提交
                $.ajax({
                    method: "post",
                    url: "/contest/edit",
                    data: JSON.stringify(contest),
                    dataType: "json",
                    contentType: "application/json",
                    async: false,
                    error: function(msg){
                        layer.msg("获取数据失败!" + msg.message);
                        return false;
                    },
                }).done(function(msg){
                    if(msg.status === 200){
                        layer.msg("比赛提交成功!");
                        return true;
                    }else{
                        showAjaxMsg(msg);
                        return false;
                    }
                });
            }else{
                return false;
            }
        },
    },
    template:"#add-contests"
})

/**
 * 根模块
 */
let app = Vue.extend({
    data: function(){
        return {
            activePage: [true,false,false],
        }
    },
    ready: function(){
        if(this.$route.params.pId){
            let pId = this.$route.params.pId;
            let temp = [false,false,false];
            temp[pId] = true;
            this.activePage = temp;
        }
        console.log(this.activePage);
    },
    methods: {
        /**
         * 导航栏高亮
         */
        'setPageActive': function(p){
            let temp = [];
            for(let i = 0; i <　this.activePage.length; i++){
                temp[i] = false;
            }
            temp[p] = true;
            this.activePage = temp;
        },
    },

});

let router = new VueRouter();
router.map({
    "/": {
        name:"index",
        component: home,
    },
    "/newprob/:pId": {
        name:"newprob",
        component:addProb,
    },
    "/newcontests/:pId": {
        name: "newcontests",
        component:addContests,
    },
});
router.start(app,"#app")
