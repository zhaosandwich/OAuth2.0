/**
 * Created by Sandwich on 2016/7/7.
 */
var host = "http://localhost:8080";
function commit(redirect){
    var pwd = document.getElementById("pwd").value.trim();
    var userName = document.getElementById("userName").value.trim();
    var oauths = document.getElementsByName("oauth");
    var oauth = "";
    var len = oauths.length;
    for(i=0 ; i<len; i++){
        if(oauths[i].checked == true){
            oauth = oauth + oauths[i].value+";";
        }
    }
    if(pwd == "" || userName == "" || oauth ==""){
        alert("请将信息填写完整！");
        return;
    }
    //获取上下文路径
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    //主机地址
    var curWwwPath=window.document.location.href;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    //请求URL
    var url = localhostPaht+projectName+"/OAuthController/userInfo.do?redirect="
        +redirect+"&userName="+userName+"&pwd="+pwd+"&oauth="+oauth;

    $.ajax({
        type:"GET",
        data:{},
        contentType:"application/json;charset=utf-8",
        url:url,
        success:function (result,statCode) {
            //解析JSON
            var obj = eval(result);
            var status = obj.statusCode;
            var code = "";
            var redirUrl = "";
            if(status == "301"){
                alert("账号密码不正确！");
                return;
            }
            var datas = obj.data;
            var obj1 = eval(datas);
            code = obj1[0].code;
            redirUrl = obj1[0].redirUrl;
            //alert(obj1[0].code);
            //alert(obj1[0].redirUrl);
            //重定向回Client
            window.location.href=redirUrl+"?code="+code;
        },
        error:function (result) {
            alert("系统出错！");
        }
    })

}
