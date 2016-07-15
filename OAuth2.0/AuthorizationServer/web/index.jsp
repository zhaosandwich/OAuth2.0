<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>AuthorizationServer</title>
      <link rel="stylesheet" href="css/index.css">
      <script type="text/javascript" src="js/jquery.min.js"></script>
      <script type="text/javascript" src="js/index.js"></script>
  </head>
  <body>
  <div class="content">
    <h1>AuthorizationServer</h1>
    <table>
      <form>
          <tr><td class="tips">*</td><td>账号:</td><td colspan="2"><input id="userName" type="text" name="userName"></td></tr>
          <tr><td class="tips">*</td><td>密码:</td><td colspan="2"><input id="pwd" type="password" name="pwd"></td></tr>
          <tr>
            <td class="tips">*</td>
            <td>选择授权信息:</td>
            <td colspan="2">
                <input type="checkbox" name="oauth" value="userInfo">用户信息
                <input type="checkbox" name="oauth" value="oauthLog">授权日志
            </td>
          </tr>
          <tr>
            <td></td>
            <td class="btn" colspan="3">
              <input type="reset" value="重置" name="submit">
              <input type="button" value="提交" name="submit" onclick="commit('<%=request.getParameter("redirect")%>')">
            </td>
          </tr>
      </form>
    </table>
  </div>
  </body>
</html>
