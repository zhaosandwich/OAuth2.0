# OAuth2.0
1.Client 模拟第三方登录,部署时，上下文路径为Client
2.AuthorizationServer 模拟授权服务器,部署时，上下文路径为AuthorizationServer
3.ResourceServer 模拟资源服务器,部署时，上下文路径为ResourceServer

#工程部署
 关于上下文路径的问题，参考Client工程的，client.js文件
先把OAuth2.0工程导出，再以moduled导入各个模块，jar包用maven管理

#DB
create table userinfo(
userName varchar2(10),
pwd varchar2(10),
openId varchar2(32)
)

create table contentinfo(
content varchar2(50),
openId varchar2(32)
)



#联系
详细问题，可以QQ2399553530

