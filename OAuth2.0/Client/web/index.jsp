<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Client</title>
    <link rel="stylesheet" type="text/css" href="css/client.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/client.js"></script>
  </head>
  <body>
      <div class="content">
        <table>
          <tr>
            <td class="title" colspan="2"><h1>第三方网页</h1></td>
          </tr>
          <tr>
            <td>用户状态:</td>
            <td>
              <%
                String code = request.getParameter("code");
                if (code != null){
                %>
                已登陆
              <%
                } else {
                %>
                尚未登陆
              <%
                }
                %>
            </td>
          </tr>
          <tr>
            <td colspan="2"><input type="button" value="登陆授权" onclick="redirUrl()"></td>
          </tr>
          <tr>
            <td colspan="2">
              查看信息:
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <select id="select">
                <option value="userInfo">------用户信息------</option>
                <option value="oauthLog">------授权日志------</option>
              </select>
              <input class="btn" type="button" value="提交" onclick="getMsg('<%=request.getParameter("code")%>')">
            </td>
          </tr>
          <tr>
            <td colspan="2">
              详细信息:
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <textarea id="textA"></textarea>
            </td>
          </tr>
        </table>
      </div>
  </body>
</html>
