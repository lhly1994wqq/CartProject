<%@ page language="java" contentType="text/html;charset=UTF-8" 
    pageEncoding="UTF-8" import="java.util.* , ModelLayer.OrderItem" isELIgnored="false" %>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<table border="1" width="400px" align="center" cellspacing="0">
    <tr>
        <td>商品名称</td>
        <td>单价</td>
        <td>数量</td>
        <td>总价</td>
        <td>操作</td>
    </tr>
    
    <c:forEach items="${order}" var="oi" varStatus="st" >
        <tr>
            <td>${oi.product.name}</td>
            <td><fmt:formatNumber type="number" value="${oi.product.price}" minFractionDigits="2" /></td>
            <td><fmt:formatNumber type="number" value="${oi.number }" minFractionDigits="2" /></td>
            <td><fmt:formatNumber type="number" value="${oi.number*oi.product.price}" minFractionDigits="2" /></td>

            <td><a href="deleteOrderItem?name=${oi.product.name}">删除</a></td>
        </tr>
    </c:forEach>
    
    <tr>
    <td colspan="5" ><a href="ordercreateServlet">生成订单</a></td>
    </tr>
</table>