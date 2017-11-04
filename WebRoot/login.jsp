<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="UTF-8" import="java.util.*" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="userloginServlet" method="POST" >
账号：<input type="text" name="name" width="100px"> <br>
密码：<input type="password" name="password" width="100px"> <br>
<button type="submit" >登录</button>
</form>
