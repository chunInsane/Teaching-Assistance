"use strict"
let express = require("express");
let app = express();

// app.use('/', express.static("staticPage/Home"));
app.use('/public', express.static('public'));

app.get("/login", function(req, res){
    res.send("login");
});

app.listen(8888, function(){
    console.log("connected !");
});
