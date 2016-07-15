/**
 * Created by Sandwich on 2016/7/6.
 */
//回调地址
var redirect_uri = "http://localhost:8080/Client/index.jsp";
//授权服务器地址
var oauth_uri = "http://localhost:8080/AuthorizationServer/";
var resource_uri = "http://localhost:8080/ResourceServer/";

function redirUrl(){
    window.location.href="http://localhost:8080/AuthorizationServer/index.jsp?redirect="+redirect_uri;
}

function getMsg(code){
    var  myselect=document.getElementById("select");
    var index=myselect.selectedIndex ;
    var selectVal = myselect.options[index].value;
    var access_token = "";
    var open_id = "";
    //1.判断Cookie中是否存在access_token
    access_token = getCookie("access_token");
    if (typeof (access_token) == "undefined" || access_token.length == 2){
        //1.1不存在，则通过CODE先获取access_token
        if(code == "null"){   //CODE为空则提示先登录
            alert("请先登录！");
            return;
        }
        var url = oauth_uri+"OAuthController/backToken.do"+"?code="+code+"&oauhts="+selectVal;
        $.ajax({
            type:"GET",
            data:{},
            contentType:"application/json;charset=utf-8",
            url:url,
            success:function (result,statCode) {
                //解析JSON
                var obj = eval(result);
                var status = obj.statusCode;
                var msg = obj.msg;
                if(status != "200"){
                    alert(msg);
                    return;
                }
                var datas = obj.data;
                var obj1 = eval(datas);
                access_token = obj1[0].access_token;
                open_id = obj1[0].open_id;
                //获取资源
                getResource(access_token, open_id, selectVal);
            },
            error:function (result) {
                alert("系统出错！");
            }
        })
    } else {
        //1.2存在access_token,直接获取
        open_id = getCookie("open_id");
        //获取资源
        getResource(access_token, open_id,selectVal);
        return;
    }
}
 function getResource(access_token, open_id, oauhts){
     //通过access_token，open_id获取资源
     var url = resource_uri+"ResourceController/getResource.do"+
                "?access_token="+access_token+"&open_id="+open_id+"&oauhts="+oauhts;
     $.ajax({
         type:"GET",
         data:{},
         contentType:"application/json;charset=utf-8",
         url:url,
         success:function (result,statCode) {
             //解析JSON
             var obj = eval(result);
             var status = obj.statusCode;
             var msg = obj.msg;
             if(status != "200"){
                 alert(msg);
             }
             var datas = obj.data;
             document.getElementById("textA").value = datas;
         },
         error:function (result) {
             alert("系统出错！");
         }
     })
 }

function getCookie(name){               // 返回名为name的Cookie
    var str = document.cookie;          // 获取Cookie字符串
    if(!str || str.indexOf(name + "=") < 0) // 寻找name=
        return;
    var cookies = str.split("; ");      // 用;将所有的Cookie分隔开
    for(var i=0; i<cookies.length; i++){    // 遍历每个Cookie
        var cookie = cookies[i];        // 当前Cookie
        if(cookie.indexOf(name + "=") == 0){    // 如果名字为name
            var value = cookie.substring(name.length + 1);
            // 获取value
            return decodeURI(value);    // 将value解码，并返回
        }
    }
}