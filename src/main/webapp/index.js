"use strict"
let express = require("express");
let bodyParser = require("body-parser");
let app = express();
app.use(bodyParser.json({limit: '1mb'}));  //body-parser 解析json格式数据
app.use(bodyParser.urlencoded({            //此项必须在 bodyParser.json 下面,为参数编码
  extended: true
}));
app.use('/resources', express.static("resources"));
app.use('/', express.static("problem"));
app.use('/public', express.static('public'));
app.use('/manage', express.static('manage'));
app.use('/contest', express.static('contest'));
app.use('/status', express.static('status'));

// app.get("/", function(req, res){
//     res.
// });

app.get("/login", function(req, res){
    res.send("login");
});

app.post('/problem/search',function(req, res){
    let p = req.body.currentPage;
    let page = {
        "errors":{},
        "result":
            {
                "pageInfo":{
                    "countPerPage":15,
                    "currentPage":p,
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
        console.log("page.result=====>"+page.result);
        res.json(page);
});

app.post('/contest/search', function(req, res){
    let contest =  {
        "errors":{},
        "result":
            {
                "list":[
                    {"contestId":1,
                    "isVisible":true,
                    "length":3600000,
                    "status":"Ended",
                    "time":1459562400000,
                    "title":"The 14th UESTC Programming Contest Final Warmup",
                    "type":0,
                    "typeName":"Public"},
                    {"contestId":2,
                    "isVisible":true,
                    "length":43200000,
                    "status":"Ended",
                    "time":1458954000000,
                    "title":"The 14th UESTC Programming Contest Preliminary",
                    "type":3,
                    "typeName":"Invited"},
                ],
                "pageInfo":
                    {
                        "countPerPage":20,
                        "currentPage":2,
                        "displayDistance":2,
                        "totalItems":98,
                        "totalPages":2
                    },
            },
            "status":200,
        }
        res.json(contest);
});

app.post('/contest/data/:id', function(req, res){
    let contest = {
        "result":{
            "contest":
                {
                    "contestId":91,
                    "currentTime":1465533343447,
                    "description":"",
                    "endTime":1459566000000,
                    "frozenTime":0,
                    "isVisible":true,
                    "length":3600000,
                    "startTime":1459562400000,
                    "status":"Ended",
                    "timeLeft":0,
                    "title":"The 14th UESTC Programming Contest Final Warmup",
                    "type":0,"typeName":"Public"
                },
            "problemList":[
                {
                    "description":"Calculate $a+b$",
                    "hint":"####For``",
                    "input":"Two integer $a$,$b$ ($0<a,b<10$)",
                    "isSpj":false,
                    "javaMemoryLimit":65535,
                    "javaTimeLimit":3000,
                    "memoryLimit":65535,
                    "order":0,
                    "orderCharacter":"A",
                    "output":"Output $a+b$",
                    "problemId":1,
                    "sampleInput":"[\"1 2\",\"2 3\"]",
                    "sampleOutput":"[\"3\",\"5\"]",
                    "solved":0,
                    "source":"",
                    "timeLimit":1000,
                    "title":"A+B Problem",
                    "tried":0
                },
                {
                    "description":"Calculate $a+b$",
                    "hint":"####For``",
                    "input":"Two integer $a$,$b$ ($0<a,b<10$)",
                    "isSpj":false,
                    "javaMemoryLimit":65535,
                    "javaTimeLimit":3000,
                    "memoryLimit":65535,
                    "order":0,
                    "orderCharacter":"A",
                    "output":"Output $a+b$",
                    "problemId":2,
                    "sampleInput":"[\"1 2\",\"2 3\"]",
                    "sampleOutput":"[\"3\",\"5\"]",
                    "solved":0,
                    "source":"",
                    "timeLimit":1000,
                    "title":"HELLO WORLD",
                    "tried":0
                },
            ],
        },
        'status': 200,
    }
    res.json(contest);
})

app.post('/contest/loginContest', function(req, res){
    let contest = {
        "result":"success",
        'status': 200,
    }
    res.json(contest);
})

app.post('/status/search', function(req, res){
    console.log("problemId" + req.body.problemId);
    console.log("languageId" + req.body.languageId);
    let status = {
        "result":{
            "list":[
                {"caseNumber":2,"email":"virtual.judge.3@gmail.com","language":"C++","length":1030,"name":"赵子龙","nickName":"赵子龙","problemId":2,"returnType":"Wrong Answer on test 2","returnTypeId":5,"statusId":207780,"time":1465530869000,"userName":"Vjudge3"},
            ],
        "pageInfo":
            {
                "countPerPage":20,
                "currentPage":1,
                "displayDistance":2,
                "totalItems":89062,
                "totalPages":4454
            },
        },
        'status':200,
    };
    res.json(status);
    /*if(req.body.languageId === 0){
        let status = {
            "result":{
                "list":[
                    {"caseNumber":2,"email":"virtual.judge.3@gmail.com","language":"C++","length":1030,"name":"赵子龙","nickName":"赵子龙","problemId":2,"returnType":"Wrong Answer on test 2","returnTypeId":5,"statusId":207780,"time":1465530869000,"userName":"Vjudge3"},
                ],
            "pageInfo":
                {
                    "countPerPage":20,
                    "currentPage":1,
                    "displayDistance":2,
                    "totalItems":89062,
                    "totalPages":4454
                },
            },
            'status':200,
        };
        res.json(status);
    }else if(req.body.problemId){
        let status = {
            "result":{
                "list":[
                    {"caseNumber":2,"email":"virtual.judge.3@gmail.com","language":"C++","length":1030,"name":"赵子龙","nickName":"赵子龙","problemId":2,"returnType":"Wrong Answer on test 2","returnTypeId":5,"statusId":207780,"time":1465530869000,"userName":"Vjudge3"},
                    {"caseNumber":132,"email":"849199382@qq.com","language":"C++","length":1075,"memoryCost":1300,"name":"Chuck","nickName":"Evian Semitemos","problemId":1,"returnType":"Accepted","returnTypeId":1,"statusId":207778,"time":1465529784000,"timeCost":78,"userName":"FirstLast"},
                ],
            "pageInfo":
                {
                    "countPerPage":20,
                    "currentPage":2,
                    "displayDistance":2,
                    "totalItems":89062,
                    "totalPages":4454
                },
            },
            'status':200,
        };
        res.json(status);
    }else{
        let status = {
            "result":{
                "list":[
                    {
                        "caseNumber":26,
                        "email":"virtual.judge.2@gmail.com",
                        "language":"C++",
                        "length":1203,
                        "memoryCost":16604,
                        "name":"张翼德",
                        "nickName":"张翼德",
                        "problemId":1,
                        "returnType":"Accepted",
                        "returnTypeId":1,
                        "statusId":207781,
                        "time":1465530904000,
                        "timeCost":627,
                        "userName":"Vjudge2"
                    },
                    {"caseNumber":2,"email":"virtual.judge.3@gmail.com","language":"C++","length":1030,"name":"赵子龙","nickName":"赵子龙","problemId":2,"returnType":"Wrong Answer on test 2","returnTypeId":5,"statusId":207780,"time":1465530869000,"userName":"Vjudge3"},
                    {"caseNumber":132,"email":"849199382@qq.com","language":"C++","length":1075,"memoryCost":1300,"name":"Chuck","nickName":"Evian Semitemos","problemId":1,"returnType":"Accepted","returnTypeId":1,"statusId":207778,"time":1465529784000,"timeCost":78,"userName":"FirstLast"},
                    {"caseNumber":82,"email":"1182563586@qq.com","language":"C++","length":1681,"memoryCost":2064,"name":"zxy","nickName":"just_sort","problemId":2,"returnType":"Accepted","returnTypeId":1,"statusId":207776,"time":1465527841000,"timeCost":5,"userName":"just_sort"},
                    {"caseNumber":15,"email":"849199382@qq.com","language":"C++","length":548,"memoryCost":63356,"name":"Chuck","nickName":"Evian Semitemos","problemId":1,"returnType":"Accepted","returnTypeId":1,"statusId":207775,"time":1465525864000,"timeCost":70,"userName":"FirstLast"},
                    {"caseNumber":2,"email":"849199382@qq.com","language":"C++","length":548,"name":"Chuck","nickName":"Evian Semitemos","problemId":1,"returnType":"Runtime Error on test 2","returnTypeId":8,"statusId":207773,"time":1465525594000,"userName":"FirstLast"},
                    {"caseNumber":50,"email":"849199382@qq.com","language":"C++","length":812,"memoryCost":1876,"name":"Chuck","nickName":"Evian Semitemos","problemId":2,"returnType":"Accepted","returnTypeId":1,"statusId":207772,"time":1465523578000,"timeCost":22,"userName":"FirstLast"},
                    {"caseNumber":1,"email":"490168650@qq.com","language":"C++","length":3998,"name":"陈俊杰","nickName":"俊杰","problemId":2,"returnType":"Time Limit Exceeded on test 1","returnTypeId":3,"statusId":207771,"time":1465523481000,"userName":"cjj490168650"},
                    {"caseNumber":1,"email":"849199382@qq.com","language":"C++","length":786,"name":"Chuck","nickName":"Evian Semitemos","problemId":2,"returnType":"Wrong Answer on test 1","returnTypeId":5,"statusId":207770,"time":1465523468000,"userName":"FirstLast"},
                    {"caseNumber":1,"email":"490168650@qq.com","language":"C++","length":3978,"name":"陈俊杰","nickName":"俊杰","problemId":1,"returnType":"Time Limit Exceeded on test 1","returnTypeId":3,"statusId":207768,"time":1465523063000,"userName":"cjj490168650"},
                    {"caseNumber":1,"email":"490168650@qq.com","language":"C++","length":3949,"name":"陈俊杰","nickName":"俊杰","problemId":1,"returnType":"Time Limit Exceeded on test 1","returnTypeId":3,"statusId":207764,"time":1465522585000,"userName":"cjj490168650"},
                    {"caseNumber":1,"email":"490168650@qq.com","language":"C++","length":3856,"name":"陈俊杰","nickName":"俊杰","problemId":1,"returnType":"Runtime Error on test 1","returnTypeId":8,"statusId":207763,"time":1465522496000,"userName":"cjj490168650"},
                    {"caseNumber":50,"email":"849199382@qq.com","language":"C++","length":1922,"memoryCost":6928,"name":"Chuck","nickName":"Evian Semitemos","problemId":2,"returnType":"Accepted","returnTypeId":1,"statusId":207761,"time":1465491917000,"timeCost":207,"userName":"FirstLast"},
                    {"caseNumber":1,"email":"849199382@qq.com","language":"C++","length":1640,"name":"Chuck","nickName":"Evian Semitemos","problemId":2,"returnType":"Wrong Answer on test 1","returnTypeId":5,"statusId":207760,"time":1465490972000,"userName":"FirstLast"},
                    {"caseNumber":50,"email":"849199382@qq.com","language":"C++","length":791,"memoryCost":2568,"name":"Chuck","nickName":"Evian Semitemos","problemId":1,"returnType":"Accepted","returnTypeId":1,"statusId":207759,"time":1465489838000,"timeCost":73,"userName":"FirstLast"},
                    {"caseNumber":19,"email":"849199382@qq.com","language":"C++","length":788,"name":"Chuck","nickName":"Evian Semitemos","problemId":1,"returnType":"Wrong Answer on test 19","returnTypeId":5,"statusId":207757,"time":1465489268000,"userName":"FirstLast"},
                    {"caseNumber":4,"email":"870380501@qq.com","language":"C++","length":1503,"memoryCost":20496,"name":"DongHaiChen","nickName":"870380501","problemId":1,"returnType":"Accepted","returnTypeId":1,"statusId":207752,"time":1465484467000,"timeCost":726,"userName":"870380501"},
                    {"caseNumber":117,"email":"849199382@qq.com","language":"C++","length":1267,"memoryCost":6112,"name":"Chuck","nickName":"Evian Semitemos","problemId":1,"returnType":"Accepted","returnTypeId":1,"statusId":207746,"time":1465478736000,"timeCost":63,"userName":"FirstLast"},
                    {"caseNumber":1,"email":"870380501@qq.com","language":"C++","length":1397,"name":"DongHaiChen","nickName":"870380501","problemId":1,"returnType":"Wrong Answer on test 1","returnTypeId":5,"statusId":207745,"time":1465478316000,"userName":"870380501"},
                    {"caseNumber":1,"email":"870380501@qq.com","language":"C++","length":1331,"name":"DongHaiChen","nickName":"870380501","problemId":1,"returnType":"Wrong Answer on test 1","returnTypeId":5,"statusId":207744,"time":1465477168000,"userName":"870380501"}
                ],
            "pageInfo":
                {
                    "countPerPage":20,
                    "currentPage":1,
                    "displayDistance":2,
                    "totalItems":89062,
                    "totalPages":4454
                },
            },
            'status':200,
        };
        res.json(status);
    }
*/
});

app.get('/contest/rankList/:id', function(req, res){
    let list = {
    	status: 200,
    	result: {
    		rankList: {
    			lastFetched: 1465531198689,
    			problemList: [
    				{
    				solved: 7,
    				title: "1028",
    				tried: 44
    				},
    				{
    				solved: 1,
    				title: "1029",
    				tried: 20
    				},
    				{
    				solved: 2,
    				title: "1030",
    				tried: 29
    				},
    				{
    				solved: 2,
    				title: "1031",
    				tried: 17
    				},
    				{
    				solved: 1,
    				title: "1032",
    				tried: 9
    				}
    			],
    			rankList: [
    				{
    				email: "fsdfssef@woiefh",
    				itemList: [
    				{
    				firstBlood: false,
    				penalty: 280,
    				solved: true,
    				solvedTime: 5400000,
    				tried: -1,
    				triedAfterFrozen: false
    				},
    				{
    				firstBlood: false,
    				penalty: 3688,
    				solved: false,
    				solvedTime: 222222,
    				tried: 0,
    				triedAfterFrozen: false
    				},
    				{
    				firstBlood: true,
    				penalty: 2820,
    				solved: true,
    				solvedTime: 2820000,
    				tried: 0,
    				triedAfterFrozen: false
    				},
    				{
    				firstBlood: false,
    				penalty: 1699,
    				solved: true,
    				solvedTime: 1699000,
    				tried: 0,
    				triedAfterFrozen: false
    				},
    				{
    				firstBlood: true,
    				penalty: 5487,
    				solved: true,
    				solvedTime: 5487000,
    				tried: 0,
    				triedAfterFrozen: false
    				}
    				],
    				name: "bugs",
    				nickName: "千里之外",
    				penalty: 13974,
    				rank: 1,
    				reallyName: "送你离开",
    				solved: 5,
    				tried: 5
    				}
    			]
    		}
    	}
    };
    res.json(list);
});

app.listen(8886, function(){
    console.log("connected !");
});
