<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>My stock View</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<h2 class="sub-header">当前股价</h2>
<div class="btn-group" role="group" aria-label="...">
	<button type="button" class="btn btn-default" onclick="refresh()">刷新</button>
	<button type="button" class="btn btn-default">重置</button>
</div>
<script>
	function refresh(){
		xmlhttp=new XMLHttpRequest();
		xmlhttp.open("GET","./refresh",true);
		xmlhttp.send();
	}
</script>
<table  class="table table-striped">
	<thead>
	<tr>
		<td>股票代码</td>
		<td>股票名称</td>
		<td>价格</td>
		<td>涨跌幅</td>
		<td>最高</td>
		<td>最低</td>
		<td>交易手数</td>
		<td>换手率</td>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="record" items="${records}">
	<tr>
		<td>${record.getKey()}</td>
		<td>${record.getValue().get("name")}</td>
		<td>${record.getValue().get("nowPrice")}</td>
		<td>${record.getValue().get("float")}</td>
		<td>${record.getValue().get("high")}</td>
		<td>${record.getValue().get("low")}</td>
		<td>${record.getValue().get("exchangeCount")}</td>
		<td>${record.getValue().get("exchangeRota")}</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>
