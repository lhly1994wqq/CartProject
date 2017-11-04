<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<!--
      用于ajax提交数据并且不必跳转页面
//-->
    var xmlhttp;
    
    function addtoCart(button_1){
        var form = button_1.form;
        var num = form.children[0].value;
        var pid = form.children[1].value;
        
        var url = "http://localhost:8080/carttest/addOrderItem?num="+num+"&pid="+pid;
        
        xmlhttp = new XMLHttpRequest();
        
        xmlhttp.onreadystatechange = function(){
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
             button_1.innerHTML="<font color='lightgray' >加入购物车</font>"
             button_1.disable=true;
            }
          
        };//响应函数
    
        xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
    
 
</script>


<c:if test="${!empty user}">
<div align="center">当前用户：${user.name}</div>
</c:if>
<table align='center' border='1' cellspacing='0'>
	<tr>
		<td>id</td>
		<td>名称</td>
		<td>价格</td>
		<td>购买</td>
	</tr>
	<c:forEach items="${ProductList}" var="product" varStatus="st">
		<tr>
			<td>${product.id}</td>
			<td>${product.name}</td>
			<td>${product.price}</td>
			<td>
			
			<form>
			数量<input type="text" value="1">
			<input type="hidden" value="${product.id}">
			<button type="button" onclick="addtoCart(this)" >加入购物车</button>
			</form>
		</tr>
	</c:forEach>
	<tr><td><a href="listOrderItem" style="decoration:none">查看购物车</a></td></tr>
</table>
