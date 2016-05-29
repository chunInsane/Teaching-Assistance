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

app.listen(8888, function(){
    console.log("connected !");
});
